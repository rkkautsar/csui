package Lab06;

/**
 * Class Ayam
 * @author AsdosA
 *
 */
public class Ayam {
	
	/**
	 * Instance variable dari class Ayam, tambahkan yang diperlukan
	 */
	private String nama;
	private int affection;
	private boolean gold;
	
	//TODO buat instance variable yang diperlukan
	public static final int GOLDEN_CHICKEN_THRESHOLD = 3;

	/**
	 * Constructor Class Ayam
	 */
	
	
	//TODO lengkapi parameter constructor
	public Ayam(String nama) {
		//TODO isi constructor
		this.nama = nama;
		affection = 1;
		gold = false;
	}

	/**
	 * Setter dan Getter
	 * 
	 * 
	 */

	public String getNama(){
		return nama;
	}

	public boolean isGoldenChicken(){
		return gold;
	}

	public int getAffection(){
		return affection;
	}
	
	//TODO buat setter dan getter yang dibutuhkan

	/**
	 * Method PickUp befungsi untuk menambah Affection point dari ayam
	 * @return jumlah affection point
	 */
	public void pickUp()	{
		//TODO set affection point ke nilai yang baru
		//TODO cek apakah sudah waktunya evolusi
		++affection;
		if(affection >= GOLDEN_CHICKEN_THRESHOLD)
			evolve();
	}
	
	/**
	 * Method kick berfungsi untuk mengurangi Affection point dari ayam 
	 * @return
	 */
	public void tendang()	{
		//TODO set affection point ke nilai yang baru
		//TODO cek apakah sudah waktunya devolve
		--affection;
		if(affection < GOLDEN_CHICKEN_THRESHOLD)
			devolve();
	}
	
	/**
	 * Jika Affection point mencukupi, ayam akan berubah menjadi Ayam Emas
	 */
	public void evolve()	{
		//TODO ganti signal ayam emas ke true
		gold = true;
	}
	
	/**
	 * Jika point berkurang dari batas tertentu, ayam akan kembali menjadi ayam biasa
	 */
	public void devolve()	{
		//TODO ganti signal ayam emas ke false
		gold = false;
	}
}
