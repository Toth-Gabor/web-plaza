package com.codecool.web.model;

public abstract class Product {
    
    protected long barcode;
    protected String name;
    protected String manufacturer;
    
    
    protected Product(long barcode, String name, String manufacturer) {
        this.barcode = barcode;
        this.name = name;
        this.manufacturer = manufacturer;
    }
    
    public long getBarcode() {
        return this.barcode;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getManufacturer() {
        return this.manufacturer;
    }
    
    @Override
    public String toString() {
        return "Product{" +
            "barcode=" + barcode +
            ", name='" + name + '\'' +
            ", manufacturer='" + manufacturer + '\'' +
            '}';
    }
}
