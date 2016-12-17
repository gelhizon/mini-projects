/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou, Raghavendran Shankar
 * -----------------------------------------------------------------------
 *  /com/wyvernzora/centipede/HighScore.java
 * -----------------------------------------------------------------------
 * 
 * This is the high score I/O class, originally written by Raghavendran Shankar.
 * I modified it to make it a singleton, and added some error handling features to
 * ensure it never causes an unhandled exception.
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HighScore {
	
	private static HighScore _instance = null;
	public static HighScore instance(){
		if (_instance == null)
			_instance = new HighScore();
		return _instance;
	}
	
	protected static final String SAVE_FILE = "savedata.sav";
	protected static final int DEF_SCORE = 0;
	protected static final int DEF_TIME = 35999999;
	
	protected ArrayList<HighScoreEntry> highscores = new ArrayList<HighScoreEntry>();
	protected ArrayList<HighScoreEntry> timeattack = new ArrayList<HighScoreEntry>();
	
	protected HighScore(){
		load();
	}
	
	public void load(){
		
		Scanner scan = null;
		
		// Initializing file loading routine
		try{
			scan = new Scanner(new File(SAVE_FILE));
		} catch (FileNotFoundException ex){
			// File does not exist, initialize using defaults and flush
			init();
			save();
			return;
		} catch (Exception ex) {
			// Another error, delete the file if it's there and reset
			Launcher.reportError(Launcher.ErrorType.Prominent, this, "An unexpected error of type [%s] was unhandled while loading high scores!", ex.getClass().getName());
			delete();
			init();
			save();
			return;
		}
		
		// Verify file identifier
		String header = scan.nextLine();
		if (!header.equalsIgnoreCase("@com-wyvernzora-centipede")) {
			Launcher.reportError(Launcher.ErrorType.Prominent, this, "\nHigh scores file is corrupted!\nRestoring default values.");
			delete();
			init();
			save();
			return;
		}
		
		try {
			// skip all lines before high score identifier
			while (!scan.nextLine().equalsIgnoreCase("#high-score")) {
			}

			for (String line = scan.nextLine();; line = scan.nextLine()) {
				if (line.equalsIgnoreCase("#end"))
					break;

				String[] spl = line.split(":::");
				int id = Integer.valueOf(spl[0]);
				String uname = spl[1];
				int diffic = Integer.valueOf(spl[2]);
				int score = Integer.valueOf(spl[3]);
				long time = Long.valueOf(spl[4]);

				HighScoreEntry entry = new HighScoreEntry(id, uname, diffic,
						score, time);
				highscores.add(entry);
			}

			// skip all lines before high score identifier
			while (!scan.nextLine().equalsIgnoreCase("#time-attack")) {
			}

			for (String line = scan.nextLine();; line = scan.nextLine()) {
				if (line.equalsIgnoreCase("#end"))
					break;

				String[] spl = line.split(":::");
				int id = Integer.valueOf(spl[0]);
				String uname = spl[1];
				int diffic = Integer.valueOf(spl[2]);
				int score = Integer.valueOf(spl[3]);
				long time = Long.valueOf(spl[4]);

				HighScoreEntry entry = new HighScoreEntry(id, uname, diffic,
						score, time);
				timeattack.add(entry);
			}
		} catch (Exception ex) {
			Launcher.reportError(Launcher.ErrorType.Prominent, this,
					"\nFailed to load high scores!\nRestoring default values!\n\nError details: \n\tType = %s\n\tMessage = %s", ex.getClass().getName(), ex.getMessage());
			delete();
			init();
			save();
			return;
		}
	}
	
	public void init(){
		highscores.clear();
		timeattack.clear();
		for (int i = 0; i < 10; i++){
			highscores.add(new HighScoreEntry(-1, "-----", 0, DEF_SCORE, DEF_TIME));
			timeattack.add(new HighScoreEntry(-1, "-----", 0, DEF_SCORE, DEF_TIME));
		}
	}
	
	public void save(){
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File(SAVE_FILE));
		} catch (Exception ex){
			Launcher.reportError(Launcher.ErrorType.Prominent, this, "An error of type [%s] occured while creating save file!\nOperation aborted!", ex.getClass().getName());
		}
		
		pw.println("@com-wyvernzora-centipede");
		pw.println("#high-score");
		for (HighScoreEntry e:highscores){
			pw.printf("%s:::%s:::%s:::%s:::%s\r\n", e.getID(), e.getUname(), e.getDifficulty().ordinal(), e.getScore(), e.getTime());
		}
		pw.println("#end");
		pw.println("#time-attack");
		for (HighScoreEntry e:timeattack){
			pw.printf("%s:::%s:::%s:::%s:::%s\r\n", e.getID(), e.getUname(), e.getDifficulty().ordinal(), e.getScore(), e.getTime());
		}
		pw.println("#end");
		
		pw.close();
	}
	
	public void delete(){
		File f = new File(SAVE_FILE);
		if (f.exists()){
			f.delete();
		}
	}
		
	public int highScorePosition(int score, int difficulty){
		for (int i = 0; i < highscores.size(); i++){
			if (score < highscores.get(i).getScore()) continue;
			else if (score > highscores.get(i).getScore()) return i;
			else { // Equal
				if (difficulty <= highscores.get(i).getDifficulty().ordinal()) continue;
				else return i;
			}
		}
		return -1;
	}
	
	public int timeAttackPosition(long time, int difficulty){
		for (int i = 0; i < timeattack.size(); i++){
			if (time > timeattack.get(i).getTime()) continue;
			else if (time < timeattack.get(i).getTime()) return i;
			else { // Equal
				if (difficulty <= timeattack.get(i).getDifficulty().ordinal()) continue;
				else return i;
			}
		}
		return -1;
	}

	public void insertHighScore(HighScoreEntry hsr, int pos){
		highscores.add(pos, hsr);
		highscores.remove(highscores.size() - 1);
	}
	
	public void insertTimeAttack(HighScoreEntry hsr, int pos){
		timeattack.add(pos, hsr);
		timeattack.remove(timeattack.size() - 1);
	}

	public ArrayList<HighScoreEntry> getHighScores(){
		return highscores;
	}
	
	public ArrayList<HighScoreEntry> getTimeAttack(){
		return timeattack;
	}
}
