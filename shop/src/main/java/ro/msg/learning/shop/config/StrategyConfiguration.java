package ro.msg.learning.shop.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.model.StrategyEnum;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.strategy.MostAbundantStrategy;
import ro.msg.learning.shop.service.strategy.SingleLocationStrategy;
import ro.msg.learning.shop.service.strategy.Strategy;


@Configuration
@Slf4j
@AllArgsConstructor
public class StrategyConfiguration {

    private StockRepository stockRepository;
    private ProductRepository productRepository;
    private LocationRepository locationRepository;


    @Bean
    public Strategy chooseStrategy(@Value("${strategy.field}") StrategyEnum strategyEnum){
        switch(strategyEnum){
            case MOSTABUNDANT:
                return new MostAbundantStrategy(productRepository,locationRepository);
            case SINGLELOCATION:
                return new SingleLocationStrategy(productRepository,locationRepository);
        }
        return null;
    }
}
