package catering.persistence;

import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.task.Task;
import catering.businesslogic.task.TaskEventReceiver;

public class TaskPersistence implements TaskEventReceiver {
    @Override
    public void updateCreatedSummarySheet(SummarySheet sumSheet) {
        SummarySheet.saveNewSummarySheet(sumSheet);
    }

    @Override
    public void updateDeletedSummarySheet(SummarySheet sumSheet) {
        SummarySheet.deleteSumSheetFromDB(sumSheet);
    }

    @Override
    public void updateAddedTask(Task task) {
        Task.saveNewTask(task);
    }

    @Override
    public void updateAssignedTask(Task task) {
        Task.saveNewAssignement(task);
    }

    @Override
    public void updatedEditedAssignement(Task task) {
        Task.saveAssignementEdit(task);
    }

    @Override
    public void updatedSetTaskCompleted(Task task) {
        Task.saveSetTaskCompleted(task);
    }

    @Override
    public void updatedSetTaskData(Task task) {
        Task.saveTaskData(task);
    }

    public void updatedDeletedCookFromTask(Task task) {

    }
}
