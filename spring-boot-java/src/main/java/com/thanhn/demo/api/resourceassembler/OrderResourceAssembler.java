package com.thanhn.demo.api.resourceassembler;

import com.thanhn.demo.api.OrderController;
import com.thanhn.demo.constant.Status;
import com.thanhn.demo.entity.Order;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>> {
    @Override
    public Resource<Order> toResource(Order order) {
        // Unconditional links to single-item resource and aggregate root

        Resource<Order> orderResource = new Resource<>(order,
                linkTo(methodOn(OrderController.class).one(order.getId())).withSelfRel(),
                linkTo(methodOn(OrderController.class).all()).withRel("orders")
        );

        // Conditional links based on state of the order

        if (order.getStatus() == Status.IN_PROGRESS) {
            orderResource.add(
                    linkTo(methodOn(OrderController.class)
                            .cancel(order.getId())).withRel("cancel"));
            orderResource.add(
                    linkTo(methodOn(OrderController.class)
                            .complete(order.getId())).withRel("complete"));
        }

        return orderResource;
    }
}
