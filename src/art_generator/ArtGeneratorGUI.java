package art_generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class ArtGeneratorGUI {

	public ArtGeneratorGUI() {

		// initialize art generator
		ArtGeneratorImpl artGenerator = new ArtGeneratorImpl(500, 500);

		// frame
		JFrame frame = new JFrame();
		frame.setBackground(Color.white);
		//size and location
		frame.setMinimumSize(new Dimension(800, 560));
		frame.setPreferredSize(new Dimension(800, 560));
		frame.setLocation(100,100);
		
		// panel
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(2, 2, 2, 2));
		panel.setLayout(new GridLayout(0, 1));
		panel.setBackground(Color.white);
		
		// image panel
		JPanel imgPanel = new JPanel();
		imgPanel.setBackground(Color.darkGray);
		ImageIcon imgIcon = new ImageIcon(artGenerator.getImage());
		JLabel imgLabel = new JLabel(imgIcon);
		imgPanel.add(imgLabel);
		
		// left panel
		JPanel lPanel = new JPanel();
		lPanel.setBackground(Color.white);
		
		// left side button panel
		JPanel lButtonPanel = new JPanel();
		lButtonPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		lButtonPanel.setBackground(Color.white);
		lButtonPanel.setLayout(new GridLayout(20, 1));
		final JLabel artLabel = new JLabel("Draw Art: ");
		lButtonPanel.add(artLabel);
		final JButton boxArtButton = new JButton("Box Art");
		lButtonPanel.add(boxArtButton);
		boxArtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.boxArtImpl();
				imgLabel.repaint();
			}
		});
		final JButton circleArtButton = new JButton("Cicle Art");
		lButtonPanel.add(circleArtButton);
		circleArtButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.circleArtImpl();
				imgLabel.repaint();
			}
		});
		final JButton rainbowBoxButton = new JButton("Rainbow Box Art");
		lButtonPanel.add(rainbowBoxButton);
		rainbowBoxButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.rainbowBoxImpl();
				imgLabel.repaint();
			}
		});
		final JButton polkaDotButton = new JButton("Polka Dot Art");
		lButtonPanel.add(polkaDotButton);
		polkaDotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.rainbowPolkaDotImpl();
				imgLabel.repaint();
			}
		});
		final JButton lightGradientButton = new JButton("Light Gradient");
		lButtonPanel.add(lightGradientButton);
		lightGradientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.lightGradientImpl();
				imgLabel.repaint();
			}
		});
		final JButton darkGradientButton = new JButton("Dark Gradient");
		lButtonPanel.add(darkGradientButton);
		darkGradientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.darkGradientImpl();
				imgLabel.repaint();
			}
		});
		final JButton vividGradientButton = new JButton("Vivid Gradient");
		lButtonPanel.add(vividGradientButton);
		vividGradientButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				artGenerator.vividGradientImpl();
				imgLabel.repaint();
			}
		});
		
		// left side option panel
		JPanel lOptionPanel = new JPanel();
		lOptionPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		lOptionPanel.setBackground(Color.white);
		lOptionPanel.setLayout(new GridLayout(20, 1));
		final JLabel optionLabel = new JLabel("Options: ");
		lOptionPanel.add(optionLabel);
		final JLabel xLabel = new JLabel("Number of Horizontal Pixels (X):");
		lOptionPanel.add(xLabel);
		SpinnerModel modelX = new SpinnerNumberModel(500, 4, 10000, 1);     
		final JSpinner spinnerX = new JSpinner(modelX);
		lOptionPanel.add(spinnerX);
		final JLabel yLabel = new JLabel("Number of Vertical Pixels (Y):");
		lOptionPanel.add(yLabel);  
		SpinnerModel modelY = new SpinnerNumberModel(500, 4, 10000, 1);     
		final JSpinner spinnerY = new JSpinner(modelY);
		lOptionPanel.add(spinnerY);
		final JButton changeResButton = new JButton("Change Resolution");
		lOptionPanel.add(changeResButton);
		changeResButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (spinnerX.getValue() != null && spinnerY.getValue() != null) {
					frame.setSize(frame.getSize().width - 1, frame.getSize().height - 1);
					artGenerator.newImage((int) spinnerX.getValue(), (int) spinnerY.getValue());
					imgIcon.setImage(artGenerator.getImage());
					imgLabel.repaint();
					frame.setSize(frame.getSize().width + 1, frame.getSize().height + 1);
				}
			}
		});
		final JLabel fileLabel = new JLabel("File name: ");
		lOptionPanel.add(fileLabel);
		final JTextArea fileNameTextBox = new JTextArea("name");
		lOptionPanel.add(fileNameTextBox);
		final JButton saveButton = new JButton("Save");
		lOptionPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileNameTextBox.getText() != null) {
					artGenerator.saveCurrentImage(fileNameTextBox.getText());
				}
			}
		});
		
		// scroll panes
		JScrollPane imgScrollPane = new JScrollPane(imgPanel);
		imgScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		imgScrollPane.getHorizontalScrollBar().setUnitIncrement(10);
		JScrollPane lButtonScrollPane = new JScrollPane(lButtonPanel);
		lButtonScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		lButtonScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		lButtonScrollPane.setMinimumSize(new Dimension(200, 200));
		JScrollPane lOptionScrollPane = new JScrollPane(lOptionPanel);
		lOptionScrollPane.getVerticalScrollBar().setUnitIncrement(10);
		lOptionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		lOptionScrollPane.setMinimumSize(new Dimension(200, 250));
		
		// split panes
		JSplitPane lSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, lOptionScrollPane, lButtonScrollPane);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lSplitPane, imgScrollPane);

		// finish setup
		frame.add(panel, BorderLayout.CENTER);
		panel.add(splitPane);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Art Generator");
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new ArtGeneratorGUI();
	}

}
