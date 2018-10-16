package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author qisy01
 * @create 18-10-15
 * @since 1.0.0
 */
@Slf4j
public class NioClient {

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandler(){
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                super.channelActive(ctx);
                                ByteBuf byteBuf = Unpooled.copyInt(111);
                                ctx.writeAndFlush(byteBuf);
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.info("nioclient receive something");
                                super.channelRead(ctx, msg);
                            }
                        });
                    }
                });
        try {
            ChannelFuture future = b.connect("127.0.0.1", 15000);
            future.sync();
//            future.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
