import javax.swing.JOptionPane;

/**
*	Kelas yang berisi method-method static yang berkaitan dengan
*	pesan-pesan dalam interface
*	@author Rakha Kanz Kautsar
*	@version 15.10.2015
*/
class Interface {

	/**
	*	Method yang melaporkan error berupa pesan exception
	*	kepada user
	*	@param e Exception yang akan dilaporkan
	*/
	public static void error(Exception e){
		System.err.println();
		System.err.println("========================================");
		System.err.println("  ___ _ __ _ __ ___  _ __| |");
		System.err.println(" / _ \\ '__| '__/ _ \\| '__| |");
		System.err.println("|  __/ |  | | | (_) | |  |_|");
		System.err.println(" \\___|_|  |_|  \\___/|_|  (_)");
		System.err.println("========================================");
		System.err.println(e.getMessage());
		System.err.println("========================================");
		System.err.println();

		delay(1000);
	}

	/**
	*	Method yang akan mencetak pesan "Game Over!" kepada user
	*	ketika game telah berakhir
	*/
	public static void gameOver(){
		System.out.println();
	  	System.out.println(" / ___| __ _ _ __ ___   ___   / _ \\__   _____ _ __| |");
		System.out.println("| |  _ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__| |");
		System.out.println("| |_| | (_| | | | | | |  __/ | |_| |\\ V /  __/ |  |_|");
		System.out.println(" \\____|\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|  (_)");
		System.out.println();

		JOptionPane.showMessageDialog(null, "Game Over!");
	}

	/**
	*	Method yang akan mencetak heading berupa ASCII art
	*	kepada user pada awal program
	*/
	public static void welcome(){
		System.out.println("		                                             l    ");
		System.out.println("           .oOKKOc                           Kc     .c    ");
		System.out.println("          ,WMMMMWMN.                         .x.  ,lK.    ");
		System.out.println("          dMMMN. .,..                  .;cdOk  x;,'k.     ");
		System.out.println("          dMMMMxcdx;            .;lx0NMMMMMMMx   ,O.      ");
		System.out.println("          dMMMMMMMMl    .':ok0NMMMMMMMMMMMMMMM:  .;oxddl,.");
		System.out.println("          dMMWKOxxxookKWMMMMMMMMMMMMMMMMMMMMMMW'   :;     ");
		System.out.println("          :oddOXWMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMX.    .,.  ");
		System.out.println("    'lx:.NMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMNc   0:cdd'");
		System.out.println("  oNMMMW',MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMW0o;.   ;'o.    ");
		System.out.println(".XMMMMMMN.lMMMMMMMMMMMMMMMMMMMMMMMMMMW0d:.         .ko    ");
		System.out.println("KMMMMMMMMK kMMMMMMMMMMMMMMMMMMMMMKx:.                .    ");
		System.out.println("MMMMMMMMMMx KMMMMMMMKoccdKMMXkc'                          ");
		System.out.println("0MMMMMMMMMMc.WMMMMN,      ,N0                             ");
		System.out.println(".KMMMMMMMMMW',WMMMd        oM'                            ");
		System.out.println("  cKMMMMMMMMN.:o:XN.      .XK                             ");
		System.out.println("    .:oxkkxo;    .xWkc;;cOWx.                             ");
		System.out.println("                   .dNMMNd.                               ");
		System.out.println("                                         Cannon War v.0.2 ");
		System.out.println();
		delay(1000);
	}

	/**
	*	Method yang akan digunakan untuk melakukan delay
	*	@param ms Lama delay dalam microsecond
	*/
	public static void delay(int ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e){
			// do nothing
		}
	}
}
