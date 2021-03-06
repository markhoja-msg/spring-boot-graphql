package com.thinkenterprise.test;

import static graphql.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GraphQLRouteTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    public void routes() throws IOException {
       
        GraphQLResponse response  = graphQLTestTemplate.postForResource("getAllRoutes.graphql");
        assertNotNull(response);
        assertTrue(response.isOk());
        //assertNotNull(response.get("$.data.routes.id"));
        
    }
    //https://stackoverflow.com/questions/46542056/how-to-pass-basic-authentication-headers-to-legacy-servlet-while-using-servletre
    
    @Test
    public void mutation() throws IOException {       
        GraphQLResponse response  = graphQLTestTemplate.postForResource("createRoute.graphql");
        assertNotNull(response);
        assertTrue(response.isOk());
        assertEquals("RO311", response.get("$.data.createRoute.flightNumber"));
        assertEquals("1", response.get("$.data.createRoute.id"));
        response  = graphQLTestTemplate.postForResource("updateRoute.graphql");
        assertEquals("RO311", response.get("$.data.updateRoute.flightNumber"));
        response  = graphQLTestTemplate.postForResource("deleteRoute.graphql");
        assertEquals(true, response.get("$.data.deleteRoute", Boolean.class));
    }
}