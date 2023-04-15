package com.zara.rates.api.infrastructure.adapter;

import com.zara.rates.api.domain.model.Price;
import com.zara.rates.api.domain.port.outbound.PricePort;
import com.zara.rates.api.infrastructure.db.PriceRepository;
import com.zara.rates.api.infrastructure.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PriceAdapter implements PricePort {

   private final PriceRepository priceRepository;
   private final PriceMapper mapper;
    @Override
    public Optional<Price> findPrice(Long brandId, Long productId, OffsetDateTime applicationDate) {
        return priceRepository.findPrices(brandId, productId, applicationDate)
                .stream()
                .findFirst()
                .map(mapper::toDomainModel);
    }
}
