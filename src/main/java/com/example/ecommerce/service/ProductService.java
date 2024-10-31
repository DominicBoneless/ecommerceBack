package com.example.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.example.ecommerce.entity.Product;

public interface ProductService {
    /**
     * Obtiene todos los productos.
     *
     * @return Lista de productos
     */
    List<Product> getAllProducts();

    /**
     * Obtiene un producto por su id.
     *
     * @param Id del producto
     * @return Producto
     */
    Optional<Product> getProductById(Long id);

    /**
     * Guarda un producto.
     *
     * @param Producto para guardar
     * @return Producto añadido
     */
    Product saveProduct(Product product);

    /**
     * Actualiza un producto.
     *
     * @param Id del producto
     * @param Producto actualizado
     * @return Producto actualizado
     */
    Product updateProduct(Long id, Product product);

    /**
     * Elimina un producto.
     *
     * @param Id del producto
     * @return Producto eliminado
     */
    Optional<Product> deleteProduct(Long id);
}
