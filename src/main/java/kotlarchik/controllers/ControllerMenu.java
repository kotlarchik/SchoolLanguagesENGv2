package kotlarchik.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import kotlarchik.dao.DAO;
import kotlarchik.model.EntityProduct;
import kotlarchik.service.ServiceProduct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

public class ControllerMenu {
    private final SessionFactory factory;
    public ControllerMenu(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    ObservableList<EntityProduct> products = FXCollections.observableArrayList();

    @FXML
    private TextField txtSearch;
    @FXML
    public FlowPane flowPane;
    @FXML
    public ScrollPane scrollPane;

    @FXML
    public void initialize(){
        initData();

        createIconProduct(products);

        productSearch();
    }

    private void productSearch() {
        txtSearch.setOnKeyReleased(event -> {
            ObservableList<EntityProduct> productsBySearch = FXCollections.observableArrayList();

            for (EntityProduct product: products){
                if (product.getTitle().toLowerCase().contains(txtSearch.getText().toLowerCase())){
                    productsBySearch.add(product);
                }
            }
            createIconProduct(productsBySearch);
        });
    }

    private void initData() {
        DAO<EntityProduct, Integer> productDaoImpl = new ServiceProduct(factory);
        products.addAll(productDaoImpl.readAll());
    }

    private void createIconProduct(ObservableList<EntityProduct> products){
        flowPane.getChildren().clear();
        scrollPane.widthProperty().addListener((obj, oldValue, newValue) -> {
            flowPane.setPrefWidth(newValue.intValue());
        });
        flowPane.setVgap(20);
        flowPane.setHgap(20);
        flowPane.setAlignment(Pos.BASELINE_LEFT);
        flowPane.setOrientation(Orientation.HORIZONTAL);
        flowPane.setPadding(new Insets(20, 0, 0, 20));

        for (EntityProduct entityProduct : products){
            // icon base
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setMaxWidth(200);
            anchorPane.prefHeight(390);
            anchorPane.setOnMouseEntered(mouseEvent -> {
                anchorPane.setPrefWidth(220);
                anchorPane.setPrefHeight(410);
            });

            // action to click mouse
            anchorPane.setOnMouseClicked(mouseEvent -> {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/view/showInfo.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Подробная информация");
                    stage.setScene(new Scene(root));
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon/school.png")));
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // image
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(entityProduct.getMainImagePath()));
            imageView.setFitWidth(200);
            imageView.setFitHeight(300);

            // on hover
            imageView.setOnMouseEntered(mouseEvent -> {

            });
            // when the hover is removed
            imageView.setOnMouseExited(mouseEvent -> {

            });

            // labels to information image
            // name
            Label label = new Label();
            label.setText(entityProduct.getTitle());
            label.setLayoutY(285);
            label.setWrapText(true);
            label.setMaxWidth(200);
            label.setPrefHeight(70);
            // cost
            Label label1 = new Label();
            label1.setText(entityProduct.getCost() + " руб.");
            label1.setPrefHeight(320);
            label1.setPadding(new Insets(350, 0, 0, 70));
            // is active
            Label label2 = new Label();
            label2.setAlignment(Pos.BOTTOM_CENTER);
            label2.setPadding(new Insets(370, 0, 0, 0));
            if (entityProduct.getIsActive() == 1){
                label2.setText("Активен.");
                label2.setTextFill(Color.GREEN);
            } else if (entityProduct.getIsActive() == 0){
                label2.setText("Не активен.");
                label2.setTextFill(Color.RED);
                anchorPane.setStyle("-fx-background-color: #858585");
            }

            // node
            anchorPane.getChildren().add(imageView);
            anchorPane.getChildren().add(label);
            anchorPane.getChildren().add(label1);
            anchorPane.getChildren().add(label2);
            flowPane.getChildren().add(anchorPane);
        }
    }
}