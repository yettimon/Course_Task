package com.company;

/*
* Classname : Main
*
* Date 23.06.2020
* @author Dmitriy Stavnichuk
*
* */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


public class Main {


    public static void main(String[] args) throws IOException {

        // creating array of distinct words
       ArrayList<String> DistinctWords = new ArrayList<>();
       // write into file
       FileWriter myWriter = new FileWriter("filename.txt");
       String fileHeader = " -------HEADER FOR HARRY POTTER TASK--------------";
       myWriter.write(fileHeader + "\n");

        String text = new String(Files.readAllBytes(Paths.get(
                "E:\\IdeaProjects\\Course_Task\\src\\com\\company\\Harry.txt")));

        String clearedText = text
                .toLowerCase()
                .replaceAll("[^a-zA-Z0-9' ]+", "");

        String[] distWords = clearedText.split(" ");
        // finding distinct words and their occurrences;
        for (int i = 0; i < distWords.length; i++) {
            int distCount = 1;
            for (int j = i + 1; j < distWords.length; j++) {
                if (distWords[i].equals(distWords[j])) {
                    distCount++;
                    distWords[j] = "0";
                }
            }
            if (distCount > 1 && distWords[i] != "0" && i < 20) {

               DistinctWords.add(distWords[i]);
               DistinctWords.add(Integer.toString(distCount));

                System.out.println("First 20 pairs of distinct words: "
                        + distWords[i] + " occurs "
                        + distCount + " times ");
                myWriter.write("First 20 pairs of distinct words: "
                        + distWords[i] + " occurs "
                        + distCount + " times ");
                myWriter.write(System.lineSeparator());
            }
            else{
                break;
            }
        }

        //finding names from book

        // Task 2. Count the lines where the names "Harry" is met.
        List<String> keys = new ArrayList<>();
        Map<String, Integer> twWords = new LinkedHashMap<>();

        String[] names = {"Harry","Hermiona","Ron","Dambldoor", "Voldemort" };
        String words2[] = text.split(" ");
        int counter = 0;
        for (int i = 0; i <names.length ; i++) {
            for (int f = 0; f < words2.length; f++) {
                if (names[i].equals(words2[f]))
                    counter++;
            }
            System.out.println("The word " + names[i] + " occurs "
                    + counter + " times in the above book");
            myWriter.write("The word " + names[i] + " occurs "
                    + counter + " times in the above book");
            myWriter.write(System.lineSeparator());
        }

        System.out.println(DistinctWords);
        myWriter.write(String.valueOf(DistinctWords));
        myWriter.close();


        List<String> IntoArray = new ArrayList<>();
        IntoArray = Arrays.asList(distWords);
//        for(String s: IntoArray){
//            System.out.println(s);
//        }


        // distincs by Map Method
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for(String word: IntoArray) {
            // if word is not in the map
            if(!map.containsKey(word))
                map.put(word, 0);
            map.put(word, map.get(word) + 1);
        }
        for(String word: map.keySet())
            if (map.get(word) > 1)
            System.out.println(word + " occurs " + map.get(word) + " times");

    }

}
