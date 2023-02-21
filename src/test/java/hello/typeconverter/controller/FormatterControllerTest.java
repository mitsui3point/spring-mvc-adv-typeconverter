package hello.typeconverter.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class FormatterControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void formatterFormTest() throws Exception {
        String expectedNumber = "10,000";
        String expectedDateTime = now().format(ofPattern("yyyy-MM-dd HH"));

        ResultActions perform = mvc.perform(get("/formatter/edit"));

        perform.andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedNumber)))
                .andExpect(content().string(containsString(expectedDateTime)))
                .andExpect(view().name("formatter-form"))
                .andDo(print());
    }

    @Test
    void formatterViewTest() throws Exception {
        String expectedNumber = "10000";
        String expectedDateTime = now().toLocalDate()
                .toString();

        String expectedFormattedNumber = "10,000";
        String expectedFormattedDateTime = now().format(ofPattern("yyyy-MM-dd HH"));

        ResultActions perform = mvc.perform(post("/formatter/edit")
                .param("number", "10000")
                .param("localDateTime", now().toString()));

        perform.andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedNumber)))
                .andExpect(content().string(containsString(expectedDateTime)))
                .andExpect(content().string(containsString(expectedFormattedNumber)))
                .andExpect(content().string(containsString(expectedFormattedDateTime)))
                .andExpect(view().name("formatter-view"))
                .andDo(print());
    }
}
