package com.buct.showhelp.pojo;

/**
 * 今日新增
 */
public class WebsiteLog {
    private int id;
    private int visit;
    private int user;
    private int goods;
    private int successDeal;
    private int failedDeal;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getSuccessDeal() {
        return successDeal;
    }

    public void setSuccessDeal(int successDeal) {
        this.successDeal = successDeal;
    }

    public int getFailedDeal() {
        return failedDeal;
    }

    public void setFailedDeal(int failedDeal) {
        this.failedDeal = failedDeal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
