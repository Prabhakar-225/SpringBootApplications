package com.axon.command.api.aggregate;

import com.axon.command.api.commands.CreateProductCommand;
import com.axon.command.api.events.ProductCreatedEvent;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private  String name;
    private double price;
    private int quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {

        //perform all validation
        ProductCreatedEvent productCreatedEvent=new ProductCreatedEvent();

        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    public ProductAggregate() {
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.name=productCreatedEvent.getName();
        this.price=productCreatedEvent.getPrice();
        this.quantity=productCreatedEvent.getQuantity();
        this.productId=productCreatedEvent.getProductId();
    }




}
