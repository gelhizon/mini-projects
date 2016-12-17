public class MyMain {
	public static void main(String[] args){
		
		BasicGradingSystem gs = new BasicGradingSystem();
		
		gs.setMidAttendance(0);//format(1-100)
		gs.setMidAssignments(0,20,0,10,0,5,0,15,0,30);//format(assign, assign_items, assign, assign_items...)
		gs.setMidQuizzes(0,1,0,1,0,1);//format(quiz, quiz_items, quiz, quiz_items...)
		gs.setMidExam(0, 50);//format(exam, items); 

		gs.setFinalAttendance(0);//format(1-100)
		gs.setFinalAssignments(0,10,0,15,0,20,0,30,0,5);//format(assign, assign_items, assign, assign_items...)
		gs.setFinalQuizzes(0,1,0,1,0,1);//format(quiz, quiz_items, quiz, quiz_items...)
		gs.setFinalExam(40,50);//format(exam, items); 
		
		gs.setPassingGrade(60);//set passing grade format(1-100)
		
		System.out.println("Midterm Grade: " + gs.getMidGrade() + "\tEquivalent: " + gs.getMidScale());
		System.out.println("Final Grade: " + gs.getFinalGrade() + "\tEquivalent: " + gs.getFinalScale());
		System.out.println("Remarks: " + gs.getRemarks());
	}
}
