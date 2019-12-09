package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import tacos.model.Ingredient;

@Relation(value = "ingredient", collectionRelation = "ingredients")
public class IngredientResource extends ResourceSupport {

    @Getter
    private final String name;
    @Getter
    private final Ingredient.Type type;

    public IngredientResource(Ingredient ingredient){
        this. name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
