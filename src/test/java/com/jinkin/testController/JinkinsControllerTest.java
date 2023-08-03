package com.jinkin.testController;

import com.jinkin.controller.JinkinsController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(JinkinsController.class)
public class JinkinsControllerTest {

    @MockBean
    private JinkinsController jinkinsController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, world!"));

        // Verify that the method in the controller is called
        verify(jinkinsController).hello();
    }

    @Test
    public void testGreetEndpoint() throws Exception {
        mockMvc.perform(get("/api/greet").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John!"));

        // Verify that the method in the controller is called
        verify(jinkinsController).greet("John");
    }

    @Test
    public void testPostMessageEndpoint() throws Exception {
        String requestBody = "Test message";
        mockMvc.perform(post("/api/post")
                        .content(requestBody)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isCreated())
                .andExpect(content().string("Received message: Test message"));

        // Verify that the method in the controller is called
        verify(jinkinsController).postMessage("Test message");
    }
}
