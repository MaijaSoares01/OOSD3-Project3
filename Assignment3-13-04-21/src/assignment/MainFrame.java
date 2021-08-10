//Name: Maija Soares
//Student Number: C19478224
//Module: OOSD
//Assignment 3 Code
package assignment;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
public class MainFrame extends JFrame {
	private JLabel orderLabel = new JLabel("Your Order:");//label 
	private JTextField orderTextField = new JTextField(20);//textfield
	private JButton okButton = new JButton("OK");//ok button
	
public MainFrame(String title) throws HeadlessException {
	super(title);
	this.setLayout(new FlowLayout());//set out layout
	this.add(orderLabel);//label is added
	this.add(orderTextField);//textfield is added
	this.add(okButton);//ok button is added
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);//closes window when exit is selected
	
	//What happens when OK Button is selected
			okButton.addActionListener(new ActionListener() {//when ok button is clicked...
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean allOK = true;//boolean allOk is set to true
					String order = orderTextField.getText();//get text within the textfield
					if (order.equals("")) {//if the textfield is blank nothing happens...
						allOK=false;
					}
					if (allOK==true) {//if allOk is set to true then....
						//Order orders = new Order(order);//
						Restaurant.runMyRestaurant(order);//Restaurant class is called
					}
					}
			});
}

public static void main(String[] args) {
	String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
	try {//try-catch exception 
		UIManager.setLookAndFeel(systemLookAndFeelClassName);
	}catch (Exception e) {
		e.printStackTrace();
	}
	MainFrame window = new MainFrame("Mak Doonalds");//Main Window is called Mak Doonalds!
	window.pack();//window is packed(size)    
	window.setLocationRelativeTo(null); 
	window.setVisible(true);//main window is visible 
}
}
//CLASS ORDER
class Order {
	//Order's have the following information
		private String order;//order
		public Order(String order) {
			this.order = order;
		}
//		public Order(Order o) {
//			this.order = order;
//		}
		//GETTERS
		public String getOrder() {
			return order;
		}
		//SETTERS
		public void setOrder(String order) {
			this.order = order;
		}
		//TO STRING METHOD
		@Override
		public String toString() {
			return order + "\n";
		}
}

//CLASS WAITER
class Waiter extends Thread {
	JFrame frame = new JFrame();//frame for pop up message
	private Restaurant restaurant;//restaurant object
	private Order order;//order object
	private Chef chef;//chef object
	public Waiter(Restaurant r, Order o) {
	order = o;
	restaurant = r;
	start();// start thread
	}
	public void run() {
	synchronized(this) {//Sync 
	try {//try catch exception
    System.out.println("Waiter gave the order to the Chef");//print out message
	this.wait();//this thread waits for the chef to notify them that the food is ready
	} catch(InterruptedException e) {
	throw new RuntimeException(e);}}
	System.out.println("Waiter now has the food and will deliver it to the customer!(5 seconds to walk over)");//print out message
	try {
		sleep(5000);//sleep for 5000 milliseconds or 5 seconds to walk over to the customer to deliver the food
		} catch(InterruptedException e) {
		throw new RuntimeException(e);
		}
	JOptionPane.showMessageDialog(frame, "Your waiter delivered your " + order);//pop up message saying order is delivered and ready
	System.out.println("Customer was given their order: " + order);//print out order
	}
}
//CLASS CHEF 
class Chef extends Thread {
	JFrame frame = new JFrame();//frame for pop up message
	private Restaurant restaurant;//restaurant object
	private Waiter waiter;//waiter object
	private Order order;//order object
	private Chef chef;//chef object
	public Chef(Restaurant r, Waiter w, Order o) {
	restaurant = r;
	waiter = w;
	order = o;
	start(); //start thread
	}
	public void run() {
	System.out.println("Chef will now make the food!(10 seconds)");
	try {
	sleep(10000);//sleep for 10000 milliseconds or 10 seconds because the chef is making the food
	} catch(InterruptedException e) {
	throw new RuntimeException(e);
	}
	System.out.println("Order is ready!");//print out message
	JOptionPane.showMessageDialog(frame, "Chef: Order up!");//pop up message saying order is ready
	synchronized(waiter) {//sync waiter
		waiter.notify();//tells the waiter
		}
	}
}
//CLASS RESTAURANT
class Restaurant {
	private List<Order> orders = new ArrayList<>();//create arraylist of orders
	public Order order;//order object
	public Restaurant(Order o) {
	order=order;
	}
	public static void runMyRestaurant(String order2) {
		Order o = new Order(order2);
		Restaurant r = new Restaurant(o);//Restaurant has order
		Waiter w = new Waiter(r,o);//waiter has r and o
		Chef c = new Chef(r,w,o);//chef has r w and o
	}
}
