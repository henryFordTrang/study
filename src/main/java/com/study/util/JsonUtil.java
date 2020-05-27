/**
 * 
 */
package com.study.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import java.lang.reflect.Field;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method; 

/**
 * @author zhaoxf
 * @date 2016年7月6日
 */
public class JsonUtil {
	public static <T>T parseObject(String text , Class<T> clazz)
	{
		return JSON.parseObject(text, clazz);
	}
	
	public static <T>T parseObject(Object obj , Class<T> clazz)
	{
//		System.out.println("-------------"+JSON.toJSONString(obj));
		return JSON.parseObject(JSON.toJSONString(obj), clazz);
	}
	
	public static <T>T gsonParseObject(Object obj , Class<T> clazz)
	{
		Gson gson = new Gson();
		return gson.fromJson(gson.toJson(obj), clazz);
	}
	
	 /** 
     * 复制源对象和目标对象的属性值   转化（String类型为空字符不为null）
     * 
     */  
    public static void copyClass(Object source, Object target) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{  
          
        Class sourceClass = source.getClass();//得到对象的Class  
        Class targetClass = target.getClass();//得到对象的Class  
          
        Field[] sourceFields = sourceClass.getDeclaredFields();//得到Class对象的所有属性  
        Field[] targetFields = targetClass.getDeclaredFields();//得到Class对象的所有属性  
          
        for(Field sourceField : sourceFields){  
            String name = sourceField.getName();//属性名  
            Class type = sourceField.getType();//属性类型  
              
            String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);  
              
            Method getMethod = sourceClass.getMethod("get" + methodName);//得到属性对应get方法  
              
            Object value = getMethod.invoke(source);//执行源对象的get方法得到属性值  
            if(type.equals(String.class)&& value==null ){
            	value = new String("");
            }
            for(Field targetField : targetFields){  
                String targetName = targetField.getName();//目标对象的属性名  
                  
                if(targetName.equals(name)){  
                    Method setMethod = targetClass.getMethod("set" + methodName, type);//属性对应的set方法  
                      
                    setMethod.invoke(target, value);//执行目标对象的set方法  
                }  
            }  
        }  
    }  
}
