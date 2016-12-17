public class Slot {
 
    private boolean isAvailable;

    public Slot(){
        isAvailable = true;
    }

    public void setUnavailable(){
    	isAvailable = false;
    }
 
    public boolean isSlotAvailable (){
        return isAvailable;
    }    
 
}