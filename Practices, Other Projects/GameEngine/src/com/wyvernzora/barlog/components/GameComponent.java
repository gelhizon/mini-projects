/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/engine/components/GameComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is the building block of the entire engine.
 * GameComponent defines one abstract method update() which should be called
 * every update loop regardless of engine implementation details.
 * Every, every and every update loop, every game component must be updated.
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

import com.wyvernzora.barlog.Game;

public abstract class GameComponent {
	
	// Reference to the game, just in case
	protected Game game;
	
	public GameComponent(Game gm){
		game = gm;
	}
	
	// update method
	public abstract void update(long time);	
	
	// Variables and encapsulation that control whether this GameComponent is enabled
	// If it is disabled, it should not be refreshed, though overrides may apply
		// that's why we just provide a property instead of enforcing it
	
	protected boolean m_enabled = true;
	
	public void enable()
	{ m_enabled = true; }
	public void disable()
	{ m_enabled = false; }

	public boolean isEnabled()
	{ return m_enabled; }
}
