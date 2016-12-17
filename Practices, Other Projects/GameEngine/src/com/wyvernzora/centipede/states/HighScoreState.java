/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/states/HighScores.java
 * -----------------------------------------------------------------------
 * 
 * High Score screen.
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
package com.wyvernzora.centipede.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.TextGraphics;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableCompositeGameComponent;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.barlog.components.GameState;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.HighScore;
import com.wyvernzora.centipede.HighScoreEntry;
import com.wyvernzora.centipede.graphics.TextButton;
import com.wyvernzora.resources.ContentPipeline;

public class HighScoreState extends GameState {

	private final int SWITCH_TIME = 10000;
	
	public enum DisplayMode {
		HIGH_SCORE,
		TIME_ATTACK
	}
	
	boolean autoswitch = true;
	DisplayMode mode = DisplayMode.HIGH_SCORE;
	int switchCoeff = SWITCH_TIME;
	
	public static final int MENU_FONT = 24;
	public static final int DEFAULT_SCALE = 20;
	public static final int ALP_COEFF = 0;
	public static final int SWITCH_COEFF = 500;
	
	private int alpha = 0;
	private int[] alpha_offset = { 0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200 }; 
	private boolean fadeIn = true;
	private boolean fadeOut = false;
	private int caCoeff = ALP_COEFF;
	private int swCoeff = SWITCH_COEFF;
	
	String title = "High Scores";
	
	HighScoreEntry highlight;
	
	DrawableCompositeGameComponent highScores;
	DrawableCompositeGameComponent timeAttack;
	
	public HighScoreState(Game gm, HighScoreEntry entry) {
		super(gm);
	
		// save highlighted entry
		this.highlight = entry;
		if (highlight == null) highlight = new HighScoreEntry(-1, "DUMMY", 0, 0, 0);
		
		// Title
		this.addComponent(new DrawableGameComponent(game){

			Font fnt = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(80, 20, CentipedeGame.TILE_SIZE));
			Point center = Utilities.reScale(new Point(300, 60), 20, CentipedeGame.TILE_SIZE);
			Color outline = Color.white;
			Color fill = Color.RED;
			
			@Override
			public void update(long time) {
				outline = Utilities.alphaBlend(Color.white, coerceAlpha(alpha));
				fill = Utilities.alphaBlend(Color.red, coerceAlpha(alpha));
			}

			@Override
			public void draw(Graphics2D g) {
				TextGraphics.drawOutlinesStringCentered(g, title, fnt, outline, fill, 3.0f, center);
			}
			
		});
		
