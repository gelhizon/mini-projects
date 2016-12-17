import java.util.ArrayList;

public class AvailableSlotProvider{
    private Slot[] slots;

    public AvailableSlotProvider(Slot[] slots){
        this.slots = slots;
    }

    public ArrayList<Slot> getAvailableSlots(){
        ArrayList<Slot> list = new ArrayList<>();

        for(Slot slot: slots)
            if(slot.isSlotAvailable())
                list.add(slot);
            
        return list;
    }

}