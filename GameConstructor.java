package ARealmReborn;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
public class GameConstructor extends JPanel implements ActionListener
{
    private boolean running = false;
    private int bodyParts = 6;
    private int highScore;
    private char direction = 'R';
    private int applesEaten;
    private int appleX;
    private int appleY;
    private static int delay = 100;
    Timer timer;
    Random random;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 800;
    private static final int UNIT_SIZE = 25;
    private static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    private final int x[] = new int[GAME_UNITS];
    private final int y[] = new int[GAME_UNITS];

    public void draw (Graphics a)
    {
        if(running == true)
        {
            for(int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++){
                a.drawLine(
                        i*UNIT_SIZE,
                        0,
                        i*UNIT_SIZE,
                        SCREEN_HEIGHT
                );
                a.drawLine(
                        0,
                        i*UNIT_SIZE,
                        SCREEN_WIDTH,
                        i*UNIT_SIZE
                );
            }
            a.setColor(Color.BLUE);
            a.fillOval(appleX,appleY,UNIT_SIZE,UNIT_SIZE);
            for(int i = 0; i < bodyParts; i++)
            {
                if(i == 0)
                {
                    a.setColor(Color.GREEN);
                    a.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }else{
                    a.setColor(new Color(45,180,0));
                    a.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
                }
            }
            a.setColor(Color.YELLOW);
            a.setFont(new Font("Monaco", Font.PLAIN, 40));
            FontMetrics metrics = getFontMetrics(getFont());
            a.drawString("Score: " + applesEaten, (SCREEN_HEIGHT - metrics.stringWidth("Score: " + applesEaten)) / 4, a.getFont().getSize());
        }
        else {
            highScore(applesEaten);
            gameOver(a);
        }
    }
    public class MyKeyAdapter extends KeyAdapter{
        public void Pressed(KeyEvent a)
        {
            switch (a.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                    {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                    {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D')
                    {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                    {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
    public void around(){
        for (int i = bodyParts; i > 0; i--)
        {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch (direction)
        {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    GameConstructor()
    {
        random = new Random();
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());
        Begin();
    }
    public void paintComponent(Graphics a)
    {
        super.paintComponent(a);
        draw(a);
    }
    public void Apple()
    {
        appleX = random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
        delay --;
    }
    public void actionPerformed(ActionEvent a)
    {
        if (running)
        {
            around();
            checkApple();
            check();
        }
        repaint();
    }
    public void checkApple()
    {
        if((x[0] == appleX) && (y[0] == appleY))
        {
            bodyParts++;
            applesEaten++;
            Apple();
        }
    }
    public void check()
    {
        for (int i = bodyParts; i>0; i--)
        {
            if ((x[0] == x[i]) && (y[0] == y[i]))
            {
                running = false;
            }
        }
        if (x[0] < 0)
        {
            running = false;
        }
        if (x[0] > SCREEN_WIDTH)
        {
            running = false;
        }
        if (y[0] < 0)
        {
            running = false;
        }
        if (y[0] > SCREEN_HEIGHT)
        {
            running = false;
        }
        if (running == false)
        {
            timer.stop();
        }
    }
    public void highScore(int a)
    {
        if (a > highScore)
        {
            highScore = a;
        }
    }
    public void Begin()
    {
        Apple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }
    public void gameOver(Graphics a)
    {
        a.setColor(Color.orange);
        a.setFont(new Font("Monaco", Font.PLAIN, 40));
        FontMetrics metrics1 = getFontMetrics(a.getFont());
        a.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 4 - 75, a.getFont().getSize());
        a.drawString("HighScore: " + highScore, (SCREEN_WIDTH - metrics1.stringWidth("HighScore: " + highScore)) - 75, a.getFont().getSize());
        a.setColor(Color.orange);
        a.setFont(new Font("Monaco", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(a.getFont());
        a.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_WIDTH / 2);
    }
}