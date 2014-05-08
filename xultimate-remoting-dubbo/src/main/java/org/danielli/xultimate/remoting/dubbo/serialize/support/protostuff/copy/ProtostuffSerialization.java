package org.danielli.xultimate.remoting.dubbo.serialize.support.protostuff.copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

/**
 * Protostuff序列化器。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtostuffSerialization implements Serialization {
	
	public byte getContentTypeId() {
        return 10;
    }

    public String getContentType() {
        return "x-application/protostuff";
    }

    public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
        return new ProtostuffObjectOutput(new BufferedOutputStream(out));
    }

    public ObjectInput deserialize(URL url, InputStream is) throws IOException {
        return new ProtostuffObjectInput(new BufferedInputStream(is));
    }

}
