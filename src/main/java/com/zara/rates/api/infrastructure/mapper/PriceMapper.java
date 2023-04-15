package com.zara.rates.api.infrastructure.mapper;

import com.zara.rates.api.application.adapter.PriceResponse;
import com.zara.rates.api.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    Price toDomainModel(com.zara.rates.api.infrastructure.db.model.Price price);

    PriceResponse toPriceResponse(Price price);
}
