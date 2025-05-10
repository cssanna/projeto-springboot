CREATE TABLE instrutor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracaoHoras DOUBLE NOT NULL,
    instrutor_id BIGINT,
    FOREIGN KEY (instrutor_id) REFERENCES instrutor(id)
);

CREATE TABLE log_curso (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    curso_id BIGINT,
    titulo VARCHAR(255),
    data_criacao DATE,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);