import java.util.*;
import algo.*;

import static algo.Infix2Postfix.in2post;
import static algo.Infix2Postfix.post_eval;


public class Main {
    public static void main(String[] args) {
        System.out.println("this is infix to postfix : ");
        String infix = "24+1.2*2";
        String postfix = in2post(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
        System.out.println("this is postfix evaluation : ");
        System.out.println("Result: " + post_eval(postfix));
        }
    }
