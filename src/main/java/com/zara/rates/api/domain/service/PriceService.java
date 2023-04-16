package com.zara.rates.api.domain.service;

import com.zara.rates.api.common.exception.ParameterEmptyException;
import com.zara.rates.api.common.exception.ResourceNotFoundException;
import com.zara.rates.api.domain.model.Price;
import com.zara.rates.api.domain.port.inbound.PriceUseCase;
import com.zara.rates.api.domain.port.outbound.PricePort;

import java.time.OffsetDateTime;

public class PriceService implements PriceUseCase {

    private final String parameterErrorMessage = "Parameter %s can't be empty or null";
    private final String notFoundErrorMessage = "no Price for the given parameters was found";
    private final PricePort pricePort;

    public PriceService(PricePort pricePort) {
        this.pricePort = pricePort;
    }

    @Override
    public Price findPrice(Long brandId, Long productId, OffsetDateTime applicationDate)
            throws ParameterEmptyException, ResourceNotFoundException {
        validateParameters(brandId, productId, applicationDate);
        return pricePort.findPrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new ResourceNotFoundException(notFoundErrorMessage));
    }

    private void validateParameters(Long brandId, Long productId, OffsetDateTime applicationDate)
            throws ParameterEmptyException {

        if(brandId == null) {
            throw new ParameterEmptyException(String.format(parameterErrorMessage, "brandId"));
        }

        if(productId == null) {
            throw new ParameterEmptyException(String.format(parameterErrorMessage, "productId"));
        }

        if(applicationDate == null) {
            throw new ParameterEmptyException(String.format(parameterErrorMessage, "applicationDate"));
        }

    }
}
