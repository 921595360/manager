package com.silence;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

	public static ThreadLocal<Object> threadLocal =new ThreadLocal<>();
	@Value("${server.port}")
	private Integer port;
	@Value("${server.contextPath}")
	private String contextPath;

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	public void customize(ConfigurableEmbeddedServletContainer container) {

		container.setPort(this.getPort());
		if(this.getContextPath().equals("/")==false&&this.getContextPath().equals(null)==false&& this.getContextPath().equals("")==false)
		container.setContextPath(this.getContextPath());
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}


}
