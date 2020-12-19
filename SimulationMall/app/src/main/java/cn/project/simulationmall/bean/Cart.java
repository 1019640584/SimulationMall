package cn.project.simulationmall.bean;

import org.litepal.crud.LitePalSupport;

/**
 * Create by ankele
 * <p>
 * 2020/12/19 - 15:54
 */
public class Cart extends LitePalSupport {
    private int imgUrl;
    private String name;
    private String describe;
    private double price;
    private boolean isRec;
    private int num;

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRec() {
        return isRec;
    }

    public void setRec(boolean rec) {
        isRec = rec;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}