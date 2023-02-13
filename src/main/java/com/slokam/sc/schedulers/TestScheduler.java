package com.slokam.sc.schedulers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class TestScheduler {
    //https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions
	//@Scheduled(cron = "*/10 * * * * *")
	public void test() {
		System.out.println("Helloo This is from Scheduler");
	}
	
}
