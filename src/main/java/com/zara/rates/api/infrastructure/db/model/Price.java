package com.zara.rates.api.infrastructure.db.model;

import com.zara.rates.api.domain.model.Currency;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity(name = "Prices")
@Data
@NoArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long brandId;
    private Long productId;
    private Integer priority;
    private BigDecimal rate;

    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    @Enumerated(EnumType.STRING)
    private Currency currency;
}
