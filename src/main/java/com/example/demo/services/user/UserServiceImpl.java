package com.example.demo.services.user;

import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return UserMapper.INSTANCE.entitiesToBos(userEntitiesFound);
    }

    @Override
    public UserBO getUserById(Long id) {
        Optional<UserEntity> userFound = userRepository.findById(id);
        if( !userFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a user with id " + id);
        }
        return UserMapper.INSTANCE.entityToBo(userFound.get());
    }

    @Override
    public UserBO getUserByName(String name) {
        Optional<UserEntity> userFound = userRepository.findByName(name);
        if(!userFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a user with name " + name);
        }
        return UserMapper.INSTANCE.entityToBo(userFound.get());
    }

    @Override
    public UserBO saveOrUpdateUser(UserBO user) {
        UserEntity userToSave = UserMapper.INSTANCE.boToEntity(user);
        userToSave = userRepository.save(userToSave);
        UserBO userBOSaved = UserMapper.INSTANCE.entityToBo(userToSave);
        return userBOSaved;
    }

    @Override
    public void deleteUser(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("Didn't find a user with id " + id);
        }
    }

}