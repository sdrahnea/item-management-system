# Item Management System Application

Let's assume that you are small/medium company whos employee's want to order something from any providers. This system allow you, as an office manager, to gather all whishes from the target audience and make an order without chating, asking, and another boring actions :).

## Summary
1. Getting Started (Prerequisites, Installing)
2. Running the tests
3. Deployment
4. Built With
5. Do you have any issue?
6. Contributing
7. Versioning
8. Authors
9. License
10. Donation


## 1. Getting Started

Clone or download a copy of this project.

### 1.2. Prerequisites

This project requires Java 1.8, MySQL and Maven.

Database installation

#### 1.2.1 H2
No installation is required.
The `spring.datasource.url` is the one required property which should be set. By default, the 
username is `sa` with empty password. Two modes: in memory and file storage. See the `application.properties`
file for more details related configuration.

#### 1.2.2 MySQL 

After MySQL instalation, it is required to create a dabase:

```
CREATE DATABSE ims;
```
Execute the content of `.sql` files, such as: 
```
chart_type.sql
data.sql
```
Note: in case that you run the application starting with MySQL 8.0.4, please execute the following query:
```
ALTER USER '${USER}'@'localhost' IDENTIFIED WITH mysql_native_password BY '${PASSWORD}';
-- where ${USER} and ${PASSWORD} should be provided. 
```

#### 1.2.3 Postgres
Install PostgreSQL. it is required to create a database:

Please, run the following commands if it is the case:
```
createuser -U postgres -s Progress
```

Please, run the following command to import a database (if it is the case):
```
pg_restore -d DATABASE_NAME <  PATH/BACKUP_FILE_NAME.sql
```

To create the JAR file please use the following command:
```
mvn clean package
```

### 3. Installing

All this files contains initial data. Just copy and paste the file's content Go to downloaded folder and create the build (you should have something similar like the following):
```
SDR:item-management-system sdrahnea$ mvn clean compile package
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------< com.ims:item-management-system >----------
[INFO] Building item-management-system 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.6.1:clean (default-clean) @ item-management-system ---
[INFO] Deleting /Users/sdrahnea/Documents/my-projects/item-management-system/target
[INFO] 
[INFO] --- maven-enforcer-plugin:1.4.1:enforce (enforce-versions) @ item-management-system ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ item-management-system ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 13 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ item-management-system ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 31 source files to /Users/sdrahnea/Documents/my-projects/item-management-system/target/classes
[INFO] /Users/sdrahnea/Documents/my-projects/item-management-system/src/main/java/com/udc/controller/AbstractController.java: Some input files use unchecked or unsafe operations.
[INFO] /Users/sdrahnea/Documents/my-projects/item-management-system/src/main/java/com/udc/controller/AbstractController.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-enforcer-plugin:1.4.1:enforce (enforce-versions) @ item-management-system ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ item-management-system ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 13 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ item-management-system ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ item-management-system ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/sdrahnea/Documents/my-projects/item-management-system/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ item-management-system ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ item-management-system ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ item-management-system ---
[INFO] Building jar: /Users/sdrahnea/Documents/my-projects/item-management-system/target/item-management-system-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.7.RELEASE:repackage (default) @ item-management-system ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.930 s
[INFO] Finished at: 2019-04-30T20:22:57+03:00
[INFO] ------------------------------------------------------------------------
SDR:item-management-system sdrahnea$ 
```

## 4. Running the tests

This project does not have any kind of tests :).

## 5. Deployment

Once the build (the jar file) is ready the application can be run. Please, use the following command to run the application:
```
SDR:item-management-system sdrahnea$ java -jar target/item-management-system-0.0.1-SNAPSHOT.jar
```
If was used default configuration then the application should be available at this url: http://localhost:8081/mytemplate/login.xhtml 
Use the following credentials: username: admin, password: 123.

## 6. Built With

* [Java](https://www.java.com/en/download/) - Java technology allows you to work and play in a secure computing environment. Java allows you to play online games, chat with people around the world, calculate your mortgage interest, and view images in 3D, just to name a few.
* [PrimeFaces](https://www.primefaces.org/) - PrimeFaces is a popular open source framework for JavaServer Faces featuring over 100 components, touch optimized mobilekit, client side validation, theme engine and more.
* [Spring Security](https://spring.io/projects/spring-security) - Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.
* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
* [Spring Data](https://spring.io/projects/spring-data) - Spring Data’s mission is to provide a familiar and consistent, Spring-based programming model for data access while still retaining the special traits of the underlying data store.
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories. This module deals with enhanced support for JPA based data access layers. It makes it easier to build Spring-powered applications that use data access technologies.
* [MySQL](https://www.mysql.com/) - MySQL is the world's most popular open source database. Whether you are a fast growing web property, technology ISV or large enterprise, MySQL can cost-effectively help you deliver high performance, scalable database applications.
* [Maven](https://maven.apache.org/) - Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information. 

## 7. Do you have any issue?

Please contact via LinkedIn account or drop an email (read [LICENSE.md](LICENSE.md) file).

## 8. Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## 9. Versioning

We use [SemVer](http://semver.org/) for versioning.

## 10. Authors

* **Sergiu Drahnea** - *Initial work* - [LinkedIn](https://www.linkedin.com/in/sergiu-drahnea)

## 11. License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## 12. Donation
* [PayPal](https://www.paypal.me/sdrahnea) - any donation is welcomed in case that you was pleased with this work :p
* [EGLD](http://elrond.com/) - Address: `erd1t3t5m8v7862asdh48nq820shsmlmuw9jpm87qw25cvch7djpkapskgq4es`
* [TROY](https://troytrade.com/) - Address: `bnb136ns6lfw4zs5hg4n85vdthaad7hq5m4gtkgf23` and Memo: `100079140`
* [PHB](https://phoenix.global/) - Address: `bnb136ns6lfw4zs5hg4n85vdthaad7hq5m4gtkgf23` and Memo: `100079140`
* [HOT](https://holochain.org/) - Address: `0x1ebfc62e2510f0a0558568223d1d101d0cf074b2`
* [VET](https://www.vechain.org/) - Address: `0x1ebfc62e2510f0a0558568223d1d101d0cf074b2`
* [TRX](https://tron.network/) - Address: `TRe8xSkGqpS73Nhk6bnvW34aiJoRTmZs8N`
* [BTT](https://www.bittorrent.com/token/btt/) - Address: `TRe8xSkGqpS73Nhk6bnvW34aiJoRTmZs8N`

