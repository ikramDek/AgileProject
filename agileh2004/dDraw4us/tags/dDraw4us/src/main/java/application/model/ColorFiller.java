package application.model;

import javafx.scene.paint.Color;

public class ColorFiller implements Filler{
	
	private Color color;
	private MyShape shape;
	public ColorFiller(Color color2, MyShape myShape) {
		// TODO Auto-generated constructor stub
		color = color2;
		shape = myShape;
	}
	@Override
	public void fill() {
		// TODO Auto-generated method stub
		shape.setFill(color);
		shape.updateFields();
	}
	@Override
	public boolean isColor() {
		// TODO Auto-generated method stub
		return true;
	}
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

}
