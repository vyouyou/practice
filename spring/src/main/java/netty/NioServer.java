package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.java.Log;

import java.nio.channels.SelectionKey;

@Log
public class NioServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        ChannelInboundHandler handler = new ChannelInboundHandler();
                        pipeline.addLast(handler);
                    }
                });
        Integer[] ports = {15000, 15001, 15002};

        try {
            Channel channel = serverBootstrap.bind(15000).sync().channel();
            log.info("i am channel" + channel.localAddress().toString());
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        ExecutorService service = Executors.newCachedThreadPool();
//        Arrays.asList(ports).stream().forEach(integer -> {
//            service.submit(() -> {
//                try {
//                    Channel channel = serverBootstrap.bind(integer).sync().channel();
//                    log.info("i am channel" + channel.localAddress().toString());
//                    channel.closeFuture().sync();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        });
    }
}
