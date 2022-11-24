package org.muny.wsrelay.backend;

import org.java_websocket.WebSocket;
import org.muny.wsrelay.MainActivity;
import org.muny.wsrelay.R;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SocketClientInboundHandler extends SimpleChannelInboundHandler {
    private WebSocket mainClient;
    private NioSocketChannel sockChannel;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        ByteBuffer bb = ByteBuffer.allocateDirect(buf.capacity());
        bb.put(buf.nioBuffer());
        bb.flip();
        if (mainClient.isOpen()){
            mainClient.send(bb);
        }
    }
    public SocketClientInboundHandler(WebSocket ws) {
        this.mainClient = ws;


    }
}
