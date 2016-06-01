package by.ansgar.rpg.music_manager;


import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioThread implements Runnable {
	public void run() {
		try {
			Player p = new Player(getClass().getResourceAsStream("/musics/lvl1.mp3"));
			p.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

}