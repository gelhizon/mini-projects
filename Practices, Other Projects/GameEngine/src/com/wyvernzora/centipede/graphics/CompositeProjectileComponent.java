/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/CompositeProjectileComponent.java
 * -----------------------------------------------------------------------
 * 
 * This is a collection of all bullets (including lasers and missiles).
 * This class implements object pool.
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
import java.util.Stack;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.components.DrawableCompositeGameComponent;
import com.wyvernzora.barlog.components.GameComponent;
import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.centipede.states.GameLevel;

public class CompositeProjectileComponent extends
		DrawableCompositeGameComponent {
	
	/* Notes:
	 * This class implements elaborate garbage collection technique so that 
	 * projectiles that fall out of screen are removed from this component
	 * i.e. garbage collected by JVM (the only reference of any projectile
	 * 	is within this component)
	 * Without the garbage collection system, the memory loss was very significant
	 * 
	 * Note 2:
	 * Memory loss persists. Investigating reason.
	 * 
	 * Note 3:
	 * Abandoned instance GC, moved to lazy pooling
	 * 
	 * Note 4:
	 * Memory consumption still significant, but not unlimited as before
	 * Lazy pooling works perfect.
	 * 
	 */
	
	GameLevel parent; // Game Level what owns this component
	Stack<ProjectileComponent> pool = new Stack<ProjectileComponent>(); // Pool of recycled projectiles waiting for reuse
	
	public CompositeProjectileComponent(Game gm, GameLevel parent) {
		super(gm);
		
		Launcher.debugPrint(this, "Instantiating %s...", this.getClass().getName());
		
		this.parent = parent; // Set up parent
		
	}

	// parent encapsulation
	public GameLevel getParent(){
		return parent;
	}

	// spawn a projectile on the game level
	public void createProjectile(Point pt, CentipedeGame.Weapon type){
		if (pool.empty()){ // if there are no reusable projectiles in the pool
			// Print debug messages
			//Launcher.debugPrint(this, "Instantiated new projectile.");
			//Launcher.debugPrint(this, "Current projectile count : %d", ProjectileComponent.count);
			ProjectileComponent proj = new ProjectileComponent(game, this, type, pt); // Create projectile
			this.addComponent(proj); // grab it into the game cycle
		} else { // there are projectiles that can be reused
			ProjectileComponent proj = pool.pop(); // get it, and remove it from the reusable list (or, more precisely, stack)
			proj.reInitialize(type, pt); // reinitialize it to fit new parameters
			proj.enable(); // enable (grab it into update cycle)
			proj.show(); // show it (grab it into render cycle)
		}
	}

	// Flag a specific projectile as recyclable
	public void flagForRemoval(ProjectileComponent cp){
		cp.disable(); // take it out from update cycle
		cp.hide(); // take it out from render cycle
		pool.add(cp); // add it to the recycled pool (stack)
	}

	// Method called during update loop
	@Override
	public void update(long time){
		super.update(time); // update according to standard procedures
	}
	
	// Returns the number of recycled projectiles, used for debugging
	public int getPoolSize(){
		return pool.size();
	}
	
	// "reset" the state of the object (force-return all projectiles to pool)
	@SuppressWarnings("unchecked")
	public void reset(){
		for (GameComponent comp:(ArrayList<GameComponent>)getChildren().clone()){
			if (!(comp instanceof ProjectileComponent)){
				Launcher.reportError(Launcher.ErrorType.Silent, this, "Unexpected object type [%s]", comp.getClass().getName());
				continue;
			}
			
			ProjectileComponent proj = (ProjectileComponent) comp;
			proj.disable(); // take it out from update cycle
			proj.hide(); // take it out from render cycle
			pool.add(proj); // add it to the recycled pool (stack)
		}
	}
}
