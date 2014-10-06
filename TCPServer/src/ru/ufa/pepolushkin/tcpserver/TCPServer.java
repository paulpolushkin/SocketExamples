/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.ufa.pepolushkin.tcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * @author Pavel Polushkin
 */
public class TCPServer {

	static final Logger logger = Logger.getLogger(TCPServer.class.getName());

	public static void main(String[] args) {
		int port = 7896;
		ExecutorService exec = Executors.newFixedThreadPool(10);
		ServerSocket listenSocket = null;
		try {
			listenSocket = new ServerSocket(port);
			while (true) {
				Connection conn = new Connection(
						listenSocket.accept());
				exec.execute(conn);
			}
		} catch (IOException e) {
			logger.severe(e.toString());
		} finally {
			exec.shutdown();
			try {
				if (listenSocket != null) {
					listenSocket.close();
				}
			} catch (IOException ex) {
				logger.severe(ex.toString());
			}
		}
	}

}
