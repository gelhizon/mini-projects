/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/centipede/CentipedeGame.java
 * -----------------------------------------------------------------------
 * 
 * This is the root of centipede game graphics tree and container for
 * all the global game data and methods.
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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.MouseState;
import com.wyvernzora.barlog.Utilities;
import com.wyvernzora.centipede.graphics.GlobalBackgroundLayer;
import com.wyvernzora.centipede.states.BeatGameState;
import com.wyvernzora.centipede.states.GameLevel;
import com.wyvernzora.centipede.states.GameOverState;
import com.wyvernzora.centipede.states.ScoreBoard;
import com.wyvernzora.centipede.states.StartMenu;
import com.wyvernzora.resources.SoundEffect;

public class CentipedeGame extends Game {
		
	// Enum to specify game difficulty
	public enum Difficulty{
		EASY, //0
		NORMAL, //1
		HARD //2
	}
	
	// Enum to specify current weapon
	public enum Weapon{
		MISSILE, //0
		LASER, //1
		SUPERLASER //2
	}
	
	// Global Font
	public static String GLOBAL_FONT = "GLOBAL_FONT";
	
	// Game Constants
		// Debug Console Header
	public static final String PROJECT_VERSION = "ver-0.1.0.0-build-12/05/2011";
	public static final String PROJECT_NAME = "CS180 Project 5 - Centipede Arcade (\"Moria II\")";
	public static final String COPYRIGHT_NOTICE = "Copyright (C) 2011 Denis Luchkin-Zhou, Raghavendran Shankar";
	
	/* Parameters for 60 fps configuration:
	 * FRAME_RATE = 60;
	 * CENT_MCOEFF = { 16, 12, 8 };
	 * MISSILE_DELAY = 10;
	 * LAER_DELAY = 6;
	 * KEYBARD_MOVE_SPEED = 4;
	 * RESPAWN_INVINCIBILITY = { 180, 120, 60 }
	 * 
	 * Parameters for 100 fps configuration
	 * FRAME_RATE = 60;
	 * CENT_MCOEFF = { 16, 12, 8 }
	 * MISSILE_DELAY = 12;
	 * LAER_DELAY = 8;
	 * KEYBARD_MOVE_SPEED = 3;
	 * RESPAWN_INVINCIBILITY = { 300, 200, 100 }
	 */
		
	// Graphic & logic related constants
	public static final int TILE_SIZE = 20; // Tile size; Zoom Factor (100% = 20)
	public static final int BOARD_HEIGHT = 30; // Height of the board
	public static final int BOARD_WIDTH = 30; // Width of the board
	public static final int SCORE_BOARD_HEIGHT = Utilities.reScale(50, 20, TILE_SIZE); // Height of the score board
	public static final int SPACESHIP_TILT_RANGE = 0; // No Use for now
	public static final int MIN_WEAPON_LV = 0; // Maximum weapon level
	public static final int MAX_WEAPON_LV = 3; // Minimum weapon level
	public static final boolean CAN_SHOOT_INVC = false; // Indicates whether the ship can shoot while invincible
	public static final int BG_MOVE_COEFFICIENT = 3; // For every x pixels of ship movement move background by 1 pixel
	public static final Point CANVAS_CENTER = new Point(BOARD_HEIGHT * TILE_SIZE / 2, BOARD_WIDTH * TILE_SIZE / 2); // Point at the center of the canvas
	public static final boolean MOUSE_LOCK_ENABLED = true;
	public static final int MOUSE_LOCK_WMARGIN = 0; // Horizontal expansion of mouse lock box
	public static final int MOUSE_LOCK_HMARGIN = 0; // vertical expansion of mouse lock box
	public static final int MOUSE_UNLOCK_WMARGIN = 5; // horixontal expansion of mouse unlock box
	public static final int MOUSE_UNLOCK_HMARGIN = 5; // vertical expansion of mouse unlock box
	public static final int MAX_NAME_LEN = 8; // maximum length of player name
	
