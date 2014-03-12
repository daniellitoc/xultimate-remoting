package org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf;

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
 * Protobuf序列化器。
 * 
 * @author Daniel Li
 * @since 18 Jun 2013
 */
public class ProtobufSerialization implements Serialization {
	
	public byte getContentTypeId() {
        return 11;
    }

    public String getContentType() {
        return "x-application/protobuf";
    }

    public ObjectOutput serialize(URL url, OutputStream out) throws IOException {
        return new ProtobufObjectOutput(new BufferedOutputStream(out));
    }

    public ObjectInput deserialize(URL url, InputStream is) throws IOException {
        return new ProtobufObjectInput(new BufferedInputStream(is));
    }

}
