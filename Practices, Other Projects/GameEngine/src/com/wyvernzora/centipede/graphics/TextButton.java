/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/graphics/TextButton.java
 * -----------------------------------------------------------------------
 * 
 * Button represented by a line of text
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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.barlog.KeyState;
import com.wyvernzora.barlog.TextGraphics;
import com.wyvernzora.barlog.controls.ButtonBase;
import com.wyvernzora.centipede.CentipedeGame;

public abstract class TextButton extends ButtonBase {

	CentipedeGame game = (CentipedeGame) super.game;
	protected String cap;
	protected Color[] fill;
	protected Color[] outline;
	protected Font fnt;
	protected float stroke;
	protected int vstate = 0;
	
	public TextButton(Game gm, Rectangle bounds, String cap, Font fnt, Color[] fill, Color[] outline, float stroke) {
		super(gm, bounds);

		this.fnt = fnt;
		this.fill = fill;
		this.outline = outline;
		this.cap = cap;
		this.stroke = stroke;
	}

	@Override
	protected void onMouseEnter() {
		vstate = 1;
	}

	@Override
	protected void onMouseLeave() {
		vstate = 0;
	}

	@Override
	protected void onMouseDown() {
		vstate = 2;
	}

	@Override
	protected void onMouseUp() {
		vstate = 1;
	}

	
	@Override
	public void draw(Graphics2D g) {
		TextGraphics.drawOutlinesStringCentered(g, cap, fnt, outline[vstate], fill[vstate], stroke, new Point(getBounds().x + getBounds().width / 2 + offset.x, getBounds().y + getBounds().height / 2 + offset.y));
		g.setColor(Color.WHITE);
		if (CentipedeGame.getDrawBounds()) g.drawRect(getBounds().x + offset.x, getBounds().y + offset.y, getBounds().width, getBounds().height);
	}

	@Override
	protected void onKeyDown() {
		
	}

	@Override
	protected void onKeyUp() {
		
	}

	@Override
	protected void onKeyPress(KeyState oldState, KeyState newState) {
		
	}
}
