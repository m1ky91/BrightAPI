# BrightAPI

### Appendix

This APIs support the creation and retrieval of representations of a User
Realm.
A User Realm is a context for the registration and authentication of a user, which comprises
a unique integer identifier, a unique name, an (encryption) key and a description.



### Installing

Since this project is a SpringBoot application, to take the compiled code and package it in its distributable format, in this case a JAR:

```
mvn install
```

The command runs a full Maven cycle and get launched both unit tests and integration tests.

#### Running the application

For the sake of this test before to launch the application we should run a MySQL instance. To do this we can use the following Docker command:

```
docker run -p 3306:3306 --name=brightdb --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_PASSWORD=password" --env="MYSQL_DATABASE=brightdb" mysql
```

To run the application from command line:

```
java -jar target/bright-api.jar
```

### DB schema

During the bootstrap of the Spring Boot application a DB table is being created if it doesn't exists with the following script:

```
CREATE TABLE IF NOT EXISTS realm (
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(32) NOT NULL,
  description VARCHAR(255),
  `key` CHAR(32),
  UNIQUE KEY unique_name (name)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;;
```

### Limitations

Unit tests for the class CustomRestExceptionHandler are not provided due to time constraint but there are strong integration tests which test validation exceptions and errors during the call of endpoints.