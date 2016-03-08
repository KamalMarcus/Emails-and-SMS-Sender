/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmailsSMS;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class EmailsAndSMSSender {
  
  public static String content; //the content of the txt file containing the password
  public static ResultSet rs;
  public static Connection connection;
  public static boolean noPassword=true;
  
  

  public static void main(String[] args)  {
    
    
    try {
      UIManager.setLookAndFeel(new NimbusLookAndFeel());
      
      String path = System.getProperty("user.dir") + "\\Password";
      content = new Scanner(new File(path)).useDelimiter("\\Z").next();//content of the file
      if(content.equals(AESenc.AESenc.encrypt(""))){
        connectToTheDB();
        new ContactsList().setVisible(true);
      }
      else{
        new LogIn().setVisible(true);
       noPassword=false;
       connectToTheDB();
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    
    

   
   
     //getting the current  working directory using the method : System.getProperty("user.dir")
    
    //new MainFrame().setVisible(true);
//  new ContactsList().setVisible(true);

    //to know current working directory
//    System.out.println("Working Directory = "
//            + System.getProperty("user.dir"));

    //checking the current typing language
//    InputContext context = InputContext.getInstance();
//    System.out.println(context.getLocale().toString());
    
    
  }

  private static void connectToTheDB() {
    ////////////connect to the Database\\\\\\\\\\\\\\
    try {
      String db_path = System.getProperty("user.dir") +"\\contactDetails.dp";
      System.out.println(db_path);
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:"+ db_path);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
