import java.util.Scanner;  
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.text.ParseException;
import java.text.SimpleDateFormat; 
import java.util.Date; 

public class TaskManagement {

	public static void main(String[] args) throws ParseException {
		ArrayList<String> options = new ArrayList<String>(); 
		ArrayList<Task> tasks = new ArrayList<Task>(); 
		
		Scanner scan = new Scanner(System.in); 
		int userInput = 0; 
		boolean validInput = false; 
		boolean validCont = false; 
		char cont = 'y';  
		
		options.add("List tasks");  
		options.add("Add task"); 
		options.add("Delete task");  
		options.add("Mark task complete"); 
		options.add("Edit Task"); 
		options.add("Quit"); 
		
		System.out.println("Welcome to the Task Manager!\n"); 
		
	while (cont == 'y') {
		System.out.println("");  
		validInput =  false; 
		displayMenu(options); 
		System.out.println(""); 
		System.out.println("What would you like to do?, (Enter the corresponding number):"); 
		
		while (validInput == false) {
			try {
				userInput = scan.nextInt(); 
				if (userInput < 1 || userInput > 6) {
					throw new IllegalArgumentException(); 
				}
				validInput = true; 
			} catch (InputMismatchException e) {
				System.out.println("Sorry, invalid input. Please try again"); 
				scan.nextLine();
				continue; 
			} catch (IllegalArgumentException e) {
				System.out.println("Sorry, invalid input. Please try again"); 
				scan.nextLine();
				continue; 
			}
		}
		
	
		switch (userInput) {
		
		case 1:
			displayTasks(tasks, scan); 
			break; 
		case 2: 
			addTasks(tasks, scan); 
			break; 
		case 3: 
			deleteTask(tasks, scan); 
			break; 
		case 4: 
			markTaskComplete(tasks, scan);
			break;
		case 5: 
			editTask(tasks, scan);
			break; 
		case 6: 
			System.out.println("Are you sure, you would like to quit? Enter (Y/N)"); 
			scan.nextLine(); 
			char ans = '.';
			while (validCont == false)  {
			
				try {
					ans = Character.toLowerCase(scan.next().charAt(0));
					if  (!Character.isAlphabetic(ans)) {
						throw new InputMismatchException(); 
					}
					if (ans != 'y' && cont != 'n') {
						throw new InputMismatchException(); 
					}
					validCont = true; 
				} catch (InputMismatchException e) {
					scan.nextLine(); 
					System.out.println("Sorry, invalid input. Please enter (Y/N)"); 
					continue; 
					}
					
			}
			if (ans == 'y') {
				System.out.println("Thanks, Have a good day!");
				cont = 'n';  
			} else {
				continue; 
			}
			
		}
			
			
		}
		

		
		
		

	}
	
	public static void displayMenu(ArrayList<String> menu) {
		for (int i = 0; i < menu.size(); i++) {
			System.out.println((i + 1) + "." + " " + menu.get(i)); 
		}
		
	}
	
