package com.example.demo;

import com.example.demo.controller.TestController;
import com.example.demo.repository.TestRepository;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(TestController.class)
public class TestControllerMockMvcUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TestRepository repository;
    @MockBean
    private TestService service;
    @Test
    public void givenTest_whenAdd_thenStatus201andTestReturned() throws Exception {

        com.example.demo.model.Test test = new com.example.demo.model.Test("Testing test");
       /* Mockito.when(repository.save(Mockito.any())).thenReturn(test);

        mockMvc.perform(
                post("/test")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON)

        )

                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(test)));*/
    }


}
