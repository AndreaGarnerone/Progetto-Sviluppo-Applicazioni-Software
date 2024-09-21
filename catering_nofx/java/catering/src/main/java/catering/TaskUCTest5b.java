package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.task.Task;

import java.util.ArrayList;

public class TaskUCTest5b {
    public static void main(String[] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        ArrayList<SummarySheet> sumSheets = CatERing.getInstance().getTaskManager().getSumSheets();
        CatERing.getInstance().getTaskManager().setCurrentSummarySheet(sumSheets.get(0));
        System.out.println("\nTEST SUMMARY SHEET BEFORE SETTING TASK COMPLETION");
        System.out.println(CatERing.getInstance().getTaskManager().getCurrentSummarySheet());

        ArrayList<Task> tasks = CatERing.getInstance().getTaskManager().getCurrentSummarySheet().getTaskList();
        CatERing.getInstance().getTaskManager().setTaskCompleted(tasks.get(0));
        System.out.println("\nTEST SUMMARY SHEET AFTER SETTING TASK COMPLETION");
        System.out.println(CatERing.getInstance().getTaskManager().getCurrentSummarySheet());
    }
}
