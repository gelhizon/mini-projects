/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/SpaceshipSprite.java
 * -----------------------------------------------------------------------
 * 
 * This is the spaceship...ugliest piece of code in this game.
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
package com.wyvernzora.centipede.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.ICollidable;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.centipede.states.GameLevel;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.FrameSequence;
import com.wyvernzora.resources.SoundEffect;

public class SpaceshipSprite extends DrawableGameComponent implements ICollidable {

	// Enum to specify ship animation type
		// (indirectly, ship state)
	public enum Animation{
		NORMAL, // Ship flying straight
		TILT_LEFT, // Ship moving left
		TILT_RIGHT, // Ship moving right
		EXPLODE // Ship blowing up
	}
	
	CentipedeGame game = (CentipedeGame)super.game; // Override superclass game variable
	GameLevel parent; // Reference to the GameLevel object owning this spaceship
	FrameSequence[] m_frames = new FrameSequence[4]; // Create a new array of animations (frame sequences)
	FrameSequence m_animInvc;
	int m_invcCoeff = CentipedeGame.RESPAWN_INVINCIBILITY[game.getDifficulty().ordinal()];
	Animation m_anim = Animation.NORMAL; // Set up animation flag and assign it a default value
	Image m_img; // Current frame. Frames are updated in update() method.
	Image m_iimg;
	Point m_pos = new Point(CentipedeGame.BOARD_WIDTH / 2 * CentipedeGame.TILE_SIZE, (CentipedeGame.BOARD_HEIGHT - 2) * CentipedeGame.TILE_SIZE); // Ship position with initial value
	Point m_oldPos = new Point(m_pos.x, m_pos.y); // Old position, used for calculating delta values
	//Point m_origin = new Point(20, 20); // Relative sprite origin
	Rectangle m_bounds = new Rectangle(0, CentipedeGame.SHIP_BOUND_X * CentipedeGame.TILE_SIZE, 
			CentipedeGame.BOARD_WIDTH * CentipedeGame.TILE_SIZE, (CentipedeGame.BOARD_HEIGHT - CentipedeGame.SHIP_BOUND_X - 1) * CentipedeGame.TILE_SIZE); // Ship movement box
	
	int m_frameCoeff = 10; // Frame rate coefficient, 1/x
	int m_cCoeff = m_frameCoeff; // current coefficient count
	int m_resCoeff = 3; // Animation Reset coefficient, 1/x
	int m_crCoeff = m_resCoeff; // Current coefficient count
	
	//MouseState oldState;
	Point oldPosition;
	boolean lockMouse = true;
	boolean mouseIn = false;

	// Constructor
	public SpaceshipSprite(Game gm, GameLevel parent) {
		super(gm); // Call for superclass constructor
		
		this.parent = parent; // Set up reference to the level
		
		// Load animations
		m_frames[0] = new FrameSequence(
				ContentPipeline.getResource("SHIP_S_1"),
				ContentPipeline.getResource("SHIP_S_2")
				);
		
		m_frames[1] = new FrameSequence(
				ContentPipeline.getResource("SHIP_L_1"),
				ContentPipeline.getResource("SHIP_L_2")
				);
		
		m_frames[2] = new FrameSequence(
				ContentPipeline.getResource("SHIP_R_1"),
				ContentPipeline.getResource("SHIP_R_2")
				);

		m_frames[3] = new FrameSequence(
				ContentPipeline.getResource("SHIP_E_0"),
				ContentPipeline.getResource("SHIP_E_1"),
				ContentPipeline.getResource("SHIP_E_2"),
				ContentPipeline.getResource("SHIP_E_3"),
				ContentPipeline.getResource("SHIP_E_4"),
				ContentPipeline.getResource("SHIP_E_5"),
				ContentPipeline.getResource("SHIP_E_6"),
				ContentPipeline.getResource("SHIP_E_7"),
				ContentPipeline.getResource("SHIP_E_8"),
				ContentPipeline.getResource("SHIP_E_9"),
				ContentPipeline.getResource("SHIP_E_A"),
				ContentPipeline.getResource("SHIP_E_B"),
				ContentPipeline.getResource("SHIP_E_C"),
				ContentPipeline.getResource("SHIP_E_D"),
				ContentPipeline.getResource("SHIP_E_E")
				);

		m_animInvc = new FrameSequence(
				//ContentPipeline.getResource("SHIP_I_0"),
				ContentPipeline.getResource("SHIP_I_1"),
				ContentPipeline.getResource("SHIP_I_2"),
				ContentPipeline.getResource("SHIP_I_3"),
				ContentPipeline.getResource("SHIP_I_4"),
				ContentPipeline.getResource("SHIP_I_5"),
				ContentPipeline.getResource("SHIP_I_6"),
				ContentPipeline.getResource("SHIP_I_7"),
				ContentPipeline.getResource("SHIP_I_8"),
				ContentPipeline.getResource("SHIP_I_9")//,
				//ContentPipeline.getResource("SHIP_I_0"),
				//ContentPipeline.getResource("SHIP_I_A"),
				//ContentPipeline.getResource("SHIP_I_B")
				);
	}

