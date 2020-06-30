package application.controller;

import java.util.ArrayList;
import java.util.List;

import application.model.Layout;

public class ChangesController {

	private List<List<Layout>> undoList;
	private List<List<Layout>> redoList;
	private CanevasController canevasController;
	private MenuUIController menuController;

	public ChangesController(CanevasController canevasController) {
		this.canevasController = canevasController;
		undoList = new ArrayList<>();
		redoList = new ArrayList<>();
	}

	public List<List<Layout>> getUndoList() {
		return undoList;
	}

	public List<List<Layout>> getRedoList() {
		return redoList;
	}

	public void undo() {
		if (!undoList.isEmpty()) {
			List<Layout> theChangeToGoTo = undoList.remove(undoList.size() - 1);
			saveStateToRedo();
			canevasController.refreshVisibleLayouts(theChangeToGoTo);
		}
	}

	public void redo() {
		if (!redoList.isEmpty()) {
			List<Layout> theChangeToGoTo = redoList.remove(redoList.size() - 1);
			saveStateToUndo();
			canevasController.refreshVisibleLayouts(theChangeToGoTo);
		}
	}

	private void saveStateToRedo() {
		redoList.add(canevasController.getLayoutsListClone());
		menuController.updateMenuItemState();
	}

	public void saveStateToUndo() {
		undoList.add(canevasController.getLayoutsListClone());
		menuController.updateMenuItemState();
	}

	public void setMenuController(MenuUIController menuUIController) {
		this.menuController = menuUIController;

	}

	public CanevasController getCanevasController() {
		return canevasController;
	}
}
