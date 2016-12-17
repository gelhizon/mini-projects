/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *  /com/wyvernzora/barlog/controls/ButtonBase.java
 * -----------------------------------------------------------------------
 * 
 * This is the lowest level abstraction of a button.
 * Extend this class to create a wide variety of responsive controls.
 * Must be used with MouseState class.
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

package com.wyvernzora.barlog.controls;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.KeyState;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.components.DrawableGameComponent;

/* MUST BE USED WITH MouseState CLASS!!!!!
 * AGAIN, USE MOUSE STATE!! */

public abstract class ButtonBase extends DrawableGameComponent {
	
	private boolean m_mouseOver = false; // flag to indicate whether mouse is inside the control
	private KeyState m_keys; // a copy of current key state
	private boolean m_focus = false; // determines whether control accepts key events
	private MouseState m_mouse; // current mouse state
	private Rectangle m_bounds; // bounds of the control
	
	private boolean m_clickS = false; // click initiated
	private boolean m_pressS = false; // key press initiated

	protected Point offset = new Point(0,0); // dynamic offset that needs to be updated
	
	public ButtonBase(Game gm, Rectangle bounds) {
		super(gm);
		
		m_bounds = bounds;
		m_keys = KeyState.instance().capture();
		m_mouse = MouseState.instance().capture();
	}

	@Override
	public void update(long time) {

		if (!isEnabled()) return;
		
		Rectangle t_bounds = (Rectangle) m_bounds.clone();
		t_bounds.translate(offset.x, offset.y);
		
		boolean t_mouseOver = t_bounds.contains(MouseState.instance().getPosition());
		if (t_mouseOver && !m_mouseOver){
			onMouseEnter();
		}
		if (!t_mouseOver && m_mouseOver){
			onMouseLeave();
		}
		m_mouseOver = t_mouseOver;
		
		
		MouseState t_mouse = MouseState.instance().capture();
		if (t_mouse.isDown() && !m_mouse.isDown() && m_mouseOver){
			onMouseDown();
			m_clickS = true;
		}
		if (!t_mouse.isDown() && m_mouse.isDown() && m_mouseOver){
			onMouseUp();
			if (m_clickS){
				m_clickS = false;
				onMouseClick(m_mouse, t_mouse);
			}
		}
		m_mouse = t_mouse;
		
		KeyState t_key = KeyState.instance().capture();
		if (t_key.isDown() && !m_keys.isDown() && m_focus){
			onKeyDown();
			m_pressS = true;
		}
		if (!t_key.isDown() && m_keys.isDown() && m_focus){
			onKeyUp();
			if (m_pressS){
				m_pressS = false;
				onKeyPress(m_keys, t_key);
			}
		}
		m_keys = t_key;
	}
	@Override
	public abstract void draw(Graphics2D g);

	public Rectangle getBounds(){
		return m_bounds;
	}
	public Rectangle getActualBounds(){
		Rectangle t_bounds = (Rectangle) m_bounds.clone();
		t_bounds.translate(offset.x, offset.y);
		return t_bounds;
	}
	public boolean getFocus(){
		return m_focus;
	}
	public void setFocus(boolean b){
		m_focus = b;
	}

	@Override
	public void enable(){
		m_mouse = MouseState.instance().capture();
		m_keys = KeyState.instance().capture();
		super.enable();
	}
	
	protected abstract void onMouseEnter();
	protected abstract void onMouseLeave();
	protected abstract void onMouseDown();
	protected abstract void onMouseUp();
	protected abstract void onKeyDown();
	protected abstract void onKeyUp();
	
	protected abstract void onMouseClick(MouseState oldState, MouseState newState);
	protected abstract void onKeyPress(KeyState oldState, KeyState newState);
}
