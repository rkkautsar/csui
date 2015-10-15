import java.util.Scanner;

/**
*	Kelas yang bertujuan untuk memvalidasi input dari user
*	dan melaporkan exception jika terjadi kesalahan
*	@author Rakha Kanz Kautsar
*	@version 15.10.2015
*/
class Validator
{
	/**
	*	Scanner untuk mendapatkan input dari user
	*/
	private Scanner sc;

	/**
	*	String untuk menjadi container sementara dari input
	*/
	String input;

	/**
	*	Konstruktor default yang menjadikan scanner masukkan menjadi
	* 	scanner pada kelas ini
	*	@param sc Scanner untuk mendapatkan input
	*/
	public Validator(Scanner sc){
		this.sc = sc;
	}

	/**
	*	Method untuk memvalidasi masukkan berupa integer
	*	yang diharuskan memiliki nilai di antara low dan high
	*	@param low nilai batasan bawah masukkan
	*	@param high nilai batasan atas masukkan
	*	@return nilai integer yang telah melewati validasi
	*/
	public int getInt(int low, int high) throws Exception{
		input = sc.nextLine();
		int x = Integer.parseInt(input);

		if(x < low || x > high)
			throw new Exception("Input is not in the allowed range.");

		return x;
	}

	/**
	*	Method untuk memvalidasi masukkan bertipe data double
	*	yang diharuskan memiliki nilai di antara low dan high
	*	@param low nilai batasan bawah masukkan
	*	@param high nilai batasan atas masukkan
	*	@return nilai double yang telah melewati validasi
	*/
	public double getDouble(double low, double high) throws Exception{
		input = sc.nextLine();
		double x = Double.parseDouble(input);

		if(x + Game.EPS < low || x - Game.EPS > high)
			throw new Exception("Input is not in the allowed range.");

		return x;
	}

	/** 
	*	Method untuk memvalidasi masukkan berupa pilihan beberapa string
	*	yang diharuskan memiliki nilai di antara constraints
	*	@param constraints varargs masukkan yang diperbolehkan
	*	@return string yang telah melewati validasi
	*/
	public String getString(String... constraints) throws Exception{
		input = sc.nextLine();

		for(String constraint : constraints){
			if(input.equalsIgnoreCase(constraint))
				return constraint;
		}

		throw new Exception("Input is not understood.");
	}

}
