# cm-db-initializer

Database migartion for consent-manager.

## :rocket: Running From Source

```
mvn clean install
```

```
mvn package
```

```
java -Djdbc.url=jdbc:postgresql://${db_host}:${db_port}/${database_name} -Djdbc.username=${user_name} -Djdbc.password=${password} -jar target/cmdb-initializer-1.0-SNAPSHOT.jar
```
