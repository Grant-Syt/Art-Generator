package art_generator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ArtGeneratorImpl {
	private BufferedImage img = null;

	/* SECTION 1:
	 * 
	 * Constructor, image initialization, and retrieval.
	 * 
	 * 
	 * 
	 * 
	 */

	public ArtGeneratorImpl(int x, int y) {
		this.newImage(x, y);
	}

	public void newImage(int x, int y) {
		/* in: x,y size in pixels of new image
		 * return: n/a
		 * effect: new blank image
		 */
		img = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
	}

	public BufferedImage getImage() {
		return img;
	}

	/* SECTION 2:
	 * 
	 * High level image creation implementations.
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 * 
	 * 
	 */

	public void originPointTestImpl() {
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);
		this.fillWhite();
		Graphics2D graphics = img.createGraphics();
		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);

			//origin pixel
			graphics.setColor(new Color(currentPoint.getColorR(), currentPoint.getColorG(), currentPoint.getColorB(), (int) (currentPoint.getColorAlpha())));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());
		}
		graphics.dispose();
	}

		public void boxArtImpl() {
			/* in: n/a
			 * return: n/a
			 * effect: draw box art on image
			 */
			Graphics2D graphics  = img.createGraphics();
			this.fillColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
					(int) (Math.random()*256), (int) (Math.random()*256)));
			
			for (int i = 0; i < 50; i++) {
				graphics.setColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
						(int) (Math.random()*256), (int) (Math.random()*256)));
				graphics.fillRect((int) ((Math.random()*img.getWidth() - (img.getWidth()*.1))),
						(int) ((Math.random()*img.getHeight() - (img.getHeight()*.1))),
						(int) (Math.random()*img.getWidth()*.5), (int) (Math.random()*img.getHeight()*.5));
			}
			
		}

	public void rainbowBoxImpl() {
		/* in: n/a
		 * return: n/a
		 * effect: draw box art on image
		 */

		this.fillWhite();
		this.rainbowBox();
	}

	public void rainbowPolkaDotImpl() {
		Graphics2D graphics  = img.createGraphics();
		int dotDiameter = 100;

		this.fillColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
				(int) (Math.random()*256), (int) (Math.random()*256)));

		for(int currentX = 0; currentX < img.getWidth(); currentX += dotDiameter) {
			for(int currentY = 0; currentY < img.getHeight(); currentY += dotDiameter){
				graphics.setColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
						(int) (Math.random()*256), (int) (Math.random()*256)));
				graphics.fillOval(currentX, currentY, dotDiameter, dotDiameter);
			}
		}
	}

	public void circleArtImpl() {
		/* in: n/a
		 * return: n/a
		 * effect: draw circleWaterColor on image
		 */

		Graphics2D graphics  = img.createGraphics();
		this.fillColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
				(int) (Math.random()*256), (int) (Math.random()*256)));
		
		for (int i = 0; i < 50; i++) {
			graphics.setColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
					(int) (Math.random()*256), (int) (Math.random()*256)));
			graphics.fillOval((int) ((Math.random()*img.getWidth() - (img.getWidth()*.1))),
					(int) ((Math.random()*img.getHeight() - (img.getHeight()*.1))),
					(int) (Math.random()*img.getWidth()*.5), (int) (Math.random()*img.getHeight()*.5));
		}
	}

	public void darkGradientImpl() {
		/* in: n/a
		 * return: n/a
		 * effect: draw random gradient on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw gradient boxes
		this.fillBlack();
		this.gradient(originPoints);
	}

	public void lightGradientImpl() {
		/* in: n/a
		 * return: n/a
		 * effect: draw random gradient on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw gradient boxes
		this.fillWhite();
		this.gradient(originPoints);
	}

	public void vividGradientImpl() {
		/* in: n/a
		 * return: n/a
		 * effect: draw random vivid gradient on image
		 */

		// select origin points
		ArrayList<OriginPointImpl> originPoints = this.selectOriginPoints((int) (Math.random() * 5) + 2);

		// draw
		this.vividGradient(originPoints);
	}
	
	public void customGradientImpl(ArrayList<OriginPointImpl> originPoints, int gradientType) {
		/* gradient type
		 * 1 = light
		 * 2 = dark
		 * 3 = vivid
		 */
		if (gradientType == 1) {
			this.fillWhite();
			this.gradient(originPoints);
		} else if (gradientType == 2) {
			this.fillBlack();
			this.gradient(originPoints);
		} else {
			this.vividGradient(originPoints);
		}
	}

	/* SECTION 3:
	 * 
	 * Lower level image creation algorithms.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public void rainbowBox() {
		/* in: n/a
		 * out: n/a
		 * effect: draw rainbow box
		 */

		Graphics2D graphics  = img.createGraphics();
		this.fillColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
				(int) (Math.random()*256), (int) (Math.random()*256)));

		// origin point
		graphics.setColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
				(int) (Math.random()*256), (int) (Math.random()*256)));
		OriginPointImpl originPoint = new OriginPointImpl((int) (img.getWidth()/2), (int) (img.getHeight()/2));
		graphics.drawLine(originPoint.getX(), originPoint.getY(), originPoint.getX(), originPoint.getY());

		int rowLength = 3;
		int layerCount = 1;
		int biggerSide = this.getBiggerSide();
		int currentX = originPoint.getX();
		int currentY = originPoint.getY();

		// box layers
		for (int i = 0; i < (biggerSide-1)/2; i++) {

			// color
			graphics.setColor(new Color((int) (Math.random()*256), (int) (Math.random()*256),
					(int) (Math.random()*256), (int) (Math.random()*256)));

			// top
			currentX = originPoint.getX() - layerCount;
			currentY = originPoint.getY() - layerCount;
			graphics.drawLine(currentX, currentY, currentX + rowLength-2, currentY);
			currentX += rowLength-1;

			// right
			graphics.drawLine(currentX, currentY, currentX, currentY + rowLength-2);
			currentY += rowLength-1;

			//bottom
			graphics.drawLine(currentX, currentY, currentX - rowLength-2+4, currentY);
			currentX -= rowLength-1;

			// left
			graphics.drawLine(currentX, currentY, currentX, currentY - rowLength-2+4);

			// adjust
			rowLength += 2;
			layerCount++;
		}
		graphics.dispose();
	}

	public void gradient(ArrayList<OriginPointImpl> originPoints) {
		/* in: list of origin points
		 * out: n/a
		 * effect: draw gradient circles
		 */
		Graphics2D graphics  = img.createGraphics();
		Color oldColor;
		int oldColorR;
		int oldColorG;
		int oldColorB;

		int biggerSide = getBiggerSide();

		// origin points
		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);

			//origin pixel
			oldColor = new Color(img.getRGB(currentPoint.getX(), currentPoint.getY()));
			oldColorR = oldColor.getRed();
			oldColorG = oldColor.getGreen();
			oldColorB = oldColor.getBlue();
			graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
					(int) ((currentPoint.getColorG() + oldColorG)/2),
					(int) ((currentPoint.getColorB() + oldColorB)/2), (int) currentPoint.getColorAlpha()));
			graphics.drawLine(currentPoint.getX(), currentPoint.getY(), currentPoint.getX(), currentPoint.getY());

			// gradient
			int newColorAlpha;
			for (int currentX = 0; currentX < img.getWidth(); currentX++) {
				for(int currentY = 0; currentY < img.getHeight(); currentY++) {
					newColorAlpha = (int) (currentPoint.getColorAlpha() - ((currentPoint.getColorAlpha()/(biggerSide)) *
							Math.sqrt((Math.pow(currentX-currentPoint.getX(), 2) + Math.pow(currentY-currentPoint.getY(), 2)))));
					if(!(newColorAlpha < 0 || (currentPoint.getX() == currentX && currentPoint.getY() == currentY))) {
						oldColor = new Color(img.getRGB(currentX, currentY));
						oldColorR = oldColor.getRed();
						oldColorG = oldColor.getGreen();
						oldColorB = oldColor.getBlue();
						graphics.setColor(new Color((int) ((currentPoint.getColorR() + oldColorR)/2), 
								(int) ((currentPoint.getColorG() + oldColorG)/2),
								(int) ((currentPoint.getColorB() + oldColorB)/2), newColorAlpha));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					}
				}
			}
		}
		graphics.dispose();	
	}

	public void vividGradient(ArrayList<OriginPointImpl> originPoints) {
		/* in: list of origin points
		 * out: n/a
		 * effect: draw vivid gradient
		 */
		Graphics2D graphics  = img.createGraphics();

		int biggerSide = getBiggerSide();

		// fill with random color
		OriginPointImpl currentPoint = originPoints.get(0);
		this.fillColor(new Color((float) Math.random(), (float) Math.random(),
				(float) Math.random(), (float) Math.random()));

		// origin points
		for(int a = 0; a < originPoints.size(); a++) {
			currentPoint = originPoints.get(a);

			// gradient
			float newColorAlpha;
			float distanceFromOrigin;
			float fadePoint;
			for (int currentX = 0; currentX < img.getWidth(); currentX++) {
				for(int currentY = 0; currentY < img.getHeight(); currentY++) {

					distanceFromOrigin = (float) Math.sqrt((Math.pow(currentX-currentPoint.getX(), 2) + Math.pow(currentY-currentPoint.getY(), 2)));
					fadePoint = biggerSide * (currentPoint.getStrength()+5) / 20; // divide by smaller number for larger centers
					float fadeDistance = distanceFromOrigin - fadePoint;

					if (fadeDistance < 0) { // solid
						graphics.setColor(new Color((float)currentPoint.getColorR()/255, (float)currentPoint.getColorG()/255,
								(float)currentPoint.getColorB()/255, (float)currentPoint.getColorAlpha()/255));
						graphics.drawLine(currentX, currentY, currentX, currentY);
					} else {
						newColorAlpha = (float) (currentPoint.getColorAlpha() - (fadeDistance/2)); // divide by larger number for longer fade
						if(newColorAlpha >= 0) {
							graphics.setColor(new Color((float)currentPoint.getColorR()/255, (float)currentPoint.getColorG()/255,
									(float)currentPoint.getColorB()/255, newColorAlpha/255));							
							graphics.drawLine(currentX, currentY, currentX, currentY);
						}
					}
				}
			}
		}
		graphics.dispose();	
	}

	/* SECTION 4:
	 * 
	 * Helper methods and I/O.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public void fillBlack() {
		this.fillColor(Color.black);
	}

	public void fillWhite() {
		this.fillColor(Color.white);
	}

	public void fillColor(Color color) {
		Graphics2D graphics  = img.createGraphics();
		graphics.setBackground(color);
		graphics.clearRect(0, 0, img.getWidth(), img.getHeight());
		graphics.dispose();
	}

	public void clear() {
		Graphics2D graphics  = img.createGraphics();
		graphics.clearRect(0, 0, img.getWidth(), img.getHeight());
		graphics.dispose();
	}

	public boolean saveCurrentImage(String fileName) {
		try {
			File outputfile = new File(fileName + ".png");
			ImageIO.write(this.getImage(), "png", outputfile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public void printOriginPoints(ArrayList<OriginPointImpl> originPoints) {
		for(int i = 0; i < originPoints.size(); i++) {
			OriginPointImpl currentPoint = originPoints.get(i);
			System.out.println("Point" + i + ": (x, y): (" + currentPoint.getX() + ", " + 
					currentPoint.getY() + ") strength: " + currentPoint.getStrength() +
					"\n    R:" + currentPoint.getColorR() + " G:" + currentPoint.getColorG() +
					" B:" + currentPoint.getColorB() + " Alpha:" + currentPoint.getColorAlpha());
		}
		System.out.print("\n");
	}

	private ArrayList<OriginPointImpl> selectOriginPoints(int numOfOriginPoints) {
		/* in: number of origin points to make
		 * out: list of origin points
		 * effect: make list of origin points
		 */

		ArrayList<OriginPointImpl> originPoints = new ArrayList<OriginPointImpl>();
		boolean onSector1 = ((int) (Math.random()*2)) == 0;
		int currentSide;
		/* sector 1
		 * 0: bottom right
		 * 1: right
		 * 2: top right
		 * 3: top
		 * sector 2
		 * 4: top left
		 * 5: left
		 * 6: bottom left
		 * 7: bottom
		 */
		ArrayList<Integer> sector1 = new ArrayList<Integer>();
		sector1.add(0);
		sector1.add(1);
		sector1.add(2);
		sector1.add(3);
		ArrayList<Integer> sector2 = new ArrayList<Integer>();
		sector2.add(4);
		sector2.add(5);
		sector2.add(6);
		sector2.add(7);

		// Select semi-random points on border
		for (int i = 0; i < numOfOriginPoints; i++) {
			if(onSector1) {
				currentSide = (int) (Math.random() * sector1.size());
				if (sector1.get(currentSide) == 0) {
					// bottom right
					originPoints.add(new OriginPointImpl(img.getWidth() - 1, 
							img.getHeight() - 1, (int) (Math.random() * 5)));
				} else if(sector1.get(currentSide) == 1) {
					// right
					originPoints.add(new OriginPointImpl(img.getWidth() - 1,
							(int) ((Math.random()*(img.getHeight()*.40)) + img.getHeight()*.30), (int) (Math.random() * 5)));
				} else if(sector1.get(currentSide) == 2) {
					// top right
					originPoints.add(new OriginPointImpl(img.getWidth() - 1,
							0, (int) (Math.random() * 5)));
				} else {
					// top
					originPoints.add(new OriginPointImpl((int) ((Math.random()*(img.getWidth()*.40)) + img.getWidth()*.30),
							0, (int) (Math.random() * 5)));
				}
				sector1.remove(currentSide);
				onSector1 = false;
			} else {
				currentSide = (int) (Math.random() * sector2.size());
				if (sector2.get(currentSide) == 4) {
					// top left
					originPoints.add(new OriginPointImpl(0, 
							0, (int) (Math.random() * 5)));
				} else if(sector2.get(currentSide) == 5) {
					// left
					originPoints.add(new OriginPointImpl(0,
							(int) ((Math.random()*(img.getHeight()*.40)) + img.getHeight()*.30), (int) (Math.random() * 5)));
				} else if(sector2.get(currentSide) == 6) {
					// bottom left
					originPoints.add(new OriginPointImpl(0,
							img.getHeight() - 1, (int) (Math.random() * 5)));
				} else {
					// bottom
					originPoints.add(new OriginPointImpl((int) ((Math.random()*(img.getWidth()*.40)) + img.getWidth()*.30),
							img.getHeight() - 1, (int) (Math.random() * 5)));
				}
				sector2.remove(currentSide);
				onSector1 = true;
			}
		}

		for(int a = 0; a < originPoints.size(); a++) {
			OriginPointImpl currentPoint = originPoints.get(a);
			currentPoint.setColorR((int) (Math.random()*256));
			currentPoint.setColorG((int) (Math.random()*256));
			currentPoint.setColorB((int) (Math.random()*256));
			currentPoint.setColorAlpha((currentPoint.getStrength() + 1) * 51);
		}

		printOriginPoints(originPoints);

		return originPoints;
	}

	private int getBiggerSide(){
		int biggerSide;
		if (img.getHeight() > img.getWidth()) {
			biggerSide = img.getHeight();
		} else {
			biggerSide = img.getWidth();
		}
		return biggerSide;
	}
}
