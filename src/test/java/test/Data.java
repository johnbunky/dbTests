package test;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

class Data {
     static  String FILENAME = "C:\\Users\\User\\Desktop\\filename.txt";
     @NotNull
     @Contract(pure = true)
     static String getMainQuery(String previousDate){
        return "use OneTouchDirect_Activity\n" +
                   "select * from CallHistory where\n" +
                   //"CallDate > '2018-03-19T00:00:00' \n" +
                   "CallDate > '" + previousDate + "T00:00:00' \n" +
                   "--and Len(UserID) = 4\n";
    }
    static String and = " AND (\n";

    static String mainFilter = "  LeadiD IS NULL OR \n" +
            "  CallID IS NULL OR \n" +
            "  AfterCallDuration IS NULL OR \n" +
            "  ScriptOutcome IS NULL \n";

    static String leadIdFilter = "  LeadiD IS NULL AND \n" +
            "  CallID IS NOT NULL AND \n" +
            "  AfterCallDuration IS NOT NULL AND \n" +
            "  ScriptOutcome IS NOT NULL \n";

    static String callIdFilter = "  CallID IS NULL AND \n" +
            "  AfterCallDuration IS NOT NULL AND \n" +
            "  ScriptOutcome IS NOT NULL \n";

    static String afterCallFilter = "  CallID IS NOT NULL AND \n" +
            "  AfterCallDuration IS NULL AND \n" +
            "  ScriptOutcome IS NOT NULL \n";

    static String scriptOutcomeFilter = "  CallID IS NOT NULL AND \n" +
            "  AfterCallDuration IS NOT NULL AND \n" +
            "  ScriptOutcome IS NULL \n";

    static String scriptOutcomePlusCallIDFilter = "  CallID IS NULL AND \n" +
            "  AfterCallDuration IS NOT NULL AND \n" +
            "  ScriptOutcome IS NULL \n";

    static String afterCallPlusScriptOutcomeFilter = "  CallID IS NULL AND \n" +
            "  AfterCallDuration IS NULL AND \n" +
            "  ScriptOutcome IS NOT NULL \n";

    static String afterCallPlusCallIDFilter = "  CallID IS NOT NULL AND \n" +
            "  AfterCallDuration IS NULL AND \n" +
            "  ScriptOutcome IS  NULL \n";

    static String afterCallPlusscriptOutcomePlusCallIDFilter = "  CallID IS NULL AND \n" +
            "  AfterCallDuration IS NULL AND \n" +
            "  ScriptOutcome IS  NULL \n";

    static String or = "OR";

    static String totalDurationFilter = " (TotalDuration <> TalkDuration + AfterCallDuration + QueueDuration + " +
            "HoldDuration + IVRDuration + VDNDuration)";

    static String orderBy = "  ) \n order by CallHistoryID DESC";

    //**************************** Connnection Data *****************************************

    static String userName = "sa";

    static String password = "qwe123*";

    static String dbURL = "jdbc:sqlserver://10.217.153.98:1433;databaseName=OneTouchDirect_Activity;" +
            "Integrated Security=False";

    //String userName = "euuser01";
    //String password = "<Uu$3r9W20T6";
    //String dbURL = "jdbc:sqlserver://Tpa-dwsqlcl01\\dwsqlcl01:1433;databaseName=OneTouchDirect_Activity;" +
    //"Encrypt=False;Integrated Security=False";

}
