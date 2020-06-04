package vn.edu.ntu.vancuong.my_fragmentdemo.controller;

import java.util.List;

import vn.edu.ntu.vancuong.my_fragmentdemo.model.Product;

public interface IController {
    public List<Product> getAllProduct();
    public boolean addtoCart(Product p);
    public List<Product> getShoppingCart();
    public void clearShoppingCart();
}
