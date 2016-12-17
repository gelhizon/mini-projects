/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/states/GameLevel.java
 * -----------------------------------------------------------------------
 * 
 * The main game.
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
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.GameTimer;
import com.wyvernzora.barlog.KeyState;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.components.GameComponent;
import com.wyvernzora.barlog.components.GameState;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.centipede.graphics.AsteroidComponent;
import com.wyvernzora.centipede.graphics.CentipedeComponent;
import com.wyvernzora.centipede.graphics.CompositeAsteroidComponent;
import com.wyvernzora.centipede.graphics.CompositeCentipedeComponent;
import com.wyvernzora.centipede.graphics.CompositeProjectileComponent;
import com.wyvernzora.centipede.graphics.MegaLaserComponent;
import com.wyvernzora.centipede.graphics.SpaceshipSprite;
import com.wyvernzora.resources.SoundEffect;

public class GameLevel extends GameState {

	/* Game Level Structure:
	 * 
	 * ShroomComponent -> Collection of all asteroids
	 * CentipedeComponent -> Collection of all centipedes (all units individual)
	 * BulletComponent -> Collection of all bullets (all units individual)
	 * SpaceshipSprite -> The big cheese, our super-hyper-mega-giga-turbo battleship
	 * HUD -> Score board, life board and all the statistical nonsence
	 * 
	 * 
	 * Collision Detection is technically done here, but it is called from game (update())
	 * 
	 */
	
	CentipedeGame game = (CentipedeGame)super.game;
	
	GameTimer timer = new GameTimer(game);
	private CompositeAsteroidComponent asteroids;
	CompositeCentipedeComponent centipedes;
	private CompositeProjectileComponent projectiles;
	MegaLaserComponent megalaser;
	SpaceshipSprite spaceship;
	
	
	boolean missiling = false;
	int bulletCoeff = CentipedeGame.MISSILE_DELAY;
	
	public GameLevel(Game gm) {
		super(gm);
		
		Launcher.debugPrint(this, "Instantiating %s...", this.getClass().getName());
		
		this.addComponent(timer);
		
		setAsteroids(new CompositeAsteroidComponent(game, this));
		this.addComponent(getAsteroids());
		
		centipedes = new CompositeCentipedeComponent(game, this);
		this.addComponent(centipedes);
		
		
		//getAsteroids().randomAsteroids(CentipedeGame.INITIAL_RAND_ASTR);
		//getAsteroids().removeRedundantAsteroids();
		//centipedes.defaultCentipede();
		
		setProjectiles(new CompositeProjectileComponent(game, this));
		this.addComponent(getProjectiles());
		
		megalaser = new MegaLaserComponent(game, this, Color.RED, new Color(160,0,0,180), new Color(100,0,0,90));
		megalaser.hide();
		megalaser.disable();
		this.addComponent(megalaser);
		
		spaceship = new SpaceshipSprite(game, this);
		this.addComponent(spaceship);
		
		this.disable();
	}

	public CompositeProjectileComponent getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(CompositeProjectileComponent projectiles) {
		this.projectiles = projectiles;
	}

	public CompositeAsteroidComponent getAsteroids() {
		return asteroids;
	}

	public void setAsteroids(CompositeAsteroidComponent asteroids) {
		this.asteroids = asteroids;
	}

	public void activateLevel(){
		this.enable();
		this.show();
		SoundEffect.loadMidi("/com/wyvernzora/resources/LEVEL_BGM.mid");
		SoundEffect.startSequencer();
		SoundEffect.ROUND1.play();
		
	}
	
	public void pause(){
		this.disable();
	}
	
	public void unpause(){
		if (game.getLives() <= 0) return;
		this.enable();
	}

	public long getGameTime(){
		return timer.getElapsedTime();
	}
	
	@Override
	public void disable(){
		super.disable();
		game.showCursor();
	}
	
	//=========================================================================
	// Input Handling
	//---------------------------------------------------------------
	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (!this.isEnabled()){
			game.getBubblingState(this).keyPressed(arg0);
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_M){
			game.toggleMainMenu();
		}
		if (!isEnabled()) {
			game.getBubblingState(this).keyPressed(arg0);
		}
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			if (!spaceship.canShoot())
				return;

