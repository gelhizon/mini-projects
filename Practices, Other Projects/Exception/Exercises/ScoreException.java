public class ScoreException extends Exception{
	public ScoreException(int score) throws ScoreException{
		super("ScoreException: " + score);
	}
}	