
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
	public static int HIGHEST_UNLOCKED = BASE_LOW;
	public static int[][] board = new int[BOARD_WIDTH][BOARD_HEIGHT];
	public static boolean full = false;
	

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
		
		Timer t = new Timer(1000, game);
		t.start();
		
		
	}
	
	public Dimension getPreferredSize()
	{
		return new Dimension(1000, 1000);
	}

	public void drawToken(Graphics g, Color tokenColor, Color textColor, int fontSize, int x, int y, int tokenNumber, int xOffset, int yOffset)
	{
		g.setColor(tokenColor);
		g.fillOval(x * 100, y * 100, 74, 74);
		if(tokenNumber > BASE_HIGH)
		{
			g.setColor(Color.WHITE);
			g.fillOval(x * 100 + 3, y * 100 + 3, 68, 68);
		}
		g.setColor(textColor);
		g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
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
					drawToken(g, new Color(100, 115, 130), Color.WHITE, 32, j, i, board[j][i], 27, 50);
					break;
				case BASE_HIGH: //3
					drawToken(g, new Color(130, 230, 130), new Color(100, 115, 130), 32, j, i, board[j][i], 27, 50);
					break;
				case BASE: //5
					drawToken(g, new Color(130, 245, 160), Color.BLACK, 32, j, i, board[j][i], 27, 50);
					if(HIGHEST_UNLOCKED < BASE)
						HIGHEST_UNLOCKED = BASE;
					break;
				case BASE * 2: //10
					drawToken(g, new Color(255, 0, 0), Color.BLACK, 32, j, i, board[j][i], 16, 50);
					if(HIGHEST_UNLOCKED < BASE * 2)
						HIGHEST_UNLOCKED = BASE * 2;
					break;
				case BASE * 4: //20
					drawToken(g, new Color(0, 0, 255), Color.BLACK, 32, j, i, board[j][i], 17, 50);
					if(HIGHEST_UNLOCKED < BASE * 4)
						HIGHEST_UNLOCKED = BASE * 4;
					break;
				case BASE * 8: //40
					drawToken(g, new Color(255, 255, 0), Color.BLACK, 32, j, i, board[j][i], 16, 50);
					if(HIGHEST_UNLOCKED < BASE * 8)
						HIGHEST_UNLOCKED = BASE * 8;
					break;
				case BASE * 16: //80
					drawToken(g, new Color(200, 70, 240), Color.BLACK, 32, j, i, board[j][i], 16, 50);
					if(HIGHEST_UNLOCKED < BASE * 16)
						HIGHEST_UNLOCKED = BASE * 16;
					break;
				case BASE * 32: //160
					drawToken(g, new Color(50, 255, 100), Color.BLACK, 32, j, i, board[j][i], 7, 50);
					if(HIGHEST_UNLOCKED < BASE * 32)
						HIGHEST_UNLOCKED = BASE * 32;
					break;
				case BASE * 64: //320
					drawToken(g, new Color(200, 0, 0), Color.BLACK, 32, j, i, board[j][i], 7, 50);
					if(HIGHEST_UNLOCKED < BASE * 64)
						HIGHEST_UNLOCKED = BASE * 64;
					break;
				case BASE * 128: //640
					drawToken(g, new Color(200, 70, 240), Color.BLACK, 32, j, i, board[j][i], 7, 50);
					if(HIGHEST_UNLOCKED < BASE * 128)
						HIGHEST_UNLOCKED = BASE * 128;
					break;
				case BASE * 256: //1280
					drawToken(g, new Color(255, 170, 30), Color.BLACK, 24, j, i, board[j][i], 6, 50);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 256)
						HIGHEST_UNLOCKED = BASE * 256;
					break;
				case BASE * 512: //2560
					drawToken(g, new Color(30, 90, 255), Color.BLACK, 24, j, i, board[j][i], 7, 48);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 512)
						HIGHEST_UNLOCKED = BASE * 512;
					break;
				case BASE * 1024: //5120
					drawToken(g, new Color(155, 155, 155), Color.BLACK, 24, j, i, board[j][i], 4, 50);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
					if(HIGHEST_UNLOCKED < BASE * 1024)
						HIGHEST_UNLOCKED = BASE * 1024;
					break;
				case BASE * 2048: //10240
					drawToken(g, new Color(155, 155, 155), Color.BLACK, 22, j, i, board[j][i], 4, 50);
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

		repaint();
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Random rand = new Random();
		int what = rand.nextInt(4);
		switch(what)
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
		placeNewTokenAfterMove();
	}
	
	public void placeNewTokenAfterMove()
	{
		boolean newPlaced = false;
		Random rand = new Random();
		while(!newPlaced)
		{
			int i = rand.nextInt(BOARD_WIDTH);
			int j = rand.nextInt(BOARD_HEIGHT);
			if(board[i][j] == 0)
			{
				newPlaced = true;
				switch(rand.nextInt(toNumberedOrder(HIGHEST_UNLOCKED)))
				{
				case 1: //2
					board[i][j] = BASE_LOW;
					break;
				case BASE_HIGH: //3
					board[i][j] = BASE_HIGH;
					break;
				case BASE: //5
					board[i][j] = BASE;
					break;
				case BASE * 2: //10
					board[i][j] = BASE * 2;
					break;
				case BASE * 4: //20
					board[i][j] = BASE * 4;
					break;
				case BASE * 8: //40
					board[i][j] = BASE * 8;
					break;
				case BASE * 16: //80
					board[i][j] = BASE * 16;
					break;
				case BASE * 32: //160
					board[i][j] = BASE * 32;
					break;
				case BASE * 64: //320
					board[i][j] = BASE * 64;
					break;
				case BASE * 128: //640
					board[i][j] = BASE * 128;
					break;
				case BASE * 256: //1280
					board[i][j] = BASE * 256;
					break;
				case BASE * 512: //2560
					board[i][j] = BASE * 512;
					break;
				case BASE * 1024: //5120
					board[i][j] = BASE * 1024;
					break;
				case BASE * 2048: //10240
					board[i][j] = BASE * 2048;
					break;
				default:
				}
			}
		}
	}
	
	public int toNumberedOrder(int num)
	{
		switch(num)
		{
			case BASE_LOW: 		//2
				return 1;
			case BASE_HIGH: 	//3
				return 2;
			case BASE:			//5
				return 3;
			case BASE * 2: 		//10
				return 4;
			case BASE * 4: 		//20
				return 5;
			case BASE * 8: 		//40
				return 6;
			case BASE * 16: 	//80
				return 7;
			case BASE * 32: 	//160
				return 8;
			case BASE * 64: 	//320
				return 9;
			case BASE * 128:	//640
				return 10;
			case BASE * 256: 	//1280
				return 11;
			case BASE * 512: 	//2560
				return 12;
			case BASE * 1024: 	//5120
				return 13;
			case BASE * 2048: 	//10240
				return 14;
			default:
				return 0;
		}
	}
} 



