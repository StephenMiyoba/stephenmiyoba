package hotel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class HotelCheckIn extends JFrame {
	
	JFrame frame;
	JLabel databaseNameLabel, usernameLabel, passwordLabel, idLabel, nameLabel, phoneNumberLabel, addressLabel, emailLabel, roomNumberLabel, dialogRoomNumberLabel,roomTypeLabel, amountLabel;
	JTextField databaseNameTextField, usernameTextField, idTextField, nameTextField, phoneNumberTextField, emailTextField, roomNumberTextField, amountTextField, dialogRoomNumberTextField;
	JTextArea addressTextArea;
	JScrollPane adressTextAreaScrollPane;
	JRadioButton maleRadioButton, femaleRadioButton;
	ButtonGroup buttonGroup;
	JPasswordField passwordField;
	JButton connectButton, checkInButton, checkOutButton, updateButton, deleteButton, selectRoomButton, okDialog, updateDialog;
	JTable jTable;
	DefaultTableModel defaultTableModel;
	JMenuBar menuBar;
    JMenu menu;		
	JMenuItem menuItemReport, menuItemRooms,menuItemExit;
	int question;;
	String url = "jdbc:mysql://localhost:3306/";
	String usernameString;
	String passwordString;
	Connection connection;
	
	public HotelCheckIn() {
		
        this.setLayout(null);
		
		databaseNameLabel = new JLabel("Database Name:");
		databaseNameLabel.setBounds(5, 5, 120, 20);
		this.add(databaseNameLabel);
		
		databaseNameTextField = new JTextField();
		databaseNameTextField.setBounds(105, 5, 150, 20);
		this.add(databaseNameTextField);
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(5, 30, 120, 20);
		this.add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(105, 30, 150, 20);
		this.add(usernameTextField);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(5, 55, 120, 20);
		this.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(105, 55, 150, 20);
		this.add(passwordField);
		
		connectButton = new JButton("Connect");
		connectButton.setBounds(165, 80, 90, 20);
		this.add(connectButton);
		
		frame = new JFrame();
        
        connectButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				
				if(actionEvent.getSource() == connectButton) {
	                Connect();
	                ShowTable();
				}
			}
		});
		
	}
	
	public void Details() {	
		
        frame.setLayout(null);
		
		idLabel = new JLabel("I.D/NRC Number:");
		idLabel.setBounds(5, 15, 95, 20);
		frame.add(idLabel);
		
		idTextField = new JTextField();
		idTextField.setBounds(125, 15, 183, 25);
		frame.add(idTextField);
		
        idTextField.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent keyEvent) {
				
	            String searchString = idTextField.getText();
				
				if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
					
				try {
					
				    java.sql.Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from details where id = "+ searchString);
					
					while(resultSet.next()) {
						 
						String idString = resultSet.getString("id");
						String compare = idTextField.getText();
						if (idString.equals(compare)) { 
							idTextField.setText(idString);
						String nameString = resultSet.getString("name");
						String searchedGenderString = resultSet.getString("gender");
						if (searchedGenderString.equals("Male")) {
							maleRadioButton.setSelected(true);
						}
						else {
		                    femaleRadioButton.setSelected(true);						
						}	
						nameTextField.setText(nameString);
						String phoneNumberString = resultSet.getString("phonenumber");
						phoneNumberTextField.setText(phoneNumberString);
						String emailString = resultSet.getString("email");
						emailTextField.setText(emailString);
						String addressString = resultSet.getString("address");
						addressTextArea.setText(addressString);
						}
						else {
							JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					ResultSet resultset = statement.executeQuery("select * from status where id = "+ searchString);
					while(resultset.next()) {
						
						String searchRoomNumberString = resultset.getString("roomnumber");
						roomNumberTextField.setText(searchRoomNumberString);
				    }		
				}
				
				catch(Exception exception) {
					JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE); 			
				}			
			}
		    
				if(keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {			
					Clear();
				}
			}
		});
		
        nameLabel = new JLabel("Name:");
		nameLabel.setBounds(5, 45, 90, 20);
		frame.add(nameLabel);
		
		nameTextField = new JTextField();	
		nameTextField.setBounds(125, 45, 183, 25);
		frame.add(nameTextField);
		
		maleRadioButton = new JRadioButton("Male");
		maleRadioButton.setActionCommand("Male");
		maleRadioButton.setSelected(true);
		maleRadioButton.setBounds(5, 85, 70, 20);
		frame.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton("Female");
		femaleRadioButton.setActionCommand("Female");
		femaleRadioButton.setBounds(75, 85, 80, 20);
		frame.add(femaleRadioButton);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRadioButton);
		buttonGroup.add(femaleRadioButton);
		
		phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setBounds(5, 120, 90, 20);
		frame.add(phoneNumberLabel);
		
		phoneNumberTextField = new JTextField();
		phoneNumberTextField.setBounds(125,120, 183, 25);
		frame.add(phoneNumberTextField);
		
		emailLabel = new JLabel("E-mail:");
		emailLabel.setBounds(5, 150, 90, 20);
		frame.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(125, 150, 183, 25);
		frame.add(emailTextField);
		
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(5, 185, 90, 20);
		frame.add(addressLabel);
		
		addressTextArea = new JTextArea();
		addressTextArea.setBounds(125, 185, 183, 50);
		addressTextArea.setLineWrap(true);
		frame.add(addressTextArea);
		adressTextAreaScrollPane = new JScrollPane(adressTextAreaScrollPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		roomNumberLabel = new JLabel("Room Number:");
		roomNumberLabel.setBounds(5, 245, 90, 20);
		frame.add(roomNumberLabel);
		
		roomNumberTextField = new JTextField();
		roomNumberTextField.setBounds(125, 245, 183, 25);
		frame.add(roomNumberTextField);
		roomNumberTextField.setEditable(false);
		
		checkInButton = new JButton("In");
		checkInButton.setBounds(3, 325, 74, 20);
		frame.add(checkInButton);
		
		checkInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (actionEvent.getSource() == checkInButton) {
					
					String stringID = idTextField.getText();
				    String stringName = nameTextField.getText();
					String genderString = buttonGroup.getSelection().getActionCommand();
					String phoneNumberString = phoneNumberTextField.getText();
					String emailString = emailTextField.getText();
				    String stringAddress = addressTextArea.getText();
				    String roomNumberString = roomNumberTextField.getText();
					String emptyString = ""; 
					
					try {
						java.sql.Statement statement = connection.createStatement();
						ResultSet resultSetDetails = statement.executeQuery("SELECT * FROM details");
	            	    while(resultSetDetails.next()) {
	            	    	
	    	   		    	String compareIdString = resultSetDetails.getString("id");
		        		    if(compareIdString.equals(stringID)) {
		        		    	String sqlUpdateStatus = "update status set checkin = 'YES', checkout = 'NO' where id =" +stringID; 
		        				statement = connection.prepareStatement(sqlUpdateStatus);
		   						statement.executeUpdate(sqlUpdateStatus);
	     							
		        				String sqlSaveDetails = "insert into status (id, roomnumber) values ('"+stringID+"', '"+roomNumberString+"')";
		    				    statement = connection.prepareStatement(sqlSaveDetails);
		    				    statement.executeUpdate(sqlSaveDetails);
		    							    
		    				    String sqlUpdateRooms = "update rooms set status = 'TAKEN' where roomnumber =" +roomNumberString;
		    				    statement =  connection.prepareStatement(sqlUpdateRooms);
		    				    statement.executeUpdate(sqlUpdateRooms);
		    							    
		    				    JOptionPane.showMessageDialog(null, "Checked In Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		        		    }
	            	    }
	            	    
	            	    if(emptyString.equals(stringID)) {
	            	    	JOptionPane.showMessageDialog(null, "Enter ID", "Error", JOptionPane.ERROR_MESSAGE);
	            	    } 
					    if(emptyString.equals(stringName)) {
						    JOptionPane.showMessageDialog(null, "Enter Name", "Error", JOptionPane.ERROR_MESSAGE);
					    } 
					    if(emptyString.equals(phoneNumberString)) {
						    JOptionPane.showMessageDialog(null, "Enter Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
					    } 
					    if(emptyString.equals(stringAddress)) {
					    	JOptionPane.showMessageDialog(null, "Enter Address", "Error", JOptionPane.ERROR_MESSAGE);
					    }
					    if(emptyString.equals(roomNumberString)) {
						    JOptionPane.showMessageDialog(null, "Select Room", "Error", JOptionPane.ERROR_MESSAGE);
					    }
					    
					    else {
					    	
					    	String sqlSaveDetails = "insert into details (id, name, gender, address, phonenumber, email) values ('"+stringID+"', '"+stringName+"', '"+genderString+"', '"+stringAddress+"', '"+phoneNumberString+"', '"+emailString+"')";
							statement = connection.prepareStatement(sqlSaveDetails);
						    statement.executeUpdate(sqlSaveDetails);
							    
						    String sqlSaveStatus = "insert into status (id, checkin, checkout, roomnumber) values ('"+stringID+"', 'YES', 'NO', '"+roomNumberString+"')"; 
				
						    statement = connection.prepareStatement(sqlSaveStatus);
							statement.executeUpdate(sqlSaveStatus);
								
							String sqlUpdateRooms = "update rooms set status = 'TAKEN' where roomnumber =" +roomNumberString;
						    statement =  connection.prepareStatement(sqlUpdateRooms);
						    statement.executeUpdate(sqlUpdateRooms);
							    
						    JOptionPane.showMessageDialog(null, "Checked In Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
							    
							ShowTable();
							Clear();

	            	    }   
					}
				
		            catch(Exception exception) {
		                JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);			
				    }
				}
			}
		});
		
		checkOutButton = new JButton("Out");
		checkOutButton.setBounds(81, 325, 75, 20);
		frame.add(checkOutButton);
		
		checkOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (actionEvent.getSource() == checkOutButton) {
		              
					String stringID = idTextField.getText();
					String emptyString = "";
					 
					 question = JOptionPane.showConfirmDialog(null, "Check Out?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);	 
					 
					 if (question == JOptionPane.YES_OPTION) {
						 if(emptyString.equals(stringID)) {
								JOptionPane.showMessageDialog(null, "Enter ID", "Error", JOptionPane.ERROR_MESSAGE);
							}
						 else {
							 
							 try {
								 
								 java.sql.Statement statement = connection.createStatement();
						
			     			     String roomNumberString = roomNumberTextField.getText();
					    	     String sqlUpdateRooms = "update rooms set status = 'FREE' where roomnumber =" +roomNumberString;
						         statement =  connection.prepareStatement(sqlUpdateRooms);
						         statement.executeUpdate(sqlUpdateRooms);
						    
				       		     String sqlUpdateStatus = "update status set checkin = 'NO', checkout = 'YES' where roomnumber =" +roomNumberString; 
						     	 statement = connection.prepareStatement(sqlUpdateStatus);
							     statement.executeUpdate(sqlUpdateStatus);
							
					     		 JOptionPane.showMessageDialog(null, "Checked Out Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
							
							     ShowTable();
							     Clear();
						
							 }
							 
							 catch(Exception exception) {
			                     
								 JOptionPane.showMessageDialog(null, exception);			
							 } 
						 }
					 }
					  else if (question == JOptionPane.NO_OPTION) {
						 JOptionPane.showMessageDialog(null, "Not Checked Out", "Error", JOptionPane.ERROR_MESSAGE);
					 }
					 
					 ShowTable();
					 Clear();
				}
			}
		});
		
		updateButton = new JButton("Update");
		updateButton.setBounds(159, 325, 74, 20);
		frame.add(updateButton);
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				if (actionEvent.getSource() == updateButton) {
					
					String stringID = idTextField.getText();
					String emptyString = "";
					if(emptyString.equals(stringID)) {
						JOptionPane.showMessageDialog(null, "Search For Record To Update", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					
					try {
				        java.sql.Statement statement = connection.createStatement();
					
					    String updateIdString = idTextField.getText();
					    String updateNameString = nameTextField.getText();
						String updateGenderString = buttonGroup.getSelection().getActionCommand();
						String updatePhoneNumberString = phoneNumberTextField.getText();
						String updateEmailString = emailTextField.getText();
					    String updateAddressString = addressTextArea.getText();
					    
					    String sqlUpdate = "update details set id = '"+updateIdString+"', name = '"+updateNameString+"', gender = '"+updateGenderString+"', address = '"+updateAddressString+"', phonenumber = '"+updatePhoneNumberString+"', email = '"+updateEmailString+"' where id = " +updateIdString;
					    statement =  connection.prepareStatement(sqlUpdate);
					    statement.executeUpdate(sqlUpdate);
					    
					    String sqlUpdateStatus = "update status set id = '"+updateIdString+"' where id =" +updateIdString;
					    statement =  connection.prepareStatement(sqlUpdateStatus);
					    statement.executeUpdate(sqlUpdateStatus);
					    
					    JOptionPane.showMessageDialog(null, "Updeted Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
					    
						ShowTable();
						Clear();
					
					}
				
		            catch(Exception exception) {
		                JOptionPane.showMessageDialog(null, exception);			
				}
					}
					 	 
				}
			}
		});
		
		deleteButton = new JButton("Delete");
		deleteButton.setBounds(237, 325, 74, 20);
		frame.add(deleteButton);
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if (actionEvent.getSource() == deleteButton) {
					question = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);	 
				 
					String stringID = idTextField.getText();
					String emptyString = "";
					
					if (question == JOptionPane.YES_OPTION) {
					
						if(emptyString.equals(stringID)) {
							JOptionPane.showMessageDialog(null, "Search For Record To Delete", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else {
							 try {
						        java.sql.Statement statement = connection.createStatement();
							    String sqlDeleteDetails = "delete from details where id = "+ stringID;
							    statement = connection.prepareStatement(sqlDeleteDetails);
							    statement.executeUpdate(sqlDeleteDetails);
							    
							    String sqlDeleteStatus = "delete from status where id = "+ stringID;
							    statement = connection.prepareStatement(sqlDeleteStatus);
							    statement.executeUpdate(sqlDeleteStatus);
							    
							    String updateRoomStatus = roomNumberTextField.getText();
							    String sqlUpdateRooms = "update rooms set status = 'FREE' where roomnumber =" +updateRoomStatus;
							    statement =  connection.prepareStatement(sqlUpdateRooms);
							    statement.executeUpdate(sqlUpdateRooms);
							    
							    JOptionPane.showMessageDialog(null, "Deleted Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
								
								ShowTable();
								Clear();
							
							}
						
				            catch(Exception exception) {
				                JOptionPane.showMessageDialog(null, exception);			
				            }
						 }
					} 
					
					if (question == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "Not Deleted", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		frame.setSize(800, 390);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
                int question = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit?", "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);	 
                if (question == JOptionPane.YES_OPTION) {
                	try {
                		connection.close();
		                System.exit(0);
	
                	} 
                	
                	catch(Exception exception) {
                		JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);			
                	}
	 
                }
                
                else {
    	
                	new HotelCheckIn();
                }
			}
		});
	
		try {
			String trueString = "nulltrue"; 
			String usernameString = usernameTextField.getText();
		    java.sql.Statement statement = connection.createStatement();
			ResultSet credentialResultSet = statement.executeQuery("select * from credentials where username = "+usernameString);
			
			while(credentialResultSet.next()) {
				 
				String checkInString = credentialResultSet.getString("checkin");
				if (trueString.equals(checkInString)) { 
					checkInButton.setEnabled(true);
				}
				String checkOutString = credentialResultSet.getString("checkout");
				if (trueString.equals(checkOutString)) {
					checkOutButton.setEnabled(true);
				}
				String updateString = credentialResultSet.getString("updates");
				if (trueString.equals(updateString)) {
					updateButton.setEnabled(true);
				}
				String deleteString = credentialResultSet.getString("deleting");
				if (trueString.equals(deleteString)) {
					deleteButton.setEnabled(true);
				}
		    }	
		}
        catch(Exception exception) {
        	JOptionPane.showMessageDialog(null, exception, "Error", JOptionPane.ERROR_MESSAGE);	 			
		}
	}
	
	public void ShowTable() {
		
		defaultTableModel = new DefaultTableModel();
		jTable = new JTable(defaultTableModel);		
	
    	JScrollPane scrollPane = new JScrollPane(jTable);
		scrollPane.setBounds(330, 15, 450, 305);
		
		defaultTableModel.addColumn("Room Number");
		defaultTableModel.addColumn("Room Type");
		defaultTableModel.addColumn("Amount");
		defaultTableModel.addColumn("Status");

        frame.add(scrollPane);
		
        try {
			
		    java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from rooms where status = 'FREE'");
			while(resultSet.next()) {
				
				String idString = resultSet.getString("roomnumber");
				String nameString = resultSet.getString("roomtype");
				String searchedGenderString = resultSet.getString("amount");
				String addressString = resultSet.getString("status");
				
				defaultTableModel.addRow(new Object[] {idString, nameString, searchedGenderString, addressString});
				
			}
		}
		catch(Exception exception) {
            JOptionPane.showMessageDialog(null, exception);			
		}
        
        selectRoomButton = new JButton("Select Room");
        selectRoomButton.setBounds(670, 325, 110, 20);
        frame.add(selectRoomButton);
        selectRoomButton.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent actionEvent) {
				
				String emptyString = "";
				String emptyRoomNumberTextFieldString = roomNumberTextField.getSelectedText();
				if(emptyRoomNumberTextFieldString == emptyString) {
					JOptionPane.showMessageDialog(null, "Select Room", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				int row = jTable.getSelectedRow();
				
				String row0 = defaultTableModel.getValueAt(row, 0).toString();
				roomNumberTextField.setText(row0);

			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void Connect() {
		
       try {
			
        	String databaseNameString = databaseNameTextField.getText();
        	url = "jdbc:mysql://localhost:3306/"+ databaseNameString;
        	usernameString = usernameTextField.getText();
        	passwordString = passwordField.getText();
        			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, usernameString, passwordString);	
			JOptionPane.showMessageDialog(null, "Connected Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
			dispose();
			Details();
				
		}
		catch(Exception exception) {
            JOptionPane.showMessageDialog(null, exception);			
		}
	}
	
    public void Clear() {
		
		idTextField.setText("");
	    nameTextField.setText("");
	    maleRadioButton.setSelected(false);
	    femaleRadioButton.setSelected(false);
		phoneNumberTextField.setText("");
		emailTextField.setText("");
	    addressTextArea.setText("");
	    roomNumberTextField.setText("");

	}

	public static void main(String[] args) {
		
		HotelCheckIn hotelCheckIn = new HotelCheckIn();
		hotelCheckIn.setTitle("Connect To DB");
		hotelCheckIn.setLayout(null);
		hotelCheckIn.setSize(275,145);
		hotelCheckIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hotelCheckIn.setLocationRelativeTo(null);
		hotelCheckIn.setVisible(true);
		hotelCheckIn.setResizable(false);

	}

}
