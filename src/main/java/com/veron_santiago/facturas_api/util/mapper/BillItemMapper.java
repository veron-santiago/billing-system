package com.veron_santiago.facturas_api.util.mapper;

import com.veron_santiago.facturas_api.persistence.entity.BillItem;
import com.veron_santiago.facturas_api.presentation.dto.entities.BillItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillItemMapper {
    BillItemDTO billItemToBillItemDTO(BillItem billItem);
    BillItem billItemDTOToBillItem(BillItemDTO billItemDTO);
}