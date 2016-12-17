import javax.swing.JOptionPane;
public class ThrowIceCream2{
	public static void main(String[]args){
		IceCreamCone2 ice1, ice2, ice3;

		try{
			ice1 = new IceCreamCone2();
			JOptionPane.showMessageDialog(null, "Successfully Created.");
		}catch(IceCreamConeException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try{
			ice2 = new IceCreamCone2();
			JOptionPane.showMessageDialog(null, "Successfully Created.");
		}catch(IceCreamConeException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try{
			ice3 = new IceCreamCone2();
			JOptionPane.showMessageDialog(null, "Successfully Created.");
		}catch(IceCreamConeException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}