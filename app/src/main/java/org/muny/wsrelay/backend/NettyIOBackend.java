package org.muny.wsrelay.backend;

import org.java_websocket.WebSocket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.file.spi.FileSystemProvider;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyIOBackend extends SimpleChannelInboundHandler<ByteBuf> {
    private final WebSocket webSocket;
    private NioSocketChannel ch;
    public EventLoopGroup g = new NioEventLoopGroup(4);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        ByteBuffer send = ByteBuffer.allocateDirect(msg.capacity());
        send.put(msg.nioBuffer());
        send.flip();
        if (webSocket.isOpen()) {
            webSocket.send(send);
        }
    }

    public void sendPacket(ByteBuffer packet) {
        if (ch != null && ch.isWritable()){
            ch.write(Unpooled.wrappedBuffer(packet));
        }
    }
    public NettyIOBackend(String remoteAddr, int remotePort, boolean tcpNoDelay, WebSocket webSocket) throws InterruptedException {
        this.webSocket = webSocket;
        Bootstrap b = new Bootstrap();
        b.group(g).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

                ch.pipeline().addLast(NettyIOBackend.this);


            }
        }).remoteAddress(remoteAddr, remotePort).option(NioChannelOption.TCP_NODELAY, true);
        b.channel(NioSocketChannel.class);
        ch = (NioSocketChannel) b.connect().sync().channel();

    }
}
