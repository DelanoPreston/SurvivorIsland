package Maps;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JComponent;

import Main.ContentBank;

@SuppressWarnings("serial")
public class Map extends JComponent{
	MapTile[][] map;
	Random random;
	
	public Map(char[][] mapKey){
		map = createMap(mapKey);
	}
	
	public void update(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
//				map[i][j].
				drawTile(g, map[i][j].getType(), i * 64, j * 64);
			}
		}
	}
	
	private void drawTile(Graphics g, TileType tileType, int xPos, int yPos){
		Image temp = null;
		switch(tileType){
		case SEA:
			temp = ContentBank.sea;
			break;
		case BEACH:
			temp = ContentBank.beach;
			break;
		case FOREST:
			temp = ContentBank.forest;
			break;
		case JUNGLE:
			temp = ContentBank.jungle;
			break;
		default:
			temp = ContentBank.sea;
			break;
		}
		g.drawImage(temp, xPos, yPos, null);
	}
	
	private MapTile[][] createMap(char[][] mapKey){
		MapTile[][] temp = new MapTile[mapKey.length][mapKey[0].length];
		
		for(int i = 0; i < mapKey.length; i++){
			for(int j = 0; j < mapKey[i].length; j++){
				temp[i][j] = new MapTile(mapKey[i][j]);
			}
		}
		return temp;
	}
}
