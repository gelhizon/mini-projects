/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/states/BeatGameState.java
 * -----------------------------------------------------------------------
 * 
 * Screen when you beat the game.
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.TextGraphics;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.barlog.components.GameState;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.HighScore;
import com.wyvernzora.centipede.HighScoreEntry;
import com.wyvernzora.centipede.graphics.TextButton;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.SoundEffect;

public class BeatGameState extends GameState {

	CentipedeGame game = (CentipedeGame) super.game;

	public static final int MENU_FONT = 24;
	public static final int DEFAULT_SCALE = 20;
	
	String name = "Player";
	int score = game.getScore();
	long time = game.getGameTime();
	int rank = HighScore.instance().highScorePosition(score, game.getDifficulty().ordinal());
	int trank = HighScore.instance().timeAttackPosition(time, game.getDifficulty().ordinal());
	DrawableGameComponent nameentry;
	int nameblink = 0;
	
	public BeatGameState(Game gm) {
		super(gm);

		this.addComponent(new DrawableGameComponent(game){

			Point center = Utilities.reScale(
					new Point(300, 100)
					, 20, CentipedeGame.TILE_SIZE);
			Font fnt = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(80, 20, CentipedeGame.TILE_SIZE));
			@Override
			public void update(long time) {
				}

			@Override
			public void draw(Graphics2D g) {
				TextGraphics.drawOutlinesStringCentered(g, "You Win", fnt, Color.WHITE, Color.RED, 3.0f, center);
			}
			
		});

