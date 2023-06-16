package com.example.business.service.converter;

import com.example.business.model.Rating;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "jakarta", implementationName = "RatingMapperBusinessEntity", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RatingMapper {

    // * https://stackoverflow.com/questions/36223752/prevent-cyclic-references-when-converting-with-mapstruct
    // * https://github.com/mapstruct/mapstruct-examples/blob/main/mapstruct-mapping-with-cycles/src/main/java/org/mapstruct/example/mapper/CycleAvoidingMappingContext.java

    @Named("toBusiness")
    @InheritInverseConfiguration
    Rating toBusiness(
            com.example.client.persistence.entity.Rating from,
            @MappingTarget Rating to,
            @Context CycleAvoidingMappingContext context);
}
