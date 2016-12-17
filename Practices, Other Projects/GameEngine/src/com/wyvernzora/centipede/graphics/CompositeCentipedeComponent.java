/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/CompositeCentipedeComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a collection of all centipede segments.
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
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.components.DrawableCompositeGameComponent;
import com.wyvernzora.barlog.components.GameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.centipede.states.GameLevel;

public class CompositeCentipedeComponent extends DrawableCompositeGameComponent {

	GameLevel parent;
	
	Stack<CentipedeComponent> pool = new Stack<CentipedeComponent>();
	ArrayList<CentipedeComponent> removePending = new ArrayList<CentipedeComponent>();
	
	HashMap<Point, CentipedeComponent> collisionMap = new HashMap<Point, CentipedeComponent>();
	
	public CompositeCentipedeComponent(Game gm, GameLevel parent) {
		super(gm);

		Launcher.debugPrint(this, "Instantiating %s...", this.getClass().getName());
		
		this.parent = parent;
		
	}

	@Override
	public void update(long time) {

		collisionMap.clear();
		super.update(time);
			
		if (collisionMap.size() == 0) {
			parent.centipedesDestroyed();
		}
	}
	
	public CentipedeComponent createCentipede(Point pt){
		if (this.getCentipede(pt) != null){
			Launcher.debugPrint(this, Color.RED, "Attempt to create overlapping centipede @ (%d, %d)!", pt.x, pt.y);
			return null;
		}
		if (pool.empty()){
			CentipedeComponent cp = new CentipedeComponent(game, this, pt);
			collisionMap.put(pt, cp);
			addComponent(cp);
			Launcher.debugPrint(this, "Centipede Instantiated @ (%d, %d)", cp.getPosition().x, cp.getPosition().y);
			return cp;
		} else {
			CentipedeComponent cp = pool.pop();
			cp.setPosition(pt);
			collisionMap.put(pt, cp);
			cp.reset();
			cp.enable();
			cp.show();
			return cp;
		}
	}
	
	public void flagForRemoval(CentipedeComponent cp){
		cp.disable();
		cp.hide();
		collisionMap.remove(cp.getPosition());
		pool.add(cp);
		Launcher.debugPrint(this, "Centipede Recycled @ (%d, %d)", cp.getPosition().x, cp.getPosition().y);
	}

	public CentipedeComponent getCentipede(Point pt){
		if (collisionMap.containsKey(pt)) return collisionMap.get(pt);
		else return  null;
	}

	public void updateCollisionMap(CentipedeComponent cp){
		collisionMap.put(cp.getPosition(), cp);
	}

	public GameLevel getParent(){
		return parent;
	}

	public void defaultCentipede(){
		CentipedeComponent cp = null;
		for (int i = ((CentipedeGame) game).getCentipedeLength() - 1; i >= 0; i--){
			CentipedeComponent tcp = this.createCentipede(new Point(i, 0));
			if (cp != null) {
				//cp.setTail(tcp);
			}
			cp = tcp;
		}
	}

	@SuppressWarnings("unchecked")
	public void reset(){
		for (GameComponent comp:(ArrayList<GameComponent>) getChildren().clone()){
			if (!(comp instanceof CentipedeComponent)){
				Launcher.reportError(Launcher.ErrorType.Silent, this, "Unexpected object type [%s]", comp.getClass().getName());
				continue;
			}
			
			CentipedeComponent cp = (CentipedeComponent)comp;
			cp.disable();
			cp.hide();
			collisionMap.remove(cp.getPosition());
			pool.add(cp);
			Launcher.debugPrint(this, "Centipede Recycled @ (%d, %d)", cp.getPosition().x, cp.getPosition().y);
		}
	}
}
