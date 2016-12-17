import javax.swing.*;
public class rb{
public static void main(String[]args){
JOptionPane.showMessageDialog(null," 1.BMW 2.RAV4 3.Honda 4.Civic Pajero 5.Other Models"); 
double num=0;

int ChooseModel=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Car Model"));
int SalesAmount=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Sales Amount."));


switch (ChooseModel){
case 1: 

num = SalesAmount * .10;
if (num>20000){
JOptionPane.showMessageDialog(null, " The reward money is" + num);
}
else 
JOptionPane.showMessageDialog(null, "The reward money is P20,000" );
break;
case 2:
num= SalesAmount * .12;
if (num<15000){
JOptionPane.showMessageDialog(null, "The reward money is" + num);
}
else
JOptionPane.showMessageDialog(null, "The reward money is P15,000");
break;
case 3:
num= SalesAmount * .30;
JOptionPane.showMessageDialog(null, "The reward money is" + num);
break;
case 4:
JOptionPane.showMessageDialog(null, "The reward money is P50,000" );
break;

case 5:
JOptionPane.showMessageDialog(null, "The reward money is P10,000");
break;

}
}
}