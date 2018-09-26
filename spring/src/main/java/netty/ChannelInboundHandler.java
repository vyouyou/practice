package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.java.Log;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log
//@ChannelHandler.Sharable
public class ChannelInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channel active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] data = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(data);
        log.info(data + "");

        byteBuf = Unpooled.copiedBuffer(intsToBytes(new int[]{32, 0x1006, 0, 0, 0, 0, 0, 0}));
        ctx.writeAndFlush(byteBuf);

    }

    public static byte[] intsToBytes(int[] ints) {
        byte[] bytes = {};
        for (int i = 0; i < ints.length; i++) {
            byte[] newBytes = new byte[]{(byte) ((ints[i] >> 24) & 0xFF),
                    (byte) ((ints[i] >> 16) & 0xFF),
                    (byte) ((ints[i] >> 8) & 0xFF),
                    (byte) (ints[i] & 0xFF)};
            bytes = ArrayUtils.addAll(bytes, newBytes);
        }
        return bytes;
    }

    ;
}
