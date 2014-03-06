package org.danielli.xultimate.remoting.dubbo.serialize.support.kryo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.danielli.xultimate.core.serializer.java.BooleanSerializer;
import org.danielli.xultimate.core.serializer.java.ByteSerializer;
import org.danielli.xultimate.core.serializer.java.DoubleSerializer;
import org.danielli.xultimate.core.serializer.java.FloatSerializer;
import org.danielli.xultimate.core.serializer.java.IntegerSerializer;
import org.danielli.xultimate.core.serializer.java.LongSerializer;
import org.danielli.xultimate.core.serializer.java.ShortSerializer;
import org.danielli.xultimate.core.serializer.java.StringSerializer;
import org.danielli.xultimate.core.serializer.kryo.RpcKryoSerializer;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

public class KryoSerialization implements Serialization {
	
	private BooleanSerializer booleanSerializer = new BooleanSerializer();
	
	private ByteSerializer byteSerializer = new ByteSerializer();
	
	private ShortSerializer shortSerializer = new ShortSerializer();
	
	private IntegerSerializer integerSerializer = new IntegerSerializer();
	
	private LongSerializer longSerializer = new LongSerializer();
	
	private FloatSerializer floatSerializer = new FloatSerializer();
	
	private DoubleSerializer doubleSerializer = new DoubleSerializer();
	
	private StringSerializer stringSerializer = new StringSerializer();
	
	private RpcKryoSerializer rpcKryoSerializer = new RpcKryoSerializer();
	
	public byte getContentTypeId() {
        return 12;
    }

    public String getContentType() {
        return "x-application/kryo";
    }

    public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
        return new KryoObjectOutput(out, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializer, floatSerializer, doubleSerializer, stringSerializer, rpcKryoSerializer);
    }

    public ObjectInput deserialize(URL url, InputStream is) throws IOException {
        return new KryoObjectInput(is, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializer, floatSerializer, doubleSerializer, stringSerializer, rpcKryoSerializer);
    }

}
