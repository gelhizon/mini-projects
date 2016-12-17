/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/GlobalBackgroundLayer.java
 * -----------------------------------------------------------------------
 * 
 * This is animated background of the game.
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
import java.util.Random;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.resources.ContentPipeline;


public class GlobalBackgroundLayer extends DrawableGameComponent {

	public static final int SIDE_COEFF = 5; // Side movement coefficient
	public static final int MOVE_SPEED = 18; // Movement speed
	public static final int TEXTURE_SIZE = 128; // Texture size
	
	Image texture;
	Dimension size;
	Point origin;
	
	int moveCoeff = 3;
	int mCoeff = moveCoeff;
	
	public GlobalBackgroundLayer(Game gm) {
		super(gm);
		size = new Dimension(CentipedeGame.BOARD_WIDTH * CentipedeGame.TILE_SIZE /* + CentipedeGame.BOARD_WIDTH / SIDE_COEFF*/,
				CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE + CentipedeGame.SCORE_BOARD_HEIGHT);
		origin = new Point(0,0);
		texture = ContentPipeline.getResource("BG_TEXTURE_C");
	}
	
	@Override
	public void update(long time) {
		if (mCoeff > 0) mCoeff--;
		else
		{
			mCoeff = moveCoeff;
			
			origin.y += MOVE_SPEED;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		//g.fillRect(0, 0, CentipedeGame.BOARD_WIDTH * CentipedeGame.TILE_SIZE, CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE);
		
		int y0 = -(TEXTURE_SIZE - origin.y % TEXTURE_SIZE);
		if (y0 == -TEXTURE_SIZE) y0 = 0;
		int x0 = -(TEXTURE_SIZE - origin.x % TEXTURE_SIZE);
		if (x0 == -TEXTURE_SIZE) x0 = 0;
		
		for (int iX = x0; iX < size.width; iX += TEXTURE_SIZE){
			for (int iY = y0; iY < size.height; iY += TEXTURE_SIZE){
				g.drawImage(texture, iX, iY, iX + TEXTURE_SIZE, iY + TEXTURE_SIZE, 0, 0, TEXTURE_SIZE, TEXTURE_SIZE, game.getImageObserver());
			}
		}
	}	
	
	public void setHorizontalOffset(int offset){
		origin.x = offset;
	}
}
