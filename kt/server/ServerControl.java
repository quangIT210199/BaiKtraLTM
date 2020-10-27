/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt.server;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import com.kt.client.SinhVien;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
/**
 *
 * @author luongtx
 */
public class ServerControl implements Runnable {
    private DatagramSocket serverSocket;
    private int serverPort = 8888;
    private int clientPort;
    private ServerView serverView;
    private DatagramPacket receivePacket;
    public ServerControl(ServerView serverView){
        this.serverView = serverView;
    }
    @Override
    public void run(){
        openUDPport(serverPort);
        serverView.showLog("Server is running on port " + serverPort);
        listening();
        closeUDPport();
    }
    public void openUDPport(int port){
        try{
            serverSocket = new DatagramSocket(port);
        }catch(Exception ex){
            serverView.showLog(Arrays.toString(ex.getStackTrace()));
            ex.printStackTrace();
        }
    }
    public void closeUDPport(){
        try{
            serverSocket.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void listening() {

        SinhVien sv = receiveData();
        String message = "";
        if (sv.getDTB() > 5) {
            message = "pass";
        } else {
            message = "fail";
        }
        serverView.showLog(Float.toString(sv.getDTB()) + " " + message);
        sendData(message);
    }
    public void sendData(String message){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream bos = new ObjectOutputStream(baos);
            bos.writeObject(message);
            byte[] send_buf = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(send_buf, send_buf.length, receivePacket.getSocketAddress());
            serverSocket.send(sendPacket);
        }catch(Exception ex){
            serverView.showLog(Arrays.toString(ex.getStackTrace()));
        }
    }
    public SinhVien receiveData(){
        SinhVien sv = null;
        try{
            byte[] receive_buf = new byte[1024];
            receivePacket = new DatagramPacket(receive_buf, receive_buf.length);
            serverSocket.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receive_buf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            sv = (SinhVien) ois.readObject();
        }catch(Exception ex){
            serverView.showLog(Arrays.toString(ex.getStackTrace()));
        }
        return sv;
    }
}
