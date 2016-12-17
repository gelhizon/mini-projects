import javax.swing.JOptionPane;
public class IceCreamCone2{
	String flavor;
	int scoops;

	public IceCreamCone2()throws IceCreamConeException{
		setFlavor();
		setScoops();
	}
	public void setFlavor()throws IceCreamConeException{
		String flavor = JOptionPane.showInputDialog(null, "Flavor: \nVanilla\nChoco\nMocha\nUbe");
		if(flavor.equalsIgnoreCase("Vanilla") || flavor.equalsIgnoreCase("Choco") || flavor.equalsIgnoreCase("Mocha") || flavor.equalsIgnoreCase("Ube"))
			this.flavor = flavor;
		else
			throw(new IceCreamConeException("Invalid: Flavor: " + flavor));
	}
	public void setScoops()throws IceCreamConeException{
		String inputString = JOptionPane.showInputDialog(null, "Scoops: ");
		int scoops = Integer.parseInt(inputString);
		if(scoops > 3)
			throw(new IceCreamConeException("Invalid: Scoops: " + scoops));
		this.scoops = scoops;
	}
}