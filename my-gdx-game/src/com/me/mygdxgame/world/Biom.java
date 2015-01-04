package com.me.mygdxgame.world;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.mygdxgame.world.objects.BiomUtils;
import com.me.mygdxgame.world.objects.Block;

public enum Biom {
	HillyTreesGreen(Background.getProPallax("backgrounds/parallax/green3.jpg")) {
		
		int rHill = 5, hillH = 5, rHillStone = 8, rHillDirt = 5, rTree = 5, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
		
        @Override
        public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
			for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
				int mapPos = level * width + j;
				map.set(mapPos, new Block(1));
				if(BiomUtils.chooseRare(rHill)) {
					GreenBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
				}
				if(BiomUtils.chooseRare(rTree)) {
					GreenBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
				} else if(BiomUtils.chooseRare(rWaterStream)) {
					GreenBiom.makeWaterStream(map, width, j,level);
				}
			}
			
			return map;
        }
    },
    
    HillyGreen(Background.getProPallax("backgrounds/parallax/green2.jpg")) {

        int rHill = 5, hillH = 5, rHillStone = 8, rHillDirt = 5, rWaterStream = 27;
        
        @Override
        public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
        	for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
				int mapPos = level * width + j;
				map.set(mapPos, new Block(1));
				if(BiomUtils.chooseRare(rHill)) {
					GreenBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
				} if(BiomUtils.chooseRare(rWaterStream)) {
					GreenBiom.makeWaterStream(map, width, j,level);
				}
			}
			
			return map;
        }
    },
    
    ForestGreen(Background.getProPallax("backgrounds/parallax/green1.jpg")) {
    	
    	int rHill = 5, hillH = 3, rHillStone = 8, rHillDirt = 5, rTree = 2, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
		
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
				int mapPos = level * width + j;
				map.set(mapPos, new Block(1));
				if(BiomUtils.chooseRare(rHill)) {
					GreenBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
				}
				if(BiomUtils.chooseRare(rTree)) {
					GreenBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
				} else if(BiomUtils.chooseRare(rWaterStream)) {
					GreenBiom.makeWaterStream(map, width, j,level);
				}
			}
			
			return map;
    	}
    },
    
    Green(Background.getProPallax("backgrounds/parallax/green.jpg")) { // 3

    	int rHill = 10, hillH = 3, rHillStone = 8, rHillDirt = 5, rTree = 10, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
		
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
				int mapPos = level * width + j;
				map.set(mapPos, new Block(1));
				if(BiomUtils.chooseRare(rHill)) {
					GreenBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
				}
				if(BiomUtils.chooseRare(rTree)) {
					GreenBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
				} else if(BiomUtils.chooseRare(rWaterStream)) {
					GreenBiom.makeWaterStream(map, width, j,level);
				}
			}
			
			return map;
    	}
    },
    
    HillyTreesSnow(Background.getProPallax("backgrounds/parallax/snow3.jpg")) {
    	
    	int rHill = 5, hillH = 8, rHillStone = 12, rHillDirt = 7, rTree = 5, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(10));
    			if(BiomUtils.chooseRare(rHill)) {
    				SnowBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			}
    			if(BiomUtils.chooseRare(rTree)) {
    				SnowBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
    			} else if(BiomUtils.chooseRare(rWaterStream)) {
    				SnowBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    HillySnow(Background.getProPallax("backgrounds/parallax/snow4.jpg")) {
    	
    	int rHill = 5, hillH = 8, rHillStone = 20, rHillDirt = 12, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(10));
    			if(BiomUtils.chooseRare(rHill)) {
    				SnowBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			} if(BiomUtils.chooseRare(rWaterStream)) {
    				SnowBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    ForestSnow(Background.getProPallax("backgrounds/parallax/snow2.jpg")) {
    	
    	int rHill = 8, hillH = 3, rHillStone = 12, rHillDirt = 5, rTree = 2, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(10));
    			if(BiomUtils.chooseRare(rHill)) {
    				SnowBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			}
    			if(BiomUtils.chooseRare(rTree)) {
    				SnowBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
    			} else if(BiomUtils.chooseRare(rWaterStream)) {
    				SnowBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    Snow(Background.getProPallax("backgrounds/parallax/snow5.jpg")) { // 7
    	
    	int rHill = 10, hillH = 3, rHillStone = 10, rHillDirt = 5, rTree = 10, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(10));
    			if(BiomUtils.chooseRare(rHill)) {
    				SnowBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			}
    			if(BiomUtils.chooseRare(rTree)) {
    				SnowBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
    			} else if(BiomUtils.chooseRare(rWaterStream)) {
    				SnowBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    HillyDesert(Background.getProPallax("backgrounds/parallax/desert3.jpg")) {
    	
    	int rHill = 5, hillH = 8, rHillStone = 20, rHillDirt = 25, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(12));
    			if(BiomUtils.chooseRare(rHill)) {
    				DesertBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			} if(BiomUtils.chooseRare(rWaterStream)) {
    				DesertBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    Desert(Background.getProPallax("backgrounds/parallax/desert.jpg")) { // 9
    	
    	int rHill = 10, hillH = 3, rHillStone = 6, rHillDirt = 12, rTree = 10, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(12));
    			if(BiomUtils.chooseRare(rHill)) {
    				DesertBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			}
    			if(BiomUtils.chooseRare(rTree)) {
    				DesertBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
    			} else if(BiomUtils.chooseRare(rWaterStream)) {
    				DesertBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    Gravel(Background.getProPallax("backgrounds/parallax/green4.jpg")) { // 10
    	
    	int rHill = 4, hillH = 3, rHillStone = 6, rHillDirt = 12, rTree = 10, treeH = 8, leefH = 3, leefMin = 2, rWaterStream = 27;
    	
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
    			int mapPos = level * width + j;
    			map.set(mapPos, new Block(1));
    			if(BiomUtils.chooseRare(rHill)) {
    				StonyBiom.makeHill(map, width, j, level, new Random().nextInt(hillH - 2) + 2, rHillStone, rHillDirt);
    			}
    			if(BiomUtils.chooseRare(rTree)) {
    				StonyBiom.makeTree(map, width, j, level, new Random().nextInt(treeH - 3) + 3, leefH, leefMin);
    			} else if(BiomUtils.chooseRare(rWaterStream)) {
    				StonyBiom.makeWaterStream(map, width, j,level);
    			}
    		}
    		
    		return map;
    	}
    },
    
    Sea(Background.getProPallax("backgrounds/parallax/green2.jpg")) { // 11
    	
    	int rHill = 1, hillH = 8;
		
    	@Override
    	public List<Block> generateBiom(List<Block> map, int width, int level, int x) {
    		for(int j = x; j < BiomGeneration.BIOM_WIDTH + x; j++) {
				int mapPos = level * width + j;
				map.set(mapPos, new Block(9));
				if(BiomUtils.chooseRare(rHill)) {
					SeaBiom.makeSea(map, width, j, level, new Random().nextInt(hillH - 4) + 4);
				}
			}
			
			return map;
    	}
    };

    private final Sprite bg;
    
    Biom(final Sprite s) {
    	this.bg = s;
    }
    
    public Sprite getBG() {
    	return bg;
    }
    
    public abstract List<Block> generateBiom(List<Block> map, int width, int level, int x);
}
