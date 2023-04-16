package com.zara.rates.api.infrastructure.mapper;

import com.zara.rates.api.application.adapter.PriceResponse;
import com.zara.rates.api.infrastructure.db.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    com.zara.rates.api.domain.model.Price toDomainModel(Price price);

    PriceResponse toPriceResponse(com.zara.rates.api.domain.model.Price price);
}
