package com.zara.rates.api.infrastructure.configuration;

import com.zara.rates.api.domain.port.inbound.PriceUseCase;
import com.zara.rates.api.domain.port.outbound.PricePort;
import com.zara.rates.api.domain.service.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    PriceUseCase priceService(PricePort pricePort) {
        return new PriceService(pricePort);
    }
}
