package com.benet.demo.beer;

import com.benet.demo.beer.domain.Beer;
import com.benet.demo.beer.domain.FavoriteBeer;
import com.benet.demo.beer.dto.BeerDTO;
import com.benet.demo.beer.dto.CreateFavoriteBeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO.FavoriteBeerDTOBuilder;
import com.benet.demo.clients.punk.dto.PunkBeerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-07T21:15:22+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9.1 (AdoptOpenJDK)"
)
public class BeerMapperImpl implements BeerMapper {

    @Override
    public Beer punkToDomain(PunkBeerDTO punkBeerDTO) {
        if ( punkBeerDTO == null ) {
            return null;
        }

        List<String> foodPairing = null;
        Long id = null;
        String name = null;
        Double fermentationTemp = null;
        Double ibu = null;

        List<String> list = punkBeerDTO.getFoodPairing();
        if ( list != null ) {
            foodPairing = new ArrayList<String>( list );
        }
        id = punkBeerDTO.getId();
        name = punkBeerDTO.getName();
        fermentationTemp = punkBeerDTO.getFermentationTemp();
        ibu = punkBeerDTO.getIbu();

        Beer beer = new Beer( id, name, fermentationTemp, ibu, foodPairing );

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
    public Beer beerFromDTO(BeerDTO beerDTO) {
        if ( beerDTO == null ) {
            return null;
        }

        List<String> foodPairing = null;
        String name = null;
        Double fermentationTemp = null;
        Double ibu = null;

        List<String> list = beerDTO.getFoodPairing();
        if ( list != null ) {
            foodPairing = new ArrayList<String>( list );
        }
        name = beerDTO.getName();
        fermentationTemp = beerDTO.getFermentationTemp();
        ibu = beerDTO.getIbu();

        Long id = null;

        Beer beer = new Beer( id, name, fermentationTemp, ibu, foodPairing );

        return beer;
    }

    @Override
    public FavoriteBeerDTO favoriteBeerToDTO(FavoriteBeer favoriteBeer) {
        if ( favoriteBeer == null ) {
            return null;
        }

        FavoriteBeerDTOBuilder favoriteBeerDTO = FavoriteBeerDTO.builder();

        favoriteBeerDTO.id( favoriteBeer.getId() );
        favoriteBeerDTO.beer( beerToDTO( favoriteBeer.getBeer() ) );
        favoriteBeerDTO.drunkBefore( favoriteBeer.getDrunkBefore() );
        favoriteBeerDTO.drunkBeerPlace( favoriteBeer.getDrunkBeerPlace() );
        favoriteBeerDTO.drunkBeerDate( favoriteBeer.getDrunkBeerDate() );
        favoriteBeerDTO.rating( favoriteBeer.getRating() );
        favoriteBeerDTO.comments( favoriteBeer.getComments() );

        return favoriteBeerDTO.build();
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
