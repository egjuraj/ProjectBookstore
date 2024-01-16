package src.view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import src.model.User;

public class AdministratorView {

User currentUser;
	
	public AdministratorView(User currentUser) {
		this.currentUser = currentUser;
	}
		
		public Scene showView(Stage st) {
			
			BorderPane Pane = new BorderPane();
			
			Pane.setStyle("-fx-background-color: THISTLE");

			Rectangle r= new Rectangle();
			r.setX(50);
			r.setY(50);
			r.setWidth(320);
			r.setHeight(360);
			r.setFill(Color.LAVENDER);
			r.setStroke(Color.BLACK);
		
			
			GridPane gp= new GridPane();
			gp.setVgap(10);
			gp.setHgap(10);
			gp.setAlignment(Pos.CENTER);
			
			
			
			Label l1= new Label("ADMINISTRATOR PAGE");
			l1.setFont(Font.font("ALGERIAN", FontWeight.BOLD, FontPosture.ITALIC, 25));
			l1.setTextFill(Color.BLACK);
			gp.add(l1, 1, 1);
			
			
			Button b1= new Button("      Quantity      ");
			b1.setAlignment(Pos.CENTER);
			b1.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			b1.setTextFill(Color.GOLDENROD);
			
			 b1.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						(new QuantityS(currentUser)).showView(st);
						
					}
			});
			 
			Button b2= new Button("         Stock        ");
			b2.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			b2.setTextFill(Color.DEEPSKYBLUE);
			b2.setId("b2Stock");
			b2.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
				(new viewStockS(currentUser)).showView(st);
					
				}
				
			});
			
			
			Button b3= new Button("      Suppliers     ");
			b3.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			b3.setTextFill(Color.SALMON);
			b3.setId("bt3Supplier");
			Button b4= new Button("      Category     ");
			b4.setFont(Font.font("Arial", FontWeight.BOLD, 15));
			b4.setTextFill(Color.SIENNA);
			b4.setId("b4Category");
			gp.add(b2, 1, 3);
			gp.add(b3, 1, 4);
			gp.add(b1, 1, 5);
			gp.add(b4, 1, 6);
			
		
			b3.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
				(new ViewSuppliersS(currentUser)).showView(st);
					
				}
				
			});
			
			
			b4.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
				(new AddCategoryS(currentUser)).showView(st);
					
				}
				
			});
			
			
			StackPane sp = new StackPane();
			sp.getChildren().addAll(r , gp);
			
			MenuBar mBar = new MenuBar();
			mBar.setStyle("-fx-background-color: linear-gradient(#fbd6ec,#f79fa9);");
	
			Menu Employees = new Menu("Employees");
			
			MenuItem Add= new MenuItem("Add");
			Add.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					RegisterView sv = new RegisterView(currentUser);
					Scene sc = sv.showView(st);
					st.setScene(sc);
		
				}
				
			});
			
			
			Menu View= new Menu("View");
	
			MenuItem All= new MenuItem("View All Employees");
			
			All.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					AllEmployeeView aev = new AllEmployeeView(currentUser);
					Scene sc = aev.showView(st);
					st.setScene(sc);
				}
				
			});
			
			
			View.getItems().add(All)	;
			
			Employees.getItems().addAll(View, Add );
			
			
			mBar.getMenus().add(Employees);
			
			HBox h= new HBox();
			
			Button logout= new Button("Log Out");
			logout.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			logout.setTextFill(Color.SALMON);
			Button cancel= new Button("Cancel");
			cancel.setStyle("-fx-background-color: linear-gradient(#fbd6ec,#f79fa9)");
			cancel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
			cancel.setTextFill(Color.WHITE);
			logout.setId("logout");
			
			h.getChildren().addAll(logout,cancel);
			h.setAlignment(Pos.BOTTOM_CENTER);
			h.setPadding(new Insets(20,20,20,20));
			h.setMargin(logout, new Insets(0,20,0,0));
		
			Pane.setTop(mBar);
			Pane.setCenter(sp);
			Pane.setBottom(h);
			
	
            logout.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					st.setScene(new LoginView().showView(st));
					
				}
           
            });
            
            cancel.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					System.exit(0);
					
				}
            	
            });
			
			
			
			Scene scene = new Scene(Pane,800,539);
			st.setScene(scene);
			st.setTitle("Admin Of BookStore");
			st.show();
			return scene;
			
	
			
				}
		

}
