package com.turan.kumbaravegelirgider.utils;

import javax.swing.JTextField;

public class Utils {

	public static void alanTemizle(JTextField[] textler) {
		for (int i = 0; i < textler.length; i++) {
			textler[i].setText("");
		}
	}

	
}
