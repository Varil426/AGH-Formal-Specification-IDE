package bgs.formalspecificationide.LogicFormulaGenerator;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class PredefinedSetEntry {
    Pattern pattern;
    String identifier;
    int argNumber;
    String[] possibleOutcomes;
    List<String> passedArgs;


    PredefinedSetEntry(String identifier, int argNumber, String[] possibleOutcomes) {
        String pattern = identifier;
        pattern += "\\(";
        for (int i = 0; i < argNumber; i++) {
            pattern += ".+,";
        }
        pattern += "\\)";
        this.pattern = Pattern.compile(pattern);
        this.identifier = identifier;
        this.argNumber = argNumber;
        this.possibleOutcomes = possibleOutcomes;
        this.passedArgs = new LinkedList<>();
    }

    public void PassArguments(List<String> args) {
        passedArgs = new LinkedList();
        if (args.size() != argNumber) {
            throw new IllegalArgumentException("Wrong argumentNumber");
        }
        for (String arg : args) {
            passedArgs.add(arg);
        }
    }


    public List<String> getPossibleOutcomes() {
        if (passedArgs.size() > 0) {
            List<String> outcomes = new LinkedList<>();
            for (String outcome : possibleOutcomes) {
                String outcomeWithParams = outcome;
                for (int i = 0; i < passedArgs.size(); i++) {
                    outcomeWithParams = outcomeWithParams.replace("arg" + i, passedArgs.get(i));
                }
                outcomes.add(outcomeWithParams);
            }
            return outcomes;
        } else {

            return Arrays.asList(possibleOutcomes);
        }
    }
}
