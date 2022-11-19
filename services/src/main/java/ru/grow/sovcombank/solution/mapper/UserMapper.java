package ru.grow.sovcombank.solution.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.grow.sovcombank.solution.dto.user.UserAddDto;
import ru.grow.sovcombank.solution.dto.user.UserDto;
import ru.grow.sovcombank.solution.dto.user.UserInfoUpdateDto;
import ru.grow.sovcombank.solution.entity.user.UserEntity;

@Mapper(componentModel = "spring", uses = PassportMapper.class)
public interface UserMapper {
    @Mapping(source = "userEntity.passportEntity", target = "passport", qualifiedByName = "passportToClient")
    UserDto toClient(UserEntity userEntity);

    @Mapping(source = "updateDto.passport", target = "passportEntity", qualifiedByName = "passportToServer")
    UserEntity infoEditDtoToServer(UserInfoUpdateDto updateDto);

    @Mapping(source = "userAddDto.passport", target = "passportEntity", qualifiedByName = "passportToServer")
    UserEntity addDtoToServer(UserAddDto userAddDto);
}
