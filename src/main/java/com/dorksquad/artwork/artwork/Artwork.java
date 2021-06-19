package com.dorksquad.artwork.artwork;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "artworks")
public class Artwork
{

    @Id
    private String name;

    private String album;

    private Binary image;

    public Artwork(String name, Binary image) {
        this(name, null, image);
    }

    public Artwork(String name, String album, Binary image)
    {
        this.name = name;
        this.album = album;
        this.image = image;
    }

    public String getName()
    {
        return this.name;
    }

    public String getAlbum()
    {
        return this.album;
    }

    public Binary getImage()
    {
        return this.image;
    }

    @Override
    public String toString()
    {
        return "Artwork{" +
                "name='" + name + '\'' +
                ", album='" + album + '\'' +
                '}';
    }
}
