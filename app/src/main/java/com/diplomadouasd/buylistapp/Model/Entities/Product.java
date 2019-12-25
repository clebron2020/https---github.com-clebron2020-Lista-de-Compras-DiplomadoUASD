package com.diplomadouasd.buylistapp.Model.Entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product {
    @ColumnInfo(name="productId")
    @PrimaryKey(autoGenerate = true)
    private int productId;
    @ColumnInfo(name="ProductCatId")
    private int ProductCatId;
    @ColumnInfo(name="Description")
    private String Description;
    @ColumnInfo(name="GenerateBySystem")
    private Boolean GenerateBySystem;
    @ColumnInfo(name="ImageProduct")
    private byte[] ImageProduct;

    private int Exist;

    public int getExist() {
        return Exist;
    }

    public void setExist(int exist) {
        Exist = exist;
    }

    public byte[] getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(byte[] imageProduct) {
        ImageProduct = imageProduct;
    }

    public Boolean getGenerateBySystem() {
        return GenerateBySystem;
    }

    public void setGenerateBySystem(Boolean generateBySystem) {
        GenerateBySystem = generateBySystem;
    }

    private String ProductCategoryDesc;

    public String getProductCategoryDesc() {
        return ProductCategoryDesc;
    }

    public void setProductCategoryDesc(String productCategoryDesc) {
        ProductCategoryDesc = productCategoryDesc;
    }

    public Product() {
    }

    public Product(int _productId,int _productCatId, String _description) {
        productId = _productId;
        ProductCatId = _productCatId;
        Description = _description;
    }

    public Product(int productCatId, String description,byte[] imageProduct,Boolean generateBySystem) {
        ProductCatId = productCatId;
        Description = description;
        GenerateBySystem = generateBySystem;
        ImageProduct = imageProduct;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCatId() {
        return ProductCatId;
    }

    public void setProductCatId(int productCatId) {
        ProductCatId = productCatId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }




}