	public static void displayTasks(ArrayList<Task> tasks, Scanner scan) throws ParseException {
		if (tasks.isEmpty()) {
			System.out.println("Sorry, the list is empty"); 
		} else {
			System.out.println("DISPLAY TASKS"); 
			System.out.println(""); 
			scan.nextLine(); 
			System.out.println("Would you like to display tasks for only one team member?"); 
			boolean validInput2 = false;
			String userInput2 = ""; 
			char ans = 'n'; 
			while (validInput2 == false) {
				try {
					userInput2 = scan.next(); 
					userInput2 = userInput2.toLowerCase(); 
					ans = userInput2.charAt(0); 
					if (!(ans == 'y') &&  !(ans == 'n')) {
						throw new IllegalArgumentException(); 
					}
					validInput2 = true; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid answer. Please try again: "); 
					scan.nextLine(); 
					userInput2 = scan.next(); 
					continue; 
				}
			}
			while (ans == 'y') {
				scan.nextLine(); 
				System.out.println("Which user would you like to know more about"); 
				String userAnswer = scan.nextLine(); 
				boolean contains = false; 
				for (Task t: tasks) {
					if (t.getTeamMembersName().equals(userAnswer)) {
						contains = true; 
					}
				}
				if (contains == true) {
					System.out.print("   Done?");
					System.out.printf("%15s", "Due Date"); 
					System.out.printf("%16s", "Team member"); 
					System.out.printf("%25s", "Description");
					System.out.println(""); 
					
					for (int i = 0; i < tasks.size(); i++) {
						if (tasks.get(i).getTeamMembersName().equals(userAnswer)) {
							System.out.print((i + 1) + "." + " " + tasks.get(i).getCompletionStatus()); 
							System.out.printf("%17s", tasks.get(i).getDueDate()); 
							System.out.printf("%8s", tasks.get(i).getTeamMembersName()); 
							System.out.printf("%" + (29 - tasks.get(i).getTeamMembersName().length()) + "s", tasks.get(i).getBriefDescriptionOfTask()); 
							System.out.println(""); 
						}
					}
					ans = 'n'; 
				} else {
					System.out.println("Sorry, this user does not exist"); 
					scan.nextLine(); 
					continue; 
				}
			}
			
			System.out.println("Would you like to display tasks before a certain due date"); 
			boolean validInput3 = false;
			String userInput3 = ""; 
			char ans2 = 'n'; 
			while (validInput3 == false) {
				try {
					userInput3 = scan.next(); 
					userInput3 = userInput3.toLowerCase(); 
					ans2 = userInput3.charAt(0); 
					if (!(ans2 == 'y') &&  !(ans2 == 'n')) {
						throw new IllegalArgumentException(); 
					}
					validInput3 = true; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid answer. Please try again: "); 
					scan.nextLine(); 
					userInput3 = scan.next(); 
					continue; 
				}
			}
			
			if (ans2 == 'y') {
				System.out.println("What date would you like to compare to?");  
				String userDate = scan.next(); 
				userDate = validdateDate(userDate, scan); 
				Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(userDate); 
				System.out.print("   Done?");
				System.out.printf("%15s", "Due Date"); 
				System.out.printf("%16s", "Team member"); 
				System.out.printf("%25s", "Description");
				System.out.println(""); 
				
				for (int i = 0; i < tasks.size(); i++) {
					Date date2 = new SimpleDateFormat("MM/dd/yyyy").parse(tasks.get(i).getDueDate()); 
					if (date2.before(date1)) {
						System.out.print((i + 1) + "." + " " + tasks.get(i).getCompletionStatus()); 
						System.out.printf("%17s", tasks.get(i).getDueDate()); 
						System.out.printf("%8s", tasks.get(i).getTeamMembersName()); 
						System.out.printf("%" + (29 - tasks.get(i).getTeamMembersName().length()) + "s", tasks.get(i).getBriefDescriptionOfTask()); 
						System.out.println("");
					}
					 
				}
				return; 
			}
				
			
			
			System.out.print("   Done?");
			System.out.printf("%15s", "Due Date"); 
			System.out.printf("%16s", "Team member"); 
			System.out.printf("%25s", "Description");
			System.out.println(""); 
			
			for (int i = 0; i < tasks.size(); i++) {
				System.out.print((i + 1) + "." + " " + tasks.get(i).getCompletionStatus()); 
				System.out.printf("%17s", tasks.get(i).getDueDate()); 
				System.out.printf("%8s", tasks.get(i).getTeamMembersName()); 
				System.out.printf("%" + (29 - tasks.get(i).getTeamMembersName().length()) + "s", tasks.get(i).getBriefDescriptionOfTask()); 
				System.out.println(""); 
			}
		}	
	}
	
	public static void addTasks(ArrayList<Task> tasks, Scanner scan) {
		scan.nextLine(); 
		String name = "";  
		String description = ""; 
		String dueDate = ""; 
		System.out.println("ADD TASK");  
		System.out.println("");  
		System.out.println("");  
		System.out.print("Please enter the Team Member Name:\n"); 
		name = scan.nextLine();  
		System.out.print("Please enter a Task Description:\n");
		description = scan.nextLine(); 
		System.out.print("Please enter a Due Date:\n"); 
		dueDate = scan.next(); 
		dueDate = validdateDate(dueDate, scan); 
		Task newTask = new Task(name, description, dueDate); 
		newTask.setCompletionStatus(false);
		tasks.add(newTask); 
		System.out.println("Task entered!"); 
	}
	
