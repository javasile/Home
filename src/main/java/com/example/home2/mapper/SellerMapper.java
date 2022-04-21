package com.example.home2.mapper;

import com.example.home2.dto.SellerDto;
import com.example.home2.model.Seller;
import org.mapstruct.Mapper;

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
