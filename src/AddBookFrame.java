import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class AddBookFrame extends JFrame {

	private JPanel contentPane;
	private final JLabel lblBookId = new JLabel("BookID:");
	private final JFormattedTextField idFTF = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JLabel lblBookName = new JLabel("BookName:");
	private final JTextField bookNameTF = new JTextField();
	private final JLabel lblAuthorName = new JLabel("AuthorName:");
	private final JTextField authorNameTF = new JTextField();
	private final JLabel lblCategory = new JLabel("Category:");
	private final JComboBox catCB = new JComboBox();
	private final JLabel lblWholesaleprice = new JLabel("WholesalePrice:");
	private final JFormattedTextField wholesaleFTF = new JFormattedTextField(NumberFormat.getCurrencyInstance());
	private final JLabel lblRetailprice = new JLabel("RetailPrice:");
	private final JFormattedTextField retailFTF = new JFormattedTextField(NumberFormat.getCurrencyInstance());
	private final JLabel lblQoh = new JLabel("QOH:");
	private final JFormattedTextField qohFTF = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JLabel lblMinquant = new JLabel("MinQuant:");
	private final JFormattedTextField minQuantFTF = new JFormattedTextField(NumberFormat.getNumberInstance());
	private final JButton btnAddBook = new JButton("Add Book");
	private KlacikBookFaceFrame mainFrame;
	private final JButton btnCancel = new JButton("Cancel");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmOpenHelp = new JMenuItem("Open Help ...");
		
	/**
	 * Create the frame.
	 */
	public AddBookFrame(KlacikBookFaceFrame frame) {
		mainFrame = frame;
		authorNameTF.setToolTipText("Enter the AuthorName Here");
		authorNameTF.setBounds(145, 61, 86, 20);
		authorNameTF.setColumns(10);
		bookNameTF.setToolTipText("Enter the BookName Here");
		bookNameTF.setBounds(145, 36, 86, 20);
		bookNameTF.setColumns(10);
		jbInit();
	}
	private void jbInit() {
		setTitle("Add Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 320);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnHelp);
		mntmOpenHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmOpenHelp_actionPerformed(e);
			}
		});
		
		mnHelp.add(mntmOpenHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblBookId.setBounds(32, 14, 46, 14);
		
		contentPane.add(lblBookId);
		idFTF.setToolTipText("Enter the BookID Here");
		idFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() { // select and selectAll methods do not work under focusGained without the SwingUtilities.invokeLater method
		            @Override
		            public void run() {
		            	do_idFTF_focusGained(e);
		            }
		        });
				
			}
		});
		idFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		idFTF.setBounds(145, 11, 46, 20);
		idFTF.setValue(0);
		contentPane.add(idFTF);
		lblBookName.setBounds(32, 39, 91, 14);
		
		contentPane.add(lblBookName);
		
		contentPane.add(bookNameTF);
		lblAuthorName.setBounds(32, 64, 103, 14);
		
		contentPane.add(lblAuthorName);
		
		contentPane.add(authorNameTF);
		lblCategory.setBounds(32, 89, 60, 14);
		
		contentPane.add(lblCategory);
		catCB.setToolTipText("Pick the Category of the book Here");
		catCB.setModel(new DefaultComboBoxModel(new String[] {"Humor", "Biography", "AutoBiography", "Literature", "Mystery", "GraphicNovel", "YoungAdult", "Romance", "SciFi", "Other"}));
		catCB.setBounds(145, 86, 86, 20);
		
		contentPane.add(catCB);
		lblWholesaleprice.setBounds(32, 114, 103, 14);
		
		contentPane.add(lblWholesaleprice);
		wholesaleFTF.setToolTipText("Enter the WholesalePrice Here");
		wholesaleFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	do_wholesaleFTF_focusGained(e);
		            }
		        });
				
			}
		});
		wholesaleFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		wholesaleFTF.setBounds(145, 111, 46, 20);
		wholesaleFTF.setValue(0);
		contentPane.add(wholesaleFTF);
		lblRetailprice.setBounds(32, 139, 91, 14);
		
		contentPane.add(lblRetailprice);
		retailFTF.setToolTipText("Enter the RetailPrice Here");
		retailFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	do_retailFTF_focusGained(e);
		            }
		        });
				
			}
		});
		retailFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		retailFTF.setBounds(145, 136, 46, 20);
		retailFTF.setValue(0);
		contentPane.add(retailFTF);
		lblQoh.setBounds(32, 164, 46, 14);
		
		contentPane.add(lblQoh);
		qohFTF.setToolTipText("Enter the Quantity On Hand (QOH) Here");
		qohFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	do_qohFTF_focusGained(e);
		            }
		        });
				
			}
		});
		qohFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		qohFTF.setBounds(145, 161, 46, 20);
		qohFTF.setValue(0);
		contentPane.add(qohFTF);
		lblMinquant.setBounds(32, 189, 91, 14);
		
		contentPane.add(lblMinquant);
		minQuantFTF.setToolTipText("Enter the Minimum Quantity (MinQuant) Here");
		minQuantFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		            	do_minQuantFTF_focusGained(e);
		            }
		        });
				
			}
		});
		minQuantFTF.setHorizontalAlignment(SwingConstants.RIGHT);
		minQuantFTF.setBounds(145, 186, 46, 20);
		minQuantFTF.setValue(0);
		contentPane.add(minQuantFTF);
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnAddBook_actionPerformed(arg0);
			}
		});
		btnAddBook.setBounds(32, 225, 89, 23);
		
		contentPane.add(btnAddBook);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setBounds(131, 225, 89, 23);
		
		contentPane.add(btnCancel);
	}
	protected void do_btnAddBook_actionPerformed(ActionEvent arg0) {//checks if certain fields are valid then sttempts in insert record into the database
		if(Integer.parseInt(idFTF.getValue().toString()) < 0) {
			JOptionPane.showMessageDialog(this, "BookID cannot be negative. Please change BookID to a positive number", "Negative ID Error", JOptionPane.ERROR_MESSAGE);
			idFTF.grabFocus();
		}
		else if(Integer.parseInt(qohFTF.getValue().toString()) < 0) {
			JOptionPane.showMessageDialog(this, "QOH cannot be negative. Please change QOH to a positive number", "Negative QOH Error", JOptionPane.ERROR_MESSAGE);
			qohFTF.grabFocus();
		}
		else if(Integer.parseInt(minQuantFTF.getValue().toString()) < 0) {
			JOptionPane.showMessageDialog(this, "MinQuant cannot be negative. Please change MinQuant to a positive number", "Negative MinQuant Error", JOptionPane.ERROR_MESSAGE);
			minQuantFTF.grabFocus();
		}
		else
		{
			Statement stmt = null;
			ResultSet rs = null;
			String insQuery = null;
			String dupCheck = null;
			
			try
			{
				//Establish the connection
				Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
				
				//Create a statement to check from duplicates
				stmt = conn.createStatement();
				dupCheck = "SELECT * FROM Inventory WHERE BookID = " + idFTF.getValue() + " OR BookName = '" + bookNameTF.getText().trim() + "'";
				System.out.println(dupCheck);
				
				//Execute duplicate check
				rs = stmt.executeQuery(dupCheck);
				
				if(rs.next()) {
					JOptionPane.showMessageDialog(this, "This BookID or BookName already exists in Inventory. Please enter a new values for BookID and BookName", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
				} 
				else 
				{
					//Create the Statement
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
					
					insQuery = "INSERT INTO Inventory (BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant) VALUES (";
					insQuery += idFTF.getValue() + ",";
					insQuery += "'" + bookNameTF.getText().trim() + "',";
					insQuery += "'" + authorNameTF.getText().trim() + "',";
					insQuery += "'" + catCB.getSelectedItem() + "',";
					insQuery += wholesaleFTF.getValue() + ",";
					insQuery += retailFTF.getValue() + ",";
					insQuery += qohFTF.getValue() + ",";
					insQuery += minQuantFTF.getValue() + ")";
					
					System.out.println(insQuery);
					
					//Execute
					if(stmt.executeUpdate(insQuery) != 0) {
						JOptionPane.showMessageDialog(this, "Book Succesfully added to Inventory", "Insert Succesful", JOptionPane.INFORMATION_MESSAGE);
						mainFrame.query();
						this.dispose();
					} 
					else JOptionPane.showMessageDialog(this, "Failed to add Book to Inventory", "Insert Unsuccesful", JOptionPane.ERROR_MESSAGE);
				}//else
				rs.close();
				conn.close();
			}//try
			catch (SQLException ex)
			{
				System.out.println("SQL Exception: " + ex.getMessage());
				System.out.println("SQL State: " + ex.getSQLState());
				System.out.println("Vendor Error: " + ex.getErrorCode());
				ex.printStackTrace();
			} //catch
		}//else
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {//closes window
		this.dispose();
	}
	protected void do_wholesaleFTF_focusGained(FocusEvent e) { //selects all text after the $ to make filling out the field more bearable
		wholesaleFTF.select(1, wholesaleFTF.getText().length());
	}
	protected void do_retailFTF_focusGained(FocusEvent e) {
		retailFTF.select(1, retailFTF.getText().length());
	}
	protected void do_idFTF_focusGained(FocusEvent e) {
		idFTF.selectAll();
	}
	protected void do_qohFTF_focusGained(FocusEvent e) {
		qohFTF.selectAll();
	}
	protected void do_minQuantFTF_focusGained(FocusEvent e) {
		minQuantFTF.selectAll();
	}
	protected void do_mntmOpenHelp_actionPerformed(ActionEvent e) {//create Add Book Help dialogbox
		JOptionPane.showMessageDialog(this, "<html><h3>Add Book Help:</h3>"+
				"<p><u>BookID:</u> Enter the BookID into the corresponding textbox</p><br>"+
				"<p><u>BookName:</u> Enter the BookName into the corresponding textbox</p><br>"+
				"<p><u>AuthorName:</u> Enter the AuthorName into the corresponding textbox</p><br>"+
				"<p><u>Category:</u> Pick the Category of the book from the corresponding drop down menu</p><br>"+
				"<p><u>WholesalePrice:</u> Enter the WholesalePrice into the corresponding textbox</p><br>"+
				"<p><u>RetailPrice:</u> Enter the RetailPrice into the corresponding textbox</p><br>"+
				"<p><u>QOH:</u> Enter the Quantity On Hand (QOH) into the corresponding textbox</p><br>"+
				"<p><u>MinQuant:</u> Enter the Minimum Quantity (MinQuant) into the corresponding textbox</p><br>"
				+ "Click the 'Add Book' Button when you've filled out all the text fields in order to add the book to the Inventory</html>", "Add Book Help", JOptionPane.INFORMATION_MESSAGE);
	}
}
