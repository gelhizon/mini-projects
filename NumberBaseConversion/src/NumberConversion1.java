import java.util.Scanner;

public class NumberConversion1 {
	private int decimal;
	private String binary;

	public void setNum(int decimal) {
		this.decimal = decimal;
	}

	public void setNum(String binary) {
		this.binary = binary;
		this.decimal = this.getDecimalEquivalent();
		System.out.println("Decimal is " + this.getDecimalEquivalent());
	}

	public String getBinaryEquivalent() {
		String binary = "";
		int remainder;
		int quotient = decimal;

		while (quotient > 0) {
			remainder = quotient % 2;
			quotient /= 2;
			binary = Integer.toString(remainder) + binary;
		}

		return binary;
	}

	public String getOctalEquivalent() {
		String octal = "";
		int remainder;
		int quotient = decimal;

		while (quotient > 0) {
			remainder = quotient % 8;
			quotient /= 8;
			octal = Integer.toString(remainder) + octal;
		}

		return octal;
	}

	public String getHexEquivalent() {
		String convertedhex = "";
		String hex = "";
		int remainder;
		int quotient = decimal;

		while (quotient > 0) {
			remainder = quotient % 16;
			quotient /= 16;
			if (remainder == 10)
				hex = "A";
			else if (remainder == 11)
				hex = "B";
			else if (remainder == 12)
				hex = "C";
			else if (remainder == 13)
				hex = "D";
			else if (remainder == 14)
				hex = "E";
			else if (remainder == 15)
				hex = "F";
			else
				hex = Integer.toString(remainder);
			convertedhex = hex + convertedhex;
		}

		return convertedhex;
	}

	public int getDecimalEquivalent() {
		int decimal = 0;
		for (int i = 0; i < binary.length(); i++) {
			if (binary.charAt(i) == '1') {
				decimal = (int) (decimal + (Math
						.pow(2, binary.length() - i - 1)));
			}
		}

		return decimal;
	}

	public static void main(String[] args) {

		NumberConversion1 myClass = new NumberConversion1();
		int decimal = 0;
		String binary = "";
		char c;

		Scanner input = new Scanner(System.in);
		System.out.println("What type of number you want to enter:");
		System.out.println("(Note: Cannot convert float or double values)");
		System.out.println("a.) DECIMAL.");
		System.out.println("b.) BINARY.");
		System.out.print(">>>>>");
		c = (input.nextLine()).charAt(0);

		switch (c) {
		case 'a':
			System.out.println("Please enter your DECIMAL number.");
			System.out.print(">>>>>");
			decimal = input.nextInt();
			myClass.setNum(decimal);
			System.out.println("Choose: ");
			System.out.println("a.) Decimal to Binary.");
			System.out.println("b.) Decimal to Octal.");
			System.out.println("c.) Decimal to Hexadecimal.");
			System.out.print(">>>>>");
			// consume line
			input.nextLine();
			c = (input.nextLine()).charAt(0);

			switch (c) {
			case 'a':
				System.out.println("Binary Equivalent is "
						+ myClass.getBinaryEquivalent());
				break;
			case 'b':
				System.out.println("Octal Equivalent is "
						+ myClass.getOctalEquivalent());
				break;
			case 'c':
				System.out.println("Hex Equivalent is "
						+ myClass.getHexEquivalent());
			}
			break;
		case 'b':
			System.out.println("Please enter your BINARY number.");
			System.out.print(">>>>>");
			binary = input.nextLine();
			myClass.setNum(binary);
			System.out.println("Choose: ");
			System.out.println("a.) Binary to Decimal.");
			System.out.println("b.) Binary to Octal.");
			System.out.println("b.) Binary to Hex.");
			System.out.print(">>>>>");
			c = (input.nextLine()).charAt(0);

			switch (c) {
			case 'a':
				System.out.println("Decimal Equivalent is "
						+ myClass.getDecimalEquivalent());
				break;
			case 'b':
				System.out.println("Octal Equivalent is "
						+ myClass.getOctalEquivalent());
				break;
			case 'c':
				System.out.println("Hexadecimal Equivalent is "
						+ myClass.getHexEquivalent());
			}
		}
	}
}