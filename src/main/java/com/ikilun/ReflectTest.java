package com.ikilun;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;

public class ReflectTest {
	public static void main(String[] args) {
		testBean();
		//getClassField(Movie.class);
		//getClassMethod(Movie.class);
	}
	public static void testBean(){
		Movie movie = new Movie();
		movie.setId(1);
		movie.setName("阿凡达");
		movie.setShowDate(new Date());
		try {
			Method method = movie.getClass().getDeclaredMethod("setName");
			method.invoke("setName", "风声");
		} catch (Exception e) {
			e.printStackTrace();
		}
		getBeanValue(movie);
		
	}
	public static void getClassType(){
		//反射机制获取类有三种方法，我们来获取Employee类型
		try {
			//第一种方式：
			Class clazz1 = Class.forName("com.ikilun.Movie");
			System.out.println(clazz1);
			//第二种方式
			Class clazz2 = Movie.class;
			System.out.println(clazz2);
			//第三种方式（com.ikilun.Movie@15db9742 已经分配了内存，内存地址：15db9742）
			Movie movie = new Movie();
			System.out.println(movie);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void getBeanValue(Object obj){
		Field[] fields = obj.getClass().getDeclaredFields();
		for(Field field : fields){
			try {
				field.setAccessible(true);
				System.out.println(field.getName()+":" + field.get(obj));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void getClassField(Class clazz){
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			System.out.println(Modifier.toString(field.getModifiers())+" " + field.getType() + " " + field.getName());
		}
	}
	
	public static void getClassMethod(Class clazz){
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			System.out.println(Modifier.toString(method.getModifiers()) + " " + method.getReturnType() + " " + method.getName());
		}
	}
}
