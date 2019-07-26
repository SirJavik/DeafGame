package net.javik.DeafGame.DeafMenus;

import net.javik.DeafGame.DeafBasics.DeafColor;
import net.javik.DeafGame.DeafBasics.DeafConsole;
import net.javik.DeafGame.DeafBasics.DeafFonts;
import net.javik.DeafGame.DeafBasics.DeafWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;


public class GameMenu extends DeafMenu implements IDeafMenu, ActionListener, ItemListener, MouseListener {
    private DeafWindow window;
    protected int windowHeight,
                  windowWidth,
                  gameHeight,
                  gameWidth,
                  menuHeight,
                  menuWidth;

    protected int randx=0;
    protected int randy=0;

    protected int       targetCount;
    protected long      milliseconds;
    protected int       maxCounts       = 10;
    protected int       count           = -1;
    protected String    timeWithPoints  = "";

    Image       gameBackground,
                menuBackground,
                borderBackground;

    Graphics    gameGraphics,
                menuGraphics,
                borderGraphics;

    JTextField          jtargetSize =   new JTextField("50",10);
    JTextField          jtargetCount =   new JTextField("10",10);

    JTextArea           targetTime          =   new JTextArea("");

    JLabel              targetSizeLabel     =   new JLabel("Größe des Ziels:");
    JLabel              targetCountLabel    =   new JLabel("Ziele pro Durchgang:");

    JComboBox <String>  targets     =   new JComboBox<String>();

    DeafClickableButton buttonGo    = new DeafClickableButton("Go!");
    DeafClickableButton btnRtnMenu  = new DeafClickableButton("Zurück zum Menü");

    DeafTarget deafTarget;

    public GameMenu(DeafWindow window, int height, int width) {
        this.window = window;

        /*
         * Setting proportion of window
         */
        this.windowHeight = height;
        this.windowWidth = width;

        /*
         * Setting proportion of playing area
         */
        this.gameHeight = this.windowHeight;
        this.gameWidth = this.windowWidth - ( this.windowWidth/100*20 );

        /*
         * Setting proportion of menu
         */
        this.menuHeight = this.windowHeight;
        this.menuWidth  = this.windowWidth/100*20;

        this.setLayout(null);
        addMouseListener(this);

        /*
         * Adding elements
         */
        this.add(this.jtargetSize);
        this.add(this.jtargetCount);
        this.add(this.targetTime);

        this.add(this.targetSizeLabel);
        this.add(this.targetCountLabel);
        this.add(this.targets);

        this.add(this.buttonGo);
        this.add(this.btnRtnMenu);
        this.setVisible(true);
        /*
         * Targets
         */
        this.targets.setVisible(true);

        this.targets.addItem("Quadrat");
        this.targets.addItem("Kreis");
        this.targets.addItem("Mond");
        this.targets.addItem("Ring");
        this.targets.setSelectedItem("Quadrat");

        /*
         * Item listeners
         */
        this.buttonGo.addActionListener(this);
        this.btnRtnMenu.addActionListener(this);
        this.targets.addItemListener(this);

        /*
         * Positioning elements
         */
        this.jtargetSize.setBounds(
                this.gameWidth+20,
                40,
                this.menuWidth-60,
                this.jtargetSize.getPreferredSize().height
        );

        this.jtargetCount.setBounds(
                this.gameWidth+20,
                80,
                this.menuWidth-60,
                this.jtargetCount.getPreferredSize().height
        );

        this.targetTime.setBounds(
                this.gameWidth+20,
                200,
                this.targetTime.getPreferredSize().width,
                200
        );

        this.targetSizeLabel.setBounds(
                this.gameWidth+20,
                20,
                this.targetSizeLabel.getPreferredSize().width,
                this.targetSizeLabel.getPreferredSize().height
        );

        this.targetCountLabel.setBounds(
                this.gameWidth+20,
                60,
                this.targetCountLabel.getPreferredSize().width,
                this.targetCountLabel.getPreferredSize().height
        );

        this.targets.setBounds(
                this.gameWidth+20,
                120,
                100,
                25
        );

        this.buttonGo.setBounds(
                this.gameWidth+20,
                220,
                this.buttonGo.getPreferredSize().width,
                this.buttonGo.getPreferredSize().height
        );

        this.btnRtnMenu.setBounds(
                this.gameWidth+20,
                260,
                this.btnRtnMenu.getPreferredSize().width,
                this.btnRtnMenu.getPreferredSize().height
        );
    }