	public static void editTask(ArrayList<Task> tasks, Scanner scan) {
		if (tasks.isEmpty()) {
			System.out.println("Sorry, list is empty"); 
		} else {
			System.out.println("EDIT TASK"); 
			System.out.println(""); 
			System.out.println("Which task would you like to edit? Tasks are entered in the order they are entered.");
			boolean validInput = false;
			int userInput = 0; 
			while (validInput == false) {
				try {
					userInput = scan.nextInt(); 
					if (userInput < 1 ||  userInput > tasks.size()) {
						throw new IllegalArgumentException(); 
					}
					validInput = true; 
				} catch (InputMismatchException e) {
					System.out.println("Sorry, this is not a valid task number. Please try again: "); 
					scan.nextLine();  
					continue; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid task number. Please try again: "); 
					scan.nextLine(); 
					continue; 
				}
			}
			
			System.out.println("This is the task that you are asking to edit, are you sure you want to edit it? (Y/N)"); 
			
			System.out.print("   Done?");
			System.out.printf("%12s", "Due Date"); 
			System.out.printf("%13s", "Team member"); 
			System.out.printf("%22s", "Description");
			System.out.println(""); 
			
			System.out.print(tasks.get(userInput - 1).getCompletionStatus()); 
			System.out.printf("%17s", tasks.get(userInput - 1).getDueDate()); 
			System.out.printf("%8s", tasks.get(userInput -  1).getTeamMembersName()); 
			System.out.printf("%" + (29 - tasks.get(userInput - 1).getTeamMembersName().length()) + "s", tasks.get(userInput - 1).getBriefDescriptionOfTask()); 
			System.out.println("");
			
			boolean validInput2 = false;
			String userInput2 = ""; 
			char ans = 'n'; 
			while (validInput2 == false) {
				try {
					userInput2 = scan.next(); 
					userInput2 = userInput2.toLowerCase(); 
					ans = userInput2.charAt(0); 
					if (!(ans == 'y') &&  !(ans == 'n')) {
						throw new IllegalArgumentException(); 
					}
					validInput2 = true; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid answer. Please try again: "); 
					scan.nextLine(); 
					userInput2 = scan.next(); 
					continue; 
				}
			}
			if (ans == 'y') {
				scan.nextLine(); 
				System.out.println("What would you like to edit?, you can enter: name, description, due date, or completion status"); 
				String userAnswer = scan.nextLine(); 
				while (!(userAnswer.equals("name") || userAnswer.equals("description") || userAnswer.equals("due date") || userAnswer.equals("completion status"))) {
					scan.nextLine(); 
					System.out.println("Error, that is not a valid option. Please enter again"); 
					userAnswer = scan.nextLine(); 
				}
				switch (userAnswer) {
				case "name":  
					System.out.println("What would you like to change the name to?"); 
					String userInputName = scan.nextLine(); 
					tasks.get(userInput - 1).setTeamMembersName(userInputName);
					break; 
				case "description":  
					System.out.println("What would you like to change the description to?"); 
					String userDescriptionName = scan.nextLine(); 
					tasks.get(userInput - 1).setTeamMembersName(userDescriptionName);
					break; 
				case "due date": 
					System.out.println("What would you like to change the due-date to?"); 
					String userDueDate = scan.nextLine(); 
					userDueDate = validdateDate(userDueDate, scan); 
					tasks.get(userInput - 1).setTeamMembersName(userDueDate);
					break; 
				case "completion status": 
					System.out.println("What would you like to change the completion status to?"); 
					String userInputBool = scan.next(); 
					boolean validEntry = false; 
					while (validEntry == false) {
						if (!(userInputBool.equals("false") || userInputBool.equals("True"))) {
							System.out.println("Error, that is not a valid input. Try Again:"); 
							userInputBool = scan.next(); 
						}
					}
					boolean status = false; 
					if (userInputBool.equals("true")) {
						status = true; 
					}
					if (userInputBool.equals("false")) {
						status = false; 
					}
					tasks.get(userInput - 1).setCompletionStatus(status);
					break; 
				default: 
					break; 
				}
			}
		}
	}
	
	public static String validdateDate(String date, Scanner scan) {
		scan.nextLine(); 
		boolean isValid = false; 
		String newDate = date; 
		while (isValid == false) {
			if (newDate.length() == 10 && newDate.charAt(2) == '/' && newDate.charAt(5) == '/') {
				if ((Character.getNumericValue(newDate.charAt(0)) == 0 && Character.getNumericValue(newDate.charAt(1)) <= 9 && Character.getNumericValue(newDate.charAt(1)) >= 0) || (Character.getNumericValue(date.charAt(0)) == 1 && Character.getNumericValue(date.charAt(1)) <= 2 && Character.getNumericValue(date.charAt(1)) >= 0)) {
					if ((((Character.getNumericValue(newDate.charAt(3)) >= 0 && Character.getNumericValue(newDate.charAt(3)) <= 2) && (Character.getNumericValue(newDate.charAt(4)) <= 9 && Character.getNumericValue(newDate.charAt(4)) >= 0))) || (Character.getNumericValue(newDate.charAt(3)) == 3 && (Character.getNumericValue(newDate.charAt(4)) <= 1 && Character.getNumericValue(newDate.charAt(4)) >= 0))) {
						try {
							Integer temp = Integer.parseInt(newDate.substring(6, 10)); 
							if (temp > 0 && temp <= 2019) {
								isValid = true; 
							}
						} catch  (NumberFormatException e) {
							isValid = false; 
						}
						
					}
				}
			}
			if (isValid == true) {
				return newDate; 
			} else {
				System.out.println("Sorry, invalid date. Please try again: "); 
				newDate = scan.next(); 
				continue; 
			}
		}
		return newDate; 
	}
		
