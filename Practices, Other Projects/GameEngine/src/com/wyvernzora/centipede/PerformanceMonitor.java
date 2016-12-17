/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/PerformanceMonitor.java
 * -----------------------------------------------------------------------
 * 
 * This class is for my personal debugging uses only.
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

import java.math.BigDecimal;
import java.util.HashMap;

public class PerformanceMonitor {
	private static PerformanceMonitor _instance = null;
	public static PerformanceMonitor getInstance(){
		if (_instance == null) _instance = new PerformanceMonitor();
		return _instance;
	}
	
	HashMap<String, BigDecimal> perflog = new HashMap<String, BigDecimal>();
	HashMap<String, Integer> calllog = new HashMap<String, Integer>();
	
	public void report(String routine, long duration){
		if (!perflog.containsKey(routine))
			{
			perflog.put(routine, new BigDecimal(0));
			calllog.put(routine, 0);
			}
		BigDecimal dec = perflog.get(routine).add(new BigDecimal(duration));
		perflog.put(routine, dec);
		calllog.put(routine, calllog.get(routine) + 1);
	}

	public String toString(){
		StringBuilder sbldr = new StringBuilder();
		for (String key:perflog.keySet()){
			sbldr.append(String.format("%s,%s,%s\n", key, perflog.get(key).toString(), calllog.get(key).toString()));
		}
		return sbldr.toString();
	}
}