    @Override
    public void paintComponent (Graphics graphics)
    {
        super.paintComponent(graphics);

        if (gameBackground == null) {
            gameBackground = createImage(this.gameWidth, this.gameHeight);
            gameGraphics = gameBackground.getGraphics();
            gameGraphics.setColor(DeafColor.lightYellow);
            gameGraphics.fillRect(0, 0, this.gameWidth, this.gameHeight);
        }

        graphics.drawImage(
                gameBackground,
                0,
                0,
                this.gameWidth,
                this.gameHeight,
                this
        );

        if (menuBackground == null) {
            menuBackground = createImage(this.menuWidth, this.menuHeight);
            menuGraphics = menuBackground.getGraphics();
            menuGraphics.setColor(DeafColor.white);
            menuGraphics.fillRect(0, 0, this.menuWidth, this.menuHeight);
        }

        graphics.drawImage(
                menuBackground,
                this.gameWidth,
                0,
                this.menuWidth,
                this.menuHeight,
                this
        );

        if(borderBackground==null) {
            borderBackground = createImage(2, this.windowHeight);
            borderGraphics = borderBackground.getGraphics();
            borderGraphics.setColor(DeafColor.black);
            menuGraphics.fillRect(0, 0, 2, this.windowHeight);
        }

        graphics.drawImage(
                borderBackground,
                this.gameWidth,
                0,
                2,
                this.windowHeight,
                this
        );
    }

    /* ------------------------------------------------------------------------ *
     * Mouse interactions
     * ------------------------------------------------------------------------ */
    public void mouseClicked(MouseEvent mouse){}

    public void mousePressed(@NotNull MouseEvent mouse)
    {
        int mx = mouse.getX();
        int my = mouse.getY();

        if(count>0)
        {
            if(deafTarget.hit(mx,my))
            {
                DeafConsole.writeLine("Hit at X: " + mx + " Y: " + my);

                long vzeit=(new Date()).getTime()-milliseconds;
                timeWithPoints+=count+". "+vzeit+"\n";
                targetTime.setText(timeWithPoints);

                if(count < maxCounts)
                {
                    count++;
                    deafTarget.color=new Color((int)(Math.random()*230),
                            (int)(Math.random()*230),
                            (int)(Math.random()*230));
                    setTarget();
                }
                else
                {
                    count=-1;
                    scoring();
                }
            }
            else
            {
                gameGraphics.setColor(DeafColor.lightYellow);
                gameGraphics.fillRect(0,0,this.gameWidth,this.gameHeight);
                int g=deafTarget.size;
                deafTarget.size=(int)(deafTarget.size*.9);
                deafTarget.draw(deafTarget.posX+(int)(g*.05),deafTarget.posY+(int)(g*.05));
                repaint();
            }
        }
    }

    public void mouseReleased(MouseEvent mouse){}

    public void mouseEntered(MouseEvent mouse){

    }
    public void mouseExited(MouseEvent mouse){}

    /* ------------------------------------------------------------------------ *
     * Action listener
     * ------------------------------------------------------------------------ */
    public void actionPerformed(ActionEvent e)
    {
        String s;
        if(e.getSource()  instanceof DeafClickableButton)
        {
            DeafClickableButton b = (DeafClickableButton)(e.getSource());
            s = b.getText();
            if(s.equals("Go!")) {
                DeafConsole.writeLine("Spielstart...");
                go();
            } else if(s.equals("Zurück zum Menü")) {
                DeafConsole.writeLine("Zurück zum Menü!");
                window.remove(this);

                window.setLayout(null);

                MainMenu MainMenu = new MainMenu(window, window.getHeight(), window.getWidth(), "assets/img/nasa-Q1p7bh3SHj8-unsplash.jpg", "1.0.0");
                MainMenu.setBounds(0,0,window.getWidth(), window.getHeight());

                window.add(MainMenu);
                this.update(window);
            }
        }
    }

    /* ------------------------------------------------------------------------ *
     * Item listener
     * ------------------------------------------------------------------------ */
    public void itemStateChanged(ItemEvent ie)
    {
        String s = (String)targets.getSelectedItem();
        if(s.equals("Kreis") || s.equals("Quadrat"));
    }

    /* ------------------------------------------------------------------------ *
     * Targets
     * ------------------------------------------------------------------------ */
    abstract class DeafTarget
    {
        protected int   size,
                        posX,
                        posY;
        protected Color color;
        protected double surface =0;

        void draw(int x,int y)
        {
            DeafConsole.writeLine(surface);
            DeafConsole.writeLine(calcSurface());
        }

        public boolean hit(int x, int y)
        {
            return true;
        }
        int calcSurface()
        {
            int f=0;
            for(int i = posX; i<=posX+ size && i>=posX; i++)
                for(int j = posY; j<=posY+ size && i>=posY; j++)
                    if(hit(i+randx,j+randy)) f++;
            return f;
        }
    }

