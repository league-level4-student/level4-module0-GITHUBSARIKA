package _02_Pixel_Art;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener, ActionListener {
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	JButton saveButton;
	JButton loadButton;
	ColorSelectionPanel csp;

	private static void save(GridPanel gp) {
		try {
			FileOutputStream fos=new FileOutputStream(new File("Artwork"));
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(gp);
			oos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

	private static GridPanel load() {
			try{
				FileInputStream fis=new FileInputStream(new File("Artwork"));
				ObjectInputStream ois = new ObjectInputStream(fis);
				GridPanel g = (GridPanel) ois.readObject();
				ois.close();
				return g;
			}catch(IOException e){
				e.printStackTrace();
				return null;
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
				 
			

	}

	public void start() {
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		saveButton = new JButton("Save Button");
		loadButton= new JButton("Load Button");
		saveButton.addActionListener(this);
		loadButton.addActionListener(this);
		window.add(saveButton);
		window.add(loadButton);
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}

	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
		
	}
	

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(saveButton==e.getSource()) {
			save(gp);
		}
		
		if(loadButton==e.getSource()) {
			window.remove(gp);
			gp=load();
			window.add(gp);
			gp.addMouseListener(this);
			window.pack();
			
		}
		
	}
}
