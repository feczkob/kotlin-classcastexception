package com.example.controller.converter;

import com.example.business.model.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", implementationName = "RatingMapperInterfaceBusiness", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingMapper extends BaseMapper {

    @Mapping(target = "id", expression = "java(null)")
    void toBusiness(com.example.controller.request.Rating from, @MappingTarget Rating to);

    com.example.controller.response.Rating toResponse(Rating from);
}
