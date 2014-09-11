/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel Polushkin
 */
public class UDPServer {
    
    static final Logger logger = Logger.getLogger(UDPServer.class.getName());

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(6789)) {
            logger.log(Level.INFO, "Port: {0}", socket.getPort());
            byte[] buf = new byte[1000];
            while(true) {
                DatagramPacket pack = new DatagramPacket(buf, buf.length);
                logger.info("Waiting for pack receive");
                socket.receive(pack);
                logger.log(Level.INFO, "Message: {0}", new String(pack.getData()));
                String replyMsg = "Accepted!";
                DatagramPacket reply = new DatagramPacket(replyMsg.getBytes(), replyMsg.length(), pack.getAddress(), pack.getPort());
                socket.send(reply);
            }
        } catch (SocketException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
}
