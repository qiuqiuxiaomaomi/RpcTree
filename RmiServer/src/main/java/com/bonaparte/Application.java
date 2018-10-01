package com.bonaparte;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;
import com.bonaparte.rmiapi.ChargeMoney;

@SpringBootApplication
public class Application{
    @Autowired
    private ChargeMoney chargeMoney;

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    /* rmi 服务器暴漏 服务*/
    @Bean
    public RmiServiceExporter rmiServiceExporter(ChargeMoney chargeMoney){
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        // 客户端通过rmi调用的端口
        rmiServiceExporter.setRegistryPort(20022);
        // 客户端调用注册调用的服务名
        rmiServiceExporter.setServiceName("charge");
        // 注册的service
        rmiServiceExporter.setService(chargeMoney);
        //注册的接口
        rmiServiceExporter.setServiceInterface(ChargeMoney.class) ;
        return rmiServiceExporter ;
    }
}