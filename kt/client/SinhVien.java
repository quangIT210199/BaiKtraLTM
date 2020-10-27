/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt.client;

import java.io.Serializable;

/**
 *
 * @author luongtx
 */
public class SinhVien implements Serializable{
    private String msv;
    private String ten;
    private float diemToan;
    private float diemTin;
    private float diemTA;

    public SinhVien(String msv, String ten, float diemToan, float diemTin, float diemTA) {
        this.msv = msv;
        this.ten = ten;
        this.diemToan = diemToan;
        this.diemTin = diemTin;
        this.diemTA = diemTA;
    }
    public float getDTB(){
        return (diemToan + diemTin + diemTA)/3;
    }

}