		// Back To Title Button
		TextButton backToTitle = new TextButton(game, Utilities.reScale(
				new Rectangle(215, 570, 170, 25), 20,
				CentipedeGame.TILE_SIZE), (trank >= 0 || rank >= 0) ? "Continue" : "Back To Title",
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
					
					if ((rank >= 0 || trank >= 0) && name.length() == 0){
						nameblink = 20;
						SoundEffect.NEGATIVE.play();
						return;
					} 
					
					if (rank >= 0 || trank >= 0){
						
						HighScoreEntry entry = new HighScoreEntry(-1, name, game.getDifficulty().ordinal(), score, time);
						if (rank >= 0) HighScore.instance().insertHighScore(entry, rank);
						if (trank >= 0) HighScore.instance().insertTimeAttack(entry, trank);
						HighScore.instance().save();
					
						((CentipedeGame) game).resetGame();
						game.removeState();
						//game.addState(new StartMenu(game));
						game.addState(new HighScoreState(game, entry));
					} else {
						((CentipedeGame) game).resetGame();
						game.removeState();
						game.addState(new StartMenu(game));
					}
					
				}
			}

		};
	
		// Draw player score
		this.addComponent(new DrawableGameComponent(game){

			int y = 190;
			Font titleFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.ITALIC, Utilities.reScale(18, DEFAULT_SCALE, 20));
			Point c0 = Utilities.reScale(new Point(300, y), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l0 = Utilities.reScale(new Point(150, y + 10), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l1 = Utilities.reScale(new Point(450, y + 10), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Font scoreFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(60, DEFAULT_SCALE, 20));
			Point c1 = Utilities.reScale(new Point(300, y + 35), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			
			Color headerColor = Color.RED;
			String headerText = rank >= 0 ? "New High Score" : "Your Score";
			int blinkCoeff = 50;
			int cbCoeff = blinkCoeff;
			boolean blink = rank >= 0;
			
			@Override
			public void update(long time) {
				if (blink) {
					if (cbCoeff > 0)
						cbCoeff--;
					else {
						cbCoeff = blinkCoeff;
						
						if (headerColor == Color.RED) headerColor = Color.YELLOW;
						else headerColor = Color.RED;
					}
				}
			}

			@Override
			public void draw(Graphics2D g) {
				TextGraphics.drawOutlinesStringCentered(g, headerText, titleFont, Color.WHITE, headerColor, 2.0f, c0);
				TextGraphics.drawOutlinesStringCentered(g, String.valueOf(score), scoreFont, Color.WHITE, Color.RED, 3.0f, c1);
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.WHITE);
				g.drawLine(l0.x, l0.y, l1.x, l1.y);
			}
			
		});
		
		// Draw player time
		this.addComponent(new DrawableGameComponent(game){

			int y = 270;
			Font titleFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.ITALIC, Utilities.reScale(18, DEFAULT_SCALE, 20));
			Point c0 = Utilities.reScale(new Point(300, y), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l0 = Utilities.reScale(new Point(150, y + 10), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l1 = Utilities.reScale(new Point(450, y + 10), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Font scoreFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(60, DEFAULT_SCALE, 20));
			Point c1 = Utilities.reScale(new Point(300, y + 35), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			
			Color headerColor = Color.RED;
			String headerText = trank >= 0 ? "New Speed Record" : "You Beated the Game In";
			int blinkCoeff = 50;
			int cbCoeff = blinkCoeff;
			boolean blink = trank >= 0;
			
			@Override
			public void update(long time) {
				if (blink) {
					if (cbCoeff > 0)
						cbCoeff--;
					else {
						cbCoeff = blinkCoeff;
						
						if (headerColor == Color.RED) headerColor = Color.YELLOW;
						else headerColor = Color.RED;
					}
				}
			}

			@Override
			public void draw(Graphics2D g) {
				TextGraphics.drawOutlinesStringCentered(g, headerText, titleFont, Color.WHITE, headerColor, 2.0f, c0);
				TextGraphics.drawOutlinesStringCentered(g, Utilities.millisToTime(time), scoreFont, Color.WHITE, Color.RED, 3.0f, c1);
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.WHITE);
				g.drawLine(l0.x, l0.y, l1.x, l1.y);
			}
			
		});
		
		// Draw Player name input box
		nameentry = new DrawableGameComponent(game){

			int y = 400;
			Font titleFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.ITALIC, Utilities.reScale(18, DEFAULT_SCALE, 20));
			Point c0 = Utilities.reScale(new Point(300, y), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l0 = Utilities.reScale(new Point(150, y + 10), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l1 = Utilities.reScale(new Point(450, y + 10), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l2 = Utilities.reScale(new Point(150, y + 70), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Point l3 = Utilities.reScale(new Point(450, y + 70), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			Font scoreFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(60, DEFAULT_SCALE, 20));
			Point c1 = Utilities.reScale(new Point(300, y + 35), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
			
			
			Color headerColor = Color.RED;
			int blinkCoeff = 20;
			int cbCoeff = blinkCoeff;
			boolean blink = rank >= 0;
			
			@Override
			public void update(long time) {
				if (blink) {
					if (cbCoeff > 0)
						cbCoeff--;
					else {
						cbCoeff = blinkCoeff;
						
						if (nameblink > 0){
							if (headerColor == Color.RED) headerColor = Color.YELLOW;
							else headerColor = Color.RED;
							
							nameblink--;
						} else {
							headerColor = Color.red;
						}
						
					}
				}
			}

			@Override
			public void draw(Graphics2D g) {
				TextGraphics.drawOutlinesStringCentered(g, "Please Enter Your Name", titleFont, Color.WHITE, headerColor, 2.0f, c0);
				TextGraphics.drawOutlinesStringCentered(g, name, scoreFont, Color.WHITE, Color.RED, 3.0f, c1);
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.WHITE);
				g.drawLine(l0.x, l0.y, l1.x, l1.y);
				g.drawLine(l2.x, l2.y, l3.x, l3.y);
			}
			
		};
		if (rank >= 0 || trank >= 0)
		{
			this.addComponent(nameentry);
		}
		
		this.addComponent(backToTitle);
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
		char c = e.getKeyChar();
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9')){
			if (name.length() < CentipedeGame.MAX_NAME_LEN){
				String tmp = String.valueOf(e.getKeyChar());
				name += e.isShiftDown() ? tmp.toUpperCase() : tmp.toLowerCase();
			}
		} else if (c == '\b'){
			if (name.length() >= 1) name = name.substring(0, name.length() - 1);
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
	
}
