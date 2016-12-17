/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/states/ScoreBoard.java
 * -----------------------------------------------------------------------
 * 
 * Weapon Upgrade Menu
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
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.KeyState;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.TextGraphics;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableCompositeGameComponent;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.barlog.components.GameState;
import com.wyvernzora.barlog.controls.ButtonBase;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.graphics.TextButton;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.SoundEffect;

public class ScoreBoard extends GameState {

	public static final int DEFAULT_SCALE = 20;
	public static final int SPEED = 20;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final Color DEFAULT_BG = Color.DARK_GRAY;

	GameState next;
	CentipedeGame game = (CentipedeGame) super.game;
	long tunnelUpdate = 0;
	boolean collapsed = true;
	int alpha = 180;

	int origin = CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE;
	Image ship;
	Rectangle shipBox = Utilities.reScale(new Rectangle(3, 3, 30, 30),
			DEFAULT_SCALE, CentipedeGame.TILE_SIZE);

	Image texture = ContentPipeline.getResource("SB_TEXTURE");
	Dimension textureBox = new Dimension(256, 256);

	Image grip = ContentPipeline.getResource("SB_GRIP");
	Dimension gripBox = new Dimension(128, 10);
	int gripHeight = Utilities.reScale(gripBox.height, DEFAULT_SCALE,
			CentipedeGame.TILE_SIZE);

