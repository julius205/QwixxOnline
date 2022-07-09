package com.julius.vaadin2;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Push
@Route("testview")
public class viewtest extends VerticalLayout {

	VerticalLayout messages = new VerticalLayout();
	Registration broadcasterRegistration;

//	public viewtest() {
//
//		HorizontalLayout l = new HorizontalLayout();
//		Button send = new Button("Klick");
//		send.addClickListener(click -> {
//			Broadcaster.broadcast("hello");
//		});
//		l.add(send);
//		add(l);
//		add(messages);
//
//	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		UI ui = attachEvent.getUI();
		broadcasterRegistration = Broadcaster.register(newMessage -> {
			ui.access(() -> {
				messages.add(new Span(newMessage));
				Notification.show("Nachricht erreicht");
				

			});
		});

	}
}
