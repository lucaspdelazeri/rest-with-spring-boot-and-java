package br.com.lpd.controllers;

import br.com.lpd.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(
            @PathVariable("numberOne") String number1,
            @PathVariable("numberTwo") String number2
    ) throws Exception {
        isNumeric(number1);
        isNumeric(number2);
        return covertToDouble(number1) + covertToDouble(number2);
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double sub(
            @PathVariable("numberOne") String number1,
            @PathVariable("numberTwo") String number2) throws Exception {
        isNumeric(number1);
        isNumeric(number2);
        return covertToDouble(number1) - covertToDouble(number2);
    }

    @RequestMapping("/mult/{numberOne}/{numberTwo}")
    public Double mult(
            @PathVariable("numberOne") String number1,
            @PathVariable("numberTwo") String number2)
            throws Exception {
        isNumeric(number1);
        isNumeric(number2);

        return covertToDouble(number1) * covertToDouble(number2);
    }

    @RequestMapping("/div/{numberOne}/{numberTwo}")
    public Double div(
            @PathVariable("numberOne") String number1,
            @PathVariable("numberTwo") String number2)
            throws Exception {
        isNumeric(number1);
        isNumeric(number2);

        Double value1 = covertToDouble(number1);
        Double value2 = covertToDouble(number2);

        if(value1 == 0 || value2 == 0) return Double.NaN;

        return value1 / value2;
    }

    @RequestMapping("/mean/{numberOne}/{numberTwo}")
    public Double mean(
            @PathVariable("numberOne") String number1,
            @PathVariable("numberTwo") String number2)
            throws Exception {
        isNumeric(number1);
        isNumeric(number2);

        double value1 = covertToDouble(number1);
        double value2 = covertToDouble(number2);

        if(value1 == 0 || value2 == 0) return Double.NaN;

        return (value1 + value2) / 2;
    }

    @RequestMapping("/square/{number}")
    public Double squareRoot(
            @PathVariable("number") String number1) throws Exception {
        isNumeric(number1);

        return Math.sqrt(covertToDouble(number1));
    }

    public static Double covertToDouble(String strNumber) {
        if (strNumber == null) return 0d;
        String number = strNumber.replaceAll(",", ".");
        return Double.parseDouble(number);
    }

	public static void isNumeric(String strNumber) {
        if (strNumber == null || !strNumber.replaceAll(",", ".").matches("[-+]?[0-9]*\\.?[0-9]+")) {
            throw new UnsupportedMathOperationException("please send a numeric value");
        };
    }
}
