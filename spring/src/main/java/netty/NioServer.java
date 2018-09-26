package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Log
public class NioServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        ChannelInboundHandler handler = new ChannelInboundHandler();
                        pipeline.addLast(handler);
                        pipeline.addLast(new ChannelInboundHandler());
                    }
                });
        Integer[] ports = {15000, 15001
                , 15002
        };

        ExecutorService service = Executors.newCachedThreadPool();
        Arrays.asList(ports).stream().forEach(integer -> {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Channel channel = serverBootstrap.bind(integer).sync().channel();
                        log.info("i am channel" + channel.localAddress().toString());
                        channel.closeFuture().sync();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
    }
}
