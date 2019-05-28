package com.viseo.ttmik.mapper;

import com.viseo.ttmik.dto.AccountDto;
import com.viseo.ttmik.model.AccountEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, collectionMappingStrategy = CollectionMappingStrategy.SETTER_PREFERRED)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity toEntity(AccountDto dto);
    AccountDto toDto(AccountEntity entity);
}
