class PolarVector
{
	private double angle, magnitude;

	public PolarVector(){
		angle = 0;
		magnitude = 0;
	}

	public PolarVector(double angle,double magnitude){
		this.angle = angle;
		this.magnitude = magnitude;
	}

	// accessor
	double getAngle(){
		return angle;
	}
	double getMagnitude(){
		return magnitude;
	}
}
