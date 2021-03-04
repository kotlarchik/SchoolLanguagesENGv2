package kotlarchik.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import kotlarchik.dao.DAO;
import kotlarchik.model.EntityProduct;
import kotlarchik.service.ServiceProduct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ControllerMenu {

    private final SessionFactory factory;
    public ControllerMenu(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    @FXML
    public FlowPane flowPane;

    @FXML
    public void initialize(){
        initData();

        flowPane.setHgap(10);
        flowPane.setVgap(15);

        for (int i = 0; i < 10; i++) {
            Image image = new Image("/icon/school.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            flowPane.getChildren().add(imageView);

        }
    }

    private void initData() {
        DAO<EntityProduct, Integer> productDaoImpl = new ServiceProduct(factory);
        productDaoImpl.readAll();
    }
}
