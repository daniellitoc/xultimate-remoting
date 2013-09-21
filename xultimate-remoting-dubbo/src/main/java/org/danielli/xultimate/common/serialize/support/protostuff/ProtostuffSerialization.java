package org.danielli.xultimate.common.serialize.support.protostuff;

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
import org.danielli.xultimate.core.serializer.protostuff.RpcProtostuffSerializer;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

public class ProtostuffSerialization implements Serialization {
	
	private BooleanSerializer booleanSerializer = new BooleanSerializer();
	
	private ByteSerializer byteSerializer = new ByteSerializer();
	
	private ShortSerializer shortSerializer = new ShortSerializer();
	
	private IntegerSerializer integerSerializer = new IntegerSerializer();
	
	private LongSerializer longSerializer = new LongSerializer();
	
	private FloatSerializer floatSerializer = new FloatSerializer();
	
	private DoubleSerializer doubleSerializer = new DoubleSerializer();
	
	private StringSerializer stringSerializer = new StringSerializer();
	
	private RpcProtostuffSerializer rpcProtostuffSerializer = new RpcProtostuffSerializer();
	
	public byte getContentTypeId() {
        return 10;
    }

    public String getContentType() {
        return "x-application/protostuff";
    }

    public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
        return new ProtostuffObjectOutput(out, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializer, floatSerializer, doubleSerializer, stringSerializer, rpcProtostuffSerializer);
    }

    public ObjectInput deserialize(URL url, InputStream is) throws IOException {
        return new ProtostuffObjectInput(is, booleanSerializer, byteSerializer, shortSerializer, integerSerializer, longSerializer, floatSerializer, doubleSerializer, stringSerializer, rpcProtostuffSerializer);
    }

}
