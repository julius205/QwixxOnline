package com.julius.vaadin2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;

public class Gameboard extends Button {
	
	Field[] row1;
	Field[] row2;
	Field[] row3;
	Field[] row4;
	Entry entry;
	
	Row redRow = new Row("Rot");
	Row yellowRow = new Row("Gelb");
	Row greenRow = new Row("Grün");
	Row blueRow = new Row("Blau");
	
	public Gameboard () {
		createGameboard();
	}
	
	public void createGameboard () {
		this.row1 = redRow.row;
		this.row2 = yellowRow.row;
		this.row3 = greenRow.row;
		this.row4 = blueRow.row;
	}
	
}
