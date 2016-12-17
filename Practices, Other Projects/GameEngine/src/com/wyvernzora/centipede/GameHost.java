/* Centipede - Deep Space Remix & Barlog Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *  /com/wyvernzora/centipede/GameHost.java
 * -----------------------------------------------------------------------
 * 
 * This is the JPanel containing the game rendering surface.
 * 
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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

import com.wyvernzora.barlog.KeyState;
import com.wyvernzora.barlog.components.GameState;

@SuppressWarnings("serial")
public class GameHost extends JFrame {
	
	//This class is pretty much the same as all the UI code done in class
		// thus I won't really comment it
	
	// Globally important stuff
	CentipedeGame game;
	CentipedePane pane;
	
	//Miscellaneous UI code, you can ignore it
	JRadioButtonMenuItem[] lvs;
	JRadioButtonMenuItem[] dfs;
	JRadioButtonMenuItem[] dmsg;
	
	public GameHost(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Make program terminate after closing the window
		this.setLayout(null); // Disable layout - we don't need Java layout messing with OUR graphics
		
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				Launcher.printLog(PerformanceMonitor.getInstance().toString());
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
			}
			
		});
		
		
		// Set cursor to blank
		//BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		//Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		//    cursorImg, new Point(0, 0), "blank cursor");
		//this.setCursor(blankCursor);
		
		// Game Initialization Code
		game = new CentipedeGame(); // Build the game
		pane = new CentipedePane(game); // Build the drawing surface
		pane.setLocation(0, 0); // Drawing surface will be put on the origin of the host
		this.setSize(pane.getWidth() + 6, pane.getHeight() + 28 + (Launcher.DEBUG ? 23 : 0)); // Setup the size of the host
		this.setResizable(false); // Host CANNOT be resized
		this.add(pane); // Add the drawing surface to the host
		this.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				GameState gs = game.getBubblingState(game);
				
				if (gs != game)
				 gs.keyPressed(arg0);
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				GameState gs = game.getBubblingState(game);
				
				if (gs != game)
				 gs.keyReleased(arg0);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				GameState gs = game.getBubblingState(game);
				
				if (gs != game)
				 gs.keyTyped(arg0);
			}
			
		});
		this.addKeyListener(KeyState.instance());
		
		if (Launcher.DEBUG) buildMenus();
		
		pane.activate(); // Start rendering
		
		this.setVisible(true); // Show the frame
	}

	private void buildMenus(){
		JMenuBar mainMenu = new JMenuBar();
		
		// Game
/*		JMenu game = new JMenu("Game");
		JMenuItem game_newGame = new JMenuItem("New Game");
		JMenuItem game_pauseGame = new JMenuItem("Pause Game");
		JMenuItem game_quitGame = new JMenuItem("Quit Game");
		
		game.add(game_newGame);
		game.add(game_pauseGame);
		game.add(game_quitGame);
		
		mainMenu.add(game);
		*/
		// Options
		JMenu options = new JMenu("Options");
		final JCheckBoxMenuItem speed_shot = new JCheckBoxMenuItem("Enable Laser");
		speed_shot.setSelected(this.game.getActiveWeapon() == CentipedeGame.Weapon.LASER);
		speed_shot.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				speedshot_click(speed_shot.isSelected());
			}
			
		});
		options.add(speed_shot);
		
		options.add(new JSeparator());
		
		ButtonGroup bgDif = new ButtonGroup();
		dfs = new JRadioButtonMenuItem[3];
		ActionListener dfsl = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				for (int i = 0; i < dfs.length; i++){
					if (e.getSource().equals(dfs[i])) id = i;
				}
				if (id < 0){
					Launcher.reportError(Launcher.ErrorType.Silent, this, "Unable to determine game difficulty event source [%d]!", id);
					return;
				}
				diff_click(id);
			}
			
		};
		String[] strdfs = { "Easy", "Normal", "Hard" };
		for (int i = 0; i < 3; i++){
			dfs[i] = new JRadioButtonMenuItem(strdfs[i]);
			dfs[i].addActionListener(dfsl);
			bgDif.add(dfs[i]);
			options.add(dfs[i]);
		}
		dfs[this.game.getDifficulty().ordinal()].setSelected(true);
		
		mainMenu.add(options);
		
		
		
		// High Scores
		
		// Debug
		
		JMenu debug = new JMenu("Debug");
		final JCheckBoxMenuItem showGrid = new JCheckBoxMenuItem("Show Grid Lines");
		showGrid.setSelected(this.game.getGridVisible());
		showGrid.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				showGrid_click(showGrid.isSelected());
			}
			
		});
		debug.add(showGrid);
		
		final JCheckBoxMenuItem drawBounds = new JCheckBoxMenuItem("Draw Bounding Rectangles");
		drawBounds.setSelected(this.game.getGridVisible());
		drawBounds.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CentipedeGame.setDrawBounds(drawBounds.isSelected());
			}
			
		});
		debug.add(drawBounds);
		
		final JCheckBoxMenuItem debugHotkeys = new JCheckBoxMenuItem("Enable Debug Hotkeys");
		debugHotkeys.setSelected(this.game.getGridVisible());
		debugHotkeys.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				game.setDebugHotkeysEnabled(debugHotkeys.isSelected());
			}
			
		});
		debug.add(debugHotkeys);
		
		final JMenuItem spawnAsteroids = new JMenuItem("Spawn Asteroids");
		spawnAsteroids.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				spawnA_click();
			}
		});
		debug.add(spawnAsteroids);
		
		JMenu dbgvis = new JMenu("Debug Console");
		ActionListener dmsgl = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int indx = -1;
				for (int i = 0; i < dmsg.length; i++){
					if (dmsg[i].equals(arg0.getSource())) indx = i;
				}
				dbgalpha_click(indx * 10);
			}
			
		};
		ButtonGroup dbgmsgg = new ButtonGroup();
		
		dmsg = new JRadioButtonMenuItem[11];
		for (int i = 0; i < 11; i++){
			dmsg[i] = new JRadioButtonMenuItem(i == 0 ? "Hide" : i * 10 + "%");
			dmsg[i].addActionListener(dmsgl);
			dbgmsgg.add(dmsg[i]);
			dbgvis.add(dmsg[i]);
		}
		dmsg[0].setSelected(true);
		debug.add(dbgvis);
		
		
		
		
		debug.add(new JSeparator());
		
		JMenu wpLv = new JMenu("Weapon Level");
		lvs = new  JRadioButtonMenuItem[CentipedeGame.MAX_WEAPON_LV - CentipedeGame.MIN_WEAPON_LV + 1];
		ActionListener lvc = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int id = -1;
				for (int i = 0; i < lvs.length; i++){
					if (e.getSource().equals(lvs[i])) id = i;
				}
				if (id < 0){
					Launcher.reportError(Launcher.ErrorType.Silent, this, "Unable to determine weapon level event source [%d]!", id);
					return;
				}
				wplv_click(id);
			}
			
		};
		
		ButtonGroup bg = new ButtonGroup();
		for (int i = 0; i < lvs.length; i++){
			lvs[i] = new JRadioButtonMenuItem(String.format("Level %d", CentipedeGame.MIN_WEAPON_LV + i));
			lvs[i].addActionListener(lvc);
			bg.add(lvs[i]);
			wpLv.add(lvs[i]);
		}
		lvs[0].setSelected(true);
		
		debug.add(wpLv);
		
		
		mainMenu.add(debug);
		debug.setEnabled(Launcher.DEBUG);
		
		this.setJMenuBar(mainMenu);
		
	}
	
	private void showGrid_click(boolean b){
		game.setGridVisible(b);
	}
	
	private void speedshot_click(boolean b){
		game.setActiveWeapon(b ? CentipedeGame.Weapon.LASER : CentipedeGame.Weapon.MISSILE);
	}
	
	@SuppressWarnings("deprecation")
	private void spawnA_click(){
		game.randomAsteroids(45);
	}
	
	private void wplv_click(int i){
		game.setWeaponLevel(game.getActiveWeapon(), i);
	}
	
	private void diff_click(int i){
		game.setDifficulty(CentipedeGame.Difficulty.values()[i]);
	}
	
	private void dbgalpha_click(int alpha){
		if (alpha == 0) game.m_debugMes.hide();
		else {
			game.m_debugMes.show();
			game.m_debugMes.setAlpha(alpha * 255 / 100);
		}
	}

}
