package com.diplomadouasd.buylistapp.Model.Entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class MetricUnit {
  @ColumnInfo(name="MetricUnitId")
  private int MetricUnitId;
  @ColumnInfo(name="Description")
  private String Description;

    public MetricUnit(int metricUnitId, String description) {
        MetricUnitId = metricUnitId;
        Description = description;
    }

    public int getMetricUnitId() {
        return MetricUnitId;
    }

    public void setMetricUnitId(int metricUnitId) {
        MetricUnitId = metricUnitId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
