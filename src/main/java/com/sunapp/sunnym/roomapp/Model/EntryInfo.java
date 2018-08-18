package com.sunapp.sunnym.roomapp.Model;

/**
 * Created by SunnyM on 10/18/2015.
 */
public class EntryInfo {

    private String sUserName;
    private String sDate;
    private String sItemName;
    private String sTotalPrice;
    private String sPriceBreak;
    private String sShare;

    public EntryInfo()
    {

    }

    public String getUserName() {
        return sUserName;
    }

    public void setUserName(String sUserName) {
        this.sUserName = sUserName;
    }

    public String getDate() {
        return sDate;
    }

    public void setDate(String sDate) {
        this.sDate = sDate;
    }

    public String getItemName() {
        return sItemName;
    }

    public void setItemName(String sItemName) {
        this.sItemName = sItemName;
    }

    public String getTotalPrice() {
        return sTotalPrice;
    }

    public void setTotalPrice(String sTotalPrice) {
        this.sTotalPrice = sTotalPrice;
    }

    public String getPriceBreak() {
        return sPriceBreak;
    }

    public void setPriceBreak(String sPriceBreak) {
        this.sPriceBreak = sPriceBreak;
    }

    public String getShare() {
        return sShare;
    }

    public void setShare(String sShare) {
        this.sShare = sShare;
    }
}
