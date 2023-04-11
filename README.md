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

## Criação do usuário

Para criar um usuário para testar execute o seguinte sql no banco de dados:
```sql
insert into usuarios values ('07b0ddaa-ba3f-4295-89b8-50df6d39909f', 'login@login.com', '$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.');
```
A senha será '123456'