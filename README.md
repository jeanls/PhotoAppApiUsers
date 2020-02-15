# Microsserviço de usuários

### Utilizando múltiplas instâncias

````
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.application.instance_id=jean, --server.port=54877
````

### Exemplo de configuração

```
server.port=${PORT:0}
spring.application.name=users-ws
eureka.client.serviceUrl.defaultZone=http://localhost:8010/eureka
spring.devtools.restart.enabled=true
eureka.instance.instance-id=${spring.application.name}:${spring.application.instance-id:${random.value}}
```