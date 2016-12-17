/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/resources/ContentPipeline.java
 * -----------------------------------------------------------------------
 * 
 * Class for loading and buffering image/font resources.
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
package com.wyvernzora.resources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.centipede.Launcher;


// Class that fetches images and fonts
	// Implemented Lazy Creation with buffer
	// Buffer is in place to reference all the same images to the same instance of BufferedImage
		// to preserve memory...though it's not a big deal
public class ContentPipeline {

	// Hashmap to store our created images...
	private static HashMap<String, Image> m_buffer = new HashMap<String, Image>();
	
	
	public static final String ROOT = "/com/wyvernzora/resources/";
	
	// Fetches a resource image and returns an Image representation of it
	public static Image getResource(String key) {
		// Check if it is already buffered in hashmap
		if (!m_buffer.containsKey(key)) {
			// if not, read it from file...
			Image img = null;
			try {
				java.net.URL uri = Game.class.getResource(ROOT + key + ".png");
				if (uri != null) img = Toolkit.getDefaultToolkit().createImage(uri);
				Launcher.debugPrint(new ContentPipeline(), "Loading [%s]...",key);
			} catch (Exception ex) {
				Launcher.reportError(Launcher.ErrorType.Prominent, new ContentPipeline(), "Unable to load resource [%s]", key);
			}
			//...and if successfully read, buffer it
			if (img != null) m_buffer.put(key, img);
			
			return img;
		}
		else {
			Launcher.debugPrint(new ContentPipeline(), "Loaded from buffer [%s]", key);
			// image already there, just pass a reference
				// to the existing image
			return m_buffer.get(key);
		}
	}
	
	// Fetches a resource image without buffering it
		// Also can be called force-load from file
	public static Image getResourceNoBuffer(String key){
		// if not, read it from file...
		Image img = null;
		try {
			java.net.URL uri = Game.class.getResource("/com/wyvernzora/resources/" + key + ".png");
			if (uri != null) img = Toolkit.getDefaultToolkit().createImage(uri);
			Launcher.debugPrint(new ContentPipeline(), "Loading [%s]...",uri);
		} catch (Exception ex) {
			System.err.printf("com.wyvernzora.resources.ContentPipeline:> Unable to load resource [%s]", key);
		}

		return img;
	}

	// Drop image buffer
		// Images themselves will not be removed from memory until they are no longer used
	public static void dropImageBuffer(){
		m_buffer.clear();
	}
	
	
// Part that creates fonts
	private static HashMap<String, Font> m_fnt = new HashMap<String, Font>();
	
	public static Font getFont(String name, int style, float size){
		if (!m_fnt.containsKey(name)){
			try{
				Launcher.debugPrint(new ContentPipeline(), "Loading Font [%s]", name);
				Font f = Font.createFont(Font.TRUETYPE_FONT, ContentPipeline.class.getResourceAsStream(ROOT + name + ".TTF"));
				m_fnt.put(name, f);
			} catch (Exception ex){
				Launcher.debugPrint(new ContentPipeline(), Color.RED, "Unhandled [%s] occured while loading font [%s]!", ex.getClass().getSimpleName(), name);
				m_fnt.put(name, new Font(Font.SANS_SERIF, Font.PLAIN, 22));
			}
		}
		Font f = m_fnt.get(name);
		if (f.getStyle() != style) f = f.deriveFont(style);
		if (f.getSize() != size) f = f.deriveFont(size);
		return f;
	}
	
}
