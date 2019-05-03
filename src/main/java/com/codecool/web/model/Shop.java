package com.codecool.web.model;

import com.codecool.web.model.exceptions.NoSuchProductException;
import com.codecool.web.model.exceptions.OutOfStockException;
import com.codecool.web.model.exceptions.ProductAlreadyExistsException;
import com.codecool.web.model.exceptions.ShopIsClosedException;

import java.util.List;

public interface Shop {
    
    String getName();
    
    String getOwner();
    
    boolean isOpen();
    
    void open();
    
    void close();
    
    Product getProductByBarcode(Long barcode) throws NoSuchProductException;
    
    int getQuantity(Long barcode) throws NoSuchProductException;
    
    List<Product> getProducts() throws ShopIsClosedException;
    
    Product findByName(String name) throws NoSuchProductException, ShopIsClosedException;
    
    float getPrice(long barcode) throws ShopIsClosedException, NoSuchProductException;
    
    boolean hasProduct(long barcode) throws ShopIsClosedException;
    
    void addNewProduct(Product product, int quantity, float price) throws ShopIsClosedException, ProductAlreadyExistsException;
    
    void addProduct(long barcode, int quantity) throws ShopIsClosedException, NoSuchProductException;
    
    Product byProduct(long barcode) throws ShopIsClosedException, NoSuchProductException, OutOfStockException;
    
    List<Product> buyProducts(long barcode, int quantity) throws ShopIsClosedException, NoSuchProductException, OutOfStockException;
    
    String toString();
}
