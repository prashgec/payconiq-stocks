package com.payconiq.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Prashant
 */
@SpringBootApplication
@ComponentScan("com.payconiq")
public class PayconiqApp extends SpringBootServletInitializer{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PayconiqApp.class, args);
    }
}
