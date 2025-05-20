-- Insertar Admin
INSERT INTO usuario (id, email, nombre, password, rol) 
VALUES (1, 'admin@rentaherramientas.com', 'Administrador Principal', 
'$2a$10$xZtcGEGRH9Ztq9qTqUGqR.EHgQ6.PEP0FdF9OLaU0RzEiUPRRDESu', 'ROLE_ADMIN')
ON CONFLICT (id) DO NOTHING;

-- Insertar Proveedores
INSERT INTO usuario (id, email, nombre, password, rol) 
VALUES 
(2, 'proveedor1@ejemplo.com', 'Herramientas del Norte', '$2a$10$2Xt8PpS8OqH3ynHGGMFM8.qO8SqEOJfwNz5GDmXNQEzlEEEHUPmKy', 'ROLE_PROVEEDOR'),
(3, 'proveedor2@ejemplo.com', 'Constructores Unidos', '$2a$10$2Xt8PpS8OqH3ynHGGMFM8.qO8SqEOJfwNz5GDmXNQEzlEEEHUPmKy', 'ROLE_PROVEEDOR')
ON CONFLICT (id) DO NOTHING;

INSERT INTO proveedor (id, empresa, contacto) 
VALUES 
(2, 'Herramientas del Norte S.A.', '3001234567'),
(3, 'Constructores Unidos Ltda', '3157894561')
ON CONFLICT (id) DO NOTHING;

-- Insertar Clientes
INSERT INTO usuario (id, email, nombre, password, rol) 
VALUES 
(7, 'cliente1@ejemplo.com', 'Juan Pérez', '$2a$10$2Xt8PpS8OqH3ynHGGMFM8.qO8SqEOJfwNz5GDmXNQEzlEEEHUPmKy', 'ROLE_CLIENTE'),
(8, 'cliente2@ejemplo.com', 'María López', '$2a$10$2Xt8PpS8OqH3ynHGGMFM8.qO8SqEOJfwNz5GDmXNQEzlEEEHUPmKy', 'ROLE_CLIENTE')
ON CONFLICT (id) DO NOTHING;

INSERT INTO cliente (id, telefono, direccion) 
VALUES 
(7, '3001234567', 'Calle 123 #45-67'),
(8, '3157894561', 'Carrera 78 #90-12')
ON CONFLICT (id) DO NOTHING;