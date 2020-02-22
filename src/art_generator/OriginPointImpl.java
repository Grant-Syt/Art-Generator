package art_generator;

public class OriginPointImpl {
	private int x;
	private int y;
	private int strength;
	private int colorR;
	private int colorG;
	private int colorB;
	private float colorAlpha;
	
	public OriginPointImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public OriginPointImpl(int x, int y, int strength) {
		this.x = x;
		this.y = y;
		this.strength = strength;
	}
	
	public OriginPointImpl(int x, int y, int r, int g, int b, int alpha, int strength) {
		this.x = x;
		this.y = y;
		this.colorR = r;
		this.colorG = g;
		this.colorB = b;
		this.colorAlpha = alpha;
		this.strength = strength;
	}
	
	public OriginPointImpl(int x, int y, int r, int g, int b, int alpha) {
		this.x = x;
		this.y = y;
		this.colorR = r;
		this.colorG = g;
		this.colorB = b;
		this.colorAlpha = alpha;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getStrength() {
		return strength;
	}
	
	public void setColorR(int colorR) {
		this.colorR = colorR;
	}
	
	public void setColorG(int colorG) {
		this.colorG = colorG;
	}
	
	public void setColorB(int colorB) {
		this.colorB = colorB;
	}
	
	public void setColorAlpha(float colorAlpha) {
		this.colorAlpha = colorAlpha;
	}
	
	public int getColorR() {
		return colorR;
	}
	
	public int getColorG() {
		return colorG;
	}
	
	public int getColorB() {
		return colorB;
	}
	
	public float getColorAlpha() {
		return colorAlpha;
	}

}
