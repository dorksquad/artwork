package com.dorksquad.artwork.artwork;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = ArtworkControllerTest.MongoDbInitializer.class)
public class ArtworkControllerTest extends JUnitTestBase {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected IArtworkRepositoryMongo artworkRepositoryMongo;

    @Test
    public void getArtworkByName_Success() throws Exception {

        Artwork artwork = new Artwork("a1", "a1", new Binary("a1".getBytes()));

        String response = "["+
            "{" +
                "\"name\":\"a1\"," +
                "\"album\":\"a1\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTE=\"" +
                "}" +
            "}" + "]";

        when(artworkRepositoryMongo.findByName(artwork.getName())).thenReturn(artwork);
        this.mockMvc.perform(get("/artworks" ).param("name","a1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    public void getArtworkByAlbum_Success() throws Exception {

        Artwork artwork = new Artwork("a1", "a1", new Binary("a1".getBytes()));

        String response = "[" +
            "{" +
                "\"name\":\"a1\"," +
                "\"album\":\"a1\"," +
                "\"image\":{" +
                    "\"type\":0," +
                    "\"data\":\"YTE=\"" +
                "}" +
            "}" +  "]";
        when(artworkRepositoryMongo.findByAlbum(artwork.getAlbum())).thenReturn(artwork);
        this.mockMvc.perform(get("/artworks").param("album","a1"))
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
        this.mockMvc.perform(multipart("/artworks").file(image).param("name", name).param("album", album))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().string(name));
    }

    @Test
    public void deleteArtwork_Success() throws Exception {

        String name = "a1";
        String album = "tester";
        MockMultipartFile image = new MockMultipartFile("image", "test.txt", "text/plain", "some image".getBytes());

        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, image.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);

        when(artworkRepositoryMongo.findByName("a1")).thenReturn(artwork).thenReturn(null);

        this.mockMvc.perform(delete("/artworks").param("name","a1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Artwork with name "+ name +" has been successfully deleted"));
    }

    @Test
    public void updateArtwork_Success() throws Exception {
        String name = "a1";
        String album = "a2";

        MockMultipartFile image = new MockMultipartFile("image", "test.txt", "text/plain", "some image".getBytes());

        Binary binaryFile = new Binary(BsonBinarySubType.BINARY, image.getBytes());
        Artwork artwork = new Artwork(name, album, binaryFile);

        String response = "{"
                + "\"name\":\"" + name + "\","
                + "\"album\":\"" + album + "\","
                +"\"image\":" + "{"
                    + "\"type\":"  + "0" + ","
                    + "\"data\":" + "\"c29tZSBpbWFnZQ==\"" +
                "}" +
                "}";

        when(artworkRepositoryMongo.findByName(name)).thenReturn(artwork);
        this.mockMvc.perform(put("/artworks").param("name",name).param("album",album).param("image", String.valueOf(binaryFile)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }


}
