package com.example.calculator;

import java.util.Stack;

public class StrChange {

    private String string;
    private int First = -1;
    Stack<Character> stack_char = new Stack<>();
    Stack<Double> stack_double = new Stack<>();


    public StrChange(String s){
        this.string = s;
    }
    
    public double change(String str) {
        int n = str.length();
        char[] s = str.toCharArray();
        StringBuilder stringBuilder2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if ((s[i] >= '0' && s[i] <= '9') || s[i] == '.') {
                stringBuilder2.append(s[i]);
                if(s[i+1] == '+' || s[i+1] == '-' || s[i+1] == 'X' || s[i+1] == '÷' || s[i+1] == '%'||s[i+1]=='='){
                    double h = Double.parseDouble(stringBuilder2.toString());
                    stringBuilder2.delete(0,stringBuilder2.length());
                    stack_double.push(h);
                }
            } else if (s[i] == '(') {
                stack_char.push(s[i]);
            } else if (s[i] == '+' || s[i] == '-' || s[i] == 'X' || s[i] == '÷' || s[i] == '%') {
                if (stack_char.empty()) {
                    stack_char.push(s[i]);
                } else {
                    if (getFirst(stack_char.peek()) <= getFirst(s[i])) {
                        stack_char.push(s[i]);
                    } else {
                        double number1 = stack_double.pop();
                        double number2 = stack_double.pop();
                        char signal = stack_char.pop();
                        double result = calculate(signal, number1, number2);
                        stack_double.push(result);
                        stack_char.push(s[i]);
                    }
                }

            } else if (s[i] == ')') {
                while (true) {
                    char signal = stack_char.pop();
                    if (signal == '(') {
                        break;
                    } else {
                        double number1 = stack_double.pop();
                        double number2 = stack_double.pop();
                        double result = calculate(signal, number1, number2);
                        stack_double.push(result);
                    }
                }
            }
        }
        while(true){
            if (stack_char.isEmpty()){
                break;
            }
            else{
                double number1 = stack_double.pop();
                double number2 = stack_double.pop();
                char signal = stack_char.pop();
                double result = calculate(signal, number1, number2);
                stack_double.push(result);
            }
        }
        return stack_double.pop();
    }

    public double calculate(char signal,double num1,double num2){
        double result;
        switch (signal){
            case '+' :result = num1 + num2;break;
            case '-' :result = num1 - num2;break;
            case 'X' :result = num1 * num2;break;
            case '÷' :result = num1 / num2;break;
            case '%' :result = num1 % num2;break;
            default:
                throw new IllegalStateException("Unexpected value: " + signal);
        }
        return result;
    }

    public int getFirst(char r){
        int first = -1;
        switch (r){
            case '+':
            case '-': first = 0 ;break;
            case 'X':
            case '÷':
            case '%': first = 1 ;break;
        }
        return first;
    }
}
