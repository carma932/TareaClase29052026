package com.wintux.compu.Controller;

import com.wintux.compu.Tools.Calculadora;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;

@RestController
public class CalculadoraController {

    @GetMapping("/")
    public String inicio() {

        return "Calculadora Infix funcionando";
    }

    @GetMapping("/expresion")
    public String calcularInfix(
            @RequestParam String infix
    ) {

        Queue<String> postfix =
                Calculadora.convertirAPostfix(infix);

        // Crear otra cola porque resolver la vacía
        Queue<String> postfixResolver =
                Calculadora.convertirAPostfix(infix);

        double resultado =
                Calculadora.resolverExpresionPostfix(postfixResolver);

        return "Infix: " + infix +
                "\nPostfix: " + postfix +
                "\nResultado: " + resultado;
    }
}