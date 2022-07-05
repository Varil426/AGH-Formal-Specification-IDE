package bgs.formalspecificationide.LogicFormulaGenerator;

import org.json.simple.parser.*;

import java.io.*;
import java.util.*;

public class ExpressionHelper {
    public static PatternPropertySet FOLPatternPropertySet;
    public static PatternPropertySet LTLPatternPropertySet;

    static {
        try {
            FOLPatternPropertySet = new PatternPropertySet("FOL");
            LTLPatternPropertySet = new PatternPropertySet("LTL");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String labelExpressions(String expression) {
        StringBuilder result = new StringBuilder();
        int labelNumber = 0;
        for (Character c :
                expression.toCharArray()) {
            if (c == '(') {
                labelNumber++;
                result.append("(").append(labelNumber).append("]");
            } else if (c == ')') {
                result.append("[").append(labelNumber).append(")");
                labelNumber--;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static int getMaxedLabelPattern(String labeledExpression) {
        labeledExpression = labeledExpression.replaceAll("[^0-9]+", " ");
        String[] labels = labeledExpression.trim().split(" ");
        int maxLabel = -1;
        for (String label : labels) {
            int castedLabel = Integer.parseInt(label);
            maxLabel = Math.max(castedLabel, maxLabel);
        }
        return maxLabel;
    }

    public static PredefinedSetEntry getPat(String labeledExpression, int l, int c, String logicType) {
        int occurenceIndex = 0;
        do {
            if (occurenceIndex == 0)
                occurenceIndex = labeledExpression.indexOf(l + "]", occurenceIndex);
            else {
                int oldOccurenceIndex = occurenceIndex;
                String subst = labeledExpression.substring(oldOccurenceIndex + 1);
                occurenceIndex = subst.indexOf(l + "]") + oldOccurenceIndex + 1;
                if (oldOccurenceIndex == occurenceIndex) return null;
            }
            c -= 1;
        } while (c > 0);
        StringBuilder pattern = new StringBuilder();
        String args = labeledExpression.substring(occurenceIndex + (l + "]").length(), labeledExpression.indexOf("[" + l, occurenceIndex));
        char[] chars = labeledExpression.toCharArray();
        int beginExpressionIndex = 0;
        for (int i = occurenceIndex; i >= 0; i--) {
            if (chars[i] == ']' || chars[i] == ',') {
                beginExpressionIndex = i;
                break;
            }
        }
        String expressionName = "";
        if (beginExpressionIndex != 0)
            expressionName = labeledExpression.substring(beginExpressionIndex + 1, occurenceIndex - 1);
        else
            expressionName = labeledExpression.substring(0, occurenceIndex - 1);
        PredefinedSetEntry result = null;
        if (logicType.equals("FOL")) {
            result = FOLPatternPropertySet.findByIdentifier(expressionName.trim());
        } else if (logicType.equals("LTL")) {
            result = LTLPatternPropertySet.findByIdentifier(expressionName.trim());
        }
        if (result != null) {
            List<String> arguments = extractArgumentsFromFunction(args);
            result.PassArguments(arguments);
        }
        return result;
    }

    public static PredefinedSetEntry getPredefinedSetEntryByExpression(String expression, String logicType) {
        String identifier = expression.substring(0, expression.indexOf("("));
        PredefinedSetEntry result = null;
        if (logicType.equals("FOL")) {
            result = FOLPatternPropertySet.findByIdentifier(identifier);
        } else if (logicType.equals("LTL")) {
            result = LTLPatternPropertySet.findByIdentifier(identifier);
        }
        if (result != null) {
            List<String> args = extractFromLabeled(expression);
            result.PassArguments(args);
        }
        return result;
    }

    public static List<String> extractArgumentsFromFunction(String args) {
        ArrayList<String> arguments = new ArrayList<String>();
        for (int i = 0; i < args.length(); i++) {
            if (Character.isLowerCase(args.charAt(i))) {
                arguments.add(Character.toString(args.charAt(i)));
            } else {
                if (Character.toString(args.charAt(i)).equals(",")) continue;
                boolean shouldStop = true;
                int counter = 99;
                int j = i;
                int stCounter = 0;
                while (shouldStop) {
                    for (j = i; j < args.length(); j++) {
                        stCounter++;
                        if (Character.toString(args.charAt(j)).equals("(")) {
                            if (counter == 99) {
                                counter = 1;
                            } else {
                                counter++;
                            }
                        } else if (Character.toString(args.charAt(j)).equals(")")) {
                            counter--;
                            if (counter == 0) {
                                arguments.add(args.substring(i, i + stCounter));
                                i = i + stCounter;
                                shouldStop = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return arguments;
    }

    public static List<String> extractFromLabeled(String labeled) {
        int x = labeled.indexOf("]");
        return extractArgumentsFromFunction(labeled.substring(x + 1, labeled.length() - 3));

    }

    public static boolean isNotAtomic(String argument) {
        return argument.contains("=>") || argument.contains("|") || argument.contains("^") || argument.contains("]");
    }
}