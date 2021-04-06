package ARealmReborn;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.*;
public class Main {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File fileOne = new File("Intro.wav");//put your audio file here (File Name Please). Copy the Audio file in your project
        Clip clipOne = AudioSystem.getClip();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileOne);
        clipOne.open(audioStream);
        clipOne.start();
        ArrayList<String> intro = new ArrayList<>();
        ArrayList x = new ArrayList();
        intro.add("Good to see you. ");
        intro.add("Shall we get started? ");
        intro.size();
        intro.add(2,"This is a Snake Game");
        intro.get(0);
        intro.set(0, "Good to see you. ");
        intro.add("SSS");
        intro.remove(3);
        ArrayList<Integer> y = new ArrayList<Integer>();
        ArrayList<Integer> intro2 = new ArrayList<Integer>();
        intro2.add(0);
        intro2.add(1);
        intro2.add(2);
        //ArrayStuff.reverse(intro2);//you don't need it
        intro2.remove(0);
        for(String a : intro)
        {
            System.out.println(a);
        }
        System.out.println("Are you ready play the game [yes or no] ");
        Scanner response = new Scanner(System.in);
        String userResponse = response.nextLine();
        if (userResponse.equals("yes"))
        {
            System.out.println("Well, let's get started. ");
        } else {
            System.out.println("Ok, let's start anyway. ");
        }
        clipOne.stop();
        ArrayList <Integer> countDown = new ArrayList();
        countDown.add(3);
        countDown.add(2);
        countDown.add(1);
        countDown.add(0);
        for(int count : countDown)
        {
            System.out.println(count);
            System.out.println();
        }
        new PlayGame();
        System.out.println("Do you want to listen to the end music ? [yes] or [no] ");
        Scanner response2 = new Scanner(System.in);
        String userResponse2 = response2.nextLine();
        if(userResponse2.equals("yes"))
        {
            File fileTwo = new File("FailMusic.wav");//put your audio file here (File Name Please). Copy the Audio file in your project
            Clip clipTwo = AudioSystem.getClip();
            audioStream = AudioSystem.getAudioInputStream(fileTwo);
            clipTwo.open(audioStream);
            clipTwo.start();
        } else {
            System.out.println("OK, Bye Bye");
        }
    }
}