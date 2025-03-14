package com.veron_santiago.facturas_api.util.mapper;

import com.veron_santiago.facturas_api.persistence.entity.Product;
import com.veron_santiago.facturas_api.presentation.dto.entities.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO productToProductDTO(Product product);
    Product productDTOToProduct(ProductDTO productDTO);
}