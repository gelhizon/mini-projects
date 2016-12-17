/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/MegaLaserComponent.java
 * -----------------------------------------------------------------------
 * 
 * THis is the mega laser.
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
import java.awt.Point;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.ICollidable;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.states.GameLevel;

public class MegaLaserComponent extends DrawableGameComponent implements ICollidable {

	CentipedeGame game = (CentipedeGame) super.game;
	Point origin;
	Color deepCol;
	Color midCol;
	Color lightCol;
	
	GameLevel parent;
	
	int cdCoeff = CentipedeGame.MEGA_LASER_DMGCOEFF;
	int frameCount = 0;;
	
	public MegaLaserComponent(Game gm, GameLevel parent, Color dp, Color mid, Color light) {
		super(gm);
		this.deepCol = dp;
		this.midCol = mid;
		this.lightCol = light;
		this.parent = parent;
	}

	@Override
	public void update(long time) {
		
		if (++frameCount > CentipedeGame.MEGA_LASER_DURATION){
			this.disable();
			this.hide();
			game.deactivateMegaLaser();
		}
		
		if (cdCoeff > 0) cdCoeff --;
		else {
			cdCoeff = CentipedeGame.MEGA_LASER_DMGCOEFF;
			
			for (int i = origin.y / CentipedeGame.TILE_SIZE; i >= 0; i--){
				Point curPoint = new Point(origin.x / CentipedeGame.TILE_SIZE, i);
				ICollidable c1 = parent.getAsteroid(curPoint);
				if (c1 != null) c1.onCollision(this);
				ICollidable c2 = parent.getCentipede(curPoint);
				if (c2 != null) c2.onCollision(this);
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(deepCol);
		g.drawLine(origin.x, 0, origin.x, origin.y);
		g.drawLine(origin.x - 1, 0, origin.x - 1, origin.y);
		g.drawLine(origin.x + 1, 0, origin.x + 1, origin.y);
		
		g.setColor(midCol);
		g.drawLine(origin.x - 2, 0, origin.x - 2, origin.y);
		g.drawLine(origin.x - 2, 0, origin.x - 2, origin.y);
		
		g.setColor(lightCol);
		g.drawLine(origin.x - 3, 0, origin.x - 3, origin.y);
		g.drawLine(origin.x + 3, 0, origin.x + 3, origin.y);
	}

	public void setOrigin(int x, int y){
		origin.x = x;
		origin.y = y;
	}
	public void setOrigin(Point p){
		origin = p;
	}

	@Override
	public void enable(){
		super.enable();
		frameCount = 0;
	}

	@Override
	public void onCollision(ICollidable source) {
		}

	@Override
	public Dimension getSize() {
		return null;
	}
}
