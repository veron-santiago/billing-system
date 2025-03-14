package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.presentation.dto.entities.ProductDTO;

import java.util.List;

public interface IProductService {

    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO getProductById(Long id);
    List<ProductDTO> getAllProducts();
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);

}
