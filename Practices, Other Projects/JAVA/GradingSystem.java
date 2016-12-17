public class GradingSystem{
	double mquiz,fquiz,mexam,fexam,mseatwork,fseatwork,mcasestudies,fcasestudies,mlabactivities,flabactivities,mlabexam,flabexam,mattendance,fattendance;

	public GradingSystem(){
		mquiz = 0;
		fquiz = 0;
		mexam = 0;
		fexam = 0;
		mseatwork = 0;
		fseatwork = 0;
		mcasestudies = 0;
		fcasestudies = 0;
		mlabactivities = 0;
		flabactivities = 0;
		mlabexam = 0;
		flabexam = 0;
		mattendance = 0;
		fattendance = 0;
	}

	//Middle Term
	public void setMQuiz(double quiz){
		this.mquiz = quiz / 100 * .15;
	}

	public void setMExam(double exam){
		this.mexam = exam / 100 * .20;
	}

	public void setMSeatWork(double seatwork){
		this.mseatwork = seatwork / 100 * .10;
	}

	public void setMCaseStudies(double casestudies){
		this.mcasestudies = casestudies / 100 * .10;
	}

	public void setMLabActivities(double labactivities){
		this.mlabactivities = labactivities / 100 * .10;
	}

	public void setMLabExam(double labexam){
		this.mlabexam = labexam / 100 * .20;
	}

	public void setMAttendance(double attendance){
		this.mattendance = attendance / 100 * .10;
	}

	//Final Term
	public void setFQuiz(double quiz){
		this.fquiz = quiz / 100 * .15;
	}

	public void setFExam(double exam){
		this.fexam = exam / 100 * .20;
	}

	public void setFSeatWork(double seatwork){
		this.fseatwork = seatwork / 100 * .10;
	}

	public void setFCaseStudies(double casestudies){
		this.fcasestudies = casestudies / 100 * .10;
	}

	public void setFLabActivities(double labactivities){
		this.flabactivities = labactivities / 100 * .10;
	}

	public void setFLabExam(double labexam){
		this.flabexam = labexam / 100 * .20;
	}

	public double getMidTermGrade(){
		return mlabactivities + mquiz + mexam + mseatwork + mcasestudies + mlabexam;
	}
	public double getFinalTermGrade(){
		return flabactivities + fquiz + fexam + fseatwork + fcasestudies + flabexam;
	}

	public void setFAttendance(double attendance){
		this.fattendance = attendance / 100 * .10;
	}
}