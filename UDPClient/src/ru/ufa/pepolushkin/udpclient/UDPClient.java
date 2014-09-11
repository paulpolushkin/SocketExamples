/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.udpclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavel Polushkin
 */
public class UDPClient {
    
    static final Logger logger = Logger.getLogger(UDPClient.class.getName());

    public static void main(String[] args) {
        try(DatagramSocket socket = new DatagramSocket(6788, InetAddress.getByName("localhost"))) {
            logger.log(Level.INFO, "Port: {0}", socket.getPort());
            logger.info("Waiting for manual input string");
            Scanner scan = new Scanner(System.in);
            String msg = scan.nextLine();
            byte[] byteMsg = msg.getBytes();
            InetAddress host = InetAddress.getByName("localhost");
            int port = 6789;
            DatagramPacket pack = new DatagramPacket(byteMsg, msg.length(), host, port);
            socket.send(pack);
            byte[] buf = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buf, buf.length);
            logger.info("Waiting for reply");
            socket.receive(reply);
            logger.log(Level.INFO, "Reply: {0}", new String(reply.getData()));
        } catch (SocketException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
}
