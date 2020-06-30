package application.model;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public interface MyShape {

	double getStartX();

	double getStartY();

	double getEndX();

	double getEndY();

	void setSelected(boolean b);

	void disableDraggable();

	boolean isSelected();

	void setPattern(boolean b);

	void updateFields();

	void draw(ObservableList<Node> children);

	void changeStrokeColor(Color value);

	void rotateShape();

	void moveShape(int noStep, int i);
	
	void flipShape(boolean value);

	void flipComposedShape(boolean value, double d, double e);
	
	void enlarge();

	void changeStrokeWidth(int val);

	void setFiller(Filler filler);

	void setFill(Paint pattern);

	void setRoot(ComposedShape compShape);

	public ComposedShape getRoot();

	boolean isInside(Double startX, Double startY, Double currentX, Double currentY);

	public MyShape getClone();

	ArrayList<SingleShape> getSingleShapeList();

	String getLayout();

	public List<MyShape> getList();

	Double getWidth();

	Double getHeight();

	Element createNode(Element sh, Document dom);
	
	TreeItem<String> getTreeItem ();

	
}
