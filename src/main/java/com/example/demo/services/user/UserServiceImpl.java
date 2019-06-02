package com.example.demo.services.user;

import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserBO> getAllUsers(){
        List<UserEntity> userEntitiesFound
                = IteratorUtils.toList(userRepository.findAll().iterator());
        return UserMapper.INSTANCE.entitiesToBOs(userEntitiesFound);
    }

    @Override
    public UserBO getUserById(Long id) {
        Optional<UserEntity> userFound = userRepository.findById(id);
        if( !userFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a user with id " + id);
        }
        return UserMapper.INSTANCE.entityToBO(userFound.get());
    }

    @Override
    public UserBO getUserByName(String name) {
        Optional<UserEntity> userFound = userRepository.findByName(name);
        if(!userFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a user with name " + name);
        }
        return UserMapper.INSTANCE.entityToBO(userFound.get());
    }

    @Override
    public UserBO saveOrUpdateUser(UserBO user) {
        //Check if we make a modification
        UserEntity userToSave = UserMapper.INSTANCE.BOtoEntity(user);
        userRepository.save(userToSave);
        return user;
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}