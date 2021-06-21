# Artwork Service

A service to create and retrieve artworks designed by dork squad using Spring Boot and MongoDB

## Install IDE

Intellij is recommended, but any IDE should work

## Install Maven

Latest should be good

## Install JDK (11 or higher)

OpenJDK or whatever works best for you. Project is set to Java 11

## Install MongoDB

This process will vary based on your OS, but there are many guides to follow

Useful aliases:

    alias mongostart='sudo systemctl start mongodb'
    alias mongostop='sudo systemctl stop mongodb'

## Building Project

 1. Sync maven if needed
 2. Start mongo
 3. ```mvn clean install```

## Standing Service Up Continuously

 1. Sync maven if needed
 2. Start mongo
 3. ```mvn spring-boot:run```
