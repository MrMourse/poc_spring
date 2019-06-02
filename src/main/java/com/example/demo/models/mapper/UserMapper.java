package com.example.demo.models.mapper;

import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserDTO;
import com.example.demo.models.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserBO entityToBO(UserEntity user);
    UserEntity BOtoEntity(UserBO user);
    List<UserBO> entitiesToBOs(List<UserEntity> users);
    UserDTO BOToDTO(UserBO user);
    UserBO DTOToBO(UserDTO user);
    List<UserDTO> BOsToDTOs(List<UserBO> users);

}
