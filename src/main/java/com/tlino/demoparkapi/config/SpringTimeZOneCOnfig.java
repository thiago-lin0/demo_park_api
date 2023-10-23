package com.tlino.demoparkapi.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class SpringTimeZOneCOnfig {
    @PostConstruct
    public void timeZoneConfig(){

        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_paulo"));
    }

}
