/* Centipede - Deep Space Remix & Barlog Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *  /com/wyvernzora/centipede/Launcher.java
 * -----------------------------------------------------------------------
 * 
 * Program entry point.
 * Home to debug-logging stuff.
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
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Launcher {

	// Error Handling Enum
	public enum ErrorType {
		Silent, // Only print a message in console
		Prominent, // Print a message in console and show a popup to the user
		Fatal // Print message, show popup and terminate the program
	}
	
	// Debug Mode Flag
	public static final boolean DEBUG = true;
	
	static DebugMessageComponent dbg;
	static PrintWriter log;
	
	public static void main(String[] args) {
		
		try{
			log = new PrintWriter(new File("debug.log"));
		} catch (Exception ex){
			
		}
		
		HighScore.instance();
		
		if (DEBUG){
			inner();
		} else {
			try {
				inner();
			} catch (Exception ex) {
				reportError(ErrorType.Fatal, new Launcher(), "Unexpected error of type [%s]", ex.getClass().getSimpleName());
			}
		}
		
	}
	
	public static void inner(){
		GameHost host = new GameHost();
		dbg = host.game.m_debugMes;
	}
	
	// Print a debug message with formatting support (param arrays)
//	@SuppressWarnings("unused")
	public static void debugPrint(Object source, Color c, String format, Object...args){
		if (!DEBUG) return; // We don't need to print anything if debug mode is off
		
		// Print the stuff in the console
		//System.out.printf("%s :> %s\n", source.getClass().getSimpleName(), String.format(format, args));
		
		if (dbg != null) dbg.println(String.format("%s :> %s\n", source.getClass().getSimpleName(), String.format(format, args)), c);
		// Print the stuff to the debug.log
		printLog(String.format("%x\t%s :> %s\n", System.currentTimeMillis(), source.getClass().getSimpleName(), String.format(format, args)));
	}
	
	public static void debugPrint(Object source, String format, Object...args){
		debugPrint(source, DebugMessageComponent.DEFAULT_COLOR, format, args);
	}
	
	// Report a runtime error
	public static void reportError(ErrorType type, Object source, String message, Object...args){
		// Print the stuff in the console
		System.out.printf("%s :> %s\n", source.getClass().getSimpleName(), String.format(message, args));
		
		// Show popup if the error is prominent or fatal
		if (type == ErrorType.Prominent){
			JOptionPane.showMessageDialog(null, String.format("Program has encountered a serious error in [%s]:\n%s", source.getClass().getSimpleName(), String.format(message, args)));
		} else if (type == ErrorType.Fatal){
			JOptionPane.showMessageDialog(null, String.format("Program has encountered a fatal error in [%s]:\n%s\n\nProgram is going to be terminated.",
					source.getClass().getSimpleName(), String.format(message, args)));
			System.exit(0);
		}
		
		// Print the stuff to the debug.log
	}
	
	public static void printLog(String str){
		if (log != null) {
			log.println(str);
			log.flush();
		}
	}

	
}
