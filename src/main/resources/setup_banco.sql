-- Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS gerenciador_tarefas;
USE gerenciador_tarefas;

-- O Spring Boot cria a tabela sozinho, mas deixar o script aqui
-- serve como documentação do que o sistema precisa.
CREATE TABLE IF NOT EXISTS tarefa (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      titulo VARCHAR(255) NOT NULL,
    status VARCHAR(50) DEFAULT 'pendente'
    );

-- Dados iniciais para teste
INSERT INTO tarefa (titulo, status) VALUES ('Estudar Spring Boot', 'pendente');