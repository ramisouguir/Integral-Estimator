import java.util.*;
import java.util.Arrays;
/**
 * Write a description of class Estimator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Estimator
{
    public static ArrayList<Integer> numbers = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input equation in terms of x");
        String equation = sc.nextLine();
        System.out.println("Please input starting x-coordinate");
        int start = sc.nextInt();
        System.out.println("Please input ending x-coordinate");
        int end = sc.nextInt();
        final int difference = end-start;
        double inc = difference/500.0;
    }
    public static double slopeAtPoint(ArrayList<String> dpieces, double xcord)
    {
        double slope = 0.0;
         for(int x = 0; x<dpieces.size(); x++)
        {
           if(!dpieces.get(x).equals("+")&&!dpieces.get(x).equals("-"))
           {
               if(dpieces.get(x).contains("x")&&x!=0)
                {
                    if(dpieces.get(x).contains("^"))
                    {
                       if(dpieces.get(x-1).contains("-"))
                       {
                            if(dpieces.get(x).indexOf("x")!=0)
                           {
                              slope-=((double)Integer.parseInt(numberBefore(dpieces.get(x),dpieces.get(x).indexOf("x"))))*(Math.pow(xcord,((double)Integer.parseInt(dpieces.get(x).substring(dpieces.get(x).indexOf("^")+1)))));
                           }
                           else
                           {
                              slope-=(Math.pow(xcord,((double)Integer.parseInt(dpieces.get(x).substring(dpieces.get(x).indexOf("^")+1)))));
                           }
                        }
                       else
                       {
                           if(dpieces.get(x).indexOf("x")!=0)
                           {
                             slope+=((double)Integer.parseInt(numberBefore(dpieces.get(x),dpieces.get(x).indexOf("x"))))*(Math.pow(xcord,((double)Integer.parseInt(dpieces.get(x).substring(dpieces.get(x).indexOf("^")+1)))));
                           }
                           else
                           {
                              slope+=(Math.pow(xcord,((double)Integer.parseInt(dpieces.get(x).substring(dpieces.get(x).indexOf("^")+1)))));
                           }
                        }
                  }
                  else if(dpieces.get(x).indexOf("x")!=0)
                  {
                      if(dpieces.get(x-1).contains("-"))
                        {
                            slope-=(double)Integer.parseInt(numberBefore(dpieces.get(x),dpieces.get(x).indexOf("x")))*xcord;
                        }
                        else
                        {
                            slope+=Integer.parseInt(numberBefore(dpieces.get(x),dpieces.get(x).indexOf("x")))*xcord;
                        }
                    }
                    else
                    {
                        if(dpieces.get(x-1).contains("-"))
                        {
                            slope-=xcord;
                        }
                         else
                        {
                            slope+=xcord;
                        }
                    }
                }
                else if(dpieces.get(x).contains("x"))
                 {
                    if(dpieces.get(x).contains("^"))
                    {
                        if(dpieces.get(x).indexOf("x")!=0)
                           {
                             slope+=((double)Integer.parseInt(numberBefore(dpieces.get(x),dpieces.get(x).indexOf("x"))))*(Math.pow(xcord,((double)Integer.parseInt(dpieces.get(x).substring(dpieces.get(x).indexOf("^")+1)))));
                           }
                           else
                           {
                              slope+=(Math.pow(xcord,((double)Integer.parseInt(dpieces.get(x).substring(dpieces.get(x).indexOf("^")+1)))));
                           }
                    }
                    else
                    {
                        if(dpieces.get(x).indexOf("x")!=0)
                        {
                            slope+=((double)Integer.parseInt(numberBefore(dpieces.get(x),dpieces.get(x).indexOf("x"))))*xcord;
                        }
                        else
                        {
                            slope+=xcord;
                        }
                    }
                 }
                 else
                 {
                        slope+=(double)(Integer.parseInt(dpieces.get(x)));
                  }
           }
        }
        return slope;
    }
    public static float finalEstimate(String equation, float thresh, int iterations, float start, float end)
    {
        float est = 0.0f;
        float distance = end-start;
        ArrayList<String> pieces = calculateDerivative(equation);
        ArrayList<String> equat = splitUpEquation(equation);
        float pdist = distance/iterations;
        int last = 1;
        for(int x = 1; x<=iterations; x++)
        {
            if(slopeAtPoint(pieces, start+(pdist*x))>=thresh)
            {
                est+=(pdist*(slopeAtPoint(equat, start+(pdist*x))+slopeAtPoint(equat, start+(pdist*(last)))))/2;
                last = x;
            }
        }
        return est;
    }
     public static ArrayList<String> calculateDerivative(String equation)
    {
        String derivative = "";
        int start = 0;
        ArrayList<String> pieces = new ArrayList<String>();
        ArrayList<String> dpieces = new ArrayList<String>(); //first element is the full derivative, all elements following are the different pieces of that equation
        ArrayList<Character> symbols = new ArrayList<Character>();
        for(int x = 0; x<equation.length(); x++)
        {
            if(equation.charAt(x)=='+'||equation.charAt(x)=='-')
            {
                if(equation.charAt(x-1)==' ')
                {
                  pieces.add(getPieceBefore(equation, x-1));
               }
               else
               {
                   pieces.add(getPieceBefore(equation, x));
                }
                symbols.add(equation.charAt(x));
            }
             else if(x==equation.length()-1)
            {
                pieces.add(getPieceBefore(equation, x+1));
            }
        }
        for(int x = 0;x<pieces.size();x++)
        {
            if(x<symbols.size())
            derivative+=powerRule(pieces.get(x))+symbols.get(x);
            else
            derivative+=powerRule(pieces.get(x));
        }
        //dpieces.add(derivative);
        for(int x = 0;x<pieces.size();x++)
        {
            if(x<symbols.size())
            {
             dpieces.add(powerRule(pieces.get(x)));
             dpieces.add(""+symbols.get(x));
            }
            else
             dpieces.add(powerRule(pieces.get(x)));
        }
        return dpieces;
    }
    public static ArrayList<String> splitUpEquation(String equation)
    {
        ArrayList<String> pieces = new ArrayList<String>();
        for(int x = 0; x<equation.length(); x++)
        {
            if(equation.charAt(x)=='+'||equation.charAt(x)=='-'||x==(equation.length()-1))
            {
               if(x!=(equation.length()-1))
               {
                 pieces.add(getPieceBefore(equation, x));
                 pieces.add(""+equation.charAt(x));
              }
              else
              {
                  pieces.add(getPieceBefore(equation, x+1));
                }
            }
        }
        return pieces;
    }
    public static String powerRule(String equation)
    {
        String derivative = "";
        for(int x = 0;x<equation.length(); x++)
        {
            if(equation.charAt(x)=='x') 
            {
                if(!((x+1)==equation.length())) //makes sure the x+1 is in bounds
                {
                    if(equation.charAt(x+1)=='^')
                    {
                        if(!(x==0))
                        {
                            if(numbers.contains(((int)equation.charAt(x-1))-48)) //checks if the character previous to 'x' is a number 
                            {
                                if(equation.charAt(x+2)=='2')
                                {
                                  derivative += Integer.parseInt(numberBefore(equation, x))*(((int)(equation.charAt(x+2))-48));
                                  derivative += "x";
                                }
                                else if(equation.charAt(x+2)!='2')
                                {
                                    derivative += Integer.parseInt(numberBefore(equation, x))*Integer.parseInt(numberAfter(equation, x+2));
                                    derivative+="x";
                                    derivative += "^"+(Integer.parseInt(numberAfter(equation, x+2))-1);
                                }
                            }
                        }
                        else
                        {
                            if(equation.charAt(x+2)=='2')
                                {
                                  derivative += (((int)(equation.charAt(x+2))-48));
                                  derivative += "x";
                                }
                                else if(equation.charAt(x+2)!='2')
                                {
                                    derivative += Integer.parseInt(numberAfter(equation, x+2));
                                    derivative+="x";
                                    derivative += "^"+(Integer.parseInt(numberAfter(equation, x+2))-1);
                                }
                        }
                    }
                }
                else if(!(x==0))
                {
                    derivative+=numberBefore(equation, x);
                }
                else
                {
                    derivative+="1";
                }
            }
        }
        if(derivative.equals(""))
         derivative = "0";
        return derivative;
    }
    public static String numberBefore(String equation, int index)
    {
        String num = "";
        int start = 0;
        for(int x = index-1; x>-1; x--)
        {
            if(!(numbers.contains(((int)equation.charAt(x))-48)))
            {
                start = x;
                break;
            }
        }
        num=equation.substring(start, index);
        return num;
    }
    public static String numberAfter(String equation, int index)
    {
        String num = "";
        int end = equation.length();
        for(int x = index; x<equation.length(); x++)
        {
            if(!(numbers.contains(((int)equation.charAt(x))-48)))
            {
                end = x;
                break;
            }
        }
        num=equation.substring(index, end);
        return num;
    }
    public static boolean isNegative(String equation, int index)
    {
        for(int x = index-1; x>-1; x--)
        {
            if(equation.charAt(x)=='-')
            {
                return true;
            }
            else if(x==0||equation.charAt(x)=='-')
            {
                return false;
            }
        }
        return false;
    }
    public static String getPieceBefore(String equation, int index)
    {
        String piece = "";
        boolean spaceBefore = false;
        int spot =0;
        for(int x = index-1; x>=0; x--)
        {
            if((equation.charAt(x)=='+'||equation.charAt(x)=='-'||equation.charAt(x)==' '))
            {
                spot = x+1;
                break;
            }
        }
        if(index==equation.length())
        {
            return equation.substring(spot);
        }
         return equation.substring(spot, index);
    }
}













