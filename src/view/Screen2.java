package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.GameController;
import model.Player;
import model.PlayerModel;

public class Screen2 extends Screen {
	private static final long serialVersionUID = 1L;

	// Biến lưu tất cả các component của Screen2.
	private JPanel screen2, jpl;

	// Nút để sang screen tiếp theo.
	private JButton btnNext, btnEnter;

	// Danh sách tên người chơi.
	private List<String> listName;

	// ComboBox lựa chọn số người chơi.
	private JComboBox<String> cbb;
	private JLabel lb;

	private JPanel[] playerPanels;
	private JLabel[] jlb;
	private JTextField[] tf;
	private JPanel center;
	private int n = 2;
	GameController gameController;

	public Screen2(View mainFrame, GameController gameController) {
		super(mainFrame);
		this.gameController = gameController;
		screen2 = new JPanel();
		screen2.setLayout(new BorderLayout());

		lb = new JLabel("Thêm người chơi");
		String[] list = { "2", "3", "4" };

		cbb = new JComboBox<String>(list);

		btnEnter = new JButton("Nhập");
		jpl = new JPanel();

		jpl.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		jpl.add(lb);
		jpl.add(cbb);
		jpl.add(btnEnter);
		cbb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == cbb) {
					n = Integer.valueOf(cbb.getSelectedItem().toString());
				}
			}
		});

		btnNext = new JButton("Next");

		// Xử lý khi nhấn nút nhập.
		btnEnter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnEnter) {
					// Xóa các component cũ trước khi thêm mới.
					if (center != null) {
						screen2.remove(center);
					}

					center = new JPanel();

					// Thiết lập layout cho panel center dựa trên số lượng người chơi.
					if (n == 2) {
						center.setLayout(new GridLayout(1, 2));
					} else if (n == 3 || n >= 4) {
						center.setLayout(new GridLayout(2, 2));
					}

					playerPanels = new JPanel[n];
					jlb = new JLabel[n];
					tf = new JTextField[n];

					for (int i = 0; i < n; i++) {
						jlb[i] = new JLabel("Tên player " + (i + 1) + ":");
						tf[i] = new JTextField(10);
						playerPanels[i] = new JPanel();
						playerPanels[i].add(jlb[i]);
						playerPanels[i].add(tf[i]);
						playerPanels[i].setLayout(new FlowLayout(FlowLayout.LEFT));
						center.add(playerPanels[i]);
					}

					screen2.add(center, BorderLayout.CENTER);
					screen2.revalidate();
					screen2.repaint();
				}
			}
		});

		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean b = true;
				listName = new ArrayList<String>();
				if (e.getSource() == btnNext) {
					if (tf == null) {
						JOptionPane.showMessageDialog(null, "Bạn cần nhấn vào nút nhập và nhập thông tin người chơi",
								"Lỗi nhập thông tin", JOptionPane.PLAIN_MESSAGE);
						return;
					}
					try {
						for (int i = 0; i < tf.length; i++) {
							// Trường hợp không nhập tên.
							if (tf[i].getText().isEmpty()) {
								b = false;
								JOptionPane.showMessageDialog(null, "Nhập thiếu tên Player" + (i + 1),
										"Lỗi nhập thông tin", JOptionPane.PLAIN_MESSAGE);
								break;
							}
							if (listName.contains(tf[i].getText().trim())) {
								b = false;
								JOptionPane.showMessageDialog(null,
										"Tên của Player" + (i + 1) + " đã tồn tại, vui lòng nhập tên khác",
										"Lỗi nhập thông tin", JOptionPane.PLAIN_MESSAGE);
								break;
							}
							listName.add(tf[i].getText().trim());
						}
						if (b) {
							// Tạo và di chuyển qua Screen3.
							// Tạo và di chuyển qua Screen3.
							for (int i = 0; i < n; i++) {
								Player player = new PlayerModel();
								gameController.addPlayer(player);
							}
							gameController.getGameModel().gameStart();
							gameController.notifyGameOb();

						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		screen2.add(btnNext, BorderLayout.SOUTH);
		screen2.add(jpl, BorderLayout.NORTH);
		this.add(screen2);
	}
}
