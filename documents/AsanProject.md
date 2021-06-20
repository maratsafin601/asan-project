# About Asan Project

This is the backend development and API testing application written in Java and built with Quarkus and Maven.

## How to create your own application

The easiest way to bootstrap a new project is to open a terminal and run the following command:

For Linux & MacOS users

```shell script
mvn io.quarkus:quarkus-maven-plugin:1.13.7.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=getting-started \
    -DclassName="org.acme.getting.started.GreetingResource"
    -Dpath="/hello"
```

For Windows users:

If using cmd, don’t use backward slash '\':
```shell script
mvn io.quarkus:quarkus-maven-plugin:1.13.7.Final:create -DprojectGroupId=org.acme -DprojectArtifactId=getting-started -DclassName="org.acme.getting.started.GreetingResource" -Dpath="/hello"
```

If using Powershell, wrap -D parameters in double quotes:
```shell script
mvn io.quarkus:quarkus-maven-plugin:1.13.7.Final:create "-DprojectGroupId=org.acme" "-DprojectArtifactId=getting-started" "-DclassName=org.acme.getting.started.GreetingResource" "-Dpath=/hello"
```

## How to run the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev OR ./mvnw quarkus:dev
```

Then navigate to http://0.0.0.0:8080/ in a browser.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/asan-project-1.0.0-SNAPSHOT-runner`

## References

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/.

Step-by-step instruction to create your first application: https://quarkus.io/guides/getting-started
Building an application with Quarkus and Maven: https://quarkus.io/guides/maven-tooling

Udemy course: https://www.udemy.com/course/quarkus-backend-development-java/.
Course GitHub link: https://github.com/quarkus-course.