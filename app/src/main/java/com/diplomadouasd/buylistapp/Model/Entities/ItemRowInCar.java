package com.diplomadouasd.buylistapp.Model.Entities;
public class ItemRowInCar {
    private String itemDescription;
    private int itemId;
    private  Integer ItemQTY;
    private  Double ItemPrice;

    public ItemRowInCar(int ItemId, String itemDescription, Integer itemQTY,Double itemPrice)
    {
        this.itemDescription = itemDescription;
        this.itemId = ItemId;
        this.ItemQTY = itemQTY;
        this.ItemPrice = itemPrice;
    }

    public Integer getItemQTY() {
        return ItemQTY;
    }

    public void setItemQTY(Integer itemQTY) {
        ItemQTY = itemQTY;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
