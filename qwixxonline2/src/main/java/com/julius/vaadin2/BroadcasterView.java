package com.julius.vaadin2;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Push
@Route("broadcaster")
public class BroadcasterView extends HorizontalLayout {

	public BroadcasterView () {
		TextField message = new TextField();
		Button send = new Button("Send", e -> {
		Broadcaster.broadcast(message.getValue());
		message.setValue("");
		});
		add(message, send);
	}
	
	VerticalLayout messages = new VerticalLayout();
	Registration broadcasterRegistration;
	
	// Creating the UI shown seperately
	
	@Override
	protected void onAttach(AttachEvent attachEvent) {
		UI ui = attachEvent.getUI();
		broadcasterRegistration = Broadcaster.register(newMessage -> {
			ui.access(() -> messages.add(new Span(newMessage)));
		});
	}
	
	@Override
	protected void onDetach(DetachEvent detachEvent) {
		broadcasterRegistration.remove();
		broadcasterRegistration = null;
	}
}
