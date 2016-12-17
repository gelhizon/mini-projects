public class Decimalto {
	double number;

	public Decimalto(double number) {
		this.number = number;
	}

	public String getBinaryEquivalent() {
		long leftvalue = (long) Math.abs(this.number);
		double rightvalue = Math.abs(this.number) - leftvalue;
		String integerpart = "";
		String fractionalpart = "";

		long remainder;
		long quotient = leftvalue;

		while (quotient > 0) {
			remainder = quotient % 2;
			quotient /= 2;
			integerpart = Long.toString(remainder) + integerpart;
		}

		if (rightvalue != 0) {
			double floatingpoint = rightvalue;

			while (floatingpoint != 0) {
				long whole = (long) (floatingpoint * 2);
				floatingpoint = (floatingpoint * 2) - whole;
				fractionalpart = fractionalpart + Long.toString(whole);
			}
			return this.number < 0 ? "-" + integerpart + "." + fractionalpart
					: integerpart + "." + fractionalpart;
		}

		return this.number < 0 ? "-" + integerpart : integerpart;
	}

	public String getOctalEquivalent() {
		long leftvalue = (long) Math.abs(this.number);
		double rightvalue = Math.abs(this.number) - leftvalue;
		String integerpart = "";
		String fractionalpart = "";

		long remainder;
		long quotient = leftvalue;

		while (quotient > 0) {
			remainder = quotient % 8;
			quotient /= 8;
			integerpart = Long.toString(remainder) + integerpart;
		}

		if (rightvalue != 0) {
			double floatingpoint = rightvalue;

			while (floatingpoint != 0) {
				long whole = (long) (floatingpoint * 8);
				floatingpoint = (floatingpoint * 8) - whole;
				fractionalpart = fractionalpart + Long.toString(whole);
			}
			return this.number < 0 ? "-" + integerpart + "." + fractionalpart
					: integerpart + "." + fractionalpart;
		}

		return this.number < 0 ? "-" + integerpart : integerpart;
	}

	public String getHexEquivalent() {
		long leftvalue = (long) Math.abs(this.number);
		double rightvalue = Math.abs(this.number) - leftvalue;
		String integerpart = "";
		String fractionalpart = "";

		long remainder;
		long quotient = leftvalue;

		while (quotient > 0) {
			remainder = quotient % 16;
			quotient /= 16;
			String hex = "";
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
				hex = Long.toString(remainder);
			integerpart = hex + integerpart;
		}

		if (rightvalue != 0) {
			double floatingpoint = rightvalue;

			while (floatingpoint != 0) {
				long whole = (long) (floatingpoint * 16);
				floatingpoint = (floatingpoint * 16) - whole;
				String hex = "";
				if (whole == 10)
					hex = "A";
				else if (whole == 11)
					hex = "B";
				else if (whole == 12)
					hex = "C";
				else if (whole == 13)
					hex = "D";
				else if (whole == 14)
					hex = "E";
				else if (whole == 15)
					hex = "F";
				else
					hex = Long.toString(whole);
				fractionalpart = fractionalpart + hex;
			}
			return this.number < 0 ? "-" + integerpart + "." + fractionalpart
					: integerpart + "." + fractionalpart;
		}

		return this.number < 0 ? "-" + integerpart : integerpart;
	}

}
