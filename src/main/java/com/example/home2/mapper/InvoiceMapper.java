package com.example.home2.mapper;

import com.example.home2.dto.InvoiceDto;
import com.example.home2.model.Invoice;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceDto modelToDto(Invoice invoice);

    List<InvoiceDto> modelToDto(List<Invoice> invoice);

    Invoice dtoToModel(InvoiceDto invoiceDto);

}
