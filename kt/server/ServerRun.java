/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt.server;

/**
 *
 * @author luongtx
 */
public class ServerRun {
    public static void main(String[] args) {
        ServerView view = new ServerView();
        ServerControl control = new ServerControl(view);
        new Thread(control).start();
    }
}