	// Constants affecting game difficulty
	public static final int SHIP_BOUND_X = BOARD_HEIGHT - 6; // X-Axis boud of the ship movement
	public static final int INITIAL_RAND_ASTR = 35; // Number of random asteroids to be spawn initially
	public static final int CENTIPEDE_LENGTH = 10; // Base length of centipede array
	public static final int[] MISSILE_PENT = {0, 15, 30, 45}; // Laser penetration possibility coefficient
	public static final int[] LVUP_RAND_ASTR = { 15, 20, 25 }; // Number of asteroids to be spawn on level up
	public static final int[] ASTEROID_HP = { 2, 3, 6 }; // Asteroid HP
	public static final int BASE_SCORE = 100; // base score for destroying a centipede
	public static final int[] CHAIN_EXPL_DAMAGE = { 0, 2, 4 }; // damage by chain explosion
	public static final int[] CHAIN_EXPL_CENTHIT = { 0, 0, 30 }; // chance of centipede being hit
	public static final int[][] UPGRADE_PRICE = {
		{ 2500, 5000, 7500 }, // MISSILE
		{ 2000, 4000, 6000 } // LASER
	};
	public static final int CHAIN_EXPL_PRICE = 3000; // Upgrade price of chain explosion
	public static final int MEGA_LASER_PRICE = 5000; // Upgrade price of mega laser
	public static final int MEGA_LASER_DURATION = 200; // Duration of mega laser in milliseconds
	public static final int MEGA_LASER_DMGCOEFF = 5; // Damage coefficient of mega laser
	
	// Constants affecting gameplay speed
	public static final int FRAME_RATE = 100; // Frame rate of the game, default = 60, smaller values will slow it down
	public static final int[] CENT_MCOEFF = { 16, 12, 8 }; // Centipede movement coefficients (speed)
	public static final int MISSILE_DELAY = 12; // Delay between firing two missiles (frames)
	public static final int LASER_DELAY = 8; // Delay between firing two lasers (frames)
	public static final int KEYBOARD_MOVE_SPEED = 3; // Speed of ship movement triggered by keyboard
	public static final int[] RESPAWN_INVINCIBILITY = { 300, 200, 100 }; // Time of invincibility after blowing up
	
	// Color Constants
	public static final Color MENU_FILL = new Color(235, 176, 0);
	public static final Color UPGRADE_MENU_TEXT = Color.RED;
	
	//=========================================================================
	// Constructor, update/draw and Game flow controlling methods
	//---------------------------------------------------------------
	
	// Constructor
	public CentipedeGame(){

		SoundEffect.init(); // Preload sound effect clips
		
		if (Launcher.DEBUG) 
	 		m_debugMes = new DebugMessageComponent(this); // If in debug mode, create debug console
				
		m_background = new GlobalBackgroundLayer(this); // Create global background layer
		this.addState(m_background); // Push it into state stack
		
		m_level = new GameLevel(this); // Create the game level
		this.addState(m_level); // push it into state stack
				
		m_scoreb = new ScoreBoard(this); // Create score board/main menu
		this.addState(m_scoreb); // push it into state stack
		
		if (Launcher.DEBUG){
			m_debugMes.hide(); // Initially hide the debug console
	 		this.addState(m_debugMes); // If in debug mode, push debug console into state stack
	 	}
	 	
		this.addState(new StartMenu(this)); // Create a start menu and push it into state stack
		
		try {
			bot = new Robot();
		} catch (Exception ex) {
			Launcher.reportError(Launcher.ErrorType.Fatal, this, "Failed to tap into OS mouse/keyboard handling!\n\nDetails: Type=%s; Message=%s", ex.getClass().getName(), ex.getMessage());
		}
	}

	// Start game (reset first)
	public void startGame(){
		Launcher.debugPrint(this, Color.GREEN, "Game Start!");
		resetGame();
		m_level.activateLevel(); // Start updating and rendering gaeme level
		m_scoreb.enable(); // start updating score board
		m_scoreb.show(); // start rendering score board
	}
	
