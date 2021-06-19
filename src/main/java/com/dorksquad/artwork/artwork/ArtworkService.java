package com.dorksquad.artwork.artwork;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ArtworkService
{

    @Autowired
    private IArtworkRepositoryMongo artworkRepo;

    public Artwork addArtwork(String name, String album, MultipartFile file) throws IOException
    {
        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, file.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);
        artwork = artworkRepo.insert(artwork);
        return artwork;
    }

    public Artwork getArtworkByName(String name)
    {
        return artworkRepo.findByName(name);
    }

    public List<Artwork> getArtworks()
    {
        return artworkRepo.findAll();
    }

    public Artwork getArtworkByAlbum(String album)
    {
        return artworkRepo.findByAlbum(album);
    }
}
