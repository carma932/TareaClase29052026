package com.wintux.compu.Tools;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculadora {

    // =========================
    // PRIORIDAD DE OPERADORES
    // =========================
    public static int prioridad(char operador) {

        switch (operador) {

            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }

        return -1;
    }

    // ======================================
    // CONVERTIR INFIJA A POSTFIJA
    // USANDO PILA (STACK)
    // ======================================
    public static Queue<String> convertirAPostfix(String infix) {

        Stack<Character> pila = new Stack<>();

        Queue<String> postfix = new LinkedList<>();

        for (int i = 0; i < infix.length(); i++) {

            char c = infix.charAt(i);

            // Ignorar espacios
            if (c == ' ') {
                continue;
            }

            // Si es número
            if (Character.isDigit(c)) {

                postfix.offer(String.valueOf(c));
            }

            // Si es (
            else if (c == '(') {

                pila.push(c);
            }

            // Si es )
            else if (c == ')') {

                while (!pila.isEmpty() &&
                        pila.peek() != '(') {

                    postfix.offer(String.valueOf(pila.pop()));
                }

                pila.pop();
            }

            // Operadores
            else {

                while (!pila.isEmpty() &&
                        prioridad(c) <= prioridad(pila.peek())) {

                    postfix.offer(String.valueOf(pila.pop()));
                }

                pila.push(c);
            }
        }

        // Vaciar pila
        while (!pila.isEmpty()) {

            postfix.offer(String.valueOf(pila.pop()));
        }

        return postfix;
    }

    // ======================================
    // RESOLVER POSTFIJA
    // USANDO PILA
    // ======================================
    public static double resolverExpresionPostfix(Queue<String> postfix) {

        Stack<Double> pila = new Stack<>();

        while (!postfix.isEmpty()) {

            String elemento = postfix.poll();

            char c = elemento.charAt(0);

            // Si es número
            if (Character.isDigit(c)) {

                pila.push(Double.parseDouble(elemento));
            }

            // Si es operador
            else {

                double b = pila.pop();
                double a = pila.pop();

                switch (c) {

                    case '+':
                        pila.push(a + b);
                        break;

                    case '-':
                        pila.push(a - b);
                        break;

                    case '*':
                        pila.push(a * b);
                        break;

                    case '/':
                        pila.push(a / b);
                        break;

                    case '^':
                        pila.push(Math.pow(a, b));
                        break;
                }
            }
        }

        return pila.pop();
    }

    // ======================================
    // MOSTRAR POSTFIJA
    // ======================================
    public static void mostrarCola(Queue<String> cola) {

        for (String elemento : cola) {

            System.out.print(elemento + " ");
        }

        System.out.println();
    }
}