	// Reset game and restart it
	public void restartGame(){
		Launcher.debugPrint(this, Color.GREEN, "Game Restart!");
		resetGame();
		startGame();
	}
	
	// TODO Remove
	// Deprecated!!
	// Pause/Unpause game with SFX
	@Deprecated
	public void pauseGame(){
		if (m_level.isEnabled()) {
			m_level.disable();
			SoundEffect.stopSequencer();
		} else {
			m_level.enable();
			SoundEffect.startSequencer();
		}
	}
	
	// Reset game to start and disable it
	public void resetGame(){
		Launcher.debugPrint(this, Color.GREEN, "Game Reset!");
		showCursor();
		this.m_laserCount = 0;
		m_chainexp = false;
		m_weapon = Weapon.MISSILE; // Reset activ weapon
		m_weaponLevel = new int[3]; // reset weapon levels
		m_gameLevel = 0; // reset current level
		m_score = 0; // reset score
		m_lives = 3; // reset lives
		m_level.reset(); // reset game level
		m_scoreb.reset(); // Reset score board
	}
	
	// Reset game and switch back to start menu
	public void resetGameToStartMenu(){
		resetGame(); // reset game variables
		m_level.hide(); // stop rendering game level
		m_level.disable(); // stop updating game level
		m_scoreb.hide(); // stop rendering score board
		m_scoreb.disable(); // stop updating score board
		this.addState(new StartMenu(this)); // show start menu
	}
	
	// Show Game over screen and decide next action
		//TODO Incomplete implementation
	public void gameOver(){
		Launcher.debugPrint(this, Color.ORANGE, "-----====== GAME OVER ======-----");
		m_level.disable();
		m_level.hide();
		m_scoreb.disable();
		m_scoreb.hide();
		this.addState(new GameOverState(this));
		SoundEffect.ANNIHILATED.play();
		SoundEffect.loadMidi("/com/wyvernzora/resources/GAMEOVER.mid");
		SoundEffect.sequencerLoop(false);
		SoundEffect.startSequencer();
	}
	
	// When a player beats the entire game...
		// TODO Incomplete implementation
	public void beatGame(){
		Launcher.debugPrint(this, Color.GREEN, "Beat Game!");
		//this.resetGameToStartMenu();
		m_level.disable();
		m_level.hide();
		m_scoreb.disable();
		m_scoreb.hide();
		this.addState(new BeatGameState(this));
		SoundEffect.loadMidi("/com/wyvernzora/resources/VICTORY.mid");
		SoundEffect.sequencerLoop(false);
		SoundEffect.startSequencer();
	}
	
	// Level Up in the game
	public void levelUp(){
		m_gameLevel ++;
		Launcher.debugPrint(this, "Level Up! [Level = %d]", m_gameLevel);
		
		// Add Bonus Score
		int base = 100;
		if (getDifficulty() != Difficulty.NORMAL) base *= getDifficulty() == Difficulty.EASY ? 0.5 : 1.5;
		base = base * getLives() / 3;
		base *= getLevel();
		m_score += base;
		
		switch (m_gameLevel){
		case 1:
			SoundEffect.ROUND2.play();
			break;
		case 2:
			SoundEffect.ROUND3.play();
			break;
		case 3:
			SoundEffect.ROUND4.play();
			break;
		case 4:
			SoundEffect.ROUND5.play();
			break;
		case 5:
			SoundEffect.ROUND6.play();
			break;
		case 6:
			SoundEffect.ROUND7.play();
			break;
		case 7:
			SoundEffect.ROUND8.play();
			break;
		default:
			SoundEffect.EXCELLENT.play();
			beatGame();
			break;
		}
	}
	
	// Render the game on the surface
	@Override
	public void draw(Graphics2D g){
		if (Launcher.DEBUG){
			if (last_frame > 0) frame_rate = (Math.round((100000.0 / (System.currentTimeMillis() - last_frame))) / 100.0);
			last_frame = System.currentTimeMillis();
		}
		
		super.draw(g);
	}
	
