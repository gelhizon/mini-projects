/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/KeyState.java
 * -----------------------------------------------------------------------
 * 
 * This is a class used for recording keyboard changes.
 * This class is partially a singleton.
 * Only to be used as an alternative for keyboard event handling.
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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyState implements KeyListener {

	// Global instance
	private static KeyState _instance = null;
	// Get instance (lazy initialization)
	public static KeyState instance(){
		if (_instance == null) _instance = new KeyState(); // Initialize if the instance is null
		return _instance; // return it
	}
	
	
	// long values here are used as boolean arrays
	long[] flags = new long[10]; 
	
	// When a key is pressed...
	@Override
	public void keyPressed(KeyEvent arg0) {
		int indx = arg0.getKeyCode(); // Calculate the index within flags array
		int arrid = indx / 64; // Calculate index within 64 bits
		
		flags[arrid] |= 1 << (indx % 64); // flip the bit on
	}

	// When a key is released...
	@Override
	public void keyReleased(KeyEvent arg0) {
		// See keyPressed(...)
		int indx = arg0.getKeyCode();
		int arrid = indx / 64;
		
		flags[arrid] &= ~(1 << (indx % 64)); // flip the bit off
	}

	// When a key is typed...
	@Override
	public void keyTyped(KeyEvent arg0) {
		// I don't handle it here
	}
	
	// Check whether a particular key is down
		// Code is obvious :)
	public boolean isKeyDown(int keycode){
		int indx = keycode;
		int arrid = indx / 64;
		
		return ((flags[arrid] & (1 << (indx % 64))) != 0);
	}

	// Check whether a particular key is up
		// Code is obvious :)
	public boolean isKeyUp(int keycode){
		int indx = keycode;
		int arrid = indx / 64;
		
		return !((flags[arrid] & (1 << (indx % 64))) != 0);
	}

	// Check whether any key is down
	public boolean isDown(){
		boolean b = false;
		for (long l:flags) if (l != 0) b = true;
		return b;
	}

	// Capture current state
		// returns a copy of current key state
	public KeyState capture(){
		KeyState state = new KeyState();
		state.flags = this.flags.clone();
		return state;
	}
}
