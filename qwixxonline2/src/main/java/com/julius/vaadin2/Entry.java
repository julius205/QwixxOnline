package com.julius.vaadin2;

public class Entry {

	Row row;
	Field numberField;
	
	//Constructor
	public Entry(Field numberField, Row _reihe, String s) {
		
		this.row = _reihe;
		this.numberField = numberField;
		if(s!="O") {
			increaseNumberOfEntriesInARow();
		}
		setEntry(_reihe, s);
	}
	
	public void increaseNumberOfEntriesInARow() {
		
		row.numberEntries += 1;
	}
	
	public Row setEntry(Row _reihe, String s) {
		
		Row temp = _reihe;
		
		for(int i = 0; i < temp.row.length; i++) {
			
			if(temp.row[i] == this.numberField) {
				
				temp.row[i].setText(s);
			}
		}
		return temp;
	}
	
	
}
