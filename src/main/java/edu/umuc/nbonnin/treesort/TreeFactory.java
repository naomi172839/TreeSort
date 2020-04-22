package edu.umuc.nbonnin.treesort;

import edu.umuc.nbonnin.parsers.FracParser;
import edu.umuc.nbonnin.parsers.IntParser;

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
        for (String error : errors) {
            errorMessage.append(error).append(" ");
        }
        throw new NumberFormatException(errorMessage.toString());
    }
}