		// Back To Title Button
		TextButton backToTitle = new TextButton(game, Utilities.reScale(
				new Rectangle(215, 570, 170, 25), 20,
				CentipedeGame.TILE_SIZE), "Back To Title",
				ContentPipeline.getFont("GLOBAL_FONT", Font.BOLD, Utilities
						.reScale(MENU_FONT, DEFAULT_SCALE,
								CentipedeGame.TILE_SIZE)), new Color[] {
						CentipedeGame.MENU_FILL, CentipedeGame.MENU_FILL, CentipedeGame.MENU_FILL },
				new Color[] { new Color(255, 255, 255, 190),
						new Color(255, 150, 255, 190),
						new Color(255, 86, 167, 190) }, 3.0f) {
			
			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1)
						&& newState.isButtonUp(MouseEvent.BUTTON1)) {
					game.removeState();
					game.addState(new StartMenu(game));
				}
			}

		};		
		this.addComponent(backToTitle);
	
		// Draw Text Stuff
		highScores = new DrawableCompositeGameComponent(game);
		ArrayList<HighScoreEntry> alhs = HighScore.instance().getHighScores();
		for (int i = 0; i < alhs.size(); i++){
			highScores.addComponent(new Entry(game, DisplayMode.HIGH_SCORE, alhs.get(i), i));
		}
		this.addComponent(highScores);
		
		timeAttack = new DrawableCompositeGameComponent(game);
		ArrayList<HighScoreEntry> tahs  = HighScore.instance().getTimeAttack();
		for (int i = 0; i < tahs.size(); i++){
			timeAttack.addComponent(new Entry(game, DisplayMode.TIME_ATTACK, tahs.get(i), i));
		}
		timeAttack.hide();
		this.addComponent(timeAttack);
	}

	@Override
	public void update(long time){
		super.update(time);
		if (caCoeff > 0) caCoeff--;
		else{
			caCoeff = ALP_COEFF;
			
			if (fadeIn){
				alpha++;
				if (alpha >= (255 + alpha_offset[alpha_offset.length - 1])) {
					fadeIn = false;
				}
			}
			if (fadeOut){
				alpha--;
				if (alpha == 0){
					fadeOut = false;
				}
			}
		}
		
		if (swCoeff > 0) swCoeff --;
		else {
			swCoeff = SWITCH_COEFF;
			
			if (highScores.isVisible()){
				alpha = 0;
				highScores.hide();
				timeAttack.show();
				fadeIn = true;
				title = "Time Attack";
			} else {
				alpha = 0;
				timeAttack.hide();
				highScores.show();
				fadeIn = true;
				title = "High Scores";
			}
		}
	}
	
	private int coerceAlpha(int a){
		if (a < 0) return 0;
		if (a > 255) return 255;
		else return a;
	}
		
	@Override

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == 'f'){
			fadeOut = true;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	
	private class Entry extends DrawableGameComponent {

		final Color OUTLINE = Color.WHITE;
		Color FILL = Color.RED;
		final Color BLINK = Color.YELLOW;
		final Color[] DIFFC = { Color.GREEN, Color.BLUE, Color.RED };
		final int SPACING = 40;
		final int ORIGIN =  Utilities.reScale(150, 20, CentipedeGame.TILE_SIZE);
		final int RANK_X =  Utilities.reScale(20, 20, CentipedeGame.TILE_SIZE);
		final int NAME_X =  Utilities.reScale(100, 20, CentipedeGame.TILE_SIZE);
		final int DIFF_X =  Utilities.reScale(400, 20, CentipedeGame.TILE_SIZE);
		final int SCR_X =  Utilities.reScale(470, 20, CentipedeGame.TILE_SIZE);
		final int TIME_POS = Utilities.reScale(450, 20, CentipedeGame.TILE_SIZE); 
		
		int ypos; int index;
		Color outline = OUTLINE;
		Color fill = FILL;
		HighScoreEntry entry;
		DisplayMode mode;
		Font fnt = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.PLAIN, Utilities.reScale(30, 20, CentipedeGame.TILE_SIZE));
		
		String rank;
		String name;
		String diff;
		
		int ea = 0;
		boolean blinking = false;
		int blinkCoeff = 50;
		int cbCoeff = blinkCoeff;
		
		
		public Entry(Game gm, DisplayMode mode, HighScoreEntry e, int index) {
			super(gm);
			this.ypos = Utilities.reScale(index * SPACING + ORIGIN, 20,CentipedeGame.TILE_SIZE);
			this.index = index;
			entry = e;
			this.mode = mode;
			rank = "#"+(index + 1);
			name = entry.getUname();
			diff = String.valueOf(entry.getDifficulty().toString().charAt(0));
			blinking = highlight.getID() == e.getID();
		}

		@Override
		public void update(long time) {

			ea = coerceAlpha(alpha - alpha_offset[index + 1]);
			
			outline = Utilities.alphaBlend(OUTLINE, ea);

			if (cbCoeff > 0) cbCoeff--;
			else {
				cbCoeff = blinkCoeff;
				if (blinking){
					if (FILL == Color.RED) FILL = BLINK;
					else FILL = Color.RED;
				}
			}
			
			fill = Utilities.alphaBlend(FILL, ea);
		}

		@Override
		public void draw(Graphics2D g) {
			if (mode == DisplayMode.HIGH_SCORE){
				//Rectangle2D rect = TextGraphics.getStringBoundingRect(g, "#" + index, fnt);
				//TextGraphics.drawOutlinedString(g, hscontent, fnt, outline, fill, 3.0f, new Point(0, ypos));
				TextGraphics.drawOutlinedString(g, rank, fnt, outline, fill, 3.0f, new Point(RANK_X, ypos));
				TextGraphics.drawOutlinedString(g, name, fnt, outline, fill, 3.0f, new Point(NAME_X, ypos));
				TextGraphics.drawOutlinedString(g, diff, fnt, outline, Utilities.alphaBlend(DIFFC[entry.getDifficulty().ordinal()], ea) , 3.0f, new Point(DIFF_X, ypos));
				TextGraphics.drawOutlinedString(g, String.valueOf(entry.getScore()), fnt, outline, fill, 3.0f, new Point(SCR_X, ypos));
				
			} else {
				TextGraphics.drawOutlinedString(g, rank, fnt, outline, fill, 3.0f, new Point(RANK_X, ypos));
				TextGraphics.drawOutlinedString(g, name, fnt, outline, fill, 3.0f, new Point(NAME_X, ypos));
				TextGraphics.drawOutlinedString(g, diff, fnt, outline, Utilities.alphaBlend(DIFFC[entry.getDifficulty().ordinal()], ea) , 3.0f, new Point(DIFF_X, ypos));
				TextGraphics.drawOutlinedString(g, Utilities.millisToTime(entry.getTime()), fnt, outline, fill, 3.0f, new Point(TIME_POS, ypos));
	
			}
		}
		
		
	}
}
