/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/CompositeAsteroidComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a collection of asteroids
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

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Stack;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.components.DrawableCompositeGameComponent;
import com.wyvernzora.barlog.components.GameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.centipede.states.GameLevel;

public class CompositeAsteroidComponent extends DrawableCompositeGameComponent {

	GameLevel parent; // Reference to the game level that owns this component
	Stack<AsteroidComponent> pool = new Stack<AsteroidComponent>();
	HashMap<Point, AsteroidComponent> collisionMap = new HashMap<Point, AsteroidComponent>();
	
	public CompositeAsteroidComponent(Game gm, GameLevel parent) {
		super(gm);
		
		Launcher.debugPrint(this, "Instantiating %s", this.getClass().getName());
		this.parent = parent;
	}
	
	public GameLevel getParent(){
		return parent;
	}

	public void createAsteroid(Point pt){
		if (getAsteroid(pt) != null) return;
		
		if (pool.empty()){
			AsteroidComponent astr = new AsteroidComponent(game,this, pt);
			addComponent(astr);
			collisionMap.put(pt, astr);
			Launcher.debugPrint(this, "Asteroid Instantiated @ (%d, %d) [HP = %d]", astr.getPosition().x, astr.getPosition().y, astr.hp);
		} else {
			AsteroidComponent astr = pool.pop();
			astr.setPosition(pt);
			astr.reset();
			astr.enable();
			astr.show();
			collisionMap.put(pt, astr);
		}
	}
	
	public void flagForRemoval(AsteroidComponent comp){
		comp.disable(); // Stop refreshing the asteroid
		comp.hide(); // Stop rendering the asteroid
		collisionMap.remove(comp.getPosition()); // Remove it from collision detection system
		pool.add(comp);
		Launcher.debugPrint(this, "Asteroid Recycled @ (%d, %d)",
				comp.getPosition().x, comp.getPosition().y);
	}


	public AsteroidComponent getAsteroid(Point pt){
		if (collisionMap.containsKey(pt)) return collisionMap.get(pt);
		else return null;
	}

	public void randomAsteroids(int num){
		Random r = new Random();
		for (int i = 0; i < num; i++){
			int x = r.nextInt(CentipedeGame.BOARD_WIDTH);
			int y = r.nextInt(CentipedeGame.SHIP_BOUND_X);
					
			if (y == 0) {
				i -= 1;
				continue;
			}
			
			createAsteroid(new Point(x,y));
		}
	}
	
	public int getPoolSize(){
		return pool.size();
	}

	public void removeRedundantAsteroids(){
		for (int i = 0; i < ((CentipedeGame) game).getCentipedeLength(); i++){
			AsteroidComponent proj = this.getAsteroid(new Point(i, 0));
			if (proj == null) continue;
			proj.disable();
			proj.hide();
			pool.add(proj);
			collisionMap.remove(proj.getPosition());
			Launcher.debugPrint(this, "Asteroid Removed @ (%d, %d)", proj.getPosition().x, proj.getPosition().y);
		}
	}

	@SuppressWarnings("unchecked")
	public void reset(){
		for (GameComponent comp:(ArrayList<GameComponent>) getChildren().clone()){
			if (!(comp instanceof AsteroidComponent)){
				Launcher.reportError(Launcher.ErrorType.Silent, this, "Unexpected object type [%s]", comp.getClass().getName());
				continue;
			}
			
			AsteroidComponent proj = (AsteroidComponent)comp;
			proj.disable();
			proj.hide();
			pool.add(proj);
			collisionMap.remove(proj.getPosition());
			Launcher.debugPrint(this, "Asteroid Recycled @ (%d, %d)", proj.getPosition().x, proj.getPosition().y);
		}
	}
}
