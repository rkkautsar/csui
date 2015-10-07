import java.math.BigInteger;

class Geometric
{
	int sukuAwal, rasio;

	public Geometric(int a, int r){
		if(a <= 0 || r <= 0)
			throw new NumberFormatException("Suku Awal dan Rasio harus lebih dari nol");
		sukuAwal = a;
		rasio	 = r;
	}

	// public int sukuKeN(int n){
	// 	return sukuAwal*(int) Math.pow(rasio,n-1);
	// }

	public BigInteger sukuKeN(int n){
		BigInteger bigRasio, bigSukuAwal;
		bigSukuAwal = BigInteger.valueOf(sukuAwal);
		bigRasio = BigInteger.valueOf(rasio);
		return bigSukuAwal.multiply(bigRasio.pow(n-1));
	}

	public void tampilBarisan(int n){
		if(n > 0)
			System.out.print(sukuAwal);
		for(int i = 2; i <= n; ++i)
			System.out.print(" " + sukuKeN(i));
		System.out.println();
	}

	public int getSukuAwal(){
		return sukuAwal;
	}
	public int getRasio(){
		return rasio;
	}
	public void setSukuAwal(int a){
		if(a <= 0)
			throw new NumberFormatException("Suku Awal harus lebih dari nol");
		sukuAwal = a;
	}
	public void setRasio(int r){
		if(r <= 0)
			throw new NumberFormatException("Rasio harus lebih dari nol");
		rasio = r;
	}
}
