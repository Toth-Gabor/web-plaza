package com.codecool.web.dao;

import com.codecool.web.model.ShopRobi;

import java.sql.SQLException;
import java.util.List;

public interface ShopDao {

    List<ShopRobi> findAll() throws SQLException;

    List<ShopRobi> findAllByCouponId(int couponId) throws SQLException;

    ShopRobi findById(int id) throws SQLException;

    ShopRobi add(String name) throws SQLException;
}
