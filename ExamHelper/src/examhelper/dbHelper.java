/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examhelper;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 *
 * @author David
 */

class Question{
    String question;
    String subject;
    
    Question(String sub, String ques){
        subject = sub;
        question = ques;
    }
}

public class dbHelper{
        
    public static Connection c = null;
    public static String subTable = "Subject";
    public static String quesTable = "Question";
    public static String dbName = "ExamHelperDB";
    public static Logger logger = Logger.getLogger(ScreensFramework.class);
    
    public static void connectToDB(){
        
        c = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dbName);
	} catch (Exception e) {
	    System.err.println(e.getClass().getName() + ": " + e.getMessage());
	    System.exit(0);
	}
        
        logger.info("Opened database successfully");
        
    }
    
    
    public static void createPopTables(){
        Statement stmt = null;
        
        try{
            stmt = c.createStatement();
            String sql = "create table if not exists subject(ID integer primary key autoincrement, sub text not null unique);\n";
            sql += "create table if not exists question(qID integer primary key autoincrement, sID int not null, ques text not null unique);\n";
            stmt.executeUpdate(sql);
            
            //create sample questions to add
            //should automatically add subjects 
            Question q1 = new Question("Math","What is 5 x 5");
            Question q2 = new Question("Data Structures","Why are interfaces important when considering data structures?");
            Question q3 = new Question("Data Structures","How do you know the size of an array in C++?");
            Question q4 = new Question("Info and Rec","What is collaborative filtering?");
            Question q5 = new Question("Info and Rec","Give 4 examples of recommender systems as discussed in class");
            Question q6 = new Question("Algorithms","How does DFS work?");
            Question q7 = new Question("Intro to Comp Sci","Write the pseudocode for an addition calculator function that takes two parameters (number A and number B)");
            Question q8 = new Question("Intro to Comp Sci","What is a stack trace and why is it important?");
            Question q9 = new Question("Math","How many flip flops does toby wear?");
            
            addQuestion(q1);
            addQuestion(q2);
            addQuestion(q3);
            addQuestion(q4);
            addQuestion(q5);
            addQuestion(q6);
            addQuestion(q7);
            addQuestion(q8);
            addQuestion(q9);
            
        } catch (Exception e){ 
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    
    
    public static boolean valid(int numQues, String subject){
        Statement stmt = null;
        int numQuesAvail = 0;
        
        try{
            stmt = c.createStatement();
            String sql = "select count(*) from (select * from subject,question where subject.id = question.sid) where sub = \""+subject+"\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                numQuesAvail = rs.getInt("count(*)");
            }
        }catch(Exception e){
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        logger.info("questions request: "+numQues+"\nquestions available: "+numQuesAvail);
        return(numQues <= numQuesAvail);
    }
    
    
    public static void deleteAll(){
        Statement stmt = null;
        
        try{
            stmt = c.createStatement();
            String sql = "drop table question;\n" +
                    "drop table subject;\n";
            stmt.executeUpdate(sql);
        } catch (Exception e){
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }

    }
    
    public static ArrayList<String> getQuestions(int num, String subject){
        // gets list of all questions
        ArrayList<String>questions = new ArrayList<String>();
        // list of "num" questions that will be returned
        ArrayList<String>finalQues = new ArrayList<String>();
        Statement stmt = null;
        
        try{
            stmt = c.createStatement();
            String sql = "select ques from (select * from question,subject where question.sid = subject.id) where sub = \""+subject+"\";";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                questions.add(rs.getString("ques"));
            }
        }catch(Exception e){
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        // randomly choose "num" questions
        Random rand = new Random();
        int n;
        for(int i = 0; i < num; i++){
            n = rand.nextInt(questions.size());
            finalQues.add(questions.get(n));
            questions.remove(n);
        }
        
        return finalQues;
    }
    
    public static ArrayList<String> getSubjects(){
        ArrayList<String> subs = new ArrayList<String>();
        Statement stmt = null;
        
        try{
            stmt = c.createStatement();
            String sql = "select sub from subject;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                subs.add(rs.getString("sub"));
            }
        }catch(Exception e){
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        return subs;
    }
    
    public static void addQuestion(Question ques){
        Statement stmt = null;
        int numSubjects = 0;
        int numQuestions = 0;
        
        String question = ques.question;
        String subject = ques.subject;
        
        
        try{
//            // create the tables if they don't already exist
            stmt = c.createStatement();
            String sql = "create table if not exists subject(ID integer primary key autoincrement, sub text not null unique);\n";
            sql += "create table if not exists question(qID integer primary key autoincrement, sID int not null, ques text not null unique);\n";
            stmt.executeUpdate(sql);
          
            //find out if the subject related to the question exists already
            sql = "select ID from subject where sub = '"+subject+"';";
            // select * from subject where sub = 'algorithms');
            int subID = 0;
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                subID = rs.getInt("ID");
            }
            
            
            //if the subject doesn't exist add it into the subject table
            if (subID == 0){
                sql = "insert into subject values (null, '"+subject+"');";
                // insert into table subject values (null, 'algorithms');
                stmt.executeUpdate(sql);
                // get newID now that it's added into the table
                sql = "select ID from subject where sub = '"+subject+"';";
                // select * from subject where sub = 'algorithms');
                rs = stmt.executeQuery(sql);
                subID = rs.getInt("ID");
            }
            
            //make sure the question isn't already in the dB
            sql = "select qID from question where sid = "+subID+" and ques = \""+question+"\";";
            //select qid from question where sid = 2 and ques = "What is a question?";
            int qID = 0;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                qID = rs.getInt("qID");
            }

            if(qID == 0){//if the question doesn't already exists
                 //use the subID to insert the question into the question table
                sql = "insert into question values (null, "+subID+", '"+question+"');";
                //insert into table question values (null, 4, 'How does DFS work?');
                stmt.executeUpdate(sql);
            }
            else{
                logger.info("Question already added");
            }
                
   
            
        } catch (Exception e){ 
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        logger.info("Question added successfully");
    }

//    public static void main(String[] args) {
//        connectToDB();
////        createPopTables();
//        Question q = new Question("Memes", "What is the meaning of life");
//        addQuestion(q);
////        deleteAll();
//        createPopTables();
//        ArrayList list = getSubjects();
//        System.out.println(list);
//        
//        
//    }
    
}
