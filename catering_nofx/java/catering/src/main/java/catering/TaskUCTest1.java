package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.recipe.Recipe;
import catering.businesslogic.shift.Shift;
import catering.businesslogic.task.SummarySheet;
import catering.businesslogic.user.User;

import java.util.ArrayList;

public class TaskUCTest1 {
    public static void main(String[] args) {
        try {
            System.out.println("TEST FAKE LOGIN");
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());

            System.out.println("\nTEST CREATE SUMMARY SHEET");
            ArrayList<EventInfo> events = CatERing.getInstance().getEventManager().getEventInfo();
            EventInfo e = events.get(0);
            ServiceInfo s = e.getServices().get(0);
            SummarySheet sumSheet = CatERing.getInstance().getTaskManager().createSummarySheet(s,e);
            System.out.println(sumSheet);

            System.out.println("\nTEST ADD TASK TO SUMMARY SHEET");
            CatERing.getInstance().getTaskManager().addTask(sumSheet.getId());
            CatERing.getInstance().getTaskManager().addTask(sumSheet.getId());
            System.out.println(sumSheet);

            System.out.println("\nTEST CONSULT SHIFT TABLE");
            ArrayList<Shift> shifts = CatERing.getInstance().getShiftManager().loadAllShift();
            System.out.println(shifts);

            System.out.println("\nTEST ASSIGN TASK");
            ArrayList<Recipe> recipes = CatERing.getInstance().getRecipeManager().getRecipes();
            Recipe r1 = recipes.get(0);
            Recipe r2 = recipes.get(1);
            User c1 = CatERing.getInstance().getUserManager().getUser("Marinella");
            User c2 = CatERing.getInstance().getUserManager().getUser("Guido");
            CatERing.getInstance().getTaskManager().assignTask(sumSheet.getTaskList().get(0),r1, shifts.get(0),c1);
            System.out.println(sumSheet);

            System.out.println("\nTEST SET TASK DATA");
            CatERing.getInstance().getTaskManager().setTaskData(sumSheet.getTaskList().get(0),30,50,"5 vassoi");
            System.out.println(sumSheet);

        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
