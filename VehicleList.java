/**
 * 
 */


import javax.swing.JOptionPane;

/**
 * Authour: Hamza Khan 
 * Date; November, 2023
 * Description; List/records being able to be save as records
 *              be able to to change. delete, print and even 
 *              increase the size of the array 
 *              
 *              
 *              
 * Method List;
 * 
 * 	public VehicleList() {  //constructor 
 * 
 * 	public int getSize() { //gets the size 
 * 
 * 	public boolean insert(VehicleRecord record) { //inserting record 
 * 
 * 	public boolean delete (VehicleRecord record) { //deleting records
 * 
 * 	public boolean change (VehicleRecord oldR, VehicleRecord newR) { //changing record 
 * 
 * 	public String toString() { //method to convert list to string
 * 
 * 	public void increaseArraySize(int newSize) { //increasing the array 
 * 
 * 	public static void main(String[] args) { //main self testing 
 * 
 */
public class VehicleList {

	/**
	 * Private Instance Variables/Attributes  
	 */

	private VehicleRecord list []; //list of VechileRecord object 
	private int maxSize; // the maximum number of records the list can move
	private int size; //the actual usable size

	//Default Constuctor  
	public VehicleList() {
		this.maxSize = 10;
		this.list = new VehicleRecord [maxSize];
		this.size = 0;   
	}




	//get size 
	public int getSize() {
		return size;
	}

	/*
	 * Method to insert a record into the list 
	 * checks if the list is below the maxSize
	 * if so, it increases the size by 1 
	 * and adds the record to the location just below the size
	 * returns true of succesful 
	 */
	public boolean insert(VehicleRecord record) {
		//if the size is below maxSize
		if (size < maxSize) {
			size++;//increae by 1 
			list [size -1] = record; //add to the record 
			return true; 
		}
		return false;
	}


	/*
	 * Method to delete a record from the valid list
	 * Cheats the delete by replacing the record to be deleted to be deleted with the last record on the list 
	 * Then is decreases the size of the valid  list. Return true if the scucesful 
	 */

	public boolean delete (VehicleRecord record) {
		for (int i = 0; i < this.getSize(); i++){

			if (list[i].getMake().equalsIgnoreCase(record.getMake())) {
				list[i] = list [size-1]; //copy the last record to the one to be deleted
				size--; //decrease the valid list size
				return true;
			}
		}
		return false;
	}


	/*
	 * Method to change
	 * delete the old record 
	 * insert a new record 
	 */

	public boolean change (VehicleRecord oldR, VehicleRecord newR) {
		if (delete(oldR)) {
			insert(newR);
			return true; //insert new record because old was found 
		}
		return false; //old record not found 
	}



	/*
	 * toString method to convert the list to to a string 
	 */
	public String toString() {
		String theList = "";
		for (int i = 0; i < this.getSize(); i++ ) {
			theList = theList + "Record " + i + ":" + list[i].toString() + "\n";
		}
		return theList;

	}

	//method which allows the reenters the old data into the 
	//newly modified array size, by intiallizing the array 
	//to newSize and newList
	public void increaseArraySize(int newSize) {
		if (newSize > maxSize) {

			//object
			VehicleRecord[] newList = new VehicleRecord[newSize];

			// Copy existing records to the new list
			for (int i = 0; i < size; i++) {
				newList[i] = list[i];
			}

			//set to newList and newSize 
			list = newList;
			maxSize = newSize;
		}
	}


	/**
	 * @param args
	 */

	//Self-Testing Method 
	public static void main(String[] args) {

		VehicleList cars = new VehicleList();

		//infinite loop for testing 
		while(true) {
			char command;
			command = JOptionPane.showInputDialog(null,
					"i - Insert\n" + "s - Increase Array Size\n" + "p - Print\n" + "d - Delete\n" + "c - Change\n" +
							"q - Quit", "i").charAt(0);

			if (command == 'q') {
				break; //break out of the loop 
			}
			switch (command) {
			case 'i': { //inserting test 

				//
				String record = JOptionPane.showInputDialog(null, "Enter <Make/ Model/ Year/ Type", 
						"Honda/Civic/2006/s");

				//create a student record object 

				VehicleRecord info = new VehicleRecord();

				info.processRecord(record);



				if (cars.insert(info)) { //insert the record and 
					JOptionPane.showMessageDialog(null, "Insert Succesfull");
				}

				else {
					JOptionPane.showMessageDialog(null, "Insert NOT Succesfull");

				}
				break;

			}

			//delete's
			case 'd': {
				String record = JOptionPane.showInputDialog(null, "Enter <Make/ Model/ Year/ Type", 
						"Honda/Civic/2006/s");

				//create a vehicle record object 
				VehicleRecord info = new VehicleRecord();

				//process
				info.processRecord(record);

				//display messages based on condtion
				if (cars.delete(info)) {
					JOptionPane.showMessageDialog(null, "Record Deleted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Record Deleted");

				}
				break; 
			}

			//changes record 
			case 'c': {

				String oldRecord = JOptionPane.showInputDialog(null, "Enter <Make/ Model/ Year/ Type", 
						"Honda/Civic/2006/s");

				//create a vehicle record for old object 
				VehicleRecord oldInfo = new VehicleRecord();

				//process old
				oldInfo.processRecord(oldRecord);


				String newRecord = JOptionPane.showInputDialog(null, "Enter <Make/ Model/ Year/ Type", 
						"Honda/Civic/2006/s");

				//create a vehicle record for new object 
				VehicleRecord newInfo = new VehicleRecord();

				//process new
				newInfo.processRecord(newRecord);


				//display statements based on condtion 
				if (cars.change(oldInfo, newInfo)) {
					JOptionPane.showMessageDialog(null, "Record Changed");
				}
				else {
					JOptionPane.showMessageDialog(null, "Record NOT Changed/ WAS NOT FOUND");

				} 

				break;

			}

			//print 
			case 'p': {
				JOptionPane.showMessageDialog(null, cars.toString());
				break;
			}

			//to increase the array size 
			case 's':{
				//ask user for size they want to increase by 
				int addedSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Please Enter the Value you would like oto increse the array by;"));
			
				//add the inputted size into the old size 
				int newSize = cars.getSize() + addedSize;
				
				//set and increase the array
				cars.increaseArraySize(newSize);
			
				//print the new array size
				JOptionPane.showMessageDialog(null, "The New List Size has increased to : " + (newSize));
				break;

			}


			}
		}




	}

}