    class SquareTarget extends DeafTarget
    {
        public void draw(int x,int y)
        {
            posX=x;
            posY=y;
            gameGraphics.setColor(color);
            gameGraphics.fillRect(x,y, size, size);
            surface = size * size;
            super.draw(x,y);
        }

        public boolean hit(int x, int y)
        {
            return  x-randx>=posX
                    && x-randx<posX+ size
                    && y-randy>=posY
                    && y-randy<posY+ size;
        }
    }

    class RingTarget extends DeafTarget
    {
        public void draw(int x,int y)
        {
            posX=x;
            posY=y;
            gameGraphics.setColor(color);
            gameGraphics.fillOval(x,y, size, size);
            surface =Math.PI* size * size /4;


            gameGraphics.setColor(DeafColor.lightYellow);
            gameGraphics.fillOval(x+10,y+10, size -20, size -20);
            surface =Math.PI*((size -20)*(size)-20)/4;
            super.draw(x,y);
        }

        public boolean hit(int x, int y)
        {
            double ax=posX+ size /2.0+randx-x;
            double ay=posY+ size /2.0+randy-y;
            return Math.sqrt(ax*ax+ay*ay)<= size /2.0;
        }
    }

    class MoonTarget extends DeafTarget
    {
        public void draw(int x,int y)
        {
            posX=x;
            posY=y;
            gameGraphics.setColor(color);
            gameGraphics.fillOval(x,y, size, size);
            surface = Math.PI* size * size /4;

            gameGraphics.setColor(DeafColor.lightYellow);
            gameGraphics.fillOval(x-15,y, size, size);
            surface =Math.PI* size * size /4;
            super.draw(x,y);
        }

        public boolean hit(int x, int y)
        {
            double ax=posX+ size /2.0+randx-x;
            double ay=posY+ size /2.0+randy-y;
            return Math.sqrt(ax*ax+ay*ay)<= size /2.0;
        }
    }

    class CircleTarget extends DeafTarget
    {
        public void draw(int x,int y)
        {
            posX=x;
            posY=y;
            gameGraphics.setColor(color);
            gameGraphics.fillOval(x,y, size, size);
            surface =Math.PI* size * size /4;
            super.draw(x,y);
        }

        public boolean hit(int x, int y)
        {
            double ax=posX+ size /2.0+randx-x;
            double ay=posY+ size /2.0+randy-y;
            return Math.sqrt(ax*ax+ay*ay)<= size /2.0;
        }
    }

    public void setTarget()
    {
        int x,y;
        gameGraphics.setColor(DeafColor.lightYellow);
        gameGraphics.fillRect(0,0,this.gameWidth, this.gameWidth);

        x = (int)(Math.random() * (this.gameWidth - deafTarget.size - deafTarget.size));
        y = (int)(Math.random() * (this.gameHeight - deafTarget.size - deafTarget.size));

        DeafConsole.writeLine("X: " + x + " Y: " + y);

        deafTarget.draw(x,y);
        repaint();
    }
    /* ------------------------------------------------------------------------ *
     * Run game
     * ------------------------------------------------------------------------ */
    void go()
    {
        String selectedTarget = (String)targets.getSelectedItem();
        
        if(selectedTarget.equals("Kreis"))
        {
            deafTarget= new CircleTarget();
            deafTarget.color =Color.green;
        }
        else if(selectedTarget.equals("Quadrat"))
        {
            deafTarget = new SquareTarget();
            deafTarget.color =Color.red;
        }
        else if(selectedTarget.equals("Ring"))
        {
            deafTarget = new RingTarget();
            deafTarget.color =Color.red;
        }
        else if(selectedTarget.equals("Mond"))
        {
            deafTarget = new MoonTarget();
            deafTarget.color =Color.red;
        }
        
        targetCount = Integer.parseInt(this.jtargetSize.getText());
        deafTarget.size = targetCount;

        setTarget();
        milliseconds = (new Date()).getTime();
        timeWithPoints = "";
        this.targetTime.setText(timeWithPoints);
        maxCounts =Integer.parseInt(this.jtargetCount.getText());
        count =1;
    }

    public void scoring()
    {
        gameGraphics.setColor(DeafColor.lightYellow);
        gameGraphics.fillRect(0,0,this.gameWidth,this.gameHeight);
        gameGraphics.setFont(DeafFonts.gameFont);
        gameGraphics.setColor(DeafColor.red);
        milliseconds = (new Date()).getTime()-milliseconds;
        gameGraphics.drawString("Zeit: " + milliseconds / 1000.0,100,300);

        long points = Math.round( 100000 /milliseconds / Math.sqrt(deafTarget.surface) * 100);

        System.out.println("Fläche: "+ deafTarget.surface);

        gameGraphics.drawString("Punkte: " + points,100,200);

        repaint();
        deafTarget = null;
    }
}
