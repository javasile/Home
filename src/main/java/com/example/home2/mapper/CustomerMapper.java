package com.example.home2.mapper;

import com.example.home2.dto.CustomerDto;
import com.example.home2.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

//    @Mappings({
//            @Mapping(source = "company",target = "isCompany")
//    })
    CustomerDto modelToDto(Customer customer);

    List<CustomerDto> modelToDto(List<Customer> customers);

//    @Mappings({
//            @Mapping(source = "company",target = "isCompany")
//    })
    Customer dtoToModel(CustomerDto customerDto);
}
