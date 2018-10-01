package com.bonaparte;

import com.bonaparte.service.ChargeMoneyService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * Created by yangmingquan on 2018/10/1.
 */
@SpringBootApplication
public class Application {
    @Autowired
    private ChargeMoneyService chargeMoneyService;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "/ChargeMoneyService")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(chargeMoneyService);
        exporter.setServiceInterface(ChargeMoneyService.class);
        return exporter;
    }
}
