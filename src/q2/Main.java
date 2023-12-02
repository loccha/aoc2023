package q2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {


    public static void main (String[] args) throws IOException {

        //set maxval
        HashMap<String,Integer> maxVal = new HashMap<>();

        maxVal.put("red", 12);
        maxVal.put("green", 13);
        maxVal.put("blue", 14);

        //readfile
        BufferedReader br = new BufferedReader(new FileReader("src/q2/input.txt"));

        int total=0;
        String line;

        while((line = br.readLine()) != null) {

            String[] games = line.split(":");
            boolean conditionNotRespected = false;
            int gameID = Integer.parseInt(games[0].split(" ")[1]);
            String[] gamesSet = games[1].split(";");

            for(String s : gamesSet){
                String[] game = s.split(",");
                if(conditionNotRespected = !verify(game, maxVal)){ break; }
            }

            if(conditionNotRespected)  {continue;} //at least one condition is not respected

            total += gameID;
        }


        System.out.println("Total: " + total);
    }



    // verifies if condition are respected
    public static boolean verify(String[] game, HashMap<String, Integer> maxVal){

        for(String colors : game){
            int numberOfBoxes = Integer.parseInt(colors.trim().split(" ")[0]);
            String color = colors.trim().split(" ")[1];
            if(numberOfBoxes > maxVal.get(color)){
                return false;
            }
        }
        return true;
    }




}

