# :cupcake: Lazuli Doces :cupcake:
#### Projeto em desenvolvimento :construction_worker:

## Sobre o projeto :scroll:
Sistema ERP para confeitaria, possuindo as seguintes funcionalidades:
- [ ] Cadastro de produtos
- [ ] Controle de vendas e compras
- [ ] Controle de estoque
- [ ] Relatórios de lucros

Entre outros.

## Tecnologias utilizadas :hammer_and_wrench:
### Back end
- Java (v1.8)
- Spring Boot (v2.6.3)
- Spring Data JPA (v2.6.3)
- Spring Security (v2.6.3)
- Lombok (v1.18.22)
- Apache Maven (v3.8.4)

### Front end
- Spring Thymeleaf (v2.6.3)
- Thymeleaf Layout Dialect (v3.0.0)
- Webjars (v0.48)
- JavaScript
- JQuery (v3.6.0)
- Bootstrap (v5.1.3)
- Font Awesome (v6.0.0)

### Implementação
- MySQL (v8.0.28)
- Docker (v20.10.18)

## Executando projeto 🧑‍💻

### Máquina local :desktop_computer:
#### Pré-requisitos
- Java v1.8
- Maven
- MySQL v8

```bash
# Clone o projeto
git clone https://github.com/liberatov13/lazuli-web.git

# Acesse o diretório do projeto
cd lazuli-web

# Instale as dependencias
mvn clean install

# Execute o projeto com maven
mvn spring-boot:run
```

#### Docker 🐳
##### Pré-requisitos
- Docker
- Apache Maven
- MySQL v8

```bash
# Clone o projeto
git clone https://github.com/liberatov13/lazuli-web.git

# Acesse o diretório do projeto
cd lazuli-web

# Compile o projeto
mvn clean install package

# Construa a Docker image
docker build -t lazuli .

# Execute o container
# Altere as variáveis de ambiente para as configurações de seu banco de dados MySQL
# DB_HOST = IP (Valor padrão: localhost)
# DB_USER = Usuário (Valor padrão: root)
# DB_PASSWORD = Senha (Valor padrão: 123mudar)
# DB_PORT = Porta (Valor padrão: 3306)
docker run --name lzl -p 8098:8080 -e DB_HOST=127.0.0.1 -e DB_PORT=3306 -e DB_PASSWORD=123mudar lazuli
```

## Autor :pencil2:
[Elvis Liberato de Barros](https://www.linkedin.com/in/elvisbarros/)
