package org.danielli.xultimate.remoting.dubbo.serialize.support.java;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;

public class JavaSerialization implements Serialization {

	@Override
	public byte getContentTypeId() {
		return 13;
	}

	@Override
	public String getContentType() {
		return "x-application/java";
	}

	@Override
	public ObjectOutput serialize(URL url, OutputStream output) throws IOException {
		return new JavaObjectOutput(new BufferedOutputStream(output));
	}

	@Override
	public ObjectInput deserialize(URL url, InputStream input) throws IOException {
		return new JavaObjectInput(new BufferedInputStream(input));
	}

}
