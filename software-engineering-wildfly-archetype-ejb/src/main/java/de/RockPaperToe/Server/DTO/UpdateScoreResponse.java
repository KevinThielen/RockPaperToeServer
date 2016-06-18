package de.RockPaperToe.Server.DTO;

public class UpdateScoreResponse extends DTO{

	private static final long serialVersionUID = 1L;
    
	private int score;
	
	public int getNewScore(){
		return score;
	}
	
	public void setNewScore(int score){
		this.score = score;
	}
}