	//=========================================================================
	// Debugging Flags and Methods
	//---------------------------------------------------------------
	private boolean m_showGrid = false; // Flag to indicate whether grid lines are shown
	private static boolean m_drawBounds = false; //Flag to indicate whether bounding rectangles are drawn
	private boolean m_dbgShortcuts = false; // Flag indicating whether debugging hotkeys are enabled
	
	// Encapsulation stuff
	public void setGridVisible(boolean b){
		m_showGrid = b;
	}
	public boolean getGridVisible(){
		return m_showGrid;
	}
	public static boolean getDrawBounds(){
		return m_drawBounds;
	}
	public static void setDrawBounds(boolean b){
		m_drawBounds = b;
	}
	public boolean debugHotkeysEnabled(){
		return m_dbgShortcuts;
	}
	public void setDebugHotkeysEnabled(boolean b){
		m_dbgShortcuts = b;
	}
	
	// Spawn *num* random asteroids across the game level
		// This method is deprecated because it is intended for debugging purposes
	@Deprecated
	public void randomAsteroids(int num){
		m_level.getAsteroids().randomAsteroids(num);
	}
	
	// FPS refreshing flags
	private long last_frame = -1; // last frame rendering time
	private double frame_rate = 60; // current frame rate
	
	public double getFrameRate(){
		return frame_rate;
	}
	// Get debug console, to be used in Launcher for printing messages
		// If you want to print custom messages in the console, use Launcher.debugPrint(...) instead
	public DebugMessageComponent getDebugMessageComponent(){
		return m_debugMes;
	}
	
	//=========================================================================


	//=========================================================================
	// Global Input Handling - Events will be tunneled here
	//---------------------------------------------------------------
	
