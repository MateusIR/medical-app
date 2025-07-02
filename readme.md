# Medical App API

Uma API RESTful para gerenciamento de consultas médicas, desenvolvida com Spring Boot e PostgreSQL.

## Tecnologias Utilizadas

- **Backend**:
  - Java 21
  - Spring Boot 3.5.3
  - Spring Data JPA
  - Spring Security
  - JWT (JSON Web Tokens) para autenticação
  - Lombok
  - Jakarta Validation
  - Maven

- **Banco de Dados**:
  - PostgreSQL (em container Docker)
  - Hibernate

- **Outras Ferramentas**:
  - Docker (para containerização do PostgreSQL)
  - Dotenv Java (para gerenciamento de variáveis de ambiente)

## Funcionalidades Principais

### Autenticação e Segurança
- Autenticação via JWT
- Proteção de rotas com Spring Security
- Criptografia de senhas com BCrypt

### Gerenciamento de Médicos
- Cadastro de médicos com especialidades (Ortopedia, Cardiologia, Ginecologia, Dermatologia)
- Listagem paginada de médicos ativos
- Atualização de informações médicas
- Desativação de médicos (exclusão lógica)
- Detalhamento de informações médicas

### Gerenciamento de Pacientes
- Cadastro de pacientes
- Listagem paginada de pacientes ativos
- Atualização de informações do paciente
- Desativação de pacientes (exclusão lógica)
- Detalhamento de informações do paciente

### Agendamento de Consultas
- Agendamento de consultas com validações:
  - Antecedência mínima de 1 hora
  - Horário de funcionamento (6h às 19h, exceto domingos)
  - Disponibilidade do médico
  - Existência do paciente
- Cancelamento de consultas (com antecedência mínima de 24 horas)
- Detalhamento de consultas

### Tratamento de Erros
- Tratamento centralizado de exceções
- Mensagens de erro personalizadas
- Validação de dados de entrada


## Como Executar

### Pré-requisitos
- Java 21
- Maven
- Docker

### Configuração
1. Crie um arquivo `.env` na raiz do projeto com as suas variáveis:
   ```
   POSTGRES_PORT=5432
   POSTGRES_DB=SEU_DB
   POSTGRES_USER=SEU_USER
   POSTGRES_PASSWORD=SUA_SENHA
   ```

### Executando com Docker
1. Inicie o container do PostgreSQL:
   ```bash
   docker-compose up -d
   ```
2. Execute a aplicação Spring Boot:
   ```bash
   mvn spring-boot:run
   ```


## Como Criar um Usuário Adm


Para criar um usuário administrativo diretamente no banco de dados, siga estes passos:

1. Acesse o banco de dados PostgreSQL:
   ```bash
   docker exec -it postgres-db psql -U SEU_USER -d SEU_DB
   ```

2. Execute o comando SQL para inserir um novo usuário:
   ```sql
   INSERT INTO users (name, password) 
   VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.Mrq4HkJpWrDi7WXjNZN1z1vZ6U0QY8a');
   ```
   (A senha no exemplo é "senha123" criptografada com BCrypt)

3. Verifique se o usuário foi criado:
   ```sql
   SELECT * FROM users;
   ```

4. Saia do PostgreSQL:
   ```sql
   \q
   ```

**Nota:** Para gerar seu próprio hash BCrypt, você pode usar este site online: https://bcrypt-generator.com/


## Endpoints da API

### Autenticação
- `POST /login` - Autentica um usuário e retorna um token JWT

### Médicos
- `POST /medicos` - Cadastra um novo médico
- `GET /medicos` - Lista médicos ativos
- `PUT /medicos` - Atualiza informações de um médico
- `DELETE /medicos/{id}` - Desativa um médico
- `GET /medicos/{id}` - Retorna detalhes de um médico específico

### Pacientes
- `POST /pacientes` - Cadastra um novo paciente
- `GET /pacientes` - Lista pacientes ativos
- `PUT /pacientes` - Atualiza informações de um paciente
- `DELETE /pacientes/{id}` - Desativa um paciente
- `GET /pacientes/{id}` - Retorna detalhes de um paciente específico

### Consultas
- `POST /consultas` - Agenda uma nova consulta
- `DELETE /consultas` - Cancela uma consulta agendada



## Licença
Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para obter mais informações.