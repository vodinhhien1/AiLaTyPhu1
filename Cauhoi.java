package com.example.ductri.ailatyphu.model;

public class Cauhoi {
    private int Id;
    private String cauhoi;
    private String cauA;
    private String cauB;
    private String cauC;
    private String cauD;
    private int dapan;

    public  Cauhoi(){

    }
    public Cauhoi(int Id, String cauhoi, String cauA, String cauB, String cauC,String cauD, int dapan) {
        this.Id = Id;
        this.cauhoi = cauhoi;
        this.cauA = cauA;
        this.cauB = cauB;
        this.cauC = cauC;
        this.cauD = cauD;
        this.dapan = dapan;
    }
    public Cauhoi(String cauhoi, String cauA, String cauB, String cauC,String cauD, int dapan) {
        this.cauhoi = cauhoi;
        this.cauA = cauA;
        this.cauB = cauB;
        this.cauC = cauC;
        this.cauD = cauD;
        this.dapan = dapan;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getCauA() {
        return cauA;
    }

    public void setCauA(String cauA) {
        this.cauA = cauA;
    }

    public String getCauB() {
        return cauB;
    }

    public void setCauB(String cauB) {
        this.cauB = cauB;
    }

    public String getCauC() {
        return cauC;
    }

    public void setCauC(String cauC) {
        this.cauC = cauC;
    }

    public String getCauD() {
        return cauD;
    }

    public void setCauD(String cauD) {
        this.cauD = cauD;
    }

    public int getDapan() {
        return dapan;
    }

    public void setDapan(int dapan) {
        this.dapan = dapan;
    }
}