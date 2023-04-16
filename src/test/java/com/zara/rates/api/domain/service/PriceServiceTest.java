package com.zara.rates.api.domain.service;

import com.zara.rates.api.common.exception.ParameterEmptyException;
import com.zara.rates.api.common.exception.ResourceNotFoundException;
import com.zara.rates.api.domain.model.Price;
import com.zara.rates.api.domain.port.outbound.PricePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    private final String notFoundErrorMessage = "no Price for the given parameters was found";
    private final String parameterErrorMessage = "Parameter %s can't be empty or null";

    @Mock
    private PricePort pricePort;

    @InjectMocks
    private PriceService priceService;

    @Test
    void shouldReturnPriceWhenAllParametersAreValid() throws ParameterEmptyException, ResourceNotFoundException {
        //given
        Long brandId = 1L;
        Long productId = 35455L;
        OffsetDateTime applicationDate = OffsetDateTime.now();
        Price priceExpected = new Price( 1L, brandId, productId, new BigDecimal(35.8), applicationDate.minusDays(1L),
                applicationDate.plusDays(1L));

        given(pricePort.findPrice(brandId, productId, applicationDate)).willReturn(Optional.of(priceExpected));

        //when
        Price price = priceService.findPrice(brandId, productId, applicationDate);

        //then
        assertThat(price).isEqualTo(priceExpected);
    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenPriceNotFound() throws ParameterEmptyException, ResourceNotFoundException {
        //given
        Long brandId = 1L;
        Long productId = 35455L;
        OffsetDateTime applicationDate = OffsetDateTime.now();

        given(pricePort.findPrice(brandId, productId, applicationDate)).willReturn(Optional.empty());

        //when
        Exception ex = assertThrows(ResourceNotFoundException.class,
                () -> priceService.findPrice(brandId, productId, applicationDate));


        //then
        assertThat(ex).hasMessage(notFoundErrorMessage);
    }

    @Test
    void shouldThrowParameterEmptyExceptionWhenBrandIdParamNotPresent() throws ParameterEmptyException, ResourceNotFoundException {
        //given
        Long productId = 35455L;
        OffsetDateTime applicationDate = OffsetDateTime.now();

        //when
        Exception ex = assertThrows(ParameterEmptyException.class,
                () -> priceService.findPrice(null, productId, applicationDate));


        //then
        assertThat(ex).hasMessage(String.format(parameterErrorMessage, "brandId"));
    }

    @Test
    void shouldThrowParameterEmptyExceptionWhenProductIdParamNotPresent() throws ParameterEmptyException, ResourceNotFoundException {
        //given
        Long brandId = 1L;
        OffsetDateTime applicationDate = OffsetDateTime.now();

        //when
        Exception ex = assertThrows(ParameterEmptyException.class,
                () -> priceService.findPrice(brandId, null, applicationDate));


        //then
        assertThat(ex).hasMessage(String.format(parameterErrorMessage, "productId"));
    }

    @Test
    void shouldThrowParameterEmptyExceptionWhenApplicationDateParamNotPresent() throws ParameterEmptyException, ResourceNotFoundException {
        //given
        Long brandId = 1L;
        Long productId = 35455L;

        //when
        Exception ex = assertThrows(ParameterEmptyException.class,
                () -> priceService.findPrice(brandId, productId, null));


        //then
        assertThat(ex).hasMessage(String.format(parameterErrorMessage, "applicationDate"));
    }
}
