package bgs.formalspecificationide.LogicFormulaGenerator;

import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

class Startup {
    public static void main(String[] args) throws IOException, ParseException {
        String input = "Seq(a, Seq(Concur(b,c,d), ConcurRe(e,f,g)))";

        System.out.println("LTL:");
        String ltl = program(input, "LTL");
        System.out.println(ltl);
        System.out.println();

        System.out.println("FOL:");
        String fol = program(input, "FOL");
        System.out.println(fol);
    }

    private static String program(String input, String logicType) throws IOException, ParseException {
        input = input.replaceAll("\\s+", "");
        System.out.println("Wejściowe wyrażenie to: " + input);
        String labeledExpression = ExpressionHelper.labelExpressions(input);
        System.out.println("Po labelowaniu: " + labeledExpression);
        PatternPropertySet set = new PatternPropertySet(logicType);
        return generateLogicalSpecifications(input, set, logicType);
    }

    public static String generateLogicalSpecifications(String pattern, PatternPropertySet propertySet, String logicType) {
        List<String> L = new LinkedList<>();
        String labeledExpression = ExpressionHelper.labelExpressions(pattern);
        int maxLabelValue = ExpressionHelper.getMaxedLabelPattern(labeledExpression);
        for (int l = maxLabelValue; l >= 1; l--) {
            int c = 1;
            PredefinedSetEntry pat = ExpressionHelper.getPat(labeledExpression, l, c, logicType);
            do {
                List<String> L2 = pat.getPossibleOutcomes();
                L2.remove(0);
                L2.remove(0);
                for (String arg : pat.passedArgs) {
                    if (ExpressionHelper.isNotAtomic(arg)) {
                        String cons = generateConsolidatedExpression(arg, 0, propertySet, logicType) + " | " + generateConsolidatedExpression(arg, 1, propertySet, logicType);
                        List<String> L2_cons = new LinkedList<String>();
                        for (String outcome : L2) {
                            L2_cons.add(outcome.replace(arg, cons));
                        }
                        L2 = L2_cons;
                    }
                }
                c++;
                L.addAll(L2);
                pat = ExpressionHelper.getPat(labeledExpression, l, c, logicType);
            } while (pat != null);
        }
        Set<String> set = new HashSet<>(L);
        L.clear();
        L.addAll(set);
        String connectedString = "";
        System.out.println("\nWynik: ");
        for (String lValue : L) {
            connectedString = connectedString + lValue + ", ";
            System.out.println(lValue);
        }
        return connectedString;
    }

    public static String generateConsolidatedExpression(String pattern, int type, PatternPropertySet propertySet, String logicType) {
        String ex = "";
        PredefinedSetEntry set = ExpressionHelper.getPredefinedSetEntryByExpression(pattern, logicType);
        List<String> possibleOutcomes = set.getPossibleOutcomes();
        String ini = possibleOutcomes.get(0);
        String fin = possibleOutcomes.get(1);
        possibleOutcomes.remove(0);
        possibleOutcomes.remove(0);
        List<String> argsToCheck;
        if (type == 0) { // 0 - ini, 1 - fin
            ex = ini;
        }

        if (type == 1) {
            ex = fin;
        }
        argsToCheck = ExpressionHelper.extractFromLabeled(pattern);
        for (String a : argsToCheck) {
            if (!argsToCheck.contains(a)) {
                continue;
            }
            if (ExpressionHelper.isNotAtomic(a)) {
                String con2 = generateConsolidatedExpression(a, type, propertySet, logicType);
                ex = ex.replace(a, con2);
            }
        }
        return ex;
    }
}