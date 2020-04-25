package edu.umuc.nbonnin.treesort;

import edu.umuc.nbonnin.parsers.FracParser;
import edu.umuc.nbonnin.parsers.IntParser;
import edu.umuc.nbonnin.parsers.StudentParser;

import java.util.ArrayList;

public abstract class TreeFactory {

    public static RedBlackTree<Integer, Integer> newIntegerTree(String list) {
        Integer[] intList = IntParser.parse(list);
        RedBlackTree<Integer, Integer> intTree = new RedBlackTree<>();
        for (Integer number : intList) {
            intTree.insert(number, number);
        }
        return intTree;
    }

    public static RedBlackTree<FracType, FracType> newFracTypeTree(String list) {
        FracType[] fracTypeList = FracParser.parse(list);
        RedBlackTree<FracType, FracType> fracTree = new RedBlackTree<>();
        for (FracType number : fracTypeList) {
            fracTree.insert(number, number);
        }
        return fracTree;
    }

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
