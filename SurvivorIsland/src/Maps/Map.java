package Maps;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Map extends JComponent{
	MapTile[][] map;
	Random random;
	
	public Map(char[][] mapKey){
		map = createMap(mapKey);
	}
	
	@Override
	public void paintComponent(Graphics g){
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
//				map[i][j].
			}
		}
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
