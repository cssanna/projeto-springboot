AtividadeJPA - Documentação do Projeto
Descrição
Este projeto é uma aplicação Spring Boot que utiliza JPA (Java Persistence API) para gerenciamento de entidades em um banco de dados relacional. A aplicação gerencia uma entidade User e fornece endpoints REST para operações CRUD.
Pré-requisitos

Java 17 ou superior
Maven 3.8+
Docker e Docker Compose
cURL ou Postman (para testar endpoints)

Instruções para Iniciar o Projeto
1. Iniciar o Docker
O projeto utiliza um banco de dados PostgreSQL configurado via Docker. Para iniciar o container:
docker-compose up -d

O comando acima inicia o PostgreSQL na porta 5432. Certifique-se de que a porta está livre.
2. Iniciar o Projeto
Clone o repositório e navegue até o diretório do projeto:
git clone <URL_DO_REPOSITORIO>
cd atividadeJPA

Compile e inicie a aplicação com Maven:
mvn clean install
mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.
Testando Endpoints
Os endpoints podem ser testados com cURL ou Postman. Abaixo estão exemplos usando cURL:
Criar um Usuário
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name":"João","email":"joao@example.com"}'

Listar Todos os Usuários
curl -X GET http://localhost:8080/api/users

Buscar Usuário por ID
curl -X GET http://localhost:8080/api/users/1

Atualizar Usuário
curl -X PUT http://localhost:8080/api/users/1 -H "Content-Type: application/json" -d '{"name":"João Silva","email":"joao.silva@example.com"}'

Deletar Usuário
curl -X DELETE http://localhost:8080/api/users/1

No Postman, configure os métodos HTTP, URLs e payloads JSON conforme os exemplos acima.
Executando Testes
Os testes unitários e de integração estão localizados em src/test/java. Para executá-los:
mvn test

Os testes cobrem as operações CRUD do repositório e os endpoints REST, utilizando o framework JUnit e o banco H2 em memória para testes de integração.
Boas Práticas Aplicadas

Arquitetura Limpa: O projeto segue uma estrutura em camadas (Controller, Service, Repository), promovendo separação de responsabilidades.
Injeção de Dependências: Utiliza Spring para gerenciar dependências, facilitando testes e manutenção.
Validação de Dados: Aplica anotações como @NotNull e @Email para validar entradas.
Tratamento de Exceções: Implementa ControllerAdvice para respostas de erro consistentes.
Testes Automatizados: Inclui testes unitários e de integração para garantir robustez.

Comparação entre JDBC e JPA
JDBC (Java Database Connectivity) é uma API de baixo nível que permite executar consultas SQL diretamente, oferecendo controle total sobre as interações com o banco. Contudo, exige escrita manual de queries e mapeamento de resultados para objetos, o que pode ser trabalhoso e propenso a erros em aplicações complexas.
JPA, por outro lado, é uma especificação de ORM (Object-Relational Mapping) que abstrai a camada de persistência. Com JPA (e implementações como Hibernate), mapeamos entidades para tabelas usando anotações ou XML, e operações CRUD são simplificadas com métodos prontos (e.g., save, findById). Isso reduz o código boilerplate e facilita a manutenção, mas pode introduzir overhead em cenários de alta performance.
Quando usar:

JDBC: Ideal para aplicações simples ou com consultas altamente otimizadas.
JPA: Preferível para projetos complexos com muitas entidades e relacionamentos, onde produtividade e manutenibilidade são prioridades.

Conclusão: Este projeto optou por JPA para aproveitar sua simplicidade e produtividade, mantendo um código limpo e escalável.
