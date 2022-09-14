package bgs.formalspecificationide.LogicFormulaGenerator;

import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class PatternPropertySet {
    List<PredefinedSetEntry> setEntries;

    PatternPropertySet(String logicType) throws IOException, ParseException {
        if (logicType.equals("FOL")) {
            setEntries = parseJsonFile("./pattern_rules/pattern_rules_FOL.json");
        } else if (logicType.equals("LTL")) {
            setEntries = parseJsonFile("./pattern_rules/pattern_rules_LTL.json");
        }
    }

    public PredefinedSetEntry findByIdentifier(String identifier) {
        PredefinedSetEntry searchedEntry = null;
        for (PredefinedSetEntry setEntry : setEntries) {
            if (setEntry.identifier.equalsIgnoreCase(identifier)) {
                searchedEntry = setEntry;
                break;
            }
        }
        return searchedEntry;
    }

    private LinkedList<PredefinedSetEntry> parseJsonFile(String filePath) throws IOException, ParseException {
        LinkedList<PredefinedSetEntry> predefinedSetEntries = new LinkedList<>();
        JSONParser parser = new JSONParser();
        JSONObject mainObject = (JSONObject) parser.parse(new FileReader(filePath));
        Set keySet = mainObject.keySet();
        for (Object o : keySet) {
            String key = (String) o;
            JSONObject ruleObject = (JSONObject) mainObject.get(key);
            int number_of_args = ((Long) ruleObject.get("number of args")).intValue();
            Object[] rulesObjectArray = ((JSONArray) ruleObject.get("rules")).toArray();
            String[] rules = new String[rulesObjectArray.length];
            for (int i = 0; i < rulesObjectArray.length; i++) {
                rules[i] = (String) rulesObjectArray[i];
            }
            predefinedSetEntries.add(new PredefinedSetEntry(key, number_of_args, rules));
        }
        return predefinedSetEntries;
    }
}
