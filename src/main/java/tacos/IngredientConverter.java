package tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.data.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();
        return ingredients.stream().filter(ingredient -> id.equals(ingredient.getId())).collect(Collectors.toList()).get(0);
    }
}
