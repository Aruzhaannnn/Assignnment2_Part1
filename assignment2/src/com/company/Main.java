package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("/Users/пользователь/Desktop/testFile.txt"); //File path
        Scanner sc = new Scanner(file); //Declaring scanner to scan through txt
        String content = "";
        char temp = ' ';

        while (sc.hasNextLine()) {
            content = content.concat(sc.nextLine()); //saving txt content into string
        }

        char array[] = content.toCharArray(); //converting string to char array

        Hashtable<Character, Double> hashtable = new Hashtable<>();


        /* STORING HASHTABLE KEYS WITHOUT VALUES */
        for (int i = 0; i < array.length; i++) {
            if (!hashtable.containsKey(array[i])) {
                hashtable.put(array[i], 0.0);
            }
        }

        ArrayList<Character> checked = new ArrayList<>(); //To reduce the number of iterations

        for (int i = 0; i < array.length; i++) {
            temp = array[i]; //creating temporary element to work with it
            int counter = 0;
            if (!checked.contains(temp)) {
                checked.add(temp); //in order to not check repeated elements, we save the current active element
                for (int j = 0; j < array.length; j++) {
                    if (array[j] == temp) {
                        counter++;
                    }   //to identify the number of occurrence in array
                }
                hashtable.put(temp, (double) counter / (double) array.length * 100);    //update the specific hashtable key with calculated probability as a value
            }
        }


        /* SORT THE HASHTABLE BY THE OCCURRENCE IN DESCENDING ORDER */
        List<Map.Entry<Character, Double>> list = new ArrayList<Map.Entry<Character, Double>>(hashtable.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Double>>() {
            public int compare(Map.Entry<Character, Double> entry1, Map.Entry<Character, Double> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        Map<Character, Double> mapSortedByValues = new LinkedHashMap<Character, Double>();
        for (Map.Entry<Character, Double> entry : list) {
            mapSortedByValues.put(entry.getKey(), entry.getValue());
        }

        /* PRINTING THE HASHTABLE */
        System.out.println(mapSortedByValues);
    }


}

