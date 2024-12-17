import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;

public class FillTemplate {

    private static Scanner console = new Scanner(System.in);

    public static void main(String[] args)
            throws FileNotFoundException {
        File file = getFile();
        processFile(file);

    }

    public static String promptUser(String prompt) {

        System.out.print(prompt + ": ");
        String answer = console.nextLine();
        return answer;
    }

    // Processes a file line by line and writes to new file (<file-name +
    // template-filled . txt>)
    public static void processFile(File file)
            throws FileNotFoundException {
        Random numGenerator = new Random();
            
        System.out.println("Processing file: " + file.getName());
        // Create output file
        PrintStream outputFile = new PrintStream(
                new File(
                        file.getName().replace(".txt", "--template-filled--" + numGenerator.nextInt(1000000) + "-.txt")));

        Scanner fileScanner = new Scanner(file, "UTF-8");

        // System.out.println("fileScanner" +  fileScanner);
        // System.out.println("fileScanner.hasNextLine()" +  fileScanner.hasNextLine());

        while (fileScanner.hasNextLine()) {
            String templateLine = fileScanner.nextLine();

            // System.out.println("Template line: " + templateLine);

            // Replace templates with user responses to prompts
            // Find number of opening tags in line
            for (int i = 0; i < templateLine.length() - 1; i++) {
                if (templateLine.charAt(i) == '<') {
                    // Find the next closing tag
                    for (int j = i + 1; j <= templateLine.length(); j++) {
                        if (templateLine.charAt(j) == '>') {
                            // remove i to j from line
                            templateLine = templateLine.substring(0, i) + // Keep beginning of string up to '<'
                                    promptUser(templateLine.substring(i, j + 1)) // Replace the <template> with the
                                                                                 // users answer
                                    +
                                    templateLine.substring(
                                            Math.min(j + 1, templateLine.length()), // Keep string after '>'
                                            templateLine.length());
                            // reset i to start at beginning of loop
                            i = 0;
                            j = templateLine.length(); // Exit the inner loop
                        }
                    }
                }
            }

            // Write to output file
            outputFile.println(templateLine);
        }

        System.out.println("File processed. Output file created: " + outputFile);
    }

    public static File getFile()
            throws FileNotFoundException {

        System.out.print("Input file name: ");
        String fileName = console.next();
        File file = new File(fileName);
        while (!file.canRead()) {
            System.out.println("Cannot read that file. Try another name.");
            System.out.print("Input file name: ");
            fileName = console.next();
            file = new File(fileName);
        }
        console.nextLine();
        return file;
    }

}