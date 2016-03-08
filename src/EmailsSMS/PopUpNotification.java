/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmailsSMS;

import AESenc.AESenc;
import static EmailsSMS.ContactsList.checkForBirthdaysNames;
import static EmailsSMS.ContactsList.email;
import static EmailsSMS.ContactsList.phone;
import static EmailsSMS.EmailsAndSMSSender.rs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author Gamer
 */
public class PopUpNotification extends JFrame {

  public void show() {
    String message = "There are Users whose Birthdays are today ! <br> Click to send them a Message !";
    String header = "'You've Birthdays Today !'";
    JFrame frame = new JFrame();
    frame.setSize(250, 100);
    frame.setTitle("Birthdays Notification");
    frame.setUndecorated(true);
    frame.setLayout(new GridBagLayout());

    frame.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent me) {
        try {
          PreparedStatement ps = EmailsAndSMSSender.connection.prepareStatement("select Name, Email, Phone, strftime('%m-%d', BirthDate) as bd_month\n"
                  + "from ContactsList where bd_month = strftime('%m-%d', 'now')");
          rs = ps.executeQuery();
          while (rs.next()) {
            checkForBirthdaysNames += AESenc.decrypt(rs.getString("Name")) + "<br>";
            email += "," + AESenc.decrypt(rs.getString("Email"));
            phone += "," + AESenc.decrypt(rs.getString("Phone"));
          }
          checkForBirthdaysNames = "<html>" + checkForBirthdaysNames + "</html>";
          email = email.replaceFirst(",", "");
          phone = phone.replaceFirst(",", "");
          System.out.println(checkForBirthdaysNames);
          MainFrame m = new MainFrame();

          m.setLocation(0, 0);

          m.setTitle("Send a Birthday Message !");

          m.jLabel5.setText("Names : ");
          m.jLabel7.setVisible(false);
          m.jLabel1.setVisible(false);
          m.jLabel6.setVisible(false);
          m.labelEmail.setVisible(false);
          m.jLabel4.setVisible(false);
          m.jSeparator1.setVisible(false);
          m.labelName.setText(checkForBirthdaysNames);
          m.textEmail.setText(email);
          m.labelPhone.setVisible(false);
          m.labelBirthdate.setVisible(false);
          m.pack();
          m.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }

        frame.dispose();
      }

      @Override
      public void mousePressed(MouseEvent me) {

      }

      @Override
      public void mouseReleased(MouseEvent me) {
      }

      @Override
      public void mouseEntered(MouseEvent me) {

      }

      @Override
      public void mouseExited(MouseEvent me) {
      }
    });
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.weightx = 1.0f;
    constraints.weighty = 1.0f;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.fill = GridBagConstraints.BOTH;
    JLabel headingLabel = new JLabel(header);
//headingLabel .setIcon(headingIcon); // --- use image icon you want to be as heading image.
    headingLabel.setOpaque(false);
    headingLabel.setForeground(Color.RED);
    frame.add(headingLabel, constraints);
    constraints.gridx++;
    constraints.weightx = 0f;
    constraints.weighty = 0f;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.NORTH;
    JLabel cloesButton = new JLabel("  × ");
    cloesButton.setFont(new Font("Arial", 14, 20));
    cloesButton.addMouseListener(new MouseListener() {

      @Override
      public void mouseClicked(MouseEvent me) {
        frame.dispose();
      }

      @Override
      public void mousePressed(MouseEvent me) {

      }

      @Override
      public void mouseReleased(MouseEvent me) {

      }

      @Override
      public void mouseEntered(MouseEvent me) {
        cloesButton.setForeground(Color.red);
      }

      @Override
      public void mouseExited(MouseEvent me) {
        cloesButton.setForeground(Color.black);
      }
    });

    cloesButton.setFocusable(false);
    frame.add(cloesButton, constraints);
    constraints.gridx = 0;
    constraints.gridy++;
    constraints.weightx = 1.0f;
    constraints.weighty = 1.0f;
    constraints.insets = new Insets(5, 5, 5, 5);
    constraints.fill = GridBagConstraints.BOTH;
    JLabel messageLabel = new JLabel("<HtMl>" + message);
    frame.add(messageLabel, constraints);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//getting the menu into the bottom right corner of the screen
    Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
    Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());// height of the task bar
    frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight());

    //make it appear on top of all windows
    frame.setAlwaysOnTop(true);
//frame.pack();

    frame.setVisible(true);

    //time for pop-up menu to disappear 
    new Thread() {
      @Override
      public void run() {
        try {
          Thread.sleep(15000); // time after which pop up will be disappeared.
          frame.dispose();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    ;
  }


.start();
  
  }
}
