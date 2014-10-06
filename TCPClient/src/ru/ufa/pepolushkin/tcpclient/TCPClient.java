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
	    String text;
        while(true) {
            try(Socket socket = new Socket("localhost", port)) {
                text = scan.nextLine();
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(text);
                String data = dis.readUTF();
                logger.info(data);
	            if ("exit".equalsIgnoreCase(data)) {
		            break;
	            }
            } catch(UnknownHostException e) {
                logger.severe(e.toString());
            } catch(EOFException e) {
                logger.severe(e.toString());
            } catch(IOException e) {
                logger.severe(e.toString());
            }
        }
    }
    
}
