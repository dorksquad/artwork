package com.dorksquad.artwork.artwork;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableMongoRepositories(basePackageClasses = IArtworkRepositoryMongo.class)
@WebMvcTest(ArtworkController.class)
public class ArtworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArtworkRepositoryMongo artworkRepositoryMongo;

    @Test
    public void getArtworkByName_Success() throws Exception {

        Artwork artwork = new Artwork("a1", "a1", new Binary("a1".getBytes()));

        String response =
            "{" +
                "\"name\":\"a1\"," +
                "\"album\":\"a1\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTE=\"" +
                "}" +
            "}";

        when(artworkRepositoryMongo.findByName(artwork.getName())).thenReturn(artwork);
        this.mockMvc.perform(get("/artworks/" + artwork.getName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void getArtworkByAlbum_Success() throws Exception {

        Artwork artwork = new Artwork("a1", "a1", new Binary("a1".getBytes()));

        String response =
            "{" +
                "\"name\":\"a1\"," +
                "\"album\":\"a1\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTE=\"" +
                "}" +
            "}";
        when(artworkRepositoryMongo.findByAlbum(artwork.getAlbum())).thenReturn(artwork);
        this.mockMvc.perform(get("/artworks/album/" + artwork.getAlbum()))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(response));
    }

    @Test
    public void getArtworksWithoutSort_Success() throws Exception {
        List<Artwork> artworks = new ArrayList<>();

        Artwork a1 = new Artwork("a1", "a1", new Binary("a1".getBytes()));
        artworks.add(a1);

        Artwork a2 = new Artwork("a2", "a2", new Binary("a2".getBytes()));
        artworks.add(a2);

        String response = "[" +
            "{" +
                "\"name\":\"a1\"," +
                "\"album\":\"a1\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTE=\"" +
                "}" +
            "}," +
            "{" +
                "\"name\":\"a2\"," +
                "\"album\":\"a2\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTI=\"" +
                "}" +
            "}" +
        "]";

        when(artworkRepositoryMongo.findAll()).thenReturn(artworks);
        this.mockMvc.perform(get("/artworks"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(response));
    }

    @Test
    public void getArtworksWithSort_Success() throws Exception {
        List<Artwork> artworks = new ArrayList<>();

        Artwork a1 = new Artwork("a", "c", new Binary("a1".getBytes()));
        artworks.add(a1);

        Artwork a2 = new Artwork("b", "b", new Binary("a2".getBytes()));
        artworks.add(a2);

        String response = "[" +
            "{" +
                "\"name\":\"a\"," +
                "\"album\":\"c\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTE=\"" +
                "}" +
            "}," +
            "{" +
                "\"name\":\"b\"," +
                "\"album\":\"b\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTI=\"" +
                "}" +
            "}" +
        "]";

        when(artworkRepositoryMongo.findAll(Sort.by("name").ascending())).thenReturn(artworks);
        this.mockMvc.perform(get("/artworks").param("sort", "name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void addArtwork_Success() throws Exception {
        String name = "tester";
        String album = "tester";
        MockMultipartFile image = new MockMultipartFile("image", "test.txt", "text/plain", "some image".getBytes());

        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, image.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);

        when(artworkRepositoryMongo.insert(artwork)).thenReturn(artwork);
        this.mockMvc.perform(multipart("/artworks/add").file(image).param("name", name).param("album", album))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(name));
    }

}
