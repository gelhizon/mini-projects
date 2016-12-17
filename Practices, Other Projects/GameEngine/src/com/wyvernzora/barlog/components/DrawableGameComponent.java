/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/engine/components/DrawableGameComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is the superclass for all the GameComponents that may require themselves
 * rendered on the drawing surface.
 * Rendering is done via draw() method by passing it a Graphics2D object of the Drawing Surface.
 * Please note that this is a GameComponent even if it is graphic, which means
 * it must be updated every update loop as well.
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

package com.wyvernzora.barlog.components;

import java.awt.Graphics2D;

import com.wyvernzora.barlog.Game;

public abstract class DrawableGameComponent extends GameComponent {


	public DrawableGameComponent(Game gm) {
		super(gm);
	}
	
	public abstract void update(long time);	
	public abstract void draw(Graphics2D g);
	
	
	protected boolean m_visible = true;
	
	public void show()
	{ m_visible = true; }
	public void hide()
	{ m_visible = false; }
	public boolean isVisible()
	{ return m_visible; }

}
