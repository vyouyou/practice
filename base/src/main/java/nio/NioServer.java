package nio;

import lombok.extern.java.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author qisy01
 * @create 18-12-21
 * @since 1.0.0
 */
@Log
public class NioServer {


    public static void main(String[] args) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(10000));
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                for (SelectionKey key : selector.selectedKeys()) {
                    if (!key.isValid()){
                        continue;
                    }
                    log.info("select");
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssc.accept();
                        if (sc ==null) {
                            continue;
                        }
                        sc.configureBlocking(false);
//                        sc.register(selector, SelectionKey.OP_READ);
                    }
//                    if (key.isReadable()) {
//                        log.info("read st");
//                        byteBuffer.clear();
//                        byteBuffer.put("aaaa".getBytes());
//                        SocketChannel sc = (SocketChannel) key.channel();
//                        sc.write(byteBuffer);
//                        sc.register(selector, SelectionKey.OP_WRITE);
//                    }
//                    if (key.isWritable()) {
//                        log.info("write st");
//                        key.channel().register(selector,SelectionKey.OP_READ);
//                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
