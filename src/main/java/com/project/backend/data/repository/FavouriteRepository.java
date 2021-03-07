package com.project.backend.data.repository;

import com.project.backend.data.entity.Favourite;
import com.project.backend.data.entity.TradeAdvert;
import com.project.backend.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteRepository extends CrudRepository<Favourite,Long> {
    List<Favourite> findAllByUser(User user);
    Favourite findByUserAndTradeAdvert(User user, TradeAdvert advert);
    void deleteByUserAndTradeAdvert(User user, TradeAdvert advert);
}
