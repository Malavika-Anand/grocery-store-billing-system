import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class home extends JFrame implements ActionListener {  
    
    private JButton b0,b1,b2;

    home() {
        super("Super Market Billing System");
		Container con=getContentPane();
        con.setLayout(null);
        con.setBackground(Color.LIGHT_GRAY);

        JLabel l1;
        l1=new JLabel("Welcome to Super Market Billing");
        l1.setBounds(520,100, 400,150);
        con.add(l1);
        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);
        Font font = new Font("Verdana", Font.BOLD, 20);
        l1.setFont(font);
        l1.setForeground(Color.BLACK);
		
		b0=new JButton("Add Customer");
		b0.addActionListener(this);
		b0.setBounds(350,275,175, 50);
        Font font1 = new Font("Verdana", Font.BOLD, 16);
        b0.setFont(font1);
        b0.setForeground(Color.BLACK);
        b0.setBackground(Color.LIGHT_GRAY);

		b1=new JButton("Billing");
		b1.addActionListener(this);
		b1.setBounds(650,275, 100, 50);
        Font font2 = new Font("Verdana", Font.BOLD, 16);
        b1.setFont(font2);
        b1.setForeground(Color.BLACK);
        b1.setBackground(Color.LIGHT_GRAY);

		b2 = new JButton("Add Item");
		b2.addActionListener(this);
		b2.setBounds(870,275, 160, 50);
        Font font3 = new Font("Verdana", Font.BOLD, 16);
        b2.setFont(font3);
        b2.setForeground(Color.BLACK);
        b2.setBackground(Color.LIGHT_GRAY);
		
        con.add(b0);
		con.add(b1);
		con.add(b2);
    }
    
    public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b0)
		{
			this.dispose();
			addCust ac=new addCust();
			ac.setSize(1035,790);
			ac.setVisible(true);
		}

		if(ae.getSource()==b1)
		{
			this.dispose();
			billing bl=new billing();
			bl.setSize(1035,790);
			bl.setVisible(true);
		}

		if(ae.getSource()==b2)
		{
			this.dispose();
			addItem b2=new addItem();
			b2.setSize(1035,790);
			b2.setVisible(true);
		}
	}
	
	public static void main(String args[])
	{
        home h=new home();
		h.setSize(1035,790);
		h.setVisible(true);
	}
}