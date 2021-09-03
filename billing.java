import java.io.*;
// import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
// import java.util.Random;
import static javax.swing.JOptionPane.showMessageDialog;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class billing extends JFrame implements ActionListener {

	private JLabel phno, name, addr, iid, l1, qty;
	private JTextArea items;
	private JTextField phno1, name1, addr1, iid1, qty1;
	private JButton check, add, bill;
	private int sumTotal=0;
	Container con=null;
	String cphno="", iiid="", caddr="", cname="", iname="", icost="";

	billing() {
		super("Billing");
		con = getContentPane();
		con.setLayout(null);
		con.setBackground(Color.LIGHT_GRAY);

		con.setSize(300,300);
		con.setLayout(null);
		con.setVisible(true);

		l1 = new JLabel("BILLING PAGE");
		l1.setBounds(650,30, 400,150);
		
		con.add(l1);

		Font font = new Font("Verdana", Font.BOLD, 16);
		l1.setFont(font);
		l1.setForeground(Color.BLACK);

		phno = new JLabel("Enter Phone Number:");
		phno.setBounds(40,170, 250,40);
		phno.setFont(font);
		phno.setForeground(Color.BLACK);
		phno1 = new JTextField(200);
		phno1.setBounds(250,170,150,30);
		phno1.setFont(font);
		phno1.setForeground(Color.BLACK);

		check = new JButton("Check");
		check.setBounds(300,210, 90,30);
		check.addActionListener(this);
        check.setFont(font);
        check.setForeground(Color.BLACK);
        check.setBackground(Color.LIGHT_GRAY);

		name=new JLabel("Enter Name:");
        name.setBounds(120,320,250,40);
        name.setFont(font);
        name.setForeground(Color.BLACK);
        name1=new JTextField(200);
		name1.setBounds(250,320,150,30);
        name1.setFont(font);
        name1.setForeground(Color.BLACK);

		addr=new JLabel("Enter Address:");
		addr.setBounds(100,420,250,40);
        addr.setFont(font);
        addr.setForeground(Color.BLACK);
		addr1=new JTextField(200);
		addr1.setBounds(250,420,150,30);
        addr1.setFont(font);
        addr1.setForeground(Color.BLACK);

		iid=new JLabel("Enter IID:");
		iid.setBounds(550,160, 250,40);
        iid.setFont(font);
        iid.setForeground(Color.BLACK);
		iid1=new JTextField(200);
		iid1.setBounds(650,160,150,30);
        iid1.setFont(font);
        iid1.setForeground(Color.BLACK);

        qty=new JLabel("Quantity:");
        qty.setBounds(550,210,250,40);
        qty.setFont(font);
        qty.setForeground(Color.BLACK);
		qty1=new JTextField(200);
		qty1.setBounds(650,210,150,30);
        qty1.setFont(font);
        qty1.setForeground(Color.BLACK);

		add = new JButton("Add Item");
		add.setBounds(630,295, 115,30);
		add.addActionListener(this);
        add.setFont(font);
        add.setForeground(Color.BLACK);
        add.setBackground(Color.LIGHT_GRAY);

		bill = new JButton("Bill");
		bill.setBounds(640,395, 80,30);
		bill.addActionListener(this);
        bill.setFont(font);
        bill.setForeground(Color.BLACK);
        bill.setBackground(Color.LIGHT_GRAY);

		items = new JTextArea();
		items.setText("");
		items.setBounds(900, 160, 400, 400);
		items.setFont(font);
        items.setForeground(Color.BLACK);
		items.setBackground(Color.WHITE);
		
		con.add(items);
		con.add(bill);
		con.add(add);
		con.add(iid);
		con.add(iid1);
		con.add(qty);
		con.add(qty1);
		con.add(addr);
		con.add(addr1);
		con.add(name);
		con.add(name1);
		con.add(check);
		con.add(phno);
		con.add(phno1);
	}

	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==check) {
			try {
				String cphno = phno1.getText();
				// String cname = "", caddr = "";
				BufferedReader br = new BufferedReader(new FileReader("customer-list.txt"));
				String ph = "", r;
				while((r= br.readLine()) !=null) {
					String[] result = r.split("\\|");
					ph=result[0];
					if(ph.equals(cphno)) {
						name1.setText(result[1]);
						addr1.setText(result[2]);
						showMessageDialog(null,"Entry Found");
						br.close();
						return;
					}
				}
				showMessageDialog(null,"Entry Not Found");
				br.close();
			}
			catch(Exception e)
                {System.out.println(e);}
		}
		else if(ae.getSource()==add) {
			try {
				String pid = iid1.getText();
				BufferedReader br = new BufferedReader(new FileReader("item-list.txt"));
				String prod = "", r;
				while((r= br.readLine()) !=null) {
					String[] result = r.split("\\|");
					prod=result[0];
					if(prod.equals(pid)) {
						try {
							int quant = (qty1.getText() == "") ? 1 : Integer.parseInt(qty1.getText());
							items.setText(items.getText()+"\n"+pid+"|"+result[1]+"|"+quant+"|"+result[2]+"|");
							sumTotal += Integer.parseInt(result[2])*quant;
							br.close();
							return;
						}
						catch(NumberFormatException e) {
							showMessageDialog(null,"Enter quantity!");
							return;
						}
					}
				}
				showMessageDialog(null,"Entry Not Found");
				br.close();
			}
			catch(Exception e)
                {System.out.println(e);}
		}
		else if(ae.getSource()==bill) {
			try {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ss_mm_HH_dd_MM_yyyy");
				LocalDateTime now = LocalDateTime.now();
				String billingFile = phno1.getText()+"_"+(dtf.format(now))+".txt";
				PrintWriter pw = new PrintWriter(new FileOutputStream(new File(billingFile),true));
				PrintWriter pw_ = new PrintWriter(new FileOutputStream(new File("transactions.txt"),true));
				pw.println("########\tRaju's Market\t########");
				pw.println();
				pw.println();
				pw.println("Customer Info:");
				pw.println("Phone:"+phno1.getText()+"\nName:"+name1.getText()+"\nAddress:"+addr1.getText()+"\n");
				pw.println();
				pw.println();
				pw.println("Name|Price|Quantity|Total|");
				pw.println();
				pw.println(items.getText());
				pw.println();
				pw.println("Sum Total: Rs."+sumTotal);
				pw.println();
				pw.println();
				pw.println("Thank You for choosing Raju's Market!");
				pw_.println(phno1.getText()+"|"+(dtf.format(now))+"|"+sumTotal+"|");
				pw.close();
				pw_.close();
				showMessageDialog(null,"Bill Generated!");
				return;
			}
			catch(Exception e)
                {System.out.println(e);}
		}
	}

	public static void main (String args[]) {
		billing bl = new billing();
		bl.setSize(1035,740);
		bl.setVisible(true);
	} 
}
