package com.viseo.ttmik.mapper;

import com.viseo.ttmik.dto.LessonDto;
import com.viseo.ttmik.model.LessonEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);
    LessonEntity toEntity(LessonDto dto);
    LessonDto toDto(LessonEntity entity);


}
