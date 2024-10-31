package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.UserService;
import com.example.ecommerce.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    
    @Autowired
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    public CartController(CartService cartService, UserService userService, ProductService productService){
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Cart>> getCartById(@RequestParam Long id) {
        Optional<Cart> cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/user")
    public ResponseEntity<Cart> getCartByUserId(@RequestParam Long userId) {
        List<Cart> carts = cartService.getAllCarts();
        for(Cart cart: carts){
            if(cart.getUserId() == userId)
                return ResponseEntity.ok(cart);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/cartByEmail")
    public ResponseEntity<Cart> getCartByEmail(@RequestParam String email) {
        if(!userService.getUserByEmail(email).isEmpty()){
            Long userId = userService.getUserByEmail(email).get().getId();
            List<Cart> carts = cartService.getAllCarts();
            for(Cart cart: carts){
                if(cart.getUserId() == userId)
                    return ResponseEntity.ok(cart);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        Cart cartAux = cartService.saveCart(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartAux);
    }

    @DeleteMapping
    public ResponseEntity<Optional<Cart>> deleteCart(@RequestParam Long id){
        Optional<Cart> cart = cartService.deleteCart(id);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/procesar")
    public boolean procesarCart(@RequestParam String email){
        if(userService.getUserByEmail(email).isPresent()){
            Long userId = userService.getUserByEmail(email).get().getId();
            Long cartId = null;
            for(Cart cart: cartService.getAllCarts()){
                if(cart.getUserId() == userId){
                    cartId = cart.getId();
                }
            }
            if(cartId != null){
                cartService.deleteCart(cartId);
                return true;
            }
        }
        return false;
    }
    
    @PostMapping("/add")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Long idProduct, @RequestParam String email) {
        if(userService.getUserByEmail(email).isPresent() && productService.getProductById(idProduct).isPresent()){
            Long userId = userService.getUserByEmail(email).get().getId();
            Cart cartUser = null;
            for(Cart cart: cartService.getAllCarts()){
                if(cart.getUserId() == userId){
                    cartUser = cart;
                }
            }
            if(cartUser != null){
                //Revisar si se guarda
                cartUser.getProductsId().add(idProduct);
                cartUser.setTotalPrice(cartUser.getTotalPrice() + productService.getProductById(idProduct).get().getPrice());
            }else{
                ArrayList<Long> productIds = new ArrayList<>();
                productIds.add(idProduct);
                cartUser = new Cart(productIds, userId, productService.getProductById(idProduct).get().getPrice());
            }
            cartService.saveCart(cartUser);
            return ResponseEntity.ok(cartUser);
        }else{
            return null;
        }
    }
    
    

}
