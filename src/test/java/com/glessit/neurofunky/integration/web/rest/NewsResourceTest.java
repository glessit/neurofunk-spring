package com.glessit.neurofunky.integration.web.rest;

import com.glessit.neurofunky.configuration.RestConfiguration;
import com.glessit.neurofunky.configuration.RootConfiguration;
import com.glessit.neurofunky.configuration.jpa.JPAConfiguration;
import com.glessit.neurofunky.configuration.security.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfiguration.class, JPAConfiguration.class, SecurityConfiguration.class, RestConfiguration.class})
@WebAppConfiguration
public class NewsResourceTest {

    @Autowired
    private WebApplicationContext applicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.applicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void test() {
        assert(true);
    }
}
