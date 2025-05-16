package com.rentadeherramientas.rentadeherramientas.aplication.services;


import java.util.List;


import com.rentadeherramientas.rentadeherramientas.domain.entity.User;
public interface UserService {
    List<User> findAll();

    User save(User user);

    boolean existsByUsername(String username);

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    Object getAdminById(Long id);

    User createAdmin(User user);

    User updateAdmin(Long id, User updatedUser);

    void deleteAdmin(Long id);

    List<User> getAllAdmins();

    User registrOneCustomer(SaveUser newUser);

    Object findOneByUsername(String username);
}
