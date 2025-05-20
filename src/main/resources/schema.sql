-- Create sequence if not exists
CREATE SEQUENCE IF NOT EXISTS usuario_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

-- Create tables if not exist
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT PRIMARY KEY DEFAULT nextval('usuario_seq'),
    email VARCHAR(255) UNIQUE NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
    id BIGINT PRIMARY KEY REFERENCES usuario(id),
    telefono VARCHAR(20),
    direccion VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS proveedor (
    id BIGINT PRIMARY KEY REFERENCES usuario(id),
    empresa VARCHAR(255),
    contacto VARCHAR(20)
);