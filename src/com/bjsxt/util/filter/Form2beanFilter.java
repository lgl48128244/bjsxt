package com.bjsxt.util.filter;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class Form2beanFilter implements Filter {

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain) throws IOException, ServletException {
		//获取请求信息，查询是否需要封装注入
		String form2bean = servletrequest.getParameter("form2bean");
		//当Form2bean的值不为null并且不为空字符串的时候
		if (form2bean != null && !"".equals(form2bean)) {
			try {
				//获取要被注入的类的Class
				Class cls = Class.forName(form2bean);
				//创建对象，用来值的注入
				Object obj = cls.getConstructor(null).newInstance(null);
				//获取所有声明的属性
				Field[] dfs = cls.getDeclaredFields();
				//遍历属性并从请求参数中获取值
				for (int i = 0; i < dfs.length; i++) {
					//获取其中的一个属性
					Field f = dfs[i];
					//获取属性的名字
					String fieldName = f.getName();
					//获取属性的值
					String fieldValue = servletrequest.getParameter(fieldName);
					//判断值是否为空
					if (fieldValue != null) {
						//						//获取属性的Set方法名字
						//						String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
						//						//获取指定的方法
						//						Method method = cls.getMethod(methodName, f.getType());
						//						//执行这个方法注入值
						//						method.invoke(obj, fieldValue);

						if (f.getType().getSimpleName().equals("Date")) {
							DateConverter dateConverter = new DateConverter();
							String[] datePattern = { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd" };
							dateConverter.setPatterns(datePattern);
							//将fieldvalue注入到对象
							BeanUtils.setProperty(obj, fieldName, dateConverter.convert(java.util.Date.class, fieldValue));
						} else {
							//将fieldvalue注入到对象
							BeanUtils.setProperty(obj, fieldName, fieldValue);
						}

					}
				}
				//将对象存放到作用域
				servletrequest.setAttribute("form2bean", obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//继续下面的请求执行
		filterchain.doFilter(servletrequest, servletresponse);

	}

	public void init(FilterConfig filterconfig) throws ServletException {

	}

	public void destroy() {

	}
}
