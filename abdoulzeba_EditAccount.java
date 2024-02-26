/*Abdoul H Zeba
 * 12/01/22
 * Comp167 section 01
 * This is a GUI banking account project
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 

public class abdoulzeba_EditAccount extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField amount;
	private JLabel lblPreviousTransaction;
	private JLabel txtBalance;
	private JLabel lblName;
	private JLabel lblBalance;
	private JLabel lblID ;
	private JLabel txtName ;
	private JLabel txtID;
	
	int tempDepositedAmounts; // this variable will hold the total amount deposited by the user
	int tempWithdrawnAmounts; // this variable will hold the total amount withdrawn by the user
	
	DefaultListModel<String>  dlm = new DefaultListModel<String> (); //list model for previous transaction list
	
	abdoulzeba_BankAccount account = new abdoulzeba_BankAccount(); //new BankAccount Object is created

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			abdoulzeba_BankAccount account = new abdoulzeba_BankAccount(); // BankAccount Object  to be passed into the constructor
			abdoulzeba_EditAccount dialog = new abdoulzeba_EditAccount(account); //new EditAccount is created with a BankAccount Object
																				//passed into it 
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public abdoulzeba_EditAccount(abdoulzeba_BankAccount account) { //EditAccount Constructor with a BankAccount Object as a parameter
		tempDepositedAmounts = 0;
		tempWithdrawnAmounts = 0;
		this.account = account; // this.account is assigned with the BankAccount Object account
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JList list = new JList();
			list.setBounds(228, 36, 199, 179);
			contentPanel.add(list);
			list.setModel(dlm);
		}
		{
			lblPreviousTransaction = new JLabel("Previous transaction");
			lblPreviousTransaction.setBounds(249, 8, 178, 16);
			contentPanel.add(lblPreviousTransaction);
		}
		{
			lblName = new JLabel("Name");
			lblName.setBounds(19, 25, 61, 16);
			contentPanel.add(lblName);
			
		}
		
		{
			lblID = new JLabel("ID");
			lblID.setBounds(19, 71, 61, 16);
			contentPanel.add(lblID);
			
		}
		{
			lblBalance = new JLabel("Balance");
			lblBalance.setBounds(19, 122, 61, 16);
			contentPanel.add(lblBalance);
		}
		{
			txtName = new JLabel("None");
			txtName.setBounds(101, 25, 61, 16);
			contentPanel.add(txtName);
			txtName.setText(account.getCustomerName()); //txtName is set to Customer name 
		}
		{
			txtID = new JLabel("None");
			txtID.setBounds(101, 71, 61, 16);
			contentPanel.add(txtID);
			txtID.setText(account.getCustomerId()); //txtID is set Customer toCustomer ID
		}
		{
			txtBalance = new JLabel("None");
			txtBalance.setBounds(101, 122, 61, 16);
			contentPanel.add(txtBalance);
			txtBalance.setText(Integer.toString(account.getBalance())); //txtBalance is set Customer  to Customer ID
		}
		{
			amount = new JTextField();
			amount.setBounds(19, 150, 130, 26);
			contentPanel.add(amount);
			amount.setColumns(10);
		}
		
		
		{
			JButton btnDeposit = new JButton("Deposit"); //Event listener for the Deposit button
			btnDeposit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					account.deposit(Integer.parseInt(amount.getText())); //amount is deposited to user's BankAccount
					txtBalance.setText(Integer.toString(account.getBalance())); // txtBalance is changed to current balance
					account.setPreviousTransaction(Integer.parseInt(amount.getText())); //previous transaction is set to amount
					dlm.addElement("Deposited: "+Integer.toString(account.getPreviousTransaction())); // previous transaction is added
																								//to previous transaction list
					
					if(account.getPreviousTransaction() > 0) { // if previous transaction is positive, then it was a deposit
						tempDepositedAmounts += account.getPreviousTransaction();//tempDepositedAmounts is increment by previous transaction
						
					}
					
					
				}
			});
			btnDeposit.setBounds(29, 172, 117, 29);
			contentPanel.add(btnDeposit);
		}
		{
			JButton btnWithdraw = new JButton("Withdraw"); 
			btnWithdraw.addActionListener(new ActionListener() { //Event listener for the Withdraw button
				public void actionPerformed(ActionEvent e) {
					
				
					if (account.getBalance() < Integer.parseInt(amount.getText())){
						dlm.addElement("You don't have suffissant funds to withdraw");

					}
					else { //otherwise this block of code execute
						
						account.withdraw(Integer.parseInt(amount.getText())); //amount is withdrawn form user's BankAccount
						txtBalance.setText(Integer.toString(account.getBalance())); // txtBalance is changed to current balance
						account.setPreviousTransaction(-Integer.parseInt(amount.getText())); //previous transaction is set to amount
						dlm.addElement("Withdrawn: "+Integer.toString(-1*account.getPreviousTransaction()));// previous transaction is added
																								//to previous transaction list
						
						
						if (account.getPreviousTransaction() < 0) {// if previous transaction 
																	//is negative, then it was a withdrawal
							tempWithdrawnAmounts +=account.getPreviousTransaction()*-1; //tempWithdrawnAmounts is increment by previous transaction
							
						}
						
					}
					
				}
			});
			btnWithdraw.setBounds(29, 198, 117, 29);
			contentPanel.add(btnWithdraw);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK"); 
				okButton.addActionListener(new ActionListener() { //action listener for OK button
					public void actionPerformed(ActionEvent e) {
						account.setInitialBalance(Integer.parseInt(txtBalance.getText())); //initial balance is set to user changes
						dispose(); 
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//the changes aren't saved and the initial balance is set back to its original amount
						account.setInitialBalance(Integer.parseInt(txtBalance.getText())-tempDepositedAmounts+tempWithdrawnAmounts);
						
						account.setBalance(account.getInitialBalance()); //balance is set initial balance
						txtBalance.setText(Integer.toString(account.getBalance()));
						
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	
	

}
