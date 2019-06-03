package com.example.demo.services.user;

import com.example.demo.models.mapper.UserMapper;
import com.example.demo.models.user.UserBO;
import com.example.demo.models.user.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Implémentation du service user permettant la gestion de la couche métier.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    /**
     * Initialisation du user repository.
     *
     * @param userRepository, service de gestion des users pour la couche de la base de données.
     */
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Récupère toutes les utilisateurs.
     *
     * @return List<UserBO>
     */
    @Override
    public List<UserBO> getAllUsers(){
        List<UserEntity> userEntitiesFound
                = IteratorUtils.toList(userRepository.findAll().iterator());
        return UserMapper.INSTANCE.entitiesToBos(userEntitiesFound);
    }

    /**
     * Récupère l'utilisateur avec l'id correspondant.
     *
     * @param id, de l'utilisateur.
     * @return UserBO
     */
    @Override
    public UserBO getUserById(Long id) {
        Optional<UserEntity> userFound = userRepository.findById(id);
        if( !userFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a user with id " + id);
        }
        return UserMapper.INSTANCE.entityToBo(userFound.get());
    }

    /**
     * Récupère l'utilisateur avec le nom correspondant.
     *
     * @param name, de l'utilisateur.
     * @return UserBO
     */
    @Override
    public UserBO getUserByName(String name) {
        Optional<UserEntity> userFound = userRepository.findByName(name);
        if(!userFound.isPresent()){
            throw new EntityNotFoundException("Didn't find a user with name " + name);
        }
        return UserMapper.INSTANCE.entityToBo(userFound.get());
    }

    /**
     * Sauvegarde l'utilisateur.
     *
     * @param user, l'utilisateur à sauvegarder.
     * @return UserBO
     */
    @Override
    public UserBO saveOrUpdateUser(UserBO user) {
        UserEntity userToSave = UserMapper.INSTANCE.boToEntity(user);
        userToSave = userRepository.save(userToSave);
        return UserMapper.INSTANCE.entityToBo(userToSave);
    }

    /**
     * Supprime l'utilisateur.
     *
     * @param id, de l'utilisateur à supprimer.
     */
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