package tacos.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.data.JpaIngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientConverter implements Converter<String, Ingredient> {

    private final JpaIngredientRepository ingredientRepository;

    @Autowired
    public IngredientConverter(JpaIngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();

        return ingredients.stream().filter(ingredientu -> id.equals(ingredientu.getId())).collect(Collectors.toList()).get(0);
    }
}
