package catering.businesslogic.task;

public interface TaskEventReceiver {
    public void updateCreatedSummarySheet(SummarySheet sumSheet);

    public void updateDeletedSummarySheet(SummarySheet sumSheet);

    public void updateAddedTask(Task task);

    void updateAssignedTask(Task task);

    void updatedEditedAssignement(Task task);

    void updatedSetTaskCompleted(Task task);

    void updatedSetTaskData(Task task);

    void updatedDeletedCookFromTask(Task task);
}
