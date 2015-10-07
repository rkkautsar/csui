class Time
{
	private int jam, menit, detik;

	public Time(){
		jam = menit = detik = 0;
	}

	public Time(int h,int m, int s){
		if(h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59)
			throw new NumberFormatException("Perhatikan range jam, menit, dan detik.");
		jam = h;
		menit = m;
		detik = s;
	}

	public void nextDetik(){
		++detik;
		if(detik == 60){
			detik = 0;
			nextMenit();
		}
	}

	public void prevDetik(){
		--detik;
		if(detik < 0){
			detik = 59;
			prevMenit();
		}
	}

	public void nextMenit(){
		++menit;
		if(menit == 60){
			menit = 0;
			jam = (jam + 1) % 24;
		}
	}

	public void prevMenit(){
		--menit;
		if(menit < 0){
			menit = 59;
			if(jam == 0) jam = 23;
			else --jam;
		}
	}

	public String toString(){
		return jam + ":" + menit + ":" + detik;
	}

	public int getJam(){
		return jam;
	}
	public int getMenit(){
		return menit;
	}
	public int getDetik(){
		return detik;
	}
	public void setJam(int h){
		if(h < 0 || h > 23)
			throw new NumberFormatException("Jam harus di antara [0,23]");
		jam = h;
	}
	public void setMenit(int m){
		if(m < 0 || m > 59)
			throw new NumberFormatException("Menit harus di antara [0,59]");
		menit = m;
	}
	public void setDetik(int s){
		if(s < 0 || s > 59)
			throw new NumberFormatException("Detik harus di antara [0,59]");
		detik = s;
	}
}
