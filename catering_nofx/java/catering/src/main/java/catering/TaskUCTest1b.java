package catering;

import catering.businesslogic.CatERing;
import catering.businesslogic.UseCaseLogicException;
import catering.businesslogic.event.EventInfo;
import catering.businesslogic.event.ServiceInfo;
import catering.businesslogic.task.SummarySheet;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class TaskUCTest1b {
    public static void main(String[] args) {
        try {
            CatERing.getInstance().getUserManager().fakeLogin("Lidia");
            ArrayList<SummarySheet> sumSheets = CatERing.getInstance().getTaskManager().getSumSheets();
            System.out.println("\nALL SUMMARY SHEETS BEFORE CREATION");
            System.out.println(sumSheets);

            System.out.println("\nALL SUMMARY SHEETS AFTER CREATION");
            ArrayList<EventInfo> events = CatERing.getInstance().getEventManager().getEventInfo();
            EventInfo e = events.get(2);
            ServiceInfo s = e.getServices().get(0);
            SummarySheet sumSheet = CatERing.getInstance().getTaskManager().createSummarySheet(s,e);
            sumSheets = CatERing.getInstance().getTaskManager().getSumSheets();
            System.out.println(sumSheets);

            CatERing.getInstance().getTaskManager().deleteSummarySheet(sumSheet);

        } catch (UseCaseLogicException e) {
            e.printStackTrace();
            System.out.println("Errore di logica nello use case");
        }

    }
}
