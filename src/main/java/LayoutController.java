//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LayoutController implements Initializable {
    @FXML
    Button addButton;
    @FXML
    Button exitButton;
    @FXML
    TextField eventTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LEvent> eventList;
    ObservableList<LEvent> list = FXCollections.observableArrayList();
    static ArrayList<LEvent> lista = new ArrayList();

    public LayoutController() {
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.datePicker.setValue(LocalDate.now());
        read();

        for(int i = 0; i < lista.size(); ++i) {
            this.list.add((LEvent)lista.get(i));
        }

        this.eventList.setItems(this.list);
    }

    @FXML
    private void addEvent(Event e) throws ClassNotFoundException, IOException {
        if (this.eventTextField.getText() != null) {
            LEvent event = new LEvent((LocalDate)this.datePicker.getValue(), this.eventTextField.getText(), this.list.size());
            this.list.add(event);
            this.eventList.setItems(this.list);
            lista.add(event);
            this.refresh();
        }

    }

    @FXML
    private void exitEvent(ActionEvent e) {
        save(lista);
        Stage stage = (Stage)this.exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void editEvent(MouseEvent e) {
        if (lista.size() != 0) {
            LEvent event = (LEvent)this.eventList.getSelectionModel().getSelectedItem();
            int id = event.getId();
            if (e.getButton() != MouseButton.SECONDARY) {
                if (e.getButton() == MouseButton.PRIMARY && e.getClickCount() == 2) {
                    new Popup(event.getDate(), event.getDesc(), event.getId());
                    Popup.display();
                    LEvent event1 = Popup.get();
                    event = (LEvent)this.list.get(id);
                    event.setDate(event1.getDate());
                    event.setDesc(event1.getDesc());
                    this.eventList.setItems(this.list);
                }
            } else {
                int size = this.list.size();
                this.list.remove(id);
                lista.remove(id);

                for(int i = id; i < size - 1; ++i) {
                    event = (LEvent)this.list.get(i);
                    event.setId(event.getId() - 1);
                }

                this.eventList.setItems(this.list);
                this.refresh();
            }

            this.eventList.refresh();
        }

    }

    private void refresh() {
        this.datePicker.setValue(LocalDate.now());
        this.eventTextField.setText((String)null);
    }

    public static void save(ArrayList list) {
        ObjectOutputStream ous = null;

        try {
            ous = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Data.txt"))));

            try {
                ous.writeObject(list);
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        } catch (FileNotFoundException var15) {
            var15.printStackTrace();
        } catch (IOException var16) {
            var16.printStackTrace();
        } finally {
            if (ous != null) {
                try {
                    ous.close();
                } catch (IOException var13) {
                    var13.printStackTrace();
                }
            }

        }

    }

    public static void read() {
        ObjectInputStream ins = null;
        ArrayList v = null;

        try {
            ins = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("Data.txt"))));

            try {
                v = (ArrayList)ins.readObject();
            } catch (ClassNotFoundException var15) {
                var15.printStackTrace();
            }

            Iterator var3 = v.iterator();

            while(var3.hasNext()) {
                LEvent vehicule = (LEvent)var3.next();
                lista.add(vehicule);
            }
        } catch (FileNotFoundException var16) {
            var16.printStackTrace();
        } catch (IOException var17) {
            var17.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                }
            }

        }

    }
}

