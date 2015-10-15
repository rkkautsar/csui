import java.util.Scanner;

class Validator
{
	private Scanner sc;
	String input;

	public Validator(Scanner sc){
		this.sc = sc;
	}

	public int getInt(int low, int high) throws Exception{
		input = sc.nextLine();
		int x = Integer.parseInt(input);

		if(x < low || x > high)
			throw new Exception("Input is not in the allowed range.");

		return x;
	}

	public double getDouble(double low, double high) throws Exception{
		input = sc.nextLine();
		double x = Double.parseDouble(input);

		if(x + Game.EPS < low || x - Game.EPS > high)
			throw new Exception("Input is not in the allowed range.");

		return x;
	}

	public String getString(String... constraints) throws Exception{
		input = sc.nextLine();

		for(String constraint : constraints){
			if(input.equalsIgnoreCase(constraint))
				return constraint;
		}

		throw new Exception("Input is not in the allowed range.");
	}

}
