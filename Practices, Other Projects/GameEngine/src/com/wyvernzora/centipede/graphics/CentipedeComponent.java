/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/CentipedeComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a single sentipede segment.
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
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.resources.ContentPipeline;
import com.wyvernzora.resources.FrameSequence;
import com.wyvernzora.resources.SoundEffect;

public class CentipedeComponent extends DrawableGameComponent implements ICollidable {

	public enum Animation {
		NORM,
		HIGHLIGHT,
		EXPLODE
	}
	
	public enum Direction{
		NORTH,
		SOUTH,
		WEST,
		EAST
	}
	
	CentipedeGame game = (CentipedeGame) super.game;
	CompositeCentipedeComponent parent;             
	FrameSequence[] frames = new FrameSequence[3];  
	int frameCoeff = 2;                             
	int cCoeff = frameCoeff;                        
	int mCoeff;                                     
	Image img;                                      
	Point pos;    
	Point oldpos;
	Animation anim = Animation.NORM;                
	Direction dirc = Direction.EAST;                
	int vspeed = 1;                                 
	int hspeed = 1;                                 
	
	CentipedeComponent head = null;
	CentipedeComponent tail = null;
	
	public CentipedeComponent(Game gm, CompositeCentipedeComponent parent, Point pos) {
		super(gm);
		
		this.parent = parent;
		this.pos = pos;
		this.oldpos = pos;
		
		this.mCoeff = game.getCentMoveCoefficient();
		
		frames[0] = new FrameSequence(
				ContentPipeline.getResource("CENT_N_N"),
				ContentPipeline.getResource("CENT_N_S"),
				ContentPipeline.getResource("CENT_N_W"),
				ContentPipeline.getResource("CENT_N_E")
				);
		
		frames[1] = frames[0];
		
		frames[2] = new FrameSequence(
				ContentPipeline.getResource("CENT_E_0"),
				ContentPipeline.getResource("CENT_E_1"),
				ContentPipeline.getResource("CENT_E_2"),
				ContentPipeline.getResource("CENT_E_3"),
				ContentPipeline.getResource("CENT_E_4"),
				ContentPipeline.getResource("CENT_E_5"),
				ContentPipeline.getResource("CENT_E_6")/*,
				ContentPipeline.getResource("CENT_E_7"),
				ContentPipeline.getResource("CENT_E_8"),
				ContentPipeline.getResource("CENT_E_9"),
				ContentPipeline.getResource("CENT_E_A"),
				ContentPipeline.getResource("CENT_E_B")*/
				);
		
	}

	@Override
	public void update(long time) {
		
		if (cCoeff > 0) cCoeff --;
		else {
			cCoeff = frameCoeff;
			
			if (anim == Animation.NORM || anim == Animation.HIGHLIGHT){
				img = frames[anim.ordinal()].getFrameAt(dirc.ordinal());
			} else {
				// Blowing up!
				img = frames[anim.ordinal()].nextFrameNoLoop();
				if (img == null){
					// Blown up
					parent.flagForRemoval(this);
					this.disable();
					this.hide();
					frames[2].reset();
				}
			}
		}
		
		if (mCoeff > 0)  mCoeff--;
		else {
			mCoeff = game.getCentMoveCoefficient();
			
			if (anim != Animation.EXPLODE) {
				if (head == null) {
					if (((pos.x + hspeed) < (CentipedeGame.BOARD_WIDTH) && (pos.x + hspeed >= 0)
							&& parent.getParent().getAsteroid(new Point(pos.x + hspeed, pos.y)) == null)) { 
						pos.x += hspeed;
						dirc = hspeed > 0 ? Direction.EAST : Direction.WEST;
					} else {
						if (((pos.y + vspeed) < (CentipedeGame.BOARD_HEIGHT) && (pos.y + vspeed > 0))) {
							if (parent.getParent().getCentipede(new Point(pos.x, pos.y + vspeed)) == null){
								pos.y = pos.y + vspeed;
								hspeed = -hspeed;
								dirc = vspeed > 0 ? Direction.SOUTH : Direction.NORTH;
							} else {
								pos.y = pos.y + vspeed * 2;
								dirc = vspeed > 0 ? Direction.SOUTH : Direction.NORTH;
							}
						} else {
							vspeed = -vspeed;
							dirc = vspeed > 0 ? Direction.SOUTH : Direction.NORTH;
						}
					}
					
					// Debug Code
					if (parent.getParent().getCentipede(pos) != null){
						Launcher.debugPrint(this, Color.YELLOW, "Centipede Overlap Detected @ (%d, %d)", pos.x, pos.y);
						
					}
				} 
			}
			img = frames[anim.ordinal()].getFrameAt(dirc.ordinal());
		}
		

		parent.updateCollisionMap(this);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, pos.x * CentipedeGame.TILE_SIZE, pos.y * CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE,
				game.getImageObserver());
		
		if (CentipedeGame.getDrawBounds()){
			g.setColor(Color.RED);
			g.drawRect(pos.x * CentipedeGame.TILE_SIZE, pos.y * CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE);
		}
	}

	@Override
	public void onCollision(ICollidable source) {
		if (source instanceof ProjectileComponent && anim != Animation.EXPLODE){
			Launcher.debugPrint(this, "Centipede Collision confirmed @ (%d, %d)!",pos.x, pos.y);
			((ProjectileComponent)source).flagForRemoval();
			blowUp();
		} else if (source instanceof MegaLaserComponent && anim != Animation.EXPLODE){
			Launcher.debugPrint(this, Color.CYAN, "MEEEEGA Laser Hits Centipede @ (%d, %d)!",pos.x, pos.y);
			blowUp();
		}
	}
	
	public void blowUp(){
		anim = Animation.EXPLODE;
		SoundEffect.EXPLODE.play();
		parent.getParent().centipedeBlownUp(this);
		game.reportCentipedeDestroyed();
	}

	public Point getPosition(){
		return pos;
	}
	public void setPosition(Point p){
		pos = p;
	}

	public void reset(){
		cCoeff = frameCoeff;
		mCoeff = game.getCentMoveCoefficient();;
		anim = Animation.NORM;
		dirc = Direction.EAST;
		vspeed = 1;
		hspeed = 1;
		head = null;
		tail = null;
	}
	
	@Override
	public Dimension getSize() {
		return new Dimension(CentipedeGame.TILE_SIZE, CentipedeGame.TILE_SIZE);
	}

	public void chainExploded(){
		if (Utilities.getRandom(100) < CentipedeGame.CHAIN_EXPL_CENTHIT[game.getDifficulty().ordinal()]){
			blowUp();
		}
	}

}
