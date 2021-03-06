package client.multicast;

import java.net.*;
import java.io.*;

public class MCSender {

	public static void main(String args[]) {
		try {
			MulticastSocket ms = new MulticastSocket();
			ms.setTimeToLive(1);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			while (true) {
				int ch;
				String s = new String();

				do {
					ch = System.in.read();
					if (ch != '\n') {
						s = s + (char) ch;
					}
				} while (ch != '\n');

				System.out.println("Sending message: " + s);

				byte[] buf = s.getBytes();
				DatagramPacket dp = new DatagramPacket(buf, buf.length, ia, 4099);
				ms.send(dp);

				byte[] bufferReceive = new byte[65536];
				DatagramPacket dgPacketReceive = new DatagramPacket(bufferReceive, bufferReceive.length);
				ms.receive(dgPacketReceive);

				System.out.println("Received: " + new String(dgPacketReceive.getData()));
			}
		} catch (IOException e) {
			System.out.println("Exception:" + e);
		}
	}

}
