package com.dorksquad.artwork.artwork;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bson.types.Binary;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

@EnableMongoRepositories(basePackageClasses = IArtworkRepositoryMongo.class)
@WebMvcTest(ArtworkController.class)
public class ArtworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IArtworkRepositoryMongo artworkRepositoryMongo;

    @Test
    public void getArtworks_Success() throws Exception {
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

}
