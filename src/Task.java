
public class Task {
	String teamMembersName; 
	String briefDescriptionOfTask; 
	String dueDate; 
	boolean isComplete; 
	
	public Task (String memberName, String description, String due) {
		teamMembersName = memberName; 
		briefDescriptionOfTask = description; 
		dueDate = due; 
	}
	
	public void setTeamMembersName(String name) {
		teamMembersName = name; 
	}
	
	public String getTeamMembersName() {
		return teamMembersName;  
	}
	
	public void setBriefDescriptionOfTask(String desc) {
		briefDescriptionOfTask = desc; 
	}
	
	public String getBriefDescriptionOfTask() {
		return briefDescriptionOfTask;  
	}
	
	public void setDueDate(String date) {
		dueDate = date; 
	}
	
	public String getDueDate() {
		return dueDate;  
	}
	
	public void setCompletionStatus(boolean status) {
		isComplete = status; 
	}
	
	public boolean getCompletionStatus() {
		return isComplete;  
	}

}
