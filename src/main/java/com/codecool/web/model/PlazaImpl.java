package com.codecool.web.model;

import com.codecool.web.model.exceptions.NoSuchShopException;
import com.codecool.web.model.exceptions.PlazaIsClosedException;
import com.codecool.web.model.exceptions.ShopAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class PlazaImpl implements Plaza {
    
    private List<ShopRobi> shops = new ArrayList<>();
    private boolean openStatus;
    private String name;
    
    public PlazaImpl(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public List<ShopRobi> getShop() throws PlazaIsClosedException {
        if (isOpen()){
            return this.shops;
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }
    
    @Override
    public void addShop(ShopRobi shop) throws ShopAlreadyExistsException, PlazaIsClosedException{
        if (isOpen()){
            if (!shops.contains(shop)){
                shops.add(shop);
            } else {
                throw new ShopAlreadyExistsException("This shop already exists!");
            }
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }
    
    @Override
    public void removeShop(ShopRobi shop) throws NoSuchShopException, PlazaIsClosedException{
        if (isOpen()) {
            if (shops.contains(shop)) {
                shops.remove(shop);
            } else {
                throw new NoSuchShopException("No such shop!");
            }
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }
    
    @Override
    public ShopRobi findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException{
        if (isOpen()){
            for (ShopRobi shop : shops) {
                if (shop.getName().equals(name)){
                    return shop;
                }
            }
            throw new NoSuchShopException("No such shop!");
        } else {
            throw new PlazaIsClosedException("The plaza is closed!");
        }
    }
    
    @Override
    public boolean isOpen() {
        return this.openStatus;
    }
    
    @Override
    public void open() {
        this.openStatus = true;
    }
    
    @Override
    public void close() {
        this.openStatus = false;
    }
    
    @Override
    public String toString() {
        return "PlazaImpl{" +
            "shops=" + shops +
            ", openStatus=" + openStatus +
            ", name='" + name + '\'' +
            '}';
    }
}
