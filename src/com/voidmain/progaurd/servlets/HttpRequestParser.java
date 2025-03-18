package com.voidmain.progaurd.servlets;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class HttpRequestParser {

	public static List<Object> requestHandler(HttpServletRequest request,Object obj)
	{
		List<Object> requestInfo=new ArrayList<Object>();

		if (ServletFileUpload.isMultipartContent(request)) {
			
			List<FileItem> items =null;
			
			try {
				items= new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
			} catch (Exception e) {
				e.printStackTrace();
			}

			requestInfo.add(parseRequest(items,obj));
			requestInfo.add(parseRequest(request,items));
		}
		else
		{
			requestInfo.add(parseRequest(request, obj));
		}

		return requestInfo;
	}

	public static List<String> parseRequest(HttpServletRequest request,List<FileItem> items)
	{
		String FILE_PATH=request.getServletContext().getRealPath("/images/");

		List<String> fileNames=new ArrayList<String>();

		for (FileItem item : items) {

			// processes only fields that are not form fields
			if (!item.isFormField()) {

				String fileName=item.getName();

				try {
					item.write(new File(FILE_PATH+fileName));
					fileNames.add(fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return fileNames;
	}

	public static Object parseRequest(List<FileItem> items,Object obj)
	{
		for (FileItem item : items) {

			Method[] methods=obj.getClass().getDeclaredMethods();

			for(Method method : methods)
			{
				String methodName=method.getName();

				if(methodName.equalsIgnoreCase("set"+item.getFieldName()))
				{
					try {
						method.setAccessible(true);
						method.invoke(obj,item.getString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return obj;
	}

	public static Object parseRequest(HttpServletRequest request,Object obj)
	{
		Enumeration<String> enumeration=request.getParameterNames();

		while(enumeration.hasMoreElements())
		{
			String parameterName=enumeration.nextElement();

			Method[] methods=obj.getClass().getDeclaredMethods();

			for(Method method : methods)
			{
				String methodName=method.getName();

				if(methodName.equalsIgnoreCase("set"+parameterName))
				{
					try {
						method.setAccessible(true);
						method.invoke(obj,request.getParameter(parameterName));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		return obj;
	}
}
