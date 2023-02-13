package com.slokam.sc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartedApplicationContextListener 
implements ApplicationListener<ContextStartedEvent>{
	
	@Override
	public void onApplicationEvent(ContextStartedEvent arg0) {
		System.out.println("ContextStartedEvent");
	}

}
