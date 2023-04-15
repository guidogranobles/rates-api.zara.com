package com.zara.rates.api.domain.port.outbound;

import com.zara.rates.api.domain.model.Price;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PricePort {
    Optional<Price> findPrice(Long brandId, Long productId, OffsetDateTime applicationDate);
}
