package ro.msg.learning.shop.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.service.strategy.Strategy;

@Configuration
@Slf4j
public class Config {
    @Value("${strategy.field}")
    private String strategy;

    @Bean
    public Strategy chooseStrategy(){
        log.info(strategy);
        switch(strategy){
            case "MostAbundant":
                return new MostAbundantStrategy();
            case "SingleLocation":
                return new SingleLocationStrategy();
        }
        return null;
    }
}
