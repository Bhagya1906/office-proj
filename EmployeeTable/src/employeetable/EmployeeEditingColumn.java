package employeetable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class EmployeeEditingColumn extends EditingSupport {

    private int columnIndex;
    private TableViewer viewer;
    private DialogBoxCellEditor dialogBoxCellEditor ;
	private final Color defaultColor;
    private final Color invalidEmailColor;
    String[] cities = {"New York", "Los Angeles", "Chicago", "San Francisco"};
    
    public EmployeeEditingColumn(TableViewer viewer, int columnIndex) {
        super(viewer);
        this.viewer = viewer;
        this.columnIndex = columnIndex;
        
        
         //Initialize colors for default and invalid email states
         defaultColor = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
         invalidEmailColor = Display.getCurrent().getSystemColor(SWT.COLOR_RED);        
        
         if (columnIndex == 7) { // Create the CitySelectionDialog for the "City" column   
        
		dialogBoxCellEditor = new DialogBoxCellEditor(viewer.getTable().getShell(),cities);
        }
    }
        

    @Override
    protected CellEditor getCellEditor(Object element) {
    	 if (columnIndex == 3) {
             String[] genders = new String[]{"Male", "Female"};
             return new ComboBoxCellEditor(viewer.getTable(), genders, SWT.READ_ONLY);
    	 }
           else if (columnIndex == 7) { // Create the CitySelectionDialog for the "City" column   
//        	return new DialogBoxCellEditor(viewer.getTable()); 
        	   return dialogBoxCellEditor; 
        	 
         }
           	
             else {
             return new TextCellEditor(viewer.getTable()); 
         }

    }
    
   
	@Override
    protected boolean canEdit(Object element) {
        return columnIndex != 0 && columnIndex != 1;
    }

    @Override
    protected Object getValue(Object Element) {
        Employee employee = (Employee) Element;
        switch (columnIndex) {
           
            case 2:
                return employee.getDesignation();
            case 3:
            	String gender = employee.getGender();
                String[] genders = new String[]{"Male", "Female"};
                for (int i = 0; i < genders.length; i++) {
                    if (genders[i].equals(gender)) {
                        return i;
                    }
                }
                // If the gender is not found, return the default value (0)
                return 0;
            case 4:
                return employee.getCompany();
            case 5:
                return String.valueOf(employee.getSalary());
            case 6:
                return employee.getEmail();
            case 7:
                return employee.getCity();
            default:
                return "";
        }
    }

    @Override
    protected void setValue(Object element, Object value) {
//        if (element instanceof Employee) {
            Employee employee = (Employee) element;
            boolean isValidEmail = true;

            switch (columnIndex) {
               
                case 2:
                    employee.setDesignation(String.valueOf(value));
                    break;
                case 3:
                	 int selectedIndex = (int) value;
                     String[] genders = new String[]{"Male", "Female"};
                     employee.setGender(genders[selectedIndex]);
                     break;
                case 4:
                    employee.setCompany(String.valueOf(value));
                    break;
                case 5:
                    employee.setSalary(Double.parseDouble(String.valueOf(value)));
                    break;
                
                case 6:

                    String email = value.toString();

                    if (email.endsWith("@gmail.com") && isValidEmail(email)) {
                        employee.setEmail(email);
                        employee.setValidEmail(true);

                    } else {
                   
                    	 System.out.println("Invalid email address: " + email);
                         employee.setValidEmail(false);
                         employee.setEmail(email);
                         isValidEmail = false;
                         new CustomLabelProvider(invalidEmailColor);
                     }

                    break;
                	
                case 7:                	
                	employee.setCity(value.toString());
                    break;
                    
            }
            viewer.update(element, null);
        }
    //}
    
//    private boolean isValidEmail(String email) {
//
//        // A simple email format validation using regex
//
//    	boolean a=true;
//		for(int i=0;i<email.length();i++)
//		{
//			char c = email.charAt(i);
//			if(Character.isUpperCase(c))
//			{
//				return a=false;
//			}
//			return a=true;
//		}
//		
//		return a;
//
//    }
    
    private boolean isValidEmail(String email) {
        // A simple email format validation using regex
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}