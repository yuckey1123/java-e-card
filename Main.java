/*
 * created by yuckey1123 from 2018/10/20
 */

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println("Welcome.");
        Util.SCANNER = new Scanner(System.in);

        new GameFunction().gameStart();
        Util.SCANNER.close();

    }
}