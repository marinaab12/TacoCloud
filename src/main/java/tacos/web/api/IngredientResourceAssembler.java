package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tacos.model.Ingredient;
import tacos.web.IngredientController;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientResourceAssembler() {
        super(IngredientController.class, IngredientResource.class);
    }

    protected IngredientResource instantiateResource(Ingredient ingredient){
        return new IngredientResource(ingredient);
    }

    @Override
    public IngredientResource toResource(Ingredient ingredient) {
        return createResourceWithId(ingredient.getId(), ingredient);
    }
}
