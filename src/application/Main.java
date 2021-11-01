package application;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;
import javafx.geometry.*;
 
public class Main extends Application{
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	primaryStage.setTitle("Camila's Pizza Shop");
    	
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setVgap(15);
        grid.setHgap(15);
        //set column width
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        grid.getColumnConstraints().add(new ColumnConstraints(300));
        //for debugging
        //grid.setGridLinesVisible(true);
        
        //Title
        Label title = new Label("Create Your Own Pizza!");
        Font titleFont = new Font(55);
        title.setFont(titleFont);
        grid.getChildren().add(title);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setColumnSpan(title, GridPane.REMAINING);
        
        //Contact Information
        Label contactInformation = new Label("Contact Information: ");
        GridPane.setColumnSpan(contactInformation, GridPane.REMAINING);
        GridPane.setConstraints(contactInformation, 0, 1);
        //Text Fields
        TextField firstName = new TextField("Enter first name");
        GridPane.setConstraints(firstName, 0, 2);
        TextField lastName = new TextField("Enter last name");
        GridPane.setConstraints(lastName, 1, 2);
        TextField phoneNumber = new TextField("Enter phone number");
        GridPane.setConstraints(phoneNumber, 2, 2);
        grid.getChildren().addAll(contactInformation, firstName, lastName, phoneNumber);
        
        //Choose size
        Label chooseSize = new Label("Choose a size: ");
        GridPane.setColumnSpan(chooseSize, GridPane.REMAINING);
        GridPane.setConstraints(chooseSize, 0, 3);
        //Radio buttons
        final ToggleGroup sizeRadioGroup = new ToggleGroup();
        RadioButton small = new RadioButton("Small-$8");
        small.setUserData("small");
        GridPane.setConstraints(small, 0, 4);
        GridPane.setHalignment(small, HPos.CENTER);
        small.setToggleGroup(sizeRadioGroup);
        RadioButton medium = new RadioButton("Medium-$12");
        medium.setUserData("medium");
        GridPane.setConstraints(medium, 1, 4);
        GridPane.setHalignment(medium, HPos.CENTER);
        medium.setToggleGroup(sizeRadioGroup);
        RadioButton large = new RadioButton("Large-$16");
        large.setUserData("large");
        GridPane.setConstraints(large, 2, 4);
        GridPane.setHalignment(large, HPos.CENTER);
        large.setToggleGroup(sizeRadioGroup);
        grid.getChildren().addAll(chooseSize, small, medium, large);
        
        //Alert
        Label alert = new Label();
        GridPane.setConstraints(alert, 0, 14);
        GridPane.setHalignment(alert, HPos.CENTER);
        GridPane.setColumnSpan(alert, GridPane.REMAINING);
        grid.getChildren().add(alert);
        
        //Choose sauce
        Label chooseSauce = new Label("Choose a sauce: ");
        GridPane.setColumnSpan(chooseSauce, GridPane.REMAINING);
        GridPane.setConstraints(chooseSauce, 0, 5);
        //Radio Buttons
        final ToggleGroup sauceRadioGroup = new ToggleGroup();
        RadioButton tomatoSauce = new RadioButton("Tomato Sauce");
        GridPane.setConstraints(tomatoSauce, 0, 6);
        GridPane.setHalignment(tomatoSauce, HPos.CENTER);
        tomatoSauce.setToggleGroup(sauceRadioGroup);
        RadioButton BBQ = new RadioButton("BBQ");
        GridPane.setConstraints(BBQ, 1, 6);
        GridPane.setHalignment(BBQ, HPos.CENTER);
        BBQ.setToggleGroup(sauceRadioGroup);
        RadioButton buffaloSauce = new RadioButton("Buffalo Sauce");
        GridPane.setConstraints(buffaloSauce, 2, 6);
        GridPane.setHalignment(buffaloSauce, HPos.CENTER);
        buffaloSauce.setToggleGroup(sauceRadioGroup);
        grid.getChildren().addAll(chooseSauce, tomatoSauce, BBQ, buffaloSauce);
        
