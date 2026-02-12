package ricardo_paulo.net;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        System.out.println("Questão 1 - SOMA RECURSIVA");
        int[] numbs = {10, 10, 6, 4, 5, 5};
        System.out.printf("Somatório recursivo: %d\n\n", recursiveSum(numbs, 0));

        System.out.println("Questão 2 - VERIFICAÇÃO DE PALINDROMICIDADE");
        String word = "anilina";
        System.out.printf("%s é um palíndromo: %b\n\n", word, isPalindrome(word, 0));

        System.out.println("Questão 3 - EXPONENCIAÇÃO RECURSIVA");
        int number = 2;
        int exponent = 100;
        System.out.printf("%d%s = %d", number, superscript(exponent), recursivePow(number, exponent));

    }

    public static int recursiveSum(int[] v, int i) {

        if (i == v.length - 1) return v[v.length - 1];

        return v[i] + recursiveSum(v, ++i);

    }

    public static boolean isPalindrome (String word, int i) {
        word = word.toLowerCase();
        int wordLength = word.length();

        if (i > wordLength/2) return true;

        boolean actualComparative = word.charAt(i) == word.charAt(wordLength - i - 1);

        return actualComparative && isPalindrome(word, ++i);
    }

    public static BigInteger recursivePow(int number, int exponent) {

        if (exponent == 1) return BigInteger.valueOf(number);

        return BigInteger.valueOf(number).multiply(recursivePow(number, exponent - 1));
    }

    // Método auxiliar utilizado apenas para retornar a forma sobrescrita de um inteiro.
    private static String superscript (int number) {
        String[] superscripts = {
                "\u2070",
                "\u00B9",
                "\u00B2",
                "\u00B3",
                "\u2074",
                "\u2075",
                "\u2076",
                "\u2077",
                "\u2078",
                "\u2079"
        };

        char[] splitNumber = String.valueOf(number).toCharArray();
        String result = "";

        for (char c : splitNumber) {
            int digit = Character.getNumericValue(c);
            result = result.concat(superscripts[digit]);
        }

        return result;
    }
}
