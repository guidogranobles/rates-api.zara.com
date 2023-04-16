package com.zara.rates.api.application.adapter;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;


public record PriceResponse(
        @JsonProperty("brand_id")
        Long brandId,

        @JsonProperty("product_id")
        Long productId,

        @JsonProperty("price")
        BigDecimal rate,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonProperty("start_date")
        OffsetDateTime startDate,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @JsonProperty("end_date")
        OffsetDateTime endDate) {
}



