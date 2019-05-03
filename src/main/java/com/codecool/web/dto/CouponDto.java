package com.codecool.web.dto;

import com.codecool.web.model.Coupon;
import com.codecool.web.model.ShopRobi;

import java.util.List;

public final class CouponDto {

    private final Coupon coupon;
    private final List<ShopRobi> couponShops;
    private final List<ShopRobi> allShops;

    public CouponDto(Coupon coupon, List<ShopRobi> couponShops, List<ShopRobi> allShops) {
        this.coupon = coupon;
        this.couponShops = couponShops;
        this.allShops = allShops;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public List<ShopRobi> getCouponShops() {
        return couponShops;
    }

    public List<ShopRobi> getAllShops() {
        return allShops;
    }
}
