package com.payconiq.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.app.PayconiqApp;
import com.payconiq.vo.Stock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PayconiqApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class PayconiqTest {

    @LocalServerPort
    private int port;

    private String hostname="http://localhost:";
    private String url="/api/stocks/";

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();


    @Test
    public void testGetStocksById()
    {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<Stock> response = restTemplate.exchange(
                hostname+port+url+"1",
                HttpMethod.GET, entity, Stock.class);
        Stock expected = new Stock(1,"My First Stock",11.2,new Timestamp(1520315871508L));
        Assert.assertEquals(expected, response.getBody());
    }

    @Test
    public void testGetStocks()
    {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<ArrayList> response = restTemplate.exchange(
                hostname+port+url,
                HttpMethod.GET, entity, ArrayList.class);


        Assert.assertNotEquals(0, response.getBody().size());
    }


    @Test
    public void testUpdateStock()
    {
        MultiValueMap<String,String> param= new LinkedMultiValueMap<String, String>();
        param.add("price","17.5");
        int id=1;
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                hostname+port+url+id,
                HttpMethod.PUT, entity, String.class);
        String expected = "Stock with id :"+id+" updated";
        Assert.assertEquals(expected, response.getBody());
    }


    @Test
    public void testUpdateStock1()
    {
        MultiValueMap<String,String> param= new LinkedMultiValueMap<String, String>();
        param.add("price","17.5");
        int id=12;
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(param, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                hostname+port+url+id,
                HttpMethod.PUT, entity, String.class);
        String expected = "Stock with id :"+id+" not available";
        Assert.assertEquals(expected, response.getBody());
    }

    @Test
    public void testAddStock() throws JsonProcessingException {
        int id=3;
        headers.add("content-type","application/json");
        Stock body = new Stock(id,"My New Stock",11.2,new Timestamp(1520315871508L));
        HttpEntity<String> entity = new HttpEntity<String>(new ObjectMapper().writeValueAsString(body), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                hostname+port+url,
                HttpMethod.POST, entity, String.class);
        String expected = "Stock with id :"+id+" added";
        Assert.assertEquals(expected, response.getBody());
    }

    @Test
    public void testAddStock1() throws JsonProcessingException {
        int id=1;
        headers.add("content-type","application/json");
        Stock body = new Stock(id,"My New Stock",11.2,new Timestamp(1520315871508L));
        HttpEntity<String> entity = new HttpEntity<String>(new ObjectMapper().writeValueAsString(body), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                hostname+port+url,
                HttpMethod.POST, entity, String.class);
        String expected = "Stock with id :"+id+" already present, provide different id";
        Assert.assertEquals(expected, response.getBody());
    }

    @Test
    public void testAddStock2() throws JsonProcessingException {
        int id=1;
        headers.add("content-type","application/json");
        Stock body = new Stock();
        HttpEntity<String> entity = new HttpEntity<String>(new ObjectMapper().writeValueAsString(body), headers);
        ResponseEntity<String> response = restTemplate.exchange(
                hostname+port+url,
                HttpMethod.POST, entity, String.class);
        String expected = "Please provide stock id";
        Assert.assertEquals(expected, response.getBody());
    }





}
