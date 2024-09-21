package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.shift.Shift;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.task.Task;
import catering.businesslogic.task.TaskException;
import catering.businesslogic.user.User;

import java.util.ArrayList;

public class TaskUCTest5a {
    public static void main(String[] args) {
        try {
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            ArrayList<SummarySheet> sumSheets = CatERing.getInstance().getTaskManager().getSumSheets();
            CatERing.getInstance().getTaskManager().setCurrentSummarySheet(sumSheets.get(0));
            System.out.println("\nTEST SUMMARY SHEET BEFORE ASSIGNMENT EDIT");
            System.out.println(CatERing.getInstance().getTaskManager().getCurrentSummarySheet());

            ArrayList<Task> tasks = CatERing.getInstance().getTaskManager().getCurrentSummarySheet().getTaskList();
            ArrayList<Shift> shifts = CatERing.getInstance().getShiftManager().loadAllShift();
            User cook = CatERing.getInstance().getUserManager().getUser("Guido");
            CatERing.getInstance().getTaskManager().editAssignement(tasks.get(0),shifts.get(1),cook);
            System.out.println("\nTEST SUMMARY SHEET AFTER ASSIGNMENT EDIT");
            System.out.println(CatERing.getInstance().getTaskManager().getCurrentSummarySheet());
        } catch (TaskException e) {
            e.printStackTrace();
            System.out.println("Errore di logica nello use case");
        }
    }
}
