package com.axon.query.api.projection;

import com.axon.command.api.entity.Product;
import com.axon.command.api.entity.repository.ProductRepository;
import com.axon.command.api.model.ProductRestModel;
import com.axon.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handler(GetProductsQuery getProductsQuery){
        List<Product> products= productRepository.findAll();

        List<ProductRestModel> productRestModels=
                products.stream().map(product -> ProductRestModel
                        .builder()
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .name(product.getName())
                        .build()).collect(Collectors.toList());

        return productRestModels;
    }
}
