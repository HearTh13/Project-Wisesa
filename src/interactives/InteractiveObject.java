package interactives;

import entity.Entity;
import main.GamePanel;

public class InteractiveObject extends Entity{
    
    GamePanel gp;
    public boolean destructible = false;
    
    public InteractiveObject(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }
    
    public boolean isCorrectItem(Entity entity){
        boolean isCorrectItem = false;
        return isCorrectItem;
    }
    
    public void playSE(){
        
    }
    
    public InteractiveObject getDestroyedForm(){
        InteractiveObject object = null;
        return object;
    }
    
    public void update(){
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    
}
