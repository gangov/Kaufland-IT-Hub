package curseofguldan;

import java.util.ArrayList;
import java.util.Scanner;

/*

TEST CASES:

3
azzaz za
azeroth rules
banana nan

3
sgatsg gs
atcgatcga cgg
aardvark ab

*/
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int limit = Integer.parseInt(scanner.nextLine());

        // splitting the input for our patient and virus
        for (int i = 0; i < limit; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String patient = input[0];
            String virus = input[1];

            separateWordsAccordingToVirusLength(patient, virus);
        }
    }


    // here we make different 'samples' of DNA from our patient. Each DNA is with the same size
    // as the virus length. Also, each DNA is being compared with the virus in the next method.
    // Finally, we print the result
    private static void separateWordsAccordingToVirusLength(String patient, String virus) {
        int patientLength = patient.length();
        int virusLength = virus.length();
        ArrayList<Integer> matches = new ArrayList<>();

        for (int i = 0; i <= patientLength - virusLength; i++) {
            String dna = patient.substring(i, i + virusLength);
            if (compareDNAvsVirus(dna, virus)) {
                // if we have a positive match between the DNA sample and the virus, then we add
                // the DNA sample in our matches list.
                matches.add(i);
            }
        }

        // printing the final result
        printingResults(matches);
    }


    // we are allowed to have 1 mismatch between the DNA and the virus. Both the DNA sample
    // and the virus are the same length, which makes it very easy to compare
    private static boolean compareDNAvsVirus(String dna, String virus) {
        int errors = 0;
        int commonLength = dna.length();

        for (int i = 0; i < commonLength; i++) {
            if (dna.charAt(i) != virus.charAt(i)) {
                errors++;
            }
        }

        return errors < 2;
    }


    // in case that we have an empty list, then no matches were found. Otherwise we print what we got
    private static void printingResults(ArrayList<Integer> matches) {
        if (matches.size() > 0) {
            matches.forEach(e -> System.out.print(e + " "));
            System.out.println();
        } else {
            System.out.println("No matches");
        }
    }
}
