/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/centipede/CentipedePane.java
 * 
 * This is equivalent to GameCanvas.
 * This class contains the timer that refreshes the entire game.
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
package com.wyvernzora.centipede;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import javax.swing.JPanel;

import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.components.GameState;

@SuppressWarnings("serial")
public class CentipedePane extends JPanel {
	
	CentipedeGame game; // Game instance
	Timer ticker; // Main Timer
	
	// Constructor
	public CentipedePane(CentipedeGame g){
		
		 // Set size according to CentipedeGame constants
		this.setSize(CentipedeGame.BOARD_WIDTH * CentipedeGame.TILE_SIZE,
				CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE + CentipedeGame.SCORE_BOARD_HEIGHT);
		
		game = g; //Initialize the game field
		game.setImageObserver(this); // Tell the game that this panel will be the drawing surface
		ticker = new Timer(1000 / CentipedeGame.FRAME_RATE, new ActionListener(){ // Set up the timer

			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.update(System.currentTimeMillis()); // Update the game
				repaint(); // Render the frame
			}
			
		});
		
		this.addMouseListener(MouseState.instance()); // Attach the MouseState
		this.addMouseMotionListener(MouseState.instance()); // Attach the MouseState
		
		// Root listeners that tunnels events down the layer stack
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				GameState state = game.getControlState();
				state.mouseClicked(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				GameState state = game.getControlState();
				state.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				GameState state = game.getControlState();
				state.mouseExited(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				GameState state = game.getControlState();
				state.mousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				GameState state = game.getControlState();
				state.mouseReleased(e);
			}
			
		});
		
		this.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				GameState state = game.getControlState();
				state.mouseDragged(e);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				GameState state = game.getControlState();
				state.mouseMoved(e);
			}
			
		});
	}
	
	@Override
	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D)g; // Get extended graphics

		game.draw(g2d);
		
		if (game.getGridVisible()){ // if grid lines are enabled
			float dash1[] = {5.5f};
		    BasicStroke dashed = new BasicStroke(1.0f,
		                                          BasicStroke.CAP_BUTT,
		                                          BasicStroke.JOIN_MITER,
		                                          10.0f, dash1, 0.0f);
		    g2d.setStroke(dashed);
			g2d.setColor(new Color(150, 150, 150, 128));
			for (int i = 1; i < CentipedeGame.BOARD_WIDTH; i++){ // paint vertical grid lines (light gray)
				g2d.drawLine(i * CentipedeGame.TILE_SIZE, 0, i * CentipedeGame.TILE_SIZE, CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE);
			}
			for (int i = 1; i < CentipedeGame.BOARD_HEIGHT; i++){ // paint horizontal grid lines (light gray)
				g2d.drawLine(0, i * CentipedeGame.TILE_SIZE, CentipedeGame.BOARD_HEIGHT * CentipedeGame.TILE_SIZE, i * CentipedeGame.TILE_SIZE);
			}
		}
		
	}
	
	public void activate(){
		ticker.start();
	}
	
}
