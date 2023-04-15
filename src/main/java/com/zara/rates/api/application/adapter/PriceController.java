package com.zara.rates.api.application.adapter;

import com.zara.rates.api.common.exception.ParameterEmptyException;
import com.zara.rates.api.common.exception.ResourceNotFoundException;
import com.zara.rates.api.domain.model.Price;
import com.zara.rates.api.domain.port.inbound.PriceUseCase;
import com.zara.rates.api.infrastructure.mapper.PriceMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@RestController
@AllArgsConstructor
public class PriceController {

    private final PriceUseCase priceService;
    private final PriceMapper mapper;

    @GetMapping("/price")
    ResponseEntity<PriceResponse> getPrice(
            @NotNull @Parameter(name = "brand_id",  required = true) @Valid @RequestParam(value = "brand_id") Long brandId,
            @NotNull @Parameter(name = "product_id", required = true) @Valid @RequestParam(value = "product_id") Long productId,
            @NotNull @Parameter(name = "application_date", required = true) @Valid @RequestParam(value = "application_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime applicationDate
    ) throws ParameterEmptyException, ResourceNotFoundException {

        Price price = priceService.findPrice(brandId, productId, applicationDate);
        return new ResponseEntity<>(mapper.toPriceResponse(price), HttpStatus.OK);
    }
}
