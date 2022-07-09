package com.julius.vaadin2;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

public class DicesUI extends UI {

	public DicesUI () {
		
		whiteDice.addClassName("white-dice");
		whiteDice2.addClassName("white-dice");
		redDice.addClassName("red-dice");
		blueDice.addClassName("blue-dice");
		greenDice.addClassName("green-dice");
		yellowDice.addClassName("yellow-dice");
		roll.addClassName("dice-button");
    
    	BroadcasterView view = new BroadcasterView();
    	add(view);
		
    	add(headline);
		layout.add(diceArray);
		clickOnRoll(diceArray);
		add(layout);
		
	}
	
	
	//Variables
	H1 headline = new H1("Qwixx Online");
	Button roll = new Button("roll");
	//Button playButton = new Button("Spielen");
	Dice whiteDice = new Dice();
	Dice whiteDice2 = new Dice();
	Dice redDice = new Dice();
	Dice blueDice = new Dice();
	Dice greenDice = new Dice();
	Dice yellowDice = new Dice();
	
	Dice [] diceArray = {whiteDice, whiteDice2, redDice, blueDice, greenDice, yellowDice};
	HorizontalLayout layout = new HorizontalLayout();
	
	
	//Methods
	public void clickOnRoll (Dice [] arr) {
    	HorizontalLayout layout = new HorizontalLayout();
    	Paragraph sumP = new Paragraph("Die Summe der weißen Würfel ist ");
    	Button sumButton = new Button();
    	roll.addClickListener(click -> {
    		fillDices(arr);
    		sumButton.setText(sumWhite(arr));
    		add(sumP, sumButton);
    		
    	});
    	add(roll);
    }
    
    	
    public void fillDices (Dice [] arr) {
    	
//    	for(int i = 0; i < arr.length; i++) {
//    		arr[i].setText(zufallsWerte[i].toString());
//    	}
    	for(int i = 0; i<arr.length; i++) {
    		arr[i].setText(arr[i].rollTheDice());
    	}
    }
    
    public Dice [] getDices (Dice [] arr) {
    	return arr;
    }
    	
    public String sumWhite (Dice [] arr) {
    	Integer value1 = arr[0].getEyeCountInt();
    	Integer value2 = arr[1].getEyeCountInt();
    	Integer sum = value1+value2;
    	return sum.toString();
    }
    	
	public static Integer zufallsZahl() {
        int  max =  6;
          if(max < 1) {

          }
          int speicher = 1+((int)(Math.random()*max));
          return speicher;
    
	}
    	
	
}
