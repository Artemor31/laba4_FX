package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// 2. Parse
// 3. Write all data to outputField



public class HTMLParser {

    Scanner scanner = new Scanner(System.in);
    List<String> tagsToSave = new ArrayList<>();

    public void addTag(String newTag){
        tagsToSave.add(newTag);
    }

    public void resetTagList(){
        tagsToSave = new ArrayList<>();
    }

    public String parse(String inputFile) {
        String html = inputFile;
        String replacedHtml = replaceWantedTags(tagsToSave, html);
        html = replaceUnwantedTags(replacedHtml);
        html = recoverWantedTags(tagsToSave, html);
        return html;
    }


    private String recoverWantedTags(List<String> tagsToSave, String html) {
        String regex;
        String replacedHtml = html;
        for (int i = 0; i < tagsToSave.size(); i++) {
            regex = "buffer" + i;
            replacedHtml = replacedHtml.replaceAll(regex, "<" + tagsToSave.get(i));
        }
        return replacedHtml;
    }

    private String replaceWantedTags(List<String> tagsToSave, String html) {
        String regex;
        String replacedHtml = html;
        for (int i = 0; i < tagsToSave.size(); i++) {
            regex = "<" + tagsToSave.get(i);
            replacedHtml = replacedHtml.replaceAll(regex, "buffer" + i);
        }
        return replacedHtml;
    }

    private String replaceUnwantedTags(String replacedHtml) {
        String html;
        html = replacedHtml.replaceAll("(<\\w+)[^>]*(>)", "$1$2");
        return html;
    }

    private void writeToFile(String outputStr){
        String path = "newIndex.html";
        File file = new File(path);
        String[] words = outputStr.split(">");
        for (int i = 0; i < words.length; i++) {
            words[i] += ">";
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for(String str : words)
                printWriter.println(str);
            printWriter.close();

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("Can't write to file");
        }
    }

    public String readFromFile() {
        String path = "index.html";
        File file = new File(path);
        String fileInput = "";
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileInput += scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            System.out.println("Can't open file");
        }
        return fileInput;
    }
}