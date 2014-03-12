package org.danielli.xultimate.remoting;

import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;
import org.danielli.xultimate.core.serializer.kryo.RpcKryoSerializer;
import org.danielli.xultimate.core.serializer.protostuff.RpcProtobufSerializer;
import org.danielli.xultimate.core.serializer.protostuff.RpcProtostuffSerializer;

public class SerializerTotal {

	public static BooleanSerializer booleanSerializer = new BooleanSerializer();
	
	public static ByteSerializer byteSerializer = new ByteSerializer();
	
	public static ShortSerializer shortSerializer = new ShortSerializer();
	
	public static IntegerSerializer integerSerializer = new IntegerSerializer();
	
	public static LongSerializer longSerializer = new LongSerializer();
	
	public static FloatSerializer floatSerializer = new FloatSerializer();
	
	public static DoubleSerializer doubleSerializer = new DoubleSerializer();
	
	public static StringSerializer stringSerializer = new StringSerializer();
	
	public static RpcKryoSerializer rpcKryoSerializer = new RpcKryoSerializer();
	
	public static RpcProtobufSerializer rpcProtobufSerializer = new RpcProtobufSerializer();
	
	public static RpcProtostuffSerializer rpcProtostuffSerializer = new RpcProtostuffSerializer();
	
}
