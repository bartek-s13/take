/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.backing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author bartek
 */
@Named(value = "managedBean")
@RequestScoped
public class ManagedBean {
    
    private int addend1;
    private int addend2;
    private int sum;
    private Date date;
    private List<Student> students;

    /**
     * Creates a new instance of ManagedBean
     */
    public ManagedBean() {
        this.date = new Date();
        students = new ArrayList<>();

        students.add(new Student("Marcel", "Krupa", 3.0));
        students.add(new Student("Konrad", "Sikora", 4.0));
        students.add(new Student("Karol", "Zalewski", 5.0));
        students.add(new Student("Cyprian", "Witkowski", 3.5));
        students.add(new Student("Nina", "Marciniak", 4.5));
        students.add(new Student("Iza", "Krajewska", 5.0));
        students.add(new Student("Maja", "Maciejewska", 3.3));
        students.add(new Student("Aleksandra", "Walczak", 3.8));
        students.add(new Student("Karolina", "Nowak", 3.7));
        students.add(new Student("Franciszek", "Chmielewski", 4.7));
        students.add(new Student("Jakub", "Kaźmierczak", 4.6));
        students.add(new Student("Dariusz", "Krawczyk", 3.8));
        students.add(new Student("Ida", "Kowalczyk", 4.9));
        students.add(new Student("Zofia", "Błaszczyk", 3.3));
        students.add(new Student("Joanna", "Marciniak", 4.4));
    }

    /**
     * @return the addend1
     */
    public int getAddend1() {
        return addend1;
    }

    /**
     * @param addend1 the addend1 to set
     */
    public void setAddend1(int addend1) {
        this.addend1 = addend1;
    }

    /**
     * @return the addend2
     */
    public int getAddend2() {
        return addend2;
    }

    /**
     * @param addend2 the addend2 to set
     */
    public void setAddend2(int addend2) {
        this.addend2 = addend2;
    }
    /**
     * @return the sum
     */
    public int getSum() {
        return sum;
    }

    /**
     * @param sum the sum to set
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void calculate() {
        this.setSum(this.getAddend1() + this.getAddend2());
        showInfo();
    }
    
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void showInfo() {
        String message = getAddend1() + " + " + getAddend2() + " = " + getSum();
        addMessage(FacesMessage.SEVERITY_INFO, message, "Addition result");
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
}
