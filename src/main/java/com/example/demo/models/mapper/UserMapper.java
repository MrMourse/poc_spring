package com.example.demo.models.mapper;

import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserDTO;
import com.example.demo.models.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    public abstract UserBO entityToBo(UserEntity user);
    public abstract UserEntity boToEntity(UserBO user);
    public abstract List<UserBO> entitiesToBos(List<UserEntity> users);
    public abstract UserDTO boToDto(UserBO user);
    public abstract UserBO dtoToBo(UserDTO user);
    public abstract List<UserDTO> bosToDtos(List<UserBO> users);

}
