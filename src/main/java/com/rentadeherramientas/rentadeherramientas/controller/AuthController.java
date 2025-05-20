package com.rentadeherramientas.rentadeherramientas.controller;
import com.rentaherramientas.dto.ApiResponse;
import com.rentaherramientas.dto.LoginRequest;
import com.rentaherramientas.dto.LoginResponse;
import com.rentaherramientas.dto.RegistroClienteRequest;
import com.rentaherramientas.dto.RegistroProveedorRequest;
import com.rentaherramientas.dto.UsuarioDTO;
import com.rentaherramientas.model.Cliente;
import com.rentaherramientas.model.Proveedor;
import com.rentaherramientas.model.Usuario;
import com.rentaherramientas.repository.ClienteRepository;
import com.rentaherramientas.repository.ProveedorRepository;
import com.rentaherramientas.repository.UsuarioRepository;
import com.rentaherramientas.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol()
        );

        return ResponseEntity.ok(new LoginResponse(jwt, usuarioDTO));
    }

    @PostMapping("/registro/cliente")
    public ResponseEntity<?> registrarCliente(@RequestBody RegistroClienteRequest request) {
        try {
            if (usuarioRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Email ya está en uso"));
            }

            Cliente cliente = new Cliente(
                request.getNombre(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getTelefono(),
                request.getDireccion()
            );

            Cliente clienteGuardado = clienteRepository.save(cliente);
            
            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Cliente registrado exitosamente"));
                
        } catch (Exception e) {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Error al registrar el cliente: " + e.getMessage()));
        }
    }

    @PostMapping("/registro/proveedor")
    public ResponseEntity<?> registrarProveedor(@RequestBody RegistroProveedorRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            return new ResponseEntity<>("Email ya está en uso", HttpStatus.BAD_REQUEST);
        }

        Proveedor proveedor = new Proveedor(
                request.getNombre(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getEmpresa(),
                request.getContacto()
        );

        Proveedor proveedorGuardado = proveedorRepository.save(proveedor);
        
        UsuarioDTO usuarioDTO = new UsuarioDTO(
                proveedorGuardado.getId(),
                proveedorGuardado.getNombre(),
                proveedorGuardado.getEmail(),
                proveedorGuardado.getRol()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }
}
