/**
 * TaskList.java
 * 
 * @author Vincent Li <li.vincent0@gmail.com>
 * Manages Task objects.
 */
import java.util.ArrayList;
import java.util.Date;

public class TaskList implements java.io.Serializable {
    private String listName;    // must be unique
    private ArrayList<Task> tasks = new ArrayList<>(0); // arraylist of Task objects
    private int numOfOpen = 0;

    public TaskList(String listName) {
        this.listName = listName;
    }

    /**
     * Add a new task to tasks.
     * If successful, return true.
     * Otherwise return false.
     */
    public boolean addTask(Task t) {
        boolean status = false;

        // if numOfOpen is 0 or there is no other task in the list that has its title
        if(numOfOpen == 0 || taskIsValid(t.getTitle())) {   // valid
            tasks.add(t);
            numOfOpen++;
            status = true;
        }

        return status;
    }

    /**
     * Checks if there is no task (open or closed) currently in the list with this taskTitle.
     * Returns true if so, false otherwise.
     */
    public boolean taskIsValid(String taskTitle) {
        boolean status = true;

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getTitle().equalsIgnoreCase(taskTitle)) {
                status = false;
                break;
            }
        }

        return status;
    }

    /**
     * Deletes the task at the index
     */
    public void deleteTask(int index) {
        if(tasks.get(index).isOpen()) numOfOpen--;

        tasks.remove(index);
    }

    /**
     * Returns the individual task at the index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Edits the title, details, or deadline of a task at the index
     * Input the original values for parameters that shouldn't be edited
     */
    public void editTask(int index, String title, String details, Date deadlineDate) {
        tasks.get(index).setTitle(title);
        tasks.get(index).setDetails(details);
        tasks.get(index).setDeadlineDate(deadlineDate);
    }

    /**
     * Moves the task from index1 to index2
     */
    public void moveTask(int index1, int index2) {
        Task task = tasks.get(index1);
        tasks.remove(index1);
        tasks.add(index2, task);
    }

    /**
     * Close the task at the index
     */
    public void closeTask(int index) {
        tasks.get(index).setClose();
        
        // update numOfOpen
        numOfOpen--;

        // move the closed task behind the open ones
        moveTask(index, numOfOpen);
    }

    /**
     * Open the task at the index
     */
    public void openTask(int index) {
        tasks.get(index).setOpen();

        // move the opened task behind the open ones
        moveTask(index, numOfOpen);

        // update numOfOpen
        numOfOpen++;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListName() {
        return listName;
    }

    /**
     * Returns the entire tasks arraylist
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns an arraylist of the open tasks
     */
    public ArrayList<Task> getOpenTasks() {
        ArrayList<Task> openTasks = new ArrayList<>(0);

        for(int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if(t.isOpen()) {
                openTasks.add(t);
            }
        }

        return openTasks;
    }

    /**
     * Returns an arraylist of the closed tasks
     */
    public ArrayList<Task> getClosedTasks() {
        ArrayList<Task> closedTasks = new ArrayList<>(0);

        for(int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if(!t.isOpen()) {
                closedTasks.add(t);
            }
        }

        return closedTasks;
    }

    /**
     * Returns a string representation of the task list
     * code values:
     * ALL: 0
     * OPEN: 1
     * CLOSED: 2
     */
    public String toString(int code) {
        String string = "TASK LIST: " + listName + "\n";

        ArrayList<Task> t;
        
        if(code == 0) {
            t = getAllTasks();
        }
       else if(code == 1) {
           t = getOpenTasks();
       }
       else if(code == 2) {
           t = getClosedTasks();
       }
       else {
           t = getAllTasks();
       }

        for(int i = 0; i < t.size(); i++) {
            string += t.get(i).toString();
        }

        return string;
    }
}
