package tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import tacos.Taco;

import java.util.*;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private SimpleJdbcInsert tacoInserter;
    private SimpleJdbcInsert tacoIngredientInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.tacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco").usingGeneratedKeyColumns("id");
        this.tacoIngredientInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Ingredients");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Taco save(Taco taco) {
        taco.setCreatedAt(new Date());
        long tacoId = saveTacoDetails(taco);
        taco.setId(tacoId);
        List<Ingredient> ingredients = taco.getIngredients();
        for (Ingredient ingredient : ingredients) {
            saveIngredientsToTaco(ingredient, tacoId);
        }
        return taco;
    }

    private long saveTacoDetails(Taco taco) {
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
        values.put("createdAt", taco.getCreatedAt());
        long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
        return tacoId;
    }

    private void saveIngredientsToTaco(Ingredient ingredient, long tacoId) {
        Map<String, Object> values = new HashMap<>();
        values.put("taco", tacoId);
        values.put("ingredient", ingredient.getId());
        tacoIngredientInserter.execute(values);
    }
}