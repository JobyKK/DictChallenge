package logic;

public class TestProgressController {

	private static Integer testMode;
	private static Float score;
	private static Boolean saveScore;
	private static long time;
	
	/**Choose mode: 0: random 
	 * 1: 1 - 2 lang
	 * 2: 2 - 1 lang*/
	public void setTestMode(Integer mode){
		//0: random
		//1: 1 - 2 lang
		//2: 2 - 1 lang
		testMode = mode;
	}
	public Integer getTestMode(){
		return testMode;
	}

	public void setScore(float sc){
		score = sc;
	}
	public Float getScore(){
		return score;
	}
	
	public void submitScore(){
		Program.submitScore(score);
	}
	
	public void setSaveScore(boolean sc){
		saveScore = sc;
	}
	public boolean getSaveScore(){
		return saveScore;
	}
	
	public void setTime(long t){
		time = t;
	}
	public long getTime(){
		return time;
	}
	
	
}
