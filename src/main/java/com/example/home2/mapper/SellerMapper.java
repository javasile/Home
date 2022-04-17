package com.example.home2.mapper;

import com.example.home2.dto.client.SellerDto;
import com.example.home2.dto.house.AddressDto;
import com.example.home2.model.client.Seller;
import com.example.home2.model.house.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SellerMapper {

//    @Mappings({
//            @Mapping(source = "company",target = "isCompany")
//    })
    SellerDto modelToDto(Seller seller);

    List<SellerDto> modelToDto(List<Seller> sellers);

//    @Mappings({
//            @Mapping(source = "company",target = "isCompany")
//    })
    Seller dtoToModel(SellerDto sellerDto);
}
