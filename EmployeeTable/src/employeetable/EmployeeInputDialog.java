package employeetable;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

public class EmployeeInputDialog extends InputDialog {
    public EmployeeInputDialog(Shell parentShell, String dialogTitle, String dialogMessage, String initialValue) {
        super(parentShell, dialogTitle, dialogMessage, initialValue, null);
    }
}

