/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anritsu.systemdelta.systemdeltastarter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ro100051
 */
public class Main {
    public static void main(String [] args){
        if(args[0] == null) {
            System.out.println("Please give as parameter the location of web application archive!");
            System.exit(0);
        }
        SystemDeltaWebserver s = new SystemDeltaWebserver(args[0], args[1]);
       
    }
    
}
