package sample;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;
import static sample.FXColourUtils.toRGBCode;

public class Controller implements Initializable {

    @FXML
    private TextArea readOnlyTextArea;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private Slider slider;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Button fadeOutButton;

    @FXML
    private Button fadeInButton;

    @FXML
    private ComboBox fontChoice;


    @FXML
    private CheckBox largeText;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ToggleGroup themeSelectColour;

    @FXML
    private RadioButton themeColour;


    //Internal vars
    String fontStyle = "Arial";
    String fontColour = "Black";
    String fontSize = "11";

    @FXML
    public void largeFontSelect(Event e){
        if (largeText.isSelected()){
            fontSize = "22";
        } else {
            fontSize = "12";
        }
        readOnlyTextArea.setStyle("-fx-text-inner-color: " + fontColour +"; -fx-font-family: " +fontStyle + "; -fx-font-size:" + fontSize + "px; ");
    }

    @FXML
    public void themeSelect(Event e){
        themeColour = (RadioButton)themeSelectColour.getSelectedToggle();

        System.out.println(themeColour.getText());
        if (themeColour.getText().equals("Red")){
            colorPicker.getStyleClass().add("colorPicker-Red");
            colorPicker.getStyleClass().add("colorPicker-Red list-cell");
            fontChoice.getStyleClass().add("comboBox-Red");
            fontChoice.getStyleClass().add("comboBox-Red picker-color list-cell");
            borderPane.getStyleClass().add("borderPane-Red");
            readOnlyTextArea.getStyleClass().add("readOnlyTextArea-Red");
            inputTextArea.getStyleClass().add("inputTextArea-Red");
            slider.getStyleClass().add("slider-Red");
            fadeOutButton.getStyleClass().add("button-Red");
            fadeInButton.getStyleClass().add("button-Red");
            largeText.getStyleClass().add("checkBox-Red");


            themeColour.getStyleClass().add("radio radio-Red");
            //themeColour.getStyleClass().add("radio-Red radio-button radio");
            System.out.println("Theme: " +themeColour.getStyleClass());



        } else if (themeColour.getText().equals("Grey")){
            /* Need to remove all red CSS */
            colorPicker.getStyleClass().remove("colorPicker-Red");
            colorPicker.getStyleClass().remove("colorPicker-Red list-cell");
            fontChoice.getStyleClass().remove("comboBox-Red");
            fontChoice.getStyleClass().remove("comboBox-Red picker-color list-cell");
            borderPane.getStyleClass().remove("borderPane-Red");
            readOnlyTextArea.getStyleClass().remove("readOnlyTextArea-Red");
            inputTextArea.getStyleClass().remove("inputTextArea-Red");
            slider.getStyleClass().remove("slider-Red");
            fadeOutButton.getStyleClass().remove("button-Red");
            fadeInButton.getStyleClass().remove("button-Red");
            largeText.getStyleClass().remove("checkBox-Red");

            //themeColour.getStyleClass().remove("radio-Red radio");
        }
}




    @FXML
    public void mirrorTextArea(Event e){
        readOnlyTextArea.setText(inputTextArea.getText());
    }


    @FXML
    public void changeFont(Event e){
        System.out.println("Info 1: " +fontChoice.getEditor().getControlCssMetaData());
        if (fontChoice.getValue().toString().equals("Option 1")){
           fontStyle = "\"Arial\"";
        }
        else if (fontChoice.getValue().toString().equals("Option 2")){
           fontStyle = "\"Courier New\"";
        }
        else if (fontChoice.getValue().toString().equals("Option 3")){
           fontStyle = "\"Comic Sans MS\"";
        }
        readOnlyTextArea.setStyle("-fx-text-inner-color: " + fontColour +"; -fx-font-family: " +fontStyle + "; -fx-font-size:" + fontSize + "px; ");
        System.out.println("Info 2: " +fontChoice.getEditor().getControlCssMetaData());
    }

    @FXML
    public void Test(Event e){
        System.out.println("Working :-)");
    }


    @FXML
    public void colourTextArea(Event e){
        //Using variable to enable
        fontColour = toRGBCode(colorPicker.getValue());
        readOnlyTextArea.setStyle("-fx-text-inner-color: " + fontColour +"; -fx-font-family: " +fontStyle + "; -fx-font-size:" + fontSize + "px; ");
    }


    @FXML
    public void fadeOutTransition(){
        fadeInButton.setDisable(true);
        readOnlyTextArea.setStyle("-fx-text-inner-color: " + fontColour +"; -fx-font-family: " +fontStyle + "; -fx-font-size:" + fontSize + "px; ");
        FadeTransition fo = new FadeTransition(Duration.seconds(slider.getValue()), readOnlyTextArea);
        fo.setFromValue(1.0);
        fo.setToValue(0.0);
        //Prevent fade in being used while fading out
        fo.setOnFinished(e -> {fadeInButton.setDisable(false);});
        // Play the Animation
        fo.play();
    }

    @FXML
    public void fadeInTransition(){
        fadeOutButton.setDisable(true);
        readOnlyTextArea.setStyle("-fx-text-inner-color: " + fontColour +"; -fx-font-family: " +fontStyle + "; -fx-font-size:" + fontSize + "px; ");
        FadeTransition fi = new FadeTransition(Duration.seconds(slider.getValue()), readOnlyTextArea);
        fi.setFromValue(0);
        fi.setToValue(1.0);
        //Prevent fade in being used while fading out
        fi.setOnFinished(e -> {fadeOutButton.setDisable(false);});
        // Play the Animation
        fi.play();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting one TextArea to read-only
        //https://stackoverflow.com/questions/20205145/javafx-how-to-show-read-only-text/41855251
        readOnlyTextArea.setEditable(false);
        //titleText.setEditable(false);
        //vBoxTextInputTitle.getStyleClass().add("vBox-default");
        inputTextArea.setWrapText(true);
        readOnlyTextArea.setWrapText(true);
        readOnlyTextArea.getStyleClass().add("readOnlyTextArea-default");
        //titleText.getStyleClass().add("title-default");
        //changing default from White to Black
        colorPicker.setValue(Color.BLACK);

        //inputTextArea.selectAll();
        //inputTextArea.setText("Working?");
        //root.setFocus(true);
    }
}
