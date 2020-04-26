package edu.umuc.nbonnin.treesort;

import edu.umuc.nbonnin.parsers.FracParser;
import edu.umuc.nbonnin.parsers.IntParser;
import edu.umuc.nbonnin.parsers.StudentParser;

import java.util.ArrayList;

/*
 *      ****Tree Factory Class**
 *
 * TreeFactory is an attempt at the GOF factory pattern
 * Abstract, all methods must be called from a static context
 *
 * TODO: Add more comments
 */
public abstract class TreeFactory {

    /*
     *Takes a space delimited list of integers, returns an appropriate tree
     */
    public static RedBlackTree<Integer, Integer> newIntegerTree(String list) {
        Integer[] intList = IntParser.parse(list); //Parses the list
        RedBlackTree<Integer, Integer> intTree = new RedBlackTree<>(); //Creates a new tree
        for (Integer number : intList) {
            intTree.insert(number, number); //Add them all to the tree
        }
        return intTree;
    }

    /*
     * Takes a space delimited list of FracType objects, returns an appropriate tree
     * See above comments
     */
    public static RedBlackTree<FracType, FracType> newFracTypeTree(String list) {
        FracType[] fracTypeList = FracParser.parse(list);
        RedBlackTree<FracType, FracType> fracTree = new RedBlackTree<>();
        for (FracType number : fracTypeList) {
            fracTree.insert(number, number);
        }
        return fracTree;
    }

    /*
     * Takes a space and comma delimited list of students, returns an appropriate tree
     * see above comments
     */

    public static RedBlackTree<? extends Comparable<?>, Student> newStudentTree(String list, String key) {
        Student[] studentList = StudentParser.parse(list);
        switch (key) {
            case ("Grade Level"):
                RedBlackTree<Integer, Student> gradeLevelTree = new RedBlackTree<>();
                for (Student person : studentList) {
                    gradeLevelTree.insert(person.getGradeLevel(), person);
                }
                return gradeLevelTree;

            case ("Student ID"):
                RedBlackTree<Integer, Student> studentIDTree = new RedBlackTree<>();
                for (Student person : studentList) {
                    studentIDTree.insert(person.getStudentID(), person);
                }
                return studentIDTree;

            case ("GPA"):
                RedBlackTree<Double, Student> gpaTree = new RedBlackTree<>();
                for (Student person : studentList) {
                    gpaTree.insert(person.getGpa(), person);
                }
                return gpaTree;
            default:
                throw new NumberFormatException("(Student) Invalid Key Selection: " + key);
        }
    }

    /*
     * Detects the datatype based on errors thrown
     * Returns an appropriate typed tree
     */
    public static RedBlackTree<? extends Comparable<?>, ?> newGenericTree(String list) {
        ArrayList<String> errors = new ArrayList<>();
        StringBuilder errorMessage = new StringBuilder();
        try {
            return newFracTypeTree(list);
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }
        try {
            return newIntegerTree(list);
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }
        try {
            return newStudentTree(list, "GPA");
        } catch (NumberFormatException e) {
            errors.add(e.getMessage());
        }
        for (String error : errors) {
            errorMessage.append(error).append(" ");
        }
        throw new NumberFormatException(errorMessage.toString());
    }
}
