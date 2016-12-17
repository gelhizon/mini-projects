/* Barlog Game Engine
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 * /com/wyvernzora/barlog/TextGraphics.java
 * -----------------------------------------------------------------------
 * 
 * This is a utility class for rendering outline  text using given graphics.
 * Outline text itself is not supported by Java LOL
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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import com.wyvernzora.centipede.CentipedeGame;
import com.wyvernzora.centipede.PerformanceMonitor;

public class TextGraphics {

	// Draw a string on the surface using default way of specifying position
			// This method shifts text around to create outline effect
			// Not tested for thick strokes
		//increased efficiency by surrendering accuracy, this if stroke is thicker than font
			// this routine will produce rendering artifacts
	public static void drawOutlinedString(Graphics2D g, String s, Font f, Color outline, Color fill, float stroke, Point position){
		long start = System.nanoTime(); // get precise time for performance monitoring
		
		// return if string is null or empty
		if (s == null) return;
		if (s.length() == 0) return;
		
		
		g.setFont(f); // set font
		// Move string around to create the effect of outline
		for (int iX = -1; iX <= 1; iX++){
			for (int iY = -1; iY <= 1; iY++){
				if (iX != 0 || iY != 0){
					g.setColor(outline);
					for (int i = 1; i < stroke; i++){
						g.drawString(s, position.x + (i * iX), position.y + (i * iY));
					}
				}
			}
		}
		g.setColor(fill);
		g.drawString(s, position.x, position.y); // render the text itself
		
		if (CentipedeGame.getDrawBounds()){
			FontMetrics metrics = g.getFontMetrics(f);
			Rectangle2D rect = metrics.getStringBounds(s, g);
			g.setColor(Color.CYAN);
			g.setStroke(new BasicStroke(1));
			int cy = (int)(position.y - (rect.getHeight() * 0.8));
			g.drawRect(position.x, cy, (int)(rect.getWidth()), (int)(rect.getHeight()));
		}
		
		// calculate elapsed time and report performance to performance monitor
		start = System.nanoTime() - start;
		PerformanceMonitor.getInstance().report("DrawOutlinedString", start);
	}

	// Same as drawOutlinedString, except that the Point parameter specifies the center of the string
		// rather than baseline.
	public static void drawOutlinesStringCentered(Graphics2D g, String s, Font f, Color outline, Color fill, float stroke, Point center){
		long start = System.nanoTime();
		
		if (s == null) return;
		if (s.length() == 0) return;
				
		g.setFont(f);
		
		FontMetrics metrics = g.getFontMetrics(f);
		Rectangle2D rect = metrics.getStringBounds(s, g);
		int cx = (int)(center.x - rect.getWidth() / 2) + 2;
		int cy = (int)(center.y + (rect.getHeight() * 3 / 10));
		
		for (int iX = -1; iX <= 1; iX++){
			for (int iY = -1; iY <= 1; iY++){
				if (iX != 0 || iY != 0){
					g.setColor(outline);
					for (int i = 1; i < stroke; i++){
						g.drawString(s, cx + (i * iX), cy + (i * iY));
					}
				}
			}
		}
		
		if (CentipedeGame.getDrawBounds()){
			g.setColor(Color.CYAN);
			g.setStroke(new BasicStroke(1));
			g.drawRect((int)(center.x - rect.getWidth() / 2), (int)(center.y - rect.getHeight() / 2), (int)(rect.getWidth()), (int)(rect.getHeight()));
		}
		
		g.setColor(fill);
		g.drawString(s, cx, cy);
		
		start = System.nanoTime() - start;
		PerformanceMonitor.getInstance().report("DrawOutlinedStringCentered", start);
	}
	
	// Draw a string on the surface and rotate it
		// Precise implementation, thus it is extremely inefficient
		// drawOutlinedString is approx. 400 times faster than this routine, so use this routine only if needed
	public static void drawOutlinedStringRotated(Graphics2D g, String s, Font f, Color outline, Color fill, float stroke, Point center, double rotation){
		long start = System.nanoTime();
		
		if (s == null) return;
		if (s.length() == 0) return;
				
		TextLayout layout = getLayout(g, s, f); // get text layout
		// calculate center
		int cx = center.x - (int)(layout.getBounds().getWidth() / 2);
		int cy = center.y + (int)(layout.getBounds().getHeight() / 2);
		// draw the result
		drawRotatedOutlinedLayout(g, layout, outline, fill, stroke, new Point(cx, cy), rotation);

		start = System.nanoTime() - start;

		PerformanceMonitor.getInstance().report("DrawOutlinedStringRotated", start);
	}

	// Returns bounding rectangle of a string if it were rendered on a surface
	public static Rectangle2D getStringBoundingRect(Graphics2D g, String s, Font f){
		if (s == null) return new Rectangle(0,0,0,0);
		if (s.length() == 0) return new Rectangle(0,0,0,0);
				
		g.setFont(f);
		
		// See Java API FontMetrics class
		FontMetrics metrics = g.getFontMetrics(f);
		Rectangle2D rect = metrics.getStringBounds(s, g);
		return rect;
	}
	
	// Returns a text layout object of a string
	public static TextLayout getLayout(Graphics2D g, String s, Font f){
		return new TextLayout(s, f, g.getFontRenderContext());
	}

	// Draws a text layout
		// Deprecated because of inefficiency
	@Deprecated
	public static void drawOutlinedLayout(Graphics2D g, TextLayout layout, Color outline, Color fill, float stroke, Point position){
	
		if (outline == null) outline = new Color(0, 0, 0, 0);
		if (fill == null) fill = new Color(0, 0, 0, 0);
		
		AffineTransform transform = new AffineTransform();
		transform.setToTranslation(position.x, position.y);
		Shape soutline = layout.getOutline(transform);
		
		Graphics2D g2 = (Graphics2D)g.create();
		
		g2.setColor(outline);
		g2.setStroke(new BasicStroke(stroke));
		g2.draw(soutline);
		g2.setColor(fill);
		g2.fill(soutline);
	}

	// Draws a text layout and rotates it
	public static void drawRotatedOutlinedLayout(Graphics2D g, TextLayout layout, Color outline, Color fill, 
		float stroke, Point position, double rotation){
		if (outline == null) outline = new Color(0, 0, 0, 0);
		if (fill == null) fill = new Color(0, 0, 0, 0);
		
		AffineTransform transform = new AffineTransform();
		transform.setToTranslation(position.x, position.y);
		transform.rotate(rotation, layout.getBounds().getWidth() / 2, layout.getBounds().getHeight() / 2);
		
		Shape soutline = layout.getOutline(transform);
		
		Graphics2D g2 = (Graphics2D)g.create();
		
		g2.setColor(outline);
		g2.setStroke(new BasicStroke(stroke));
		g2.draw(soutline);
		g2.setColor(fill);
		g2.fill(soutline);
	}
}
