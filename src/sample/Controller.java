package sample;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.control.Alert;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea inputField;

    @FXML
    private Button fromFile;

    @FXML
    private Button reset;

    @FXML
    private Button apply;

    @FXML
    private TextField tagField;

    @FXML
    private Button addTag;

    @FXML
    private TextArea outputField;

    HTMLParser htmlParser = new HTMLParser();
    @FXML
    void initialize() {
        addTag.setOnMouseClicked(event -> addTag());
        fromFile.setOnMouseClicked(event -> loadInputHtml());
        apply.setOnMouseClicked(event -> startParse());
        reset.setOnMouseClicked(event -> resetTags());
    }

    void resetTags(){
        htmlParser.resetTagList();
    }

    private void loadInputHtml() {
        inputField.setText(htmlParser.readFromFile());
    }

    private void startParse() {
        if (inputField.getText() == "") return;
        outputField.setText(htmlParser.parse(inputField.getText()));
    }

    void addTag(){
        if(tagField.getText() != "")
        htmlParser.addTag(tagField.getText());
    }
}