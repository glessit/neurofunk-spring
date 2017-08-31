package com.glessit.neurofunky.integration.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glessit.neurofunky.configuration.RestConfiguration;
import com.glessit.neurofunky.configuration.RootConfiguration;
import com.glessit.neurofunky.configuration.jpa.JPAConfiguration;
import com.glessit.neurofunky.configuration.security.SecurityConfiguration;
import com.glessit.neurofunky.web.rest.dto.NewsDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class, JPAConfiguration.class, SecurityConfiguration.class, RestConfiguration.class})
@WebAppConfiguration
public class NewsResourceTest {

    @Autowired
    private WebApplicationContext applicationContext;
    @Autowired
    private Filter springSecurityFilterChain;

    private ObjectMapper objectMapper = new ObjectMapper();
    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.applicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    /**
     * Test cover case when some non-auth.user try to create news item
     */
    @Test
    @Rollback(false)
    public void createNewsTestWithoutAuth() throws Exception {

        NewsDto newsItem = new NewsDto();
        newsItem.setTitle("This is news title");
        newsItem.setImage("http://img02.deviantart.net/82ac/i/2009/185/1/8/neurofunk_by_stavrozinio.png");
        newsItem.setNews("Hi, some info right here!");
        newsItem.setShortNews(null);
//        newsItem.setCreated(LocalDateTime.now());
        newsItem.setVisible(Boolean.TRUE);

        this.mockMvc
                .perform(
                    put("/news/create")
                    .content(objectMapper.writeValueAsString(newsItem))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andDo(print());

    }

    @Test
    public void test() {
        assert(true);
    }
}
