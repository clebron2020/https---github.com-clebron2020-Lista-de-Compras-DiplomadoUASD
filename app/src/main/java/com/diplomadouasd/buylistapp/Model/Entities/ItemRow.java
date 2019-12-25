package com.diplomadouasd.buylistapp.Model.Entities;
public class ItemRow {
    private String itemDescription;
    private int itemId;
    private  byte[] ImageItem;
    private  int ItemQTY;
    private  Double ItemPrice;
    private Boolean ItemIsSystem;
    private Boolean Exist;

    public ItemRow(int ItemId, String itemDescription,byte[] ImageItem,Boolean itemIsSystem,boolean exist)
    {
        this.itemDescription = itemDescription;
        this.itemId = ItemId;
        this.ImageItem = ImageItem;
        this.ItemIsSystem = itemIsSystem;
        this.Exist = exist;
    }

    public Boolean getExist() {
        return Exist;
    }

    public void setExist(Boolean exist) {
        Exist = exist;
    }

    public Boolean getItemIsSystem() {
        return ItemIsSystem;
    }

    public void setItemIsSystem(Boolean itemIsSystem) {
        ItemIsSystem = itemIsSystem;
    }

    public int getItemQTY() {
        return ItemQTY;
    }

    public void setItemQTY(int itemQTY) {
        ItemQTY = itemQTY;
    }

    public Double getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        ItemPrice = itemPrice;
    }

    public byte[] getItemImage() {
        return ImageItem;
    }

    public void setItemImage(byte[] ItemImage) {
        ImageItem = ItemImage;
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
