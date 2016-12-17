public class ArcadeGame extends Player{
	String gametype, gamemode;
	int score, maxscore, time;

	public void setGameType(String gametype){
		this.gametype = gametype;
	}
	public void setGameMode(String gamemode){
		this.gamemode = gamemode;
	}
	public void setScore(int score){
		this.score = score;
	}
	public void setMaxScore(int maxscore){
		this.maxscore = maxscore;
	}
	public void setTime(int time){
		this.time = time;
	}
	public String getGameType(){
		return gametype;
	}
	public String getGameMode(){
		return gamemode;
	}
	public int getScore(){
		return score;
	}
	public int getMaxScore(){
		return maxscore;
	}
	public int getTime(){
		return time;
	}
	public void resetGame(){
		gamemode = null;
		score = 0;
		maxscore = 0;
		time = 0;		
	}
	public void reset(){
		gametype = null;
		gamemode = null;
		score = 0;
		maxscore = 0;
		time = 0;
	}
}