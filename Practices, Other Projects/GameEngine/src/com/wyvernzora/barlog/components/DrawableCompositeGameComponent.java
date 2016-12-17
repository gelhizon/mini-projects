/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/engine/components/DrawableGameComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a composite collection of DrawableGameComponents that is treated
 * as single DrawableGameComponent. It may be used to unify multiple units
 * of graphics into one entity to simplify management of graphic components.
 * Non-drawable game components can also be added into this class, which enables it
 * to manage multiple graphic units as well as data and behavior or the entity.
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
import java.util.ArrayDeque;
import java.util.ArrayList;

import com.wyvernzora.barlog.Game;

public class DrawableCompositeGameComponent extends DrawableGameComponent {

	private enum OpType{
		ADD,
		REMOVE
	}
	
	private class Op{
		public Op(OpType type, GameComponent target){
			this.type = type;
			this.target = target;
		}
		OpType type;
		GameComponent target;
	}
	
	// Collection of game components (not necessarily drawable!!)
	protected ArrayList<GameComponent> children = new ArrayList<GameComponent>();
	protected ArrayDeque<Op> opqueue = new ArrayDeque<Op>();
	
	public void addComponent(GameComponent item){
		//children.add(item);
		opqueue.add(new Op(OpType.ADD, item));
	}
	public void removeComponent(GameComponent item){
		//children.remove(item);
		opqueue.add(new Op(OpType.REMOVE, item));
	}
	public ArrayList<GameComponent> getChildren(){
		return children;
	}
	
	public DrawableCompositeGameComponent(Game gm) {
		super(gm);
	}

	@Override
	public void update(long time) {
		while (!opqueue.isEmpty()){
			Op operation = opqueue.poll();
			if (operation.type == OpType.ADD)
				children.add(operation.target);
			else
				children.remove(operation.target);
		}
		// Iterate through all the children
		for (GameComponent component:children){
			if (component.isEnabled())
				component.update(time); // update if it is enabled
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// iterate through all the children
		for (GameComponent component:children){
			// Check whether it is drawable
			if (component instanceof DrawableGameComponent){
				DrawableGameComponent dgc = (DrawableGameComponent)component;
				if (dgc.isVisible()) // check whether it is visible
					dgc.draw(g); // if drawable and visible, draw it
			}
		}
	}

}
