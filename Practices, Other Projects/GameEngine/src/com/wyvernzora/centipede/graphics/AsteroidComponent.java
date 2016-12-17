/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/AsteroidComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a single asteroid.
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
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.FrameSequence;
import com.wyvernzora.resources.SoundEffect;

public class AsteroidComponent extends DrawableGameComponent implements ICollidable {

	// Enum to indicate current animation (state)
	// of the asteroid
	public enum Animation {
		NORM, // Normal
		EXPLODE // Asteroid blowing up
	}
	
	CentipedeGame game = (CentipedeGame)super.game; // Override the super class variable
	CompositeAsteroidComponent parent; // Composite asteroid owning this asteroid
	int hp;	// Remaining Hit-Points
	Point pos; // Absolute position
	Animation anim = Animation.NORM; // Initial state
	Image img; // Current frame
	FrameSequence[] frames = new FrameSequence[2]; // Animations
	int frameCoeff = 3; // Frame rate coefficient, 1/x
	int cCoeff = frameCoeff; // Current coefficient counter
	boolean dead = false; // Flag that determines whether this asteroid is "dead"
	// dead means invisible and doesn't collide with any projectiles, however, centipede units
	// still consider it as an obstacle and it still shows up in collision queries
	// in general, dead means invisible to the player but visible to the program
	boolean hit = false; // Flag that indicates whether this projectile was hit during any update cycle
	// It is used for suppressing SFX in case if multiple projectile hit it in the same cycle
	
	public AsteroidComponent(Game gm, CompositeAsteroidComponent parent, Point pos) {
		super(gm);

		hp = CentipedeGame.ASTEROID_HP[game.getDifficulty().ordinal()]; // Initialize hp
		
		// Load animations
		frames[0] = new FrameSequence(
				null,
				ContentPipeline.getResource("ASTR_0"),
				ContentPipeline.getResource("ASTR_1"),
				ContentPipeline.getResource("ASTR_2"),
				ContentPipeline.getResource("ASTR_3"),
				ContentPipeline.getResource("ASTR_4"),
				ContentPipeline.getResource("ASTR_5")
				);
		
		frames[1] = new FrameSequence(
				ContentPipeline.getResource("ASTR_E_0"),
				ContentPipeline.getResource("ASTR_E_1"),
				ContentPipeline.getResource("ASTR_E_2"),
				ContentPipeline.getResource("ASTR_E_3"),
				ContentPipeline.getResource("ASTR_E_4")
				);
		
		
		this.pos = pos; // Initialize position
		this.parent = parent; // Initialize parent
	}

	// Method called during update loop
	@Override
	public void update(long time) {
		
		hit = false; // reset the flag
		
		// Check for dead flag
		boolean remove = true;
		if (dead) {
			// Check for centipedes in surrounding cells
			for (int i = -1; i <= 1; i++){
				for (int j = -1; j <= 1; j++){
					Point pt = new Point(pos.x + i, pos.y + j);
					if (parent.getParent().getCentipede(pt) != null) remove = false;
				}
			}
			
			if (remove) parent.flagForRemoval(this); // if centipedes not found, let parent know that this asteroid is ready to be reused
		}
		
		// Update component according to HP
		if (hp <= 0 && anim != Animation.EXPLODE){
			// If hp reached 0, asteroid blows up
			anim = Animation.EXPLODE; // Shatter into pieces
			SoundEffect.EXPLODE2.play(); // BOOOOM!
		}
		
		// Update Graphics
		// I'll just skip explaining what is coefficient for
		// I've been using this stuff all over this game
		if (cCoeff > 0)
			cCoeff--;
		else {
			cCoeff = frameCoeff;
			
			if (anim == Animation.NORM) {
				// Update the frame if asteroid is in normal state
				img = frames[0].getFrameAt(hp);
			} else {
				// Update it another way if it is blowing up
				img = frames[1].nextFrameNoLoop();
				if (img == null) {
					// chain explosion
					if (game.chainExplosionEnabled()){
						for (int iX = -1; iX <=1; iX++){
							for (int iY = -1; iY <=1; iY++){
								if (iX == iY && iX == 0) continue;
								AsteroidComponent astr = parent.getAsteroid(new Point(this.pos.x + iX, this.pos.y + iY));
								if (astr != null) astr.chainExploded();
								CentipedeComponent cent = parent.getParent().getCentipede(new Point(this.pos.x + iX, this.pos.y + iY));
								if (cent != null) cent.chainExploded();
							}
						}
					}
					// If explosion is finished, mark it dead
					dead = true;
					this.hide(); // and hide it (exclude from render loop)
				}
			}
		}
	}

