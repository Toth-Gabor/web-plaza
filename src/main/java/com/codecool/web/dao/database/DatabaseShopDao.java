package com.codecool.web.dao.database;

import com.codecool.web.dao.ShopDao;
import com.codecool.web.model.ShopRobi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseShopDao extends AbstractDao implements ShopDao {

    public DatabaseShopDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<ShopRobi> findAll() throws SQLException {
        List<ShopRobi> shops = new ArrayList<>();
        String sql = "SELECT id, name FROM shops";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                shops.add(fetchShop(resultSet));
            }
        }
        return shops;
    }

    @Override
    public List<ShopRobi> findAllByCouponId(int couponId) throws SQLException {
        List<ShopRobi> shops = new ArrayList<>();
        String sql = "SELECT s.id, s.name " +
            "FROM shops AS s " +
            "JOIN coupons_shops AS cs ON s.id = cs.shop_id " +
            "JOIN coupons AS c ON c.id = cs.coupon_id " +
            "WHERE c.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, couponId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    shops.add(fetchShop(resultSet));
                }
            }
        }
        return shops;
    }

    @Override
    public ShopRobi findById(int id) throws SQLException {
        String sql = "SELECT id, name FROM shops WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchShop(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public ShopRobi add(String name) throws SQLException {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        boolean autoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        String sql = "INSERT INTO shops (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            executeInsert(statement);
            int id = fetchGeneratedId(statement);
            return new ShopRobi(id, name);
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(autoCommit);
        }
    }

    private ShopRobi fetchShop(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new ShopRobi(id, name);
    }
}
