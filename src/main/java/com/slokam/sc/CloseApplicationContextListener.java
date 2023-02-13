package com.slokam.sc;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class CloseApplicationContextListener
  implements ApplicationListener<ContextClosedEvent>{
	@Override
	public void onApplicationEvent(ContextClosedEvent arg0) {
		  System.out.println("ContextClosedEvent ");
	}
}
