package com.btb.tcloud.common.network.protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import com.btb.test.support.LogUtil;


public class SocketService {
	private static final String LOG_TAG = SocketService.class.getSimpleName();
	
	private SocketServer itsService = null;
	private ServerSocket serverSocket = null;
	private Thread serviceThread = null;
	private boolean running = false;
	private LinkedList<Thread> threads = new LinkedList<Thread>();
	
	public SocketService(int port, SocketServer service) throws Exception {
		itsService = service;
		serverSocket = new ServerSocket(port);
	
		serviceThread  = new Thread(new Runnable(){
			@Override
			public void run() {
				serviceThread();
			}
		});
		serviceThread.start();
	}

	public void close() throws Exception {
		waitForServerThreadToStart();;
		running = false;
		serverSocket.close();
		serviceThread.join();
		waitForServerThreads();
	}

	private void waitForServerThreadToStart() {
		while (running = false) {
			Thread.yield();
		}
	}
	
	private void serviceThread() {
		LogUtil.e(LOG_TAG, "serviceThread Start!!!");
		running  = true;
		while(running) {
			try {
				Socket s = serverSocket.accept();
				LogUtil.e(LOG_TAG, "client accepted!!!");
				startServerThread(s);
			} catch (IOException e) {
				e.printStackTrace();
				LogUtil.e(LOG_TAG, e.getMessage());
			}
		}
	}

	private void startServerThread(Socket s) {
		Thread serverThread = new Thread(new ServerRunner(s));
		synchronized (threads) {
			threads.add(serverThread);
		}
		serverThread.start();
	}
	
	private void waitForServerThreads() throws InterruptedException {
		while (threads.size() > 0) {
			Thread t;
			synchronized (threads) {
				t = threads.getFirst();
			}
			t.join();
		}
	}
	
	private class ServerRunner implements Runnable {
		private Socket itsSocket;

		public ServerRunner(Socket s) {
			itsSocket = s;
		}

		@Override
		public void run() {
			try {
				itsService.serve(itsSocket);
				synchronized (threads) {
					threads.remove(Thread.currentThread());
				}
				itsSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
