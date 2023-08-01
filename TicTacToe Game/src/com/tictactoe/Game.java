package com.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Game extends JFrame implements ActionListener {
    JLabel header , clock;
    boolean gameOver=false;


    int winner =2;

    int wps[][] =
            {
                    {0,1,2},
                    {3,4,5},
                    {6,7,8},
                    {0,3,6},
                    {1,4,7},
                    {2,5,8},
                    {0,4,8},
                    {2,4,6}
            };
    int gameChances[] = {2,2,2,2,2,2,2,2,2};
    int activePlayer =0;
    JPanel panel;

    JButton[] buttons= new JButton[9];
    Font font = new Font("",Font.BOLD,40);
    Game()
    {
        setTitle("Tic Tac Toe Game");
        setSize(700,700);
        ImageIcon icon = new ImageIcon("src/icons/icon.png");
        setIconImage(icon.getImage());



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createGUI();
        setVisible(true);
    }
    private void createGUI()
    {
        this.getContentPane().setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());

        header= new JLabel("Tic Tac Toe");
        header.setIcon(new ImageIcon("src/icons/icon.png"));
        header.setFont(font);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setForeground(Color.white);


        this.add(header,BorderLayout.NORTH);

        clock = new JLabel("Clock");
        clock.setFont(font);
        clock.setHorizontalAlignment(SwingConstants.CENTER);
        clock.setForeground(Color.white);
        this.add(clock,BorderLayout.SOUTH);

        Thread t=new Thread()
        {
            public void run()
            {
                try {
                    while (true) {
                        String datetime = new Date().toLocaleString();
                        clock.setText(datetime);

                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        };
        t.start();


        panel = new JPanel();
        panel.setLayout(new GridLayout(3,3));

        for(int i=1;i<=9;i++)
        {
            JButton btn = new JButton();
            btn.setFont(font);
            btn.setBackground(Color.YELLOW);
            panel.add(btn);
            buttons[i-1]=btn;
            btn.addActionListener(this);
            btn.setName(String.valueOf(i-1));

        }

        this.add(panel,BorderLayout.CENTER);


    }

    public void actionPerformed(ActionEvent e)
    {
        JButton currentButton = (JButton)e.getSource();
        String namestr =currentButton.getName();
        int name =Integer.parseInt(namestr.trim());
        if(gameOver)
        {
            JOptionPane.showMessageDialog(this,"Game is Over");
            setVisible(false);
            return;
        }

        if (gameChances[name]==2)
        {
            if(activePlayer==1)
            {
                currentButton.setIcon(new ImageIcon("src/icons/cross2.png.png"));
                gameChances[name] = activePlayer;
                activePlayer=0;
            }
            else
            {
                currentButton.setIcon(new ImageIcon("src/icons/circle4.png"));
                gameChances[name] =activePlayer;
                activePlayer=1;
            }
        }

        else {
            JOptionPane.showMessageDialog(this,"Postion Already Occupied");
        }
        for(int temp[]:wps)
        {
            if((gameChances[temp[0]]==gameChances[temp[1]])&&(gameChances[temp[1]]==gameChances[temp[2]])&&(gameChances[temp[2]]!=2))
            {
                winner=gameChances[temp[0]];
                gameOver=true;
                JOptionPane.showMessageDialog(null,"Player "+winner+" has won the Game");
                int i=JOptionPane.showConfirmDialog(this,"Do You Want To Play More ");
                if(i==0)
                {
                    this.setVisible(false);
                    new Game();
                } else if (i==1) {
                    System.exit(12321);
                }
                else {

                }
                break;
            }
        }
        int count =0;
        for(int x:gameChances)
        {
            if(x==2)
            {
                count++;
                break;
            }
        }
        if(count==0 && gameOver==false)
        {
            JOptionPane.showMessageDialog(null,"The Match is Draw");

            int i=JOptionPane.showConfirmDialog(this,"Do You want to Play More");
            if(i==0)
            {
                this.setVisible(false);
                new Game();
            } else if (i==1) {
                System.exit(0);
                
            }else
            {

            }
                gameOver=true;
        }


    }
    public static void main(String[] args)
    {

    }
}
