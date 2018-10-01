package com.bonaparte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import com.bonaparte.rmiapi.ChargeMoney;

/**
 * Created by yangmingquan on 2018/10/1.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl("rmi://127.0.0.1:20022/charge/info");
        rmiProxyFactoryBean.setServiceInterface(ChargeMoney.class);
        return rmiProxyFactoryBean;
    }
}
