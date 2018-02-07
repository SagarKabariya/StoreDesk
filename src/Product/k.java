package Product;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;


public class k {
    
    public static void main(String[] args) {
    	 String[] buttons = { "Yes", "Yes to all", "No", "Cancel" };

    	    int rc = JOptionPane.showOptionDialog(null, "Question ?", "Confirmation",
    	        JOptionPane.WARNING_MESSAGE, 0, null, buttons,null);

    }
}