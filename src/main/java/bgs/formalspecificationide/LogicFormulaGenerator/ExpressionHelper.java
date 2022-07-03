package bgs.formalspecificationide.LogicFormulaGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionHelper {
    public static PatternPropertySet patternPropertySet = new PatternPropertySet();

    public static String labelExpressions(String expression) {
        StringBuilder result = new StringBuilder();
        int labelNumber = 0;
        for (Character c :
                expression.toCharArray()) {
            if (c == '(') {
                labelNumber++;
                result.append("(" + labelNumber + "]");
            } else if (c == ')') {
                result.append("[" + labelNumber + ")");
                labelNumber--;
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }


    public static int getMaxedLabelPattern(String labeledExpression) {
        labeledExpression = labeledExpression.replaceAll("[^0-9]+", " ");
        List<String> labels = Arrays.asList(labeledExpression.trim().split(" "));
        int maxLabel = -1;
        for (String label : labels) {
            int castedLabel = Integer.valueOf(label);
            maxLabel = Math.max(castedLabel, maxLabel);
        }

        return maxLabel;
    }


    public static PredefinedSetEntry getPat(String labeledExpression, int l, int c) {
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
        PredefinedSetEntry result = patternPropertySet.findByIdentifier(expressionName.trim());
        if (result != null) {
            List<String> arguments = extractArgumentsFromFunction(args);
            result.PassArguments(arguments);
        }
        return result;
    }

    public static PredefinedSetEntry getPredefinedSetEntryByExpression(String expression) {
        String identifier = expression.substring(0, expression.indexOf("("));
        PredefinedSetEntry result = patternPropertySet.findByIdentifier(identifier);
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

        List<String> argsLabeled = extractArgumentsFromFunction(labeled.substring(x + 1, labeled.length() - 3));

        return argsLabeled;

    }


    public static boolean isAtomic(String argument) {
        return !(argument.contains("=>") || argument.contains("|") || argument.contains("^") || argument.contains("]"));
    }
}
