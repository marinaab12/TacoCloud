package tacos.data;

import tacos.model.Order;

public interface OrderRepository {

    Order save(Order order);

}