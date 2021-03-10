package kotlarchik.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import kotlarchik.dao.DAO;
import kotlarchik.model.EntityProduct;
import kotlarchik.service.ServiceProduct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ControllerShowInfo {
    ObservableList<EntityProduct> products = FXCollections.observableArrayList();

    @FXML
    public Label labelDes;
    @FXML
    public void initialize(){

     }

    private void findAll(){

     }
}