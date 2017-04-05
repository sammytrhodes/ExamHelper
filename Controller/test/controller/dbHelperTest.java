/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David
 */
public class dbHelperTest {
    
   

    /**
     * Test of addQuestion method, of class dbHelper.
     */
    @Test
    public void testAddQuestion() {
        System.out.println("addQuestion");
        Question ques = new Question("Memes","What is the meaning of life");
        dbHelper db = new dbHelper();
        db.connectToDB();
        db.createPopTables();
        db.addQuestion(ques);
        
        
        Statement stmt = null;
        try{
            //check to see if the subject was added or already exists
            stmt = db.c.createStatement();
            //find out if the subject related to the question exists already
            String sql = "select ID from subject where sub = '"+ques.subject+"';";
            // select * from subject where sub = 'algorithms');
            int subID = 0;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                subID = rs.getInt("ID");
            }
            
            
            //check to see if the question was added or already exists
            sql = "select qID from question where sid = "+subID+" and ques = \""+ques.question+"\";";
            //select qid from question where sid = 2 and ques = "What is a question?";
            int qID = 0;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                qID = rs.getInt("qID");
            }
            
            
            
            
            //assert that the subID shouldn't be 0 because then the subject for the question you added doesn't exist
            assertNotEquals(0, subID);
            assertNotEquals(0, qID);
        }
        catch(Exception e){
            System.out.println("Couldn't connect to database");
        }
        
    }
    
}
