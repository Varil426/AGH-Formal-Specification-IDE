package bgs.formalspecificationide.LogicFormulaGenerator;

import java.util.*;

class Startup {
    public static void main(String[] args) {
        String input = "Seq(a, Seq(Concur(b,c,d), ConcurRe(e,f,g)))";
        Scanner userInput = new Scanner(System.in);
        System.out.print("Podaj pattern: ");
        String userInpt = userInput.next();
        input = input.replaceAll("\\s+", "");
        if (userInpt.length() > 0)
            input = userInpt;
        System.out.println("Wejściowe wyrażenie to: " + input);
        String labeledExpression = ExpressionHelper.labelExpressions(input);
        System.out.println("Po labelowaniu: " + labeledExpression);
        PatternPropertySet set = new PatternPropertySet();
        generateLogicalSpecifications(input, set);
    }

    public static String generateLogicalSpecifications(String pattern, PatternPropertySet propertySet) {
        List<String> L = new LinkedList<>();
        String labeledExpression = ExpressionHelper.labelExpressions(pattern);
        int maxLabelValue = ExpressionHelper.getMaxedLabelPattern(labeledExpression);
        for (int l = maxLabelValue; l >= 1; l--) {
            int c = 1;
            PredefinedSetEntry pat = ExpressionHelper.getPat(labeledExpression, l, c);
            do {
                List<String> L2 = pat.getPossibleOutcomes();
                L2.remove(0);
                L2.remove(0);
                for (String arg : pat.passedArgs) {
                    if (!ExpressionHelper.isAtomic(arg)) {
                        String cons = generateConsolidatedExpression(arg, 0, propertySet) + " | " + generateConsolidatedExpression(arg, 1, propertySet);

                        List<String> L2_cons = new LinkedList<String>();
                        for (String outcome : L2) {
                            L2_cons.add(outcome.replace(arg, cons));
                        }
                        L2 = L2_cons;
                    }
                }
                c++;
                L.addAll(L2);
                pat = ExpressionHelper.getPat(labeledExpression, l, c);
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

    public static String generateConsolidatedExpression(String pattern, int type, PatternPropertySet propertySet) {
        String ex = "";
        PredefinedSetEntry set = ExpressionHelper.getPredefinedSetEntryByExpression(pattern);
        List<String> possibleOutcomes = set.getPossibleOutcomes();
        String ini = possibleOutcomes.get(0);
        String fin = possibleOutcomes.get(1);
        possibleOutcomes.remove(0);
        possibleOutcomes.remove(0);
        List<String> argsToCheck = new ArrayList<>();
        int argType;
        if (type == 0) { // 0 - ini, 1 - fin
            ex = ini;
            argType = 0;
        }

        if (type == 1) {
            ex = fin;
            argType = 1;
        }
        argsToCheck = ExpressionHelper.extractFromLabeled(pattern);
        for (String a : argsToCheck) {
            if (!argsToCheck.contains(a)) {
                continue;
            }
            if (!ExpressionHelper.isAtomic(a)) {
                String con2 = generateConsolidatedExpression(a, type, propertySet);
                ex = ex.replace(a, con2);
            }
        }
        return ex;
    }
}