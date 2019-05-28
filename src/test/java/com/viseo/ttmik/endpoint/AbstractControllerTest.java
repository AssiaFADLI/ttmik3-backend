package com.viseo.ttmik.endpoint;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Abstract class for unit testing controllers.
 */
@Tag("controller-test")
@ExtendWith(SpringExtension.class)
public abstract class AbstractControllerTest {

    //pour mocker les web services
    MockMvc mvc;

    //le context web spring
    @Autowired
    WebApplicationContext context;

    //permet la serialisation et deserialisation des objets java en JSON et vice versa
    @Autowired
    ObjectMapper objectMapper;

    //pour re-initialiser le mock a chaque test
    @BeforeEach
    public void setup() {
        setupMockMvc();
    }

    protected void setupMockMvc() {
        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
}
