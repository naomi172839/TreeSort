package edu.umuc.nbonnin.treesort;

public class Student {

    private String lastName, firstName;
    private int gradeLevel, studentID;
    private double gpa;

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

    public String toString() {
        return firstName + " " + lastName;
    }

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
