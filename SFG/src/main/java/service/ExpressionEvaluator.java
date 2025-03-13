//package service;
//
//import model.graph.impl.Edge;
//import model.graph.impl.Path;
//
//import java.util.*;
//
//public class ExpressionEvaluator {
//    private final LinkedList<String> symbolicTerms;
//    private final Map<String, Double> symbolValues;
//    private final String gain;
//
//    public ExpressionEvaluator(Path path) {
//        this.symbolicTerms = new LinkedList<>();
//        this.symbolValues = new HashMap<>();
//        this.gain = path.getGain();
//
//        for (Edge edge : path.getEdges()) {
//            symbolicTerms.add(edge.getGainSymbol());
//            symbolValues.putIfAbsent(edge.getGainSymbol(), edge.getGain());
//            if (!symbolValues.get(edge.getGainSymbol()).equals(edge.getGain())) {
//                throw new IllegalArgumentException("Symbol " + edge.getGainSymbol() +
//                " has multiple values: " + symbolValues.get(edge.getGainSymbol()) +
//                " vs " + edge.getGain());
//            }
//        }
//    }
//
//    public String getSymbolicGain() {
//        return String.join(" • ", symbolicTerms);
//    }
//
//    public String getSubstitutedGain() {
//        return String.join(" • ", symbolicTerms.stream()
//                .map(term -> String.valueOf(symbolValues.get(term)))
//                .toArray(String[]::new));
//    }
//
//    //                                         L1L2   L3L1   L1L2L3
//    // (delta1 * exp1 + delta2 * exp2) / (1 - (exp3 + exp4) + exp5)
//    // delta = 1 - (∑ 2 nontouching loops) + (∑ 3 nontouching loops) - (∑ 4 nontouching loops) + ..........
//}
