package art_generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ArtGeneratorEditor {

	public static void main(String[] args) {
		
		// initialize main window
		JFrame mainFrame = new JFrame("Art Generator");
		mainFrame.setMinimumSize(new Dimension(1200, 700));
		mainFrame.setLocation(50,20);
		mainFrame.setBackground(Color.lightGray);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialize art generator
		ArtGeneratorImpl artGenerator = new ArtGeneratorImpl(500, 500);
		
		// initialize image frame
		JPanel imgFrame = new JPanel();
		Dimension imgFrameDimension = new Dimension(500, 500);
		imgFrame.setMinimumSize(imgFrameDimension);
		mainFrame.getContentPane().add(BorderLayout.CENTER, imgFrame);
		ImageIcon imgIcon = new ImageIcon(artGenerator.getImage());
		JLabel imgLabel = new JLabel(imgIcon);
		imgFrame.add(imgLabel);
		
		// left side buttons
		JPanel lButtonFrame = new JPanel();
		Dimension lButtonFrameDimension = new Dimension(5000, mainFrame.getHeight());
		lButtonFrame.setMinimumSize(lButtonFrameDimension);
		lButtonFrame.setLayout(new BoxLayout (lButtonFrame, BoxLayout.Y_AXIS));
		mainFrame.getContentPane().add(BorderLayout.WEST, lButtonFrame);
		final JButton boxArtButton = new JButton("Box Art");
		lButtonFrame.add(boxArtButton);
		final JButton circleArtButton = new JButton("Cicle Art");
		lButtonFrame.add(circleArtButton);
		final JButton rainbowBoxButton = new JButton("Rainbow Box Art");
		lButtonFrame.add(rainbowBoxButton);
		final JButton polkaDotButton = new JButton("Polka Dot Art");
		lButtonFrame.add(polkaDotButton);
		final JButton lightGradientButton = new JButton("Light Gradient");
		lightGradientButton.setMinimumSize(new Dimension(50, 50));
		lButtonFrame.add(lightGradientButton);
		final JButton darkGradientButton = new JButton("Dark Gradient");
		lButtonFrame.add(darkGradientButton);
		final JButton vividGradientButton = new JButton("Vivid Gradient");
		lButtonFrame.add(vividGradientButton);
		boxArtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.boxArtImpl();
				imgLabel.repaint();
			}
		});
		circleArtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.circleArtImpl();
				imgLabel.repaint();
			}
		});
		rainbowBoxButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.rainbowBoxImpl();
				imgLabel.repaint();
			}
		});
		polkaDotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.rainbowPolkaDotImpl();
				imgLabel.repaint();
			}
		});
		lightGradientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.lightGradientImpl();
				imgLabel.repaint();
			}
		});
		darkGradientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.darkGradientImpl();
				imgLabel.repaint();
			}
		});
		vividGradientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.vividGradientImpl();
				imgLabel.repaint();
			}
		});

		// right side buttons
		JPanel rButtonFrame = new JPanel();
		Dimension rButtonFrameDimension = new Dimension(50, mainFrame.getHeight());
		rButtonFrame.setSize(rButtonFrameDimension);
		rButtonFrame.setMinimumSize(new Dimension(rButtonFrameDimension));
		rButtonFrame.setLayout(new BoxLayout (rButtonFrame, BoxLayout.Y_AXIS));
		mainFrame.getContentPane().add(BorderLayout.EAST, rButtonFrame);
		final JLabel xLabel = new JLabel("Number of Horizontal Pixels (X):");
		rButtonFrame.add(xLabel);
		SpinnerModel modelX = new SpinnerNumberModel(500, 4, 10000, 1);     
		final JSpinner spinnerX = new JSpinner(modelX);
		rButtonFrame.add(spinnerX);
		final JLabel yLabel = new JLabel("Number of Vertical Pixels (Y):");
		rButtonFrame.add(yLabel);  
		SpinnerModel modelY = new SpinnerNumberModel(500, 4, 10000, 1);     
		final JSpinner spinnerY = new JSpinner(modelY);
		rButtonFrame.add(spinnerY);
		final JButton changeResButton = new JButton("Change Resolution");
		rButtonFrame.add(changeResButton);
		final JLabel fileLabel = new JLabel("File name: ");
		rButtonFrame.add(fileLabel);
		final JTextArea fileNameTextBox = new JTextArea("name");
		rButtonFrame.add(fileNameTextBox);
		final JButton saveButton = new JButton("Save");
		rButtonFrame.add(saveButton);
		changeResButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (spinnerX.getValue() != null && spinnerY.getValue() != null) {
					artGenerator.newImage((int) spinnerX.getValue(), (int) spinnerY.getValue());
					imgFrame.repaint();
					imgLabel.repaint();
				}
			}
		});
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileNameTextBox.getText() != null) {
					artGenerator.saveCurrentImage(fileNameTextBox.getText());
				}
			}
		});
//		button11.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				artGenerator.newImage(1920, 1080);
//				imgIcon.setImage(artGenerator.getImage());
//				imgFrameDimension.width = 1080;
//				imgFrameDimension.height = 1920;
//				imgFrame.setSize(imgFrameDimension);
//				imgFrame.setMinimumSize(new Dimension(imgFrameDimension));
//				imgLabel.repaint();
//				imgFrame.repaint();
//			}
//		});
		
		// finish setup
//		mainFrame.pack();
		mainFrame.setVisible(true);

	}
	
}
