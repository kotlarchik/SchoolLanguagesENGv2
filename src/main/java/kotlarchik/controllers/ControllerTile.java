package kotlarchik.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import kotlarchik.model.EntityProduct;

public class ControllerTile {
    @FXML
    public ImageView imageView;
    @FXML
    public Label labelTitle;
    @FXML
    public Label labelCost;
    @FXML
    public Label labelActive;

    private EntityProduct entityProduct;

    private void click(MouseEvent mouseEvent){

    }

    public void initData(EntityProduct entityProduct){
        imageView.setImage(new Image("/"+entityProduct.getMainImagePath())  );
        labelTitle.setText(entityProduct.getTitle());
        labelTitle.setWrapText(true);
        labelTitle.setMaxHeight(40);
        labelTitle.setMaxWidth(200);
        labelCost.setText(String.valueOf(entityProduct.getCost())+" руб.");
        labelActive.setText(entityProduct.getIsActive() == 0 ? "Не активен" : "Активен");
        if (entityProduct.getIsActive() == 0) {
            labelActive.setTextFill(Color.RED);
        } else {
            labelActive.setTextFill(Color.GREEN);
        }
    }

    // substring title
    private String subTitle(String title){
        if (title.length() < 25){
            return title;
        } else {
            return title.substring(0, 25) + "...";
        }
    }
}
