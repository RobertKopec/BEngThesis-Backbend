package com.project.backend.data.repository;

import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TradeAdvertRepository extends CrudRepository<TradeAdvert, Long> {
    List<TradeAdvert> getAdvertsByUser(User user);
}
