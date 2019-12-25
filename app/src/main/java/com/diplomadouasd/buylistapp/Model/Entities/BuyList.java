package com.diplomadouasd.buylistapp.Model.Entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;
import java.util.List;

@Entity
@TypeConverters(DataConverter.class)
public class BuyList {
    @PrimaryKey()
    @ColumnInfo(name = "BuyListId")
    private int BuyListId;
    @ColumnInfo(name = "BuyList_CreattionDate")
    private  Date BuyListCreattionDate;
    @ColumnInfo(name = "BuyList_SuperMarketId")
    private int BuyListSuperMarketId;
    @ColumnInfo(name = "SuperMarket_Name")
    private String SuperMarketName;
    @ColumnInfo(name = "BuyList_Total")
    private Double BuyListTotal;
    @ColumnInfo(name = "Finaly")
    private boolean Finaly;

    public BuyList() {

    }

    public BuyList(int buyListId,Double buyListTotal) {
        BuyListId = buyListId;
        BuyListTotal = buyListTotal;
    }

    public BuyList(int buyListId,Date buyListCreattionDate, int buyListSuperMarketId, boolean finaly) {
        this.BuyListId = buyListId;
        this.BuyListCreattionDate = buyListCreattionDate;
        this.BuyListSuperMarketId = buyListSuperMarketId;
        this.Finaly = finaly;
    }

    public boolean isFinaly() {
        return Finaly;
    }

    public void setFinaly(boolean finaly) {
        Finaly = finaly;
    }

    public int getBuyListId() {
        return BuyListId;
    }

    public void setBuyListId(int buyListId) {
        BuyListId = buyListId;
    }

    public Date getBuyListCreattionDate() {
        return BuyListCreattionDate;
    }

    public void setBuyListCreattionDate(Date buyListCreattionDate) {
        BuyListCreattionDate = buyListCreattionDate;
    }

    public int getBuyListSuperMarketId() {
        return BuyListSuperMarketId;
    }

    public void setBuyListSuperMarketId(int buyListSuperMarketId) {
        BuyListSuperMarketId = buyListSuperMarketId;
    }

    public String getSuperMarketName() {
        return SuperMarketName;
    }

    public void setSuperMarketName(String superMarketName) {
        SuperMarketName = superMarketName;
    }

    public Double getBuyListTotal() {
        return BuyListTotal;
    }

    public void setBuyListTotal(Double buyListTotal) {
        BuyListTotal = buyListTotal;
    }
}

