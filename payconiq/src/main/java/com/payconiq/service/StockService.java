package com.payconiq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payconiq.cache.LocalCache;
import com.payconiq.vo.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



@Service
@Profile("test")
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);



    @PostConstruct
    public void loadStocks() throws URISyntaxException, IOException {
        LOGGER.info("Loading stocks data");

        List<String> lstStocksStr=Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("stocks.json").toURI()), Charset.defaultCharset());
        List<Stock> stocks= new ArrayList<Stock>();
        ObjectMapper mapper = new ObjectMapper();
        lstStocksStr.forEach(str->{
            try{
            stocks.add(mapper.readValue(str,Stock.class));
            }
            catch (IOException i)
            {i.printStackTrace();}
        });
        stocks.forEach(s->{LocalCache.STOCKS.put(s.getId(),s);});
        LOGGER.info("Stocks Loaded successfully!!!");

    }
}
