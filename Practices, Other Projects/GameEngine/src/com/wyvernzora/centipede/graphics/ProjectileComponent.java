/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/ProjectileComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a single projectile (laser or missile).
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

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.ICollidable;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.FrameSequence;

public class ProjectileComponent extends DrawableGameComponent implements ICollidable {
	
	public static int count = 0; // Instance counter, used for debugging
	
	// Enum to indicate type of projectile
	//public enum ProjectileType{ 
	//	MISSILE, // Bullet (slow fire)
	//	LASER // Missile (rapid file)
	//}
	
	CentipedeGame game = (CentipedeGame)super.game; // Override super class variable
	CompositeProjectileComponent parent; // Composite projectile that owns this projectile
	FrameSequence[] frames = new FrameSequence[2]; // Frame collections (animations)
	
	public ProjectileComponent(Game gm, CompositeProjectileComponent parent, CentipedeGame.Weapon type, Point pos) {
		super(gm);

		count++; // Increment instance count
		
		this.parent = parent; // set up reference to parent
		m_pos = pos; // set up position
		m_type = type; // set up type
		 
		// Load animations
		frames[1] = new FrameSequence(
				ContentPipeline.getResource("BULLET_A_0")
				);
		
		frames[0] = new FrameSequence(
				ContentPipeline.getResource("BULLET_B_0"),
				ContentPipeline.getResource("BULLET_B_1")
				);
		
		// set initial frame
		m_img = frames[m_type.ordinal()].nextFrame();
	}

	// Java equivalent of destructor
	@Override
	protected void finalize() throws Throwable{
		count--; // Decrement instance count when JVM CGs this object
		super.finalize();
	}
	
	int m_frameCoeff = 10; // Frame coefficient, 1/x
	int m_cCoeff = m_frameCoeff; // Current frame coefficient count
	int m_moveCoeff = 0;  // Motion coefficient, 1/x
	int m_cmCoeff = m_moveCoeff; // Current motion coefficient count
	int m_speed = 12; // Actual speed = m_speed / m_moveCoeff
	CentipedeGame.Weapon m_type = CentipedeGame.Weapon.MISSILE; // Projectile type
	Image m_img; // current frame
	Point oldPos = new Point(-1, -1);
	
	//HashMap<ICollidable, Byte> m_collided = new HashMap<ICollidable, Byte>(); // Collision history. HashMap for efficiency
	
	Point m_pos; // Absolute Position
	public Point getPosition(){
		return m_pos;
	}
	public Point getGamePosition(){
		return new Point(m_pos.x / CentipedeGame.TILE_SIZE, m_pos.y / CentipedeGame.TILE_SIZE);
	}
	
	// Method called during update loop
	@Override
	public void update(long time) {
		
		if (m_cCoeff >= 0) // coefficient trick...again
			m_cCoeff--;
		else {
			m_cCoeff = m_frameCoeff;
			// Frame update
			m_img = frames[m_type.ordinal()].nextFrame();
		}
		
		
		if (m_cmCoeff >= 0) // another coefficient trick... I won't comment it next time 
			m_cmCoeff--;
		else {
			m_cmCoeff = m_moveCoeff;
			// Position update
			this.m_pos = new Point(m_pos.x, m_pos.y - m_speed);
			if (getGamePosition() != oldPos) { detectCollision(); oldPos = getGamePosition(); }
		}
		
		if (m_pos.y < -15) flagForRemoval(); // if bullet is out of screen, mark it as ready for recycling
	}

	// Method called during render loop
	@Override
	public void draw(Graphics2D g) {
		// Just simply render current frame to the surface
		g.drawImage(m_img, m_pos.x - 2, m_pos.y, game.getImageObserver());
		
		if (CentipedeGame.getDrawBounds()){
			g.setColor(Color.PINK);
			g.drawRect(m_pos.x - 2, m_pos.y, 5, 15);
			g.setColor(Color.RED);
			g.fillOval(m_pos.x - 2, m_pos.y - 2, 5, 5);
		}
	}

	private void detectCollision(){
		// Detect collision against all other units
		onCollision(parent.getParent().getAsteroid(getGamePosition()));
		onCollision(parent.getParent().getCentipede(getGamePosition()));
	}

	// Flag this projectile as ready for recycling
	public void flagForRemoval(){
		
		if (m_type == CentipedeGame.Weapon.LASER){
			// Missile upgrades : Target Penetration
			int pent = CentipedeGame.MISSILE_PENT[game.getWeaponLevel(CentipedeGame.Weapon.LASER)];
			
			if (Utilities.getRandom(100) < pent) return; // If player is lucky, skip removal (target penetration)
		}
		
		this.hide();
		this.disable();
		parent.flagForRemoval(this); // Tell the parent to recycle this projectile
	}

	// Method called when collisionoccurs
	@Override
	public void onCollision(ICollidable source) {
		if (source == null) return; // if collided with nothing, do nothing
		else {

			//if (m_collided.containsKey(source))
				//return; // Collision already took place for this projectile, ignore it
			
			source.onCollision(this); // Notify the target of collision that collision occured
		//	m_collided.put(source, (byte) 0); // Add it to the collided list
			// USELESS EXPLANATION:
			//Bullets are highly mobile, thus detecting collision from other objects is very elaborate.
			//However, collision detection from mobile objects to static objects is relatively simple.
			//All we need is to detect collision here, and reverse the procedure.
			//That's the reason for using this notification design
		}
	}
		
	// Initialize again using specified parameters
	public void reInitialize(CentipedeGame.Weapon type, Point pos){
		m_pos = pos;
		m_type = type;
		m_cCoeff = m_frameCoeff;
		m_cmCoeff = m_moveCoeff;
	//	m_collided.clear();

		m_img = frames[m_type.ordinal()].nextFrame();
	}

	@Override
	public Dimension getSize() {
		return new Dimension(5,15);
	}
}

