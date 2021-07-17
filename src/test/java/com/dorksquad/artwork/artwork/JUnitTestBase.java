package com.dorksquad.artwork.artwork;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class JUnitTestBase {


    protected static MongoDbContainer mongoDbContainer;

    @BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
        mongoDbContainer = new MongoDbContainer();
        mongoDbContainer.start();
    }
    @After
    public static void endContainer()
    {
        mongoDbContainer.stop();
    }

    public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static final Logger log = LoggerFactory.getLogger(MongoDbInitializer.class);

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            log.info("Overriding Spring Properties for mongodb !!!!!!!!!");

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDbContainer.getPort()

            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
