package com.codecool.web.service.simple;

import com.codecool.web.dao.ShopDao;
import com.codecool.web.model.ShopRobi;
import com.codecool.web.service.ShopService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public final class SimpleShopService implements ShopService {

    private final ShopDao shopDao;

    public SimpleShopService(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public List<ShopRobi> getShops() throws SQLException {
        return shopDao.findAll();
    }

    @Override
    public ShopRobi getShop(String id) throws SQLException, ServiceException {
        try {
            ShopRobi shop = shopDao.findById(Integer.parseInt(id));
            if (shop == null) {
                throw new ServiceException("Unknown shop");
            }
            return shop;
        } catch (NumberFormatException ex) {
            throw new ServiceException("Shop id must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public ShopRobi addShop(String name) throws SQLException, ServiceException {
        try {
            return shopDao.add(name);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
