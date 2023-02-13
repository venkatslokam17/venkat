package com.slokam.sc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("managerone")
@Scope("singleton")
public class Manager implements BeanNameAware,
BeanFactoryAware,ApplicationContextAware 
, InitializingBean,DisposableBean {
   
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("8.Initialization afterPropertiesSet");
	}
	
	@PostConstruct
	public void ourinitMethod() {
		System.out.println("7.Initialization ourinitMethod");
	}
	
	private Manager() {
		System.out.println("1. Instatiation");
	}
	
	
	
	private TL tl;
	
	@Autowired
	public void setTl(TL tl) {
		System.out.println("2.Property injection");
		this.tl = tl;
	}

	private String name;
	
	private BeanFactory beanFactory;
	private ApplicationContext ac;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("5.ApplicationContext aware");
		this.ac=arg0;
	}
	
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		System.out.println("4.BeanFactory aware");
	  this.beanFactory = arg0;
	}
	
	@Override
	public void setBeanName(String arg0) {
		System.out.println("3.Bean Name aware.");
		this.name=arg0;
	}
	
	public void dowork() {
		System.out.println("Started Manager work");
		System.out.println("Bean name is::"+ this.name);
		tl.dowork();
		System.out.println("End Manager work");
	}
	
	@PreDestroy
	public void ourdestroy() {
		System.out.println("10.THis is ourdestroy");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("11.THis is destroy");
		
	}
	
}
