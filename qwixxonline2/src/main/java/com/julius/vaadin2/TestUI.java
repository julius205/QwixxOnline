package com.julius.vaadin2;

import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinRequest;

public class TestUI extends UI {

	public void init (VaadinRequest vaadinRequest) {
		Button send = new Button("Klick");
		send.addClickListener(click -> Notification.show("Hello"));
		add(send);
	}
	
	
	
	
}
