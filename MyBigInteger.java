package aish;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;


public class MyBigInteger extends Frame implements ActionListener {      //AWT Declaration
	 	Label l1, l2, l3;
	    Button b1, b2, b3, b4, b5, b6;
	    TextField t1, t2, t3;
	    Panel p1, p2, p3, p4, p5, p6, p7, p8, p9;
	    Panel mainPanel = new Panel();
	    Panel leftPanel = new Panel();
	    Panel rightPanel = new Panel();
		
	    MyBigInteger() {             // Constructor for AWT
	        
	        setBackground(Color.PINK);
	        setLayout(new GridLayout(1,3));
	        this.setTitle("BIG INTEGER");
	        
	        leftPanel.setLayout(new FlowLayout());
	        rightPanel.setLayout(new FlowLayout());
	        mainPanel.setLayout(new GridLayout(10,1));
	        
	        setSize(400, 400);
	        l1 = new Label("Enter a: ");
	        l2 = new Label("Enter b: ");
	        l3 = new Label("Result:");
	        t1 = new TextField();
	        t2 = new TextField();
	        t3 = new TextField();
	        t1.setColumns(50);
	        t2.setColumns(50);
	        t3.setColumns(100);
	               
	        b1 = new Button("ADD");
	        b2 = new Button("SUBTRACT");
	        b3 = new Button("MULTIPLY");
	        b4 = new Button("DIVIDE");
	        b5 = new Button("REVERSE");
	        b6 = new Button("CLEAR");
	               
	        p1 = new Panel(new GridLayout(1,1));
	        p2 = new Panel(new GridLayout(1,1));
	        p3 = new Panel(new GridLayout(1,1));
	        p4 = new Panel(new GridLayout(1,1));
	        Panel ex = new Panel(new GridLayout(1,1));
	        
	        p5 = new Panel(new GridLayout(1,1));
	        p6 = new Panel(new GridLayout(1,1));
	        
	        p7 = new Panel(new GridLayout(1,1));
	        p8 = new Panel(new GridLayout(1,1));
	        p9 = new Panel(new GridLayout(1,1));
	        
	        Panel butPanel1 = new Panel();
	        Panel butPanel2 = new Panel();
	        
	        butPanel1.setLayout(new GridLayout(3, 1));
	        butPanel2.setLayout(new GridLayout(3, 1));
	              
	        butPanel1.add(b1);
	        butPanel1.add(b2);
	        butPanel1.add(b3);
	        butPanel2.add(b4);
	        butPanel2.add(b5);
	        butPanel2.add(b6);

	        p1.add(l1);
	        p2.add(t1);
	        p3.add(l2);
	        p4.add(t2);
	        p5.add(butPanel1);
	        p6.add(butPanel2);
	        p7.add(l3);
	        p8.add(t3);
	        
	        b1.addActionListener(this);       
	        b2.addActionListener(this);       
	        b3.addActionListener(this);       
	        b4.addActionListener(this);       
	        b5.addActionListener(this);
	        b6.addActionListener(this);
	             
	        mainPanel.add(p1);
	        mainPanel.add(p2);
	        mainPanel.add(p3);
	        mainPanel.add(p4);
	        mainPanel.add(ex);
	        mainPanel.add(p5);
	        mainPanel.add(p6);
	        mainPanel.add(p7);
	        mainPanel.add(p8);
	        mainPanel.add(p9);
	        
	        add(leftPanel);
	        add(mainPanel);
	        add(rightPanel);
	        
	        setVisible(true);           //to make AWT visible
	        this.addWindowListener(new WindowAdapter() {          //to close AWT Window
	            public void windowClosing(WindowEvent e) {
	                System.exit(0);
	            }
	        });
	    }
	    
	    private ArrayList<Integer> digits;

	    public MyBigInteger(String str) {          //to take string input and store it in ArrayList in reversing order
	        digits = new ArrayList<Integer>();
	        for (int i = str.length() - 1; i >= 0; i--) {
	            digits.add(Integer.parseInt(str.substring(i, i + 1)));
	        }
	    }

