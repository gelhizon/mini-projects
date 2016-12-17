import java.util.Arrays;
public class PossibleWinningTurnProvider{
	Slot[] slots;
	AvailableSlotProvider availableSlotProvider;

	public PossibleWinningTurnProvider(Slot[] slots){
		this.slots = slots;
		availableSlotProvider = new AvailableSlotProvider(slots);
	}

	public Slot getPossibleWinningTurn(){
		Slot[] availableSlots = availableSlotProvider.getAvailableSlots();
		for(Slot slot: availableSlots){
			int i = Arrays.asList(slots).indexOf(slot);

		}
	}

	public int getPossibleWinningTurnIndex(){

	}
}