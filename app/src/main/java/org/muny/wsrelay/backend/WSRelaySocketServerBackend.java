package org.muny.wsrelay.backend;

import org.java_websocket.WebSocket;
import org.java_websocket.framing.CloseFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.muny.wsrelay.MainActivity;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.CryptoPrimitive;
import java.util.UUID;

public class WSRelaySocketServerBackend extends WebSocketServer {
    public int maxClients;
    private int currentAmountOfClients = 0;

    private int maxSocketCount = 40;
//    private Socket[] socketPool;
    public class SocketIdentifier {
        private int sockId;
        public NettyIOBackend backend;

    public SocketIdentifier(int sockId) {
        this.sockId = sockId;
    }

    public int getSockId() {
        return sockId;
    }

    public void setBackend(NettyIOBackend backend) {
        this.backend = backend;
    }
}
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("WEBSOCKET SERVER CONN START!");

        if (currentAmountOfClients == maxClients){
            conn.close(CloseFrame.NORMAL, "Too many connections!");

            return;
        }
        conn.setAttachment(new SocketIdentifier(Integer.parseInt(UUID.randomUUID().toString().replace("-", ""))));

        currentAmountOfClients += 1;

    }
    public WSRelaySocketServerBackend(InetSocketAddress socketAddress) {
        super(socketAddress);
        this.maxClients = maxClients;
    }
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {

    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        SocketIdentifier si = conn.getAttachment();

    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {

        
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.out.println("Aww :(");
        ex.printStackTrace();
    }

    @Override
    public void onStart() {
        System.out.println("Yay!");
    }
}
