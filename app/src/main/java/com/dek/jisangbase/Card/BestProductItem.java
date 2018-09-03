package com.dek.jisangbase.Card;

public class BestProductItem {
    private int pid;
    private int image;

    public BestProductItem(int image) {
        this.image = image;
    }

    public BestProductItem(int pid, int image) {
        this.pid = pid;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}