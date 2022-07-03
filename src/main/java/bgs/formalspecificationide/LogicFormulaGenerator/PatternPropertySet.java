package bgs.formalspecificationide.LogicFormulaGenerator;

import java.util.*;
import java.util.regex.Pattern;

public class PatternPropertySet {
    List<PredefinedSetEntry> setEntries;

    PatternPropertySet() {
        setEntries = new LinkedList<>();

        setEntries.add((new PredefinedSetEntry("Seq", 2, new String[]{
                "arg0", "arg1", "Exist(arg0)", "ForAll(arg0 => Exist(arg1))", "ForAll(~(arg0 ^ arg1))"
        })));
        setEntries.add(new PredefinedSetEntry("Branch", 3, new String[]{
                "arg0", "arg1 | arg2", "Exist(arg0)", "ForAll(arg0 => (Exist(arg1) ^ ~Exist(arg2) | (~Exist(arg1) ^ Exist(arg2)))", "ForAll(arg0 => Exist(arg1))", "ForAll(arg0 => Exist(arg2))", "ForAll(~(arg0 ^ arg1))", "ForAll(~(arg0 ^ arg2))"
        }));
        setEntries.add((new PredefinedSetEntry("BranchRe", 3, new String[]{
                "arg0 | arg1", "arg2", "(Exist(arg0) ^ ~Exist(arg1)) | (~Exist(arg0) ^ Exist(arg1))", "ForAll(arg0 | arg1 => Exist(arg2))", "ForAll(~(arg0 ^ arg2))", "ForAll(~(arg1 ^ arg2))"
        })));
        setEntries.add(new PredefinedSetEntry("Concur", 3, new String[]{
                "arg0", "arg1 | arg2", "Exist(arg0)", "ForAll(arg0 => Exist(arg1) ^ Exist(arg2))", "ForAll(~(arg0 ^ arg1))", "ForAll(~(arg0 ^ arg2))"
        }));
        setEntries.add(new PredefinedSetEntry("ConcurRe", 3, new String[]{
                "arg0 | arg1", "arg2", "Exist(arg0)", "Exist(arg1)", "ForAll(arg0 => Exist(arg2))", "ForAll(arg1 => Exist(arg2))", "ForAll(~(arg0 ^ arg2))", "ForAll(~(arg1 ^ arg2))"
        }));
    }

    public PredefinedSetEntry findByIdentifier(String identifier) {
        PredefinedSetEntry searchedEntry = null;
        for (PredefinedSetEntry setEntry : setEntries) {
            if (setEntry.identifier.toLowerCase().equals(identifier.toLowerCase())) {
                searchedEntry = setEntry;
                break;
            }
        }
        return searchedEntry;
    }
}
