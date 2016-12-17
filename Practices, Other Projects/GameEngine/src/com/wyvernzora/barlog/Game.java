/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/Game.java
 * -----------------------------------------------------------------------
 * 
 * This is the "game" class designed to hold all the game specific data.
 * Local game data may be held in subsequent components, but global data should
 * be within the game class. Global operations such as transition between
 * states are also to be defined in the game class.
 * 
 * Improvement Log:
 * ver.7.2.2
 *  - Added the operation queue feature so that the game class works around
 *    unhandled ConcurrentModificationExceptions when a child component invokes
 *    removeComponent() or addComponent() method while in update loop.
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

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Stack;

import com.wyvernzora.barlog.components.*;

/* Game class extends DrawableCompositeGameComponent because
 * it is the root of visual tree (graphic tree).
 * 
 * IMPORTANT NOTES
 * Since its superconstructor is passed with a null, calling methods
 * of game object (defined in DrawableCompositeGameComponent) will not cause
 * compilation errors but will cause NullPointerException at runtime
 */
public abstract class Game extends GameState {
	// Enum to specify type of operation in the operation queue
	public enum OperationType{
		ADD,
		REMOVE,
		REMOVE_STATE
	}
	
	// A single operation representation
	private class Operation{
		
		public Operation(OperationType type, GameComponent target){
			this.type = type;
			this.target = target;
		}
		
		public OperationType type;
		public GameComponent target;		
	}
	
	// ImageObserver used for rendering entire graphics tree
		// Usually it is the drawing surface
	ImageObserver m_observer;
	
	// Overloaded Constructor.
	public Game(ImageObserver observer) {
		super(null);
		m_observer = observer;
		super.game = this;
	}
	// Default Constructor
	public Game(){
		super(null);
	}
	
	// Encapsulation for m_observer
	public ImageObserver getImageObserver(){
		return m_observer;
	}
	public void setImageObserver(ImageObserver is){
		m_observer = is;
	}

	//=========================================================================
	// Game State Manager Code
	//---------------------------------------------------------------
	// GameState Stack
	Stack<DrawableGameComponent> m_states = new Stack<DrawableGameComponent>();
	// GameState currently accepting input events
	GameState m_controlState = null;
	
	// Operation queue
		// Add and remove requests are processed before the next frame is updated,
		// regardless of where the method has been called
		// When calling add or remove methods, an operation is added to the operation queue
		// and executed on the next update to ensure it is thread safe.
	ArrayList<Operation> pending = new ArrayList<Operation>();
	
	// Push a new GameState into the stack
	public void addState(DrawableGameComponent comp){
		// As you can see, nothing is done here
		// Only a request is queued to be processed
		pending.add(new Operation(OperationType.ADD, comp));
	}
	// Pop the top state
	public void removeState(){
		// Same as addState(), only  a request is sent
		pending.add(new Operation(OperationType.REMOVE_STATE, m_states.peek()));
	}
	
	// Get the top state capable of accepting input events
	private GameState innerGetControlState(){
		// Just a linear search for the first enabled game state
		for (int i = m_states.size() - 1; i >= 0; i--){
			DrawableGameComponent cp = m_states.elementAt(i);
			if (cp instanceof GameState){
				if (cp.isEnabled())	return (GameState)cp; 
			}
		}
		return null;
	}

	// Get the top state capable of accepting input events
		// Faster version for calling from outside of this class
	public GameState getControlState(){
		return m_controlState == null ? this : m_controlState;
	}
	
	// Get the next state down the stack
		// Used for event tunneling
	public GameState getTunnelingState(GameState tunnel){
		// Another a little bit modified linear search...
		boolean found = false;
		for (int i = m_states.size() - 1; i >= 0; i--){
			DrawableGameComponent cp = m_states.elementAt(i);
			if (cp instanceof GameState){ 
				GameState gs = (GameState)cp;
				if (gs.equals(tunnel)) found = true;
				else if (found) return gs;
			}
		}
		return this;
	}
	
	// Get the next state up the stack
		// Used for event bubbling
	public GameState getBubblingState(GameState bubble){
		// See event tunneling...
		boolean found = bubble.equals(this);
		for (int i = 0; i < m_states.size(); i++){
			DrawableGameComponent cp = m_states.elementAt(i);
			if (cp instanceof GameState){ 
				GameState gs = (GameState)cp;
				if (gs.equals(bubble)) found = true;
				else if (found) return gs;
			}
		}
		return this;
	}

	// Override add component to accommodate GameState framework.
	@Override
	public void addComponent(GameComponent cp){
		// It's not allowed to add global undrawable game components
		if (!(cp instanceof DrawableGameComponent)) return;
		
		addState((DrawableGameComponent)cp);
	}

	// Request removal of a particular state within the stack
	public void removeComponent(GameComponent cp){
		pending.add(new Operation(OperationType.REMOVE, cp));
	}
	
	//=========================================================================
	
	// Render the game on the surface
	@Override
	public void draw(Graphics2D g){		
		//g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// iterate through all the children
		for (GameComponent component:m_states){
			// Check whether it is drawable
			if (component instanceof DrawableGameComponent){
				DrawableGameComponent dgc = (DrawableGameComponent)component;
				if (dgc.isVisible()) // check whether it is visible
					dgc.draw(g); // if drawable and visible, draw it
			}
		}
	}

	// Update the game
	@Override
	public void update(long d) {
		
		if (pending.size() != 0) { // If there are operations pending...
			for (Operation op : pending) { // for each operation in the queue
				if (op.type == OperationType.ADD) { 
					m_states.add((DrawableGameComponent) op.target); // Execute add operation
				} else if (op.type == OperationType.REMOVE) {
					m_states.remove(op.target); // execute remove operation
				} else {
					m_states.pop(); // execute remove state operation
				}
			}
			
			pending.clear(); // clear the operation queue
			m_controlState = this.innerGetControlState(); // refresh control state
		}
		
		// Iterate through all the children
		for (GameComponent component:m_states){
			if (component.isEnabled())
				component.update(d); // update if it is enabled
		}
	}
}
