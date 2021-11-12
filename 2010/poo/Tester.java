package es.uam.eps.poo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;

/**
 *
 * Tester
 *
 * @author Alejandro Bellogín
 */
public class Tester {

    public static void main(String[] args) throws Exception {
        PrintStream old = System.out;
        PrintStream newOut = new PrintStream("out.main.txt");
        System.setOut(newOut);
        // do things here
        Main.main(args);
        // compare file with salida.txt
        BufferedReader output = new BufferedReader(new FileReader("../salida.txt"));
        BufferedReader main = new BufferedReader(new FileReader("out.main.txt"));
        String line1 = null;
        String line2 = null;
        StringBuffer errors = new StringBuffer();
        while ((line1 = main.readLine()) != null) {
			// assuming the same length
            line2 = output.readLine().trim();
            if (!line1.trim().equals(line2)) {
                errors.append("r:" + line1 + System.getProperty("line.separator")); // real
                errors.append("e:" + line2 + System.getProperty("line.separator")); // expected
            }
        }
        // end
        System.setOut(old);
        System.out.println(errors.toString());
    }
}
