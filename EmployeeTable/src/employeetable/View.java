package employeetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
	public static final String ID = "EmployeeTable.view";

	private static final TabFolder tab = null;

	
	@Inject IWorkbench workbench; //represents workbench environment.provide methods to manage workbench components
	private TableViewer viewer;
	
	@Override
	public void createPartControl(Composite parent) {
		
		TabFolder tab = new TabFolder(parent,SWT.BORDER);
		TabItem item1 = new TabItem(tab,SWT.NONE);
		item1.setText("Tab 1");
				
		Composite tabcomp = new Composite(tab,SWT.NONE);
		item1.setControl(tabcomp);
		tabcomp.setLayout(new FillLayout());	
		
		TabItem tabItem2 = new TabItem(tab, SWT.NULL);
	    tabItem2.setText("Tab 2");
	    Composite tabComp2 = new Composite(tab, SWT.NONE);
	    tabComp2.setLayout(new FillLayout());
	    tabItem2.setControl(tabComp2);
		
		viewer = new TableViewer(tabcomp, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL |SWT.FULL_SELECTION);
		viewer.getTable().setLinesVisible(false);
		viewer.getTable().setHeaderVisible(true);
		
		
		String column_name[]= {"EMP_ID","NAME","DESIGNATION","GENDER","COMPANY","SALARY","EMAIL","CITY"};
		
		
		for(int i=0;i<column_name.length;i++)
		{
		TableViewerColumn column = new TableViewerColumn(viewer, SWT.NONE);
		column.getColumn().setText(column_name[i]);
		column.getColumn().setWidth(200);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);
		column.setLabelProvider(new EmployeeLabelProvider(i));
		column.setEditingSupport(new EmployeeEditingColumn(viewer, i));		
		}
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setInput(createEmployee());
		
		
		TableViewer viewerTab2 = new TableViewer(tabComp2, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
        viewerTab2.getTable().setLinesVisible(false);
        viewerTab2.getTable().setHeaderVisible(true);
        
        for (int j= 0; j < column_name.length; j++) {
            TableViewerColumn column = new TableViewerColumn(viewerTab2, SWT.NONE);
            column.getColumn().setText(column_name[j]);
            column.getColumn().setWidth(200);
            column.getColumn().setResizable(true);
            column.getColumn().setMoveable(true);
            column.setLabelProvider(new EmployeeLabelProvider(j));
        }
        
        viewerTab2.setContentProvider(ArrayContentProvider.getInstance());
        viewerTab2.setInput(createEmployee());
        
        //for setting remove menu
        MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(viewer.getTable());
        viewer.getTable().setMenu(menu);
        getSite().registerContextMenu(menuManager, viewer);
        getSite().setSelectionProvider(viewer);
        
        Action addButtonAction = new Action("ADD", SWT.PUSH) {
            @Override
            public void run() {
                addNewRow();
            }

            private void addNewRow() {
            	           	
                Employee newEmployee = new Employee("New_ID", "New_Name", "New_Designation", "New_Gender", "New_Company", 0, "New_Email", "New_City");
                // Assuming you have a List<Employee> as your input, you can add the new Employee to it
                List<Employee> employees = (List<Employee>) viewer.getInput();
                employees.add(newEmployee);
             

                // Refresh the viewer to display the new row
                viewer.refresh();
                
            }
        };

        

		 Action buttonAction = new Action("SAVE", SWT.PUSH) {
	            @Override
	            public void run() {
	                // Define the behavior of the button here
	                //System.out.println("Button clicked!");
	                saveChanges();
	            }

				private void saveChanges() {
					 Employee[] employees = getEmployeesFromTable();
					    // Update the original list with the edited data
					    List<Employee> originalEmployees = createEmployee();
					    for (int i = 0; i < originalEmployees.size(); i++) {
					        Employee originalEmployee = originalEmployees.get(i);
					        Employee editedEmployee = employees[i];
					        originalEmployee.setName(editedEmployee.getName());
					        originalEmployee.setDesignation(editedEmployee.getDesignation());
					        originalEmployee.setGender(editedEmployee.getGender());
					        originalEmployee.setCompany(editedEmployee.getCompany());
					        originalEmployee.setSalary(editedEmployee.getSalary());
					        originalEmployee.setEmail(editedEmployee.getEmail());
					        originalEmployee.setCity(editedEmployee.getCity());
					    }
					    
					    // Now you have updated Employee objects, you can print the details to the console
					    for (Employee employee : originalEmployees) {
					        System.out.println(employee);
					    }
					
				}

				
					private Employee[] getEmployeesFromTable() {
					    TableItem[] tableItems = viewer.getTable().getItems();
					    Employee[] employees = new Employee[tableItems.length];
					    for (int i = 0; i < tableItems.length; i++) {
					        TableItem tableItem = tableItems[i];
					        String empId = tableItem.getText(0);
					        String name = tableItem.getText(1);
					        String designation = tableItem.getText(2);
					        String gender = tableItem.getText(3);
					        String company = tableItem.getText(4);
					        double salary = Double.parseDouble(tableItem.getText(5));
					        String email = tableItem.getText(6);
					        String city = tableItem.getText(7);					        					        
					        employees[i] = new Employee(empId, name, designation, gender, company, salary,email,city);
					    }
					    return employees;
				}
	        };
	        
        
	        // Get the toolbar manager from the action bars
	        IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();

	        // Add the action to the toolbar
	        toolBarManager.add(buttonAction);
	        toolBarManager.add(addButtonAction);

	        // Update the action bars
	        getViewSite().getActionBars().updateActionBars();
                
	}
	
	@Override
	public void setFocus() {
		
		//the method is responsible for setting the focus on the control
		//making it ready to receive user input or keyboard events.
		viewer.getControl().setFocus();
	}
	
	private List<Employee> createEmployee() {
		
		//returning array as list
		 return new ArrayList<>(Arrays.asList(
				new Employee("23bhag19", "Bhagya", "Software Engineer", "FeMale", "MBRDI", 30000, "bhagya@example.com", "Bangalore"),
				new Employee("23azn19","Atharsh","Software Engineer","Male","Amazon",30000,"atharsh@gmail.com","Pune"),
				new Employee("23ran19","Ranjani","Data Analyst","Female","Aptive",30000,"ranjani@gmail.com","Chennai"),
				new Employee("23vij19","Vijay","System Analyst","Male","Bosch",30000,"vijay@gmail.com","Bangalore"),
				new Employee("23sne19","Sneka","Software Engineer","Female","MBRDI",30000,"sneka@gmail.com","Nagpur"),
				new Employee("23jan19","Janani","Engineer Trainee","Female","MBRDI",30000,"janani@gmail.com","Pune"),
				new Employee("23dha19","Dharshini","System Analyst","Female","Divum",30000,"dharshini@gmail.com","Bangalore"),
				new Employee("23san19","Sankar","Software Engineer","Male","MBRDI",30000,"sankar@gmail.com","Mumbai"),
				new Employee("23kav19","Kavi","Data Analyst","Male","Amazon",30000,"kavi@gmail.com","Chennai"),
				new Employee("23pra19","Pramila","Engineer Trainee","Female","Amazon",30000,"pramila@gmail.com","Bangalore")
				));
	}
	
}