package com.payconiq.controller;

import com.payconiq.cache.LocalCache;
import com.payconiq.vo.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;


/**
 * @author Prashant
 */
@Controller
@RequestMapping("/api/stocks")
public class PayconiqController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayconiqController.class);

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<Stock> getStocks()
    {
        LOGGER.info("getStocks method invoked");
        return LocalCache.STOCKS.values();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Stock getStockById(@PathVariable Integer id)
    {
        LOGGER.info("getStockById method invoked");
        return LocalCache.STOCKS.get(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody String updateStockPrice(@PathVariable Integer id, @RequestParam Double price)
    {
        LOGGER.info("updateStockPrice method invoked");
        if(LocalCache.STOCKS.get(id)==null)
        {return "Stock with id :"+id+" not available";}
        Stock updatedStock=LocalCache.STOCKS.get(id);
        updatedStock.setCurrentPrice(price);
        updatedStock.setLastUpdate(Timestamp.from(Instant.now()));
        LocalCache.STOCKS.put(id,updatedStock);
        return "Stock with id :"+id+" updated";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String addStock(@RequestBody Stock stock)
    {
        LOGGER.info("addStock method invoked");
        if(stock.getId()==null)
        {
            return "Please provide stock id";
        }
        if(LocalCache.STOCKS.get(stock.getId())!=null)
        {
            return "Stock with id :"+stock.getId()+" already present, provide different id";
        }
        stock.setLastUpdate(Timestamp.from(Instant.now()));
        LocalCache.STOCKS.put(stock.getId(),stock);
        return "Stock with id :"+stock.getId()+" added";
    }

    @RequestMapping("/view")
    public String home() {
        return "index";
    }


}
