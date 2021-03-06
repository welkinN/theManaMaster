package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

	/** The view frame. */
	private ViewFrame					viewFrame;
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -998294702363713521L;
	
	private BufferedImage imageArray[][];
	
	private BufferedImage background;
	
	JPanel pScoreAndLife = new JPanel();
	
	private JLabel lLife = new JLabel();
	
	private JLabel lScore = new JLabel();
	
	private String arraySymbol[] = {"b","c","f1","f2","f3","f4","f5","gc","go","lb","lbl","lbr","lbu","lul","lur","ll","lr","m1","m2",
			"m3","m4","p","vb","hb"," "};
	
	private String arrayImageName[] = {"bone","crystal_ball","fireball_1","fireball_2","fireball_3","fireball_4","fireball_5","gate_closed",
			   "gate_open","lorann_b","loran_lbl","lorann_lbr","loran_lu","lorann_lul","loran_lur","lorann_ll",
			   "loran_lr","monster_1","monster_2","monster_3","monster_4","purse","vertical_bone","horizontal_bone",
			   "blank"};


	/**
	 * Instantiates a new view panel.
	 *
	 * @param viewFrame
	 *          the view frame
	 */
	public ViewPanel(final ViewFrame viewFrame) {
		this.setViewFrame(viewFrame);
		viewFrame.getModel().getObservable().addObserver(this);
		this.buildViewPanel();
	}
	
	public void buildViewPanel(){
		String arrayMap[][] = View.getArrayMap();
		imageArray = new BufferedImage[12][20];
		try{
			this.background = ImageIO.read(new File("sprite/fond.png"));
		}catch (IOException e){
			System.err.println("Can't read background");
			e.printStackTrace();
		}
		if(arrayMap == null) {
			for(int i=0; i<12; i++){
				for(int j=0; j<20; j++){
					try{
						this.imageArray[i][j] = ImageIO.read(new File("sprite/blank.png"));
					}
					catch (IOException e){
						System.err.println("Can't read images (without array)");
		                e.printStackTrace();
		            }
				}
			}
		}
		else {			
			for(int i=0; i<12; i++){
				for(int j=0; j<20; j++){
					for(int k=0; k<25; k++){
						try{
							if (arrayMap[i][j].equals(arraySymbol[k])) { this.imageArray[i][j] = ImageIO.read(new File("sprite/" + arrayImageName[k] + ".png")); }
						}catch (IOException e){
							System.err.println("Can't read images (with array)");
			                e.printStackTrace();
			            }
					}
				}
			}
		}
		
		//pScoreAndLife.setLayout();
		this.setLayout(new BorderLayout());
		pScoreAndLife.setPreferredSize(new Dimension(485,30));
		pScoreAndLife.setBackground(Color.BLACK);
		Font font = new Font("Tahoma", Font.BOLD, 20);
		lLife.setFont(font);
		lLife.setText("<html><font color = #1E7FCB >Resurections : 00</font></html>");
		//lLife.setLocation(10,10);
		lScore.setFont(font);
		lScore.setText("<html><font color = #F0C300 >Score : 00000000</font></html>");
		//lScore.setLocation(10,300);
		pScoreAndLife.add(lLife);
		pScoreAndLife.add(lScore);
		this.add(pScoreAndLife, BorderLayout.SOUTH);
		
		this.repaint();
	}

	/**
	 * Gets the view frame.
	 *
	 * @return the view frame
	 */
	private ViewFrame getViewFrame() {
		return this.viewFrame;
	}

	/**
	 * Sets the view frame.
	 *
	 * @param viewFrame
	 *          the new view frame
	 */
	private void setViewFrame(final ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		//graphics.clearRect(0, 0, this.getWidth(), this.getHeight());
		//graphics.drawString(this.getViewFrame().getModel().getMessage(), 10, 20);
		Graphics2D g2 = (Graphics2D)graphics ;
		g2.drawImage(this.background,0,0,null);
		
		int placeX = 10, placeY = 10;
		for(int i = 0; i<this.imageArray.length; i++) {
			for(int j=0; j<imageArray[0].length; j++){
	            g2.drawImage(this.imageArray[i][j],placeX,placeY, null);
	            placeX += 32;
			}
			placeX = 10;
			placeY += 32;
        }
        
	}
}