	// Method called if collision occurs
	@Override
	public void onCollision(ICollidable source) {
				
		// if shroom, avoid
		// otherwise, blow up
		
	}

	// Method called in update loop
	@Override
	public void update(long time) {

		if (!parent.isEnabled()) return;
		
		if (m_invcCoeff > 0){
			m_invcCoeff--;
		}
				
		// Movement
		if (CentipedeGame.MOUSE_LOCK_ENABLED) {
			if (oldPosition == null)
				oldPosition = game.getMouseLocation();
			Point current = game.getMouseLocation();

			int deltaX = current.x - CentipedeGame.CANVAS_CENTER.x;
			int deltaY = current.y - CentipedeGame.CANVAS_CENTER.y;

			Point npos = (Point) m_pos.clone();
			npos.translate(deltaX, deltaY);

			if (CentipedeGame.LOCK_AREA.contains(game.getMouseLocation())
					&& !lockMouse) {
				Launcher.debugPrint(this, Color.CYAN, "Mouse Lock ON");
				lockMouse = true;
				//game.hideCursor();
				Point screenpos = game
						.convertToOnScreen(CentipedeGame.CANVAS_CENTER);
				game.getBot().mouseMove(screenpos.x, screenpos.y);
				npos = (Point) m_pos.clone();
			}

			if (lockMouse) {
				game.hideCursor();
				
				if (!CentipedeGame.UNLOCK_AREA.contains(npos)) {
					Launcher.debugPrint(this, Color.CYAN,
							"Mouse Leave Lock Range");
					Point screenp = game.convertToOnScreen(npos);
					game.getBot().mouseMove(screenp.x, screenp.y);
					lockMouse = false;
					game.showCursor();
				} else {

					npos = coercePosition(npos);
					setPosition(npos);
					Point screenpos = game
							.convertToOnScreen(CentipedeGame.CANVAS_CENTER);
					game.getBot().mouseMove(screenpos.x, screenpos.y);
				}
			}

			// setPosition(current.getPosition());
			oldPosition = current;
		} else {
			this.setPosition(coercePosition(MouseState.instance().getPosition()));
		}
		// Calculate positions taking relative origin into account
		
		if (m_cCoeff > 0) {
			m_cCoeff--; // Skip the frame if coefficient is not 0
		} else {
			if (m_anim.ordinal() != 3) { // Otherwise, if animation is not "EXPLOSION"
				m_cCoeff = m_frameCoeff; // Reset the coefficient and...

				// Update animation type using delta values
				if (m_pos.x - m_oldPos.x > CentipedeGame.SPACESHIP_TILT_RANGE) {
					m_anim = Animation.TILT_RIGHT;
					m_crCoeff = m_resCoeff;
				} else if (m_pos.x - m_oldPos.x < -CentipedeGame.SPACESHIP_TILT_RANGE) {
					m_anim = Animation.TILT_LEFT;
					m_crCoeff = m_resCoeff;
				} else {
					m_anim = Animation.NORMAL;
				}

				// Update current frame
				m_img = m_frames[m_anim.ordinal()].nextFrame();
			} else { // If ship is blowing up
				m_cCoeff = m_frameCoeff; // Same story with the coefficient
				Image nframe = m_frames[m_anim.ordinal()].nextFrameNoLoop(); // Update next frame without looping
				if (nframe == null) { // If reached the end of animation
					this.invincible();
					m_frames[m_anim.ordinal()].reset(); // Reset blow up animation
					m_anim = Animation.NORMAL; // Switch back to normal animation
					m_frameCoeff = 10; // Reset coefficient
					((CentipedeGame) game).shipBlowUpFinished(); // Notify game object
					update(time); // Refresh sprite immediately
				} else m_img = nframe; // Update frames
			}	
			
			if (m_invcCoeff > 0){
				m_iimg = m_animInvc.nextFrame();
			}
		}
		
		if (m_crCoeff > 0){
			m_crCoeff--; // Skip the frame if the coefficient is not 0
		} else {
			m_crCoeff = m_resCoeff; // Reset coefficient
			
			if (m_anim != Animation.NORMAL)
				m_oldPos = (Point) m_pos.clone(); //Reset delta values
		}
		
		// Collision Detection
			// Collosion always goes FreeMoving -> GridMoving -> Idle
		Point gamepos = new Point(m_pos.x / CentipedeGame.TILE_SIZE, m_pos.y / CentipedeGame.TILE_SIZE);
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				CentipedeComponent cp = parent.getCentipede(new Point(gamepos.x + i, gamepos.y + j)); 
				if (cp != null){					
						Rectangle rc = new Rectangle(cp.getPosition().x * CentipedeGame.TILE_SIZE, cp.getPosition().y  * CentipedeGame.TILE_SIZE, cp.getSize().width, cp.getSize().height);
						Rectangle rp = new Rectangle(m_pos.x - CentipedeGame.TILE_SIZE, m_pos.y - CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2);
						
						if (Utilities.rectIntersect(rc, rp)){
							blowUp();
						}
				}
			}
		}
	}

	// Method called in render loop
	@Override
	public void draw(Graphics2D g) {	
		// Calculate positions taking relative origin into account
		int px = m_pos.x - CentipedeGame.TILE_SIZE;
		int py = m_pos.y - CentipedeGame.TILE_SIZE;
		
		// Render the ship
		g.drawImage(m_img, px, py, CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2, game.getImageObserver());
		
		// Render Invincibility Animation
		if (m_invcCoeff > 0){
			g.drawImage(m_iimg, px, py, CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2, game.getImageObserver());
		}
		
		// Draw Bounding Rectangle
		if (CentipedeGame.getDrawBounds()){
			// Draw Bounding Rect
			g.setColor(Color.WHITE);
			g.drawRect(px, py, CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2);
			g.setColor(new Color(0, 255, 0, 100));
			g.fillOval(px, py, CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2);
		}
	}

	// Mayday!!
	public void blowUp(){
		if (m_invcCoeff > 0) return;		
		if (m_anim == Animation.EXPLODE) return;
		game.shipBlownUpStarted();
		m_frameCoeff = 3; // Make coefficient smaller (faster frame rate)
		m_cCoeff = 3; // Initialize frame counter
		m_anim = Animation.EXPLODE; // Set animation
		SoundEffect.EXPLODE.play(); // Play explosion SFX
		SoundEffect.FATALITY.play(); // Play fatality SFX
	}

	// Encapsulation
	public Point getPosition(){
		return m_pos;
	}
	
	private Point coercePosition(Point p){
		Point res = (Point) m_pos.clone();
		if (p.x >= m_bounds.x && p.x <= m_bounds.x + m_bounds.width)
		{
			res.x = p.x;
		
			if ( p.y < m_bounds.y || p.y > m_bounds.y + m_bounds.height)
				return res;
			res.y = p.y;
		}
		return res;
	}
	
	public void setPosition(Point p){
		if (m_anim == Animation.EXPLODE) return;
		
		m_oldPos = (Point) m_pos.clone();
		
		p = coercePosition(p);
		
		Point gamepos = new Point(p.x / CentipedeGame.TILE_SIZE, p.y / CentipedeGame.TILE_SIZE);
		for (int i = -1; i <= 1; i++){
			for (int j = -1; j <= 1; j++){
				AsteroidComponent astr = parent.getAsteroid(new Point(gamepos.x + i, gamepos.y + j)); 
				if (astr != null){					
						//Rectangle rc = new Rectangle(astr.getPosition().x * CentipedeGame.TILE_SIZE, astr.getPosition().y * CentipedeGame.TILE_SIZE, astr.getSize().width, astr.getSize().height);
						//Rectangle rp = new Rectangle(m_pos.x - CentipedeGame.TILE_SIZE, m_pos.y - CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2);
						
						//if (Utilities.rectIntersect(rc, rp)){
						//	return;
						//}
					
						Point astr_pos = Utilities.reScale(astr.getPosition(), 1, CentipedeGame.TILE_SIZE);
							astr_pos.translate(CentipedeGame.TILE_SIZE / 2, CentipedeGame.TILE_SIZE / 2);
						Point ship_pos = (Point) this.getPosition().clone();
							//ship_pos.translate(-CentipedeGame.TILE_SIZE, -CentipedeGame.TILE_SIZE);
						double dist = astr_pos.distance(ship_pos);
						
						float range= Utilities.reScale(30.0f, 20, CentipedeGame.TILE_SIZE);
						
						if (dist < (range)){
							//return; // TOTO More precise collision routine
							while (astr_pos.distance(ship_pos) < range){
								if (ship_pos.y < astr_pos.y) ship_pos.y--;
								else ship_pos.y++;
								if (ship_pos.x < astr_pos.x) ship_pos.x--;
								else ship_pos.x++;
							}
							
							p.y = ship_pos.y;
							p.x = ship_pos.x;
						}
				}
			}
		}
		
		p = coercePosition(p);
		
		m_pos = p;
		game.reportShipPosition(m_pos);
		

	}
	// Method used by shooting part of the code
		// returns the position where the ship's cannons should be
	public Point getCannonPosition(){
		return new Point(m_pos.x, m_pos.y - CentipedeGame.TILE_SIZE);
	}
	// Check whether ship's current state allows shooting
	public boolean canShoot(){
		return m_anim != Animation.EXPLODE && (m_invcCoeff > 0 ? CentipedeGame.CAN_SHOOT_INVC : true);
	}

	@Override
	public Dimension getSize() {
		return new Dimension(CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2);
	}

	public void invincible(){
		m_invcCoeff = CentipedeGame.RESPAWN_INVINCIBILITY[game.getDifficulty().ordinal()];
	}
}
