package com.axon.command.api.events;

import com.axon.command.api.entity.Product;
import com.axon.command.api.entity.repository.ProductRepository;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;



@Component              //@ProcessingGroup("product")
public class ProductEventHandler {


    private ProductRepository repository;

    public ProductEventHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {

        Product product=new Product();

        BeanUtils.copyProperties(event,product);
        repository.save(product);
      //  throw new Exception("Exception Raised: ");
    }

    // to handle the exception

//    @ExceptionHandler
//    public void handler(Exception exception) throws Exception {
//        throw exception;
//    }
}
