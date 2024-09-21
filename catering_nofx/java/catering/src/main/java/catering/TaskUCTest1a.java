package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.task.SummarySheet;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TaskUCTest1a {
    public static void main(String[] args) {
        try {
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            System.out.println("\nTEST BEFORE SETTING CURRENT SUMMARY SHEET");
            System.out.println(CatERing.getInstance().getTaskManager().getCurrentSummarySheet());
            System.out.println("\nTEST AFTER OPENING SUMMARY SHEET");
            ArrayList<SummarySheet> sumSheets = CatERing.getInstance().getTaskManager().getSumSheets();
            CatERing.getInstance().getTaskManager().openSummarySheet(sumSheets.get(0));
            System.out.println(CatERing.getInstance().getTaskManager().getCurrentSummarySheet());
        } catch (UseCaseLogicException e) {
            System.out.println("Errore di logica nello use case");
        }
    }
}
