/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt.client;

import java.util.Scanner;

/**
 *
 * @author luongtx
 */
public class ClientView {
    public SinhVien newSV(){
        SinhVien sv = null;
        Scanner in = new Scanner(System.in);
        System.out.println("___Nhập SV___");
        System.out.println("msv: "); String msv = in.nextLine();
        System.out.println("Tên sv: "); String ten = in.nextLine();
        System.out.println("Điểm Toán: "); float dToan = in.nextFloat();
        System.out.println("Điểm Tin: "); float dTin = in.nextFloat();
        System.out.println("Điểm Tiếng Anh: "); float dTA  = in.nextFloat();
        sv = new SinhVien(msv,ten,dToan,dTin,dTA);
        return sv;
    }
    public void showResult(String res){
        System.out.println(res);
    }
    public void showError(String msg){
        System.out.println(msg);
    }
}
