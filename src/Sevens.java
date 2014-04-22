
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Sevens extends JComponent implements ActionListener
{
	//You can change the size of the board and the base numbers to match the different variants of this game.
	//The graphics are a little weird if you do something thats not "fives" because the numbers are different sizes and I havent accounted for that yet.
	public static final int BOARD_WIDTH = 4;
	public static final int BOARD_HEIGHT = 4;
	public static final int BASE_LOW = 2;
	public static final int BASE_HIGH = 3;
	public static final int BASE = BASE_LOW + BASE_HIGH;
	public static int HIGHEST_UNLOCKED = 0;
	public static int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
	

	public static void main(String[] args)
	{
		Random initialPlacement = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < (BOARD_WIDTH + BOARD_HEIGHT) / 2; i++)
		{
			board[initialPlacement.nextInt(BOARD_WIDTH)][initialPlacement.nextInt(BOARD_HEIGHT)] = BASE_LOW;
			board[initialPlacement.nextInt(BOARD_WIDTH)][initialPlacement.nextInt(BOARD_HEIGHT)] = BASE_HIGH;
		}
		
		
		JFrame window = new JFrame("Sevens");
		Sevens game = new Sevens();
		window.add(game);
		window.pack();
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setFocusable(true);
		window.requestFocusInWindow();
		window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		Timer t = new Timer(750, game);
		t.start();
		
		
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(1000, 1000);
	}

	public void drawToken(Graphics g, Color tokenColor, Color textColor, int fontSize, int x, int y, int height, int width, int tokenNumber, int xOffset, int yOffset)
	{
		g.setColor(tokenColor);
		g.fillOval(x * 100, y * 100, height, width);
		if(tokenNumber > BASE_HIGH)
		{
			g.setColor(Color.WHITE);
			g.fillOval(x * 100 + 3, y * 100 + 3, height - 6, width - 6);
		}
		g.setColor(textColor);
		g.drawString(Integer.toString(tokenNumber), x * 100 + xOffset, y * 100 + yOffset);
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
		for(int i = 0; i < BOARD_HEIGHT; i++)
		{
			for(int j = 0; j < BOARD_WIDTH; j++)
			{
				
				switch(board[j][i])
				{
				case BASE_LOW: //2
					drawToken(g, new Color(100, 115, 130), Color.WHITE, 32, j, i, 74, 74, board[j][i], 27, 50);
					break;
				case BASE_HIGH: //3
					drawToken(g, new Color(130, 230, 130), new Color(100, 115, 130), 32, j, i, 74, 74, board[j][i], 27, 50);
					break;
				case BASE: //5
					drawToken(g, new Color(130, 245, 160), Color.BLACK, 32, j, i, 74, 74, board[j][i], 27, 50);
					if(HIGHEST_UNLOCKED < BASE)
						HIGHEST_UNLOCKED = BASE;
					break;
				case BASE * 2: //10
					drawToken(g, new Color(255, 0, 0), Color.BLACK, 32, j, i, 74, 74, board[j][i], 16, 50);
					if(HIGHEST_UNLOCKED < BASE * 2)
						HIGHEST_UNLOCKED = BASE * 2;
					break;
				case BASE * 4: //20
					drawToken(g, new Color(0, 0, 255), Color.BLACK, 32, j, i, 74, 74, board[j][i], 17, 50);
					if(HIGHEST_UNLOCKED < BASE * 4)
						HIGHEST_UNLOCKED = BASE * 4;
					break;
					
					/*	I'm in the process of changing these over to just call the drawToken method rather than have a bunch of lines each.
						Above is the new way, below is the old way.	*/
					
				case BASE * 8: //40
					g.setColor(new Color(255, 255, 0));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(board[j][i]), j * 100 + 16, i * 100 + 50);
					if(HIGHEST_UNLOCKED < BASE * 8)
						HIGHEST_UNLOCKED = BASE * 8;
					break;
				case BASE * 16: //80
					g.setColor(new Color(200, 70, 240));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(board[j][i]), j * 100 + 16, i * 100 + 50);
					if(HIGHEST_UNLOCKED < BASE * 16)
						HIGHEST_UNLOCKED = BASE * 16;
					break;
				case BASE * 32: //160
					g.setColor(new Color(50, 255, 100));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(board[j][i]), j * 100 + 7, i * 100 + 50);
					if(HIGHEST_UNLOCKED < BASE * 32)
						HIGHEST_UNLOCKED = BASE * 32;
					break;
				case BASE * 64: //320
					g.setColor(new Color(200, 0, 0));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(board[j][i]), j * 100 + 7, i * 100 + 50);
					if(HIGHEST_UNLOCKED < BASE * 64)
						HIGHEST_UNLOCKED = BASE * 64;
					break;
				case BASE * 128: //640
					g.setColor(new Color(200, 70, 240));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(board[j][i]), j * 100 + 7, i * 100 + 50);
					if(HIGHEST_UNLOCKED < BASE * 128)
						HIGHEST_UNLOCKED = BASE * 128;
					break;
				case BASE * 256: //1280
					g.setColor(new Color(255, 170, 30));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
					g.drawString(Integer.toString(board[j][i]), j * 100 + 6, i * 100 + 46);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 256)
						HIGHEST_UNLOCKED = BASE * 256;
					break;
				case BASE * 512: //2560
					g.setColor(new Color(30, 90, 255));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
					g.drawString(Integer.toString(board[j][i]), j * 100 + 7, i * 100 + 46);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 512)
						HIGHEST_UNLOCKED = BASE * 512;
					break;
				case BASE * 1024:
					g.setColor(new Color(155, 155, 155));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
					g.drawString(Integer.toString(board[j][i]), j * 100 + 4, i * 100 + 50);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 1024)
						HIGHEST_UNLOCKED = BASE * 1024;
					break;
				case BASE * 2048:
					g.setColor(new Color(155, 155, 155));
					g.fillOval(j * 100, i * 100, 74, 74);
					g.setColor(Color.WHITE);
					g.fillOval(j * 100 + 3, i * 100 + 3, 68, 68);
					g.setColor(Color.BLACK);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
					g.drawString(Integer.toString(board[j][i]), j * 100 + 4, i * 100 + 50);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 2048)
						HIGHEST_UNLOCKED = BASE * 2048;
					break;
				default:
					g.setColor(Color.BLACK);
				}
				g.setColor(Color.BLACK);
				g.drawOval(j * 100, i * 100, 74, 74);
				
			}
		}
	}

	public void moveRIGHT()
	{
		for(int i = 0; i < BOARD_HEIGHT; i++)
		{
			boolean matchedNum = false;
			for(int j = BOARD_WIDTH - 1; j >= 0; j--)
			{
				if(j - 1 >= 0)
				{
					if(!matchedNum)
					{
						if((board[j][i] == board[j-1][i]) && (board[j][i] >= BASE))
						{
							board[j][i] = board[j][i] * 2;
							board[j-1][i] = 0;
							matchedNum = true;
						}
						else if(((board[j][i] == BASE_LOW) && (board[j-1][i] == BASE_HIGH)) || ((board[j][i] == BASE_HIGH) && (board[j-1][i] == BASE_LOW)))
						{
							board[j][i] = BASE;
							board[j-1][i] = 0;
							matchedNum = true;
						}
					}
					if(board[j][i] == 0)
					{
						board[j][i] = board[j-1][i];
						board[j-1][i] = 0;
					}
					
				}
				
			}
		}
		boolean newPlaced = false;
		Random rand = new Random();
		while(!newPlaced)
		{
			int i = rand.nextInt(BOARD_HEIGHT);
			if(board[0][i] == 0)
			{
				newPlaced = true;
				switch(rand.nextInt(4))
				{
				case 0:
					board[0][i] = BASE_LOW;
					break;
				case 1:
					board[0][i] = BASE_HIGH;
					break;
				case 2:
					board[0][i] = BASE;
					break;
				case 3:
					board[0][i] = BASE * 2;
					break;
				}
			}
		}
		repaint();
	}
	
	public void moveDOWN()
	{
		for(int j = 0; j < BOARD_WIDTH; j++)
		{
			boolean matchedNum = false;
			for(int i = BOARD_HEIGHT - 1; i >= 0; i--)
			{
				if(i - 1 >= 0)
				{
					if(!matchedNum)
					{
						if((board[j][i] == board[j][i-1]) && (board[j][i] >= BASE))
						{
							board[j][i] = board[j][i] * 2;
							board[j][i-1] = 0;
							matchedNum = true;
						}
						else if(((board[j][i] == BASE_LOW) && (board[j][i-1] == BASE_HIGH)) || ((board[j][i] == BASE_HIGH) && (board[j][i-1] == BASE_LOW)))
						{
							board[j][i] = BASE;
							board[j][i-1] = 0;
							matchedNum = true;
						}
					}
					if(board[j][i] == 0)
					{
						board[j][i] = board[j][i-1];
						board[j][i-1] = 0;
					}
					
				}
				
			}
		}
		boolean newPlaced = false;
		Random rand = new Random();
		while(!newPlaced)
		{
			int i = rand.nextInt(BOARD_WIDTH);
			if(board[i][0] == 0)
			{
				newPlaced = true;
				switch(rand.nextInt(4))
				{
				case 0:
					board[i][0] = BASE_LOW;
					break;
				case 1:
					board[i][0] = BASE_HIGH;
					break;
				case 2:
					board[i][0] = BASE;
					break;
				case 3:
					board[i][0] = BASE * 2;
					break;
				}
			}
		}
		repaint();
	}
	public void moveLEFT()
	{
		for(int i = 0; i < BOARD_HEIGHT; i++)
		{
			boolean matchedNum = false;
			for(int j = 0; j < BOARD_WIDTH; j++)
			{
				if(j + 1 < BOARD_WIDTH)
				{
					if(!matchedNum)
					{
						if((board[j][i] == board[j+1][i]) && (board[j][i] >= BASE))
						{
							board[j][i] = board[j][i] * 2;
							board[j+1][i] = 0;
							matchedNum = true;
						}
						else if(((board[j][i] == BASE_LOW) && (board[j+1][i] == BASE_HIGH)) || ((board[j][i] == BASE_HIGH) && (board[j+1][i] == BASE_LOW)))
						{
							board[j][i] = BASE;
							board[j+1][i] = 0;
							matchedNum = true;
						}
					}
					if(board[j][i] == 0)
					{
						board[j][i] = board[j+1][i];
						board[j+1][i] = 0;
					}
					
				}
				
			}
		}
		boolean newPlaced = false;
		Random rand = new Random();
		while(!newPlaced)
		{
			int i = rand.nextInt(BOARD_HEIGHT);
			if(board[BOARD_WIDTH-1][i] == 0)
			{
				newPlaced = true;
				switch(rand.nextInt(4))
				{
				case 0:
					board[BOARD_WIDTH-1][i] = BASE_LOW;
					break;
				case 1:
					board[BOARD_WIDTH-1][i] = BASE_HIGH;
					break;
				case 2:
					board[BOARD_WIDTH-1][i] = BASE;
					break;
				case 3:
					board[BOARD_WIDTH-1][i] = BASE * 10;
					break;
				}
			}
		}
		repaint();
	}
	public void moveUP()
	{
		for(int j = 0; j < BOARD_WIDTH; j++)
		{
			boolean matchedNum = false;
			for(int i = 0; i < BOARD_HEIGHT; i++)
			{
				if(i + 1 < BOARD_HEIGHT)
				{
					if(!matchedNum)
					{
						if((board[j][i] == board[j][i+1]) && (board[j][i] >= BASE))
						{
							board[j][i] = board[j][i] * 2;
							board[j][i+1] = 0;
							matchedNum = true;
						}
						else if(((board[j][i] == BASE_LOW) && (board[j][i+1] == BASE_HIGH)) || ((board[j][i] == BASE_HIGH) && (board[j][i+1] == BASE_LOW)))
						{
							board[j][i] = BASE;
							board[j][i+1] = 0;
							matchedNum = true;
						}
					}
					if(board[j][i] == 0)
					{
						board[j][i] = board[j][i+1];
						board[j][i+1] = 0;
					}
					
				}
				
			}
		}
		boolean newPlaced = false;
		Random rand = new Random();
		while(!newPlaced)
		{
			int i = rand.nextInt(BOARD_WIDTH);
			if(board[i][BOARD_HEIGHT-1] == 0)
			{
				newPlaced = true;
				switch(rand.nextInt(4))
				{
				case 0:
					board[i][BOARD_HEIGHT-1] = BASE_LOW;
					break;
				case 1:
					board[i][BOARD_HEIGHT-1] = BASE_HIGH;
					break;
				case 2:
					board[i][BOARD_HEIGHT-1] = BASE;
					break;
				case 3:
					board[i][BOARD_HEIGHT-1] = BASE * 2;
					break;
				}
			}
		}
		
		repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Random rand = new Random();
		switch(rand.nextInt(4))
		{
		case 0:
			moveUP();
			break;
		case 1:
			moveLEFT();
			break;
		case 2:
			moveDOWN();
			break;
		case 3:
			moveRIGHT();
			break;
		}
		
	}

	
} 



