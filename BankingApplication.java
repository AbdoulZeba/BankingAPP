/*Abdoul H. Zeba

 * 11/04/2022
 * Comp167 section 01
 * This is a bank account program that allows the user to carry out transactions
 * 
 */

import  java.util.Scanner;

public class BankingApplication {

    //The show menu method display a menu option
    //for the user
    public static void showMenu(){

        System.out.println();
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous Balance");
        System.out.println("E. Exit");
    }

    //the getMenuOption() prompt the user to enter 
    //an option from the menu obove
    public static char getMenuOption(){

        Scanner scnr = new Scanner(System.in);
        System.out.println("= = = = = = = = = = = = = = = =");
        System.out.println("Enter an option: ");
        char userInput = scnr.next().charAt(0);
        System.out.println("= = = = = = = = = = = = = = = =");
        return Character.toLowerCase(userInput);

    }

    public static void main(String[] args) {  
        Scanner scnr = new Scanner(System.in);
        System.out.println(); // A print method to create space between output
        System.out.println("* * * * * * * * * * * * * * *"); 
        System.out.println();//A print method to create space between output
        System.out.println("Aggie Bank account");

        BankAccount bankAccount = new BankAccount(); //this create a new instance of the BankAccount class
        System.out.println();//A print method to create space between output
        System.out.println("Welcome NCAT student"); 
        System.out.println("Your ID is "+bankAccount.getCustomerId()); //displaying the user ID

        showMenu(); //The showMenu method is called to display the menu
        char menuOption; // The variable that holds the value of the user's choice
        boolean doLoop = true;
        // int firstAmountDeposited  = 0;
        // int i = 1;

        while(doLoop){ //while true, the loop will execute
            System.out.println();
            menuOption = getMenuOption(); //The getMenuOption() is called assigned the returned value to menuOption
            
            if (menuOption == 'a'){ //if user option is a the following code block excecute
                System.out.println("Your balance is "+bankAccount.getBalance());
            }
            System.out.println();
            if (menuOption == 'b'){ //if user option is 'deposit' the following code block executes
                System.out.println("Enter an amount to deposit"); 
                int amount = scnr.nextInt();
                bankAccount.deposit(amount); // the deposit method is called to set the amount deposited by the user
                bankAccount.setPreviousTransaction(amount);//the value of previous transaction is changed
            }
            if (menuOption == 'c'){ //if user option is 'withdraw' the following code block executes
                System.out.println("Enter an amount to withdraw");
                int amount = scnr.nextInt();
                bankAccount.withdraw(amount); //the withdraw method is called to set the amount withdrawal 
                bankAccount.setPreviousTransaction(-amount); //the value of previous transaction is changed
            }
            
            if (menuOption == 'd'){ //if the user input is 'previous balance', the folloowing code block executes
                bankAccount.getPreviousTransaction(); //
                
            }

            if (menuOption == 'e'){ //if the user input is 'exit', the loop exits
                doLoop = false;
            }
            
        }

        System.out.println("Thank you NCAT Student for using Aggie Bank");
        
    }


}
