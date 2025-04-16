package backend.SFG.service;

import java.util.List;

import org.springframework.stereotype.Service;

import backend.SFG.model.graph.impl.Path;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Service
public class ExpressionEvaluator {

    public static String[] findFormula(List<Path> forwardPaths, String delta) {
        StringBuilder formulaBuilder = new StringBuilder();

        for (int i = 0; i < forwardPaths.size(); i++) {
            Path forwardPath = forwardPaths.get(i);
            formulaBuilder.append("[(")
                    .append(forwardPath.getGain())
                    .append(") . (")
                    .append(forwardPath.getDelta())
                    .append(")]");

            if (i < forwardPaths.size() - 1) {
                formulaBuilder.append(" + ");
            }
        }

        return new String[] { formulaBuilder.toString(), delta };
    }

    public static Double evaluateFormula(String formula) {
        try {
            Expression expression = new ExpressionBuilder(formula).build();
            return expression.evaluate();
        } catch (Exception e) {
            return null;
        }
    }
}