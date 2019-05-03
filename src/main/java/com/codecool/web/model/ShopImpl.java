package com.codecool.web.model;

import com.codecool.web.model.exceptions.NoSuchProductException;
import com.codecool.web.model.exceptions.OutOfStockException;
import com.codecool.web.model.exceptions.ProductAlreadyExistsException;
import com.codecool.web.model.exceptions.ShopIsClosedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopImpl implements Shop {
    
    private String name;
    private String owner;
    private boolean shopStatus;
    public Map<Long, ShopEntryImpl> products = new HashMap<>();
    
    public ShopImpl(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public String getOwner() {
        return this.owner;
    }
    
    @Override
    public boolean isOpen() {
        return shopStatus;
    }
    
    @Override
    public void open() {
        this.shopStatus = true;
    }
    
    @Override
    public void close() {
        this.shopStatus = false;
    }
    
    @Override
    public Product getProductByBarcode(Long barcode) throws NoSuchProductException {
        return products.get(barcode).getProduct();
    }
    
    @Override
    public int getQuantity(Long barcode) throws NoSuchProductException{
        if (products.get(barcode) != null){
            return products.get(barcode).getQuantity();
        }
        throw new NoSuchProductException("There is not any product with this barcode!");
    }
    
    @Override
    public List<Product> getProducts() throws ShopIsClosedException {
        if (isOpen()){
            List<Product> productsList = new ArrayList<>();
            for (ShopEntryImpl value : products.values()) {
                productsList.add(value.getProduct());
            }
            return productsList;
        }
        throw new ShopIsClosedException("Please open the shop first!");
    }
    
    @Override
    public Product findByName(String name) throws NoSuchProductException, ShopIsClosedException {
        if (isOpen()){
            for (ShopEntryImpl value : products.values()){
                if (value.getProduct().getName().equals(name)){
                    return value.getProduct();
                }
            }
            throw new NoSuchProductException("There is not any product with this name!");
        }
        throw new ShopIsClosedException("Please open the shop first!");
    }
    
    @Override
    public float getPrice(long barcode) throws ShopIsClosedException, NoSuchProductException {
        if (isOpen()){
            for (ShopEntryImpl value : products.values()){
                if (value.getProduct().getBarcode() == barcode){
                    return value.getPrice();
                }
            }
            throw new NoSuchProductException("There is not any product with this name!");
        }
        throw new ShopIsClosedException("Please open the shop first!");
    }
    
    @Override
    public boolean hasProduct(long barcode) throws ShopIsClosedException {
        if (isOpen()){
            if(products.containsKey(barcode)){
                return true;
            } else {
                return false;
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
        
    }
    @Override
    public void addNewProduct(Product product, int quantity, float price) throws ShopIsClosedException, ProductAlreadyExistsException {
        if (isOpen()){
            if(!products.containsKey(product.getBarcode())){
                products.put(product.getBarcode(), new ShopEntryImpl(product, quantity, price));
            } else {
                throw new ProductAlreadyExistsException("This product already exists!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
    
    @Override
    public void addProduct(long barcode, int quantity) throws ShopIsClosedException, NoSuchProductException {
        if (isOpen()){
            if (products.get(barcode) != null){
                products.get(barcode).setQuantity(quantity);
            } else {
                throw new NoSuchProductException("There is not any product with this barcode!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
    
    @Override
    public Product byProduct(long barcode) throws ShopIsClosedException, NoSuchProductException, OutOfStockException {
        if (isOpen()){
            if (products.get(barcode) != null){
                if (products.get(barcode).getQuantity() >= 1){
                    products.get(barcode).decreaseQuantity(1);
                    return products.get(barcode).getProduct();
                } else {
                    throw new OutOfStockException("There are not enough quantity of this product!");
                }
            } else {
                throw new NoSuchProductException("There is not any product with this barcode!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
    }
    
    @Override
    public List<Product> buyProducts(long barcode, int quantity) throws ShopIsClosedException, NoSuchProductException, OutOfStockException {
        List<Product> buyedProducts = new ArrayList<>();
        if (isOpen()){
            if (products.get(barcode) != null){
                if (products.get(barcode).getQuantity() >= quantity){
                    products.get(barcode).decreaseQuantity(quantity);
                    buyedProducts.add(products.get(barcode).getProduct());
                } else {
                    throw new OutOfStockException("There are not enough quantity of this product!");
                }
            } else {
                throw new NoSuchProductException("There is not any product with this barcode!");
            }
        } else {
            throw new ShopIsClosedException("Please open the shop first!");
        }
        return null;
    }
    
    @Override
    public String toString() {
        return " " + name + " shop";
    }
    
    private class ShopEntryImpl{
        private Product product;
        private int quantity;
        private float price;
    
        private ShopEntryImpl(Product product, int quantity, float price) {
            this.product = product;
            this.quantity = quantity;
            this.price = price;
        }
    
        public Product getProduct() {
            return this.product;
        }
    
        public void setProduct(Product product) {
            this.product = product;
        }
    
        public int getQuantity() {
            return this.quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    
        public void increaseQuantity(int amount){
            this.quantity += amount;
        }
        public void decreaseQuantity(int amount){
            this.quantity -= amount;
        }
    
        public float getPrice() {
            return this.price;
        }
    
        public void setPrice(float price) {
            this.price = price;
        }
    
        @Override
        public String toString() {
            return "ShopEntryImpl{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
        }
    }
}
