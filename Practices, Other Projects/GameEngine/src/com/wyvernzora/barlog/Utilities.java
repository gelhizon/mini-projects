/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/Utilities.java
 * -----------------------------------------------------------------------
 * 
 * Utility class for miscellaneous methods.
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

package com.wyvernzora.barlog;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utilities {
	
	static Random rg = new Random(); // Global random number generator
	
	// Bounding rectangle collision
	public static boolean rectIntersect(Rectangle a, Rectangle b){
		return !(b.x > (a.x + a.width) 
				|| (b.x + b.width) < a.x
				|| b.y > (a.y + a.height)
				|| (b.y + b.height) < a.y);
	}
	
	// Get a random number quickly
	public static int getRandom(int n){
		return rg.nextInt(n); 
	}
	
	// Adjust alpha value of an existing color
	public static Color alphaBlend(Color o, int a){
		return new Color(o.getRed(), o.getGreen(), o.getBlue(), a);
	}
	
	// calculate actual number according to scales
	public static int reScale(int num, int oldScale, int newScale){
		double dnum = new Double(num);
		double dold = new Double(oldScale);
		double dnew = new Double(newScale);
		
		return (int)(dnum * (dnew / dold));
	}
	
	public static float reScale(float num, int oldScale, int newScale){
		double dnum = new Double(num);
		double dold = new Double(oldScale);
		double dnew = new Double(newScale);
		
		return (float)(dnum * (dnew / dold));
	}
	
	public static Point reScale(Point p, int oldScale, int newScale){
		return new Point(reScale(p.x, oldScale, newScale), reScale(p.y, oldScale, newScale));
	}
	
	// calculate actual rectangle according to scales
	public static Rectangle reScale(Rectangle rect, int oldScale, int newScale){
		Rectangle res = new Rectangle();
		res.x = reScale(rect.x, oldScale, newScale);
		res.y = reScale(rect.y, oldScale, newScale);
		res.width = reScale(rect.width, oldScale, newScale);
		res.height = reScale(rect.height, oldScale, newScale);
		return res;
	}

	public static String millisToTime(long millis){
		long h = TimeUnit.HOURS.convert(millis, TimeUnit.MILLISECONDS);
		long m = TimeUnit.MINUTES.convert(millis, TimeUnit.MILLISECONDS) - h * 60;
		long s = TimeUnit.SECONDS.convert(millis, TimeUnit.MILLISECONDS) - h * 3600 - m * 60;
		
		String sh = Long.toString(h);
		String sm = Long.toString(m);
		String ss = Long.toString(s);
		
		if (sm.length() < 2) sm = "0" + sm;
		if (ss.length() < 2) ss = "0" + ss;
	
		return String.format("%s:%s:%s", sh, sm, ss);
	}
}
