package netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.java.Log;

@ChannelHandler.Sharable
@Log
public class SimpleChannelHandler extends SimpleChannelInboundHandler<Byte[]> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Byte[] msg) throws Exception {
        log.info("receive msg " + msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel active");
        super.channelActive(ctx);
    }
}
