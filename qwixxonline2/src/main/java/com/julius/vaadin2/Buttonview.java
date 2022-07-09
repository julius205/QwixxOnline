package com.julius.vaadin2;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Push
@Route("buttonview")
public class Buttonview extends VerticalLayout {

	Registration broadcasterRegistration;
	VerticalLayout layout = new VerticalLayout();
	HorizontalLayout l = new HorizontalLayout();
	Dice whiteDice = new Dice();
	Dice whiteDice2 = new Dice();
	Dice redDice = new Dice();
	Dice blueDice = new Dice();
	Dice greenDice = new Dice();
	Dice yellowDice = new Dice();
	Button roll = new Button("ROLL");
	Button sumButton = new Button();
	Button red1Button = new Button();
	Button red2Button = new Button();
	Button yellow1Button = new Button();
	Button yellow2Button = new Button();
	Button green1Button = new Button();
	Button green2Button = new Button();
	Button blue1Button = new Button();
	Button blue2Button = new Button();
	HorizontalLayout hl = new HorizontalLayout();
	Button showSum = new Button("SUM");
	private SoundLoader sound;

	String[] stringArray;

	Dice[] diceArray = { whiteDice, whiteDice2, redDice, blueDice, greenDice, yellowDice };

	public Buttonview() {

		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		whiteDice.addClassName("white-dice");
		whiteDice2.addClassName("white-dice");
		redDice.addClassName("red-dice");
		blueDice.addClassName("blue-dice");
		greenDice.addClassName("green-dice");
		yellowDice.addClassName("yellow-dice");
		roll.addClassName("roll-button");

	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {

		UI ui = attachEvent.getUI();
		broadcasterRegistration = Broadcaster.register(newMessage -> {
			ui.access(() -> {
				String[] arrayInputSplit = newMessage.split(" ");
				for (int i = 0; i < diceArray.length; i++) {
					diceArray[i].setText(arrayInputSplit[i]);
				}
				String s1 = arrayInputSplit[0];
				String s2 = arrayInputSplit[1];
				Integer i1 = Integer.parseInt(s1);
				Integer i2 = Integer.parseInt(s2);
				diceArray[0].setEyeCount(i1);
				diceArray[1].setEyeCount(i2);

			});

		});
	}

	public String sumWhite(Dice[] arr) {
		Integer value1 = arr[0].getEyeCountInt();
		Integer value2 = arr[1].getEyeCountInt();
		Integer sum = value1 + value2;
		return sum.toString();
	}

	public void playsound() {
		sound = new SoundLoader();
		sound.load();
		sound.play(SoundLoader.sound);
	}

	public void clickOnRoll() {
		showSum.addClassName("sum-button");
		sumButton.addClassName("sum-button");
		roll.addClickListener(click -> {
			playsound();
			for (int i = 0; i < diceArray.length; i++) {
				diceArray[i].setText(diceArray[i].rollTheDice());
			}
			Broadcaster.broadcast(whiteDice.getText() + " " + whiteDice2.getText() + " " + redDice.getText() + " "
					+ blueDice.getText() + " " + greenDice.getText() + " " + yellowDice.getText());

//			sumButton.setText(sumWhite(diceArray));
//			add(sumButton);

			if (red1Button.isVisible() == false) {
				red1Button.setVisible(true);
				red2Button.setVisible(true);

			}

			if (red2Button.isVisible() == false) {
				red1Button.setVisible(true);
				red2Button.setVisible(true);

			}

			if (yellow1Button.isVisible() == false) {
				yellow1Button.setVisible(true);
				yellow2Button.setVisible(true);

			}

			if (yellow2Button.isVisible() == false) {
				yellow1Button.setVisible(true);
				yellow2Button.setVisible(true);

			}

			if (green1Button.isVisible() == false) {
				green1Button.setVisible(true);
				green2Button.setVisible(true);

			}

			if (green2Button.isVisible() == false) {
				green1Button.setVisible(true);
				green2Button.setVisible(true);

			}
			if (blue1Button.isVisible() == false) {
				blue1Button.setVisible(true);
				blue2Button.setVisible(true);

			}

			if (blue2Button.isVisible() == false) {
				blue1Button.setVisible(true);
				blue2Button.setVisible(true);

			}

			showSum.setVisible(true);

		});

		clickOnShowSumButton();
		add(roll, l);
		l.add(diceArray);
		clickOnRedDice();
		clickOnYellowDice();
		clickOnGreenDice();
		clickOnBlueDice();

	}

	public void clickOnShowSumButton() {

		add(showSum);
		showSum.addClickListener(click -> {
			sumButton.setText(sumWhite(diceArray));
			add(sumButton);

			if (sumButton.isVisible() == false) {
				sumButton.setVisible(true);
			}
			showSum.setVisible(false);
		});
	}

	public void broadcastSumMethod() {
		Broadcaster.broadcast(sumButton.getText());
	}

	public void clickOnRedDice() {

		red2Button.addClassName("red-choice");
		red1Button.addClassName("red-choice");
		redDice.addClickListener(click -> {

			red1Button.setText(MainView.sumOfRedWhite1(diceArray));
			red2Button.setText(MainView.sumOfRedWhite2(diceArray));
			hl.add(red1Button, red2Button);
			add(hl);
			remove(red1Button, red2Button);

		});
		red1Button.addClickListener(click -> {
			red1Button.setVisible(false);
			red2Button.setVisible(false);

		});

		red2Button.addClickListener(click -> {
			red1Button.setVisible(false);
			red2Button.setVisible(false);
		});
	}

	public void clickOnYellowDice() {

		yellow2Button.addClassName("yellow-choice");
		yellow1Button.addClassName("yellow-choice");
		yellowDice.addClickListener(click -> {

			yellow1Button.setText(MainView.sumOfYellowWhite1(diceArray));
			yellow2Button.setText(MainView.sumOfYellowWhite2(diceArray));
			hl.add(yellow1Button, yellow2Button);
			add(hl);
			remove(yellow1Button, yellow2Button);

		});
		yellow1Button.addClickListener(click -> {
			yellow1Button.setVisible(false);
			yellow2Button.setVisible(false);
		});

		yellow2Button.addClickListener(click -> {
			yellow1Button.setVisible(false);
			yellow2Button.setVisible(false);
		});
	}

	public void clickOnGreenDice() {

		green2Button.addClassName("green-choice");
		green1Button.addClassName("green-choice");
		greenDice.addClickListener(click -> {

			green1Button.setText(MainView.sumOfGreenWhite1(diceArray));
			green2Button.setText(MainView.sumOfGreenWhite2(diceArray));
			hl.add(green1Button, green2Button);
			add(hl);
			remove(green1Button, green2Button);

		});
		green1Button.addClickListener(click -> {
			green1Button.setVisible(false);
			green2Button.setVisible(false);
		});

		green2Button.addClickListener(click -> {
			green1Button.setVisible(false);
			green2Button.setVisible(false);
		});
	}

	public void clickOnBlueDice() {

		blue2Button.addClassName("blue-choice");
		blue1Button.addClassName("blue-choice");
		blueDice.addClickListener(click -> {

			blue1Button.setText(MainView.sumOfBlueWhite1(diceArray));
			blue2Button.setText(MainView.sumOfBlueWhite2(diceArray));
			hl.add(blue1Button, blue2Button);
			add(hl);
			remove(blue1Button, blue2Button);

		});
		blue1Button.addClickListener(click -> {
			blue1Button.setVisible(false);
			blue2Button.setVisible(false);
		});

		blue2Button.addClickListener(click -> {
			blue1Button.setVisible(false);
			blue2Button.setVisible(false);
		});
	}

}