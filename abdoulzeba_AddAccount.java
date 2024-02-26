/*Abdoul H Zeba
 * 12/01/22
 * Comp167 section 01
 * This is a GUI banking account project
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class abdoulzeba_AddAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	abdoulzeba_GuiBanking gb = new abdoulzeba_GuiBanking(this);//a new GuiBanking Object is created  with this particular AddAccount as
															//a parameter since we want to refer to the same AddAccount Object
	private JTextField txtName;
	private JTextField txtID;
	private JTextField txtBalance;
	
	public abdoulzeba_AddAccount(abdoulzeba_GuiBanking window) { //this constructor takes a Gui object as a parameter called window
		gb =  window;  //gb is assigned to window
		init(); 
	}
	
	public abdoulzeba_AddAccount(){ //default constructor
		init();
	}
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		try {
			
			
			abdoulzeba_AddAccount dialog = new abdoulzeba_AddAccount(); // a new AddAccount Object is created 
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Create the dialog.
	*/
	public void init() { // this init method is initialize the application 
		setTitle("Account information");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblName = new JLabel("Name");
			lblName.setBounds(22, 22, 61, 16);
			contentPanel.add(lblName);
		}
		{
			JLabel lblBalance = new JLabel("ID");
			lblBalance.setBounds(22, 76, 61, 16);
			contentPanel.add(lblBalance);
		}
		{
			JLabel lblBalance = new JLabel("Balance");
			lblBalance.setBounds(22, 137, 61, 16);
			contentPanel.add(lblBalance);
		}
		{
			txtName = new JTextField();
			txtName.setBounds(87, 17, 130, 26);
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			txtID = new JTextField();
			txtID.setBounds(87, 71, 130, 26);
			contentPanel.add(txtID);
			txtID.setColumns(10);
		}
		{
			txtBalance = new JTextField();
			txtBalance.setBounds(87, 132, 130, 26);
			contentPanel.add(txtBalance);
			txtBalance.setColumns(10);
		}
		{
			
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		{
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() { //Event listener for the OK button
			public void actionPerformed(ActionEvent e) {
				
				abdoulzeba_BankAccount ba = new abdoulzeba_BankAccount(); // new BankAccount Object is created when the user click OK
				ba.setCustomerName(txtName.getText()); //setting the customer name with txtName entered by the user
				ba.setCustomerId(txtID.getText());  //setting the customer id with txtID entered by the user
				ba.setInitialBalance(Integer.parseInt(txtBalance.getText())); //The starting balance is set to the user first entered 
																			//balance
				ba.setBalance(Integer.parseInt(txtBalance.getText()) ); //The balance to updated balance text field after the 
																		//user make any changes
				gb.addTolist(ba); //GuiBanking's addTolist method is called to add the new account the list of Bank Accounts
				
				gb.frmAddAccount.setVisible(true); // The GuiBanking visibility is set to true
				
				txtName.setText(""); //the text fields are updated to empty
				txtID.setText("");
				txtBalance.setText("");
				
				dispose(); //This activity exits
			
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		}
		
		
		{
		JButton cancelButton = new JButton("Cancel"); //Event listener for the Cancel button
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gb.frmAddAccount.setVisible(true); // The GuiBanking visibility is set to true
				dispose(); //This activity exits
			}
		});
		
		cancelButton.setActionCommand("Cancel");
			buttonPane.add(cancelButton);
		}
		
		}
	}



}
