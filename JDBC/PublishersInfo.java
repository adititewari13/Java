package databasehandling;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class PublishersInfo extends JFrame implements ActionListener
{
    
     JPanel p;
     JLabel h,l1,l2,l3,l4,l5,l6,l7;
     JTextField t1,t2,t3,t4,t5,t6,t7;
     JButton b1,b2,b3;
     Connection con;
     ResultSet res;
     Statement stat;
     String url,user,pass;
    public PublishersInfo()
    {
        try
        {
              url="jdbc:derby://localhost:1527/Publisher";
              user="Publishers";
              pass="pass";
             
        }
        catch(Exception e)
        {
            System.out.println("Err"+e.getMessage());
        }
    }
     public void connect()
    {
        try{
               con=DriverManager.getConnection(url, user, pass);
           }
        catch(SQLException e)
        {
            System.out.println("Error in connection..."+e.getMessage());
        }
        
    }
    
     public void pane ()
    {
        
        h= new JLabel("PUBLISHER'S INFORMATION");
        p=new JPanel();
        l1=new JLabel("PUBLISHER'S ID");
        l2=new JLabel("PUBLISHER'S NAME");
        l3=new JLabel("PHONE NO.");
        l4=new JLabel("ADDRESS");
        l5=new JLabel("CITY");
        l6=new JLabel("STATE");
        l7=new JLabel("ZIP");
        
        t1=new JTextField(50);
        t2=new JTextField(50);
        t3=new JTextField(50);
        t4=new JTextField(50);
        t5=new JTextField(50);
        t6=new JTextField(50);
        t7=new JTextField(50);
        
        p.setLayout(null);
        h.setBounds(250,35, 200,40);
        p.add(h);
        
        l1.setBounds(75, 90,200,30);
        t1.setBounds(400,90,100,25);
        p.add(l1);
        p.add(t1);
        
        l2.setBounds(75, 120,200,30);
        t2.setBounds(400,120,200,25);
        p.add(l2);
        p.add(t2);
        
        l3.setBounds(75, 150,200,30);
        t3.setBounds(400,150,150,25);
        p.add(l3);
        p.add(t3);
        
        l4.setBounds(75, 180,200,30);
        t4.setBounds(400,180,250,25);
        p.add(l4);
        p.add(t4);
        
        l5.setBounds(75, 210,200,30);
        t5.setBounds(400,210,200,25);
        p.add(l5);
        p.add(t5);
        
        l6.setBounds(75, 240,200,30);
        t6.setBounds(400,240,200,25);
        p.add(l6);
        p.add(t6);
        
        l7.setBounds(75, 270,200,30);
        t7.setBounds(400,270,200,25);
        p.add(l7);
        p.add(t7);
        
        
        b1=new JButton("INSERT");
        b2=new JButton("EXIT");
        b3=new JButton("VIEW");
        b1.setBounds(175, 350, 100, 30);
        b2.setBounds(325, 350, 100, 30);
        b3.setBounds(475, 350, 100, 30);
       
        p.add(b1);
        p.add(b2);
        p.add(b3);
      
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
       
        add(p);
        setVisible(true);
      
    }
    
    public static void main(String [] args)
    {
        PublishersInfo pub= new PublishersInfo();
        pub.pane();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       if(e.getActionCommand()=="INSERT")
       {
           try{
           connect();
           PreparedStatement pstat=con.prepareStatement("insert into  untitled values(?,?,?,?,?,?,?)");
                      pstat.setString(1,t1.getText());
                      pstat.setString(2,t2.getText());
                      pstat.setString(3, t3.getText());
                      pstat.setString(4,t4.getText());
                      pstat.setString(5,t5.getText());
                      pstat.setString(6, t6.getText());
                      pstat.setString(7, t7.getText());
                      int rowsaff=pstat.executeUpdate();
                      if(rowsaff>0)
                         JOptionPane.showMessageDialog(null,"Information Stored");
                      else
                         JOptionPane.showMessageDialog(null,"Failed To Store Publisher's  Information ");
                  }
                  catch(SQLException ee)
                  {
                          System.out.println("Error..."+ee.getMessage());
                  }
       }
       if(e.getActionCommand()=="VIEW")
       {
           try{   
        connect();
            stat=con.createStatement();
            res= stat.executeQuery("select * from publishers.untitled");
        while(res.next())
        {
            System.out.println("Publisher's ID:"+res.getString(1));
            System.out.println("Publisher's Name:"+res.getString(2));
            System.out.println("Phone:"+res.getString(3));
            System.out.println("Address:"+res.getString(4));
            System.out.println("City:"+res.getString(5));
            System.out.println("State:"+res.getString(6));
            System.out.println("ZIP:"+res.getString(7));
            System.out.println();
            
        }
        }
        catch(SQLException ae)
        {
            System.out.println("Error..."+ae.getMessage());
        }
       }
       if(e.getActionCommand()=="EXIT")
       {
           System.exit(0);
       }
    }

    
}
