package com.chenqinhao.cat.cache.util;

import com.chenqinhao.cat.cache.exception.SerializerException;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 序列化工具
 * Created by chenqinhao on 2017/7/13.
 */
public class ProtoStuffSerializerUtils {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();
    private static Objenesis objenesis = new ObjenesisStd(true);

    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.getSchema(clazz);
            if (schema != null) {
                cachedSchema.put(clazz, schema);
            }
        }
        return schema;
    }

    /**
     * 序列化对象
     * @param target
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(T target) {
        if (target == null) {
            throw new SerializerException("serialize throws arguments exception: " + target);
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Class<T> clazz = (Class<T>) target.getClass();
            Schema<T> schema = getSchema(clazz);
            byte[] protostuff = ProtostuffIOUtil.toByteArray(target, schema, buffer);
            return protostuff;
        } catch (Exception e) {
            throw new SerializerException("serialize(" + target.getClass() + " " + target + ")发生异常!", e);
        } finally {
            buffer.clear();
        }

    }

    /**
     * 反序列化
     * @param paramArrayOfByte
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] paramArrayOfByte, Class<T> targetClass) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new SerializerException("deserialize throws exception, byteArray is null!");
        }
       try {
           T instance = objenesis.newInstance(targetClass);
           Schema<T> schema = getSchema(targetClass);
           ProtostuffIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
           return instance;
       } catch (Exception e) {
           throw new SerializerException("deserialize create instance type error!", e);
       }
    }

    /**
     * 序列化列表
     * @param targetList
     * @param <T>
     * @return
     */
    public static <T> byte[] serializeList(List<T> targetList) {
        if (targetList == null || targetList.isEmpty()) {
            throw new SerializerException("serializeList throws arguments exception: " + targetList);
        }
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            Class<T> clazz = (Class<T>) targetList.get(0).getClass();
            Schema<T> schema = getSchema(clazz);
            ProtostuffIOUtil.writeListTo(bos, targetList, schema, buffer);
            byte[] protostuff = bos.toByteArray();
            return protostuff;
        } catch (Exception e) {
            throw new SerializerException("serializeList throws exception  " + targetList, e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化列表
     * @param paramArrayOfByte
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> deserializeList(byte[] paramArrayOfByte, Class<T> targetClass) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new SerializerException("deserializeList throws exception, byteList is null");
        }
        try {
            Schema<T> schema = getSchema(targetClass);
            List<T> result = ProtostuffIOUtil.parseListFrom(new ByteArrayInputStream(paramArrayOfByte), schema);
            return result;
        } catch (Exception e) {
            throw new SerializerException("deserializeList throws exception: ", e);
        }
    }

}