	// Method called during render loop
	@Override
	public void draw(Graphics2D g) {
		// Calculate absolute position using in-game position
		int gx = pos.x * CentipedeGame.TILE_SIZE;
		int gy = pos.y * CentipedeGame.TILE_SIZE;
		int gw = CentipedeGame.TILE_SIZE;
		int gh = CentipedeGame.TILE_SIZE;
		
		// Adjust to fit explosion animation (which is bigger)
		if (anim == Animation.EXPLODE) {
			gx -= CentipedeGame.TILE_SIZE / 2;
			gy -= CentipedeGame.TILE_SIZE / 2;
			gw *= 2;
			gh *= 2;
		}
		
		// Render current frame to the surface
		g.drawImage(img, gx, gy, gw, gh, game.getImageObserver());
		
		// Draw bounds
		if (CentipedeGame.getDrawBounds()){
			g.setColor(Color.BLUE);
			g.drawRect(gx, gy, gw, gh);
			g.setColor(new Color(0, 255, 0, 100));
			g.fillOval(gx, gy, gw, gh);
		}
	}

	// Method called when collision occurs
	@Override
	public void onCollision(ICollidable source) { 
		// Check whether it is a projectile
		if ((source instanceof ProjectileComponent)){
			if (!dead && anim != Animation.EXPLODE) { // If the asteroid is alive and not exploding
				((ProjectileComponent)source).flagForRemoval(); // Flag the projectile for recycling
				hp--; // Decrease HP
				//if (!hit) 
					SoundEffect.ASTR_HIT.playAsync(); // Play asteroid-hit SFX
				hit = true;
			}
		}
		
		if (source instanceof MegaLaserComponent){
			if (!dead && anim != Animation.EXPLODE) { // If the asteroid is alive and not exploding
				hp--; // Decrease HP
				//if (!hit) 
					SoundEffect.ASTR_HIT.playAsync(); // Play asteroid-hit SFX
				hit = true;
			}
		}
		
	}

	// Resets asteroid to initial state
	// Only to be used in composite asteroid class
	// Please refer to constructor
	public void reset(){
		hp = CentipedeGame.ASTEROID_HP[game.getDifficulty().ordinal()];
		
		frames[0].reset();
		frames[1].reset();
		
		cCoeff = frameCoeff;
		dead = false;
		anim = Animation.NORM;
	}
	
	// Encapsulation
	public Point getPosition(){
		return pos;
	}

	public void setPosition(Point pt){
		pos = pt;
	}
	
	public boolean isDead(){
		return dead;
	}

	@Override
	public Dimension getSize() {
		if (anim == Animation.EXPLODE)
			return new Dimension(CentipedeGame.TILE_SIZE * 2, CentipedeGame.TILE_SIZE * 2);
		else
			return new Dimension(CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE);
			
	}

	public void chainExploded(){
		Launcher.debugPrint(this, "Chain explosion detected @ (%d, %d)", pos.x, pos.y);
		this.hp -= CentipedeGame.CHAIN_EXPL_DAMAGE[game.getDifficulty().ordinal()];
		if (this.hp < 0) this.hp = 0;
	}
}
