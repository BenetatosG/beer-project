package com.benet.demo.beer;

import com.benet.demo.beer.domain.FavoriteBeer;
import com.benet.demo.beer.dto.BeerDTO;
import com.benet.demo.beer.dto.CreateFavoriteBeerDTO;
import com.benet.demo.beer.dto.FavoriteBeerDTO;
import com.benet.demo.beer.dto.FermentationType;
import com.benet.demo.common.excpetion.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerResource {

    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<List<BeerDTO>> getBeers(@RequestParam("fermentationType") FermentationType fermentationType,
                                                  @RequestParam("ibu_lt") Integer ibuLessThan,
                                                  @RequestParam("ibu_gt") Integer ibuGreaterThan,
                                                  @RequestParam("food") String food) {

        List<BeerDTO> beers = beerService.findBeers(fermentationType, ibuLessThan, ibuGreaterThan, food)
                .stream()
                .map(BeerMapper.INSTANCE::beerToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(beers);
    }

    @PostMapping("/favorites")
    public ResponseEntity<Void> saveFavoriteBeer(@RequestBody @Valid CreateFavoriteBeerDTO dto) {

        FavoriteBeer favoriteBeer = BeerMapper.INSTANCE.fromCreateDTO(dto);
        Long id = beerService.saveFavoriteBeer(favoriteBeer);
        return ResponseEntity.created(getUri(id)).build();
    }

    @GetMapping("/favorites/{id}")
    public ResponseEntity<?> getFavoriteBeer(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(BeerMapper.INSTANCE.favoriteBeerToDTO(beerService.getFavoriteBeer(id)));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<FavoriteBeerDTO>> getAllFavoriteBeers() {
        return ResponseEntity.ok(beerService.getAllFavoriteBeers().stream()
        .map(BeerMapper.INSTANCE::favoriteBeerToDTO)
        .collect(Collectors.toList()));
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/beers/favorites/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
