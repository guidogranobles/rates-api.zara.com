package com.zara.rates.api.application.adapter;

import com.zara.rates.api.application.model.PriceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
public class PriceAdapter implements PriceApi{

    @Override
    public ResponseEntity<PriceResponse> getPrice(Long brandId, Long productId, OffsetDateTime applicationDate) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
