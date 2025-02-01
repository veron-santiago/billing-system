package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.persistance.entity.Product;

import java.util.List;

public interface IProductService {

    Product createProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

}
