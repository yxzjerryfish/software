package fish.software.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.internal.StringUtil;

import java.util.Date;
import java.util.Scanner;

/**
 * Netty Client
 *
 * @author : Fish Paradise
 * @version 1.0
 * @date : 2019/11/24 19:03
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 7654).channel();

        String buffer = "s";
        while (!buffer.equals("exit")) {
            buffer = new Scanner(System.in).next();
            channel.writeAndFlush(new Date() + ":" + buffer);
        }
    }
}