        //Choose Toppings
        Label chooseToppings = new Label("Choose toppings: ");
        GridPane.setHalignment(chooseToppings, HPos.CENTER);
        GridPane.setColumnSpan(chooseToppings, GridPane.REMAINING);
        GridPane.setConstraints(chooseToppings, 0, 7);
        grid.getChildren().add(chooseToppings);
        //Check box
        CheckBox pepperoni = new CheckBox("Pepperoni");
        GridPane.setConstraints(pepperoni, 1, 8);
        CheckBox italianSausage = new CheckBox("Italian Sausage");
        GridPane.setConstraints(italianSausage, 1, 9);
        CheckBox ham = new CheckBox("Ham");
        GridPane.setConstraints(ham, 1, 10);
        CheckBox pineapple = new CheckBox("Pineapple");
        GridPane.setConstraints(pineapple, 1, 11);
        CheckBox cheese = new CheckBox("Extra Cheese +$2");
        GridPane.setConstraints(cheese, 1, 12);
        grid.getChildren().addAll(pepperoni, italianSausage, ham, pineapple, cheese);
        
        //Calculate Button
        Button calculate = new Button("Calculate");
        GridPane.setConstraints(calculate, 1, 15);
        GridPane.setHalignment(calculate, HPos.CENTER);
        calculate.setVisible(false);
        grid.getChildren().add(calculate);
        //Labels
        Label subtotalLb = new Label();
        GridPane.setHalignment(subtotalLb, HPos.CENTER);
        GridPane.setConstraints(subtotalLb, 1, 16);
        Label taxLb = new Label();
        GridPane.setHalignment(taxLb, HPos.CENTER);
        GridPane.setConstraints(taxLb, 1, 17);
        Label totalLb = new Label();
        GridPane.setHalignment(totalLb, HPos.CENTER);
        GridPane.setConstraints(totalLb, 1, 18);
        grid.getChildren().addAll(subtotalLb, taxLb, totalLb);
        
        //Size Listener
        sizeRadioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                 if (sizeRadioGroup.getSelectedToggle() != null) {
                	 String selectedRadioButton = sizeRadioGroup.getSelectedToggle().getUserData().toString();
                	 
                	 alert.setText("Here is your updated price for a " + selectedRadioButton + " pizza");
                	 calculate.fire();
                	 
                 }

             } 
        });
        
        //Toppings Listener
        ChangeListener<Boolean> toppingsListener = new ChangeListener<Boolean>() {
        	@Override
        	public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
        		if(new_val || old_val) {
        			calculate.fire();
        		}
        	}
        };
        
        pepperoni.selectedProperty().addListener(toppingsListener);
        italianSausage.selectedProperty().addListener(toppingsListener);
        ham.selectedProperty().addListener(toppingsListener);
        pineapple.selectedProperty().addListener(toppingsListener);
        cheese.selectedProperty().addListener(toppingsListener);
        
        calculate.setOnAction(new EventHandler<ActionEvent>(){
        	@Override public void handle(ActionEvent e) {
        		final double TAX_RATE = 0.0625;
        		double sizePrice = calculateSize(small, medium, large);
        		double toppingsPrice = calculateToppings(pepperoni, italianSausage, ham, pineapple, cheese);
        		
        		double subTotal = sizePrice + toppingsPrice;
        		double tax = subTotal * TAX_RATE;
        		double total = subTotal + tax;
        		
        		subtotalLb.setText("Subtotal: $" + String.format("%.2f", subTotal));
        		taxLb.setText("Tax: $ " + String.format("%.2f", tax));
        		totalLb.setText("Total: $" + String.format("%.2f", total));
        	}
        });
        
        Scene scene = new Scene(grid, 1000, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private double calculateSize(RadioButton small, RadioButton medium, RadioButton large) {
    	double sizePrice = 0;
    	
    	if(small.isSelected()) {
        	sizePrice = 8;
        }
    	else if(medium.isSelected()) {
    		sizePrice = 12;
    	}
    	else if(large.isSelected()) {
    		sizePrice = 16;
    	}
    	
    	return sizePrice;
    }
    
    private double calculateToppings(CheckBox pepperoni, CheckBox italianSausage, CheckBox ham, CheckBox pineapple, CheckBox cheese) {
    	double toppingsPrice = 0;
    	
    	if(pepperoni.isSelected())
    		toppingsPrice += 1.5;
    	if(italianSausage.isSelected())
    		toppingsPrice += 1.5;
    	if(ham.isSelected())
    		toppingsPrice += 1.5;
    	if(pineapple.isSelected())
    		toppingsPrice += 1.5;
    	if(cheese.isSelected())
    		toppingsPrice += 2;
    	
    	return toppingsPrice;
    }
    
}
