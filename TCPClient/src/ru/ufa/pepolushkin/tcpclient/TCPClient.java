/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.tcpclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel Polushkin
 */
public class TCPClient {
    
    static final Logger logger = Logger.getLogger(TCPClient.class.getName());

    public static void main(String[] args) {
        int port = 7896;
        Scanner scan = new Scanner(System.in);
        DataInputStream dis;
        DataOutputStream dos;
        while(true) {
            try(Socket socket = new Socket("localhost", port)) {
                String text = scan.nextLine();
                if("exit".equalsIgnoreCase(text))
                    break;
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(text);
                String data = dis.readUTF();
                logger.info(data);
            } catch(UnknownHostException e) {
                logger.log(Level.SEVERE, "Socket: {0}", e.getMessage());
            } catch(EOFException e) {
                logger.log(Level.SEVERE, "EOF: {0}", e.getMessage());
            } catch(IOException e) {
                logger.log(Level.SEVERE, "IOException: {0}", e.getMessage());
            }
        }
    }
    
}
