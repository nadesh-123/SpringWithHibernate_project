package com.app;

import com.app.DAO.AccountDAO;
import com.app.DAO.AuthDAO;
import com.app.DAO.TransactionDAO;
import com.app.config.SpringJpaConfig;
import com.app.enums.AccountType;
import com.app.enums.TransactionType;
import com.app.exception.InvalidOwnershipException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Account;
import com.app.model.Transaction;
import com.app.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Scanner;

public class MainRun {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SpringJpaConfig.class);
        Scanner sc=new Scanner(System.in);
        TransactionDAO transactionDAO=context.getBean(TransactionDAO.class);
        AuthDAO authDAO=context.getBean(AuthDAO.class);
        AccountDAO accountDAO=context.getBean(AccountDAO.class);
   try{
       System.out.println("--------welcome BMS---------");
       System.out.println("enter the user name: ");
       String username=sc.nextLine();
       System.out.println("enter the password: ");
       String password=sc.nextLine();
       User user=authDAO.login(username,password);
       System.out.println(user);

       while(true){
           System.out.println("0 exit");
           System.out.println("1.Create an account.");
           System.out.println("2.Delete an account");
           System.out.println("3.update an account");
           System.out.println("4.Get account by Id");
           System.out.println("5.Fetch all accounts");
           int ch=sc.nextInt();
           if(ch==0){

               break;
           }
           switch (ch){
               case 1:
                   System.out.println("Enter the initial amount.");
                   double amount=sc.nextDouble();
                   sc.nextLine();
                   System.out.println("enter the account type");
                   AccountType accountType=AccountType.valueOf(sc.nextLine());
                   Account account=new Account();
                   account.setAccountType(accountType);
                   account.setBalance(amount);
                   accountDAO.addAccount(account,user.getUsername());
                   break;
               case 2:
                   System.out.println("enter the id to be deleted.");
                   int id=sc.nextInt();
                   try{
                   accountDAO.deleteAccountById(id,user.getUsername());
                   }catch (InvalidOwnershipException | ResourceNotFoundException e){
                       System.out.println(e.getMessage());
                   }

                   break;
               case 3:
                   System.out.println("enter the details to be updated");
                   System.out.println("Enter the initial amount.");
                   amount=sc.nextDouble();
                   sc.nextLine();
                   System.out.println("enter the account type");
                   accountType=AccountType.valueOf(sc.nextLine());
                  account=new Account();
                   account.setAccountType(accountType);
                   account.setBalance(amount);
                   accountDAO.updateAccount(account,user.getUsername());
                   break;
               case 4:
                   System.out.println("enter the id");
                    id=sc.nextInt();
                   try{
                   System.out.println(accountDAO.getAccountById(id,user.getUsername()));
                   }catch (InvalidOwnershipException |ResourceNotFoundException e){
                       System.out.println(e.getMessage());
                   }
                   break;
               case 5:
                   accountDAO.fetchAllAccounts(user.getUsername()).forEach(System.out::println);
                   break;
           }
       }
   }catch(Exception e){
       System.out.println(e.getMessage());
   }
        context.close();
    }
}
