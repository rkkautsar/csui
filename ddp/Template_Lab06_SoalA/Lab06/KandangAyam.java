package Lab06;

import java.util.ArrayList;

/**
 * Class kandang ayam
 * @author AsdosA
 *
 */
public class KandangAyam {

	/**
	 * Instance variable dari class KandangAyam, tambahkan yang diperlukan
	 */
	private ArrayList<Ayam> ayams;
	private int size;

	/**
	 * Constructor kandang ayam
	 */ 
	
	//TODO lengkapi parameter constructor
	public KandangAyam() {
		//TODO isi constructor
		//TODO ukuran default kandang adalah 5
		ayams = new ArrayList<Ayam>();
		size = 5;
	}

	//TODO buat setter dan getter yang dibutuhkan
	public ArrayList<Ayam> getAyams(){
		return ayams;
	}

	public int getSize(){
		return size;
	}
	
	/**
	 * Method ini digunakan untuk mencari ayam tertentu didalam kandang ayam
	 * @param namaAyam nama ayam yang dicari
	 * @return ayam yang dicari
	 */
	public Ayam findAyam(String namaAyam) {
		
		//TODO cari didalam arraylist ayam yang dicari (sesuaikan return)
		for(Ayam ayam : ayams){
			if(ayam.getNama().equalsIgnoreCase(namaAyam))
				return ayam;
		}

 		return null;
	}

	/**
	 * Method ini berfungsi untuk menambahkan ayam baru kedalam kandang ayam
	 * @param namaAyam nama ayam baru yang dimasukkan ke kandang
	 */
	public void addAyam(String namaAyam) {
		//TODO hint cek dulu ukuran kandang
		//TODO masukan ayam baru kedalam kandang
		if(ayams.size() < size)
			ayams.add(new Ayam(namaAyam));
	}

	/**
	 * Method ini berfungsi untuk menghapus ayam dari kandang ayam
	 * @param namaAyam nama ayam yang ingin dihapus
	 * @return jenis ayam yang dihapus, true jika Ayam emas
	 */
	public boolean removeAyam(String namaAyam) {
		//TODO sediakan flag untuk menentukan ayam emas atau bukan
		//TODO loop seluruh kandang, cari ayam yang diinginkan
		//TODO return jenis ayam (true jika emas, false jika biasa)
		boolean isGold = false;
		Ayam ayam;

		ayam = findAyam(namaAyam);

		if(ayam != null){
			isGold = ayam.isGoldenChicken();
			ayams.remove(ayam);
		}

		return isGold;
	}

	public void upgrade(){
		size *= 2;
	}
}
