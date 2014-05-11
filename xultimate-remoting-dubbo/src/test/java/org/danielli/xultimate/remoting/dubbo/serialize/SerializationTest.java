package org.danielli.xultimate.remoting.dubbo.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.danielli.xultimate.core.compression.support.NullCompressor;
import org.danielli.xultimate.remoting.dubbo.serialize.support.java.JavaSerialization;
import org.danielli.xultimate.remoting.dubbo.serialize.support.kryo.KryoSerialization;
import org.danielli.xultimate.remoting.dubbo.serialize.support.protobuf.ProtobufSerialization;
import org.danielli.xultimate.remoting.dubbo.serialize.support.protostuff.ProtostuffSerialization;
import org.danielli.xultimate.util.performance.PerformanceMonitor;
import org.danielli.xultimate.util.time.stopwatch.support.AdvancedStopWatchSummary;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.dubbo.common.serialize.ObjectInput;
import com.alibaba.dubbo.common.serialize.ObjectOutput;
import com.alibaba.dubbo.common.serialize.Serialization;
import com.alibaba.dubbo.common.serialize.support.dubbo.DubboSerialization;
import com.alibaba.dubbo.common.serialize.support.hessian.Hessian2Serialization;

/**
 * 实际应用情况应该是多线程。多线程情况下Kryo应该会有另外的状态变化。
 */
public class SerializationTest {

	private SerializerObject serializerObject;
	
	@Before
	public void before() {
		serializerObject = new SerializerObject();
		serializerObject.setBool(true);
		serializerObject.setDate(new Date());
//		serializerObject.setDateTime(new DateTime());
		serializerObject.setInteger(24);
		serializerObject.setLongNum(Long.MAX_VALUE);
		serializerObject.setString("呵呵，a,1");
	}
	
	@Test
	public void test() throws ClassNotFoundException, IOException {
		PerformanceMonitor.start("SerializationTest");
		Hessian2Serialization hessian2Serialization = new Hessian2Serialization();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 264
				receive(hessian2Serialization, send(hessian2Serialization));
			}
			PerformanceMonitor.mark("hessian2Serialization" + i);
		}
		
		DubboSerialization dubboSerialization = new DubboSerialization();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 238
				receive(dubboSerialization, send(dubboSerialization));
			}
			PerformanceMonitor.mark("dubboSerialization" + i);
		}
		
		JavaSerialization javaSerialization = new JavaSerialization();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 471
				receive(javaSerialization, send(javaSerialization));
			}
			PerformanceMonitor.mark("javaSerialization + Snappy" + i);
		}
		
		KryoSerialization notCompressKryoSerialization = new KryoSerialization() {
			{
				compressor = NullCompressor.COMPRESSOR;
				decompressor = NullCompressor.COMPRESSOR;
			}
		};
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 242
				receive(notCompressKryoSerialization, send(notCompressKryoSerialization));
			}
			PerformanceMonitor.mark("kryoSerialization" + i);
		}
		
		ProtobufSerialization notCompressProtobufSerialization = new ProtobufSerialization() {
			{
				compressor = NullCompressor.COMPRESSOR;
				decompressor = NullCompressor.COMPRESSOR;
			}
		};
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 229
				receive(notCompressProtobufSerialization, send(notCompressProtobufSerialization));
			}
			PerformanceMonitor.mark("protobufSerialization" + i);
		}
		
		KryoSerialization kryoSerialization = new KryoSerialization();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 211
				receive(kryoSerialization, send(kryoSerialization));
			}
			PerformanceMonitor.mark("kryoSerialization + Snappy" + i);
		}
		
		ProtobufSerialization protobufSerialization = new ProtobufSerialization();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 199
				receive(protobufSerialization, send(protobufSerialization));
			}
			PerformanceMonitor.mark("protobufSerialization + Snappy" + i);
		}
		
		ProtostuffSerialization protostuffSerialization = new ProtostuffSerialization();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 100000; j++) {
				// length: 199
				receive(protostuffSerialization, send(protostuffSerialization));
			}
			PerformanceMonitor.mark("protostuffSerialization + Snappy" + i);
		}
		
		PerformanceMonitor.stop();
		PerformanceMonitor.summarize(new AdvancedStopWatchSummary(true));
		PerformanceMonitor.remove();
	}
	
	private ByteArrayInputStream send(Serialization serialization) throws IOException {
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		ObjectOutput objectOutput = serialization.serialize(null, arrayOutputStream);
		objectOutput.writeUTF("2.5.3");
		objectOutput.writeUTF("org.danielli.xultimate.remoting.service.AccountService");
		objectOutput.writeUTF("1.0.0");
		objectOutput.writeUTF("insertAccount");
		objectOutput.writeUTF("Lorg/danielli/xultimate/remoting/dto/Account;");
		objectOutput.writeObject(serializerObject);
		objectOutput.flushBuffer();
		byte[] result = arrayOutputStream.toByteArray();
		System.out.println(result.length);
		return new ByteArrayInputStream(result);
	}
	
	private void receive(Serialization serialization, ByteArrayInputStream arrayInputStream) throws IOException, ClassNotFoundException {
		ObjectInput objectInput = serialization.deserialize(null, arrayInputStream);
		objectInput.readUTF();
		objectInput.readUTF();
		objectInput.readUTF();
		objectInput.readUTF();
		objectInput.readUTF();
		objectInput.readObject();
	}
}

class SerializerObject implements Serializable {
	private static final long serialVersionUID = -9083252535959846024L;

	private String string;
	
	private Integer integer;
	
	private Long longNum;
	
	private Date date;
	
//	private DateTime dateTime;	// Hessian不支持。
	
	private Boolean bool;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public Long getLongNum() {
		return longNum;
	}

	public void setLongNum(Long longNum) {
		this.longNum = longNum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

//	public DateTime getDateTime() {
//		return dateTime;
//	}
//
//	public void setDateTime(DateTime dateTime) {
//		this.dateTime = dateTime;
//	}

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}
}
