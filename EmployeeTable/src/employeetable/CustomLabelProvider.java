package employeetable;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class CustomLabelProvider extends ColumnLabelProvider {
    private int ColIndex; // Assuming you have defined this variable elsewhere.
	private Color invalidEmailColor;

    
    public CustomLabelProvider(Color invalidEmailColor) {
		// TODO Auto-generated constructor stub
    	this.invalidEmailColor= invalidEmailColor;
	}

	@Override
    public Color getBackground(Object element) {
    	System.out.println("heloo");
        Employee employee = (Employee) element;
        //String i = employee.getEmpId();
        Color c = new Color(Display.getCurrent(), 255, 0, 0);
        if (ColIndex == 6) {
            if (!employee.getValidEmail()) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Color getForeground(Object element) {
        // TODO Auto-generated method stub
        return super.getForeground(element);
    }
    
}
