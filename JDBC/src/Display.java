/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Display {
    Scanner scan = new Scanner(System.in);
    Statement stmt = null;
    static final String displayFormat="%-5s%-15s%-15s%-15s\n";
    
    Display(Statement state)
    {
        stmt = state;
        mainDis();
    }
    void mainDis()
    {
        System.out.println("*******JDBC Project*******");
        System.out.println("1. Print All Writing Groups");
        System.out.println("2. Choose Writing Group data");
        System.out.println("3. Print All Publishers");
        System.out.println("4. Choose Publisher data");
        System.out.println("5. Print All Books");
        System.out.println("6. Choose Book data");
        System.out.println("7. Insert new Book");
        System.out.println("8. Insert new Publisher");
        System.out.println("9. Remove Book");
        System.out.println("10. end");
        
        int choice = scan.nextInt();
        if(validChoice(1,10,choice)){
            switch (choice) {
                case 1:
                    printWritingsGroups();
                    break;
                case 2:
                    chooseWritingGroups();
                    break;
                case 3:
                    printPublishers();
                    break;
                case 4:
                    choosePublisher();
                    break;
                case 5:
                    printBooks();
                    break;
                case 6:
                    chooseBooks();
                    break;
                case 7:
                    insertBook();
                    break;
                case 8:
                    insertPublisher();
                    break;
                case 9:
                    removeBook();
                    break;
                default:
                    break;
            }
        }else{
            System.out.println("Please enter a valid choice");
            mainDis();
        }
        
    }
    void printWritingsGroups()
    {
        String sql = "SELECT GroupName FROM WritingGroups";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.getFetchSize() > 0){
                System.out.println("Writing Group Names: ");
                while(rs.next())
                {
                    String names = rs.getString("GroupName");
                    System.out.println(names);
                }
            }
            else
                System.out.println("Sorry, there are no Writing Groups in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    void chooseWritingGroups()
    {
       System.out.println("Enter the Writing Group Name you wish to find");
       String name = scan.next();
       String sql = "SELECT  * FROM WritingGroups WHERE GroupName = '" + name +"'";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
            if(rs.getFetchSize() > 0){
                while(rs.next())
                {
                    String gName = rs.getString("GroupName");
                    String writer = rs.getString("HeadWriter");
                    String year = rs.getString("YearFormed");
                    String subject = rs.getString("Subject");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(gName), dispNull(writer), dispNull(year), dispNull(subject));
                }
            }
            else
                System.out.println("Sorry, there are no Writing Groups in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        System.out.println("\n\n1. Back to main menu");
        System.out.println("2. Try again");
        System.out.println("3. Exit");
        int choice = scan.nextInt();
        
        while(!validChoice(1,3,choice))
        {
            System.out.println("Enter a valid choice");
            choice = scan.nextInt();
        }
        if(choice == 1)
            mainDis();
        else if(choice == 2)
            chooseWritingGroups();
   
    }
    String dispNull (String input) 
    {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
    //checks if values are between low and high
    boolean validChoice(int low, int high, int choice)
    {
        return (choice >= low && choice <= high);
    }
    void printPublishers()
    {
        String sql = "SELECT PublisherName FROM Publishers";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.getFetchSize() > 0){
                System.out.println("Publisher Names: ");
                while(rs.next())
                {
                    String names = rs.getString("PublisherName");
                    System.out.println(names);
                }
            }
            else
                System.out.println("Sorry, there are no Publishers in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    void choosePublisher()
    {
       System.out.println("Enter the Writing Group Name you wish to find");
       String name = scan.next();
       String sql = "SELECT  * FROM Publishers WHERE PublisherName = '" + name +"'";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
            if(rs.getFetchSize() > 0){
                while(rs.next())
                {
                    String gName = rs.getString("PublisherName");
                    String writer = rs.getString("PublisherAddress");
                    String year = rs.getString("PublisherPhone");
                    String subject = rs.getString("PublisherEmail");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(gName), dispNull(writer), dispNull(year), dispNull(subject));
                }
            }
            else
                System.out.println("Sorry, there are no Writing Groups in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        System.out.println("\n\n1. Back to main menu");
        System.out.println("2. Try again");
        System.out.println("3. Exit");
        int choice = scan.nextInt();
        
        while(!validChoice(1,3,choice))
        {
            System.out.println("Enter a valid choice");
            choice = scan.nextInt();
        }
        if(choice == 1)
            mainDis();
        else if(choice == 2)
            chooseWritingGroups();
    }
    void printBooks()
    {
        String sql = "SELECT BookTitle FROM Books";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.getFetchSize() > 0){
                System.out.println("Book Names: ");
                while(rs.next())
                {
                    String names = rs.getString("BookTitle");
                    System.out.println(names);
                }
            }
            else
                System.out.println("Sorry, there are no Books in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
    }
    void chooseBooks()
    {
        
    }
    void insertBook()
    {
        
    }
    void insertPublisher()
    {
        //edit
    }
    void removeBook()
    {
        
    }
}
