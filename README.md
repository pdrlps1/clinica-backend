# Sistema de Gestão de Consultas Médicas

Sistema backend desenvolvido em Java com Spring Boot para gerenciamento de consultas médicas, incluindo cadastro de pacientes, médicos, agendamento de consultas e prescrição de receitas.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Data JPA** - Persistência de dados
- **MySQL 8** - Banco de dados relacional
- **Lombok** - Redução de código boilerplate
- **SpringDoc OpenAPI (Swagger)** - Documentação da API
- **Maven** - Gerenciador de dependências

## Funcionalidades

### Módulo de Pacientes
- Cadastro, edição, listagem e exclusão de pacientes
- Validação de email único
- Informações: nome, email, telefone, data de nascimento e endereço

### Módulo de Médicos
- Cadastro, edição, listagem e exclusão de médicos
- Validação de email e CRM únicos
- Informações: nome, email, CRM, especialidade e telefone

### Módulo de Consultas
- Agendamento de consultas médicas
- Atualização de data/hora e status
- Status disponíveis: AGENDADA, CANCELADA, CONCLUIDA
- Vinculação com paciente e médico
- Campo para observações

### Módulo de Receitas
- Prescrição de receitas vinculadas a consultas
- Informações: medicamento, dosagem e instruções
- Listagem por consulta

## Estrutura do Projeto

```
baseSpring/
├── src/
│   ├── main/
│   │   ├── java/projetos/baseSpring/
│   │   │   ├── Config/
│   │   │   │   ├── CorsConfig.java          # Configuração CORS
│   │   │   │   └── SwaggerConfig.java       # Configuração Swagger
│   │   │   ├── Controller/
│   │   │   │   ├── ClienteController.java
│   │   │   │   ├── PacienteController.java
│   │   │   │   ├── MedicoController.java
│   │   │   │   ├── ConsultaController.java
│   │   │   │   └── ReceitaController.java
│   │   │   ├── Service/
│   │   │   │   ├── ClienteService.java
│   │   │   │   ├── PacienteService.java
│   │   │   │   ├── MedicoService.java
│   │   │   │   ├── ConsultaService.java
│   │   │   │   └── ReceitaService.java
│   │   │   ├── model/
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Paciente.java
│   │   │   │   ├── Medico.java
│   │   │   │   ├── Consulta.java
│   │   │   │   └── Receita.java
│   │   │   ├── repository/
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   ├── PacienteRepository.java
│   │   │   │   ├── MedicoRepository.java
│   │   │   │   ├── ConsultaRepository.java
│   │   │   │   └── ReceitaRepository.java
│   │   │   ├── dto/
│   │   │   │   ├── Paciente/
│   │   │   │   │   ├── PacienteRequest.java
│   │   │   │   │   └── PacienteResponse.java
│   │   │   │   ├── Medico/
│   │   │   │   │   ├── MedicoRequest.java
│   │   │   │   │   └── MedicoResponse.java
│   │   │   │   ├── Consulta/
│   │   │   │   │   ├── ConsultaRequest.java
│   │   │   │   │   └── ConsultaResponse.java
│   │   │   │   └── Receita/
│   │   │   │       ├── ReceitaRequest.java
│   │   │   │       └── ReceitaResponse.java
│   │   │   └── BaseSpringApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Pré-requisitos

- **Java JDK 17** ou superior
- **Maven 3.6+**
- **MySQL 8.0+**
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code)

## Configuração do Banco de Dados

### 1. Criar o banco de dados no MySQL

```sql
CREATE DATABASE consultas_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Configurar credenciais

Edite o arquivo `src/main/resources/application.properties` e ajuste as seguintes propriedades:

```properties
# URL do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/consultas_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC

# Credenciais do MySQL
spring.datasource.username=root
spring.datasource.password=SUA_SENHA_AQUI
```

**IMPORTANTE**: Altere `SUA_SENHA_AQUI` para a senha do seu usuário root do MySQL.

## Como Executar o Projeto

### Opção 1: Via Maven (Linha de Comando)

1. Clone o repositório e navegue até a pasta do projeto:
```bash
cd baseSpring
```

2. Compile o projeto:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

### Opção 2: Via IDE

1. Importe o projeto como **Maven Project**
2. Aguarde o download das dependências
3. Execute a classe `BaseSpringApplication.java` (botão Run/Debug)

### Opção 3: Via JAR compilado

1. Compile o projeto:
```bash
mvn clean package
```

2. Execute o JAR gerado:
```bash
java -jar target/baseSpring-0.0.1-SNAPSHOT.jar
```

## Acessando a Aplicação

Após iniciar o projeto, a aplicação estará disponível em:

- **API Base URL**: `http://localhost:8080`
- **Swagger UI (Documentação interativa)**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## Endpoints da API

