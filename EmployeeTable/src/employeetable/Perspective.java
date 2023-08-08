package employeetable;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	 public void createInitialLayout(IPageLayout layout) {
	        // Add your custom view to the perspective
		 
		 String editorArea = layout.getEditorArea();
	        layout.setEditorAreaVisible(false);
	        layout.setFixed(true);
	        //layout.addView("EmployeeTable.view", IPageLayout.TOP, 0.1f, editorArea);
	        layout.addStandaloneView("EmployeeTable.view", false, IPageLayout.TOP, 0.1f, editorArea);
	    }
}