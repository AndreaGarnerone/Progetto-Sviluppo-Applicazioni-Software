package catering.businesslogic.task;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.shift.Shift;
import catering.businesslogic.user.User;
import catering.businesslogic.user.UserManager;
import catering.persistence.TaskPersistence;

import java.util.ArrayList;

public class TaskManager {
    private SummarySheet currentSummarySheet;

    private ArrayList<TaskEventReceiver> eventReceivers;
    public TaskManager() {
        eventReceivers = new ArrayList<>();
//      SummarySheet.loadAllSumSheets();
    }

    public ArrayList<SummarySheet> getSumSheets() {
        return SummarySheet.loadAllSumSheets();
    }

    public SummarySheet createSummarySheet(ServiceInfo service, EventInfo event) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }
        if (!event.isAssignedChef(user)) {
            throw new UseCaseLogicException();
        }
        SummarySheet sumSheet = new SummarySheet(service,event);
        this.setCurrentSummarySheet(sumSheet);
        this.notifyCreatedSummarySheet(sumSheet);
        return sumSheet;
    }

    public Task addTask(int sumSheet_id) {
        Task task = this.currentSummarySheet.addTask(sumSheet_id);
        this.notifyAddedTask(task);
        return task;
    }

    public void assignTask(Task task, Recipe recipe, Shift shift) throws UseCaseLogicException {
        assignTask(task,recipe,shift,null);
    }

    public void assignTask(Task task, Recipe recipe, Shift shift, User cook) throws UseCaseLogicException {
            this.currentSummarySheet.assignTask(task, recipe, shift, cook);
            this.notifyAssignedTask(task);
    }

    public void editAssignement(Task task, Shift shift, User cook) throws TaskException {
        this.currentSummarySheet.editAssignement(task, shift, cook);
        this.notifyEditedAssignement(task);
    }

    public void setTaskCompleted(Task task) {
        this.currentSummarySheet.setTaskCompleted(task);
        this.notifySetTaskCompleted(task);
    }

    public void setTaskData(Task task, int time, int portions, String amount) {
        this.currentSummarySheet.setTaskData(task,time,portions,amount);
        this.notifySetTaskData(task);
    }

    public void deleteSummarySheet(SummarySheet sumSheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }
        if (!sumSheet.getReferredEvent().isAssignedChef(user)) {
            throw new UseCaseLogicException();
        }
        this.currentSummarySheet = null;
        this.notifyDeletedSummarySheet(sumSheet);
    }

    private void notifySetTaskData(Task task) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updatedSetTaskData(task);
        }
    }

    private void notifySetTaskCompleted(Task task) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updatedSetTaskCompleted(task);
        }
    }

    private void notifyEditedAssignement(Task task) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updatedEditedAssignement(task);
        }
    }

    private void notifyAssignedTask(Task task) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updateAssignedTask(task);
        }
    }

    private void notifyAddedTask(Task task) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updateAddedTask(task);
        }
    }

    private void notifyDeletedSummarySheet(SummarySheet sumSheet) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updateDeletedSummarySheet(sumSheet);
        }
    }

    private void notifyCreatedSummarySheet(SummarySheet sumSheet) {
        for (TaskEventReceiver er : this.eventReceivers) {
            er.updateCreatedSummarySheet(sumSheet);
        }
    }

    public void setCurrentSummarySheet(SummarySheet sumSheet) {
        this.currentSummarySheet = sumSheet;
    }

    public void openSummarySheet(SummarySheet sumSheet) throws UseCaseLogicException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }
        if (!sumSheet.getReferredEvent().isAssignedChef(user)) {
            throw new UseCaseLogicException();
        }
        setCurrentSummarySheet(sumSheet);
    }

    public SummarySheet getCurrentSummarySheet() {
        return currentSummarySheet;
    }

    public void addEventReceiver(TaskPersistence taskPersistence) {
        this.eventReceivers.add(taskPersistence);
    }

}