	    public MyBigInteger(ArrayList<Integer> digits) {      //to initialize ArrayList in the form of Big Integer objects
	        this.digits = digits;
	    }

	    //Method for Addition
	    public MyBigInteger add(MyBigInteger other) {
	        ArrayList<Integer> resultDigits = new ArrayList<Integer>();
	        int carry = 0;
	        int i = 0;
	        while (i < digits.size() || i < other.digits.size() || carry != 0) {
	            int sum = carry;
	            if (i < digits.size()) {
	                sum += digits.get(i);
	            }
	            if (i < other.digits.size()) {
	                sum += other.digits.get(i);
	            }
	            resultDigits.add(sum % 10);
	            carry = sum / 10;
	            i++;
	        }
	        return new MyBigInteger(resultDigits);
	    }
	    
	   //Method for Subtraction	  
	    public MyBigInteger subtract(MyBigInteger other) {
	        ArrayList<Integer> resultDigits = new ArrayList<>();
	        int borrow = 0;

	        int size = Math.max(digits.size(), other.digits.size());
	        for (int i = 0; i < size; i++) {
	            int digit1 = getDigit(i);
	            int digit2 = other.getDigit(i);

	            int diff = digit1 - digit2 - borrow;

	            if (diff < 0) {
	                diff += 10;
	                borrow = 1;
	            } else {
	                borrow = 0;
	            }

	            resultDigits.add(diff);
	        }

	        // Remove leading zeros from the result
	        while (resultDigits.size() > 1 && resultDigits.get(resultDigits.size() - 1) == 0) {
	            resultDigits.remove(resultDigits.size() - 1);
	        }

	        // Check if the result is negative
	        boolean isNegative = false;
	        for (int digit : resultDigits) {
	            if (digit < 0) {
	                isNegative = true;
	                break;
	            }
	        }

	        if (isNegative) {
	            for (int i = 0; i < resultDigits.size(); i++) {
	                resultDigits.set(i, resultDigits.get(i) + 10);
	            }
	        }

	        return new MyBigInteger(resultDigits);
	    }
	    public int getDigit(int index) {
	        if (index >= digits.size()) {
	            return 0;
	        }
	        return digits.get(index);
	    }
	    
	    //Method for Multiplication
	    public MyBigInteger multiply(MyBigInteger other) {
	        ArrayList<Integer> resultDigits = new ArrayList<Integer>(
	                Collections.nCopies(digits.size() + other.digits.size(), 0));
	        for (int i = 0; i < digits.size(); i++) {
	            int carry = 0;
	            for (int j = 0; j < other.digits.size() || carry != 0; j++) {
	                int product = carry;
	                if (j < other.digits.size()) {
	                    product += digits.get(i) * other.digits.get(j);
	                }
	                product += resultDigits.get(i + j);
	                resultDigits.set(i + j, product % 10);
	                carry = product / 10;
	            }
	        }
	        return new MyBigInteger(resultDigits);
	    }

	    public int compareTo(MyBigInteger other) {      //gives 1 until dividend is greater than divisor
	        int thisSize = digits.size();
	        int otherSize = other.digits.size();

	        if (thisSize < otherSize) {
	            return -1;
	        } else if (thisSize > otherSize) {
	            return 1;
	        } else {
	            for (int i = thisSize - 1; i >= 0; i--) {
	                int thisDigit = digits.get(i);
	                int otherDigit = other.digits.get(i);

	                if (thisDigit < otherDigit) {
	                    return -1;
	                } else if (thisDigit > otherDigit) {
	                    return 1;
	                }
	            }
	            return 0;
	        }
	    }