	@Override
	public void keyPressed(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {
	
	}
	@Override
	public void mouseExited(MouseEvent e) {
	
	}
	@Override
	public void mousePressed(MouseEvent e) {
	
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {

	}
	@Override
	public void mouseMoved(MouseEvent arg0) {

	}
	
	//=========================================================================
	
	//=========================================================================
	//	Game Specific Code
	//---------------------------------------------------------------
	GlobalBackgroundLayer m_background; // Global background
	GameLevel m_level; // Game level
	ScoreBoard m_scoreb; // Score Board/Main Menu
	DebugMessageComponent m_debugMes; // Debug Console
	
	Difficulty m_difficulty = Difficulty.NORMAL; // Current difficulty, default NORMAL
	Weapon m_weapon = Weapon.MISSILE; // Current weapon, default MISSILE
	int[] m_weaponLevel = new int[3]; // Current weapon levels, default { 0, 0, 0 }
	int m_gameLevel = 0; // Current game level, default 0
	int m_score = 0; // Current score, default 0
	int m_lives = 3; // ship lives, default 3
	
	long m_startTime = 0; // game start time
	
	boolean m_chainexp = false; // flag determining whether chain explosion is active
	
	//-------------------------------------------
	// Spaceship related global methods
	//-------------------------------------------
	
	// Invoked when spaceship starts to play blow up animation
	public void shipBlownUpStarted(){
		m_lives--; // decreace lives
		Launcher.debugPrint(this, Color.ORANGE, "Ship Blown Up, BOOOOMM! [Lives = %d]", m_lives); // Print out debug message
	}
	// Invoked when spaceship blow up animation is finished playing
	public void shipBlowUpFinished(){
		// Detect game over
		if (m_lives == 0) { gameOver(); }
	}
	// Invoked every time spaceship is moved
		//all it does is to move the background
	public void reportShipPosition(Point p){
		int ofs = (CentipedeGame.CANVAS_CENTER.x - p.x) / BG_MOVE_COEFFICIENT;
		m_background.setHorizontalOffset(ofs);
	}
	
	
	//-------------------------------------------
	// Centipede related global methods
	//-------------------------------------------
	
	// Calculates and returns the length of centipede
		// depending on current game level
	public int getCentipedeLength(){
		int base = CentipedeGame.CENTIPEDE_LENGTH;
		base += m_gameLevel * 2;
		return base;
	}	
	// Calculates and returns centipede movement coefficient
		// which decidec centipede movement speed
	public int getCentMoveCoefficient(){
		int base = CENT_MCOEFF[m_difficulty.ordinal()];
		base -= m_gameLevel;
		return base;
	}
	// Invoked when a single centipede segment is destroyed
		// calculates an appropriate score for the player
	public void reportCentipedeDestroyed(){
		double base = BASE_SCORE;
		if (m_difficulty != Difficulty.NORMAL) base *= m_difficulty == Difficulty.EASY ? 0.6 : 1.4;
		base *= getActiveWeapon() == Weapon.LASER ? 0.6 : 1;
		m_score += base;
	}
		
	// Encapsulation stuff...		
	public Weapon getActiveWeapon(){
		return m_weapon;
	}
	public void setActiveWeapon(Weapon w){
		Launcher.debugPrint(this, "Active Weapon Type Changed [Weapon = %s]", w);
		m_weapon = w;
	}
	public int getWeaponLevel(Weapon w){
		return m_weaponLevel[w.ordinal()];
	}
	public void setWeaponLevel(Weapon w, int level){
		if (level < MIN_WEAPON_LV || level > MAX_WEAPON_LV) return;
		Launcher.debugPrint(this, "[Weapon = %s] Level Changed [Level = %d]", w, level);
		m_weaponLevel[w.ordinal()] = level;
	}
	
	public Difficulty getDifficulty(){
		return m_difficulty;
	}
	public void setDifficulty(Difficulty d){
		Launcher.debugPrint(this, "Game Difficulty Changed [Difficulty = %s]", m_difficulty);
		m_difficulty = d;
	}
	public int getLives(){
		return m_lives;
	}
	public int getDefaultAsteroidHP(){
		return ASTEROID_HP[m_difficulty.ordinal()];
	}

	public int getLevel() {
		return m_gameLevel;
	}
	
	public int getScore(){
		return m_score;
	}
	
	public boolean chainExplosionEnabled(){
		return m_chainexp;
	}
	public void enabledChainExplosion(){
		m_chainexp = true;
	}
	
	// Get number of points necessary for an upgrade
	public int getUpgradePrice(Weapon wp, int level){
		return UPGRADE_PRICE[wp.ordinal()][level - 1];
	}
	// Deduct points on upgrade
	public void spentScores(int i){
		m_score -= i;
	}
	// Get description of an upgrade
		// Ugly implementation, don't do it like this
	public String[] getUpgradeDescription(int id, int level){
		switch (id){
		case -1:
			return new String[] { "This upgrade is currently locked.",
					"It may be unaccessible in your",
					"difficulty, or you may need to",
					"install lower level upgrades first." };
		case 0: // MISSILE
			if (level == 1){
				return new String[] { "You will be able to fire 2 missiles",
						"at a time."
						};
			} else if (level == 2) {
				return new String[] {
						"You will be able to fire 3 missiles", 
						"at a time." };
			} else {
				return new String[] {
						"You will be able to fire 5 missiles", 
						"at a time." };
			}
		case 1: // LASER
			if (level == 1){
				return new String[] { 
						"There will be 15% of chance that your",
						"laser doesn't disappear after hitting",
						"a target." };
			} else if (level == 2) {
				return new String[] { 
						"There will be 30% of chance that your",
						"laser doesn't disappear after hitting",
						"a target." };
			} else {
				return new String[] { 
						"There will be 45% of chance that your",
						"laser doesn't disappear after hitting",
						"a target." };
			}
		case 2: // MEGALASER
			return new String[] {
				"By purchasing this upgrade, you will",
				"obtain one opportunity to shoot",
				"mega laser: the most devastating",
				"weapon of the game!"
			};
		case 3: // CHAIN EXPL
			return new String[] {
					"After installing this upgrade, each",
					"exploding asteroid will damage",
					"asteroids around. In Hard difficulty",
					"centipedes may also be hit!"
				};
		case 4: // SOME OTHER STUFF
			break;
		}
		return new String[0];
	}
	//=========================================================================
	
	//=========================================================================
	//  Mega Laser
	//------------------------------------------------------------------------

	Weapon m_backup; // backup copy of weapon
	int m_laserCount = 0; // number or mega lasers
	
	public void activateMegaLaser(){
		m_backup = this.getActiveWeapon();
		this.setActiveWeapon(Weapon.SUPERLASER);
		m_laserCount--;
	}
	public void deactivateMegaLaser(){
		this.setActiveWeapon(m_backup);
	}
	public boolean canUseMegaLaser(){
		return m_laserCount > 0;
	}
	
	public void megaLaserPurchased(){
		m_laserCount++;
	}
	public int getMegaLaserCount(){
		return m_laserCount;
	}
	
	public long getGameTime(){
		return m_level.getGameTime();
	}
	//=========================================================================
	
	//=========================================================================
	//  Menus
	//------------------------------------------------------------------------
	public void mainMenuShown(){
		m_level.disable();
	}
	public void mainMenuHidden(){
		m_level.enable();
	}
	
	public void showMainMenu(){
		m_level.disable();
		m_scoreb.setCollapsed(false);
	}
	public void hideMainMenu(){
		m_scoreb.setCollapsed(true);
		m_level.enable();
	}
	public void toggleMainMenu(){
		if (m_scoreb.isCollapsed()){
			showMainMenu();
		} else {
			hideMainMenu();
		}
	}
	//=========================================================================
	

	
	//=========================================================================
	// Tampering with native event handling
	//---------------------------------------------------------------
	
	public static Rectangle LOCK_AREA = new Rectangle(
			CentipedeGame.TILE_SIZE - MOUSE_LOCK_WMARGIN,
			CentipedeGame.TILE_SIZE - MOUSE_LOCK_HMARGIN,
			(CentipedeGame.BOARD_WIDTH - 2) * CentipedeGame.TILE_SIZE + MOUSE_LOCK_WMARGIN * 2, 
			(CentipedeGame.BOARD_HEIGHT - 2) * CentipedeGame.TILE_SIZE + MOUSE_LOCK_HMARGIN * 2);
	
	public static Rectangle UNLOCK_AREA = new Rectangle(
			CentipedeGame.TILE_SIZE - MOUSE_UNLOCK_HMARGIN,
			CentipedeGame.TILE_SIZE - MOUSE_UNLOCK_HMARGIN,
			(CentipedeGame.BOARD_WIDTH - 2) * CentipedeGame.TILE_SIZE + MOUSE_UNLOCK_HMARGIN * 2, 
			(CentipedeGame.BOARD_HEIGHT - 2) * CentipedeGame.TILE_SIZE + MOUSE_UNLOCK_HMARGIN * 2);
	
	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "Blank");
	
	Robot bot = null;
	
	public void hideCursor(){
		if (getImageObserver() != null) ((JPanel)this.getImageObserver()).setCursor(blankCursor);
	}
	public void showCursor(){
		if (getImageObserver() != null) ((JPanel)this.getImageObserver()).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	
	@Override
	public void disable(){
		super.disable();
		showCursor();
	}

	public Robot getBot(){
		return bot;
	}
	
	public Point convertToOnScreen(Point pos){
		CentipedePane pane = (CentipedePane)this.getImageObserver();
		Point screenpos = pane.getLocationOnScreen();
		return new Point(screenpos.x + pos.x, screenpos.y + pos.y);
	}

	public Point convertFromOnScreen(Point pos){
		CentipedePane pane = (CentipedePane)this.getImageObserver();
		Point screenpos = pane.getLocationOnScreen();
		return new Point(pos.x - screenpos.x, pos.y - screenpos.y);
	}
	
	public Point getMouseLocation(){
		return convertFromOnScreen(MouseState.instance().getScreenPosition());
	}
	//=========================================================================
}
