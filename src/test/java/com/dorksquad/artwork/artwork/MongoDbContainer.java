package com.dorksquad.artwork.artwork;


import org.jetbrains.annotations.NotNull;
import org.testcontainers.containers.GenericContainer;

/**
 * This class is for creating the docker mongodb container for testing
 */
public class MongoDbContainer extends GenericContainer<MongoDbContainer> {

    public static final int MONGODB_PORT = 27017;
    public static final String DEFAULT_IMAGE_AND_TAG = "mongo:latest";

    //constructor to pass the mongo:latest docker image to be built
    public MongoDbContainer() {
        this(DEFAULT_IMAGE_AND_TAG);
    }

    //constructor to call to genericContainer to be built using mongo:latest image and binding to port 27017
    public MongoDbContainer(@NotNull String image) {
        super(image);
        addExposedPort(MONGODB_PORT);
    }

    @NotNull
    public Integer getPort() {
        return getMappedPort(MONGODB_PORT);
    }


}