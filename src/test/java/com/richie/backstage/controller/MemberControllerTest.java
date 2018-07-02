package com.richie.backstage.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author richie on 2018.07.02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberControllerTest {

    private MockMvc mockMvc;
    private WebApplicationContext context;

    @Autowired
    public void setContext(WebApplicationContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void editMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/member_edit")
                .param("member_id", "46"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}