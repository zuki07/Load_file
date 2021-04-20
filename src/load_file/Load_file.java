



package load_file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


public class Load_file extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    private boolean sort_count=false;                                                           //sort_count boolean starts false to check if the file has been sorted
    
    
    @Override
    public void start(Stage primaryStage) {
        String search_results_padding="-fx-padding: 8px 15px;";                                 //search results label padding value
        String search_results_error="-fx-background-color: red;";                               //search results label background color value
        
        String[] names_array=new String[65];                                                    //string array to hold names
        
//                        SETUP BUTTONS
        Button load_file_btn = new Button("LOAD FILE");
        Button sort_btn=new Button("SORT");
        Button search_btn=new Button("SEARCH");
        
//                        SETUP TEXTFIELD
        TextField search_text_input=new TextField();
        search_text_input.setMaxWidth(200);
        search_text_input.setAlignment(Pos.CENTER);
            
//                        SETUP LABEL
        Label search_results=new Label ("");

//                                        LOAD FILE BUTTON LAMBDA EVENT        
        load_file_btn.setOnAction(event ->{
            FileChooser file_chooser=new FileChooser(); 
            File selected_file=file_chooser.showOpenDialog(primaryStage);                   //open file chooser to select file
            
            if (selected_file != null){
                int index=0;
                Scanner input_file;
                try {
                    input_file = new Scanner(selected_file);                                //start reading file and putting it in names array
                    while (input_file.hasNext() && index<names_array.length){
                        names_array[index]=input_file.next();
                        index++;
                    }
                    input_file.close();                                                     //close scanner file
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Load_file.class.getName()).log(Level.SEVERE, null, ex);
                }
                search_results.setText("LOAD COMPLETE");
                search_results.setStyle(search_results_padding);
                sort_count=false;                                                           //reset sort_count to false if a different file is selected
            }
        });
                                    
//                                        SORT BUTTON LAMBDA EVENT
        sort_btn.setOnAction(event ->{
            if (names_array[0]==null){                                                      //if array is empty, change search_results label
                search_results.setText("PLEASE LOAD A FILE");
                search_results.setStyle(search_results_error+search_results_padding);
            }
            else if (sort_count==true){                                                     //if array has already been sorted, display it has been sorted
                search_results.setText("FILE HAS ALREADY BEEN SORTED");
                search_results.setStyle(search_results_error+search_results_padding);
            }
            else {
                arrayQuickSort.quickSort(names_array);                                      //pass array to quickSort method in arrayQuickSort class
                sort_count=true;                                                            //change sort_coun to true because it has been sorted
                search_results.setText("SORT COMPLETE");
                search_results.setStyle(search_results_padding);
            }
        });
        
//                                        SEARCH BUTTON LAMBDA EVENT        
        search_btn.setOnAction(event ->{
            
            if (names_array[0]==null){                                                      //if array is empty, change search_results label
                search_results.setText("PLEASE LOAD A FILE");
                search_results.setStyle(search_results_error+search_results_padding);
            }
            else if (sort_count==false){                                                    //if not sorted, change search_results label
                search_results.setText("PLEASE SORT THE FILE");
                search_results.setStyle(search_results_error+search_results_padding);
            }
            else if (search_text_input.getText().equals("")){                               //if text field is empty, change sear_results label
                search_results.setText("PLEASE ENTER A NAME");
                search_results.setStyle(search_results_error+search_results_padding);
            }
            else {                                                                          //start searching
                String search_value=search_text_input.getText().toUpperCase();              //get value from text field and upper case it
                int results=arrayBinarySearcher.search(names_array, search_value);          //run search method in arrayBinarySearcher class and store in results
                
                if (results==-1){                                                           //if text field value does not match, change search_results label
                    search_results.setText(search_text_input.getText()+" was not found in file");
                    search_results.setStyle("-fx-background-color: rgb(0,0,0);"
                                            +"-fx-text-fill: rgb(4,217,18);"
                                            +search_results_padding);
                    search_text_input.clear();                                              //clear text field
                    search_text_input.requestFocus();                                       //keep curson on text field for input without clicking on it
                }
                else{                                                                       //change search_results label that name was found
                    search_results.setText(search_text_input.getText()+" was found in file");
                    search_results.setStyle(search_results_padding);
                    search_text_input.clear();                                              //clear text field
                    search_text_input.requestFocus();                                       //keep curson on text field for input without clicking on it
                }
            }
        });
//                                                SETUP VBOX
        VBox vbox=new VBox(load_file_btn, 
                            sort_btn, 
                            search_text_input, 
                            search_btn, 
                            search_results);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
//                                                SETUP SCENE
        Scene scene = new Scene(vbox, 700, 450);
        scene.getStylesheets().add("styles.css");
        
        primaryStage.setTitle("Search Names");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
