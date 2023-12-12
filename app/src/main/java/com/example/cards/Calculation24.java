package com.example.cards;

/**
 * Created on 2016/6/19.
 */
public class Calculation24
{
    public static String resultText = "";
    public static int count = 0;
    public static void main(String[] args)throws Exception
    {

    }

    public static String calculate(Integer[] cards){
        resultText = "";
        count = 0;
        Integer[] num = cards;
        permutationNumber(num);//打印出计算结果
        System.out.println("已列出所有結果！");
        resultText = "总共"+count+"种计算方式。\n" + resultText;
        return resultText;
    }

    public static void permutationNumber(Integer[] num)
    {
        //计算的数字排列为 4*3*2*1 种
        //举出所以的组合，将答案为24的打印出
        for (int i = 0; i < num.length; i++)
        {
            for (int j = 0; j < num.length; j++)
            {
                for (int k = 0; k < num.length; k++)
                {
                    for (int l = 0; l < num.length; l++)
                    {
                        if (i == j || i == k || i == l || j == k || j == l || k == l)
                        {
                            continue;
                        }
                        sequence(num[i], num[j], num[k], num[l]);
                    }
                }
            }
        }
    }
    // a mark1 b mark2 c mark3 d 的 计算顺序和不同+-*/的各种组合
    // 计算形式为 +-*/ 四种
    // 计算的顺序为 （（1#2）#3）#4、（1#2）#（3#4）、（1#（2#3））#4、1#（（2#3）#4）、1#（2#（3#4）） 五种
    public static void sequence(int a, int b, int c, int d)
    {
        // 第一个运算符4种
        for (int mark1 = 1; mark1 <= 4; mark1++)
        {
            // 第二个运算符4种
            for (int mark2 = 1; mark2 <= 4; mark2++)
            {
                // 第三个运算符4种
                for (int mark3 = 1; mark3 <= 4; mark3++)
                {
                    // 五种计算顺序类型
                    for (int type = 1; type <= 5; type++)
                    {
                        double result = 0;
                        switch (type)
                        {
                            case 1:// ((a mark1 b)mark2 c) mark3 d 形式
                                result = calculate(calculate(calculate(a, b, mark1), c, mark2), d, mark3);
                                if (result == 24)
                                {
                                    System.out.println("((" + a + getMarkString(mark1) + b + ")" + getMarkString(mark2) + c + ")" + getMarkString(mark3) + d);
                                    count++;
                                    resultText = resultText+count + ":" + "((" + a + getMarkString(mark1) + b + ")" + getMarkString(mark2) + c + ")" + getMarkString(mark3) + d+"\n";

                                }
                                break;
                            case 2:// (a mark1 b)mark2 (c mark3 d )形式
                                result = calculate(calculate(a, b, mark1), calculate(c, d, mark3), mark2);
                                if (result == 24)
                                {
                                    System.out.println("(" + a + getMarkString(mark1) + b + ")" + getMarkString(mark2) + "(" + c + getMarkString(mark3) + d + ")");
                                    count++;
                                    resultText = resultText+count + ":" + "(" + a + getMarkString(mark1) + b + ")" + getMarkString(mark2) + "(" + c + getMarkString(mark3) + d + ")"+"\n";
                                }
                                break;
                            case 3:// (a mark1 (b mark2 c)) mark3 d 形式
                                result = calculate(calculate(a, calculate(b, c, mark2), mark1), d, mark3);
                                if (result == 24)
                                {
                                    System.out.println("(" + a + getMarkString(mark1) + "(" + b + getMarkString(mark2) + c + "))" + getMarkString(mark3) + d);
                                    count++;
                                    resultText = resultText + count + ":" + "(" + a + getMarkString(mark1) + "(" + b + getMarkString(mark2) + c + "))" + getMarkString(mark3) + d + "\n";
                                }
                                break;
                            case 4:// a mark1 ((b mark2 c) mark3 d) 形式
                                result = calculate(a, calculate(calculate(b, c, mark2), d, mark3), mark1);
                                if (result == 24)
                                {
                                    System.out.println("((" + a + getMarkString(mark1) + "((" + b + getMarkString(mark2) + c + ")" + getMarkString(mark3) + d + ")");
                                    count++;
                                    resultText = resultText + count + ":" + "((" + a + getMarkString(mark1) + "((" + b + getMarkString(mark2) + c + ")" + getMarkString(mark3) + d + ")" + "\n";
                                }
                                break;
                            case 5:// a mark1 (b mark2 (c mark3 d)) 形式
                                result = calculate(a, calculate(b, calculate(c, d, mark3), mark2), mark1);
                                if (result == 24)
                                {
                                    System.out.println("((" + a + getMarkString(mark1) + "(" + b + getMarkString(mark2) + "(" + c + getMarkString(mark3) + d + "))");
                                    count++;
                                    resultText = resultText + count + ":" + "((" + a + getMarkString(mark1) + "(" + b + getMarkString(mark2) + "(" + c + getMarkString(mark3) + d + "))" + "\n";
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
    // 数字计算，first mark second
    // 其中markType(1:+;2:-;3:*;4:/)
    public static double calculate(double first, double second, int markType)
    {
        switch (markType)
        {
            case 1:
                return first + second;
            case 2:
                return first - second;
            case 3:
                return first * second;
            case 4:
                return first / second;
            default:
                return 0;
        }
    }
    // 打印时候判定运算符
    // 其中markType(1:+;2:-;3:*;4:/)
    public static String getMarkString(int markType)
    {
        switch (markType)
        {
            case 1:
                return "+";
            case 2:
                return "-";
            case 3:
                return "×";
            case 4:
                return "÷";
            default:
                return " ";
        }
    }
}