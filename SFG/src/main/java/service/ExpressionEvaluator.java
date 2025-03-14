package service;
import model.graph.impl.Path;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.*;

public class ExpressionEvaluator {
    public static String findFormula(List<Path> forwardPaths, String delta) {
        StringBuilder formulaBuilder = new StringBuilder();
        formulaBuilder.append("(");

        for (int i = 0; i < forwardPaths.size(); i++) {
            Path forwardPath = forwardPaths.get(i);
            formulaBuilder.append("(")
                    .append(forwardPath.getGain())
                    .append(" * ")
                    .append(forwardPath.getDelta())
                    .append(")");
            if (i < forwardPaths.size() - 1) {
                formulaBuilder.append(" + ");
            }
        }

        formulaBuilder.append(") / (").append(delta).append(")");

        return formulaBuilder.toString();
    }

    public static double evaluateFormula(String formula) {
        Expression expression = new ExpressionBuilder(formula).build();
        return expression.evaluate();
    }

    //                                         L1L2   L3L1   L1L2L3
    // (delta1 * exp1 + delta2 * exp2) / (1 - (exp3 + exp4) + exp5)
    // delta = 1 - (∑ 2 nontouching loops) + (∑ 3 nontouching loops) - (∑ 4 nontouching loops) + ..........
}
