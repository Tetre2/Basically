import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
	public static double WORLD_WIDTH = 500;
	public static double WORLD_HEIGHT = 500;

	public static int basicly = 0;
	public static Text t = new Text(basicly+"");; 

	private Group root = new Group();
	private Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);

	@Override
	public void start(Stage primaryStage) throws IOException{
		primaryStage.setTitle("Basicly");


		Scanner sc = new Scanner(new File("Data.txt"));

		String s;
		try {
			s = sc.nextLine();
		} catch (Exception e1) {

			s = "0";

		}

		int i = Integer.parseInt(s);
		if(i == 0){
			basicly = 0;
		}else{
			basicly = i;
		}
		t.setText(basicly+"");
		sc.close();







		t.setTranslateX(WORLD_WIDTH/2 - t.getLayoutBounds().getWidth()/2);
		t.setTranslateY(200);
		Font f = new Font(20);
		t.setFont(f);


		Group g = createTextBtn("Basicly", f);
		g.setOnMouseClicked(event->{

			basicly++;
			t.setText(basicly+"");

			try {
				FileWriter fw = new FileWriter(new File("Data.txt"),false);
				String string = basicly+"";

				fw.write(string);	
				fw.close();


			} catch (IOException e) {}

		});
		g.setTranslateX(WORLD_WIDTH/2 - g.getLayoutBounds().getWidth()/2);
		g.setTranslateY(WORLD_HEIGHT/2 - g.getLayoutBounds().getHeight()/2);





		Group quit = createTextBtn("Quit", f);
		quit.setOnMouseClicked(event->{

			System.exit(0);

		});

		quit.setTranslateX(WORLD_WIDTH/2 - quit.getLayoutBounds().getWidth()/2);
		quit.setTranslateY(WORLD_HEIGHT/2 + WORLD_HEIGHT*2/8 - quit.getLayoutBounds().getHeight()/2);



		Group reset = createTextBtn("Reset", f);
		reset.setOnMouseClicked(event->{

			basicly = 0;
			t.setText(basicly+"");
			FileWriter fw;
			try {
				fw = new FileWriter(new File("Data.txt"),false);

				String string = basicly+"";

				fw.write(string);	
				fw.close();

			} catch (IOException e) {}

		});

		reset.setTranslateX(WORLD_WIDTH/2 - reset.getLayoutBounds().getWidth()/2);
		reset.setTranslateY(WORLD_HEIGHT/2 + WORLD_HEIGHT/8 - reset.getLayoutBounds().getHeight()/2);


		root.getChildren().addAll(g, t, quit, reset);




		primaryStage.setScene(scene);	
		primaryStage.show();	


	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Group createTextBtn(String s, Font f){

		Text t = new Text(s);
		t.setFont(f);

		double textWidth = t.getLayoutBounds().getWidth();
		double textHight = t.getLayoutBounds().getHeight();

		Text x = new Text("x");
		x.setFont(f);
		double wordSpacing = x.getLayoutBounds().getWidth();

		Rectangle r = new Rectangle(textWidth+ wordSpacing*2, textHight+ textHight/2);
		r.setArcHeight(textHight/3);
		r.setArcWidth(textHight/3);
		r.setStrokeWidth(textHight/15);
		r.setStroke(Color.BLACK);
		r.setFill(Color.rgb(204, 204, 255));


		t.setTranslateX(wordSpacing);
		t.setTranslateY(textHight + textHight/8);

		Group g = new Group();
		g.getChildren().addAll(r,t);



		g.setOnMouseEntered(event->{

			r.setFill(Color.rgb(255, 153, 102));

		});

		g.setOnMouseExited(event->{

			r.setFill(Color.rgb(204, 204, 255));

		});


		return g;

	}


}
