/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EmailsSMS;

import java.util.Comparator;

//This class is a Comparator for comparing Objects by their Properties 

public class CustomComparator implements Comparator<ContactListObject> {
 @Override
  public int compare(ContactListObject t, ContactListObject t1) {
    return t.Label.compareTo(t1.Label);
  }
  
}
