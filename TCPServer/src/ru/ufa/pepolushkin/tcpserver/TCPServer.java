/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel Polushkin
 */
public class TCPServer {
    
    static final Logger logger = Logger.getLogger(TCPServer.class.getName());

    public static void main(String[] args) {
        int port = 7896;
        int i = 0;
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            ExecutorService exec = Executors.newFixedThreadPool(10);
            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection conn = new Connection(clientSocket);
                exec.execute(conn);
                i++;
                if(i > port) {
                    exec.shutdown();
                }
            }
        } catch(IOException e) {
            logger.log(Level.SEVERE, "IOException: {0}", e.getMessage());
        }
    }
    
}
