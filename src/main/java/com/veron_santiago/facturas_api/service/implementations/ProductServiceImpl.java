package com.veron_santiago.facturas_api.service.implementations;

import com.veron_santiago.facturas_api.persistence.entity.Product;
import com.veron_santiago.facturas_api.persistence.repository.IProductRepository;
import com.veron_santiago.facturas_api.presentation.dto.entities.ProductDTO;
import com.veron_santiago.facturas_api.service.interfaces.IProductService;
import com.veron_santiago.facturas_api.util.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.productDTOToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.productToProductDTO(savedProduct);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Producto no encontrado") );
        return productMapper.productToProductDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::productToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product extistingProduct = productRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Producto no encontrado") );

        extistingProduct.setName(productDTO.getName());
        extistingProduct.setPrice(productDTO.getPrice().doubleValue());

        return productMapper.productToProductDTO(productRepository.save(extistingProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                        .orElseThrow( () -> new RuntimeException("Producto no encontrado con ID: " + id));
        productRepository.deleteById(id);
    }
}
