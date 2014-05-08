package org.danielli.xultimate.remoting.dubbo.serialize.support.kryo.copy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.danielli.xultimate.core.serializer.kryo.support.ThreadLocalKryoGenerator;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

/**
 * Kryo序列化器。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class KryoSerialization implements Serialization {

	public byte getContentTypeId() {
        return 12;
    }

    public String getContentType() {
        return "x-application/kryo";
    }

    public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
        return new KryoObjectOutput(new org.danielli.xultimate.core.serializer.kryo.KryoObjectOutput(out, ThreadLocalKryoGenerator.INSTANCE.generate()));
    }

    public ObjectInput deserialize(URL url, InputStream is) throws IOException {
        return new KryoObjectInput(new org.danielli.xultimate.core.serializer.kryo.KryoObjectInput(is, ThreadLocalKryoGenerator.INSTANCE.generate()));
    }

}
