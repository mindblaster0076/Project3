package cs2321;

import net.datastructures.Position; // Make sure to import the Position interface

public class ExpressionTree {

    public static Double eval(LinkedBinaryTree<String> tree) {
        return eval(tree.root(), tree);
    }

    private static Double eval(Position<String> p, LinkedBinaryTree<String> tree) {
        String element = p.getElement();
        if (tree.isExternal(p)) {
            return Double.parseDouble(element); // Leaf node, return its value
        }

        // Recursively evaluate the left and right children
        Double leftValue = eval(tree.left(p), tree);
        Double rightValue = eval(tree.right(p), tree);

        switch (element) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                return leftValue / rightValue;
            default:
                throw new IllegalArgumentException("Invalid operator: " + element);
        }
    }

    public static String toExpression(LinkedBinaryTree<String> tree) {
        return toExpression(tree.root(), tree);
    }

    private static String toExpression(Position<String> p, LinkedBinaryTree<String> tree) {
        if (tree.isExternal(p)) {
            return p.getElement(); // Leaf node, return its value
        }

        // Recursively get the expressions of the left and right children
        String leftExpr = toExpression(tree.left(p), tree);
        String rightExpr = toExpression(tree.right(p), tree);

        return "(" + leftExpr + p.getElement() + rightExpr + ")"; // Construct the expression
    }
}