	    //Method for Division
	    public MyBigInteger divide(MyBigInteger divisor) {
	        if (divisor.isZero()) {      //if b (divisor) =0
	            throw new ArithmeticException("Division by zero");
	        }

	        if (this.isZero()) {          //if a (dividend) =0
	            return MyBigInteger.ZERO;
	        }

	        boolean negativeResult = (this.signum() < 0) ^ (divisor.signum() < 0);   //checks if a xor b is negative or positive
	        MyBigInteger dividend = this.abs();   
	        divisor = divisor.abs();         //returns an absolute value

	        MyBigInteger quotient = MyBigInteger.ZERO;      //quotient initialized to 0

	        while (dividend.compareTo(divisor) >= 0) {
	            int shift = 0;
	            while (dividend.compareTo(divisor.shiftLeft(shift + 1)) >= 0) {
	                shift++;       //shift until divisor is equal to or smaller than dividend
	            }

	            MyBigInteger subtractor = divisor.shiftLeft(shift);  
	            dividend = dividend.subtract(subtractor);       
	            quotient = quotient.add(MyBigInteger.ONE.shiftLeft(shift));
	        }

	        if (negativeResult) {
	            quotient = quotient.negate();   //negates if the inputs were negative
	        }

	        return quotient;
	    }

	    public MyBigInteger abs() {        
	        return new MyBigInteger(digits);
	    }

	    public MyBigInteger shiftLeft(int n) {
	        ArrayList<Integer> shiftedDigits = new ArrayList<Integer>(digits);
	        for (int i = 0; i < n; i++) {
	            shiftedDigits.add(0, 0);
	        }
	        return new MyBigInteger(shiftedDigits);
	    }

	    public boolean isZero() {
	        for (int digit : digits) {
	            if (digit != 0) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public MyBigInteger negate() {
	        ArrayList<Integer> negatedDigits = new ArrayList<Integer>(digits);
	        for (int i = 0; i < negatedDigits.size(); i++) {
	            negatedDigits.set(i, -negatedDigits.get(i));
	        }
	        return new MyBigInteger(negatedDigits);
	    }

	    public int signum() {
	        if (isZero()) {
	            return 0;
	        }
	        return digits.get(digits.size() - 1) < 0 ? -1 : 1;
	    }

	    public String toString() {      //to convert Big Integer Object to String 
	        StringBuilder sb = new StringBuilder();
	        for (int i = digits.size() - 1; i >= 0; i--) {
	            sb.append(digits.get(i));
	        }
	        return sb.toString();
	    }

	    public static final MyBigInteger ZERO = new MyBigInteger("0");    //declares 0 constant
	    public static final MyBigInteger ONE = new MyBigInteger("1");     //declares 1 constant


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new MyBigInteger();         //calling AWT Constructor
    }

    public void actionPerformed(ActionEvent e) {     //handling action events 
        String x = t1.getText();
        String y = t2.getText();
        MyBigInteger a = new MyBigInteger(x);  //creating Big Integer Objects by taking Strings as input
        MyBigInteger b = new MyBigInteger(y);
        BigInteger c;
        boolean flag = false;
        if (e.getSource() == b1) {
            MyBigInteger sum = a.add(b);   //calls for addition 
            t3.setText("" + sum);
        }

       
       if (e.getSource() == b2) {
            BigInteger p=new BigInteger(x);
            BigInteger q=new BigInteger(y);
           
                BigInteger sub = p.subtract(q);     //calls for subtraction
                t3.setText("" + sub);
   
        }  
        if (e.getSource() == b3) {
            MyBigInteger product = a.multiply(b);     //calls for multiplication
            t3.setText("" + product);
        }
        if (e.getSource() == b4) {
            if (b.isZero())
                t3.setText("Division by zero not possible");
            else {
                MyBigInteger divide = a.divide(b);      //calls for division
                t3.setText("" + divide);
            }
        }
        if (e.getSource() == b5) {
            StringBuffer sb = new StringBuffer(x);
            t3.setText("" + sb.reverse());           //reverses the string

        }
        if (e.getSource() == b6) {               //used to clear the textField
            t3.setText("");
            t1.setText("");
            t2.setText("");
        }
    }
    }

