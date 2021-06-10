package de.hhbk.jahresprojekt.help;

import com.dlsc.workbenchfx.Workbench;

public class WorkbenchHolder {
    private static WorkbenchHolder workbenchHolder;

    private Workbench workbench;

    public static WorkbenchHolder getInstance(){
        if(workbenchHolder == null) workbenchHolder = new WorkbenchHolder();
        return workbenchHolder;
    }

    public Workbench getWorkbench() {
        return workbench;
    }

    public void setWorkbench(Workbench workbench) {
        this.workbench = workbench;
    }
}