### Pacientes (`/api/pacientes`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/api/pacientes` | Criar novo paciente |
| GET    | `/api/pacientes` | Listar todos os pacientes |
| GET    | `/api/pacientes/{id}` | Buscar paciente por ID |
| PUT    | `/api/pacientes/{id}` | Atualizar paciente |
| DELETE | `/api/pacientes/{id}` | Excluir paciente |

### Médicos (`/api/medicos`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/api/medicos` | Criar novo médico |
| GET    | `/api/medicos` | Listar todos os médicos |
| GET    | `/api/medicos/{id}` | Buscar médico por ID |
| PUT    | `/api/medicos/{id}` | Atualizar médico |
| DELETE | `/api/medicos/{id}` | Excluir médico |

### Consultas (`/api/consultas`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/api/consultas` | Agendar nova consulta |
| GET    | `/api/consultas` | Listar todas as consultas |
| GET    | `/api/consultas/{id}` | Buscar consulta por ID |
| PATCH  | `/api/consultas/{id}/data-hora?valor={datetime}` | Atualizar data/hora |
| PATCH  | `/api/consultas/{id}/status?valor={status}` | Alterar status |
| DELETE | `/api/consultas/{id}` | Excluir consulta |

### Receitas (`/api/receitas`)

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST   | `/api/receitas` | Criar nova receita |
| GET    | `/api/receitas` | Listar todas as receitas |
| GET    | `/api/receitas/{id}` | Buscar receita por ID |
| GET    | `/api/receitas/por-consulta/{consultaId}` | Listar receitas de uma consulta |
| PUT    | `/api/receitas/{id}` | Atualizar receita |
| DELETE | `/api/receitas/{id}` | Excluir receita |

## Exemplos de Requisições

### Criar Paciente

```bash
POST http://localhost:8080/api/pacientes
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "telefone": "(11) 98765-4321",
  "dataNascimento": "1990-05-15",
  "endereco": "Rua das Flores, 123, São Paulo - SP"
}
```

### Criar Médico

```bash
POST http://localhost:8080/api/medicos
Content-Type: application/json

{
  "nome": "Dra. Maria Santos",
  "email": "maria.santos@hospital.com",
  "crm": "123456-SP",
  "especialidade": "Cardiologia",
  "telefone": "(11) 3456-7890"
}
```

### Agendar Consulta

```bash
POST http://localhost:8080/api/consultas
Content-Type: application/json

{
  "dataHora": "2025-11-01T14:30:00",
  "pacienteId": 1,
  "medicoId": 1,
  "observacoes": "Paciente relata dores no peito"
}
```

### Criar Receita

```bash
POST http://localhost:8080/api/receitas
Content-Type: application/json

{
  "consultaId": 1,
  "medicamento": "Paracetamol 500mg",
  "dosagem": "1 comprimido",
  "instrucoes": "Tomar de 8 em 8 horas, durante 5 dias"
}
```

## Configurações Adicionais

### CORS
O projeto está configurado para aceitar requisições da porta `5173` (Vite/React). Para alterar, edite:
- `src/main/java/projetos/baseSpring/Config/CorsConfig.java:17`

### Pool de Conexões (HikariCP)
Configurado no `application.properties`:
- Máximo de conexões: 10
- Mínimo de conexões ociosas: 5
- Timeout: 30 segundos

### Hibernate
- **DDL Auto**: `update` (cria/atualiza tabelas automaticamente)
- **Show SQL**: Ativado (exibe queries no console)
- **Format SQL**: Ativado (formata queries no console)

## Solução de Problemas

### Erro de conexão com MySQL
- Verifique se o MySQL está rodando: `systemctl status mysql` (Linux) ou Services (Windows)
- Confirme usuário e senha no `application.properties`
- Certifique-se que o banco `consultas_db` foi criado

### Erro "Table doesn't exist"
- Com `ddl-auto=update`, as tabelas são criadas automaticamente na primeira execução
- Caso persista, verifique os logs para erros de DDL

### Porta 8080 já em uso
Adicione ao `application.properties`:
```properties
server.port=8081
```

## Estrutura de Dados

### Relacionamentos
- **Paciente** 1:N **Consulta** - Um paciente pode ter várias consultas
- **Médico** 1:N **Consulta** - Um médico pode atender várias consultas
- **Consulta** 1:N **Receita** - Uma consulta pode gerar várias receitas

### Validações
- Email único para Paciente e Médico
- CRM único para Médico
- Campos obrigatórios validados via Bean Validation
- Foreign Keys garantem integridade referencial

## Testes

Para executar os testes unitários:

```bash
mvn test
```

## Construído com

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Persistência
- [MySQL](https://www.mysql.com/) - Banco de Dados
- [Lombok](https://projectlombok.org/) - Geração de código
- [SpringDoc](https://springdoc.org/) - Documentação OpenAPI/Swagger
- [Maven](https://maven.apache.org/) - Gerenciamento de Dependências

## Autor

Desenvolvido como projeto acadêmico - 5º termo ADS

## Licença

Este projeto é de código aberto e está disponível para fins educacionais.
