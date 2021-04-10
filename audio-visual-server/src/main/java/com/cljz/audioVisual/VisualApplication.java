package com.cljz.audioVisual;

import com.cljz.audioVisual.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import oshi.SystemInfo;

import java.util.Random;

@SpringBootApplication
@EnableTransactionManagement(order = 10)
@ServletComponentScan
public class VisualApplication {
    public static void main(String[] args) {
        SpringApplication.run(VisualApplication.class, args);
    }

    @Bean
    public IdWorker createIdWorker() {
        return new IdWorker(0, 0);
    }

    @Bean
    public BCryptPasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Random initRandom() {
        return new Random();
    }

    @Bean
    public SystemInfo systemInfo() {
        return new SystemInfo();
    }

}