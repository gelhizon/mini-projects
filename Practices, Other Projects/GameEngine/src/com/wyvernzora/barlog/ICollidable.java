/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/ICollidable.java
 * -----------------------------------------------------------------------
 * 
 * Interface for all game components that can collide.
 * For efficiency reasons, this game uses bounding rectangle collision.
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

import java.awt.Dimension;

public interface ICollidable {
	
	// Method called to notify the compontent of collision
	public void onCollision(ICollidable source);
	
	// Get size of the collidable object
	public Dimension getSize();
}
