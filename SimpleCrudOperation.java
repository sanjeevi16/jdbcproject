package simpleproject.s;
import java.sql.*;
import java.util.Scanner;

public class SimpleCrudOperation {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
	     Scanner s = new Scanner(System.in);
	     System.out.println("select what operation you want do on database ");
	     System.out.println("1.insertdata ");
	     System.out.println("2.displaydata ");
         System.out.println("3.deletedata ");
	     
	     Class.forName("com.mysql.cj.jdbc.Driver");
    	 Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/office?user=root&password=root");
	     int val = s.nextInt();
	     
	     if(val == 1 )
	     {
	    	CallableStatement cs = c.prepareCall("call office.insertdata(?,?,?,?)");
	    	
	    	System.out.println("Enter employee ID :: ");
	    	cs.setInt(1,s.nextInt());
	    	
	    	System.out.println("Enter employee Name :: ");
	    	cs.setString(2,s.next());
	    	
	    	System.out.println("Enter employee email :: ");
	    	cs.setString(3,s.next());
	    	
	    	System.out.println("enter employee contact :: ");
	    	cs.setLong(4,s.nextLong());
	    	
	    	boolean b = cs.execute();
	    	
	    	System.out.println(cs.getUpdateCount()+" rows inserted successfully ");
	    	c.close();
	     }
	     else if(val == 2)
	     {
	    	 CallableStatement cs = c.prepareCall("call office.displaydata(?)");
	    	 System.out.println("enter employee id that details you want display :: ");
	    	 cs.setInt(1,s.nextInt());
	    	 
	    	 boolean b = cs.execute();
	    	 ResultSet rs = cs.getResultSet();
	    	 rs.next();
	    	 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getLong(4));
	    	 c.close();
	     }
	     else if(val == 3 )
	     {
	    	 CallableStatement cs = c.prepareCall("call office.deletedata(?)");
	    	 System.out.println("enter employee id that you want to delete ");
	    	 cs.setInt(1,s.nextInt());
	    	 
	    	 cs.execute();
	    	 System.out.println(cs.getUpdateCount()+" rows deleted successfully");
	    	 
	     }
	     else
	     {
	    	 System.out.println("Please enter valid number ");
	     }
	     s.close();
	}

}
