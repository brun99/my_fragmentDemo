package vn.edu.ntu.vancuong.my_fragmentdemo.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.vancuong.my_fragmentdemo.model.Product;

public class Controller extends Application implements IController {
    List<Product> products = new ArrayList<>();

    List<Product> shoppingCart = new ArrayList<>();
    public Controller(){
        products.add(new Product("khoai lang","25000","vàng to"));
        products.add(new Product("khoai lang","25000","vàng to"));
        products.add(new Product("khoai lang","25000","vàng to"));
        products.add(new Product("khoai lang","25000","vàng to"));
        products.add(new Product("khoai lang","25000","vàng to"));
        products.add(new Product("khoai lang","25000","vàng to"));
    }

    @Override
    public List<Product> getAllProduct() {
        return products;
    }

    @Override
    public boolean addtoCart(Product p) {
        if (shoppingCart.contains(p))
            return false;
        else {
            shoppingCart.add(p);
            return true;
        }
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
       shoppingCart.clear();
    }
}
