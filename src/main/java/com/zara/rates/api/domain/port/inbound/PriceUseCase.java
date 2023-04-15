package com.zara.rates.api.domain.port.inbound;

import com.zara.rates.api.common.exception.ParameterEmptyException;
import com.zara.rates.api.common.exception.ResourceNotFoundException;
import com.zara.rates.api.domain.model.Price;

import java.time.OffsetDateTime;

public interface PriceUseCase {

    Price findPrice(Long brandId, Long productId, OffsetDateTime applicationDate)
            throws ParameterEmptyException, ResourceNotFoundException;
}
