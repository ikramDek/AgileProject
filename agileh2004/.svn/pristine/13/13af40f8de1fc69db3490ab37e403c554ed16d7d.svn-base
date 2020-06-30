package application.model;

import java.util.ArrayList;
import java.util.List;

public class Layout {
	private String layoutName;
	private ArrayList<MyShape> shapesList;
	private boolean isDisplayed;

	public Layout(String string, boolean isDisplayed) {
		this.layoutName = string;
		this.shapesList = new ArrayList<>();
		this.isDisplayed = isDisplayed;
	}

	public void addShape(MyShape lastDrawnShape) {
		shapesList.add(lastDrawnShape);
	}

	public MyShape getLastShape() {
		return shapesList.get(shapesList.size() - 1);
	}

	public List<MyShape> getShapesList() {
		return shapesList;
	}
 
	public MyShape getShape(int i) {
		return shapesList.get(i);
	}

	public void removeShape(int i) {
		shapesList.remove(i);
	}

	public void addShape(int i, MyShape tmp) {
		shapesList.add(i, tmp);
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public void setShapesList(List<MyShape> shapesList) {
		this.shapesList = (ArrayList<MyShape>) shapesList;
	}

	public void setShape(int i, MyShape shape) {
		shapesList.set(i, shape);
	}

	public boolean isDisplayed() {
		return isDisplayed;
	}

	public void setDisplayed(boolean isDisplayed) {
		this.isDisplayed = isDisplayed;
	}

	public Layout getClone() {
		Layout tmpLayout = new Layout(this.layoutName, this.isDisplayed);
		tmpLayout.setDisplayed(this.isDisplayed);
		for (MyShape myShape : shapesList) {
			tmpLayout.shapesList.add(myShape.getClone());
		}
		return tmpLayout;
	}

}
