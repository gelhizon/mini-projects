/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *  /com/wyvernzora/centipede/DebugMessageComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is debug console component.
 * You can just ignore this, this is for my personal debugging purposes.
 * 
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

package com.wyvernzora.centipede;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.barlog.components.DrawableGameComponent;
import com.wyvernzora.resources.ContentPipeline;

public class DebugMessageComponent extends DrawableGameComponent {

	// private class representing a single debug message
		// very straightforward
	private class DbgMessage { 
		
		public DbgMessage(String m, Color c){
			message = m;
			color = c;
		}
		
		public Color color;
		public String message;
	}
	
	// Default message color
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	CentipedeGame game = (CentipedeGame) super.game; // Override superclass game variable type
	DbgMessage[] msgs; // message queue (circular array)
	int head = 0; // current array head
	Point origin = new Point(3,12); // Origin of the console
	int coeff = 2; // coefficient...forgot what for
	int step = CentipedeGame.TILE_SIZE / coeff; // distance between adjacent line baselines
	int alpha = 51; // alpha transparency (0~255)
	double fps = 100.0; // Initial frame rate.. just to avoid mistakes
	
	int fcoeff = 10; // frame rate refresh coefficient
	int cfcoeff = fcoeff; // current frame rate refresh coefficient
	
	// Constructor
	public DebugMessageComponent(Game gm) {
		super(gm);
		// Initialize message array according to screen space.
		msgs = new DbgMessage[CentipedeGame.BOARD_HEIGHT * coeff / 5 * 4 - 2];
	}

	// update the component
	@Override
	public void update(long time) {
		if (cfcoeff > 0) cfcoeff--;
		else
		{
			cfcoeff = fcoeff;
			
			fps = game.getFrameRate();
			
		}
	}

	// draw the componwnt
	@Override
	public void draw(Graphics2D g) {
		//g.setFont(new Font("Arial", Font.BOLD, step));
		g.setFont(ContentPipeline.getFont("FONT", Font.BOLD, step));
		g.setColor(alphaBlend(DEFAULT_COLOR, alpha));
		int crow = 0;
		
		g.drawString(String.format("%s %s", CentipedeGame.PROJECT_NAME, CentipedeGame.PROJECT_VERSION), origin.x, origin.y + (crow++) * step);
		g.drawString(String.format("%1$s%1$s%1$s%1$s%1$s", "------------------------------"), origin.x, origin.y + (crow++) * step);
		g.drawString(CentipedeGame.COPYRIGHT_NOTICE, origin.x, origin.y + (crow++) * step);
		crow++;
		
		g.drawString(String.format("Bullet Pool = %2$d; Asteroid Pool = %3$d; Mouse (%4$d, %5$d); %1$sfps; Timer = %6$s", fps,
				game.m_level.getProjectiles().getPoolSize(), game.m_level.getAsteroids().getPoolSize(), MouseState.instance().getPosition().x, MouseState.instance().getPosition().y, Utilities.millisToTime(game.m_level.getGameTime())),
				origin.x, origin.y + (crow++) * step);
		crow += 2;
		

		//g.setFont(new Font("Arial", Font.PLAIN, step));
		g.setFont(ContentPipeline.getFont("FONT", Font.PLAIN, step));
		for (int i = 0; i < msgs.length; i++){
			if (msgs[coercePos(i)] == null) continue;
			g.setColor(alphaBlend(msgs[coercePos(i)].color, alpha));
			String mes = msgs[coercePos(i)].message;
			g.drawString(mes, origin.x, origin.y + (crow++) * step);
		}
	}

	// calculate actual position of an element
		// within a circular array
	private int coercePos(int i){
		int ps = i + head;
		if (ps >= msgs.length) ps-=msgs.length;
		return ps;
	}
	
	// I don't have to explian what this is
	public void println(String s, Color c){
		int next = coercePos(msgs.length - 1);
		msgs[next] = new DbgMessage(s, c);
		head = next;
	}

	// same as above
	public void println(String s){
		println(s, alphaBlend(DEFAULT_COLOR, alpha));
	}

	// Deprecated. Use Utilities.alphaBlend() instead
	@Deprecated
	private Color alphaBlend(Color o, int a){
		return new Color(o.getRed(), o.getGreen(), o.getBlue(), a);
	}

	// encapsulation for alpha
	@SuppressWarnings("unused")
	private int getAlpha(){
		return alpha;
	}
	public void setAlpha(int a){
		alpha = a;
	}
}
