package com.codecool.web.model;

import com.codecool.web.model.exceptions.NoSuchShopException;
import com.codecool.web.model.exceptions.PlazaIsClosedException;
import com.codecool.web.model.exceptions.ShopAlreadyExistsException;

import java.util.List;

public interface Plaza {
    
    List<ShopRobi> getShop() throws PlazaIsClosedException;
    
    void addShop(ShopRobi shop) throws ShopAlreadyExistsException, PlazaIsClosedException;
    
    void removeShop(ShopRobi shop) throws NoSuchShopException, PlazaIsClosedException;
    
    ShopRobi findShopByName(String name) throws NoSuchShopException, PlazaIsClosedException;
    
    boolean isOpen();
    
    void open();
    
    void close();
    
    String toString();
    
}
