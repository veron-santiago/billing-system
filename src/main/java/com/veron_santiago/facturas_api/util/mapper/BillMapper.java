package com.veron_santiago.facturas_api.util.mapper;

import com.veron_santiago.facturas_api.persistence.entity.Bill;
import com.veron_santiago.facturas_api.presentation.dto.entities.BillDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BillMapper {
    BillDTO billToBillDTO(Bill bill);
    Bill billDTOToBill(BillDTO billDTO);
}