# Formação Java e Spring Boot - Alura

Projeto utilizando durante o curso de formação Java e Spring Boot.

## Execução
### Execução dos contêineres de banco de dados
```bash
podman-compose up -d
```
### Execução da aplicação em abiente de PROD
```bash
java -Dspring.profiles.active=prod -DDATASOURCE_URL=jdbc:mysql://localhost/vollmed_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=root -jar target/api-0.0.1-SNAPSHOT.jar
```