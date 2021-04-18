package com.example.demo;
import com.example.demo.exception.TestNotFoundException;
import com.example.demo.repository.TestRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@AutoConfigureMockMvc
public class HttpRequestTest {


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRepository repository;

    @Autowired
    private MockMvc mockMvc;

    /*@AfterEach
    public void resetDb() {
        repository.deleteAll();
    }*/

    private com.example.demo.model.Test createTest(String name) {
        com.example.demo.model.Test test = new com.example.demo.model.Test(name);
        return repository.save(test);
    }

    @Test
    public void givenTest_whenAdd_thenStatus201andTestReturned() throws Exception {
        com.example.demo.model.Test test = createTest("Testing test");
        mockMvc.perform(
                post("/test")
                        .content(objectMapper.writeValueAsString(test))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.testId").isNumber())
                .andExpect(jsonPath("$.name").value("Testing test"));
    }

    @Test
    public void givenId_whenGetExistingTest_thenStatus200andTestReturned() throws Exception {
        long id = createTest("Testing test").getTestId();
        mockMvc.perform(
                get("/test/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.testId").value(id))
                .andExpect(jsonPath("$.name").value("Testing test"));
    }
/*    @Test
    public void givenId_whenGetNotExistingTest_thenStatus404anExceptionThrown() throws Exception {
        mockMvc.perform(
                get("/test/0"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> mvcResult.getResolvedException().getClass().equals(TestNotFoundException.class));
    }*/
    @Test
    public void giveTest_whenUpdate_thenStatus200andUpdatedReturns() throws Exception {
        long id = createTest("Testing test 1").getTestId();
        mockMvc.perform(
                put("/test/{id}", id)
                        .content(objectMapper.writeValueAsString
                                (new com.example.demo.model.Test("Testing test 2")))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testing test 2"));
    }

  /*  @Test
    public void givenPerson_whenDeletePerson_thenStatus204() throws Exception {
        com.example.demo.model.Test test = createTest("Testing test");
        mockMvc.perform(
                delete("/test/{id}", test.getTestId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(test)));
    }*/

/*    @Test
    public void givenTests_whenGetTests_thenStatus200() throws Exception {
        com.example.demo.model.Test test1 = createTest("Testing test 1");
        com.example.demo.model.Test test2 =createTest( "Testing test 2");
        mockMvc.perform(
                get("/tests"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(test1, test2))));
        ;
    }*/
}