	int fntSize = Utilities.reScale(20, DEFAULT_SCALE,
			CentipedeGame.TILE_SIZE);
	Font fnt = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.PLAIN, fntSize);
	
	Point org_lives = new Point(Utilities.reScale(33, DEFAULT_SCALE,
			CentipedeGame.TILE_SIZE), Utilities.reScale(33,
			DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
	Point org_score = new Point(Utilities.reScale(85, DEFAULT_SCALE,
			CentipedeGame.TILE_SIZE), Utilities.reScale(33,
			DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
	Point org_round = new Point(Utilities.reScale(270, DEFAULT_SCALE,
			CentipedeGame.TILE_SIZE), Utilities.reScale(33,
			DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
	
	UpgradeDescriptor descriptor;
	
	DrawableCompositeGameComponent m_menu;

	public ScoreBoard(Game gm) {
		super(gm);
		ship = ContentPipeline.getResource("SHIP_S_0");
		int fntSize = Utilities.reScale(25, DEFAULT_SCALE,
				CentipedeGame.TILE_SIZE);

		// Score Strip
		TextButton toggleLaser = new TextButton(game, Utilities.reScale(
				new Rectangle(CentipedeGame.BOARD_WIDTH * DEFAULT_SCALE - 200,
						15, 100, 25), DEFAULT_SCALE, CentipedeGame.TILE_SIZE),
				"[Laser]", ContentPipeline.getFont("GLOBAL_FONT", Font.PLAIN,
						fntSize),
				new Color[] { Color.RED, Color.RED, Color.RED }, new Color[] {
						Color.WHITE, new Color(255, 150, 255, 190),
						new Color(255, 86, 167, 190) }, 3.0f) {

			@Override
			public void update(long time) {
				super.update(time);
				super.offset.y = origin;

				if (game.getActiveWeapon() == CentipedeGame.Weapon.LASER) {
					this.show();
					this.fill = new Color[] { new Color(0, 104, 0, 255),
							new Color(0, 104, 0, 255),
							new Color(0, 104, 0, 255) };
					this.outline = new Color[] { Color.WHITE,
							new Color(120, 255, 210, 190),
							new Color(86, 255, 167, 190) };
				} else if (game.getActiveWeapon() == CentipedeGame.Weapon.MISSILE) {
					this.show();
					this.fill = new Color[] { Color.RED, Color.RED, Color.RED };
					this.outline = new Color[] { Color.WHITE,
							new Color(255, 150, 255, 190),
							new Color(255, 86, 167, 190) };
				} else {
					this.hide();
				}

			}

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1)
						&& newState.isButtonUp(MouseEvent.BUTTON1)) {
					if (game.getActiveWeapon() == CentipedeGame.Weapon.MISSILE) {
						game.setActiveWeapon(CentipedeGame.Weapon.LASER);
					} else if (game.getActiveWeapon() == CentipedeGame.Weapon.LASER) {
						game.setActiveWeapon(CentipedeGame.Weapon.MISSILE);
					}
				}
			}

		};
		this.addComponent(toggleLaser);

		TextButton toggleMainMenu = new TextButton(game, Utilities.reScale(
				new Rectangle(CentipedeGame.BOARD_WIDTH * DEFAULT_SCALE - 100,
						15, 100, 25), DEFAULT_SCALE, CentipedeGame.TILE_SIZE),
				"[Menu]", ContentPipeline.getFont("GLOBAL_FONT", Font.PLAIN,
						fntSize),
				new Color[] { Color.RED, Color.RED, Color.RED }, new Color[] {
						Color.WHITE, new Color(255, 150, 255, 190),
						new Color(255, 86, 167, 190) }, 3.0f) {

			@Override
			public void update(long time) {
				super.update(time);
				super.offset.y = origin;
				if (collapsed) {
					cap = "[Menu]";
				} else {
					cap = "[Close]";
				}
			}

			@Override
			protected void onMouseClick(MouseState oldState, MouseState newState) {
				if (oldState.isButtonDown(MouseEvent.BUTTON1)
						&& newState.isButtonUp(MouseEvent.BUTTON1)) {
					if (collapsed) {
						// collapsed = false;
						game.showMainMenu();
					} else {
						game.hideMainMenu();
						// collapsed = true;
					}
				}
			}

		};
		this.addComponent(toggleMainMenu);

		// Main menu and all the other stuff
		m_menu = new DrawableCompositeGameComponent(game);

		// WEAPON UPGRADE MENU
		m_menu.addComponent(new DrawableGameComponent(game) {

			Image img = ContentPipeline.getResource("WPUP_TITLE");
			Rectangle box = Utilities.reScale(new Rectangle(150, 55, 450, 40),
					20, CentipedeGame.TILE_SIZE);

			@Override
			public void update(long time) {

			}

			@Override
			public void draw(Graphics2D g) {
				g.drawImage(img, box.x, box.y + origin, box.x + box.width,
						box.y + box.height + origin, 0, 0, 450, 40,
						game.getImageObserver());
			}

		});

		WeaponButton missile1 = new WeaponButton(game, new Point(165, 95), CentipedeGame.Weapon.MISSILE, 1);
		missile1.setHeadString("Missile", 26, new Point(58, 20));
		missile1.setBottomString("Upgrade", 20, new Point(58, 100));
		
		WeaponButton missile2 = new WeaponButton(game, new Point(315, 95), CentipedeGame.Weapon.MISSILE, 2);
		missile2.setHeadString("Missile", 26, new Point(58, 20));
		missile2.setBottomString("Upgrade", 20, new Point(58, 100));
		
		WeaponButton missile3 = new WeaponButton(game, new Point(465, 95), CentipedeGame.Weapon.MISSILE, 3);
		missile3.setHeadString("Missile", 26, new Point(58, 20));
		missile3.setBottomString("Upgrade", 20, new Point(58, 100));

		m_menu.addComponent(missile1);
		m_menu.addComponent(missile2);
		m_menu.addComponent(missile3);

		WeaponButton laser1 = new WeaponButton(game, new Point(165, 230), CentipedeGame.Weapon.LASER, 1);
		laser1.setHeadString("Laser", 28, new Point(58, 20));
		laser1.setBottomString("Upgrade", 20, new Point(58, 100));
		
		WeaponButton laser2 = new WeaponButton(game, new Point(315, 230), CentipedeGame.Weapon.LASER, 2);
		laser2.setHeadString("Laser", 28, new Point(58, 20));
		laser2.setBottomString("Upgrade", 20, new Point(58, 100));
		
		WeaponButton laser3 = new WeaponButton(game, new Point(465, 230), CentipedeGame.Weapon.LASER, 3);
		laser3.setHeadString("Laser", 28, new Point(58, 20));
		laser3.setBottomString("Upgrade", 20, new Point(58, 100));

		m_menu.addComponent(laser1);
		m_menu.addComponent(laser2);
		m_menu.addComponent(laser3);

		MenuTextButton chainxp = new MenuTextButton(game, new Point(165, 365), "WPUP_CHAINEXPL", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				chainExplRequested();
			}
			
		}){
			CentipedeGame game = (CentipedeGame) super.game;
			Font fntGreen = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.PLAIN, Utilities.reScale(24, DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
			Font fntRed = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(22, DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
			Double rotation = -(Math.PI / 6);
			
			@Override
			protected void drawOverlay(Graphics2D g){
				if (((CentipedeGame) game).chainExplosionEnabled()) {
					TextGraphics.drawOutlinedStringRotated(g, "[Installed]", 
							fntGreen,
							Color.WHITE, new Color(0, 196, 0), 3.0f, new Point(65,60), rotation);
				} else if (((CentipedeGame) game).getDifficulty() == CentipedeGame.Difficulty.EASY) {
					TextGraphics.drawOutlinedStringRotated(g, "[Locked]", 
							fntRed,
							Color.WHITE, Color.RED, 3.5f, new Point(65,60), rotation);
				}
			}
		
			@Override
			protected void onMouseEnter(){
				super.onMouseEnter();
				if (game.chainExplosionEnabled()){
					descriptor.setDescription(-2, game.getUpgradeDescription(3, 0));
				} else if (game.getDifficulty() == CentipedeGame.Difficulty.EASY) {
					descriptor.setDescription(-1, game.getUpgradeDescription(-1, 0));
				} else {
					descriptor.setDescription(CentipedeGame.CHAIN_EXPL_PRICE, game.getUpgradeDescription(3, 0));
				}
			}
		};
		chainxp.setHeadString("Chain", 28, new Point(58, 20));
		chainxp.setBottomString("Explosion", 18, new Point(58, 100));
		m_menu.addComponent(chainxp);

		MenuTextButton megalaser = new MenuTextButton(game, new Point(315, 365), "WPUP_MEGALASER", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				megaLaserRequested();
			}
			
		}){
			
			
			@Override
			public void update(long time){
				super.update(time);
				this.setCenterString(String.format("x%d", ((CentipedeGame) game).getMegaLaserCount()), 45, new Point(60,60));
			}
			
			@Override
			protected void onMouseEnter(){
				super.onMouseEnter();
				descriptor.setDescription(CentipedeGame.MEGA_LASER_PRICE, ((CentipedeGame) game).getUpgradeDescription(2,0));
			}
			
		};
		megalaser.setHeadString("Mega", 28, new Point(58, 20));
		megalaser.setBottomString("Laser", 24, new Point(58, 100));
		m_menu.addComponent(megalaser);
		
		// GAME MENU
		m_menu.addComponent(new DrawableGameComponent(game) {

			Image img = ContentPipeline.getResource("MENU_TITLE");
			Rectangle box = Utilities.reScale(new Rectangle(0, 55, 150, 40),
					20, CentipedeGame.TILE_SIZE);

			@Override
			public void update(long time) {
				
			}

			@Override
			public void draw(Graphics2D g) {
				g.drawImage(img, box.x, box.y + origin, box.x + box.width,
						box.y + box.height + origin, 0, 0, 150, 40,
						game.getImageObserver());
			}

		});

		
		MenuTextButton resumeGame = new MenuTextButton(game, new Point(15,95), "MENU_ITEM", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.hideMainMenu();
			}
			
		});
		resumeGame.setHeadString("Resume", 20, new Point(58, 20));
		resumeGame.setBottomString("Game", 28, new Point(58, 100));
		
		MenuTextButton restartGame = new MenuTextButton(game, new Point(15,230), "MENU_ITEM", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.restartGame();
				game.hideMainMenu();
			}
			
		});
		restartGame.setHeadString("Restart", 24, new Point(58, 20));
		restartGame.setBottomString("Game", 28, new Point(58, 100));

		MenuTextButton backToTitle = new MenuTextButton(game, new Point(15,365), "MENU_ITEM", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.resetGameToStartMenu();
			}
			
		});
		backToTitle.setHeadString("Back", 28, new Point(58, 20));
		backToTitle.setCenterString("to", 24, new Point(60, 60));
		backToTitle.setBottomString("Title", 28, new Point(58, 100));
		
		MenuTextButton quitGame = new MenuTextButton(game, new Point(15,500), "MENU_ITEM", new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		quitGame.setHeadString("Quit", 28, new Point(58, 20));
		quitGame.setBottomString("Game", 28, new Point(58, 100));

		m_menu.addComponent(resumeGame);
		m_menu.addComponent(restartGame);
		m_menu.addComponent(backToTitle);
		m_menu.addComponent(quitGame);

		descriptor = new UpgradeDescriptor(game);
		m_menu.addComponent(descriptor);
		
		this.addComponent(m_menu);

		this.disable();
		this.hide();
	}

	@Override
	public void update(long time) {
		if (time - tunnelUpdate > 100) {
			tunnelUpdate = time;
			next = game.getTunnelingState(this);
		}

		if (collapsed)
			alpha = 127;
		else
			alpha = 220;

		if (!collapsed && origin != 0) {
			if (origin < SPEED)
				origin = 0;
			else
				origin -= SPEED;
		} else if (collapsed
				&& origin != CentipedeGame.BOARD_HEIGHT
						* CentipedeGame.TILE_SIZE) {
			if (CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE - origin < SPEED) {
				origin = CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE;
				m_menu.disable();
			} else
				origin += SPEED;
		}

		super.update(time);

		if (!collapsed)
			m_menu.enable();
	}

	@Override
	public void draw(Graphics2D g) {

		// Draw texture
		for (int iX = 0; iX <= CentipedeGame.BOARD_WIDTH
				* CentipedeGame.TILE_SIZE; iX += textureBox.width) {
			for (int iY = origin + gripBox.height; iY <= CentipedeGame.BOARD_HEIGHT
					* CentipedeGame.TILE_SIZE
					+ CentipedeGame.SCORE_BOARD_HEIGHT; iY += textureBox.height) {
				g.drawImage(texture, iX, iY, iX + textureBox.width, iY
						+ textureBox.height, 0, 0, textureBox.width,
						textureBox.height, game.getImageObserver());
			}
		}

		// Draw grip
		for (int i = 0; i < CentipedeGame.BOARD_WIDTH * CentipedeGame.TILE_SIZE; i += gripBox.width) {
			g.drawImage(grip, i, origin, i + gripBox.width,
					origin + gripHeight, 0, 0, gripBox.width, gripBox.height,
					game.getImageObserver());
		}

		// Draw grip 2
		for (int i = 0; i < CentipedeGame.BOARD_WIDTH * CentipedeGame.TILE_SIZE; i += gripBox.width) {
			g.drawImage(grip, i, origin + shipBox.height + gripHeight, i
					+ gripBox.width, origin + shipBox.height + gripHeight * 2,
					0, 0, gripBox.width, gripBox.height,
					game.getImageObserver());
		}

		// Draw ship icon and lives
		g.drawImage(ship, shipBox.x, shipBox.y + origin + 10, shipBox.x
				+ shipBox.width, shipBox.height + shipBox.y + origin + 10, 0,
				0, 40, 40, game.getImageObserver());

		Point nl = (Point) org_lives.clone();
		nl.translate(0, origin);
		TextGraphics.drawOutlinedString(
				g,
				String.format("x %d", game.getLives()),
				fnt,
				Color.WHITE,
				Color.RED,
				/*
				 * Utilities.reScale(3.0f, DEFAULT_SCALE,
				 * CentipedeGame.TILE_SIZE)
				 */3.0f, nl
				);

		Point ns = (Point) org_score.clone();
		ns.translate(0, origin);
		TextGraphics.drawOutlinedString(
				g,
				String.format("Score : %d", game.getScore()),
				fnt,
				Color.WHITE,
				Color.RED,
				/*
				 * Utilities.reScale(3.0f, DEFAULT_SCALE,
				 * CentipedeGame.TILE_SIZE)
				 */3.0f, ns
				);

		Point nr = (Point) org_round.clone();
		nr.translate(0, origin);
		TextGraphics.drawOutlinedString(
				g,
				String.format("Round %d", game.getLevel() + 1),
				fnt,
				Color.WHITE,
				Color.RED,
				/*
				 * Utilities.reScale(3.0f, DEFAULT_SCALE,
				 * CentipedeGame.TILE_SIZE)
				 */3.0f, nr
				);

		super.draw(g);
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean b) {
		collapsed = b;
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		GameState gs = game.getBubblingState(this);
		gs.keyTyped(e);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getY() < origin && collapsed) {
			next.mouseClicked(arg0);
		}
		if (arg0.getButton() == MouseEvent.BUTTON3 && !collapsed) {
			collapsed = true;
			game.mainMenuHidden();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getY() < origin && collapsed) {
			next.mouseEntered(arg0);
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (arg0.getY() < origin && collapsed) {
			next.mouseExited(arg0);
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getY() < origin && collapsed) {
			next.mousePressed(arg0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (arg0.getY() < origin && collapsed) {
			next.mouseReleased(arg0);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		if (collapsed) {
			next.mouseDragged(arg0);
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (collapsed) {
			next.mouseMoved(arg0);
		}
	}

	private WeaponState getWeaponState(CentipedeGame.Weapon weapon, int level){
		if (game.getWeaponLevel(weapon) >= level) {
			return WeaponState.INSTALLED;
		} else if (game.getDifficulty().ordinal() + 1 < level
				|| game.getWeaponLevel(weapon) < level - 1) {
			return WeaponState.LOCKED;
		} else {
			return WeaponState.AVAILABLE;
		}
	}
	
	public void upgradeRequested(CentipedeGame.Weapon wp, int lv) {
		WeaponState wps = getWeaponState(wp, lv);
		if (wps == WeaponState.INSTALLED) {
			return;
		} else if (wps == WeaponState.LOCKED) {
			SoundEffect.NEGATIVE.play();
		} else if (game.getScore() < game.getUpgradePrice(wp, lv)){
			SoundEffect.NEGATIVE.play();
			descriptor.blink();
		} else {
			game.setWeaponLevel(wp, lv);
			SoundEffect.UPGRADE_PURCHASED.play();
			game.spentScores(game.getUpgradePrice(wp, lv));
		}
	}

	public void chainExplRequested() {
		if (game.chainExplosionEnabled())
			return;
		if (game.getDifficulty() == CentipedeGame.Difficulty.EASY) {
			SoundEffect.NEGATIVE.play();
		} else if (game.getScore() < CentipedeGame.CHAIN_EXPL_PRICE){
			SoundEffect.NEGATIVE.play();
			descriptor.blink();
		} else {
			game.enabledChainExplosion();
			SoundEffect.UPGRADE_PURCHASED.play();
			game.spentScores(CentipedeGame.CHAIN_EXPL_PRICE);
		}
	}

	public void megaLaserRequested() {
		if (game.getScore() < CentipedeGame.MEGA_LASER_PRICE){
			SoundEffect.NEGATIVE.play();
			descriptor.blink();
		} else {
			game.megaLaserPurchased();
			game.spentScores(CentipedeGame.MEGA_LASER_PRICE);
		}
	}
	
	public void reset() {
		collapsed = true;
		origin = CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE;
	}

	// INTERNAL CLASSES
	private enum WeaponState{
		AVAILABLE,
		LOCKED,
		INSTALLED
	}
	
	private class WeaponButton extends MenuTextButton {

		CentipedeGame game = (CentipedeGame) super.game;
		CentipedeGame.Weapon weapon;
		int level;
		Point ov_origin = Utilities.reScale(new Point(65,60), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
		Font ovf_red = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.BOLD, Utilities.reScale(22, DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
		Font ovf_green = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.PLAIN, Utilities.reScale(24, DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
		
		public WeaponButton(Game gm, Point position, CentipedeGame.Weapon weapon,
				int level) {
			super(gm, position, "WPUP_" + weapon.toString(), null);
			this.weapon = weapon;
			this.level = level;
			String tmp = ""; for (int i = 0; i < level; i++) tmp += "I";
			this.setCenterString(tmp, 80, new Point(58, 62));
		}
			
		@Override
		protected void onMouseEnter(){
			super.onMouseEnter();
			WeaponState state = getWeaponState(weapon, level);
			if (state == WeaponState.AVAILABLE)
				descriptor.setDescription(((CentipedeGame) game).getUpgradePrice(weapon, level), 
						game.getUpgradeDescription(weapon.ordinal(), level)); 
			else if (state == WeaponState.INSTALLED){
				descriptor.setDescription(-2, 
						game.getUpgradeDescription(weapon.ordinal(), level));
			} else descriptor.setDescription(-1, game.getUpgradeDescription(-1, 0));
		}
		
		@Override 
		protected void onMouseLeave(){
			super.onMouseLeave();
			//descriptor.setDescription(-1, null);
		}
		
		@Override
		protected void drawOverlay(Graphics2D g){
			WeaponState wps = getWeaponState(weapon, level);
			if (wps == WeaponState.INSTALLED){
				TextGraphics.drawOutlinedStringRotated(g, "[Installed]", 
						ovf_green,
						Color.WHITE, new Color(0, 196, 0), 3.0f, ov_origin , -(Math.PI / 6));
			} else if (wps == WeaponState.LOCKED){
				TextGraphics.drawOutlinedStringRotated(g, "[Locked]", 
						ovf_red,
						Color.WHITE, Color.RED, 3.5f, ov_origin, -(Math.PI / 6));
			}
		}
	
		@Override
		protected void onMouseClick(MouseState oldState, MouseState newState){
			if (oldState.isButtonDown(MouseEvent.BUTTON1) && newState.isButtonUp(MouseEvent.BUTTON1)){
				upgradeRequested(weapon, level);
			}
		}
	}
	
	private class MenuTextButton extends ButtonBase {

		private final Color UNSELECTED = Utilities.alphaBlend(Color.RED, 127);
		private final Color SELECTED = Color.RED;
		private final int MARGIN = Utilities.reScale(3, 20,
				CentipedeGame.TILE_SIZE);
		private final int WIDTH = Utilities.reScale(6, 20,
				CentipedeGame.TILE_SIZE);
		private final int LENGTH = Utilities.reScale(25, 20,
				CentipedeGame.TILE_SIZE);
		private final Dimension SIZE = new Dimension(Utilities.reScale(120, 20,
				CentipedeGame.TILE_SIZE), Utilities.reScale(120, 20,
				CentipedeGame.TILE_SIZE));

		Color sel = UNSELECTED;

		Image icon;
		Rectangle iconBox = Utilities.reScale(new Rectangle(0, 0, 120, 120),
				DEFAULT_SCALE, CentipedeGame.TILE_SIZE);

		String head = null;
		Font headFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
		Point headCenter = new Point();
		String center = null;
		Font centerFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
		Point centerCenter = new Point();
		String bottom = null;
		Font bottomFont = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
		Point bottomCenter = new Point();

		ActionListener act;

		public MenuTextButton(Game gm, Point position, String iconKey,
				ActionListener act) {
			super(gm, new Rectangle(Utilities.reScale(position, 20,
					CentipedeGame.TILE_SIZE), new Dimension(Utilities.reScale(
					120, 20, CentipedeGame.TILE_SIZE), Utilities.reScale(120,
					20, CentipedeGame.TILE_SIZE))));
			icon = ContentPipeline.getResource(iconKey);
			this.act = act;
		}

		@Override
		public void update(long time) {
			super.update(time);
			offset.y = origin;
		}

		@Override
		public void draw(Graphics2D g) {
			Graphics2D g2 = (Graphics2D) g.create(getBounds().x + offset.x,
					getBounds().y + offset.y, getBounds().width,
					getBounds().height);

			// g2.clearRect(0, 0, 120, 120);

			// draw selection arrows
			g2.setColor(sel);
			// Top Left
			g2.fillRect(MARGIN, MARGIN, LENGTH, WIDTH); // Horizontal
			g2.fillRect(MARGIN, MARGIN + WIDTH, WIDTH, LENGTH - WIDTH); // Vertical
			// Top Right
			g2.fillRect(SIZE.width - MARGIN - LENGTH, MARGIN, LENGTH, WIDTH); // Horizontal
			g2.fillRect(SIZE.width - MARGIN - WIDTH, MARGIN + WIDTH, WIDTH,
					LENGTH - WIDTH); // Vertical
			// Botton Left
			g2.fillRect(MARGIN, SIZE.height - MARGIN - WIDTH, LENGTH, WIDTH); // Horizontal
			g2.fillRect(MARGIN, SIZE.height - MARGIN - LENGTH, WIDTH, LENGTH
					- WIDTH); // Vertical
			// Botton RIght
			g2.fillRect(SIZE.width - MARGIN - LENGTH, SIZE.height - MARGIN
					- WIDTH, LENGTH, WIDTH); // Horizontal
			g2.fillRect(SIZE.width - MARGIN - WIDTH, SIZE.height - MARGIN
					- LENGTH, WIDTH, LENGTH - WIDTH); // Vertical

			// draw icon box
			g2.drawImage(icon, iconBox.x, iconBox.y, iconBox.x + iconBox.width,
					iconBox.y + iconBox.height, 0, 0, 120, 120,
					game.getImageObserver());

			// draw text
			// head
			if (head != null)
				TextGraphics.drawOutlinesStringCentered(g2, head, headFont,
						Color.WHITE, CentipedeGame.UPGRADE_MENU_TEXT, 3.0f, headCenter);
			// center
			if (center != null)
				TextGraphics.drawOutlinesStringCentered(g2, center, centerFont,
						Color.WHITE, CentipedeGame.UPGRADE_MENU_TEXT, 3.0f, centerCenter);
			// bottom
			if (bottom != null)
				TextGraphics.drawOutlinesStringCentered(g2, bottom, bottomFont,
						Color.WHITE, CentipedeGame.UPGRADE_MENU_TEXT, 3.0f, bottomCenter);

			// draw overlaying layer
			drawOverlay(g2);
		}

		protected void drawOverlay(Graphics2D g) {

		}

		public void setHeadString(String str, int fnt, Point pos) {
			head = str;
			headFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT,
					Font.PLAIN, Utilities.reScale(fnt, DEFAULT_SCALE,
							CentipedeGame.TILE_SIZE));
			headCenter = Utilities.reScale(pos, DEFAULT_SCALE,
					CentipedeGame.TILE_SIZE);
		}

		public void setCenterString(String str, int fnt, Point pos) {
			center = str;
			centerFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT,
					Font.PLAIN, Utilities.reScale(fnt, DEFAULT_SCALE,
							CentipedeGame.TILE_SIZE));
			centerCenter = Utilities.reScale(pos, DEFAULT_SCALE,
					CentipedeGame.TILE_SIZE);
		}

		public void setBottomString(String str, int fnt, Point pos) {
			bottom = str;
			bottomFont = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT,
					Font.PLAIN, Utilities.reScale(fnt, DEFAULT_SCALE,
							CentipedeGame.TILE_SIZE));
			bottomCenter = Utilities.reScale(pos, DEFAULT_SCALE,
					CentipedeGame.TILE_SIZE);
		}

		@Override
		protected void onMouseEnter() {
			sel = SELECTED;
		}

		@Override
		protected void onMouseLeave() {
			sel = UNSELECTED;
		}

		@Override
		protected void onMouseDown() {
		}

		@Override
		protected void onMouseUp() {
		}

		@Override
		protected void onKeyDown() {
		}

		@Override
		protected void onKeyUp() {
		}

		@Override
		protected void onMouseClick(MouseState oldState, MouseState newState) {
			if (oldState.isButtonDown(MouseEvent.BUTTON1)
					&& newState.isButtonUp(MouseEvent.BUTTON1)) {
				act.actionPerformed(new ActionEvent(this, 0, ""));
			}
		}

		@Override
		protected void onKeyPress(KeyState oldState, KeyState newState) {
		}

	}

	private class UpgradeDescriptor extends DrawableGameComponent {

		CentipedeGame game = (CentipedeGame) super.game;
		
		Rectangle area = Utilities.reScale(new Rectangle(165, 500, 420, 135), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
		private final int MARGIN = Utilities.reScale(3, 20,
				CentipedeGame.TILE_SIZE);
		private final int WIDTH = Utilities.reScale(6, 20,
				CentipedeGame.TILE_SIZE);
		private final int LENGTH = Utilities.reScale(25, 20,
				CentipedeGame.TILE_SIZE);
		Point offset = new Point(0, 0);
		
		int sepy = Utilities.reScale(25, DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
		int descOrigin = Utilities.reScale(28, DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
		int lnstep = Utilities.reScale(20, DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
		Point priceOrigin = Utilities.reScale(new Point(230, 25), DEFAULT_SCALE, CentipedeGame.TILE_SIZE);
		
		Font priceFnt = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.PLAIN,
				Utilities.reScale(25, DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
		Font textFnt = ContentPipeline.getFont(CentipedeGame.GLOBAL_FONT, Font.PLAIN,
				Utilities.reScale(20, DEFAULT_SCALE, CentipedeGame.TILE_SIZE));
		
		Color price_neg = Color.RED;
		int blinkCoeff = 20;
		int cbCoeff = blinkCoeff;
		int blinkc = 0;
		
		
		int price = 0;
		String[] description = new String[0];
		
		public UpgradeDescriptor(Game gm) {
			super(gm);
			
			priceOrigin.y += 2;
			
		}

		@Override
		public void update(long time) {
			offset.y = origin;
			if (blinkc > 0){
				if (cbCoeff > 0) cbCoeff --;
				else {
					cbCoeff = blinkCoeff;
					blinkc--;
					if (price_neg.equals(Color.RED)){
						price_neg = Color.PINK;
					} else price_neg = Color.red;
				}
			} else price_neg = Color.RED;
		}

		@Override
		public void draw(Graphics2D g) {
			Graphics2D g2 = (Graphics2D)g.create(area.x + offset.x, area.y + offset.y, area.width, area.height);
			
			g2.setColor(Color.RED);
			// Top Left
			g2.fillRect(MARGIN, MARGIN, LENGTH, WIDTH); // Horizontal
			g2.fillRect(MARGIN, MARGIN + WIDTH, WIDTH, LENGTH - WIDTH); // Vertical
			// Top Right
			g2.fillRect(area.width - MARGIN - LENGTH, MARGIN, LENGTH, WIDTH); // Horizontal
			g2.fillRect(area.width - MARGIN - WIDTH, MARGIN + WIDTH, WIDTH,
					LENGTH - WIDTH); // Vertical
			// Botton Left
			g2.fillRect(MARGIN, area.height - MARGIN - WIDTH, LENGTH, WIDTH); // Horizontal
			g2.fillRect(MARGIN, area.height - MARGIN - LENGTH, WIDTH, LENGTH
					- WIDTH); // Vertical
			// Botton RIght
			g2.fillRect(area.width - MARGIN - LENGTH, area.height - MARGIN
					- WIDTH, LENGTH, WIDTH); // Horizontal
			g2.fillRect(area.width - MARGIN - WIDTH, area.height - MARGIN
					- LENGTH, WIDTH, LENGTH - WIDTH); // Vertical
			
			if (description != null) {
				
				String pricec = String.valueOf(price);
				if (price == -1) pricec = "----";
				if (price == -2) pricec = "Installed";
				
				// Draw Constant Price stuff
				TextGraphics.drawOutlinedString(g2, "Upgrade Price : ",
						priceFnt, Color.WHITE, Color.RED, 3.0f, new Point(
								MARGIN + WIDTH + 2, sepy + 2));
				// Draw Pricing
				TextGraphics.drawOutlinedString(g2, pricec, priceFnt, 
						Color.WHITE, game.getScore() >= price ? Color.GREEN : price_neg, 3.0f, priceOrigin);
				
				// Draw Separator
				g2.setColor(Color.WHITE);
				g2.setStroke(new BasicStroke(2));
				g2.drawLine(MARGIN + WIDTH + 5, sepy + 5, area.width - MARGIN - WIDTH - 5, sepy + 5);
				
				// Draw Description
				g2.setFont(textFnt);
				for (int i = 0; i < description.length; i++){
					g2.drawString(description[i], MARGIN + WIDTH + 2, descOrigin + lnstep * (i + 1));
					//TextGraphics.DrawOutlinedString(g2, description[i], textFnt, Color.WHITE, Color.RED, 3.0f,  new Point(MARGIN + WIDTH + 2, descOrigin + lnstep * (i + 1)));
				}
				
			}
		}

		public void setDescription(int price, String[] lines){
			blinkc = 0;
			this.price = price;
			this.description = lines;
		}

		public void blink(){
			blinkc = 8;
		}
	}
}
