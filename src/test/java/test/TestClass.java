package test;

import org.jetbrains.annotations.NotNull;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static test.Data.*;

public class TestClass {
    private static Connection connection;
    private static String previousDate = null;
    private static BufferedWriter bw = null;
    private static Statement statement;

    @BeforeClass
    public static void setUp() throws ClassNotFoundException, SQLException, IOException {

        bw = new BufferedWriter(new FileWriter(FILENAME));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        previousDate = dateFormat.format(yesterday());

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        connection = DriverManager.getConnection(dbURL, userName, password);
        statement = connection.createStatement();
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        connection.close();
    }

    @Test
    public void testExample() throws SQLException, IOException {

        bw.write(previousDate);
        bw.newLine();

        ResultSet rs = statement.executeQuery(getMainQuery(previousDate));
        counterWriter("The all records amount: ", bw, rs);

        rs = statement.executeQuery(getMainQuery(previousDate) +
                and + mainFilter + or + totalDurationFilter + orderBy);
        counterWriter("The all records with issues amount:  ", bw, rs);

        queryWithFilter(" -- There are TotalDuration issues: ", bw, statement,
                totalDurationFilter);
        queryWithFilter(" -- LeadID issue only: ", bw, statement,
                leadIdFilter);
        queryWithFilter(" -- СallID issue only: ", bw, statement,
                callIdFilter);
        queryWithFilter(" -- СallID + AfterCallduration issues only: ", bw, statement,
                afterCallFilter);
        queryWithFilter(" -- ScriptOutcome issue only: ", bw, statement,
                scriptOutcomeFilter);
        queryWithFilter(" -- СallID + ScriptOutcome issues only: ", bw, statement,
                scriptOutcomePlusCallIDFilter);
        queryWithFilter(" -- ScriptOutcome + AfterCallduration issues only: ", bw, statement,
                afterCallPlusScriptOutcomeFilter);
        queryWithFilter(" -- СallID + AfterCallduration issues only: ", bw, statement,
                afterCallPlusCallIDFilter);
        queryWithFilter(" -- СallID + ScriptOutcome + AfterCallduration issues only: ", bw, statement,
                afterCallPlusscriptOutcomePlusCallIDFilter);
    }

    private void queryWithFilter(String category,
                                 BufferedWriter bw, @NotNull Statement statement, String callIdFilter)
            throws SQLException, IOException {
        ResultSet rs = statement.executeQuery(getMainQuery(previousDate) +
                and + callIdFilter + orderBy);
            resultWriter(category, bw, rs);
    }
    private void resultWriter(String category,
                              BufferedWriter bw, @NotNull ResultSet rs) throws SQLException, IOException {
        int rowsCounter = 0;
        while (rs.next()) {
            bw.newLine();
            bw.write(rs.getString("UCID"));
            rowsCounter++;
        }
        if( rowsCounter == 0 ){ return;}
        bw.write(category + String.valueOf(rowsCounter));
        bw.newLine();
        bw.flush();
    }
    private void counterWriter(String category,
                               BufferedWriter bw, @NotNull ResultSet rs) throws SQLException, IOException {
        int rowsCounter = 0;
        while (rs.next()) {
            rowsCounter++;
        }
        bw.write(category + String.valueOf(rowsCounter));
        bw.newLine();
        bw.flush();
    }
    @NotNull
    private static Date yesterday(){
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
