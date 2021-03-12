package kotlarchik.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import kotlarchik.dao.DAO;
import kotlarchik.model.EntityProduct;
import kotlarchik.service.ServiceProduct;
import lombok.SneakyThrows;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMainMenu implements Initializable {
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private ObservableList<EntityProduct> products = FXCollections.observableArrayList();

    @FXML
    public TextField txtSearch;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TilePane tilePane;

    VBox anchorPane;

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        search();
        initTile();
        rubberWindow();
    }

    private void initTile() throws IOException {
        tilePane.getChildren().clear();
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        for (EntityProduct entityProduct: products){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Tile.fxml"));
            anchorPane = loader.load();
            ControllerTile controllerTile = loader.getController();
            tilePane.getChildren().add(anchorPane);
            controllerTile.initData(entityProduct);

            if (entityProduct.getIsActive() == 0){
                anchorPane.setStyle("-fx-background-color: #848484; -fx-background-radius: 10; -fx-border-color: #f8adff; -fx-border-radius: 15;");

            }
        }
    }

    // method to search object
    private void search(){
        txtSearch.setOnKeyReleased(event -> {
            ObservableList<EntityProduct> list = FXCollections.observableArrayList();
            for (EntityProduct entityProduct: list){
                if (entityProduct.getTitle().toLowerCase().contains(txtSearch.getText().toLowerCase())){
                    list.add(entityProduct);
                }
            }
        });
    }

    private void rubberWindow(){
        scrollPane.widthProperty().addListener((obj, oldValue, newValue) -> {
            tilePane.setPrefWidth(newValue.doubleValue());
        });
    }
    private void initData(){
        DAO<EntityProduct, Integer> productDAO = new ServiceProduct(factory);
        products.addAll(productDAO.readAll());
    }
}
