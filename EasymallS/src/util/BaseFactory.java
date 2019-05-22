package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseFactory {

	private BaseFactory() {
	}

	private static BaseFactory factory = new BaseFactory();

	public static BaseFactory getFactory() {
		return factory;
	}

	public <T> T getInstence(Class<T> ct) {
		T t = null;
		try {
			ClassLoader loader = this.getClass().getClassLoader();
			String path = loader.getResource("uconfig.properties").getPath();
			String className = ct.getSimpleName();
			System.out.println("className:"+className);
			Properties prop = new Properties();
			prop.load(new FileInputStream(path));
			System.out.println(prop.getProperty(className));
			t = (T) Class.forName(prop.getProperty(className)).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

}
