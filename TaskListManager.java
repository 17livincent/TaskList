/**
 * Test.java
 * 
 * @author Vincent Li <li.vincent0@gmail.com>
 * Interacts with a TaskList object
 * 
 * Features:
 *      Create lists and tasks
 *      Each task has a title, details, deadline, open/closed, and creation date
 *      Tasks can be edited (title, details, deadline, open/closed)
 *      Open tasks can be reordered
 *      Closed tasks are sorted by most recently closed first
 *      Closed tasks can be reopened
 */
import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Date;

public class TaskListManager implements Serializable {
    private ArrayList<TaskList> lists = new ArrayList<>(0);

    public static final String SERFILE = "tasklists.ser";
    private static final long serialversionUID = 129348938L;

    public static void main(String[] args) {
        TaskListManager manager = new TaskListManager();

        TaskList list2 = new TaskList("List 2");
        list2.addTask(new Task("Task 1", "Details of task 1", new Date()));
        list2.addTask(new Task("Task 2", "Details of task 2", null));
        list2.addTask(new Task("Task 3", "Details of task 3", new Date()));
        list2.closeTask(0); // close Task 1
        list2.closeTask(0); // close Task 2
        list2.openTask(2);  // open Task 1
        list2.deleteTask(1);    // delete Task 1

        manager.addList(list2);
        
        System.out.println(list2.toString(0));
        
        manager.saveLists();
        
        System.out.println("Deserialize");
        manager.readLists();
        System.out.println(manager.toString(0));  
    }

    /**
     * Add a TaskList object to lists
     */
    public void addList(TaskList list) {
        lists.add(list);
    }

    /**
     * Removes a TaskList from lists with the given index
     */
    public void removeList(int index) {
        lists.remove(index);
    }

    /**
     * Serializes the TaskLists
     */
    public void saveLists() {
        try {
            FileOutputStream fileOut = new FileOutputStream(SERFILE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(lists);

            objectOut.close();
            fileOut.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the TaskLists
     */
    public void readLists() {
        try {
            FileInputStream fileIn = new FileInputStream(SERFILE);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            ArrayList<TaskList> tempTasks = (ArrayList<TaskList>)objectIn.readObject();

            lists.clear();
            lists = (ArrayList<TaskList>)tempTasks.clone();

            objectIn.close();
            fileIn.close();
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a printable string of all task lists and their tasks.
     * Codes:
     * ALL: 0
     * OPEN: 1
     * CLOSED: 2
     */
    public String toString(int code) {
        String allTasks = "";

        for(int i = 0; i < lists.size(); i++) {
            allTasks += lists.get(i).toString(code);
        }

        return allTasks;
    } 
}
