package com.dovantuan.assignment1;

import java.io.Serializable;

public class Listdsnv implements Serializable {
    public String maNV;

    public String name;
    public String phongBan;


    public Listdsnv() {
    }

    public Listdsnv(String maNV, String name, String phongBan) {
        this.maNV = maNV;
        this.name = name;
        this.phongBan = phongBan;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }
}
