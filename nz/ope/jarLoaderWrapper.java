package nz.ope;

import java.lang.reflect.*;
import java.io.File;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.Arrays;

import anywheresoftware.b4a.BA.Author;
import anywheresoftware.b4a.BA.ShortName;
import anywheresoftware.b4a.BA.Version;

import anywheresoftware.b4a.AbsObjectWrapper;

@Author("Trevor Hart")
@Version(1.0f)
@ShortName("jarLoader")

public class jarLoaderWrapper
{
	public static synchronized void loadJAR(String jarFile) 
	{ 
		try
		{
			java.io.File JAR = new java.io.File(jarFile);
			java.net.URLClassLoader systemLoader = (java.net.URLClassLoader)ClassLoader.getSystemClassLoader();
			java.net.URL JARURL =  JAR.toURI().toURL();
			for (java.net.URL loadedJars : java.util.Arrays.asList(systemLoader.getURLs()))
			{
				if (loadedJars.equals(JARURL))
				{
					return;
				}
			}
			
			java.lang.reflect.Method methodAddURL = java.net.URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{java.net.URL.class});
			methodAddURL.setAccessible(true); 
			methodAddURL.invoke(systemLoader, new Object[]{JARURL});
		} catch (Exception e)
		{
		  throw new RuntimeException(e);	   
		}
	}
}