package com.diplomadouasd.buylistapp.Model.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductCategory
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="ProductCatId")
    private int ProductCatId;
    @ColumnInfo(name="Description")
    private String Description;
    @ColumnInfo(name="GenerateBySystem")
    private Boolean GenerateBySystem;

    public Boolean getGenerateBySystem() {
        return GenerateBySystem;
    }

    public void setGenerateBySystem(Boolean generateBySystem) {
        GenerateBySystem = generateBySystem;
    }

    public ProductCategory() {
    }

    public ProductCategory(int productCatId, String description,Boolean generateBySystem) {
        ProductCatId = productCatId;
        Description = description;
        this.GenerateBySystem = generateBySystem;
    }

    public ProductCategory(String description) {
        Description = description;
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
