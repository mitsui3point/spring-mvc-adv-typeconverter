package hello.typeconverter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@SpringBootTest
class ConverterControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void converterViewTest() throws Exception {
        mockMvc.perform(get("/converter-view"))
                .andExpect(status().isOk())
                .andExpect(view().name("converter-view"))
                .andExpect(content().string(containsString("192.168.0.1:8080")))
                .andExpect(content().string(containsString("10000")))
                .andDo(print());
    }

    @Test
    public void converterEditViewTest() throws Exception {
        mockMvc.perform(get("/converter/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("converter-form"))
                .andExpect(model().attribute("form", new ConverterController.Form(new IpPort("192.168.0.1", 8080))))
                .andDo(print());
    }

    @Test
    public void converterEditTest() throws Exception {
        mockMvc.perform(post("/converter/edit")
                        .queryParam("ipPort", "127.0.0.1:8090"))
                .andExpect(status().isOk())
                .andExpect(view().name("converter-view"))
                .andExpect(model().attribute("ipPort", new IpPort("127.0.0.1", 8090)))
                .andExpect(content().string(containsString("127.0.0.1:8090")))
                .andDo(print());
    }
}

