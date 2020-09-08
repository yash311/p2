package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Creating instance of Saving account
        SavingsAccount savingsAccount = new SavingsAccount(10000);

        //Creating Instance of Checking account
        CheckingAccount checkingAccount = new CheckingAccount(10000);
        String ip1="";


        // Performing Operations
        while (true){
            System.out.println("**********  For doing transaction : Press 1,      For Exit : Press 2 **********");
            ip1 = br.readLine();    // For main Input

            if(ip1.equals("2")){  // If requested for exit
                System.out.println("Exited from app");
                break;
            }else if(ip1.equals("1")){  // If requested for performing transaction

                System.out.println("********** Select type of transaction.  For Debit : Press 1,    For Credit : Press 2");

                String ip2 = br.readLine(); // Transaction Type

                if(!ip2.equals("1") && !ip2.equals("2")) {  // Invalid Input
                    System.out.println("Invalid Input. Please Enter again");
                    continue;
                }
                ip2 = ip2.equals("1")? "Debit" : "Credit";    // Updating the input variable

                int amt = 0;
                System.out.println("Please Provide the transaction amount in multiple of 500");
                String rs = br.readLine().trim();

                if(!isValidAmount(rs)) // If not a valid amount
                    continue;

                amt = Integer.parseInt(rs);

                System.out.println("********** Select type of Account.  For Savings : Press 1,    For Checking : Press 2");
                String ip3 = br.readLine();
                if(!ip3.equals("1") && !ip3.equals("2")) {
                    System.out.println("Invalid Input. Please Enter again");
                    continue;
                }

                if(ip3.equals("1")){
                    handleSavingAccountTransaction(savingsAccount, ip2, amt);
                }else{
                    handleCheckingAccountTransaction(checkingAccount, ip2, amt);
                }

            }else {
                System.out.println("Invalid Input. Please Enter again");
                continue;
            }
        }
        savingsAccount.printTransaction();
        checkingAccount.printTransaction();

    }


    /***
     * Handling Transaction on Savings account
     * @param account
     * @param transactionType
     * @param amt
     */
    private static void handleSavingAccountTransaction(SavingsAccount account, String transactionType, int amt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:SS");
        LocalDateTime d = LocalDateTime.now();
        String dateTime = formatter.format(d);

        account.performTransactin(dateTime, amt, transactionType);
        System.out.println("Transaction Performed Successfully");
    }


    /***
     * Handling Transaction on Checking account
     * @param account
     * @param transactionType
     * @param amt
     */
    private static void handleCheckingAccountTransaction(CheckingAccount account, String transactionType, int amt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:MM:SS");
        LocalDateTime d = LocalDateTime.now();
        String dateTime = formatter.format(d);

        account.performTransactin(dateTime, amt, transactionType);
        System.out.println("Transaction Performed Successfully");
    }


    /***
     * Checking for Valid Amount is provided or not
     * @param amt
     * @return boolean
     */
    private static boolean isValidAmount(String amt){
        if(amt==null){
            System.out.println("Amount not provided");
            return false;
        }

        for(char c : amt.toCharArray()){
            if(c<'0' || c>'9')return false;    // Amount is not an integer
        }

        int rs = Integer.parseInt(amt);
        if(rs%5 != 0)System.out.println("Amount is not multiple of 500");
        return rs % 500 == 0; // If amount is multiple of 500 then return TRUE or return FALSE
    }
}


/***
 * Main Parent Account class
 */
class Account{
    ArrayList<String> transaction;  // Arraylist to store the transactions
    Account(){
        transaction = new ArrayList<>();
    }
}

/***
 * Child class of Account class
 * SavingsAccount class
 */
class SavingsAccount extends Account{
    int balance;
    SavingsAccount(int bal){
        balance = bal;
    }

    public void performTransactin(String date, int amt, String transactionType){
        if(transactionType.equals("Debit"))
            balance-=amt;
        else if(transactionType.equals("Credit"))
            balance+=amt;

        transaction.add(date+"  "+transactionType+"  "+amt+"\n");
    }

    public void printTransaction(){
        System.out.println();
        System.out.println("_____________________________________________________________");
        System.out.println("          Printing Details for Savings Account          ");
        for(String tran : transaction)
            System.out.println(tran);

        System.out.println("Final Balance = " + balance);
    }
}


/***
 * Child class of Account class
 * Checking class
 */
class CheckingAccount extends Account{
    int balance;
    CheckingAccount(int bal){
        balance = bal;
    }

    public void performTransactin(String date, int amt, String transactionType){
        if(transactionType.equals("Debit"))
            balance-=amt;
        else if(transactionType.equals("Credit"))
            balance+=amt;

        transaction.add(date+"  "+transactionType+"  "+amt+"\n");
    }

    public void printTransaction(){
        System.out.println();
        System.out.println("_____________________________________________________________");
        System.out.println("          Printing Details for Checking Account          ");

        for(String tran : transaction)
            System.out.println(tran);

        System.out.println("Final Balance = " + balance);
    }
}