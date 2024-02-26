/*Abdoul H Zeba
 * 12/01/22
 * Comp167 section 01
 * This is a GUI banking account project
 */



import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;

public class abdoulzeba_GuiBanking { //Gui Main Application
	
	ArrayList<abdoulzeba_BankAccount> alAccount = new ArrayList<abdoulzeba_BankAccount>();
	DefaultListModel<abdoulzeba_BankAccount>  dlm = new DefaultListModel<abdoulzeba_BankAccount> ();
	JFrame frmAddAccount;
	private JButton btnAdd ;
	private JList list;
	abdoulzeba_AddAccount  AddAccount; // an AddAccount Object to refer to the user added account
	private JButton btnEdit;
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					abdoulzeba_AddAccount  AddAccount = new abdoulzeba_AddAccount(); //An Addaccount Object is created to be passed into
																					//the constructor
					abdoulzeba_GuiBanking window = new abdoulzeba_GuiBanking(AddAccount); // AddedAccount is passed into the
																						//GUI window
					window.frmAddAccount.setVisible(true);
					
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	* Create the application.
	*/
	public abdoulzeba_GuiBanking(abdoulzeba_AddAccount a) { // the AddAccount Object is assigned to the AddAccount Object passed in by the 
															//constructor

		AddAccount = a;
		initialize(AddAccount);
		
	}
	public abdoulzeba_GuiBanking() { //the default constructor
		
		initialize(AddAccount);
	}
	
	/**
	* Initialize the contents of the frame.
	*/
	public void initialize(abdoulzeba_AddAccount a) {
		
		frmAddAccount = new JFrame();
		frmAddAccount.setTitle("Add Account");
		frmAddAccount.setBounds(100, 100, 450, 300);
		frmAddAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddAccount.getContentPane().setLayout(null);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				a.setVisible(true);
				frmAddAccount.setVisible(false);
			}
		});
		btnAdd.setBounds(33, 74, 117, 29);
		frmAddAccount.getContentPane().add(btnAdd);
		
		list = new JList();
		list.setBounds(218, 32, 153, 186);
		frmAddAccount.getContentPane().add(list);
		
		btnEdit = new JButton("Edit"); //Edit account event listener
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//a new edit object is created when the user click on the edit button
				//and selected account is passed into the EditAccount constructor
				
				abdoulzeba_EditAccount edit = new abdoulzeba_EditAccount(alAccount.get(list.getSelectedIndex()));
				edit.setVisible(true);// edit is set to visible
			}
		});
		btnEdit.setBounds(33, 121, 117, 29);
		frmAddAccount.getContentPane().add(btnEdit);
		
		
		
	}
	
	public void addTolist(abdoulzeba_BankAccount a) { //This method add a BankAccount Object to the List of BankAccounts
		
		alAccount.add(a); //account added to the List of BankAccounts
		dlm.addElement(a); //account added to the list model
		list.setModel(dlm); 
		
	}

}
