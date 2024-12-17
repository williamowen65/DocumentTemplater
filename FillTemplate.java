import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
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
     
        Scanner fileScanner = new Scanner(file, "UTF-8");

        // System.out.println("fileScanner" +  fileScanner);
        // System.out.println("fileScanner.hasNextLine()" +  fileScanner.hasNextLine());

        Queue<Map> placeholders = new LinkedList<>();

        while (fileScanner.hasNextLine()) {
            String templateLine = fileScanner.nextLine();

            // System.out.println("Template line: " + templateLine);

            // Replace templates with user responses to prompts
            // Find number of opening tags in line
            for (int i = 0; i < templateLine.length() - 1; i++) {
                if (templateLine.charAt(i) == '<') {
                    // Find the next closing tag
                    for (int j = i + 1; j < templateLine.length(); j++) {
                        if (templateLine.charAt(j) == '>') {
                            String placeholder = templateLine.substring(i, j + 1);
                            placeholders.add(Map.of(placeholder, ""));
                            i = j; // Move the outer loop index to the end of the current placeholder
                            break; // Exit the inner loop
                        }
                    }
                }
            }

            // System.out.println("Processed line: " + templateLine);
            
            // Write to output file
            // outputFile.println(templateLine);
            
        }
        // System.out.println("Placeholders: " + placeholders);

        // Create a file with the placeholders
        File placeHolderFile = new File("placeholders.txt");
        PrintStream newFileOutput = new PrintStream(placeHolderFile);
        


        while (!placeholders.isEmpty()) {
            Map<String, String> placeholder = placeholders.poll();
            for (String key : placeholder.keySet()) {
                newFileOutput.println(key + ": \n");
            }
        }

        // open the file in notepad

        try {
            Runtime.getRuntime().exec("notepad " + placeHolderFile.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error opening file in notepad: " + e);
        }

        // Prompt user for responses to Placeholders
        System.out.println("Please enter responses to the placeholders in the file: " + placeHolderFile);
        System.out.println("Press Enter to continue after filling responses");

        // check for enter key
        console.nextLine();

        // Close the file
        newFileOutput.close();

        // Read the responses from the file
        
        File fileToRead = new File("placeholders.txt");
        Scanner newFileScanner = new Scanner(fileToRead, "UTF-8");

        System.out.println("fileToRead: " + fileToRead);
        System.out.println("newFileScanner: " + newFileScanner);
        System.out.println("newFileScanner.hasNextLine(): " + newFileScanner.hasNextLine());

        while (newFileScanner.hasNextLine()) {
            String line = newFileScanner.nextLine();

            String prompt = line.substring(0, line.indexOf(">:") + 1);
            if(prompt.length() == 0) {
                continue;
            }
            // print prompt and answer
            System.out.println("Prompt: " + prompt);
           
            String answer = line.substring(line.indexOf(">:") + 2).trim();

            System.out.println("Answer: " + answer);

        }


        // Create output file
        PrintStream outputFile = new PrintStream(
                new File(
                        file.getName().replace(".txt", "--template-filled--" + numGenerator.nextInt(1000000) + "-.txt")));

        // process the file again
        Scanner fileScanner2 = new Scanner(file, "UTF-8");

        while (fileScanner2.hasNextLine()) {
            String templateLine = fileScanner2.nextLine();

            // System.out.println("Template line: " + templateLine);

            // Replace templates with user responses to prompts
            // Find number of opening tags in line
            for (int i = 0; i < templateLine.length() - 1; i++) {
                if (templateLine.charAt(i) == '<') {
                    // Find the next closing tag
                    for (int j = i + 1; j < templateLine.length(); j++) {
                        if (templateLine.charAt(j) == '>') {
                            String placeholder = templateLine.substring(i, j + 1);
                            System.out.println("Placeholder: " + placeholder);
                            System.out.println("Placeholders: " + placeholders);
                            for (Map<String, String> map : placeholders) {
                                for (String key : map.keySet()) {
                                    if (key.equals(placeholder.replace("<", "").replace(">",""))) {
                                        // System.out.println("Found key: " + key);
                                        // System.out.println("Found value: " + map.get(key));
                                        templateLine = templateLine.replace(placeholder, map.get(key));
                                    }
                                }
                            }
                            i = j; // Move the outer loop index to the end of the current placeholder
                            break; // Exit the inner loop
                        }
                    }
                }
            }

            // System.out.println("Processed line: " + templateLine);

            // Write to output file
            outputFile.println(templateLine);

        }



        // Write the processed line to the output file
        // outputFile.println(templateLine);





        



        // System.out.println("File processed. Output file created: " + outputFile);
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