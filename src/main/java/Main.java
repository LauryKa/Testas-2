import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Database database = new Database();
    TextField vardas;
    TextField pavarde;
    TextField adresas;
    TextField email;
    TextField informacija;
    Label fullInfo;

    @Override
    public void start(Stage primaryStage) throws Exception {

        //database.createTable("Testas");

        Label vardasLabel = new Label("Iveskite varda");
        vardas = new TextField();
        Label pavardeLabel = new Label("Iveskite pavarde");
        pavarde = new TextField();
        Label adresasLabel = new Label("Iveskite adresa");
        adresas = new TextField();
        Label emailLabel = new Label("Iveskite emaila");
        email = new TextField();
        informacija = new TextField();
        fullInfo = new Label();


        Button prideti = new Button("Prideti");
        prideti.setOnAction(event -> prideti());

        Button gautiInformacija = new Button("Gauti info");
        gautiInformacija.setOnAction(event -> gautiInfo());


        VBox controlLayout = new VBox();
        controlLayout.getChildren().addAll(vardasLabel, vardas, pavardeLabel, pavarde,
                adresasLabel, adresas, emailLabel, email, prideti, new Label("Iveskite vartotojo varda, pavarde, arba emaila"),
                informacija, gautiInformacija, fullInfo);
        controlLayout.setSpacing(10);
        controlLayout.setMaxWidth(400);
        controlLayout.setPadding(new Insets(20, 20, 20, 20));
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(controlLayout);

        Scene scene = new Scene(borderPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void prideti() {
        database.insert(vardas.getText(), pavarde.getText(), adresas.getText(), email.getText());
    }

    public void gautiInfo() {
        String info = informacija.getText();
        fullInfo.setText(database.select(info));
    }
}
