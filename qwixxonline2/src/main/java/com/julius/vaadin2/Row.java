package com.julius.vaadin2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;

public class Row {

	Field f2 = new Field(2);
	Field f3 = new Field(3);
	Field f4 = new Field(4);
	Field f5 = new Field(5);
	Field f6 = new Field(6);
	Field f7 = new Field(7);
	Field f8 = new Field(8);
	Field f9 = new Field(9);
	Field f10 = new Field(10);
	Field f11 = new Field(11);
	Field f12 = new Field(12);
	
	private String color;
	private final Field [] rowVersion1 = {f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12};
	private final Field [] rowVersion2 = {f12,f11,f10,f9,f8,f7,f6,f5,f4,f3,f2};
	protected Field[] row;
	protected int numberEntries;
	private boolean isLocked = false;
	
	//Constructor
	public Row(String _color) {
		this.color = _color;
		createRow();
		
	}
	
	
	/**
	 * Diese Methode erstellt je nach angegebener Farbe die passende Reihe
	 */
	public void createRow() {
		
		switch(this.color) {
		
		case "Rot": this.row = this.rowVersion1; break;
		
		case "Gelb": this.row = this.rowVersion1; break;
		
		case "Grün": this.row = this.rowVersion2; break;
		
		case "Blau": this.row = this.rowVersion2; break;
		
		default: System.err.println("Keine gültige Farbe angegeben / mögliche Farben: Rot, Gelb, Grün, Blau");
		}
	}
	
	public void fillRowWithNumbers (Field [] arr) {
		for(Integer i = 0; i<arr.length; i++) {
			arr[i].setText(i.toString());
		}
	}
	
	public int getNumberOfEntries() {
		
		return this.numberEntries;
	}
	
	
	
	public void countEntries () {
		if(this.numberEntries == 5) {
			Notification.show("Row " + this.color + " locked.");
			this.isLocked = true;
		}
		else {
			this.isLocked = false;
		}
	}
	
	
}
