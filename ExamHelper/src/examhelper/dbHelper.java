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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.log4j.Logger;

/**
 * question class with two strings
 * question and subject
 * @author David
 */
class Question{
    String question;
    String subject;
    
    /**
     * Question for Constructor.
     * @param sub subject 
     * @param ques question
     */
    Question(String sub, String ques){
        subject = sub;
        question = ques;
    }
    
    
    /**
     * to string method
     * @return String to be printed
     */
    public String toString(){
        return("Sub: "+subject+"\nQues: "+question);
    }
}


/**
 * dbHelper class
 * create to get access to database
 * @author David
 */
public class dbHelper{
        
    public static Connection c = null;
    public static String subTable = "Subject";
    public static String quesTable = "Question";
    public static String dbName = "ExamHelperDB";
    public static Logger logger = Logger.getLogger(ScreensFramework.class);
    
    
    /***
     * dbConstructor 
     */
    dbHelper(){
        connectToDB();
        createPopTables();
    }
    
    
    /***
     * @post should connect to database after or return error
     * @return boolean as to whether or not database connects
     */
    public static boolean connectToDB(){
        
        c = null;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + dbName);
	} catch (Exception e) {
	    logger.error(e.getClass().getName() + ": " + e.getMessage());
            return false;
	}
        
        logger.info("Opened database successfully");
        return true;
        
    }
    
    /***
     * 
     * @return Map with an integer value that represents the id of the question
     *         and a Question object associated with it
     */
    public static Map<Integer,Question> getAllQuestions(){
        // empty map to contain all questions and ids
        Map<Integer,Question> questions = new HashMap<Integer,Question>();

        Statement stmt = null;
        
        try{
            stmt = c.createStatement();
            String sql = "select * from (select * from question,subject where question.sid = subject.id);";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Question q = new Question(rs.getString("sub"),rs.getString("ques"));
                questions.put(Integer.parseInt(rs.getString("qid")),q);
            }
        }catch(Exception e){
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        
        
        return questions;
    }
    
    
    /***
     * creates and populates tables for sqlite dB
     * can be called multiple times without adding duplicate questions or
     * creating duplicate tables
     * @post should always have tables afterwards
     */
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
            Question q10 = new Question("Rob Lowe", "How many Lowes could Rob Lowe rob if Rob Lowe could rob Lowes?");
            Question q11 = new Question("Math", "How many apostrophies would you like in your salad?");
            Question q12 = new Question("John The Legend Barr", "What is John Barrs favorite soft drink?");
            Question q13 = new Question("John The Legend Barr", "What branch of the military was John Barr in?");
            Question q14 = new Question("John The Legend Barr", "John Barr was once challeneged to a pull up competetion by a fellow student\n. John barr proceeded to kick the students ass... how many pull ups did John Barr do?");
            Question q15 = new Question("John The Legend Barr", "John Barr competes in a special race once a year. What is that race?");
            Question q16 = new Question("John The Legend Barr", "John Barr is so amazing that he even has a form of alcohol named after him. What type of alcoholic beverage is named after him?");
            Question q17 = new Question("Backpacks", "who is the best backpack of them all?");
            Question q18 = new Question("Rob Lowe", "What amazing show was Rob Lowe on? (hint: its totally Parks and Rec");
            Question q19 = new Question("Algorithms", "Why is it that Ali wants you to learn LaTeX when no one else uses it?");
            Question q20 = new Question("Data Structures", "how much data can you fit into a strucutre?");
            
            addQuestion(q1);
            addQuestion(q2);
            addQuestion(q3);
            addQuestion(q4);
            addQuestion(q5);
            addQuestion(q6);
            addQuestion(q7);
            addQuestion(q8);
            addQuestion(q9);
            addQuestion(q10);
            addQuestion(q11);
            addQuestion(q12);
            addQuestion(q13);
            addQuestion(q14);
            addQuestion(q15);
            addQuestion(q16);
            addQuestion(q17);
            addQuestion(q18);
            addQuestion(q19);
            addQuestion(q20);
            
            
        } catch (Exception e){ 
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    
    
    /***
     * 
     * @param numQues number of questions you're requesting
     * @param subject subject to grab questions from
     * @return boolean whether it's possible or not
     */
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
    
    /**
     * deletes all questions and resets IDs
     * @post id's should be reset
     * @post there should be no questions left
     */
    public static void deleteAll(){
        Statement stmt = null;
        
        try{
            stmt = c.createStatement();
            String sql = "delete from question;\n" +
                    "delete from subject;\n" +
                    "delete from sqlite_sequence where name='question';\n" +
                    "delete from sqlite_sequence where name='subject';";
            stmt.executeUpdate(sql);
        } catch (Exception e){
            logger.fatal(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
    }
    
    /***
     * 
     * @pre number of questions for subject is valid (tested with func "valid()")
     * @param num number of questions 
     * @param subject subject questions are coming from
     * @return arraylist of the questions
     */
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
    
    
    /***
     * gets all the subjects from the database
     * @return arraylist of the subjects
     */
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
    
    /***
     * adds a question the the database given a question object
     * @param ques question object (question and subject)
     */
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
//        Map m = getAllQuestions();
//        System.out.println(list);
//        
//        
//    }
    
}
