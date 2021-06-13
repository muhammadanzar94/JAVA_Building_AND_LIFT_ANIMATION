import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class building extends JPanel{
    //windows and floors
    static final int windowsPerFloor=10;
    static final int floors=10;

    //start coordinates for windows
    static final int startX=100;
    static final int startY=50;

    //building height and width
    static final int bHeight=550;
    static final int bWidth=400;

    //window height and width calculated according to windowsPerFloor
    static final int wWidth=bWidth/windowsPerFloor-10;
    static final int wHeight=bHeight/floors-10;

    static int elevatorFloor=3;

    static Graphics2D graphics2D;

    static JFrame frame=new JFrame();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        graphics2D = (Graphics2D) g;

        //for ground
        graphics2D.setColor(new Color(92, 255, 0));
        graphics2D.fillRect(0,550,getParent().getWidth(),200);

        //for sky
        graphics2D.setColor(new Color(0, 248, 255));
        graphics2D.fillRect(0,0,getParent().getWidth(),550);


        //for building
        graphics2D.setColor(new Color(78, 90, 71));
        graphics2D.fillRect(100,50,bWidth,bHeight);



        //for windows
        graphics2D.setColor(new Color(165, 165, 0));    //initial color of windows
        //Draw windows in each story using drawStory() function
        for (int i = 0; i < floors; i++) {
            drawStory(graphics2D,startX,(startY+5)+(i*(wHeight+10)),wWidth,wHeight);
        }

        //to draw initial elevator position
        System.out.println("Floor no to lit:"+elevatorFloor);
        graphics2D.setColor(new Color(255, 255, 0));
        graphics2D.fillRect((startX+5)+(5*(wWidth+10)),
                (startY+5)+(elevatorFloor*(wHeight+10)),wWidth,wHeight);

    }


    //Draw windows in each story using drawStory() function
    void drawStory(Graphics2D g2,int storyX,int storyY,int wWidth,int wHeight){

        g2.setColor(new Color(165, 165, 0));    //initial color of windows
            for (int j = 0; j < windowsPerFloor; j++) {
                drawWindow(g2,(storyX+5)+(j*(wWidth+10)),storyY,wWidth,wHeight);
            }
        }

    //Draw windows at a given X and Y
    void drawWindow(Graphics2D g2,int X,int Y,int wWidth,int wHeight){
        g2.setColor(new Color(165, 165, 0));    //initial color of windows
        g2.fillRect(X,Y,wWidth,wHeight);
    }

    static Graphics2D moveElevatortoWindow(Graphics2D g2,int windowNo, int elevatorFloor, int wWidth, int wHeight){
        elevatorFloor+=1;
        g2.setColor(new Color(255, 255, 0));
        g2.fillRect((startX+5)+(windowNo*(wWidth+10)),
                (startY+5)+(elevatorFloor*(wHeight+10)),wWidth,wHeight);
        return g2;
    }


    public static void main(String[] args) {
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(1, 225, 255));

        // add combo box to frame
        JComboBox c;
        //array of options of floors in the combo box
        String stories[] = {"1","2","3","4","5","6","7","8","9","10"};
        c = new JComboBox(stories);
        c.setLocation(10,10);
        c.setSize(50,30);
        frame.add(c);

        //add start elevator button to frame
        JButton start=new JButton();
        start.setText("start");
        start.setLocation(80,10);
        start.setSize(100,30);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Update elevator elevatorFloor
                elevatorFloor=Integer.parseInt(c.getSelectedItem().toString());
                elevatorFloor=10-elevatorFloor;

                System.out.println(elevatorFloor);
                JPanel newpanel=new building();
                frame.add(newpanel);

                frame.repaint();
            }
        });
        frame.add(start);


        //add building to frame
        JPanel jPanel=new building();
        frame.add(jPanel);


        frame.setVisible(true);

    }
}
