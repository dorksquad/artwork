package com.dorksquad.artwork.artwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @GetMapping("/artworks")
    public ResponseEntity<?> getArtworks() {
        List<Artwork> artworks = artworkService.getArtworks();
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/artworks/{name}")
    public ResponseEntity<?> getArtworkByName(@PathVariable String name) {
        Artwork artwork = artworkService.getArtworkByName(name);
        return ResponseEntity.ok(artwork);
    }

    @GetMapping("/artworks/album/{album}")
    public ResponseEntity<?> getArtworkByAlbum(@PathVariable String album) {
        Artwork artwork = artworkService.getArtworkByAlbum(album);
        return ResponseEntity.ok(artwork);
    }

    @PostMapping("/artworks/add")
    public ResponseEntity<?> addArtwork(@RequestParam("name") String name,
                                        @RequestParam("album") String album,
                                        @RequestParam("image") MultipartFile image) throws IOException {
        String artwork = artworkService.addArtwork(name, album, image);
        return ResponseEntity.ok(artwork);
    }
}