			switch (game.getActiveWeapon()) {
			case MISSILE:
				shoot();
			case LASER:
				missiling = true;
			}
		}	
		if (arg0.getKeyCode() == KeyEvent.VK_L){
			if (game.getActiveWeapon() == CentipedeGame.Weapon.MISSILE)
				game.setActiveWeapon(CentipedeGame.Weapon.LASER);
			else if	(game.getActiveWeapon() == CentipedeGame.Weapon.LASER)
				game.setActiveWeapon(CentipedeGame.Weapon.MISSILE);
			
		}
		
		if (Launcher.DEBUG && game.debugHotkeysEnabled()) {
			if (arg0.getKeyCode() == KeyEvent.VK_I) {
				spaceship.invincible();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_B){
				spaceship.blowUp();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_R) {
				game.resetGame();
			}	
			if (arg0.getKeyCode() == KeyEvent.VK_P) {
				game.pauseGame();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_G){
				game.megaLaserPurchased();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_C){
				if (arg0.isShiftDown()) {
					for (GameComponent comp:centipedes.getChildren()){
						if (comp instanceof CentipedeComponent){
							((CentipedeComponent)comp).blowUp();
						}
					}
				} else {
					if (centipedes.isEnabled()) {
						centipedes.disable();
					} else {
						centipedes.enable();
					}
				}
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (!this.isEnabled()){
			game.getBubblingState(this).keyReleased(arg0);
		}
		
		if (arg0.getKeyCode() == KeyEvent.VK_SPACE && missiling) missiling = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
			game.getBubblingState(this).keyTyped(arg0);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
			
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (!isEnabled()) return;
		if (arg0.getButton() == MouseEvent.BUTTON1){
			//if (!spaceship.canShoot()) return;
			if (game.getActiveWeapon() == CentipedeGame.Weapon.MISSILE){
				shoot();
			}
		}
		else{
			if (game.canUseMegaLaser() && spaceship.canShoot() && game.getActiveWeapon() != CentipedeGame.Weapon.SUPERLASER) {
				game.activateMegaLaser();
				shoot();
			} else {
				SoundEffect.NEGATIVE.playAsync();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouseMoved(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		if (!isEnabled()) return;
		if (!CentipedeGame.MOUSE_LOCK_ENABLED) spaceship.setPosition(arg0.getPoint());
	}
	//=========================================================================
	
	
	//=========================================================================
	// Game Specific Code
	//---------------------------------------------------------------
	
	public AsteroidComponent getAsteroid(Point pt){
		return getAsteroids().getAsteroid(pt);
	}
	
	public CentipedeComponent getCentipede(Point pt){
		return centipedes.getCentipede(pt);
	}
	
	@Override
	public void update(long time){
				
		megalaser.setOrigin(spaceship.getPosition());
		
			if (bulletCoeff >= 0) bulletCoeff--;
			if (bulletCoeff < 0){
				
				switch (game.getActiveWeapon()){
				case LASER:
					bulletCoeff = CentipedeGame.LASER_DELAY;
					break;
				case MISSILE:
					bulletCoeff = 0;
					break;
				}
			}
		
		
		if (((MouseState.instance().isButtonDown(MouseEvent.BUTTON1) || KeyState.instance().isKeyDown(KeyEvent.VK_SPACE)))){
			if(game.getActiveWeapon() == CentipedeGame.Weapon.LASER && bulletCoeff == 0){
				shoot();
			}
		}
		
		if (KeyState.instance().isKeyDown(KeyEvent.VK_LEFT) || KeyState.instance().isKeyDown(KeyEvent.VK_A)){
			spaceship.setPosition(new Point(spaceship.getPosition().x - CentipedeGame.KEYBOARD_MOVE_SPEED, spaceship.getPosition().y));
		}
		if (KeyState.instance().isKeyDown(KeyEvent.VK_RIGHT) || KeyState.instance().isKeyDown(KeyEvent.VK_D)){
			spaceship.setPosition(new Point(spaceship.getPosition().x + CentipedeGame.KEYBOARD_MOVE_SPEED, spaceship.getPosition().y));
		}
		if (KeyState.instance().isKeyDown(KeyEvent.VK_UP) || KeyState.instance().isKeyDown(KeyEvent.VK_W)){
			spaceship.setPosition(new Point(spaceship.getPosition().x, spaceship.getPosition().y - CentipedeGame.KEYBOARD_MOVE_SPEED));
		}
		if (KeyState.instance().isKeyDown(KeyEvent.VK_DOWN) || KeyState.instance().isKeyDown(KeyEvent.VK_S)){
			spaceship.setPosition(new Point(spaceship.getPosition().x, spaceship.getPosition().y + CentipedeGame.KEYBOARD_MOVE_SPEED));
		}
		if (KeyState.instance().isKeyDown(KeyEvent.VK_ENTER)){
			spaceship.blowUp();
		}
		
		super.update(time);
	}

	@Override
	public void draw(Graphics2D g){
		super.draw(g);
		if (CentipedeGame.getDrawBounds()){
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.RED);
			g.draw(CentipedeGame.LOCK_AREA);
			g.setColor(Color.PINK);
			g.draw(CentipedeGame.UNLOCK_AREA);
		}
	}
	
	public void shoot(){
		if (!spaceship.canShoot()) return;
		
		switch (game.getActiveWeapon()){
		case MISSILE:	
			if (bulletCoeff != 0) return;
			int level = game.getWeaponLevel(CentipedeGame.Weapon.MISSILE);
						
			if (level != 1) {
				getProjectiles().createProjectile(spaceship.getPosition(), CentipedeGame.Weapon.MISSILE);
			}
			if (level == 1 || level == 2){
				getProjectiles().createProjectile(new Point(spaceship.getPosition().x - (CentipedeGame.TILE_SIZE * 2) / 3, spaceship.getPosition().y), CentipedeGame.Weapon.MISSILE);
				getProjectiles().createProjectile(new Point(spaceship.getPosition().x + (CentipedeGame.TILE_SIZE * 2) / 3, spaceship.getPosition().y), CentipedeGame.Weapon.MISSILE);
			} else if (level == 3) {
				getProjectiles().createProjectile(new Point(spaceship.getPosition().x - (CentipedeGame.TILE_SIZE * 2) / 5, spaceship.getPosition().y), CentipedeGame.Weapon.MISSILE);
				getProjectiles().createProjectile(new Point(spaceship.getPosition().x + (CentipedeGame.TILE_SIZE * 2) / 5, spaceship.getPosition().y), CentipedeGame.Weapon.MISSILE);
				getProjectiles().createProjectile(new Point(spaceship.getPosition().x - (CentipedeGame.TILE_SIZE * 2) * 2 / 5, spaceship.getPosition().y), CentipedeGame.Weapon.MISSILE);
				getProjectiles().createProjectile(new Point(spaceship.getPosition().x + (CentipedeGame.TILE_SIZE * 2) * 2 / 5, spaceship.getPosition().y), CentipedeGame.Weapon.MISSILE);
			}
			SoundEffect.MISSILE_SHOT.play();
			bulletCoeff = CentipedeGame.MISSILE_DELAY;
			break;
		case LASER:
			getProjectiles().createProjectile(spaceship.getPosition(), CentipedeGame.Weapon.LASER);
			SoundEffect.BULLET_SHOT.play();
			break;
		case SUPERLASER:
			megalaser.enable();
			megalaser.show();
			break;
		}
	}
	
	public void centipedeBlownUp(CentipedeComponent cp){
		getAsteroids().createAsteroid(cp.getPosition());
	}
	
	public void centipedesDestroyed(){
		game.levelUp();
		asteroids.removeRedundantAsteroids();
		centipedes.defaultCentipede();
		getAsteroids().randomAsteroids(CentipedeGame.LVUP_RAND_ASTR[game.getDifficulty().ordinal()]);
	}
	
	public void reset(){
		timer.reset();
		centipedes.reset();
		asteroids.reset();
		projectiles.reset();
		getAsteroids().randomAsteroids(CentipedeGame.INITIAL_RAND_ASTR);
		getAsteroids().removeRedundantAsteroids();
		centipedes.defaultCentipede();	

		SoundEffect.loadMidi("/com/wyvernzora/resources/LEVEL_BGM.mid");
		
		this.disable();
	}
	
	//=========================================================================
	
}
