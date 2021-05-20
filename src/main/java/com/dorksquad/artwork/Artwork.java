package com.dorksquad.artwork;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class Artwork {

    @Id
    private String name;

    private String album;

    private Binary image;

    public Artwork() {}

    public Artwork(String name, Binary image) {
        this(name, null, image);
    }

    public Artwork(String name, String album, Binary image) {
        this.name = name;
        this.album = album;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Artwork{" +
                "name='" + name + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
