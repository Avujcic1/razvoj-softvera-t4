package ba.unsa.etf.rs.tut4;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class ControllerTest {

    @Start
    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        stage.setTitle("Tutorijal 4");
        stage.setScene(new Scene(root, 500, 600));
        stage.show();
        stage.toFront();
    }

    @Test
    void unosJednogArtikla(FxRobot robot) {
        TextArea tekst = robot.lookup("#prosljedi").queryAs(TextArea.class);
        assertNotNull(tekst);
        KeyCode kontrola = KeyCode.CONTROL;
        robot.clickOn("#prosljedi");
        robot.press(kontrola).press(KeyCode.A).release(KeyCode.A).release(kontrola);
        robot.write("P3,Pecivo,1");
        Button pritisni = robot.lookup("#dugme").queryAs(Button.class);
        assertNotNull(pritisni);
        robot.clickOn("#dugme");
        TextArea prikazi = robot.lookup("#prikaziTekst").queryAs(TextArea.class);
        assertNotNull(prikazi);
        assertEquals("P3,Pecivo,3.0", prikazi.getText());
    }

    @Test
    void pogresnoUpisano(FxRobot robot) {
        TextArea tekst = robot.lookup("#pokupiTekst").queryAs(TextArea.class);
        assertNotNull(tekst);
        KeyCode ctrl = KeyCode.CONTROL;
        robot.clickOn("#prikazi");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.write("Pecivo");
        Button pritisni = robot.lookup("#dugme").queryAs(Button.class);
        assertNotNull(pritisni);
        robot.clickOn("#dugme");
        Button ok = robot.lookup("#myID").queryAs(Button.class);
        assertNotNull(ok);
        robot.clickOn(ok);
        TextArea prikazi = robot.lookup("#prikazi").queryAs(TextArea.class);
        assertNotNull(prikazi);
        assertEquals("", prikazi.getText());

    }
}
