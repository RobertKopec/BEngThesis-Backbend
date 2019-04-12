package com.project.backend.data.repository;

import com.project.backend.data.entity.TradeAdvert;
import org.springframework.data.repository.CrudRepository;

public interface TradeAdvertRepository extends CrudRepository<TradeAdvert, Long> {
    TradeAdvert findTradeAdvertByTitle (String title);
}
