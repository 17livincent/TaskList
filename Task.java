/**
 * Task.java
 * 
 * @author Vincent Li <li.vincent0@gmail.com>
 * Represents a task.
 */
import java.util.Date;

public class Task implements java.io.Serializable {

    private String title;       // the name of the task
    private String details;     // the description of the task
    private Date deadlineDate;  // when this task should be completed

    private boolean open;       // this task hasn't been completed - true or false
    private Date initDate;      // when this task was created

    /**
     * constructor with just title
     * @param title cannot be blank and must be unique
     */
    public Task(String title) {
        this.title = title.trim();

        this.initDate = new Date();
        this.open = true;
    }

    /**
     * full constructor
     * @param title cannot be blank and must be unique
     */
    public Task(String title, String details, Date deadlineDate) {
        this.title = title.trim();
        this.details = details.trim();
        this.deadlineDate = deadlineDate;

        this.initDate = new Date();
        this.open = true;
    }

    /**
     * Set the task as completed
     */
    public void setClose() {
        this.open = false;
    }

    /**
     * Reset the task as incomplete
     */
    public void setOpen() {
        this.open = true;
    }

    /**
     * Returns true if the task is overdue, or false otherwise
     */
    public boolean isOverdue() {
        return deadlineDate.before(new Date());
    }

    public String toString() {
        String string = "";
        string += "Title: " + title + "\n" 
            + "- Details: " + details + "\n"
            + "- Deadline: " + ((deadlineDate != null) ? deadlineDate.toString(): "None") + "\n"
            + "- Created on: " + initDate.toString() + "\n"
            + "- Status: " + ((open) ? "OPEN": "CLOSED") + "\n";

        return string;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getTitle() {
        return title;
    }
    public String getDetails() {
        return details;
    }
    public Date getDeadlineDate() {
        return deadlineDate;
    }
    public Date getInitDate() {
        return initDate;
    }
    public boolean isOpen() {
        return open;
    }
}