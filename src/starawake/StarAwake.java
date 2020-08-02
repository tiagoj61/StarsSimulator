package starawake;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class StarAwake extends Application {

    boolean isgrab = false;
    Pane root = new Pane();
    Scene scene = new Scene(root, 1024, 700);
    Canvas canvas = new Canvas(1024, 700);
    GraphicsContext ctx = canvas.getGraphicsContext2D();
    Circle circle = new Circle(20);
    Rectangle quadr = new Rectangle(40, 40);
    Rectangle rect = new Rectangle(100, 40);
    double x = 0, y = 0;
    private ArrayList< Shape> nosObjct;

    public void arrastaItens(final Shape figuras) {
        figuras.setOnMousePressed(new EventHandler< MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                isgrab = true;
                x = figuras.getLayoutX() - mouseEvent.getSceneX();
                y = figuras.getLayoutY() - mouseEvent.getSceneY();
                figuras.setCursor(Cursor.CLOSED_HAND);
            }
        });
        figuras.setOnMouseReleased(new EventHandler< MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
             
                figuras.setCursor(Cursor.HAND);
                isgrab = false;
            }
        });
        figuras.setOnMouseDragged(new EventHandler< MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getSceneX() > 50
                        && mouseEvent.getSceneX() < 1024) {
                    figuras.setLayoutX(mouseEvent.getSceneX() + x);
                }
                if (mouseEvent.getSceneY() > 50
                        && mouseEvent.getSceneY() < 700) {
                    figuras.setLayoutY(mouseEvent.getSceneY() + y);
                }
                if (mouseEvent.getSceneX() == 50
                        || mouseEvent.getSceneX() == 1024) {
                }
                if (mouseEvent.getSceneY() == 50
                        || mouseEvent.getSceneY() == 700) {
                }
            }
        });

    }

    public void cria_figuras() {
        nosObjct = new ArrayList<>();
        nosObjct.add(circle);
        circle.setCenterX(230.0f);
        circle.setCenterY(150.0f);
        circle.setFill(Color.RED);
        nosObjct.add(quadr);
        quadr.setLayoutX(310.0f);
        quadr.setLayoutY(130.0f);
        quadr.setFill(Color.YELLOW);
        nosObjct.add(rect);
        rect.setLayoutX(430.0f);
        rect.setLayoutY(130.0f);
        rect.setFill(Color.GREEN);
        for (Shape moveFiguras : nosObjct) {
            arrastaItens(moveFiguras);
        }
    }

    final long startNanoTime = System.nanoTime();

    public void alterarpossi(Rectangle rect, double t) {
        if (!isgrab) {
            rect.setTranslateX(rect.getTranslateX() + t/10);// usar a posição da figura
            rect.setTranslateY(rect.getTranslateY() + t/10);// usar a posição da figura
        }
    }

    public void start(Stage stage) throws FileNotFoundException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        stage.setTitle("Frist stage");
        // Criando uma moldura retangular em canvas
        Image earth = new Image(new FileInputStream("D:\\Pc\\Documents\\NetBeansProjects\\StarAwake\\src\\starawake\\download.png"));
        cria_figuras();
        root.getChildren().add(canvas);
        root.getChildren().addAll(nosObjct);
        stage.setScene(scene);
        stage.show();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                
                System.out.println(quadr.getBoundsInParent().intersects(rect.getBoundsInParent()));

                quadr.setRotate(t);
                alterarpossi(rect, t);
             
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
