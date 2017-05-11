package calculator;

import java.util.*;

public class Calculator {

    private static final Map<String, Integer> MAIN_MATH_OPERATIONS;
    private static final String leftBracket = "(";
    private static final String rightBracket = ")";

    static {
        MAIN_MATH_OPERATIONS = new HashMap<>();
        MAIN_MATH_OPERATIONS.put("*", 1);
        MAIN_MATH_OPERATIONS.put("/", 1);
        MAIN_MATH_OPERATIONS.put("+", 2);
        MAIN_MATH_OPERATIONS.put("-", 2);
    }

    public static double calc(String expression) {

        String rpn = convertToPolishNotation(expression);
        StringTokenizer tokenizer = new StringTokenizer(rpn, " ");
        Stack<Double> stack = new Stack<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();

            if (!MAIN_MATH_OPERATIONS.keySet().contains(token)) {
                stack.push(new Double(token));
            } else {
                Double operand2 = stack.pop();
                Double operand1 = stack.empty() ? 0.0 : stack.pop();
                if (token.equals("*")) {
                    stack.push(operand1 * operand2);
                } else if (token.equals("/")) {
                    if (operand2 == 0.0)
                        throw new IllegalArgumentException("Division by 0");
                    stack.push(operand1 / operand2);
                } else if (token.equals("+")) {
                    stack.push(operand1 + operand2);
                } else if (token.equals("-")) {
                    stack.push(operand1 - operand2);
                }
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException("Expression syntax error.");
        return stack.pop();

    }

    private static String convertToPolishNotation(String expression) {

        if (expression == null || expression.length() == 0)
            throw new IllegalStateException("Expression isn't specified.");

        List<String> out = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        expression = expression.replace(" ", "");

        Set<String> operationSymbols = new HashSet<>(MAIN_MATH_OPERATIONS.keySet());
        operationSymbols.add(leftBracket);
        operationSymbols.add(rightBracket);

        int index = 0;
        boolean findNext = true;
        while (findNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";

            for (String operation : operationSymbols) {
                int i = expression.indexOf(operation, index);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }

            if (nextOperationIndex == expression.length()) {
                findNext = false;
            } else {
                if (index != nextOperationIndex) {
                    out.add(expression.substring(index, nextOperationIndex));
                }

                if (nextOperation.equals(leftBracket)) {
                    stack.push(nextOperation);
                } else if (nextOperation.equals(rightBracket)) {
                    while (!stack.peek().equals(leftBracket)) {
                        out.add(stack.pop());
                        if (stack.empty()) {
                            throw new IllegalArgumentException("Unmatched brackets");
                        }
                    }
                    stack.pop();
                } else {
                    while (!stack.empty() && !stack.peek().equals(leftBracket) &&
                            (MAIN_MATH_OPERATIONS.get(nextOperation) >= MAIN_MATH_OPERATIONS.get(stack.peek()))) {
                        out.add(stack.pop());
                    }
                    stack.push(nextOperation);
                }
                index = nextOperationIndex + nextOperation.length();
            }
        }

        if (index != expression.length()) {
            out.add(expression.substring(index));
        }

        while (!stack.empty()) {
            out.add(stack.pop());
        }
        StringBuilder result = new StringBuilder();
        if (!out.isEmpty())
            result.append(out.remove(0));
        while (!out.isEmpty())
            result.append(" ").append(out.remove(0));

        return result.toString();
    }

}
