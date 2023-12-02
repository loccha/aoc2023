package q1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("src/q1/input.txt"));

        int total=0;

        String line = br.readLine();
        while(line!=null) {

            int extractedNumber = numberStrip(line);
            total += extractedNumber;
            line = br.readLine();
        }

        System.out.println("Total: " + total);
    }


    public static int numberStrip(String line){

        char[] lineTab = line.toCharArray();
        String firstDigit = null; String lastDigit = null;
        String extractedDigit;

        String[] digitInLetters = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for(int i=0; i<lineTab.length; i++){
            if(Character.isDigit(lineTab[i])){ firstDigit = String.valueOf(lineTab[i]);}
            else if ("oftsen".indexOf(lineTab[i])!=-1) {  //worth investigate
                for(String s : digitInLetters ){
                    int j = line.indexOf(s);
                    if(j == i) {
                        firstDigit = convert(s);    //found first digit index
                        break;
                    }
                }
            }
            if(firstDigit!=null){ break; } //firstDigitFound
        }

        for(int i=lineTab.length-1; i>=0; i--){
            if(Character.isDigit(lineTab[i])){ lastDigit = String.valueOf(lineTab[i]); }
            else if ("oftsen".indexOf(lineTab[i])!=-1){
                for(String s : digitInLetters) {
                    int j = line.lastIndexOf(s);
                    if(j == i) {
                        lastDigit = convert(s);
                        break;
                    }
                }
            }
            if(lastDigit!=null){ break; } //firstDigitFound
        }
        extractedDigit = firstDigit + lastDigit;

        return Integer.parseInt(extractedDigit);
    }


    public static String convert(String s){
        switch (s){
            case "one" :
                return "1";
            case "two":
                return "2";
            case "three":
                return "3";
            case "four":
                return "4";
            case "five":
                return "5";
            case "six":
                return "6";
            case "seven":
                return "7";
            case "eight":
                return "8";
            case "nine":
                return "9";
            default:
                return null;

        }
    }






}