package org.danielli.xultimate.remoting;

import org.danielli.xultimate.core.serializer.java.ObjectSerializer;
import org.danielli.xultimate.core.serializer.kryo.RpcKryoSerializer;
import org.danielli.xultimate.core.serializer.protostuff.RpcProtobufSerializer;
import org.danielli.xultimate.core.serializer.protostuff.RpcProtostuffSerializer;
import org.danielli.xultimate.core.serializer.support.BaseTypeDeserializer;
import org.danielli.xultimate.core.serializer.support.BaseTypeSerializer;
import org.danielli.xultimate.core.serializer.support.NullableStreamSerializer;


public class SerializerTotal {

	public static final BaseTypeSerializer OBJECT_BASE_TYPE_SERIALIZER = new BaseTypeSerializer();
	
	public static final BaseTypeDeserializer OBJECT_BASE_TYPE_DESERIALIZER = new BaseTypeDeserializer();
	
	public static final BaseTypeSerializer KRYO_BASE_TYPE_SERIALIZER = new BaseTypeSerializer();
	
	public static final BaseTypeDeserializer KRYO_BASE_TYPE_DESERIALIZER = new BaseTypeDeserializer();
	
	public static final BaseTypeSerializer PROTOBUF_BASE_TYPE_SERIALIZER = new BaseTypeSerializer();
	
	public static final BaseTypeDeserializer PROTOBUF_BASE_TYPE_DESERIALIZER = new BaseTypeDeserializer();
	
	public static final BaseTypeSerializer PROTOSTUFF_BASE_TYPE_SERIALIZER = new BaseTypeSerializer();
	
	public static final BaseTypeDeserializer PROTOSTUFF_BASE_TYPE_DESERIALIZER = new BaseTypeDeserializer();
	
	static {
		ObjectSerializer objectSerializer = new ObjectSerializer();
		NullableStreamSerializer nullableStreamObjectSerializer = new NullableStreamSerializer(objectSerializer, objectSerializer);
		OBJECT_BASE_TYPE_SERIALIZER.setCheckKnownType(false);
		OBJECT_BASE_TYPE_SERIALIZER.setExportKnownTypeToOutputStream(false);
		OBJECT_BASE_TYPE_SERIALIZER.setPackZerosWithoutOutputStream(false);
		OBJECT_BASE_TYPE_SERIALIZER.setSerializer(nullableStreamObjectSerializer);
		OBJECT_BASE_TYPE_DESERIALIZER.setCheckKnownType(false);
		OBJECT_BASE_TYPE_DESERIALIZER.setImportKnownTypeFromInputStream(false);
		OBJECT_BASE_TYPE_DESERIALIZER.setDeserializer(nullableStreamObjectSerializer);
		
		RpcKryoSerializer rpcKryoSerializer = new RpcKryoSerializer();
		rpcKryoSerializer.setBufferSize(512);
		NullableStreamSerializer nullableStreamKryoSerializer = new NullableStreamSerializer(rpcKryoSerializer, rpcKryoSerializer);
		KRYO_BASE_TYPE_SERIALIZER.setCheckKnownType(false);
		KRYO_BASE_TYPE_SERIALIZER.setExportKnownTypeToOutputStream(false);
		KRYO_BASE_TYPE_SERIALIZER.setPackZerosWithoutOutputStream(false);
		KRYO_BASE_TYPE_SERIALIZER.setSerializer(nullableStreamKryoSerializer);
		KRYO_BASE_TYPE_DESERIALIZER.setCheckKnownType(false);
		KRYO_BASE_TYPE_DESERIALIZER.setImportKnownTypeFromInputStream(false);
		KRYO_BASE_TYPE_DESERIALIZER.setDeserializer(nullableStreamKryoSerializer);
		
		RpcProtobufSerializer rpcProtobufSerializer = new RpcProtobufSerializer();
		rpcProtobufSerializer.setBufferSize(1024);
		NullableStreamSerializer nullableStreamProtobufSerializer = new NullableStreamSerializer(rpcProtobufSerializer, rpcProtobufSerializer);
		PROTOBUF_BASE_TYPE_SERIALIZER.setCheckKnownType(false);
		PROTOBUF_BASE_TYPE_SERIALIZER.setExportKnownTypeToOutputStream(false);
		PROTOBUF_BASE_TYPE_SERIALIZER.setPackZerosWithoutOutputStream(false);
		PROTOBUF_BASE_TYPE_SERIALIZER.setSerializer(nullableStreamProtobufSerializer);
		PROTOBUF_BASE_TYPE_DESERIALIZER.setCheckKnownType(false);
		PROTOBUF_BASE_TYPE_DESERIALIZER.setImportKnownTypeFromInputStream(false);
		PROTOBUF_BASE_TYPE_DESERIALIZER.setDeserializer(nullableStreamProtobufSerializer);
		
		RpcProtostuffSerializer rpcProtostuffSerializer = new RpcProtostuffSerializer();
		rpcProtostuffSerializer.setBufferSize(1024);
		NullableStreamSerializer nullableStreamProtostuffSerializer = new NullableStreamSerializer(rpcProtostuffSerializer, rpcProtostuffSerializer);
		PROTOSTUFF_BASE_TYPE_SERIALIZER.setCheckKnownType(false);
		PROTOSTUFF_BASE_TYPE_SERIALIZER.setExportKnownTypeToOutputStream(false);
		PROTOSTUFF_BASE_TYPE_SERIALIZER.setPackZerosWithoutOutputStream(false);
		PROTOSTUFF_BASE_TYPE_SERIALIZER.setSerializer(nullableStreamProtostuffSerializer);
		PROTOSTUFF_BASE_TYPE_DESERIALIZER.setCheckKnownType(false);
		PROTOSTUFF_BASE_TYPE_DESERIALIZER.setImportKnownTypeFromInputStream(false);
		PROTOSTUFF_BASE_TYPE_DESERIALIZER.setDeserializer(nullableStreamProtostuffSerializer);
	}
	
}
