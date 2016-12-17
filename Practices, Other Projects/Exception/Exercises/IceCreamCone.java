import javax.swing.JOptionPane;
public class IceCreamCone{
	String flavor;
	int scoops;

	public IceCreamCone()throws IceCreamConeException{
		setFlavor();
		setScoops();
	}
	public void setFlavor(){
		flavor = JOptionPane.showInputDialog(null, "Flavor: ");
	}
	public void setScoops()throws IceCreamConeException{
		String inputString = JOptionPane.showInputDialog(null, "Scoops: ");
		int scoops = Integer.parseInt(inputString);
		if(scoops > 3)
			throw(new IceCreamConeException("Invalid: Scoops: " + scoops));
		this.scoops = scoops;
	}
}