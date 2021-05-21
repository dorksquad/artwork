package com.dorksquad.artwork.artwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArtworkController
{

    @Autowired
    private ArtworkService artworkService;

    @GetMapping("/artworks")
    public ResponseEntity<?> getArtworks()
    {
        List<Artwork> artworks = artworkService.getArtworks();
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/artwork/{name}")
    public ResponseEntity<?> getArtwork(@PathVariable String name)
    {
        Artwork artwork = artworkService.getArtworkByName(name);
        return ResponseEntity.ok(artwork);
    }
}
