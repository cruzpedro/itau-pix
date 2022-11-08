# ITAU - PIX

Projeto de módulo de cadastro de chaves PIX.

## Começando

Essas instruções permitirão que você obtenha uma cópia de trabalho do projeto em sua máquina local para fins de desenvolvimento e teste.

### Pré requisitos

Antes de começar, você precisará ter as seguintes ferramentas instaladas em sua máquina:

- [Java](https://jdk.java.net/java-se-ri/17)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker-compose](https://docs.docker.com/compose/install/)
- [Maven](https://maven.apache.org/download.cgi)

### Tecnologias

- Java 17
- Maven
- Spring Boot
- Cucumber
- Flyway

### Arquitetura do Projeto

O domínio ```pix``` é um projeto maven multi-módulo que é apresentado a seguir:
- ```pix-api``` este módulo gera um artefato e é responsável por tratar a comunicação via REST.
- ```pix-core``` este módulo **não** gera um artefato e é responsável por tratar toda a regra de negócio.

### Rodando testes

Para executar todos os Cenários do Cucumber, basta executar o seguinte comando no prompt de comando:
```mvn test```

### Rodando

- Primeiro, vamos construir o artefato de **pix-api** com o seguinte comando: 
```mvn package``` <br/>
- Em seguida, execute o comando **docker-compose** para iniciar o aplicativo com toda a infraestrutura necessária: 
```docker-compose up --build```
