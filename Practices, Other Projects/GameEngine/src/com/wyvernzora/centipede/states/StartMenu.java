/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/states/StartMenu.java
 * -----------------------------------------------------------------------
 * 
 * Game start menu. Game Title
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
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableCompositeGameComponent;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.barlog.components.GameState;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.graphics.TextButton;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.SoundEffect;

public class StartMenu extends GameState {

	public static final int DEFAULT_SCALE = 20;
	public static final int MENU_FONT = 24;

	CentipedeGame game = (CentipedeGame) super.game;
	DrawableCompositeGameComponent m_start;
	DrawableCompositeGameComponent m_difficulty;

	public StartMenu(Game gm) {
		super(gm);

		DrawableGameComponent title = new DrawableGameComponent(game) {

			Image img = ContentPipeline.getResource("TITLE");
			Rectangle bounds = new Rectangle(Utilities.reScale(100,
					DEFAULT_SCALE, CentipedeGame.TILE_SIZE), Utilities.reScale(
					60, DEFAULT_SCALE, CentipedeGame.TILE_SIZE),
					Utilities.reScale(400, DEFAULT_SCALE,
							CentipedeGame.TILE_SIZE), Utilities.reScale(200,
							DEFAULT_SCALE, CentipedeGame.TILE_SIZE));

			@Override
			public void update(long time) {

			}

			@Override
			public void draw(Graphics2D g) {
				g.drawImage(img, bounds.x, bounds.y, (int) bounds.getMaxX(),
						(int) bounds.getMaxY(), 0, 0, 400, 200,
						game.getImageObserver());
			}

		};

		this.addComponent(title);

		m_start = new DrawableCompositeGameComponent(game);
		
		TextButton startGame = new TextButton(game, 
				Utilities.reScale(new Rectangle(200, 400, 200, 30), DEFAULT_SCALE, CentipedeGame.TILE_SIZE), "Start Game",
				ContentPipeline.getFont("GLOBAL_FONT", Font.BOLD, Utilities.reScale(MENU_FONT, DEFAULT_SCALE, CentipedeGame.TILE_SIZE)),
				new Color[] {
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL
		}, new Color[] {
			new Color(255, 255, 255, 190),
			new Color(255,150,255,190),
			new Color(255,86,167,190)
		}, 3.0f){

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1) && newState.isButtonUp(MouseEvent.BUTTON1)) {
					startGame_click();
				}
			}
			
		};		
		m_start.addComponent(startGame);

		
		TextButton highScores = new TextButton(game, 
				Utilities.reScale(new Rectangle(190, 440, 220, 30), DEFAULT_SCALE, CentipedeGame.TILE_SIZE), "High Scores",
				ContentPipeline.getFont("GLOBAL_FONT", Font.BOLD, Utilities.reScale(MENU_FONT, DEFAULT_SCALE, CentipedeGame.TILE_SIZE)),
				new Color[] {
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL
		}, new Color[] {
			new Color(255, 255, 255, 190),
			new Color(255,150,255,190),
			new Color(255,86,167,190)
		}, 3.0f){

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1) && newState.isButtonUp(MouseEvent.BUTTON1)) {
					highScores_click();
				}
			}
			
		};	
		m_start.addComponent(highScores);

		this.addComponent(m_start);

		m_difficulty = new DrawableCompositeGameComponent(game);
		
		
		TextButton easy = new TextButton(game, 
				Utilities.reScale(new Rectangle(225, 360, 150, 30), DEFAULT_SCALE, CentipedeGame.TILE_SIZE), "Easy",
				ContentPipeline.getFont("GLOBAL_FONT", Font.BOLD, Utilities.reScale(MENU_FONT, DEFAULT_SCALE, CentipedeGame.TILE_SIZE)),
				new Color[] {
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL
		}, new Color[] {
			new Color(255, 255, 255, 190),
			new Color(255,150,255,190),
			new Color(255,86,167,190)
		}, 3.0f){

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1) && newState.isButtonUp(MouseEvent.BUTTON1)) {
					difficultySelected(CentipedeGame.Difficulty.EASY);
				}
			}
			
		};
		
		TextButton normal = new TextButton(game, 
				Utilities.reScale(new Rectangle(225, 400, 150, 30), DEFAULT_SCALE, CentipedeGame.TILE_SIZE), "Normal",
				ContentPipeline.getFont("GLOBAL_FONT", Font.BOLD, Utilities.reScale(MENU_FONT, DEFAULT_SCALE, CentipedeGame.TILE_SIZE)),
				new Color[] {
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL
		}, new Color[] {
			new Color(255, 255, 255, 190),
			new Color(255,150,255,190),
			new Color(255,86,167,190)
		}, 3.0f){

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1) && newState.isButtonUp(MouseEvent.BUTTON1)) {
					difficultySelected(CentipedeGame.Difficulty.NORMAL);
				}
			}
			
		};

		TextButton hard = new TextButton(game, 
				Utilities.reScale(new Rectangle(225, 440, 150, 30), DEFAULT_SCALE, CentipedeGame.TILE_SIZE), "Hard",
				ContentPipeline.getFont("GLOBAL_FONT", Font.BOLD, Utilities.reScale(MENU_FONT, DEFAULT_SCALE, CentipedeGame.TILE_SIZE)),
				new Color[] {
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL,
			CentipedeGame.MENU_FILL
		}, new Color[] {
			new Color(255, 255, 255, 190),
			new Color(255,150,255,190),
			new Color(255,86,167,190)
		}, 3.0f){

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1) && newState.isButtonUp(MouseEvent.BUTTON1)) {
					difficultySelected(CentipedeGame.Difficulty.HARD);
				}
			}
			
		};

		m_difficulty.addComponent(easy);
		m_difficulty.addComponent(normal);
		m_difficulty.addComponent(hard);
		
		m_difficulty.disable();
		m_difficulty.hide();
		
		this.addComponent(m_difficulty);
		
		SoundEffect.loadMidi("/com/wyvernzora/resources/TITLE.mid");
		SoundEffect.startSequencer();
		
		game.showCursor();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON3){
			if (m_difficulty.isVisible()){
				m_difficulty.disable();
				m_difficulty.hide();
				m_start.enable();
				m_start.show();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	private void startGame_click() {
		m_start.disable();
		m_start.hide();
		
		m_difficulty.enable();
		m_difficulty.show();
	}

	private void highScores_click() {
		game.removeState();
		game.addState(new HighScoreState(game, null));
	}

	private void difficultySelected(CentipedeGame.Difficulty diff){
		game.setDifficulty(diff);
		SoundEffect.stopSequencer();
		game.removeState();
		game.startGame();
	}
	
}
