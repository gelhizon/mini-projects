/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/MouseState.java
 * -----------------------------------------------------------------------
 *  
 * This is a class used for recording mouse changes.
 * This class is a modified singleton.
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

package com.wyvernzora.barlog;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseState implements MouseMotionListener, MouseListener {

	// Global instance
	private static MouseState _instance = null;
	// Get the global instance (w/ lazy initialization)
	public static MouseState instance(){
		if (_instance == null) _instance = new MouseState(); // Initialize instance if it is null
		return _instance; // return it
	}
	
	// buttons is used as a boolean array not as an int!!
	private int buttons = 0;
	// Current mouse position
	private Point position = new Point(0,0);
	
	// Protected constructor to ensure it's impossible to instantiate this class
	protected MouseState(){
		
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

	// When a mouse button is pressed
	@Override
	public void mousePressed(MouseEvent arg0) {
		buttons |= 1 << arg0.getButton(); // flip corresponding bit on
	}
	// When a mouse button is released
	@Override
	public void mouseReleased(MouseEvent arg0) {
		buttons &= ~(1 << arg0.getButton()); // flip the corresponding bit off
	}

	// When the mouse has been dragged
	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouseMoved(arg0); // handle it as mouse have been moved
	}

	// When the mouse has been moved
	@Override
	public void mouseMoved(MouseEvent arg0) {
		position = arg0.getPoint(); // Update current mouse position
	}

	// Encapsulation stuff, obvious code
	public Point getPosition(){
		return position;
	}
	public int getX(){
		return position.x;
	}
	public int getY(){
		return position.y;
	}

	//Check whether specified mouse button is down
	public boolean isButtonDown(int button){
		return ((buttons & (1 << button)) != 0);
	}
	// Check whether specified mouse button is up
	public boolean isButtonUp(int button){
		return ((buttons & (1 << button)) == 0);
	}
	// Check if any mouse button is down
	public boolean isDown(){
		return buttons != 0;
	}
	// Capture current mouse state
		// returns a copy of current state
	public MouseState capture(){
		MouseState state = new MouseState();
		state.buttons = this.buttons;
		state.position = (Point) this.position.clone();
		return state;
	}

	// Returns on-screen position of the mouse
	public Point getScreenPosition(){
		return MouseInfo.getPointerInfo().getLocation();
	}
}
