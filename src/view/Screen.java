package view;

import javax.swing.JPanel;


public abstract class Screen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected View mainFrame;

	public Screen(View mainFrame) {
		super();
		this.mainFrame = mainFrame;
	}
}
