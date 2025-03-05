package service;

import model.graph.impl.Edge;
import model.graph.impl.Path;

import java.util.*;

public class ExpressionEvaluator {
    private final LinkedList<String> symbolicTerms;
    private final Map<String, Integer> symbolValues;
    private double gain;

    public ExpressionEvaluator(Path path) {
        this.symbolicTerms = new LinkedList<>();
        this.symbolValues = new HashMap<>();
        this.gain = 1;
        for (Edge edge : path.getEdges()) {
            symbolicTerms.add(edge.getGainSymbol());
            gain *= edge.getGain();
            symbolValues.putIfAbsent(edge.getGainSymbol(), edge.getGain());
            if (!symbolValues.get(edge.getGainSymbol()).equals(edge.getGain())) {
                throw new IllegalArgumentException("Symbol " + edge.getGainSymbol() +
                        " has multiple values: " + symbolValues.get(edge.getGainSymbol()) + " vs " + edge.getGain());
            }
        }
    }

    public String getSymbolicGain() {
        return String.join(" * ", symbolicTerms);
    }

    public String getSubstitutedGain() {
        return String.join(" * ", symbolicTerms.stream()
                .map(term -> String.valueOf(symbolValues.get(term)))
                .toArray(String[]::new));
    }

    public double getNumericGain() {
        return gain;
    }
}
