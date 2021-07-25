package com.dorksquad.artwork.artwork;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/artworks")
public class ArtworkController {

    @Autowired
    private ArtworkService artworkService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getArtworks(@RequestParam(required = false) String sort,
                                         @RequestAttribute(name = "name", required = false) String name,
                                         @RequestAttribute(name = "album", required = false) String album) {
        List<Artwork> artworks = artworkService.getArtwork(sort, name, album);
        return ResponseEntity.ok(artworks);
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> addArtwork(@RequestParam("name") String name,
                                        @RequestParam("album") String album,
                                        @RequestParam("image") MultipartFile image) throws IOException {
        String artwork = artworkService.addArtwork(name, album, image);
        return ResponseEntity.ok(artwork);
    }
}
