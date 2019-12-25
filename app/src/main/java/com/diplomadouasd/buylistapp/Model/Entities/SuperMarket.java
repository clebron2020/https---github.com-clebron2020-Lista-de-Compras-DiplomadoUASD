package com.diplomadouasd.buylistapp.Model.Entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SuperMarket {
    @ColumnInfo(name="SuperMarketId")
    @PrimaryKey(autoGenerate = true)
    private int SuperMarketId;
    @ColumnInfo(name="Name")
    private String Name;
    @ColumnInfo(name="IsFavorite")
    private boolean IsFavorite;

    public boolean getIsFavorite() {
        return IsFavorite;
    }

    public void setIsFavorite(boolean favorite) {
        IsFavorite = favorite;
    }

    public SuperMarket() {
    }

    public SuperMarket(String name, boolean isFavorite) {
        Name = name;
        IsFavorite = isFavorite;
    }

    public int getSuperMarketId() {
        return SuperMarketId;
    }

    public void setSuperMarketId(int superMarketId) {
        SuperMarketId = superMarketId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
