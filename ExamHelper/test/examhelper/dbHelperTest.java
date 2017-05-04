/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author David
 */
public class dbHelperTest {
    

    /**
     * Test of connectToDB method, of class dbHelper.
     */
    @Test
    public void testConnectToDB() {
        dbHelper db = new dbHelper();
        Assert.assertTrue(db.connectToDB());
        System.out.println("connectToDB");
    }

    
    /**
     * Test of createPopTables method, of class dbHelper.
     */
    @Test
    public void testCreatePopTables() {
        dbHelper db = new dbHelper();
        db.deleteAll();
        db.createPopTables();
        
        Statement stmt = null;
        //will hold all questions but there shouldn't be any
        ArrayList<String> questions = new ArrayList<String>();
        try{
            stmt = db.c.createStatement();
            String sql = "select * from question;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                questions.add(rs.getString("ques"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //should be 20 because thats how many questions are inserted for 
        Assert.assertEquals(20, questions.size());
    }

    
    /**
     * Test of valid method, of class dbHelper.
     */
    @Test
    public void testValid() {
        dbHelper db = new dbHelper();
        db.createPopTables();
        db.deleteAll();
        
        Assert.assertFalse(db.valid(3, "Rob Lowe"));
    }

    
    /**
     * Test of deleteAll method, of class dbHelper.
     */
    @Test
    public void testDeleteAll() {
        dbHelper db = new dbHelper();
        db.createPopTables();
        db.deleteAll();
        
        Statement stmt = null;
        //will hold all questions but there shouldn't be any
        ArrayList<String> questions = new ArrayList<String>();
        try{
            stmt = db.c.createStatement();
            String sql = "select * from (select * from question,subject where "
                    + "question.sid = subject.id) where sub = \"John The Legend Barr\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                questions.add(rs.getString("ques"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(0, questions.size());
    }

    
    /**
     * Test of getQuestions method, of class dbHelper.
     */
    @Test
    public void testGetQuestions() {
        dbHelper db = new dbHelper();
        db.deleteAll();
        db.createPopTables();
        
        Statement stmt = null;
        //will hold all questions that are actually in the database to compare
        ArrayList<String> questions = new ArrayList<String>();
        try{
            stmt = db.c.createStatement();
            String sql = "select ques from (select * from question,subject where "
                    + "question.sid = subject.id) where sub = \"John The Legend Barr\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                questions.add(rs.getString("ques"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        //test with one subject
        ArrayList<String> getQues = db.getQuestions(3, "John The Legend Barr");
        //make sure it returns the correct number
        Assert.assertEquals(3, getQues.size());
        for(int i = 0; i < getQues.size(); i++){
            String ques = getQues.get(i);
            //make sure that everything generated from getQuestions is in the database
            Assert.assertTrue(questions.contains(ques));
        }   
    }

    
    /**
     * Test of getSubjects method, of class dbHelper.
     */
    @Test
    public void testGetSubjects() {
        dbHelper db = new dbHelper();
        db.deleteAll();
        db.createPopTables();
        
        Statement stmt = null;
        //will hold all questions that are actually in the database to compare
        ArrayList<String> questions = new ArrayList<String>();
        try{
            stmt = db.c.createStatement();
            String sql = "select * from subject;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                questions.add(rs.getString("sub"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        ArrayList<String> getQues = db.getSubjects();
        //make sure the lists are the same size
        Assert.assertEquals(questions.size(), getQues.size());
        for(int i = 0; i < getQues.size(); i++){
            String ques = getQues.get(i);
            //make sure that everything generated from getSubject is everything in the database
            Assert.assertTrue(questions.contains(ques));
        }   
    }

    
    /**
     * Test of addQuestion method, of class dbHelper.
     */
    @Test
    public void testAddQuestion() {
        dbHelper db = new dbHelper();
        db.deleteAll();
        db.createPopTables();
        
        Question q = new Question("Eric Ghaly", "What is sitting on top of the glad "
                + "garbage bags in Erics closet right now? \nHint:(it might be bounty paper towels)");
        db.addQuestion(q);
        
        Statement stmt = null;
        try{
            stmt = db.c.createStatement();
            String sql = "select * from (select * from question,subject where "
                    + "question.sid = subject.id) where question = \"What is sitting "
                    + "on top of the glad garbage bags in Erics closet right now? "
                    + "\nHint:(it might be bounty paper towels)\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Assert.assertEquals("Eric Ghaly", rs.getString("sub"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
}
