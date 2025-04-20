import java.util.*;
import algo.*;

import static algo.Infix2Postfix.in2post;
import static algo.Infix2Postfix.post_eval;


public class Main {
    public static void main(String[] args) {
        String infix = "22.3+4.5*3.1";
        String postfix = in2post(infix);
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Result: " + post_eval(postfix));
        }
    }
