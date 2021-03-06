/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.tcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 *
 * @author Pavel Polushkin
 */
public class Connection implements Runnable {
    private Socket clientSocket;
    static final Logger logger = Logger.getLogger(Connection.class.getName());
    
    public Connection(Socket socket) {
        clientSocket = socket;
    }
    
    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
            String data = dis.readUTF();
            dos.writeUTF(data);
            logger.info(data);
        } catch (EOFException ex) {
            logger.severe(ex.toString());
        } catch (IOException ex) {
            logger.severe(ex.toString());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ex) {
                logger.severe(ex.toString());
            }
        }
    }
}
