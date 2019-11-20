package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.model.Order;
import tacos.model.User;
import tacos.data.JpaOrderRepository;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/json")
//@SessionAttributes("order")
@CrossOrigin(origins = "*")
public class OrderController {

    private OrderProps props;

    private JpaOrderRepository orderRepository;

    @Autowired
    public OrderController(OrderProps props, JpaOrderRepository orderRepository) {
        this.props = props;
        this.orderRepository = orderRepository;
    }

    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {

        Order order = orderRepository.findById(orderId).get();
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getState() != null) {
            order.setState(patch.getState());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }

        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }

//    @GetMapping("/current")
//    public String orderForm(Model model) {
//        return "orderForm";
//    }

//    @PostMapping
//    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
//        if (errors.hasErrors()) {
//            return "orderForm";
//        }
//
//        order.setUser(user);
//
//        orderRepository.save(order);
//
//        sessionStatus.setComplete();
//
//        log.info("Order submitted: " + order);
//        return "redirect:/";
//    }

//    @GetMapping
//    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
//        Pageable pageable = PageRequest.of(0, props.getPageSize());
//
//        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
//
//        return "orderList";
//    }
}
