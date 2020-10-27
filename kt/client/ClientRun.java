/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kt.client;

/**
 *
 * @author luongtx
 */
public class ClientRun {
    public static void main(String[] args) {
        ClientView view = new ClientView();
        CientControl control = new CientControl(view);;
    }
}
