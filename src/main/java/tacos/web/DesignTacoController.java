package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Taco;
import tacos.data.JpaIngredientRepository;
import tacos.data.JpaTacoRepository;
import tacos.web.api.TacoResource;
import tacos.web.api.TacoResourceAssembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@Slf4j
//@RestController
//@RequestMapping(value = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final JpaIngredientRepository ingredientRepository;
    private final JpaTacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(JpaIngredientRepository ingredientRepository, JpaTacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
//        Optional<Taco> optionalTaco = tacoRepository.findById(id);
//        return optionalTaco
//                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
//    }
//
//    @GetMapping("/recent")
//    public Resources<TacoResource> recentTacos() {
//        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
//
//        List<Taco> tacos = tacoRepository.findAll(page).getContent();
//
//        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
//        Resources<TacoResource> recentResources = new Resources<>(tacoResources);
//        recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));
//
//        return recentResources;
//    }
//
//    @PostMapping(consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Taco postTaco(@RequestBody Taco taco) {
//        return tacoRepository.save(taco);
//    }
//
//    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
//        return ingredients.stream()
//                .filter(ingredient -> type.equals(ingredient.getType()))
//                .collect(Collectors.toList());
//    }
}
