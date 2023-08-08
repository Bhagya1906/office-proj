package employeetable;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class ComboDialog extends Dialog {

    private String title;
    private String message;
    private String[] items;
    private String initialValue;
    private String selectedValue;
    private Combo combo;

    public ComboDialog(Shell parentShell, String title, String message, String[] items, String initialValue) {
        super(parentShell);
        this.title = title;
        this.message = message;
        this.items = items;
        this.initialValue = initialValue;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(title);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        composite.setLayout(layout);

        combo = new Combo(composite, SWT.READ_ONLY);
        combo.setItems(items);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        combo.setText(initialValue);
        selectedValue = initialValue;

        combo.addListener(SWT.Selection, e -> {
            selectedValue = combo.getText();
        });
        return composite;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    @Override
    protected void okPressed() {
        // Set the selected value to be returned when OK is pressed.
        selectedValue = combo.getText();
        super.okPressed();
    }

    public String getSelectedCity() {
        return selectedValue;
    }
}
