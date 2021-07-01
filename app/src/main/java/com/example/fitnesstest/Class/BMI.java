package com.example.fitnesstest.Class;

public class BMI {
    String date;
    double cao,nang,bmi;
    int hinh;

    public BMI(String date, double cao, double nang, double bmi, int hinh) {
        this.date = date;
        this.cao = cao;
        this.nang = nang;
        this.bmi = bmi;
        this.hinh = hinh;
    }

    public BMI(double cao, double nang, double bmi, int hinh) {
        this.cao = cao;
        this.nang = nang;
        this.bmi = bmi;
        this.hinh = hinh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCao() {
        return cao;
    }

    public void setCao(double cao) {
        this.cao = cao;
    }

    public double getNang() {
        return nang;
    }

    public void setNang(double nang) {
        this.nang = nang;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}
