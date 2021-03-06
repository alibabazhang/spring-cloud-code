package com.rui.cn;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer
public class CloudTxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudTxManagerApplication.class, args);
    }

}
