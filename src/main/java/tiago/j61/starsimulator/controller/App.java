package tiago.j61.starsimulator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import tiago.j61.starsimulator.platform.LevelPlatform;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

	private ArrayList<Node> platforms = new ArrayList<Node>();
	private ArrayList<Node> coins = new ArrayList<Node>();

	private Pane appRoot = new Pane();
	private Pane gameRoot = new Pane();
	private Pane uiRoot = new Pane();

	private Node player;
	private Point2D playerVelocity = new Point2D(0, 0);
	private boolean canJump = true;

	private int levelWidth;

	private boolean dialogEvent = false, running = true;

//	@Override
//	public void start(Stage stage) throws IOException {
//		scene = new Scene(loadFXML("/tiago/j61/primary"), 640, 480);
//		stage.setScene(scene);
//		stage.show();
//	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		initContent();

		Scene scene = new Scene(appRoot);
		scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
		primaryStage.setTitle("Tutorial 14 Platformer");
		primaryStage.setScene(scene);
		primaryStage.show();

	
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

	private void initContent() {
		Rectangle bg = new Rectangle(1280, 720);

		levelWidth = LevelPlatform.LEVEL1[0].length() * 60;

		for (int i = 0; i < LevelPlatform.LEVEL1.length; i++) {
			String line = LevelPlatform.LEVEL1[i];
			for (int j = 0; j < line.length(); j++) {
				switch (line.charAt(j)) {
				case '0':
					break;
				case '1':
					Node platform = createEntity(j * 60, i * 60, 60, 60, Color.BROWN);
					platforms.add(platform);
					break;
				case '2':
					Node coin = createEntity(j * 60, i * 60, 60, 60, Color.GOLD);
					coins.add(coin);
					break;
				}
			}
		}


		appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
	}

	private Node createEntity(int x, int y, int w, int h, Color color) {
		Rectangle entity = new Rectangle(w, h);
		entity.setTranslateX(x);
		entity.setTranslateY(y);
		entity.setFill(color);
		entity.getProperties().put("alive", true);

		gameRoot.getChildren().add(entity);
		return entity;
	}

}