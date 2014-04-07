package poke.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.compression.ZlibWrapper;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerInitializer extends ChannelInitializer<SocketChannel> {
	boolean compress = false;
    protected static Logger logger = LoggerFactory.getLogger("server-Initializer");
    ServerHandler handler;

	public ServerInitializer(boolean enableCompression) {
		compress = enableCompression;
	}
	public void setHandler(ServerHandler handler)
	{
		this.handler=handler;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
<<<<<<< HEAD
        logger.info("inside initChannel of ServerHandler***********************");
=======
        logger.info("inside initChannel***********************");
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9
		// Enable stream compression (you can remove these two if unnecessary)
		if (compress) {
			pipeline.addLast("deflater", ZlibCodecFactory.newZlibEncoder(ZlibWrapper.GZIP));
			pipeline.addLast("inflater", ZlibCodecFactory.newZlibDecoder(ZlibWrapper.GZIP));
		}

		/**
		 * length (4 bytes).
		 * 
		 * Note: max message size is 64 Mb = 67108864 bytes this defines a
		 * framer with a max of 64 Mb message, 4 bytes are the length, and strip
		 * 4 bytes
		 */
		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(67108864, 0, 4, 0, 4));

		// pipeline.addLast("frameDecoder", new
		// DebugFrameDecoder(67108864, 0, 4, 0, 4));

		// decoder must be first
		pipeline.addLast("protobufDecoder", new ProtobufDecoder(eye.Comm.Request.getDefaultInstance()));
		pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("protobufEncoder", new ProtobufEncoder());

		// our server processor (new instance for each connection)
<<<<<<< HEAD
		
=======
>>>>>>> 16cb6562cc02ef3108b1149670403bb03d2d94d9
		pipeline.addLast("handler", new ServerHandler());
	}
}
