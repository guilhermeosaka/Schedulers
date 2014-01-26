package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import model.Job;
import model.Watch;
import controller.Processor;

public class View extends JComponent {
	public boolean finished;
	
	int blockWidth = 50;
	int[] blockSize = new int[20];
	Color[][] blockColor = new Color[6][20];
	private int actual;
	private Job[] jobs = new Job[6];
	Color[] actualColor = new Color[20];
	
	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D)g;
		
		graphics.setColor(new Color(0xFFFFFF));
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		//Itera sobre cada processador
		for (int i = 0; i < 6; i++) {
			//Itera sobre cada job em execução no intervalo de 1 segundo
			for (int j = 0; j < 20; j++) {
				graphics.setColor(blockColor[i][j]);
				graphics.fillRect(getBlockX(j), getBlockY(i), blockSize[j], getBlockHeight());
				graphics.setColor(Color.WHITE);
				graphics.drawLine(getBlockX(j), getBlockY(i), getBlockX(j), getBlockY(i) + 20);
			}
		}

		graphics.setFont(new Font("Monospaced", Font.BOLD, 30));
		graphics.drawString("Teste", 50, 50);
	}
	
	private int getBlockWidth() {
		return 50;
	}
	
	private int getBlockHeight() {
		return (int)(getWidth() * 0.03);
	}
	
	private int getBlockX(int j) {
		return (int)((getWidth() - 1000) / 2 + getBlockWidth() * j);
	}
	
	private int getBlockY(int i) {
		return (int)(getHeight() * 0.1 * (1 + i * 1.5));
	}
	
	public void update(List<Processor> processors) {
		//Itera na lista de processadores
		if (blockSize[actual] < 50) {
			for (int i = 0; i < 6; i++) {
				Job job = processors.get(i).getJob();
				
				if (blockColor[i][actual] == null && job != null) {
					switch (job.getId()) {
					case 1:
						 blockColor[i][actual] = new Color(0x333745);
						break;
					case 2:
						blockColor[i][actual] = new Color(0x77C4D3);
						break;
					case 3:
						blockColor[i][actual] = new Color(0xDAEDE2);
						break;
					case 4:
						blockColor[i][actual] = new Color(0xEA2E49);
						break;		
					case 5:
						blockColor[i][actual] = new Color(0xF6F792);
					}
				}
			}
			blockSize[actual]++;
		} else {
			actual++;
		}
		repaint();
	}
	
	public void updateSingle(Job job) {
		if (actual > 20) {
			finished = true;
		} else {
			finished = false;
		}
		
		if (actual == 0) {
			switch (job.getId()) {
			case 1:
				actualColor[actual] = new Color(0x333745);
				break;
			case 2:
				actualColor[actual] = new Color(0x77C4D3);
				break;
			case 3:
				actualColor[actual] = new Color(0xDAEDE2);
				break;
			case 4:
				actualColor[actual] = new Color(0xF6F792);
				break;		
			case 5:
				actualColor[actual] = new Color(0xEA2E49);
			}
		}
		
		if (blockSize[actual] > 50) {
			actual++;
			if (job != null) {
				System.out.println(Watch.getReal() + " - Girando " + job.getId());
				switch (job.getId()) {
				case 1:
					actualColor[actual] = new Color(0x333745);
					break;
				case 2:
					actualColor[actual] = new Color(0x77C4D3);
					break;
				case 3:
					actualColor[actual] = new Color(0xDAEDE2);
					break;
				case 4:
					actualColor[actual] = new Color(0xF6F792);
					break;		
				case 5:
					actualColor[actual] = new Color(0xEA2E49);
				}
			}
		} else {
			blockSize[actual]++;
		}
		
		
		if (!finished) repaint();
	}
	
	public boolean isFinished() {
		return actual >= 20;
	}
}
