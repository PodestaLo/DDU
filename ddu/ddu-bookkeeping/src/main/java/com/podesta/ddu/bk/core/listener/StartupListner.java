package com.podesta.ddu.bk.core.listener;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

import com.podesta.ddu.bk.util.AppUtil;

/**
 * Spring启动监听器。<br/>
 * 用于注入servletContext和applicationContext。
 * <pre>
 * 在webxml配置如下：
 * &lt;listener>
 *       &lt;listener-class>com.podesta.ddu.bk.core.listener.StartupListner&lt;/listener-class>
 *   &lt;/listener>
 *  <pre>
 *
 */
public class StartupListner extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		AppUtil.init(event.getServletContext());
		super.contextInitialized(event);
	}
	
}
