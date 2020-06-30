package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.model.Iterator;
import application.model.Layout;
import application.model.ListIterator;
import application.model.MyShape;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HistoryController {
	private TreeView<String> tree;
	private List<Layout> layoutsList;

	public TreeView<String> getTree() {
		return tree;
	}

	public List<Layout> getLayoutsList() {
		return layoutsList;
	}

	public void setLayoutsList(List<Layout> layoutsList) {
		this.layoutsList = layoutsList;
	}

	public HistoryController() {
		this.layoutsList = new ArrayList<>();
	}

	public Stage initialiseHistoryStage() {
		Stage stage = new Stage();
		TreeItem<String> rootItem = new TreeItem<>("Canevas");
		rootItem.setExpanded(true);

		for (Iterator iter = new ListIterator(layoutsList); iter.hasNext();) {
			Layout layout = (Layout) iter.next();
			TreeItem<String> layoutItem = new TreeItem<>(layout.getLayoutName());
			layoutItem.setExpanded(true);
			for (MyShape shape : layout.getShapesList()) {
				layoutItem.getChildren().add(shape.getTreeItem());
			}
			rootItem.getChildren().add(layoutItem);
		}
		tree = new TreeView<String>(rootItem);

		StackPane root = new StackPane();
		root.getChildren().setAll(tree);
		stage.initModality(Modality.NONE);
		stage.setScene(new Scene(root, 300, 250));
		stage.show();
		return stage;
	}

	public void refreshHistoryTable() {
		if (tree != null) {
			TreeItem<String> rootItem = new TreeItem<>("Canevas");
			rootItem.setExpanded(true);
			for (Iterator iter = new ListIterator(layoutsList); iter.hasNext();) {
				Layout layout = (Layout) iter.next();
				TreeItem<String> layoutItem = new TreeItem<>(layout.getLayoutName());
				layoutItem.setExpanded(true);
				for (MyShape shape : layout.getShapesList()) {
					layoutItem.getChildren().add(shape.getTreeItem());
				}
				rootItem.getChildren().add(layoutItem);
			}
			tree.setRoot(rootItem);
		}
	}

}
