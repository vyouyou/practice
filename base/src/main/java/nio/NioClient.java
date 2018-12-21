package nio;

import lombok.extern.java.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * @author qisy01
 * @create 18-12-21
 * @since 1.0.0
 */
@Log
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        ByteBuffer bb = ByteBuffer.allocate(1024);
        socketChannel.connect(new InetSocketAddress(10000));
        while (true) {
            selector.select();
            for (SelectionKey selectionKey : selector.selectedKeys()) {
                if (selectionKey.isConnectable()) {
                    socketChannel.finishConnect();
                    write((SocketChannel) selectionKey.channel(), bb, selector);
                }
                if (selectionKey.isWritable()) {
                    selectionKey.channel().register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    log.info("log is aaa");
                    write((SocketChannel) selectionKey.channel(), bb, selector);
                }
            }
        }
    }


    public static void write(SocketChannel channel, ByteBuffer bb, Selector selector) {
        try {
            bb.clear();
            bb.put("i am smt".getBytes());
            channel.write(bb);
            channel.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


