package starawake;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StarAwake extends Application {
    // Criando o objecto Pane

    Pane root = new Pane();
    // Criando o objecto scene
    Scene scene = new Scene(root, 600, 300);
    Canvas canvas = new Canvas(600, 300);
    GraphicsContext ctx = canvas.getGraphicsContext2D();
    Ellipse elipse = new Ellipse(30, 30);
    Circle circle = new Circle(20);
    Rectangle quadr = new Rectangle(40, 40);
    Rectangle rect = new Rectangle(100, 40);
    double x = 0, y = 0;
    private ArrayList< Shape> nosObjct;
    // /////////////////////////////////////////////////////////////////////////

    public void Informe() {
        ctx.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        ctx.setFill(Color.RED);
        ctx.fillText("Por: ", 200, 230);
        ctx.setFill(Color.BLUE);
        ctx.fillText("Samuel Lima", 240, 230);
        ctx.setFill(Color.BLACK);
        ctx.fillText("sa_sp10@hotmail.com", 200, 245);
        ctx.setFill(Color.RED);
        ctx.fillText(" MUITO OBRIGADO", 250, 280);
    }
    // /////////////////////////////////////////////////////////////////////////

    public void arrastaItens(final Shape figuras) {
        figuras.setOnMousePressed(new EventHandler< MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = figuras.getLayoutX() - mouseEvent.getSceneX();
                y = figuras.getLayoutY() - mouseEvent.getSceneY();
                figuras.setCursor(Cursor.CROSSHAIR);
            }
        });
        figuras.setOnMouseReleased(new EventHandler< MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                figuras.setCursor(Cursor.HAND);
            }
        });
        figuras.setOnMouseDragged(new EventHandler< MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getSceneX() > 50
                        && mouseEvent.getSceneX() < 550) {
                    figuras.setLayoutX(mouseEvent.getSceneX() + x);
                }
                if (mouseEvent.getSceneY() > 50
                        && mouseEvent.getSceneY() < 250) {
                    figuras.setLayoutY(mouseEvent.getSceneY() + y);
                }
                if (mouseEvent.getSceneX() == 50
                        || mouseEvent.getSceneX() == 550) {
                }
                if (mouseEvent.getSceneY() == 50
                        || mouseEvent.getSceneY() == 250) {
                }
            }
        });
    }
    // /////////////////////////////////////////////////////////////////////////

    public void cria_figuras() {
        nosObjct = new ArrayList<>();
        nosObjct.add(elipse);
        elipse.setCenterX(100.0f);
        elipse.setCenterY(150.0f);
        elipse.setRadiusX(40.0f);
        elipse.setRadiusY(20.0f);
        elipse.setFill(Color.BLUE);
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
    // /////////////////////////////////////////////////////////////////////////
    final long startNanoTime = System.nanoTime();

    public void start(Stage stage) {
        // Configurando um título para o stage
        stage.setTitle("JAVAFX - ARRASTANDO OBJETOS\n"
                + " E LIMITANDO O ALCANCE DO MOUSE");
        // Criando uma moldura retangular em canvas
        ctx.setStroke(Color.rgb(255, 0, 255));
        ctx.setLineWidth(10.0);
        ctx.strokeRoundRect(5, 5, 590, 290, 5, 5);
        ctx.setFont(Font.font("Helvetica", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        ctx.setFill(Color.RED);
        ctx.fillText("JAVAFX - ARRASTANDO OBJETOS E\n"
                + "LIMITANDO O ALCANCE DO MOUSE", 150, 40);
        Informe();
        cria_figuras();
        root.getChildren().add(canvas);
        root.getChildren().addAll(nosObjct);
        stage.setScene(scene);
        stage.show();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
                System.out.println(t);
            
                    quadr.setLayoutX(t);
                
                
            }
        }.start();
    }
    // /////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        launch(args);
    }
}
