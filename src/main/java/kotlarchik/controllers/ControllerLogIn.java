package kotlarchik.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kotlarchik.dao.DAO;
import kotlarchik.model.Users;
import kotlarchik.service.ServiceUsers;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class ControllerLogIn {
    private SessionFactory factory = new Configuration().configure().buildSessionFactory();
    ObservableList<Users> list = FXCollections.observableArrayList();

    @FXML
    public TextField txtLogin;
    @FXML
    public TextField txtPass;
    @FXML
    public Label txtStatus;
    @FXML
    public Button buttonSign;

    public void initialize(){
        initData();
    }

    public void pressSign(ActionEvent event) throws IOException {
        for (Users users: list){
            if (txtLogin.getText().equals(users.getName()) || txtPass.getText().equals(users.getPass())){
                Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Авторизуйтесь");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
                buttonSign.getScene().getWindow().hide();
            } else {
                txtStatus.setText("Ошибка входа.");
            }
        }
    }

    private void initData(){
        DAO<Users, Integer> usersDAO = new ServiceUsers(factory);
        list.addAll(usersDAO.readAll());
    }
}
