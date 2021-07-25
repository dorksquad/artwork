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
                                         @RequestParam(required = false, name = "name") String name,
                                         @RequestParam(name = "album", required = false) String album) {
        List<Artwork> artworks = artworkService.getArtworks(sort, name, album);
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

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteArtwork(@RequestParam("name") String name){
        String result = artworkService.deleteArtwork(name);
        return ResponseEntity.ok(result);
    }


    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateArtwork(@RequestParam("name") String name,
                                           @RequestParam(name = "album", required = false) String album,
                                           @RequestParam(name = "image", required = false) MultipartFile image) throws IOException {
        Artwork artwork = artworkService.updateArtwork(name, album,image);
        return ResponseEntity.ok(artwork);
    }


}
