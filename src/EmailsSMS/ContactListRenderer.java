/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmailsSMS;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Gamer
 */
public class ContactListRenderer extends JLabel implements ListCellRenderer<Object> {

  public ContactListRenderer() {
    setOpaque(true);
  }

  @Override
  public Component getListCellRendererComponent(JList<? extends Object> list, Object e, int index, boolean isSelected, boolean cellHasFocus) {
    ContactListObject object = (ContactListObject) e;

    //Arranging items : arabic right alignment & English left alignment
    StringBuilder sb = new StringBuilder(); //this object used for calling string by ASCII code
    // A:Z 65:90 a:z 97:122
    // RTL <=> LTR
    for (int i = 65; i <= 122; i++) {

      if (object.Label.contains(sb.appendCodePoint(i).delete(0, sb.length() - 1))) {
        setHorizontalAlignment(LEFT);
        break;
      } else {
        setHorizontalAlignment(RIGHT);

      }
    }

    setText(object.Label);
    setBorder(new EmptyBorder(2, 5, 2, 5));
    Color background;
    Color foreground;

    setFont(new Font("Tahoma", Font.PLAIN, 16));
    // check if this cell represents the current DnD drop location
    JList.DropLocation dropLocation = list.getDropLocation();
    if (dropLocation != null
            && !dropLocation.isInsert()
            && dropLocation.getIndex() == index) {

      background = Color.BLUE;
      foreground = Color.WHITE;

      // check if this cell is selected
    } else if (isSelected) {
      background = Color.BLUE;
      foreground = Color.WHITE;

      // unselected, and not the DnD drop location
    } else {
      background = Color.WHITE;
      foreground = Color.BLACK;
    }

    setBackground(background);
    setForeground(foreground);

    return this;
  }
}
