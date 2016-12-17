/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * 
 * High score entry. Originally written by Raghavendran Shankar.
 * Nothing unusual.
 * 
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.wyvernzora.centipede;

public class HighScoreEntry {
	private String uname;
	private int score;
	private long time;
	private CentipedeGame.Difficulty diff;
	private int id;
	
	public HighScoreEntry(int id, String uname, int diff, int score, long time){
		if (id >= 0) this.id = id;
		else this.id = this.hashCode();
		this.diff = CentipedeGame.Difficulty.values()[diff];
		this.uname = uname;
		this.score = score;
		this.time = time;
	}
	
	public String getUname(){
		return uname;
	}
	
	public int getScore(){
		return score;
	}
	
	public long getTime(){
		return time;
	}

	public CentipedeGame.Difficulty getDifficulty(){
		return diff;
	}

	public int getID() {
		return id;
	}
}
