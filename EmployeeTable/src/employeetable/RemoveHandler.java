package employeetable;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.handlers.HandlerUtil;

public class RemoveHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
        TableViewer tableViewer = (TableViewer) HandlerUtil.getActivePart(event).getSite().getSelectionProvider();

        if (tableViewer != null && selection != null) {
            for (Iterator<?> iterator = selection.iterator(); iterator.hasNext();) {
                Object element = iterator.next();
                tableViewer.remove(element);
            }
        }

        
        return null;
    }
}