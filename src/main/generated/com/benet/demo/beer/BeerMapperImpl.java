package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.domain.FavoriteBeer;
import com.benet.demo.beer.dto.BeerDTO;
import com.benet.demo.beer.dto.CreateBeerDTO;
import com.benet.demo.beer.dto.CreateFavoriteBeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-08T22:07:18+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
public class BeerMapperImpl implements BeerMapper {

    @Override
    public Beer punkToDomain(PunkBeerDTO punkBeerDTO) {
        if ( punkBeerDTO == null ) {
            return null;
        }

        Beer beer = new Beer();

        beer.setId( punkBeerDTO.getId() );
        beer.setName( punkBeerDTO.getName() );
        beer.setFermentationTemp( punkBeerDTO.getFermentationTemp() );
        beer.setIbu( punkBeerDTO.getIbu() );
        List<String> list = punkBeerDTO.getFoodPairing();
        if ( list != null ) {
            beer.setFoodPairing( new ArrayList<String>( list ) );
        }

        return beer;
    }

    @Override
    public BeerDTO beerToDTO(Beer beer) {
        if ( beer == null ) {
            return null;
        }

        List<String> foodPairing = null;
        Long id = null;
        String name = null;
        Double fermentationTemp = null;
        Double ibu = null;

        List<String> list = beer.getFoodPairing();
        if ( list != null ) {
            foodPairing = new ArrayList<String>( list );
        }
        id = beer.getId();
        name = beer.getName();
        fermentationTemp = beer.getFermentationTemp();
        ibu = beer.getIbu();

        BeerDTO beerDTO = new BeerDTO( id, name, fermentationTemp, ibu, foodPairing );

        return beerDTO;
    }

    @Override
    public Beer beerFromDTO(CreateBeerDTO beerDTO) {
        if ( beerDTO == null ) {
            return null;
        }

        Beer beer = new Beer();

        beer.setName( beerDTO.getName() );
        beer.setFermentationTemp( beerDTO.getFermentationTemp() );
        beer.setIbu( beerDTO.getIbu() );
        List<String> list = beerDTO.getFoodPairing();
        if ( list != null ) {
            beer.setFoodPairing( new ArrayList<String>( list ) );
        }

        return beer;
    }

    @Override
    public FavoriteBeerDTO favoriteBeerToDTO(FavoriteBeer favoriteBeer) {
        if ( favoriteBeer == null ) {
            return null;
        }

        Long id = null;
        BeerDTO beer = null;
        Boolean drunkBefore = null;
        String drunkBeerPlace = null;
        LocalDate drunkBeerDate = null;
        Integer rating = null;
        String comments = null;

        id = favoriteBeer.getId();
        beer = beerToDTO( favoriteBeer.getBeer() );
        drunkBefore = favoriteBeer.getDrunkBefore();
        drunkBeerPlace = favoriteBeer.getDrunkBeerPlace();
        drunkBeerDate = favoriteBeer.getDrunkBeerDate();
        rating = favoriteBeer.getRating();
        comments = favoriteBeer.getComments();

        FavoriteBeerDTO favoriteBeerDTO = new FavoriteBeerDTO( id, beer, drunkBefore, drunkBeerPlace, drunkBeerDate, rating, comments );

        return favoriteBeerDTO;
    }

    @Override
    public FavoriteBeer fromCreateDTO(CreateFavoriteBeerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FavoriteBeer favoriteBeer = new FavoriteBeer();

        favoriteBeer.setBeer( beerFromDTO( dto.getBeer() ) );
        favoriteBeer.setDrunkBefore( dto.getDrunkBefore() );
        favoriteBeer.setDrunkBeerPlace( dto.getDrunkBeerPlace() );
        favoriteBeer.setDrunkBeerDate( dto.getDrunkBeerDate() );
        favoriteBeer.setRating( dto.getRating() );
        favoriteBeer.setComments( dto.getComments() );

        return favoriteBeer;
    }
}
