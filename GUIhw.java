import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.windows.resources.windows;

import sun.misc.FormattedFloatingDecimal.Form;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class GUIhw {

	private JFrame frame;
	private JPanel panel_1;
	private JPanel panel_2;
	private JButton button;
	public JList list;
	public DefaultListModel listModel;
	private JLabel label;
	private JList itemList;
	private DefaultListModel items;
	private DefaultListModel number_items;
	private JList number_itemList;
	private JButton buttonin;
	private JButton buttonout;
	private JList shoppingList;
	private DefaultListModel shopping;
	private JPanel number_panel;
	private JButton[] jbnButton;
	private HashSet<Integer> HSet;
	private TreeSet<Integer> Tset;
	private static GUIhw window;
	private JTextField textField_13;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private TreeSet<String> TsetMoney;
	private int count;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new GUIhw();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GUIhw() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 932, 58);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		label = new JLabel("\u5927\u6A02\u900F ");
		label.setBounds(401, 13, 105, 32);
		label.setFont(new Font("新細明體", Font.PLAIN, 32));
		panel.add(label);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 431, 932, 322);
		frame.getContentPane().add(panel_1);
		
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.LINE_AXIS));
		panel_2 = new JPanel();
		panel_2.setBounds(0, 394, 932, 37);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		button = new JButton("\u5FEB\u9078");//快選
		button.setFont(new Font("新細明體", Font.PLAIN, 20));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Tset!=null)//清除之前快選的號碼
				{
					Object[] trr=Tset.toArray();
					int tj=0;
					for(Object j:trr)
					{
						tj=(int)j;
						jbnButton[tj-1].setBackground(new Color(238, 238, 238));
					}
				}
				randomNumber();
				GUIhw.MyThread mt1=window.new MyThread();
				mt1.start();
				GUIhw.MyThread2 mt2=window.new MyThread2();
				mt2.start();
				
			}
		});
		panel_2.add(button);
		
		number_panel = new JPanel();
		number_panel.setBounds(0, 57, 932, 337);
		frame.getContentPane().add(number_panel);
		number_panel.setLayout(null);

		
		
		//放入快選數字
		items = new DefaultListModel();
		itemList = new JList(items);
		itemList.setFont(new Font("新細明體", Font.PLAIN, 20));
		itemList.setBorder(new EmptyBorder(0, 10, 0, 10));
        itemList.setVisibleRowCount(10);
        itemList.setFixedCellHeight(20);
        itemList.setFixedCellWidth(100);
        itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane list1 = new JScrollPane(itemList);
        list1.setBounds(14, 34, 198, 253);
        
        
         //存入保存數字
            shopping = new DefaultListModel();
            shoppingList = new JList(shopping);
            shoppingList.setFont(new Font("Dialog", Font.PLAIN, 20));
            shoppingList.setVisibleRowCount(10);
            shoppingList.setFixedCellHeight(20);
            shoppingList.setFixedCellWidth(140);
            shoppingList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane list2 = new JScrollPane(shoppingList);
            list2.setBounds(297, 34, 219, 243);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBounds(215, 34, 74, 178);
            buttonPanel.setLayout(null);

            buttonin = new JButton(">>");
            buttonin.setFont(new Font("新細明體", Font.PLAIN, 20));
            buttonin.setBounds(10, 13, 60, 27);
            buttonin.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					int[] fromindex = itemList.getSelectedIndices();
		            Object[] from = itemList.getSelectedValues();
		            int j = 0;
		            for(j = 0; j < from.length; j++)
		            {
		            	shopping.addElement(from[j]);
		            }
		            for(j = (fromindex.length-1); j >=0; j--)
		            {
		                items.remove(fromindex[j]);
		            }
					
				}
			});
            buttonPanel.add(buttonin);
            buttonout = new JButton("<<");
            buttonout.setFont(new Font("新細明體", Font.PLAIN, 20));
            buttonout.setBounds(10, 52, 60, 27);
            buttonout.addActionListener(new ActionListener() {
				int j=0;
				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] to = shoppingList.getSelectedValues();
		            int[] toindex = shoppingList.getSelectedIndices();

		            for(j = 0; j < to.length; j++)
		            {
		                items.addElement(to[j]);
		            }
		            for(j = (toindex.length-1); j >=0; j--)
		            {
		            	shopping.remove(toindex[j]);
		            }
					
				}
			});
            buttonPanel.add(buttonout);
            JPanel bottomPanel = new JPanel();
            bottomPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 10, 0, 10), new EmptyBorder(0, 10, 0, 10)));
            bottomPanel.setLayout(null);
            bottomPanel.add(list1);//快選數字
            bottomPanel.add(buttonPanel);
            bottomPanel.add(list2);//保存數字
            panel_1.add(bottomPanel);
            JButton button_1 = new JButton("\u5C0D\u734E");//對獎
            button_1.setFont(new Font("新細明體", Font.PLAIN, 20));
            button_1.addActionListener(new ActionListener() {
				
				

				

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						
						String texeplaneStr="";
						
						String t1="",t2="",t3="",t4="",t5="",t6="",ts="";
						t1=textField_7.getText().replaceAll(" ", "");t2=textField_8.getText().replaceAll(" ", "");t3=textField_9.getText().replaceAll(" ", "");
						t4=textField_10.getText().replaceAll(" ", "");t5=textField_11.getText().replaceAll(" ", "");t6=textField_12.getText().replaceAll(" ", "");
						ts=textField_13.getText().replaceAll(" ", "");
						int it1=t1.length();int it2=t2.length();int it3=t3.length();int its=ts.length();
						int it4=t4.length();int it5=t5.length();int it6=t6.length();
						if(it1!=0&&it2!=0&&it3!=0&&it4!=0&&it5!=0&&it6!=0&&its!=0){
							TsetMoney=new TreeSet<String>();
							
							TsetMoney.add(t1);TsetMoney.add(t2);TsetMoney.add(t3);TsetMoney.add(t4);TsetMoney.add(t5);TsetMoney.add(t6);
							int size = shoppingList.getModel().getSize();
						
						String[] str=new String[size]; 
						for(int i = 0; i < size; i++) {
						    str[i]=(String) shoppingList.getModel().getElementAt(i);
							str[i]=str[i].substring(1,str[i].length()-1);//去頭去尾
							str[i]=str[i].replaceAll(" ", "");//消除空白
							String[] aa=str[i].split(",");//每項目裡的值
							int good=0;
							boolean ifsp=false;
							count=0;
							for(int j=0;j<aa.length;j++){
								
								good=prize(aa[j]);//比對有幾個相同的數字
								if(aa[j].equals(ts))
								{
									ifsp=true;//有號碼與特別獎號相同
								}
							}
							
							String showstr="";
							switch (good) {
							case 6:
								showstr="恭喜你中了頭獎,對中的號碼是:"+str[i];
								break;
							case 5:
								if(ifsp){
								showstr="恭喜你中了二獎,對中的號碼是:"+str[i];
								}else{
								showstr="恭喜你中了三獎,對中的號碼是:"+str[i];
								}
								break;
							case 4:
								if(ifsp){
									showstr="恭喜你中了四獎,對中的號碼是:"+str[i];
									}else{
									showstr="恭喜你中了五獎,對中的號碼是:"+str[i];
									}
								break;
							case 3:
								if(ifsp){
									showstr="恭喜你中了六獎,對中的號碼是:"+str[i];
									}else{
									showstr="恭喜你中了普獎,對中的號碼是:"+str[i];
									}
								break;
							case 2:
								if(ifsp){
								showstr="恭喜你中了七獎,對中的號碼是:"+str[i];
								}else{
									showstr="";
								}
								break;
							default:
								showstr="";
								break;
							}
							if(!showstr.equals("")){
							texeplaneStr+=showstr+"\n";
						}
						
						}
						
						if (texeplaneStr.length()>10){
							textArea.setText(texeplaneStr);
						}else {
							texeplaneStr="沒有中獎";
							textArea.setText(texeplaneStr);
						}
					
						
					}else{
						JOptionPane.showMessageDialog(null, "當期獎號輸入格式錯誤");
					}
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					
				}
			});
            
            button_1.setBounds(389, 282, 74, 27);
            bottomPanel.add(button_1);
            JLabel label_2 = new JLabel("\u5FEB\u9078\u865F\u78BC");
            label_2.setFont(new Font("新細明體", Font.PLAIN, 20));
            label_2.setBounds(14, 9, 110, 23);
            bottomPanel.add(label_2);
            JLabel label_3 = new JLabel("\u5DF2\u4FDD\u5B58\u865F\u78BC");
            label_3.setFont(new Font("Dialog", Font.PLAIN, 20));
            label_3.setBounds(287, 9, 110, 23);
            bottomPanel.add(label_3);        
            JLabel label_4 = new JLabel("\u7576\u671F\u734E\u865F:");
            label_4.setFont(new Font("新細明體", Font.PLAIN, 20));
            label_4.setBounds(527, 9, 105, 24);
            bottomPanel.add(label_4);
            
            JLabel label_5 = new JLabel("\u7279\u5225\u865F:");
            label_5.setFont(new Font("新細明體", Font.PLAIN, 20));
            label_5.setBounds(527, 76, 74, 27);
            bottomPanel.add(label_5);
            
            textField_13 = new JTextField();
            textField_13.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_13.setBounds(527, 112, 57, 25);
            bottomPanel.add(textField_13);
            textField_13.setColumns(5);
            
            textField_7 = new JTextField();
            textField_7.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_7.setColumns(5);
            textField_7.setBounds(527, 46, 61, 25);
            bottomPanel.add(textField_7);
            
            textField_8 = new JTextField();
            textField_8.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_8.setColumns(5);
            textField_8.setBounds(593, 46, 61, 25);
            bottomPanel.add(textField_8);
            
            textField_9 = new JTextField();
            textField_9.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_9.setColumns(5);
            textField_9.setBounds(659, 46, 61, 25);
            bottomPanel.add(textField_9);
            
            textField_10 = new JTextField();
            textField_10.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_10.setColumns(5);
            textField_10.setBounds(725, 46, 61, 25);
            bottomPanel.add(textField_10);
            
            textField_11 = new JTextField();
            textField_11.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_11.setColumns(5);
            textField_11.setBounds(791, 46, 61, 25);
            bottomPanel.add(textField_11);
            
            textField_12 = new JTextField();
            textField_12.setFont(new Font("新細明體", Font.PLAIN, 20));
            textField_12.setColumns(5);
            textField_12.setBounds(857, 46, 61, 25);
            bottomPanel.add(textField_12);
            
            textArea = new JTextArea(5,10);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
            textArea.setBounds(527, 143, 380, 166);
            bottomPanel.add(textArea);
            panel_1.setOpaque(true);
            
            
            jbnButton=new JButton[49];
    	    int array[]=new int[49];
    	        for(int i=0;i<49;i++)
    	        {array[i]=i+1;}
    	        number_panel.setLayout(new GridLayout(7,18));
    	        int tmp,k;
    	        Random ran = new Random();
    	        for(int i=0;i<49;i++){
    	        jbnButton[i]=new JButton();
    	        jbnButton[i].setFont(new Font("Arial", Font.BOLD, 18));
    	        jbnButton[i].setText(""+(i+1));
    	        jbnButton[i].setBackground(new Color(238,238,238));
    	        jbnButton[i].setActionCommand(""+(i+1));
    	        jbnButton[i].addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    				    String cmd = e.getActionCommand();
    				    JOptionPane.showMessageDialog(null,"此數字為"+cmd); 
    				}
    			});
    	        number_panel.add(jbnButton[i]);
    	        }
    	        frame.getContentPane().add(number_panel, BorderLayout.CENTER);
    	        
	
	}
	private int prize(String aa) 
	{
		boolean b=!TsetMoney.add(aa);	
		if(b)//如果aa與獎號相同 數量+1
			{
				count++;
			}else{
				TsetMoney.remove(aa);
			}
			
		return count;	
	}
	private void randomNumber() {
		//取六個亂數
		 HSet= new HashSet<Integer>();
		 Tset= new TreeSet<Integer>();
		int rdnum=0;
		do{
			Random rdm=new Random();
			rdnum=rdm.nextInt(49)+1;
			HSet.add(rdnum);
			Tset.add(rdnum);
			
		}
		while(HSet.size()<6);
		
		
		
	}
	class MyThread extends Thread {
  	  public void run() {
  		  
  		  Random ran = new Random();
  		  for(int i=0;i<10;i++){
  		  int intcmd1=ran.nextInt(8);
  		  int intcmd2=ran.nextInt(8)+8;
  		  int intcmd3=ran.nextInt(8)+16;
  		  int intcmd4=ran.nextInt(8)+24;
  		  int intcmd5=ran.nextInt(8)+32;
  		  int intcmd6=ran.nextInt(9)+40;
  		  
  		  int[] rdmlist=new int[]{intcmd1,intcmd2,intcmd3,intcmd4,intcmd5,intcmd6};

  		  jbnButton[intcmd1].setBackground(Color.RED);
  		  jbnButton[intcmd2].setBackground(Color.RED);
  		  jbnButton[intcmd3].setBackground(Color.RED);
  		  jbnButton[intcmd4].setBackground(Color.RED);
  		  jbnButton[intcmd5].setBackground(Color.RED);
  		  jbnButton[intcmd6].setBackground(Color.RED);
  		  try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
  		  for(int j=0;j<6;j++)
  		  {
  			  if(!Tset.contains(rdmlist[j]+1))//如果是快選號碼,就不能變回灰色
  			  {  				 
  				jbnButton[rdmlist[j]].setBackground( new Color(238,238,238));
  			  }
  		  }
  		  

			

  		  }
  	  }
  	}
	class MyThread2 extends Thread {
	  	  public void run() {
	  		  try {
	  			Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	  		  
	  		Iterator<Integer> i=Tset.iterator();
		    for (int j=0;j<Tset.size();j++)  // 只要還有下個元素, 就繼續迴圈
			{
		    	int rdn=i.next();
		    	jbnButton[rdn-1].setBackground(Color.RED);
			}
		    items.addElement(Tset.toString());
		    }
	}
}