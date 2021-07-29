package com.dorksquad.artwork.artwork;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(ArtworkService.class);

    @GetMapping("/artworks")
    public ResponseEntity<?> getArtworks(@RequestParam(required = false) String sort) {
        logger.debug("ArtworkController: getArtworks endpoint was hit");
        List<Artwork> artworks;
        if (sort != null) {
            logger.debug(String.format("ArtworkController: getArtworks request contained a RequestParam: sort='%s'", sort));
            artworks = artworkService.getArtworks(sort);
        } else {
            logger.debug("ArtworkController: getArtworks request did not contain a RequestParam");
            artworks = artworkService.getArtworks();
        }
        return ResponseEntity.ok(artworks);
    }

    @GetMapping("/artworks/{name}")
    public ResponseEntity<?> getArtworkByName(@PathVariable String name) {
        logger.debug(String.format("ArtworkController: getArtworkByName endpoint was hit with name='%s'", name));
        Artwork artwork = artworkService.getArtworkByName(name);
        return ResponseEntity.ok(artwork);
    }

    @GetMapping("/artworks/album/{album}")
    public ResponseEntity<?> getArtworkByAlbum(@PathVariable String album) {
        logger.debug(String.format("ArtworkController: getArtworkByAlbum endpoint was hit with album='%s'", album));
        Artwork artwork = artworkService.getArtworkByAlbum(album);
        return ResponseEntity.ok(artwork);
    }

    @PostMapping("/artworks/add")
    public ResponseEntity<?> addArtwork(@RequestParam("name") String name,
                                        @RequestParam("album") String album,
                                        @RequestParam("image") MultipartFile image) throws IOException {
        logger.debug(String.format("ArtworkController: addArtwork endpoint was hit with:\n" +
                "name='%s'\nalbum='%s'\nimage='%s'", name, album, image.toString()));
        String artwork = artworkService.addArtwork(name, album, image);
        return ResponseEntity.ok(artwork);
    }
}
