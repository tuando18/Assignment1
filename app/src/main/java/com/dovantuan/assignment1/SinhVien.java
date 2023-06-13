package com.dovantuan.assignment1;

public class SinhVien {
    public String namePB;
    public int iconId;

    public SinhVien() {
    }

    public SinhVien(String namePB, int iconId) {
        this.namePB = namePB;
        this.iconId = iconId;
    }

    public String getNamePB() {
        return namePB;
    }

    public void setNamePB(String namePB) {
        this.namePB = namePB;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
