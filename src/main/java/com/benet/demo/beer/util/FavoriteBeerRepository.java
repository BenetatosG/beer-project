package com.benet.demo.beer.util;

import com.benet.demo.beer.domain.FavoriteBeer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteBeerRepository extends JpaRepository<FavoriteBeer, Long> {
}
