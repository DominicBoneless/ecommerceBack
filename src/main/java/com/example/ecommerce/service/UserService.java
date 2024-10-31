package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.User;

public interface UserService {
    /*
     * Obtiene todos lo usuarios
     * 
     * @return lista de usuarios
     */
    List<User> getAllUsers();

    /**
     * Obtiene un usuario por su id.
     *
     * @param Id del usuario
     * @return usuario
     */
    Optional<User> getUserById(Long id);

    /*
     * Obtiene un usuario por su e-mail
     * 
     * @param String del usuario
     * @return usuario
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Guarda un usuario.
     *
     * @param User para guardar
     * @return usuario añadido
     */
    User saveUser(User user);
    
    /**
     * Elimina un usuario.
     *
     * @param Id del usuario
     * @return usuario eliminado
     */
    Optional<User> deleteUser(Long id);
}
