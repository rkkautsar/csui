package Lab06;

/**
 * Class peternak
 * @author AsdosA
 *
 */
public class Peternak {

	/**
	 * Instance variable dari class Peternak, tambahkan yang diperlukan
	 */

	private String nama;
	private int uang;
	private KandangAyam kandang;

	//TODO buat instance variable yang diperlukan
	public static final int HARGA_BELI_AYAM = 1500;
	public static final int HARGA_JUAL_AYAM_BIASA = 1500;
	public static final int HARGA_JUAL_AYAM_EMAS = 3000;
	public static final int HARGA_TELUR = 500;
	public static final int HARGA_TELUR_MAS = 1000;

	/**
	 * Constructor peternak
	 * @param nama nama peternak
	 * @param duit jumlah uang yang dimiliki peternak
	 */
	
	//TODO lengkapi parameter constructor
	public Peternak(String nama, int uang) {
		//TODO isi constructor
		this.nama = nama;
		this.uang = uang;
		kandang = new KandangAyam();
	}
	
	/**
	 * Setter dan Getter
	 * @return
	 */
	public int getDuit(){
		return uang;
	}

	public String getNama(){
		return nama;
	}

	public KandangAyam getKandang(){
		return kandang;
	}
	
	//TODO buat setter dan getter yang diperlukan
	
	
	/**
	 * Method ini berfungsi untuk membeli ayam baru, 
	 * jika kekurangan uang, akan mengembalikan nilai -1
	 * jika kandang penuh akan mengembalikan 0
	 * 
	 * @param namaAyam nama ayam yang baru dibeli
	 * @return uang sekarang
	 */
	public int buyAyam(String namaAyam) {
		if(getDuit() < HARGA_BELI_AYAM)
			return -1;
		if(kandang.getAyams().size() >= kandang.getSize())
			return -2;
		
		//TODO update jumlah uang sekarang dan masukan ayam ke kandang
		//TODO gunakan method yang ada di KandangAyam
		uang -= HARGA_BELI_AYAM;
		kandang.addAyam(namaAyam);

		return 0;
	}

	/**
	 * Method ini berfungsi untuk menjual ayam, harga tergantung jenis ayam
	 * Jika tidak ada ayam yang tersedia, return -1
	 * @param namaAyam nama ayam yang ingin dijual.
	 * @return uang sekarang
	 */
	public int sellAyam(String namaAyam) {
		//TODO cek jenis ayam yang akan dijual, harga tergantung jenis
		//TODO hint gunakan method findAyam dari class KandangAyam
		Ayam ayam = kandang.findAyam(namaAyam);

		if(ayam.isGoldenChicken())
			uang += HARGA_JUAL_AYAM_EMAS;
		else
			uang += HARGA_JUAL_AYAM_BIASA;

		kandang.removeAyam(namaAyam);
		
		return 0;
	}

	/**
	 * Method ini membuat peternak mengangkat ayam
	 * @param namaAyam nama ayam yang diangkat
	 */
	public void pickUpAyam(String namaAyam) {
		//TODO gunakan method di kandangAyam
		//TODO hint gunakan method findAyam dari class KandangAyam
		kandang.findAyam(namaAyam).pickUp();
	}
	
	/**
	 * Method ini membuat peternak menendang ayam
	 * @param namaAyam nama ayam yang ditendang
	 */
	public void tendangAyam(String namaAyam)	{
		//TODO gunakan method di KandangAyam
		//TODO hint gunakan method findAyam dari class KandangAyam
		kandang.findAyam(namaAyam).tendang();
	}
	
	/**
	 * Method ini berfungsi untuk mengupgrade kandang ayam, menambahkan ukuran kandang 2x lipat ukuran sebelumnya
	 */
	public void upgradeKandang() {
		//TODO set ukuran kandang menjadi 2x sebelumnya
		kandang.upgrade();
	}
	
	/**
	 * Method ini berfungsi untuk mejual telur, ada 2 jenis telur dengan harga yang berbeda
	 */
	public void jualTelur() {
		//TODO jumlahkan semua harga telur, jangan lupa cek apakah terlurnya emas atau bukan
		//TODO update jumlah uang
		int hasilJualTelur = 0;
		for(Ayam ayam : kandang.getAyams()){
			if(ayam.isGoldenChicken())
				hasilJualTelur += HARGA_TELUR_MAS;
			else
				hasilJualTelur += HARGA_TELUR;
		}

		uang += hasilJualTelur;
	}
}
