package edu.umuc.nbonnin.parsers;

import edu.umuc.nbonnin.datatypes.DataType;

@FunctionalInterface
public interface DataParser {

    DataType[] parseCustom(String list);

}
