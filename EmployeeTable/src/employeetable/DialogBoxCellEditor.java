//package employeetable;
//import org.eclipse.jface.dialogs.InputDialog;
//import org.eclipse.jface.viewers.DialogCellEditor;
//import org.eclipse.jface.window.Window;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//
//public class DialogBoxCellEditor extends DialogCellEditor {
//
//    static final String OK = null;
//	private String selectedCity;
//
//    public DialogBoxCellEditor(Composite parent) {
//        super(parent);
//    }
//
//    
//    public DialogBoxCellEditor(Shell shell, String[] cityOptions) {
//		// TODO Auto-generated constructor stub
//	}
//
//
//	@Override
//    protected Object openDialogBox(Control cellEditorWindow) {
//        Shell shell = cellEditorWindow.getShell();
//        InputDialog dialog =new InputDialog(shell, "City", "select the city",getDialogInitialValue() , null);
//        return (dialog.open()==Window.OK ? dialog.getValue():null);
//    }
//    
//    protected String getDialogInitialValue() {
//		Object value = getValue();
//		return (value == null ? null : value.toString());
//	}
//
//    String open() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//    protected void updateContents(Object value) {
//        if (getDefaultLabel() != null && value instanceof String) {
//            getDefaultLabel().setText((String) value);
//        }
//    }
//
//    @Override
//    protected Control createContents(Composite cell) {
//    	Text t= new Text(cell,SWT.NONE);
//    	return t;
//    }
//
//    public String getSelectedCity() {
//        return selectedCity;
//    }
//
//    public void setSelectedCity(String selectedCity) {
//        this.selectedCity = selectedCity;
//    }
//}

package employeetable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class DialogBoxCellEditor extends DialogCellEditor {

    private String[] cityOptions;
    private String selectedCity;

    public DialogBoxCellEditor(Composite parent, String[] cityOptions) {
        super(parent);
        this.cityOptions = cityOptions;
    }

    @Override
    protected Object openDialogBox(Control cellEditorWindow) {
        Shell shell = cellEditorWindow.getShell();
        System.out.println("dialog");
        ComboDialog dialog = new ComboDialog(shell, "City", "Select the city", cityOptions, getDialogInitialValue());
        if (dialog.open() == Dialog.OK) {
            return dialog.getSelectedCity();
        }
        return null;
    }

    protected String getDialogInitialValue() {
        Object value = getValue();
        return (value == null ? "" : value.toString());
    }

    @Override
    protected void updateContents(Object value) {
        if (getDefaultLabel() != null && value instanceof String) {
            getDefaultLabel().setText((String) value);
        }
    }

    @Override
    protected Control createContents(Composite cell) {
        return getDefaultLabel();
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }
}
