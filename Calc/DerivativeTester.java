import java.util.*;
import javax.swing.*;
/**
 * Write a description of class DerivativeTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DerivativeTester
{
     public static void main(String args[])
    {
        Scanner kb = new Scanner(System.in);
        System.out.println("Input equation in terms of x");
        String equation = kb.nextLine();
        System.out.println("Input Threshold");
        String thresh = kb.nextLine();
        System.out.println("Input Precision (The higher this value is, the more precise the estimate will be)");
        String iterations = kb.nextLine();
        System.out.println("Input Lower Bound");
        String start = kb.nextLine();
        System.out.println("Input Upper Bound");
        String end = kb.nextLine();
        System.out.println(Estimator.finalEstimate(equation,Float.parseFloat(thresh), Integer.parseInt(iterations),Float.parseFloat(start),Float.parseFloat(end)));
    }
}





