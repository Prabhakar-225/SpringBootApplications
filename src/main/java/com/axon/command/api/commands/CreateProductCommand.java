package com.axon.command.api.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private  String name;
    private double price;
    private int quantity;

}
