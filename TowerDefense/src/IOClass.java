import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**IOClass manages reading and writing txt files for saving and loading
 * 
 * @author Preston Delano
 *
 */
public class IOClass{
	String filePath = "";
	
	public IOClass(){
		GetMaps();
	}
	public IOClass(String dataSet, String inFilePath){
		filePath = inFilePath;
		
		if(dataSet.equalsIgnoreCase("tower")){
			//run tower reader
		}else if(dataSet.equalsIgnoreCase("creep")){
			//run creep reader
		}else if(dataSet.equalsIgnoreCase("map")){
			//run save loading
		}else if(dataSet.equalsIgnoreCase("load")){
			//run save loading
		}
	}
	
	/**public method for saving a Level object
	 * 
	 * @param gameData - the level object to be saved
	 */
	public void Save(Level gameData){
		if(WriteSave(gameData)){
			//say woo hoo
		}
		else{
			//cry about it
		}
	}
	/**public method for loading a Level object
	 * 
	 * @return - a level object
	 */
	public Level Load(){
		//this might have to return Level
		ReadSave();
		return null;
	}
	/**public method for reading the entire map file
	 * 
	 * @return - returns a List<Map> of the maps in the file
	 */
	public List<Map> GetMaps(){
		return ReadMaps();
	}
	/**public method for reading the entire map file
	 * 
	 * @param level - the specified level to be returned
	 * @return - returns a Map object at 'level' location in the map file
	 */
	public Map GetMapAt(int level){
		return ReadMaps().get(level - 1);
	}
	
	/**private method to be called by Load()
	 * 
	 * @return - returns true or false based on success of loading
	 */
	private boolean ReadSave(){
		//this will be fun
		return false;
	}
	/**private method to be called by Save(Level gameData)
	 * 
	 * @param gameData - the Level to be saved
	 * @return - returns true or false based on success of saving
	 */
	private boolean WriteSave(Level gameData){
		//save player profile
		//save map
		//save towers
		//save save#
		//save creeps
		
		return false;
	}
	/**private method to be called by ReadSave() - possibly
	 * 
	 * @return - returns a List<Tower> of tower types (probably redo to read existing towers)
	 */
	private List<Tower> ReadTowers(){
		String[] towerFileInfo = ReadFile("TowerInfo.txt");
		
		for(int i = 0; i < towerFileInfo.length; i++){
			String[] towerInfo = towerFileInfo[i].split("|");
			for(int j = 0; j < towerInfo.length; j++){
				String[] towerTrait = towerInfo[j].split("=");

				if(towerTrait[0].equalsIgnoreCase("name")){
					
				}else if(towerTrait[0].equalsIgnoreCase("cost")){
					
				}else if(towerTrait[0].equalsIgnoreCase("range")){
					
				}else if(towerTrait[0].equalsIgnoreCase("damage")){
					
				}else if(towerTrait[0].equalsIgnoreCase("firespeed")){
					
				}
			}
		}
		return null;
	}
	/**private method to be called by ReadSave() - possibly
	 * 
	 * @return - returns a List<Creep> of existing creeps - possibly
	 */
	private List<Creep> ReadCreeps(){
		try{
			String[] creepInfo = OpenFile("CreepInfo.txt");
		}catch(IOException e){
			//file not read in correctly
		}
		return null;
	}
	/**private method to be called by GetMaps()
	 * 
	 * @return - returns a List<Map> of the maps from the file
	 */
	private List<Map> ReadMaps(){
		String[] mapFileData = null;
		try{
			mapFileData = OpenFile("MapInfo.txt");
		}catch(IOException e){
			//make a log - file did not load correctly
		}
		List<Map> tempLevels = new ArrayList<Map>();
		Map tempMap = null;
		
		for(int mapFileLine = 0; mapFileLine < mapFileData.length;){// mapFileLine++
			int height = 0;
			int width = 0;
			//String[][] mapKeyInfo = null;
			HashMap<Character, String> mapKeyInfo = new HashMap<Character, String>();
			String mapName = "";
			char[][] mapKey = null;
			
			//read map data
			String[] levelInfo = mapFileData[mapFileLine].split("\\|");// '\\' this declassify the '|' as a special character
			mapFileLine++;
			for(int j = 0; j < levelInfo.length; j++){
				String[] levelData = levelInfo[j].split("=");
				if(levelData[0].equalsIgnoreCase("levelname")){
					mapName = levelData[1];
				}else if(levelData[0].equalsIgnoreCase("height")){
					height = Integer.parseInt(levelData[1]);
				}else if(levelData[0].equalsIgnoreCase("width")){
					width = Integer.parseInt(levelData[1]);
				}else if(levelData[0].equalsIgnoreCase("characters")){
					//mapKeyInfo = new String[Integer.parseInt(levelData[1])][2];
					String[] characterInfo = levelData[2].split(":");
					for(int k = 0; k < characterInfo.length; k++){
						String[] charIdentity = characterInfo[k].split("-");
						mapKeyInfo.put(charIdentity[0].toCharArray()[0], charIdentity[1]);
					}
				}
			}
			
			
			List<Point2D> tempList = new ArrayList<Point2D>();
			String[] pathInfo = mapFileData[mapFileLine].split("\\|");
			mapFileLine++;
			for(int i = 0; i < pathInfo.length; i++){
				String[] coords = pathInfo[i].split(",");
				int x = Integer.parseInt(coords[0]);
				int y = Integer.parseInt(coords[1]);
				tempList.add(new Point2D.Double(x, y));
			}
			
			//read map character
			mapKey = new char[height][width];
			for(int j = 0; j < height; j++, mapFileLine++){
				char[] charLine = mapFileData[mapFileLine].toCharArray();
				mapKey[j] = charLine;
			}
			tempMap = new Map(mapName, mapKey, mapKeyInfo, tempList);
			tempLevels.add(tempMap);
		}
		
		return tempLevels;
	}
	/**private method called by several private methods
	 * 
	 * @param fileName - name of the text file in the GameInfoTxtFiles folder
	 * @return - returns a String[] of the lines of the file
	 */
	private String[] ReadFile(String fileName){
		String[] temp = null;
		try{
			temp = OpenFile(fileName);
		}catch(IOException e){
			System.out.println("file did not get read in correctly");
		}
		return temp;
	}
	/**private method called by ReadFile(String)
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private String[] OpenFile(String fileName) throws IOException{
		String path = System.getProperty("user.dir") + "/GameInfoTxtFiles/" + fileName;
		String[] fileStrings = null;
		
//		try
//		{
		Scanner tempInput = new Scanner(new File(path));
		String stringInput = tempInput.useDelimiter("\\Z").next();
		fileStrings = stringInput.split("\r\n");
//		}
//		catch(IOException e){
//			//file not read correctly
//		}
		
		return fileStrings;
	}
}
