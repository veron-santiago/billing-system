package com.veron_santiago.facturas_api.service.implementations;

import com.veron_santiago.facturas_api.persistance.entity.Product;
import com.veron_santiago.facturas_api.persistance.repository.IProductRepository;
import com.veron_santiago.facturas_api.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Producto no encontrado") );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product extistingProduct = productRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Producto no encontrado") );

        extistingProduct.setName(product.getName());
        extistingProduct.setPrice(product.getPrice());

        return productRepository.save(extistingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                        .orElseThrow( () -> new RuntimeException("Producto no encontrado con ID: " + id));
        productRepository.deleteById(id);
    }
}
