package com.julius.vaadin2;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean. Use the @PWA
 * annotation make the application installable on phones, tablets and some
 * desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 */
@Push
@Route
@PWA(name = "Vaadin Application", shortName = "Vaadin App", description = "This is an example Vaadin application.", enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

	public static Integer anzahlSitzungen = 0;
	public static Integer[] zufallsWerte = new Integer[6];

	H1 headline = new H1("Qwixx Online");
	Button roll = new Button("roll");
	// Button playButton = new Button("Spielen");
	Dice whiteDice = new Dice();
	Dice whiteDice2 = new Dice();
	Dice redDice = new Dice();
	Dice blueDice = new Dice();
	Dice greenDice = new Dice();
	Dice yellowDice = new Dice();

	Dice[] diceArray = { whiteDice, whiteDice2, redDice, blueDice, greenDice, yellowDice };
	HorizontalLayout layout = new HorizontalLayout();

	Gameboard g = new Gameboard();

	Row redRow = new Row("Rot");
	Row yellowRow = new Row("Gelb");
	Row greenRow = new Row("Grün");
	Row blueRow = new Row("Blau");

	HorizontalLayout row1Layout = new HorizontalLayout();
	HorizontalLayout row2Layout = new HorizontalLayout();
	HorizontalLayout row3Layout = new HorizontalLayout();
	HorizontalLayout row4Layout = new HorizontalLayout();

	Buttonview v = new Buttonview();

	/**
	 * Construct a new Vaadin view.
	 * <p>
	 * Build the initial UI state for the user accessing the application.
	 *
	 * @param service The message service. Automatically injected Spring managed
	 *                bean.
	 */
	public MainView(@Autowired GreetService service) {

		addClassName("all");

		// CSS FOR DICES
		headline.addClassName("headline-qwixx");
		whiteDice.addClassName("white-dice");
		whiteDice2.addClassName("white-dice");
		redDice.addClassName("red-dice");
		blueDice.addClassName("blue-dice");
		greenDice.addClassName("green-dice");
		yellowDice.addClassName("yellow-dice");
		roll.addClassName("dice-button");

		// CSS FOR GAMEBOARD
		for (int i = 0; i < redRow.row.length; i++) {
			redRow.row[i].addClassName("red-row");
			yellowRow.row[i].addClassName("yellow-row");
			greenRow.row[i].addClassName("green-row");
			blueRow.row[i].addClassName("blue-row");
		}

//		viewtest test = new viewtest();

		add(headline);
		row1Layout.add(redRow.row);
		row2Layout.add(yellowRow.row);
		row3Layout.add(greenRow.row);
		row4Layout.add(blueRow.row);
		add(row1Layout, row2Layout, row3Layout, row4Layout);
		layout.add(v);
		v.clickOnRoll();
		clickOnSumButton(v.sumButton);
		clickOnRedChoiceButton(v.red1Button, v.red2Button);
		clickOnYellowChoiceButton(v.yellow1Button, v.yellow2Button);
		clickOnGreenChoiceButton(v.green1Button, v.green2Button);
		clickOnBlueChoiceButton(v.blue1Button, v.blue2Button);

		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		add(layout);

	}

	public void clickOnRoll(Dice[] arr) {
		Dice red = arr[2];
		HorizontalLayout layout = new HorizontalLayout();
		Paragraph sumP = new Paragraph("Die Summe der weißen Würfel ist ");
		Button sumButton = new Button();

		sumButton.addClassName("sum-button");
		roll.addClickListener(click -> {

			fillDices(arr);
			sumButton.setText(sumWhite(arr));
			add(sumP, sumButton);

		});

		add(roll);
		clickOnSumButton(sumButton);
		red.addClickListener(click -> {

		});
		red.setDisableOnClick(true);

	}

	public void fillDices(Dice[] arr) {

		for (int i = 0; i < arr.length; i++) {
			arr[i].setText(arr[i].rollTheDice());
		}
	}

	public Dice[] getDices(Dice[] arr) {
		return arr;
	}

	public String sumWhite(Dice[] arr) {
		Integer value1 = arr[0].getEyeCountInt();
		Integer value2 = arr[1].getEyeCountInt();
		Integer sum = value1 + value2;
		return sum.toString();
	}

	public static Integer zufallsZahl() {
		int max = 6;
		if (max < 1) {

		}
		int speicher = 1 + ((int) (Math.random() * max));
		return speicher;

	}

	public void clickOnSumButton(Button sum) {
		HorizontalLayout choiceLayout = new HorizontalLayout();
		Button yellowButton = new Button("Yellow");
		Button redButton = new Button("Red");
		Button greenButton = new Button("Green");
		Button blueButton = new Button("Blue");
		yellowButton.addClassName("yellow-sum-button");
		redButton.addClassName("red-sum-button");
		greenButton.addClassName("green-sum-button");
		blueButton.addClassName("blue-sum-button");

		sum.addClickListener(click -> {
			choiceLayout.add(redButton, yellowButton, greenButton, blueButton);
			add(choiceLayout);

		});

		redButton.addClickListener(click -> {
			String text = sum.getText();

			for (int i = 0; i < redRow.row.length; i++) {
				if (redRow.row[i].getText().equals(text) && !redRow.row[i].equals("X")) {
					{
						Entry e = new Entry(redRow.row[i], redRow, "X");
						for (int j = 0; j < redRow.row.length; j++) {
							if (j < i && !redRow.row[j].getText().equals("X")) {
								Entry e2 = new Entry(redRow.row[j], redRow, "O");
							}
						}

					}
				}

			}

			redRow.countEntries();
			choiceLayout.remove(greenButton, redButton, yellowButton, blueButton);
			sum.setVisible(false);

		});

		yellowButton.addClickListener(click -> {
			String text = sum.getText();

			for (int i = 0; i < redRow.row.length; i++) {
				if (yellowRow.row[i].getText().equals(text) && !yellowRow.row[i].equals("X")) {
					{
						Entry e = new Entry(yellowRow.row[i], yellowRow, "X");
						for (int j = 0; j < yellowRow.row.length; j++) {
							if (j < i && !yellowRow.row[j].getText().equals("X")) {
								Entry e2 = new Entry(yellowRow.row[j], yellowRow, "O");
							}
						}

					}
				}
			}
			choiceLayout.remove(greenButton, redButton, yellowButton, blueButton);
			sum.setVisible(false);
		});

		greenButton.addClickListener(click -> {
			String text = sum.getText();

			for (int i = 0; i < greenRow.row.length; i++) {
				if (greenRow.row[i].getText().equals(text) && !greenRow.row[i].equals("X")) {
					{
						Entry e = new Entry(greenRow.row[i], greenRow, "X");
						for (int j = 0; j < greenRow.row.length; j++) {
							if (j < i && !greenRow.row[j].getText().equals("X")) {
								Entry e2 = new Entry(greenRow.row[j], greenRow, "O");
							}
						}

					}
				}
			}
			choiceLayout.remove(greenButton, redButton, yellowButton, blueButton);
			sum.setVisible(false);
		});

		blueButton.addClickListener(click -> {
			String text = sum.getText();

			for (int i = 0; i < blueRow.row.length; i++) {
				if (blueRow.row[i].getText().equals(text) && !blueRow.row[i].equals("X")) {
					{
						Entry e = new Entry(blueRow.row[i], blueRow, "X");
						for (int j = 0; j < blueRow.row.length; j++) {
							if (j < i && !blueRow.row[j].getText().equals("X")) {
								Entry e2 = new Entry(blueRow.row[j], blueRow, "O");
							}
						}

					}
				}
			}
			choiceLayout.remove(greenButton, redButton, yellowButton, blueButton);
			sum.setVisible(false);
		});

	}

	public static String sumOfRedWhite1(Dice[] arr) {
		Integer valueWhite1 = arr[0].getEyeCountInt();
		Integer valueRed = arr[2].getEyeCountInt();

		Integer sum1 = valueWhite1 + valueRed;
		return sum1.toString();
	}

	public static String sumOfRedWhite2(Dice[] arr) {
		Integer valueWhite2 = arr[1].getEyeCountInt();
		Integer valueRed = arr[2].getEyeCountInt();

		Integer sum2 = valueWhite2 + valueRed;
		return sum2.toString();
	}

	public static String sumOfYellowWhite1(Dice[] arr) {
		Integer valueWhite1 = arr[0].getEyeCountInt();
		Integer valueYellow = arr[5].getEyeCountInt();

		Integer sum1 = valueWhite1 + valueYellow;
		return sum1.toString();
	}

	public static String sumOfYellowWhite2(Dice[] arr) {
		Integer valueWhite2 = arr[1].getEyeCountInt();
		Integer valueYellow = arr[5].getEyeCountInt();

		Integer sum2 = valueWhite2 + valueYellow;
		return sum2.toString();
	}

	public static String sumOfGreenWhite1(Dice[] arr) {
		Integer valueWhite1 = arr[0].getEyeCountInt();
		Integer valueGreen = arr[4].getEyeCountInt();

		Integer sum1 = valueWhite1 + valueGreen;
		return sum1.toString();
	}

	public static String sumOfGreenWhite2(Dice[] arr) {
		Integer valueWhite2 = arr[1].getEyeCountInt();
		Integer valueGreen = arr[4].getEyeCountInt();

		Integer sum2 = valueWhite2 + valueGreen;
		return sum2.toString();
	}

	public static String sumOfBlueWhite1(Dice[] arr) {
		Integer valueWhite1 = arr[0].getEyeCountInt();
		Integer valueBlue = arr[3].getEyeCountInt();

		Integer sum1 = valueWhite1 + valueBlue;
		return sum1.toString();
	}

	public static String sumOfBlueWhite2(Dice[] arr) {
		Integer valueWhite2 = arr[1].getEyeCountInt();
		Integer valueBlue = arr[3].getEyeCountInt();

		Integer sum2 = valueWhite2 + valueBlue;
		return sum2.toString();
	}

	public void clickOnRedChoiceButton(Button red1Button, Button red2Button) {

		red1Button.addClickListener(click -> {
			String text = red1Button.getText();
			for (int i = 0; i < redRow.row.length; i++) {
				if (redRow.row[i].getText().equals(text) && !redRow.row[i].equals("X")) {
					Entry e = new Entry(redRow.row[i], redRow, "X");
					for (int j = 0; j < redRow.row.length; j++) {
						if (j < i && !redRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(redRow.row[j], redRow, "O");
						}
					}
				}
			}
			v.hl.remove(red1Button, red2Button);

		});

		red2Button.addClickListener(click -> {
			String text = red2Button.getText();
			for (int i = 0; i < redRow.row.length; i++) {
				if (redRow.row[i].getText().equals(text) && !redRow.row[i].equals("X")) {
					Entry e = new Entry(redRow.row[i], redRow, "X");
					for (int j = 0; j < redRow.row.length; j++) {
						if (j < i && !redRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(redRow.row[j], redRow, "O");
						}
					}
				}
			}
			v.hl.remove(red1Button, red2Button);
		});

	}

	public void clickOnYellowChoiceButton(Button yellow1Button, Button yellow2Button) {

		yellow1Button.addClickListener(click -> {
			String text = yellow1Button.getText();
			for (int i = 0; i < yellowRow.row.length; i++) {
				if (yellowRow.row[i].getText().equals(text) && !yellowRow.row[i].equals("X")) {
					Entry e = new Entry(yellowRow.row[i], yellowRow, "X");
					for (int j = 0; j < yellowRow.row.length; j++) {
						if (j < i && !yellowRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(yellowRow.row[j], yellowRow, "O");
						}
					}
				}
			}
			v.hl.remove(yellow1Button, yellow2Button);

		});

		yellow2Button.addClickListener(click -> {
			String text = yellow2Button.getText();
			for (int i = 0; i < yellowRow.row.length; i++) {
				if (yellowRow.row[i].getText().equals(text) && !yellowRow.row[i].equals("X")) {
					Entry e = new Entry(yellowRow.row[i], yellowRow, "X");
					for (int j = 0; j < yellowRow.row.length; j++) {
						if (j < i && !yellowRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(yellowRow.row[j], yellowRow, "O");
						}
					}
				}
			}
			v.hl.remove(yellow1Button, yellow2Button);
		});

	}

	public void clickOnGreenChoiceButton(Button green1Button, Button green2Button) {

		green1Button.addClickListener(click -> {
			String text = green1Button.getText();
			for (int i = 0; i < greenRow.row.length; i++) {
				if (greenRow.row[i].getText().equals(text) && !greenRow.row[i].equals("X")) {
					Entry e = new Entry(greenRow.row[i], greenRow, "X");
					for (int j = 0; j < greenRow.row.length; j++) {
						if (j < i && !greenRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(greenRow.row[j], greenRow, "O");
						}
					}
				}
			}
			v.hl.remove(green1Button, green2Button);

		});

		green2Button.addClickListener(click -> {
			String text = green2Button.getText();
			for (int i = 0; i < greenRow.row.length; i++) {
				if (greenRow.row[i].getText().equals(text) && !greenRow.row[i].equals("X")) {
					Entry e = new Entry(greenRow.row[i], greenRow, "X");
					for (int j = 0; j < greenRow.row.length; j++) {
						if (j < i && !greenRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(greenRow.row[j], greenRow, "O");
						}
					}
				}
			}
			v.hl.remove(green1Button, green2Button);
		});

	}

	public void clickOnBlueChoiceButton(Button blue1Button, Button blue2Button) {

		blue1Button.addClickListener(click -> {
			String text = blue1Button.getText();
			for (int i = 0; i < blueRow.row.length; i++) {
				if (blueRow.row[i].getText().equals(text) && !blueRow.row[i].equals("X")) {
					Entry e = new Entry(blueRow.row[i], blueRow, "X");
					for (int j = 0; j < blueRow.row.length; j++) {
						if (j < i && !blueRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(blueRow.row[j], blueRow, "O");
						}
					}
				}
			}
			v.hl.remove(blue1Button, blue2Button);

		});

		blue2Button.addClickListener(click -> {
			String text = blue2Button.getText();
			for (int i = 0; i < blueRow.row.length; i++) {
				if (blueRow.row[i].getText().equals(text) && !blueRow.row[i].equals("X")) {
					Entry e = new Entry(blueRow.row[i], blueRow, "X");
					for (int j = 0; j < blueRow.row.length; j++) {
						if (j < i && !blueRow.row[j].getText().equals("X")) {
							Entry e2 = new Entry(blueRow.row[j], blueRow, "O");
						}
					}
				}
			}
			v.hl.remove(blue1Button, blue2Button);
		});

	}

}