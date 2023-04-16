package com.zara.rates.api.application.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.rates.api.common.exception.ErrorDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceControllerIT {

    private static final String PARAMETER_ERROR_MESSAGE = "Required parameter %s is not present.";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenThereIsOneOnlyMatch() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "35.50",
               "2020-06-14T00:00:00.000Z", "2020-12-31T23:59:59.000Z" );

        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                .param("product_id", "35455")
                .param("application_date", "2020-06-14T13:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenThereAreTwoMatches() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "25.45",
                "2020-06-14T15:00:00.000Z", "2020-06-14T18:30:00.000Z" );


        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-14T16:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenDay14AndTime1000() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "35.50",
                "2020-06-14T00:00:00.000Z", "2020-12-31T23:59:59.000Z" );


        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-14T10:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenDay14AndTime1600() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "25.45",
                "2020-06-14T15:00:00.000Z", "2020-06-14T18:30:00.000Z" );


        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-14T16:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenDay14AndTime2100() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "35.50",
                "2020-06-14T00:00:00.000Z", "2020-12-31T23:59:59.000Z" );


        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-14T21:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenDay15AndTime1000() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "30.50",
                "2020-06-15T00:00:00.000Z", "2020-06-15T11:00:00.000Z" );


        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-15T10:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/clear-table.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/init-default.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    })
    void shouldGetPriceWhenDay16AndTime2100() throws Exception {
        //given
        PriceResponse priceResponseExpected = createExpectedPriceResponse(1L, 35455L, "38.95",
                "2020-06-15T16:00:00.000Z", "2020-12-31T23:59:59.000Z" );


        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-16T21:00:00.000Z"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                PriceResponse.class);

        assertThat(priceResponse).isEqualTo(priceResponseExpected);
    }

    @Test
    void shouldGet404WhenNotPriceFound() throws Exception {
        //given

        //when
        var response = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-13T16:00:00.000Z"))
                .andExpect(status().isNotFound())
                .andReturn();

        //then
        var priceResponse =  objectMapper.readValue(response.getResponse().getContentAsString(),
                ErrorDetails.class);

        assertThat(priceResponse.getMessage()).isEqualTo("no Price for the given parameters was found");
    }

    @Test
    void shouldGet400WhenBrandIdParamNotPresent() throws Exception {
        //given

        //when
        var mvcResult = this.mockMvc.perform(get("/price")
                        .param("product_id", "35455")
                        .param("application_date", "2020-06-13T16:00:00.000Z"))
                .andExpect(status().isBadRequest())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo(String.format(PARAMETER_ERROR_MESSAGE, "'brand_id'"));
    }

    @Test
    void shouldGet400WhenProductIdParamNotPresent() throws Exception {
        //given

        //when
        var mvcResult = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("application_date", "2020-06-13T16:00:00.000Z"))
                .andExpect(status().isBadRequest())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo(String.format(PARAMETER_ERROR_MESSAGE, "'product_id'"));
    }

    @Test
    void shouldGet400WhenApplicationDateParamNotPresent() throws Exception {
        //given

        //when
        var mvcResult = this.mockMvc.perform(get("/price")
                        .param("brand_id", "1")
                        .param("product_id", "35455"))
                .andExpect(status().isBadRequest())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getErrorMessage()).isEqualTo(String.format(PARAMETER_ERROR_MESSAGE, "'application_date'"));
    }

    private PriceResponse createExpectedPriceResponse(Long brandId, Long productId, String price,
                                                      String startDate, String endDate) {

        var start = OffsetDateTime.parse(startDate);
        var end =   OffsetDateTime.parse(endDate);

        return new PriceResponse( brandId, productId, new BigDecimal(price), start, end);
    }
}
