public class BasicGradingSystem {
	private double mid_quiz;
	private double final_quiz;
	private double mid_attendance;
	private double final_attendance;
	private double mid_exam;
	private double final_exam;
	private double mid_assignment;
	private double final_assignment;
	private double passinggrade;

	public BasicGradingSystem() {
		this.mid_attendance = 0;
		this.final_attendance = 0;
		this.mid_assignment = 0;
		this.final_assignment = 0;
		this.mid_quiz = 0;
		this.final_quiz = 0;
		this.mid_exam = 0;
		this.final_exam = 0;
		this.passinggrade = 60;
	}

	public void setMidAttendance(double mid_attendance) {
		this.mid_attendance = mid_attendance;
	}

	public void setFinalAttendance(double final_attendance) {
		this.final_attendance = final_attendance;
	}

	public void setMidAssignments(double assign1, double assign1_items, double assign2, double assign2_items, double assign3, double assign3_items, double assign4, double assign4_items, double assign5,double assign5_items) {
		this.mid_assignment = (((assign1 / assign1_items) + (assign2 / assign2_items) + (assign3 / assign3_items) + (assign4 / assign4_items) + (assign5 / assign5_items)) / 5) * 100;
	}

	public void setFinalAssignments(double assign1, double assign1_items, double assign2, double assign2_items, double assign3, double assign3_items, double assign4, double assign4_items, double assign5,double assign5_items){
		this.final_assignment = (((assign1 / assign1_items) + (assign2 / assign2_items) + (assign3 / assign3_items) + (assign4 / assign4_items) + (assign5 / assign5_items)) / 5) * 100;
	}
	
	public void setMidQuizzes(double quiz1, double quiz1_items, double quiz2, double quiz2_items, double quiz3, double quiz3_items){
		this.mid_quiz = (((quiz1 / quiz1_items) + (quiz2 / quiz2_items) + (quiz3 / quiz3_items)) / 3) * 100;
	}
	
	public void setFinalQuizzes(double quiz1, double quiz1_items, double quiz2, double quiz2_items, double quiz3, double quiz3_items){
		this.final_quiz = (((quiz1 / quiz1_items) + (quiz2 / quiz2_items) + (quiz3 / quiz3_items)) / 3) * 100;
	}
	
	public void setMidExam(double exam, double exam_items){
		this.mid_exam = (exam / exam_items) * 100; 
	}
	
	public void setFinalExam(double exam, double exam_items){
		this.final_exam = ( exam / exam_items) * 100; 
	}
	
	public void setPassingGrade(double passinggrade){
		this.passinggrade = passinggrade;
	}
	
	public double getMidGrade(){
		return (mid_attendance * .10) + (mid_assignment * .20) + (mid_quiz * .30) + (mid_exam * .40);		
	}
	
	public double getFinalGrade(){
		return (getMidGrade() + ((final_attendance * .10) + (final_assignment * .20) + (final_quiz * .30) + (final_exam * .40))) / 2.0;
	}
	
	public String getRemarks(){
		if(getFinalGrade() >= passinggrade){
			return "PASSED";
		}else{
			return "FAILED";
		}
	}
	
	public double getMidScale(){
		if(getMidGrade() >= passinggrade){
			return Math.abs(((getMidGrade() - passinggrade) * 2 / (100 - passinggrade)) -3);
		}else{
			return Math.abs((getMidGrade() * 2 / passinggrade) -3) + 2;
		}
	}
	
	public double getFinalScale(){
		if(getFinalGrade() >= passinggrade){
			return Math.abs(((getFinalGrade() - passinggrade) * 2 / (100 - passinggrade)) -3);
		}else{
			return Math.abs((getFinalGrade() * 2 / passinggrade) -3) + 2;
		}
	}

}
