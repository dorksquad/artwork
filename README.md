# Artwork Service

A service to create and retrieve artworks designed by dork squad using Spring Boot and MongoDB

## Install IDE

Intellij is recommended, but any IDE should work

## Install JDK (16 or higher)

OpenJDK or whatever works best for you. Project is set to Java 16

## Install Maven

Latest should be good

## Install MongoDB

This process will vary based on your OS, but there are many guides to follow

Useful aliases:

    alias mongostart='sudo systemctl start mongodb'
    alias mongostop='sudo systemctl stop mongodb'

## Install docker
This process will vary based on your OS, but there are multiple guides
Make sure your docker daemon is up

Useful aliases:

    alias dockerstart='sudo systemctl start docker.service'
    alias dockerstop='sudo systemctl stop docker.service'
    alias dockerkill='docker stop $(docker ps -aq) && docker rm $(docker ps -aq)'


## Install docker-compose
This process will vary based on your OS, but there are multiple guides

## Building Project

From the parent directory (artwork) run the following command
 1. ```mvn clean install``` (uses default maven profile `prod` with INFO log levels)
 2. ```mvn clean install -P debug``` (uses maven profile `debug` with DEBUG log levels)

## Standing Service Up (prod profile)
From the parent directory (artwork) run the following command
 1. ```docker-compose up```
    if there are issues you can specify --build at the end to force re-build containers
    
## Standing up in Debug mode (debug profile)
From the parent directory (artwork) run the following command
1. ```docker-compose -f docker-compose-debug.yml up --build```

