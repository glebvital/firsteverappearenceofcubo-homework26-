package org.example;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class MP3Player {

    public void playMP3(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.play();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stopMP3(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            AdvancedPlayer player = new AdvancedPlayer(fis);
            player.close();
        } catch (JavaLayerException | IOException e) {
            e.printStackTrace();
        }
    }

}
