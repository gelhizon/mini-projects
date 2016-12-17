public class Board {
 
    private Slot[] slots;
 
    public Board(){
    	slots = new Slots[9];

    	for(int i = 0; i < 9; i++){
    		slots.setPosition(i);
    	}
    }
 
    public Slot[] getSlots(){
        return slots;
    }
}