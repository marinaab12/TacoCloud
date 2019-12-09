package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.data.IngredientRepository;
import tacos.model.Ingredient;
import tacos.web.api.IngredientResource;
import tacos.web.api.IngredientResourceAssembler;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Slf4j
//@RestController
//@RequestMapping(value = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {

    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

//    @GetMapping
//    public Iterable<Ingredient> ingredients(){
//        return ingredientRepository.findAll();
//    }

//    @GetMapping
//    public Resources<IngredientResource> getIngredients(){
//        Iterable<Ingredient> ingredients = ingredientRepository.findAll();
//
//        List<IngredientResource> ingredientResources = new IngredientResourceAssembler().toResources(ingredients);
//        Resources<IngredientResource> ingredientResourceResources = new Resources<>(ingredientResources);
//
//        ingredientResourceResources.add(linkTo(methodOn(IngredientController.class).getIngredients()).withRel("ingredients"));
//        return ingredientResourceResources;
//    }
}
