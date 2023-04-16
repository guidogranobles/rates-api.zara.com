package com.zara.rates.api.infrastructure.adapter;

import com.zara.rates.api.infrastructure.db.PriceRepository;
import com.zara.rates.api.infrastructure.db.model.Price;
import com.zara.rates.api.infrastructure.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PriceAdapterTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper mapper;

    @InjectMocks
    private PriceAdapter priceAdapter;

    @Test
    void shouldReturnPrice() {
        //given
        Long brandId = 1L;
        Long productId = 35455L;
        OffsetDateTime applicationDate = OffsetDateTime.now();

        Price price = new Price();
        price.setId(1L);
        price.setBrandId(1L);
        price.setProductId(35455L);
        price.setRate(new BigDecimal(35.8));
        price.setStartDate(applicationDate.minusDays(1L));
        price.setEndDate(applicationDate.plusDays(1L));

        given(priceRepository.findPrices(brandId, productId, applicationDate)).willReturn(List.of(price));
        given(mapper.toDomainModel(price)).willReturn(mapToDomainModel(price));

        //when
        Optional<com.zara.rates.api.domain.model.Price> priceOpt = priceAdapter.findPrice(brandId, productId, applicationDate);

        //then
        assertThat(priceOpt.isPresent());
        com.zara.rates.api.domain.model.Price priceResult = priceOpt.get();
        assertThat(priceResult.id()).isEqualTo(price.getId());
        assertThat(priceResult.brandId()).isEqualTo(price.getBrandId());
        assertThat(priceResult.productId()).isEqualTo(price.getProductId());
        assertThat(priceResult.rate()).isEqualTo(price.getRate());
        assertThat(priceResult.startDate()).isEqualTo(price.getStartDate());
        assertThat(priceResult.endDate()).isEqualTo(price.getEndDate());
    }

    private com.zara.rates.api.domain.model.Price mapToDomainModel(Price price) {
        return new com.zara.rates.api.domain.model.Price( price.getId(), price.getBrandId(), price.getProductId(),
                price.getRate(), price.getStartDate(), price.getEndDate());
    }
}
