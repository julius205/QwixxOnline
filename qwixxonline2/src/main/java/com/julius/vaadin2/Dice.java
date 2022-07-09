package com.julius.vaadin2;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;

public class Dice extends Button {

	private Integer eyeCount;
	
	public Dice () {
		Button button = new Button();
	}
	
	public String rollTheDice () {
		this.eyeCount = randomNumber();
		return this.eyeCount.toString();
	}
	
	public String getEyeCountString () {
		return this.eyeCount.toString();
	}
	
	public Integer getEyeCountInt () {
		return this.eyeCount;
	}
	
	public void setEyeCount(Integer i) {
		this.eyeCount = i;
	}
	
	
	public static Integer randomNumber() {
        int  max =  6;
          if(max < 1) {
        	  
          }
          Integer storage = 1+((int)(Math.random()*max));
          return storage;
      }
}
