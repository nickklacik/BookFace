import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PriceFilterFrame extends JFrame {

	private JPanel contentPane;
	private final JCheckBox checkBoxMax = new JCheckBox("");
	private final JCheckBox checkBoxMin = new JCheckBox("");
	private final JLabel lblMaximum = new JLabel("Maximum Retail Price:");
	private final JLabel lblMinimum = new JLabel("Minimum Retail Price:");
	private final JFormattedTextField maxFTF = new JFormattedTextField(NumberFormat.getCurrencyInstance());
	private final JFormattedTextField minFTF = new JFormattedTextField(NumberFormat.getCurrencyInstance());
	private KlacikBookFaceFrame mainFrame;
	private final JButton btnApplyFilters = new JButton("Apply");
	private final JButton btnCancel = new JButton("Cancel");
	/**
	 * Create the frame.
	 */
	public PriceFilterFrame(KlacikBookFaceFrame frame) {
		mainFrame = frame;
		jbInit();
	}
	private void jbInit() {
		setTitle("Set Retail Price Filter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 292, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		checkBoxMax.setToolTipText("Click this to enable Maximum Retail Price Filter");
		checkBoxMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_checkBoxMax_actionPerformed(e);
			}
		});
		checkBoxMax.setBounds(6, 20, 21, 23);
		
		contentPane.add(checkBoxMax);
		checkBoxMin.setToolTipText("Click this to enable Minimum Retail Price Filter");
		checkBoxMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_checkBoxMin_actionPerformed(e);
			}
		});
		checkBoxMin.setBounds(6, 46, 21, 23);
		
		contentPane.add(checkBoxMin);
		lblMaximum.setBounds(33, 24, 130, 14);
		
		contentPane.add(lblMaximum);
		lblMinimum.setBounds(33, 50, 130, 14);
		
		contentPane.add(lblMinimum);
		maxFTF.setToolTipText("Enter Maximum Price Filter Here");
		maxFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {// select and selectAll methods do not work under focusGained without the SwingUtilities.invokeLater method
		            @Override
		            public void run() {
		            	do_maxFTF_focusGained(arg0);
		            }
		        });
				
			}
		});
		maxFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		maxFTF.setEnabled(false);
		maxFTF.setBounds(163, 23, 87, 20);
		
		contentPane.add(maxFTF);
		minFTF.setToolTipText("Enter Minimum Price Filter Here");
		minFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	do_minFTF_focusGained(e);
		            }
		        });
			}
		});
		minFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		minFTF.setEnabled(false);
		minFTF.setBounds(163, 49, 87, 20);
		
		contentPane.add(minFTF);
		btnApplyFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnApplyFilters_actionPerformed(e);
			}
		});
		btnApplyFilters.setBounds(41, 90, 89, 23);
		
		contentPane.add(btnApplyFilters);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnCancel_actionPerformed(arg0);
			}
		});
		btnCancel.setBounds(140, 90, 89, 23);
		
		contentPane.add(btnCancel);
		if(mainFrame.getMaxPriceFilter() == -1)
			maxFTF.setOpaque(false);
		else {
			checkBoxMax.setSelected(true);
			maxFTF.setEnabled(true);
			maxFTF.setValue(mainFrame.getMaxPriceFilter());
		}
		
		if(mainFrame.getMinPriceFilter() == -1)
			minFTF.setOpaque(false);
		else {
			checkBoxMin.setSelected(true);
			minFTF.setEnabled(true);
			minFTF.setValue(mainFrame.getMinPriceFilter());
		}
	}
	protected void do_checkBoxMax_actionPerformed(ActionEvent e) {//enables and disables maxFTF when checkBoxMax is clicked. Also changes the opaqueness to visually show if maxFTF is enabled or not
		if(checkBoxMax.isSelected())
		{
			maxFTF.setEnabled(true);
			maxFTF.setOpaque(true);
			maxFTF.setValue(0);
		}
		else
		{
			maxFTF.setEnabled(false);
			maxFTF.setOpaque(false);
		}
		
	}
	protected void do_checkBoxMin_actionPerformed(ActionEvent e) {//enables and disables minFTF when checkBoxMin is clicked. Also changes the opaqueness to visually show if minFTF is enabled or not
		if(checkBoxMin.isSelected())
		{
			minFTF.setEnabled(true);
			minFTF.setOpaque(true);
			minFTF.setValue(0);
		}
		else
		{
			minFTF.setEnabled(false);
			minFTF.setOpaque(false);
		}
	}
	protected void do_btnCancel_actionPerformed(ActionEvent arg0) {//closes the window
		this.dispose();
	}
	protected void do_btnApplyFilters_actionPerformed(ActionEvent e) {//checks to make sure the max filter is not less than the min filter, then sets the filters in the main frame, then closes the window
		if(checkBoxMax.isSelected() && checkBoxMin.isSelected() && ((Number) maxFTF.getValue()).doubleValue() < ((Number) minFTF.getValue()).doubleValue()) {
			JOptionPane.showMessageDialog(this, "Please Enter a Price greater than the minimum", "Max Less Than Min Error", JOptionPane.ERROR_MESSAGE);
			maxFTF.grabFocus();
		}
		else {
			if(checkBoxMax.isSelected())
				mainFrame.setMaxPriceFilter(((Number) maxFTF.getValue()).doubleValue());
			else mainFrame.setMaxPriceFilter(-1);
			
			if(checkBoxMin.isSelected())
				mainFrame.setMinPriceFilter(((Number) minFTF.getValue()).doubleValue());
			else mainFrame.setMinPriceFilter(-1);
			
			mainFrame.query();
			this.dispose();
		}
	}
	protected void do_maxFTF_focusGained(FocusEvent arg0) {//selects all text after the $ to make filling out the field more bearable
		maxFTF.select(1, maxFTF.getText().length());
	}
	protected void do_minFTF_focusGained(FocusEvent e) {
		minFTF.select(1, minFTF.getText().length());
	}
}
