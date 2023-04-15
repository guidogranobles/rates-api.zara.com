package com.zara.rates.api.infrastructure.db;

import com.zara.rates.api.infrastructure.db.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

                @Query("select p from Prices p where :applicationDate between p.startDate and p.endDate " +
                        "and p.brandId = :brandId and p.productId = :productId order by priority desc")
    List<Price> findPrices(Long brandId, Long productId, OffsetDateTime applicationDate);
}
