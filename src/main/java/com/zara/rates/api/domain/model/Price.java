package com.zara.rates.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record Price (Long id, Long brandId, Long productId, BigDecimal rate,
                     OffsetDateTime startDate, OffsetDateTime endDate ) {}
