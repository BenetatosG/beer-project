package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.domain.FavoriteBeer;
import com.benet.demo.beer.dto.BeerDTO;
import com.benet.demo.beer.dto.CreateFavoriteBeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer punkToDomain(PunkBeerDTO punkBeerDTO);

    BeerDTO beerToDTO(Beer beer);

    @Mapping(target = "id", ignore = true)
    Beer beerFromDTO(BeerDTO beerDTO);

    FavoriteBeerDTO favoriteBeerToDTO(FavoriteBeer favoriteBeer);

    @Mapping(target = "id", ignore = true)
    FavoriteBeer fromCreateDTO(CreateFavoriteBeerDTO dto);
}
