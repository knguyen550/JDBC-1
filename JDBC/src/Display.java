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
    Connection conn = null;
    static final String displayFormat="%-15s%-15s%-15s%-15s\n";
    
    Display(Statement state, Connection con)
    {
        stmt = state;
        conn = con;
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
        
        int choice = getIntRange(1,10);
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
        System.out.println("\n");
    }
    void printWritingsGroups()
    {
        String sql = "SELECT GroupName FROM WritingGroups";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                System.out.println("Writing Group Names: ");
                
                do{
                    String names = rs.getString("GroupName");
                    System.out.println(names);
                }while(rs.next());
            }
            else
                System.out.println("\nSorry, there are no Writing Groups in your database\n");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        System.out.println("\n");
        mainDis();
    }
    void chooseWritingGroups()
    {
       System.out.println("Enter the Writing Group Name you wish to find");
       String name = "";
       scan = scan.useDelimiter("\n");
       name = scan.next();
     
       String sql = "SELECT * FROM WritingGroups WHERE GroupName = '" + name + "'";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
                System.out.printf(displayFormat, "Group Name", "Head Writer", "Year Formed", "Subject");
                
                do{
                    String gName = rs.getString("GroupName");
                    String writer = rs.getString("HeadWriter");
                    String year = rs.getString("YearFormed");
                    String subject = rs.getString("Subject");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(gName), dispNull(writer), dispNull(year), dispNull(subject));
                }while(rs.next());
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
        
        int choice = getIntRange(1,3);
        
        System.out.println("\n");
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
    int getIntRange( int low, int high ) {
        Scanner in = new Scanner( System.in );
        int input = 0;
        boolean valid = false;
        while( !valid ) {
                if( in.hasNextInt() ) {
                        input = in.nextInt();
                        if( input <= high && input >= low ) {
                                valid = true;
                        } else {
                                System.out.println( "Invalid Range." );
                        }
                } else {
                        in.next(); //clear invalid string
                        System.out.println( "Invalid Input." );
                }
        }
        return input;
    }
    void printPublishers()
    {
        String sql = "SELECT PublisherName FROM Publishers";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                System.out.println("Publisher Names: ");
                
                do{
                    String names = rs.getString("PublisherName");
                    System.out.println(names);
                }while(rs.next());
            }
            else
                System.out.println("Sorry, there are no Publishers in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        System.out.println("\n");
        mainDis();
    }
    void choosePublisher()
    {
       System.out.println("Enter the Publisher Name you wish to find");
       String name = "";
       scan = scan.useDelimiter("\n");
       name = scan.next();
       String sql = "SELECT  * FROM Publishers WHERE PublisherName = '" + name +"'";
        try{
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next()){
                System.out.printf(displayFormat, "Publisher Name", "Publisher Address", "Publisher Phone", "Publisher Email");
                
                do{
                    String gName = rs.getString("PublisherName");
                    String writer = rs.getString("PublisherAddress");
                    String year = rs.getString("PublisherPhone");
                    String subject = rs.getString("PublisherEmail");

                    //Display values
                    System.out.printf(displayFormat, 
                            dispNull(gName), dispNull(writer), dispNull(year), dispNull(subject));
                }while(rs.next());
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
        
        int choice = getIntRange(1,3);
        
        System.out.println("\n");
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
            if(rs.next()){
                System.out.println("Book Names: ");
                do{
                    String names = rs.getString("BookTitle");
                    System.out.println(names);
                }
                while(rs.next());
                
            }
            else
                System.out.println("Sorry, there are no Books in your database");
        }
        catch(SQLException err){
            System.out.println(err.getMessage());
        }
        System.out.println("\n");
        mainDis();
    }
    void chooseBooks()
    {
        
    }
    void insertBook()
    {
        
    }
    void insertPublisher()
    {
        
    }
    void removeBook()
    {
        
    }
}
