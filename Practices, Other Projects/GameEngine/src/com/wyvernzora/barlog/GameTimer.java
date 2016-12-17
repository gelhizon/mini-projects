/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/GameTimer.java
 * -----------------------------------------------------------------------
 * 
 * This is a timer class that can be used to measure elapsed time.
 * The advantage of this timer is that it extends GameComponent and can be added
 * into any CompositeGameComponent and measure its ACTIVE time.
 * Please note that this timer is paused when disabled.
 * Not intended to be used for tasks that require precision!
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

import com.wyvernzora.barlog.components.GameComponent;

public class GameTimer extends GameComponent {
	
	long elapsedTime = 0; // amount of elapsed time (in milliseconds)
	long lastFrame = -1; // last update
	
	public GameTimer(Game gm) {
		super(gm);
	}

	// Override
		// Called every update loop
	@Override
	public void update(long time) {
		if (lastFrame < 0) lastFrame = System.currentTimeMillis(); // If last frame is not initialized, initialize it
		else {
			long st = System.currentTimeMillis(); // get current time
			elapsedTime += st - lastFrame; // add time elapsed since last frame
			lastFrame = st; // update last frame time
		}
	}

	// encapsulation stuff
	@Override
	public void enable(){
		// Make sure that while this element is paused
			// no more time is added to the elapsedTime
		lastFrame = System.currentTimeMillis();
	}

	// get elapsed time
	public long getElapsedTime(){
		return elapsedTime;
	}
	
	// reset timer
	public void reset(){
		elapsedTime = 0;
		lastFrame = -1;
	}
}
