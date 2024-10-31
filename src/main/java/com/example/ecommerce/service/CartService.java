package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;

public interface CartService {
    /*
     * Obtiene todos lo carritos
     * 
     * @return lista de carritos
     */
    List<Cart> getAllCarts();

    /**
     * Obtiene un carrito por su id.
     *
     * @param Id del carrito
     * @return carrito
     */
    Optional<Cart> getCartById(Long id);

    /**
     * Guarda un Carrito.
     *
     * @param Cart para guardar
     * @return Carrito añadido
     */
    Cart saveCart(Cart cart);
    
    /**
     * Elimina un Carrito.
     *
     * @param Id del carrito
     * @return Carrito eliminado
     */
    Optional<Cart> deleteCart(Long id);
}
