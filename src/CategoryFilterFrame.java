import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CategoryFilterFrame extends JFrame {

	private JPanel contentPane;
	private final JLabel lblSelectWhichCategories = new JLabel("Select which categories you woud like to be displayed:");
	private final JCheckBox chckbxHumor = new JCheckBox("Humor");
	private final JCheckBox chckbxBiography = new JCheckBox("Biography");
	private final JCheckBox chckbxAutobiography = new JCheckBox("Autobiography");
	private final JCheckBox chckbxLiterature = new JCheckBox("Literature");
	private final JCheckBox chckbxMystery = new JCheckBox("Mystery");
	private final JCheckBox chckbxGraphicnovel = new JCheckBox("GraphicNovel");
	private final JCheckBox chckbxYoungadult = new JCheckBox("YoungAdult");
	private final JCheckBox chckbxRomance = new JCheckBox("Romance");
	private final JCheckBox chckbxScifi = new JCheckBox("SciFi");
	private final JCheckBox chckbxOther = new JCheckBox("Other");
	private final JButton btnApply = new JButton("Apply");
	private final JButton btnCancel = new JButton("Cancel");
	private final ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	private KlacikBookFaceFrame mainFrame;
	/**
	 * Create the frame.
	 */
	public CategoryFilterFrame(KlacikBookFaceFrame frame) {
		mainFrame = frame;
		jbInit();
	}
	private void jbInit() {
		setTitle("Set Category Filter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 255);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblSelectWhichCategories.setBounds(10, 11, 324, 14);
		
		contentPane.add(lblSelectWhichCategories);
		chckbxHumor.setBounds(20, 32, 97, 23);
		checkBoxes.add(chckbxHumor);
		contentPane.add(chckbxHumor);
		chckbxBiography.setBounds(20, 58, 97, 23);
		checkBoxes.add(chckbxBiography);
		contentPane.add(chckbxBiography);
		chckbxAutobiography.setBounds(20, 84, 111, 23);
		checkBoxes.add(chckbxAutobiography);
		contentPane.add(chckbxAutobiography);
		chckbxLiterature.setBounds(20, 110, 97, 23);
		checkBoxes.add(chckbxLiterature);
		contentPane.add(chckbxLiterature);
		chckbxMystery.setBounds(20, 136, 97, 23);
		checkBoxes.add(chckbxMystery);
		contentPane.add(chckbxMystery);
		chckbxGraphicnovel.setBounds(155, 32, 111, 23);
		checkBoxes.add(chckbxGraphicnovel);
		contentPane.add(chckbxGraphicnovel);
		chckbxYoungadult.setBounds(155, 58, 97, 23);
		checkBoxes.add(chckbxYoungadult);
		contentPane.add(chckbxYoungadult);
		chckbxRomance.setBounds(155, 84, 97, 23);
		checkBoxes.add(chckbxRomance);
		contentPane.add(chckbxRomance);
		chckbxScifi.setBounds(155, 110, 97, 23);
		checkBoxes.add(chckbxScifi);
		contentPane.add(chckbxScifi);
		chckbxOther.setBounds(155, 136, 97, 23);
		checkBoxes.add(chckbxOther);
		contentPane.add(chckbxOther);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnApply_actionPerformed(e);
			}
		});
		btnApply.setBounds(44, 166, 89, 23);
		
		contentPane.add(btnApply);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setBounds(155, 166, 89, 23);
		
		contentPane.add(btnCancel);
		
		for(String cat : mainFrame.getCategories()) //makes all of the current filters' corresponding checkBoxes are checked
			for(JCheckBox checkbox : checkBoxes)
				if(cat.equals(checkbox.getText()))
					checkbox.setSelected(true);
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {//closes the window
		this.dispose();
	}
	protected void do_btnApply_actionPerformed(ActionEvent e) {//generates a string array from the selected checkBoxes and uses it to set Categories in the mainFrame, then the window is closed
		ArrayList<String> categories = new ArrayList<String>();
		for(JCheckBox checkbox : checkBoxes)
			if(checkbox.isSelected())
				categories.add(checkbox.getText());
		mainFrame.setCategories(categories.toArray(new String[categories.size()]));
		mainFrame.query();
		this.dispose();
	}
}
