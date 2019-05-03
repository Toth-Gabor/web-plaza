package com.codecool.web.service;

import com.codecool.web.model.ShopRobi;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface ShopService {

    List<ShopRobi> getShops() throws SQLException;

    ShopRobi getShop(String id) throws SQLException, ServiceException;

    ShopRobi addShop(String name) throws SQLException, ServiceException;
}
