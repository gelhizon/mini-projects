/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/resources/FrameSequence.java
 * -----------------------------------------------------------------------
 * 
 * A circular buffer of animation key frames.
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

import java.awt.Image;

public class FrameSequence {
	
	private Image[] m_frames;
	private int m_pt = 0;
	
	public FrameSequence(Image...frames){
		m_frames = frames;
	}
	
	public Image nextFrame(){
		if (m_pt >= m_frames.length) m_pt = 0;
		return m_frames[m_pt++];
	}
	
	public Image nextFrameNoLoop(){
		if (m_pt >= m_frames.length) return null;
		return m_frames[m_pt++];
	}
	
	public Image currentFrame(){
		return m_frames[m_pt];
	}
	
	public void reset(){
		m_pt = 0;
	}
	
	public Image getFrameAt(int i){
		if (i >= 0 || i < m_frames.length) return m_frames[i];
		else return null;
	}
}
