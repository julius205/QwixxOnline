package com.julius.vaadin2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;

public class Field extends Button {

	private Integer numberOnField;
	
	public Field (int number) {
		Button button = new Button();
		setNumberOnField(number);
		
	}
	
	public void setNumberOnField (Integer numberOnField) {
		this.setText(numberOnField.toString());
	}
	
	public String getNumberOnField () {
		return this.numberOnField.toString(); 
	}
	
}
