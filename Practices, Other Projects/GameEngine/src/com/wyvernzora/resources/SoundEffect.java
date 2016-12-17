/* Centipede - Deep Space Remix
 * Copyright (C) 2011 Jieni Luchijinzhou a.k.a. Denis Luchkin-Zhou
 * -----------------------------------------------------------------------
 *   /com/wyvernzora/centipede/resources/SoundEffect.java
 * -----------------------------------------------------------------------
 * 
 * Special enum for handling sound related tasks.
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
import java.io.IOException;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.wyvernzora.barlog.Game;
import com.wyvernzora.centipede.Launcher;
import com.wyvernzora.centipede.Launcher.ErrorType;
  

 public enum SoundEffect {
	 // Sound Effects used in the game
   FATALITY("/com/wyvernzora/resources/FATALITY.wav"),
   EXCELLENT("/com/wyvernzora/resources/EXCELLENT.wav"),
   SACRIFICED("/com/wyvernzora/resources/SACRIFICED.wav"),
   ANNIHILATED("/com/wyvernzora/resources/ANNIHILATED.wav"),
   BULLET_SHOT("/com/wyvernzora/resources/BULLET_SHOT.wav"),
   MISSILE_SHOT("/com/wyvernzora/resources/MISSILE_SHOT.wav"),
   ASTR_HIT("/com/wyvernzora/resources/ASTR_HIT.wav"),
   EXPLODE2("/com/wyvernzora/resources/EXPLODE2.wav"),
   ROUND1("/com/wyvernzora/resources/R_1.wav"),
   ROUND2("/com/wyvernzora/resources/R_2.wav"),
   ROUND3("/com/wyvernzora/resources/R_3.wav"),
   ROUND4("/com/wyvernzora/resources/R_4.wav"),
   ROUND5("/com/wyvernzora/resources/R_5.wav"),
   ROUND6("/com/wyvernzora/resources/R_6.wav"),
   ROUND7("/com/wyvernzora/resources/R_7.wav"),
   ROUND8("/com/wyvernzora/resources/R_8.wav"),
   BTN_MOUSEENTER("/com/wyvernzora/resources/BUTTON_ME.wav"),
   UPGRADE_PURCHASED("/com/wyvernzora/resources/UPGRADE_PURCHASE.wav"),
   NEGATIVE("/com/wyvernzora/resources/NEGATIVE.wav"),
   EXPLODE("/com/wyvernzora/resources/EXPLODE.wav");
   
   // Nested enum for specifying volume
   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }
   
   // Current volume
   public static Volume volume = Volume.LOW;
   
   // Each sound effect has its own clip, loaded with its own sound file.
   private Clip clip;
   
   // Constructor to construct each element of the enum with its own sound file.
   SoundEffect(String soundFileName) {
      try {
    	 java.net.URL url = Game.class.getResource(soundFileName);
         // Set up an audio input stream piped from the sound file.
         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
         // Get a clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         Launcher.reportError(ErrorType.Silent, this, "Attempt to load unsupported sound file [%s]!", soundFileName);
      } catch (IOException e) {
          Launcher.reportError(ErrorType.Silent, this, "An error of type [%s] occured while loading [%s]", e.getClass().getSimpleName(), soundFileName);
      } catch (LineUnavailableException e) {
          Launcher.reportError(ErrorType.Silent, this, "An error of type [%s] occured while loading [%s]", e.getClass().getSimpleName(), soundFileName);
      }
   }
     
   // Play or Re-play the sound effect from the beginning, by rewinding.
   public void play() {
	   playAsync();
   }
   
   // Play or replay the sound effect from the beginning by rewinding
   		// Concurrent version that doesn't jam Barlog Engine's update loop
   public void playAsync() {
	   Thread async = new Thread(new Runnable(){

		@Override
		public void run() {
			if (volume != Volume.MUTE) {
		        if (clip.isRunning())
		          clip.stop();   // Stop the player if it is still running
		       clip.setFramePosition(0); // rewind to the beginning
		        clip.start();     // Start playing
		      }
		}
		   
	   });
	   async.setPriority(Thread.MAX_PRIORITY);
	   async.start();
   }
   
   // Optional static method to pre-load all the sound files.
   public static void init() {
      values(); // calls the constructor for all the elements
   }
   
   //MIDI Sequence
   private static Sequencer sequencer;
   
   public static void startSequencer(){
	   if (sequencer != null) sequencer.start();
   }
   
   public static void stopSequencer(){
		if (sequencer != null) {
			sequencer.stop();
		}
		//sequencer = null;

   }
   
   public static void sequencerLoop(boolean b){
	   sequencer.setLoopCount(b ? Sequencer.LOOP_CONTINUOUSLY : 0);
   }
   
   public static void loadMidi(String file){
	   if (sequencer == null){
		   try{
			   sequencer = MidiSystem.getSequencer();
			   sequencer.open();
			   sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
		   } catch (Exception ex) {
				  Launcher.debugPrint(EXPLODE, Color.RED, "An error of type [%s] occured while initializing midi sequencer.", ex.getClass().getSimpleName()); 
		   }
	   }
	   
	   stopSequencer();
	   
	   try{
		   Sequence sequence = MidiSystem.getSequence(file.getClass().getResourceAsStream(file));
		   sequencer.setSequence(sequence);
		   sequencerLoop(true);
	   } catch (Exception ex) {
		  Launcher.debugPrint(EXPLODE, Color.RED, "An error of type [%s] occured while loading midi sequence.", ex.getClass().getSimpleName()); 
	   }
	   

   }

   public static void disposeSequencer(){
	   sequencer.close();
   }
 }