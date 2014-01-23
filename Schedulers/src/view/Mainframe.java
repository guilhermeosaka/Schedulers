package view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Mainframe extends JFrame {
	
	public Mainframe() {
		super("Simulador de escalonadores de processo");
		
		//Configuração do frame
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setMinimumSize(new Dimension(1024, 768));
	    setExtendedState(Frame.MAXIMIZED_BOTH);
		setVisible(true);
	}
}
