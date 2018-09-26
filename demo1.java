/*import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class demo1 extends JFrame{
    JButton button ;
    JLabel label;
    JTextField jtf;

    public demo1(){
    super("retrieve image from database in java");
    
    button = new JButton("Retrieve");
    button.setBounds(250,300,100,40);
    
    jtf = new JTextField();
    jtf.setBounds(360,310,100,20);
    
    label = new JLabel();
    label.setBounds(10,10,670,250);
    
    add(button);
    add(label);
    add(jtf);
    
    button.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
        
            try{
            	Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/te3375db","te3375","root");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("select img from FIR where img = '"+jtf.getText()+"'");
                if(rs.next()){
                    byte[] img = rs.getBytes("img");



                    //Resize The ImageIcon
                    ImageIcon image = new ImageIcon(img);
                    Image im = image.getImage();
                    Image myImg = im.getScaledInstance(label.getWidth(), label.getHeight(),Image.SCALE_SMOOTH);
                    ImageIcon newImage = new ImageIcon(myImg);
                    label.setIcon(newImage);
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "No Data");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        
        }
    });
    
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.decode("#bdb76b"));
    setLocationRelativeTo(null);
    setSize(700,400);
    setVisible(true);
    } 
     
    public static void main(String[] args){
        new demo1();
    }
   }*/


import java.sql.*;
import java.io.*;
public class demo1{
	public static void main(String[] args){
		System.out.println("Retrive Image Example!");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "te3375db";
		String userName = "te3375";
		String password = "root";
		Connection con = null;
		try{
			Class.forName(driverName);
			con = DriverManager.getConnection(url+dbName,userName,password);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select img from FIR");
			int i = 0;
			while (rs.next()) {
				InputStream in = rs.getBinaryStream(1);
				OutputStream f = new FileOutputStream(new File("test"+i+".png"));
				i++;
				int c = 0;
				while ((c = in.read()) > -1) {
					f.write(c);
				}
				f.close();
				in.close();
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}
