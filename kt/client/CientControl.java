/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt.client;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

/**
 *
 * @author luongtx
 */
public class CientControl {
    private ClientView view;
    private DatagramSocket clientSocket;
    private int clientPort = 6666;
    private int serverPort = 8888;
    private String serverHost = "localhost";
    public CientControl(ClientView view){
        this.view = view;
        openConnection(clientPort);
        sendData(view.newSV());
        String msg = receiveData();
        String ans="";
        if(msg.equals("pass")) ans = "Sinh viên được lên lớp";
        else ans = "Sinh viên không được lên lớp";
        view.showResult(ans);
        writeFile("sv.txt", ans);
        closeConnection();
    }
    public void openConnection(int port){
        try{
            clientSocket = new DatagramSocket(port);
        }catch(Exception ex){
           view.showError(Arrays.toString(ex.getStackTrace()));
           ex.printStackTrace();
        }
    }
    public void closeConnection(){
        try{
            clientSocket.close();
        }catch(Exception ex){
           view.showError(Arrays.toString(ex.getStackTrace()));
        }
    }
    public void sendData(SinhVien sv){
        try{
            //tạo đối tượng baos để chứa dữ liệu mảng bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //tạo oos để ghi dữ liệu vào baos
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            //ghi đối tượng sv vào baos
            oos.writeObject(sv);
            //tạo một mảng byte từ baos
            byte[] send_buf = baos.toByteArray();
            InetAddress hostAddresss = InetAddress.getByName(serverHost);
//            System.out.println(hostAddresss);
            DatagramPacket sendPacket = new DatagramPacket(send_buf, send_buf.length, hostAddresss, serverPort);
            clientSocket.send(sendPacket);
        }catch(Exception ex){
           view.showError(Arrays.toString(ex.getStackTrace()));
        }
    }
    public String receiveData(){
        String serverAns = "";
        try{
            byte[] receive_buf = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receive_buf, receive_buf.length);
            clientSocket.receive(receivePacket);
            //chuyển mảng byte đọc được vào bais
            ByteArrayInputStream bais = new ByteArrayInputStream(receive_buf);
            //tạo ois để đọc dữ liệu từ bais
            ObjectInputStream ois = new ObjectInputStream(bais);
            //Đọc dữ liệu object từ ois và chuyển về kiểu mong muốn
            serverAns = (String) ois.readObject();
        }catch(Exception ex){
           view.showError(Arrays.toString(ex.getStackTrace()));
           ex.printStackTrace();
        }
        return serverAns;
    }
    public void writeFile(String file, String text) {
        try (FileOutputStream fout = new FileOutputStream(new File(file),true)
                ) {
            fout.write(text.getBytes());
        } catch (Exception ex) {
           view.showError(Arrays.toString(ex.getStackTrace()));
        }
    }
}
