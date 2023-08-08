package employeetable;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Color;

public class EmployeeLabelProvider extends ColumnLabelProvider{
	
	private int ColIndex;

	public EmployeeLabelProvider(int ColIndex)
	{
		this.ColIndex = ColIndex;
	}
	

	@Override
	public String getText(Object Element)
	{
		switch(ColIndex)
		{
		case 0: return ((Employee) Element).getEmpId();
		case 1: return ((Employee) Element).getName();
		case 2: return ((Employee) Element).getDesignation();
		case 3: return ((Employee) Element).getGender();
		case 4: return ((Employee) Element).getCompany();
		case 5: return String.valueOf(((Employee) Element).getSalary());
		case 6: return ((Employee) Element).getEmail();
		case 7: return ((Employee) Element).getCity();
		default:return "";
		}		
	}

//	@Override
//	public Color getBackground(Object element) {
//		 Employee employee = (Employee) element;
//		 String i =employee.getEmpId();
//		 Color c= new Color(255, 0, 0);
//            if(ColIndex == 6) {
//           	if(!employee.getValidEmail()) {
//            		return c;
//            	}
//        }
//            return null;
//	}
//
//	@Override
//	public Color getForeground(Object element) {
//		// TODO Auto-generated method stub
//		return super.getForeground(element);
//	}
   }