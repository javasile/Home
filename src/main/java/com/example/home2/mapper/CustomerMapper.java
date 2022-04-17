package com.example.home2.mapper;

import com.example.home2.dto.client.CustomerDto;
import com.example.home2.dto.house.AddressDto;
import com.example.home2.model.client.Customer;
import com.example.home2.model.house.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
