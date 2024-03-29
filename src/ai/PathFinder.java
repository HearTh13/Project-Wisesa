package ai;

import java.util.ArrayList;
import main.GamePanel;

public class PathFinder {
    
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList();
    public ArrayList<Node> pathList = new ArrayList();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;
    
    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNode();
    }
    
    public void instantiateNode(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {            
            node[col][row] = new Node(col, row);
            
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
    
    public void resetNodes(){
        
        int col = 0;
        int row = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {            
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow){
        resetNodes();
        
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);
        
        int col = 0;
        int row = 0;
        
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {            
            int tileNum = gp.tile.mapTileNum[gp.currentMap][col][row];
            if (gp.tile.tile[tileNum].collision) {
                node[col][row].solid = true;
            }
            
            for (int i = 0; i < gp.iObject[1].length; i++) {
                if(gp.iObject[gp.currentMap][i] != null 
                        && gp.iObject[gp.currentMap][i].destructible){
                    int iCol = gp.iObject[gp.currentMap][i].worldX/gp.tileSize;
                    int iRow = gp.iObject[gp.currentMap][i].worldY/gp.tileSize;
                    node[iCol][iRow].solid = true;
                }
            }
            
            getCost(node[col][row]);
            
            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
    
    public void getCost(Node node){
        int xDistance = Math.abs(node.col - startNode.col);
        int YDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + YDistance;
        
        xDistance = Math.abs(node.col - goalNode.col);
        YDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + YDistance;
        
        node.fCost = node.gCost + node.hCost;
    }
    
    public boolean search(){
        while (!goalReached && step < 500) {            
            int col = currentNode.col;
            int row = currentNode.row;
            
            currentNode.checked = true;
            openList.remove(currentNode);
            
            if (row - 1 >= 0) {
                openNode(node[col][row-1]);
            }
            if (col - 1 >= 0) {
                openNode(node[col-1][row]);
            }
            if (row + 1 < gp.maxWorldRow) {
                openNode(node[col][row+1]);
            }
            if (col + 1 < gp.maxWorldCol) {
                openNode(node[col+1][row]);
            }
            
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            
            for (int i = 0; i < openList.size(); i++) {
                if (openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                
                else if (openList.get(i).fCost == bestNodefCost) {
                    if (openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            
            if (openList.size() == 0) {
                break;
            }

            currentNode = openList.get(bestNodeIndex);

            if (currentNode == goalNode) {
                goalReached = true;
                trace();
            }
            step++;
            System.out.println(step);
        }
        return goalReached;
    }
    
    public void openNode(Node node){
        if (!node.open && !node.checked && !node.solid) {
            node.open = true;
            node.parent = currentNode;
            openList.add(node);
        }
    }
    
    public void trace(){
        Node current = goalNode;
        
        while (current != startNode) {            
            pathList.add(0, current);
            current = current.parent;
        }
    }
}
