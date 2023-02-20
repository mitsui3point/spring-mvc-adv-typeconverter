package hello.typeconverter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class HelloControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    void helloV1Test() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/hello-v1")
                .param("data", "10")
        );
        //then
        perform.andExpect(status().isOk())
                .andExpect(content().string("ok"))
                .andDo(print());
    }

    @Test
    void helloV2Test() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/hello-v2")
                .param("data", "10")
        );
        //then
        perform.andExpect(status().isOk())
                .andExpect(content().string("ok"))
                .andDo(print());
    }

    @Test
    void helloV2FailTest() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/hello-v2")
                .param("data", "string")
        );
        //then
        perform.andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void ipPortTest() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/ip-port")
                .param("ipPort", "192.168.0.1:8080")
        );
        //then
        perform.andExpect(status().isOk())
                .andExpect(content().string("ok"))
                .andDo(print());
    }

    @Test
    void ipPortFailTest() throws Exception {
        //when
        ResultActions perform = mvc.perform(get("/ip-port")
                .param("ipPort", "string")
        );
        //then
        perform.andExpect(status().isBadRequest())
                .andDo(print());
    }
}
