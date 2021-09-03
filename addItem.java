import java.io.*;
// import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class addItem extends JFrame implements ActionListener {  
    
    private  JLabel l1, iid, iname, icost;
    private  JTextField iid1, iname1, icost1;
    private  JButton additem, moditem;
    Container con=null;
    String cname="",cphno="",caddr="";
    
    addItem() {
        super("Items");
        con = getContentPane();
        con.setLayout(null);
        con.setBackground(Color.LIGHT_GRAY);

        con.setSize(300,300);
        con.setLayout(null);
        con.setVisible(true);

        l1 = new JLabel("ITEMS PAGE");
        l1.setBounds(550,30, 400,150);

        con.add(l1);

        Font font = new Font("Verdana", Font.BOLD, 16);
        l1.setFont(font);
        l1.setForeground(Color.BLACK);

        iid=new JLabel("Enter Item ID:");
        iid.setBounds(350,200,150,50);
        iid.setFont(font);
        iid.setForeground(Color.BLACK);
        iid1=new JTextField(200);
		iid1.setBounds(725,200,250,40);
        iid1.setFont(font);
        iid1.setForeground(Color.BLACK);

        iname=new JLabel("Enter Item Name:");
		iname.setBounds(350,300,250,40);
        iname.setFont(font);
        iname.setForeground(Color.BLACK);
		iname1=new JTextField(200);
		iname1.setBounds(725,300,250,50);
        iname1.setFont(font);
        iname1.setForeground(Color.BLACK);

        icost=new JLabel("Enter Item Cost:");
		icost.setBounds(350,400,350,40);
        icost.setFont(font);
        icost.setForeground(Color.BLACK);
		icost1=new JTextField(200);
		icost1.setBounds(725,400,250,50);
        icost1.setFont(font);
        icost1.setForeground(Color.BLACK);
        
        additem=new JButton("Add Item");
		additem.setBounds(350,600,225,30);
		additem.addActionListener(this);
        additem.setFont(font);
        additem.setForeground(Color.BLACK);
        additem.setBackground(Color.LIGHT_GRAY);

        moditem=new JButton("Modify Item");
		moditem.setBounds(725,600,225,30);
		moditem.addActionListener(this);
        moditem.setFont(font);
        moditem.setForeground(Color.BLACK);
        moditem.setBackground(Color.LIGHT_GRAY);

        con.add(iid);
		con.add(iid1);
		con.add(iname);
		con.add(iname1);
		con.add(icost);
		con.add(icost1);
		con.add(additem);
        con.add(moditem);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==additem) {
        	try
	        	{
			        String r; 
			        String pid = iid1.getText();
			        String pname = iname1.getText();
			        String price = icost1.getText();
					BufferedReader br1 = new BufferedReader(new FileReader("item-list.txt"));
					while((r = br1.readLine()) != null)
					{
						String[] result = r.split("\\|");
						String pidx = result[0];
						if(pid.equals(pidx))
						{
	                    	showMessageDialog(null, "An entry already exists for the given product ID under the name '"+result[1]+"' for price Rs"+ result[2]);
							br1.close();
							return;
						}
					}
					br1.close();
					PrintWriter pw = new PrintWriter(new FileOutputStream(new File("item-list.txt"),true));
					String b = pid + "|" + pname + "|" + price + "|";
					pw.println(b);
					pw.flush();
					pw.close();
                    showMessageDialog(null, "Entry Added!");
					return;
	        	}
	        catch (Exception e)
                {System.out.println(e);}
        }
        else if(ae.getSource()==moditem) {
			try{
		        String pid = iid1.getText();
		        String pname = iname1.getText();
		        String price = icost1.getText();
		        String r,pid1; 
		        File file = new File("item-list.txt");
				BufferedReader br = new BufferedReader(new FileReader(file));
				File temp = new File("temp.txt");
				PrintWriter pw = new PrintWriter(temp);
				while((r= br.readLine()) !=null)
				{	
					String[] result = r.split("\\|");
					pid1=result[0];	
					if(pid1.equals(pid))
					{
						String b = pid+"|"+pname+"|"+price+"|";
						pw.println(b);
					}
					else
						pw.println(r);
				}
				pw.flush();
				pw.close();
				br.close();	
				file.delete();
				temp.renameTo(file);
                showMessageDialog(null, "Entry Modified!");
        	}catch (Exception e)
                {System.out.println(e);}
        }
    }

    public static void main(String args[]) {
        addItem ai = new addItem();
        ai.setSize(1035,740);
        ai.setVisible(true);
    }
}
