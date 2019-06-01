package com.example.demo.services.user;

import com.example.demo.exceptions.BusinessResourceException;
import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserBO> getAllUsers() throws BusinessResourceException{
        List<UserEntity> userEntitiesFound
                = IteratorUtils.toList(userRepository.findAll().iterator());
        return UserMapper.INSTANCE.entitiesToBOs(userEntitiesFound);
    }

    @Override
    public UserBO getUserById(Long id) {
        try {
            Optional<UserEntity> userFound = userRepository.findById(id);
            if (userFound.isPresent()){
                return UserMapper.INSTANCE.entityToBO(userFound.get());
            }
            else{
                throw new BusinessResourceException("UserNotFound", "Erreur utilisation non trouvé avec l'id :" +id, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            throw new BusinessResourceException("Delete User Error", "Erreur de suppression de l'utilisateur avec l'identifiant: "+id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserBO getUserByName(String name) throws BusinessResourceException {
        try {
            Optional<UserEntity> userFound = userRepository.findByName(name);
            if (userFound.isPresent()){
                return UserMapper.INSTANCE.entityToBO(userFound.get());
            }
            else{
                throw new BusinessResourceException("UserNotFound", "Erreur utilisation non trouvé avec l'id :" + name, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            throw new BusinessResourceException("Delete User Error", "Erreur de suppression de l'utilisateur avec l'identifiant: "+ name, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public UserBO saveOrUpdateUser(UserBO user) {
        try{
            //Check if we make a modification
            UserEntity userToSave = UserMapper.INSTANCE.BOtoEntity(user);
            userRepository.save(userToSave);
            return user;
        }catch(Exception ex){
            throw new BusinessResourceException("Create Or Update User Error", "Erreur de création ou de mise à jour de l'utilisateur: "+user.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteUser(Long id) throws BusinessResourceException {
        try{
            userRepository.deleteById(id);
        }catch(Exception ex){
            throw new BusinessResourceException("Delete User Error", "Erreur de suppression de l'utilisateur avec l'identifiant: "+id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}