	public static void deleteTask(ArrayList<Task> tasks, Scanner scan) {
		if (tasks.isEmpty()) {
			System.out.println("Sorry, list is empty");
		} else {
			System.out.println("Which task would you like to delete? They are numbered starting from 1 in the order in which they were inserted into this list");  
			boolean validInput = false;
			int userInput = 0; 
			while (validInput == false) {
				try {
					userInput = scan.nextInt(); 
					if (userInput < 1 ||  userInput > tasks.size()) {
						throw new IllegalArgumentException(); 
					}
					validInput = true; 
				} catch (InputMismatchException e) {
					System.out.println("Sorry, this is not a valid task number. Please try again: "); 
					scan.nextLine(); 
					userInput = scan.nextInt(); 
					continue; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid task number. Please try again: "); 
					scan.nextLine(); 
					userInput = scan.nextInt(); 
					continue; 
				}
			}
			
			System.out.println("This is the task that you are asking to delete, are you sure you want to delete it? (Y/N)"); 
			
			System.out.print("   Done?");
			System.out.printf("%12s", "Due Date"); 
			System.out.printf("%13s", "Team member"); 
			System.out.printf("%22s", "Description");
			System.out.println(""); 
			
			System.out.print(tasks.get(userInput - 1).getCompletionStatus()); 
			System.out.printf("%17s", tasks.get(userInput - 1).getDueDate()); 
			System.out.printf("%8s", tasks.get(userInput -  1).getTeamMembersName()); 
			System.out.printf("%" + (29 - tasks.get(userInput - 1).getTeamMembersName().length()) + "s", tasks.get(userInput - 1).getBriefDescriptionOfTask()); 
			System.out.println("");
			
			boolean validInput2 = false;
			String userInput2 = ""; 
			char ans = 'n'; 
			while (validInput2 == false) {
				try {
					userInput2 = scan.next(); 
					userInput2 = userInput2.toLowerCase(); 
					ans = userInput2.charAt(0); 
					if (!(ans == 'y') &&  !(ans == 'n')) {
						throw new IllegalArgumentException(); 
					}
					validInput2 = true; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid answer. Please try again: "); 
					scan.nextLine(); 
					userInput2 = scan.next(); 
					continue; 
				}
			}
			if (ans == 'y') {
				System.out.println("Task deleted!"); 
				tasks.remove(userInput - 1); 
			}
		}
	}
	
	public static void markTaskComplete(ArrayList<Task> tasks, Scanner scan) {
		if (tasks.isEmpty()) {
			System.out.println("Sorry, list is empty"); 
		} else {
			System.out.println("Which task would you like to mark complete? They are numbered in the order they were inserted.  ");
			boolean validInput = false;
			int userInput = 0; 
			while (validInput == false) {
				try {
					userInput = scan.nextInt(); 
					if (userInput < 1 ||  userInput > tasks.size()) {
						throw new IllegalArgumentException(); 
					}
					validInput = true; 
				} catch (InputMismatchException e) {
					System.out.println("Sorry, this is not a valid task number. Please try again: "); 
					scan.nextLine(); 
					userInput = scan.nextInt(); 
					continue; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid task number. Please try again: "); 
					scan.nextLine(); 
					userInput = scan.nextInt(); 
					continue; 
				}
			}
			
			System.out.println("This is the task that you are asking to delete, are you sure you want to mark it complete? (Y/N)"); 
			
			System.out.print("   Done?");
			System.out.printf("%12s", "Due Date"); 
			System.out.printf("%13s", "Team member"); 
			System.out.printf("%22s", "Description");
			System.out.println(""); 
			
			System.out.print(tasks.get(userInput - 1).getCompletionStatus()); 
			System.out.printf("%17s", tasks.get(userInput - 1).getDueDate()); 
			System.out.printf("%8s", tasks.get(userInput -  1).getTeamMembersName()); 
			System.out.printf("%" + (29 - tasks.get(userInput - 1).getTeamMembersName().length()) + "s", tasks.get(userInput - 1).getBriefDescriptionOfTask()); 
			System.out.println("");
			
			boolean validInput2 = false;
			String userInput2 = ""; 
			char ans = 'n'; 
			while (validInput2 == false) {
				try {
					userInput2 = scan.next(); 
					userInput2 = userInput2.toLowerCase(); 
					ans = userInput2.charAt(0); 
					if (!(ans == 'y') &&  !(ans == 'n')) {
						throw new IllegalArgumentException(); 
					}
					validInput2 = true; 
				} catch (IllegalArgumentException e) {
					System.out.println("Sorry, this is not a valid answer. Please try again: "); 
					scan.nextLine(); 
					userInput2 = scan.next(); 
					continue; 
				}
			}
			if (ans == 'y') {
				System.out.println("Task marked complete!"); 
				Task temp = tasks.get(userInput - 1); 
				temp.setCompletionStatus(true); 
				tasks.set(userInput - 1, temp); 
			}
			
		
		}
		
	}
	
	
		
		
		
		
	

}
