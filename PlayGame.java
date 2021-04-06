package ARealmReborn;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PlayGame extends JFrame
{
    PlayGame()
    {
        File fileOne = new File("GamePlayTheme.wav");//put your audio file here (File Name Please). Copy the Audio file in your project
        Clip clipOne = null;
        try {
            clipOne = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(fileOne);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clipOne.open(audioStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clipOne.start();
        GameConstructor a = new GameConstructor();
        add(a);
        setTitle("Snake Easter");
        setResizable(true);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        System.out.println("Stop the Gameplay music now ? [yes or no] ");
        Scanner response = new Scanner(System.in);
        String userResponse = response.nextLine();
        if (userResponse.equals("yes"))
        {
            clipOne.close();
        }
    }
}
