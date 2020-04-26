package edu.umuc.nbonnin.treesort;

/*
 * Skeleton Class as a proof of concept
 * No real comments as it isn't really graded
 */
public class Student {

    //Instance variables
    private String lastName, firstName;
    private int gradeLevel, studentID;
    private double gpa;

    //Constructors to assign instance variables
    public Student(
            String lastName,
            String firstName,
            int gradeLevel,
            int studentID,
            double gpa
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gradeLevel = gradeLevel;
        this.studentID = studentID;
        this.gpa = gpa;
    }

    //Overridden tostring method
    public String toString() {
        return firstName + " " + lastName;
    }

    //Below are all of the getters and setters
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
