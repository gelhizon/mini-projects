import java.util.Arrays;
public class Test{
	public static void main(String[] args){
		Slot[] slots = new Slot[9];
		for(int i = 0; i < slots.length; i++)
			slots[i] = new Slot();

		slots[0].setUnavailable();
		slots[1].setUnavailable();
		//slots[2].setUnavailable();
		slots[3].setUnavailable();
		slots[4].setUnavailable();
		//slots[5].setUnavailable();
		slots[6].setUnavailable();
		slots[7].setUnavailable();
		//slots[8].setUnavailable();
		System.out.println((4 / 3) * 3);
		AvailableSlotProvider asp = new AvailableSlotProvider(slots);
		int i = Arrays.asList(slots).indexOf(asp.getAvailableSlots().get(1));
		System.out.println(i);

		slots[5].setUnavailable();

		i = Arrays.asList(slots).indexOf(asp.getAvailableSlots().get(1));
		System.out.println(i);
	}
}