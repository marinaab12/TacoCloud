package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.data.JpaIngredientRepository;
import tacos.data.JpaTacoRepository;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/design", produces = "application/json")
//@SessionAttributes("order")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private final JpaIngredientRepository ingredientRepository;
    private final JpaTacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(JpaIngredientRepository ingredientRepository, JpaTacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

//    @ModelAttribute(name = "order")
//    public Order order() {
//        return new Order();
//    }

//    @ModelAttribute(name = "taco")
//    public Taco taco() {
//        return new Taco();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        return optionalTaco
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepository.save(taco);
    }

//    @GetMapping
//    public String showDesignForm(Model model) {
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredientRepository.findAll().forEach(ingredients::add);
//        Type[] types = Type.values();
//        for (Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//        }
//        return "design";
//    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(ingredient -> type.equals(ingredient.getType()))
                .collect(Collectors.toList());
    }

//    @PostMapping
//    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
//        if (errors.hasErrors()) {
//            return "design";
//        }
//        Taco savedTaco = tacoRepository.save(taco);
//        order.addTaco(savedTaco);
//        log.info("Processing taco: " + taco);
//        return "redirect:/orders/current";
//    }
}
