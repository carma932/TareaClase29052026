package com.wintux.compu.Controller;

import com.wintux.compu.Tools.Calculadora;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

        String postfix =
                Calculadora.convertirAPostfix(infix);

        double resultado =
                Calculadora.resolverExpresionPostfix(postfix);

        return "Infix: " + infix +
                "\nPostfix: " + postfix +
                "\nResultado: " + resultado;
    }
}