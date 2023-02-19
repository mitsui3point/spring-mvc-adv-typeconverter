package hello.typeconverter.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HelloControllerTest {
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();

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
}
