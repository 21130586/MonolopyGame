package view;

import java.awt.BorderLayout;
import javax.swing.JButton;


public class Screen1 extends Screen {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnStart;
	public Screen1(View mainFrame) {
		super(mainFrame);
		btnStart = new JButton("Play");
		setLayout(new BorderLayout());
		btnStart.addActionListener(e -> mainFrame.navigateTo("Screen2"));
		add(btnStart, BorderLayout.CENTER);
	}

}
