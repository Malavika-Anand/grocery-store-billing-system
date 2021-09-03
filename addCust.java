import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
// import java.util.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class addCust extends JFrame implements ActionListener {
    
    private  JLabel name,phone,addr,l1;
    private  JTextField name1,phone1,addr1;
    private  JButton addcust;
    Container con=null;
    String cname="",cphno="",caddr="";

    addCust() {
        super("Add Customer");
        con = getContentPane();
        con.setLayout(null);
        con.setBackground(Color.LIGHT_GRAY);

        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);

        l1 = new JLabel("ADD CUSTOMER PAGE");
        l1.setBounds(550,30, 400,150);

        con.add(l1);

        Font font = new Font("Verdana", Font.BOLD, 16);
        l1.setFont(font);
        l1.setForeground(Color.BLACK);
        
        name=new JLabel("Enter Name:");
        name.setBounds(350,200,150,50);
        name.setFont(font);
        name.setForeground(Color.BLACK);
        name1=new JTextField(200);
		name1.setBounds(725,200,250,40);
        name1.setFont(font);
        name1.setForeground(Color.BLACK);

        phone=new JLabel("Enter Phone Number:");
		phone.setBounds(350,300,250,40);
        phone.setFont(font);
        phone.setForeground(Color.BLACK);
		phone1=new JTextField(200);
		phone1.setBounds(725,300,250,50);
        phone1.setFont(font);
        phone1.setForeground(Color.BLACK);

        addr=new JLabel("Enter Address:");
		addr.setBounds(350,400,350,40);
        addr.setFont(font);
        addr.setForeground(Color.BLACK);
		addr1=new JTextField(200);
		addr1.setBounds(725,400,250,50);
        addr1.setFont(font);
        addr1.setForeground(Color.BLACK);
        
        addcust=new JButton("Add Customer");
		addcust.setBounds(550,550,225,30);
		addcust.addActionListener(this);
        addcust.setFont(font);
        addcust.setForeground(Color.BLACK);
        addcust.setBackground(Color.LIGHT_GRAY);

        JLabel jl = new JLabel();
        jl.setBounds(700,500,150,30);

        con.add(name);
		con.add(name1);
		con.add(phone);
		con.add(phone1);
		con.add(addr);
		con.add(addr1);
		con.add(addcust);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==addcust)
            try
                {
                    String r; 
                    String cname = name1.getText();
                    String cphno = phone1.getText();
                    String caddr = addr1.getText();
                    BufferedReader br = new BufferedReader (new FileReader("customer-list.txt"));
                    while((r = br.readLine())!= null) {
                        String[] result = r.split("\\|");
                        String ph1 = result[0];
                        if(cphno.equals(ph1)) {
                            showMessageDialog(null, "An entry already exits for this number with name: "+result[1]);
                        	br.close();
                            return;
                        }
                    } 
                    PrintWriter pw = new PrintWriter(new FileOutputStream(new File("customer-list.txt"),true));
                    String b = cphno + "|" + cname + "|" + caddr + "|";
                    pw.println(b);
                    pw.flush();
                    pw.close();
                    showMessageDialog(null, "Entry Added!");
                    br.close();
                    return;
                }
            catch(Exception e)
                {System.out.println(e);}
    }
    public static void main(String args[]) {
        addCust ac = new addCust();
        ac.setSize(1035,740);
        ac.setVisible(true);
    }
}