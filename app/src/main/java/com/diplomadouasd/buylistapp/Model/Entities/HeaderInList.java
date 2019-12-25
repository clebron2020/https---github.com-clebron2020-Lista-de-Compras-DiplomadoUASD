package com.diplomadouasd.buylistapp.Model.Entities;
public class HeaderInList {
    private String _HeaderName;
    public HeaderInList(String headername)
    {
       this._HeaderName = headername;
    }

    public String get_HeaderName() {
        return _HeaderName;
    }

    public void set_HeaderName(String _HeaderName) {
        this._HeaderName = _HeaderName;
    }
}
