package com.diplomadouasd.buylistapp.Model.Entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BuyListDetail {
    @ColumnInfo(name="BuyListDetailId")
    @PrimaryKey(autoGenerate = true)
    private int BuyListDetailId;
    @ColumnInfo(name="BuyListId")
    private int BuyListId;
    @ColumnInfo(name="ProductId")
    private int ProductId;
    @ColumnInfo(name="Quantity")
    private  int Quantity;
    @ColumnInfo(name="UnitPrice")
    private  Double UnitPrice;
    @ColumnInfo(name="InCar")
    private  Boolean InCar;

    private Boolean GenerateBySystem;

    public Boolean getGenerateBySystem() {
        return GenerateBySystem;
    }

    public void setGenerateBySystem(Boolean generateBySystem) {
        GenerateBySystem = generateBySystem;
    }

    public Boolean getInCar() {
        return InCar;
    }

    public void setInCar(Boolean inCar) {
        InCar = inCar;
    }

    private int ProductCatId;
    private String ProductCatDesc;
    private String ProductDesc;

    public String getProductCatDesc() {
        return ProductCatDesc;
    }

    public void setProductCatDesc(String productCatDesc) {
        ProductCatDesc = productCatDesc;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public int getProductCatId() {
        return ProductCatId;
    }

    public void setProductCatId(int productCatId) {
        ProductCatId = productCatId;
    }

    public BuyListDetail() {
    }

    public BuyListDetail(int buyListId, int productId, int quantity, Double unitPrice,Boolean inCar) {
        BuyListId = buyListId;
        ProductId = productId;
        Quantity = quantity;
        UnitPrice = unitPrice;
        InCar = inCar;
    }

    public int getBuyListDetailId() {
        return BuyListDetailId;
    }

    public void setBuyListDetailId(int buyListDetailId) {
        BuyListDetailId = buyListDetailId;
    }

    public int getBuyListId() {
        return BuyListId;
    }

    public void setBuyListId(int buyListId) {
        BuyListId = buyListId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        UnitPrice = unitPrice;
    }
}
