import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class KlacikBookFaceFrame extends JFrame {

	private JPanel contentPane;
	private final JTable outputTable = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnTools = new JMenu("Tools");
	private final JMenuItem mntmAddBook = new JMenuItem("Add Book ...");
	private final JMenu mnSetSort = new JMenu("Set Sort");
	private final JRadioButtonMenuItem rdbtnmntmByBookName = new JRadioButtonMenuItem("By Book Name");
	private final JRadioButtonMenuItem rdbtnmntmByRetailPrice = new JRadioButtonMenuItem("By Retail Price");
	private final JRadioButtonMenuItem rdbtnmntmByCategory = new JRadioButtonMenuItem("By Category");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final JMenu mnSetFilter = new JMenu("Set Filter");
	private final JMenuItem mntmByRetailPrice = new JMenuItem("By Retail Price ...");
	private final JMenuItem mntmByCategory = new JMenuItem("By Category ...");
	private final JMenuItem mntmRemoveCurrentSort = new JMenuItem("Remove Current Sort");
	private double maxPriceFilter = -1, minPriceFilter = -1;
	private String[] categories = new String[0];
	private final JLabel lblCurrentSort = new JLabel("<html><u>Current Sort:</u></html>");
	private final JLabel lblSort = new JLabel("None");
	private final JLabel lblRetailPriceFilters = new JLabel("<html><u>Retail Price Filters:</u></html>");
	private final JLabel lblMaxPrice = new JLabel("Max Price: ");
	private final JLabel lblMinPrice = new JLabel("Min Price:");
	private final JLabel lblMax = new JLabel("None");
	private final JLabel lblMin = new JLabel("None");
	private final JLabel lblCategoryFilters = new JLabel("<html><u>Category Filters:</u></html>");
	private final JTextArea categoryTA = new JTextArea();
	private final JLabel lblNewLabel = new JLabel("Inventory");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmOpenHelp = new JMenuItem("Open Help ...");
	private DecimalFormat priceFormat = new DecimalFormat("$ #,###.00");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KlacikBookFaceFrame frame = new KlacikBookFaceFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KlacikBookFaceFrame() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Klacik Book Face");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 400);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmExit_actionPerformed(e);
			}
		});
		
		mnFile.add(mntmExit);
		
		menuBar.add(mnTools);
		mntmAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mntmAddBook_actionPerformed(arg0);
			}
		});
		
		mnTools.add(mntmAddBook);
		
		mnTools.add(mnSetSort);
		buttonGroup.add(rdbtnmntmByBookName);
		rdbtnmntmByBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtnmntmByBookName_actionPerformed(e);
			}
		});
		
		mnSetSort.add(rdbtnmntmByBookName);
		buttonGroup.add(rdbtnmntmByRetailPrice);
		rdbtnmntmByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtnmntmByRetailPrice_actionPerformed(e);
			}
		});
		
		mnSetSort.add(rdbtnmntmByRetailPrice);
		buttonGroup.add(rdbtnmntmByCategory);
		rdbtnmntmByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_rdbtnmntmByCategory_actionPerformed(e);
			}
		});
		
		mnSetSort.add(rdbtnmntmByCategory);
		mntmRemoveCurrentSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmRemoveCurrentSort_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mntmRemoveCurrentSort);
		
		mnTools.add(mnSetFilter);
		mntmByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_mntmByRetailPrice_actionPerformed(arg0);
			}
		});
		
		mnSetFilter.add(mntmByRetailPrice);
		mntmByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByCategory_actionPerformed(e);
			}
		});
		
		mnSetFilter.add(mntmByCategory);
		
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
		scrollPane.setBounds(10, 45, 764, 273);
		
		contentPane.add(scrollPane);
		outputTable.setFont(new Font("Courier New", Font.PLAIN, 11));
		outputTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"BookID", "BookName", "AuthorName", "Category", "WholesalePrice", "RetailPrice", "QOH", "MinQuant"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Double.class, Double.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		outputTable.getColumnModel().getColumn(0).setPreferredWidth(54);
		outputTable.getColumnModel().getColumn(1).setPreferredWidth(140);
		outputTable.getColumnModel().getColumn(2).setPreferredWidth(140);
		outputTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		outputTable.getColumnModel().getColumn(4).setPreferredWidth(90);
		outputTable.getColumnModel().getColumn(5).setPreferredWidth(70);
		outputTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		outputTable.getColumnModel().getColumn(7).setPreferredWidth(60);
		scrollPane.setViewportView(outputTable);
		
		//load the driver
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		query();//queries the database and fills the JTable
		
		lblCurrentSort.setToolTipText("In order to set the current sort, go to Tools > Set Sort");
		lblCurrentSort.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCurrentSort.setBounds(781, 45, 83, 14);
		
		contentPane.add(lblCurrentSort);
		lblSort.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSort.setBounds(815, 62, 73, 14);
		
		contentPane.add(lblSort);
		lblRetailPriceFilters.setToolTipText("In order to set price filters, go to Tools > Set Filter > By Retail Price");
		lblRetailPriceFilters.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRetailPriceFilters.setBounds(784, 87, 122, 14);
		
		contentPane.add(lblRetailPriceFilters);
		lblMaxPrice.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMaxPrice.setBounds(817, 105, 64, 14);
		
		contentPane.add(lblMaxPrice);
		lblMinPrice.setFont(new Font("Tahoma", Font.ITALIC, 11));
		lblMinPrice.setBounds(817, 130, 64, 14);
		
		contentPane.add(lblMinPrice);
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMax.setBounds(873, 105, 46, 14);
		
		contentPane.add(lblMax);
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMin.setBounds(873, 130, 46, 14);
		
		contentPane.add(lblMin);
		lblCategoryFilters.setToolTipText("In order to set category filters, go to Tools > Set Filter > By Category");
		lblCategoryFilters.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCategoryFilters.setBounds(784, 155, 109, 14);
		
		contentPane.add(lblCategoryFilters);
		categoryTA.setEditable(false);
		categoryTA.setFont(new Font("Tahoma", Font.PLAIN, 11));
		categoryTA.setBounds(817, 170, 73, 148);
		categoryTA.setOpaque(false);
		contentPane.add(categoryTA);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 15, 122, 19);
		
		contentPane.add(lblNewLabel);
	}
	
	private String createQuery() {//this code generates an SQL SELECT query based on the current filters and sorts
		String query = "SELECT BookID, BookName, AuthorName, Category, WholesalePrice, RetailPrice, QOH, MinQuant FROM Inventory WHERE 1=1 ";
		
		//Filters
			//Price
		if(maxPriceFilter != -1)
			query += "AND RetailPrice < " + maxPriceFilter + " ";
		if(minPriceFilter != -1)
			query += "AND RetailPrice > " + minPriceFilter + " ";
		
			//Categories
		if(categories.length > 0)
		{
			query += "AND (1=2 ";
			for(String cat : categories)
				query += "OR Category = '" + cat + "' ";
			
			query += ") ";
		}
		
		//Sorts
		if(rdbtnmntmByBookName.isSelected())
			query += "ORDER BY BookName";
		else if(rdbtnmntmByCategory.isSelected())
			query += "ORDER BY Category";
		else if(rdbtnmntmByRetailPrice.isSelected())
			query += "ORDER BY RetailPrice";
		else query += "ORDER BY BookID";
		System.out.println(query);
		return query;
	}
	void query() {// queries the database and updates the JTable
		ResultSet rs = null;
		Statement stmt = null;
		String query = createQuery();
		
		try
		{
			//Establish the connection
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/Public/BookFace.accdb");
			
			//Create the Statement
			stmt = conn.createStatement();
			
			//Execute the Statement
			rs = stmt.executeQuery(query);
			
			//Process Results
			
			//remove previously added rows
			while(outputTable.getRowCount() > 0)
				((DefaultTableModel) outputTable.getModel()).removeRow(0);
			
			int numCol = rs.getMetaData().getColumnCount();
			
			while(rs.next()) 
			{
				//create an object to hold a record
				Object[] row = new Object[numCol];
				
				//grab the fields from the record and put them into the row
				for(int i = 0; i < numCol; i++)
				{
					row[i] = rs.getObject(i+1);
					
				}
				
				//insert this row into our JTable
				((DefaultTableModel) outputTable.getModel()).insertRow(rs.getRow()-1, row);
			}
			
			//Clean up
			rs.close();
			conn.close();
		} //try
		catch (SQLException ex)
		{
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			ex.printStackTrace();
		} //catch
	}
	protected void do_mntmAddBook_actionPerformed(ActionEvent arg0) {//Creates an AddBookFrame so the user can add books to the database
		AddBookFrame bookFrame = new AddBookFrame(this);
		bookFrame.setVisible(true);
	}
	protected void do_mntmByRetailPrice_actionPerformed(ActionEvent arg0) {// Creates a PriceFilterFrame so the user can set the RetailPrice filters
		PriceFilterFrame priceFrame = new PriceFilterFrame(this);
		priceFrame.setVisible(true);
	}
	void setMaxPriceFilter(double maxFilter) {//sets maxPriceFilter and updates lblMax
		maxPriceFilter = maxFilter;
		if(maxFilter == -1)
			lblMax.setText("None");
		else lblMax.setText(priceFormat.format(maxPriceFilter));
	}
	void setMinPriceFilter(double minFilter) {//sets minPriceFilter and updates lblMin
		minPriceFilter = minFilter;
		if(minFilter == -1)
			lblMin.setText("None");
		else lblMin.setText(priceFormat.format(minPriceFilter));
	}
	double getMaxPriceFilter() {
		return maxPriceFilter;
	}
	double getMinPriceFilter(){
		return minPriceFilter;
	}
	
	protected void do_mntmByCategory_actionPerformed(ActionEvent e) {// creates a CategoryFilterFrame so the user can set the category filters
		CategoryFilterFrame catFrame = new CategoryFilterFrame(this);
		catFrame.setVisible(true);
	}
	void setCategories(String[] cats) {//sets categories and updates categoryTA
		categories = cats;
		categoryTA.setText("");
		for(String cat : categories)
			categoryTA.append(cat + "\n");
	}
	String[] getCategories() {
		return categories;
	}
	
	protected void do_mntmExit_actionPerformed(ActionEvent e) {//closes the program
		System.exit(0);
	}
	protected void do_mntmRemoveCurrentSort_actionPerformed(ActionEvent e) {//changes sort back to default by clearing the radiobuttion in the Set Category Menu 
		buttonGroup.clearSelection();
		lblSort.setText("None");
		query();
	}
	protected void do_rdbtnmntmByBookName_actionPerformed(ActionEvent e) {//changes sort to BookName and updates the JTable
		lblSort.setText("BookName");
		query();
	} 
	protected void do_rdbtnmntmByRetailPrice_actionPerformed(ActionEvent e) {//changes the sort to RetailPrice and updates the JTable
		lblSort.setText("RetailPrice");
		query();
	}
	protected void do_rdbtnmntmByCategory_actionPerformed(ActionEvent e) {//changes the sort to Category and updates the JTable
		lblSort.setText("Category");
		query();
	}
	protected void do_mntmOpenHelp_actionPerformed(ActionEvent e) {//displays the help dialog box
		JOptionPane.showMessageDialog(this, "<html>The table below displays records from the Inventory table corresponding the the filters listed to the right of the table.<br>"
				+ "The table is sorted by the field listed to the right of the table under \"Current Sort.\"<br><br>"
				+ "In order to set the current sort, go to Tools > Set Sort<br><br>"
				+ "In order to set price filters, go to Tools > Set Filter > By Retail Price<br><br>"
				+ "In order to set category filters, go to Tools > Set Filter > By Category<br><br>"
				+ "In order to add more books to the Inventory Table, go to Tools > Add Book</html>", "Help", JOptionPane.INFORMATION_MESSAGE);
	}
}
