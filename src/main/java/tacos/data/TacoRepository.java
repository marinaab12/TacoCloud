package tacos.data;

import tacos.model.Taco;

public interface TacoRepository {

    Taco save(Taco taco);

}