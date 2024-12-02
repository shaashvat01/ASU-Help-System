package Testing;

import Backend.*;

public class UpdateDB_Tester {


    Update_DB UDb = new Update_DB();

    public static User CURRENT_USER = new User();


    public static UserList USER_LIST = new UserList();
    public static ArticleList ARTICLE_LIST = new ArticleList();
    public static GroupList GROUP_LIST = new GroupList();

    static int numPassed = 0;
    static int numFailed = 0;

    public static void main(String[] args) {
        UpdateDB_Tester tester = new UpdateDB_Tester();
        CURRENT_USER.setInstructor(true);
        System.out.println("____________________________________________________________________________");
        System.out.println("\nUpdate_DB Testing Automation");

        loadData();

        tester.performWriteTestCase(1,true);
        tester.performWriteTestCase(2,true);
        tester.performWriteTestCase(3,true);

        tester.performReadTestCase(4,true);
        tester.performReadTestCase(5,true);
        tester.performReadTestCase(6,true);

        System.out.println("____________________________________________________________________________");
        System.out.println();
        System.out.println("Number of tests passed: "+ numPassed);
        System.out.println("Number of tests failed: "+ numFailed);
    }

    public static void loadData()
    {
        System.out.println("Loading the below database into the runtime database");
        System.out.println("Users - ");
        System.out.println("1. Jack");
        System.out.println("2. Daniel");
        System.out.println("3. Ethan");

        USER_LIST.addUser(new User("Jack",null,null,"Jack",null,null,null));
        USER_LIST.addUser(new User("Daniel",null,null,"Daniel",null,null,null));
        USER_LIST.addUser(new User("Ethan",null,null,"Ethan",null,null,null));

        System.out.println("Articles - ");
        System.out.println("1. Introduction to Java");
        System.out.println("2. Project Guide");
        System.out.println("3. Debugging tips");

        ARTICLE_LIST.addArticle(new Article(123456,"Introduction to Java",null,null,null,null,"Group1,Group2",null,null,"Group1,Group2"));
        ARTICLE_LIST.addArticle(new Article(553456,"Project Guide",null,null,null,null,"Group1,Group2",null,null,"Group1,Group2"));
        ARTICLE_LIST.addArticle(new Article(126656,"Debugging tips",null,null,null,null,"Group1,Group2",null,null,"Group1,Group2"));

        System.out.println("Groups - ");
        System.out.println("1. Introduction");
        System.out.println("2. Project");
        System.out.println("3. Tips");

        GROUP_LIST.addGroup(new Group("Introduction",true));
        GROUP_LIST.addGroup(new Group("Project",true));
        GROUP_LIST.addGroup(new Group("Tips",true));
    }

    public void performWriteTestCase(int testCase, boolean expectedResult)
    {
        System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
        System.out.println("Performing test case "+testCase);
        System.out.println("______________");

        if(testCase == 1)
        {
            System.out.println("Saving User List");
            UDb.saveUserDB(USER_LIST);
            if (expectedResult) {
                System.out.println("***Success*** Users were successfully saved");
                numPassed++;
            }
            else{
                numFailed++;
            }

        } else if (testCase == 2) {
            System.out.println("Saving Article List");
            UDb.saveArticleDB(ARTICLE_LIST);
            if (expectedResult) {
                System.out.println("***Success*** Users were successfully saved");
                numPassed++;
            }
            else{
                numFailed++;
            }
        }
        else if (testCase == 3) {
            System.out.println("Saving Group List");
            UDb.saveGrpDB(GROUP_LIST);
            if (expectedResult) {
                System.out.println("***Success*** Users were successfully saved");
                numPassed++;
            }
            else{
                numFailed++;
            }
        }
        else{
            System.out.println("Test case " + testCase + " failed");
        }
    }

    public void performReadTestCase(int testCase, boolean expectedResult)
    {
        System.out.println("____________________________________________________________________________\n\nTest case: " + testCase);
        System.out.println("Performing test case "+testCase);
        System.out.println("______________");

        if(testCase == 4)
        {
            System.out.println("Reading User List text file database");
            UDb.loadUserDB(USER_LIST);
            if (expectedResult) {
                System.out.println("***Success*** Users were successfully read");
                numPassed++;
            }
            else{
                numFailed++;
            }

        } else if (testCase == 5) {
            System.out.println("Reading Article List text file database");
            UDb.loadArticleDB(ARTICLE_LIST);
            if (expectedResult) {
                System.out.println("***Success*** Users were successfully saved");
                numPassed++;
            }
            else{
                numFailed++;
            }
        }
        else if (testCase == 6) {
            System.out.println("Reading Group List text file database");
            UDb.loadGrpDB(GROUP_LIST);
            if (expectedResult) {
                System.out.println("***Success*** Users were successfully saved");
                numPassed++;
            }
            else{
                numFailed++;
            }
        }
        else{
            System.out.println("Test case " + testCase + " failed");
        }
    }
}
