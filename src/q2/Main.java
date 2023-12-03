package q2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

public class Main {


    public static void main (String[] args) throws IOException {

        //set maxval
        HashMap<String,Integer> maxValToPlay = new HashMap<>();


        maxValToPlay.put("red", 12);
        maxValToPlay.put("green", 13);
        maxValToPlay.put("blue", 14);

        //readfile
        BufferedReader br = new BufferedReader(new FileReader("src/q2/input.txt"));

        int total=0;
        String line;
        int totalPower = 0;

        while((line = br.readLine()) != null) {
            HashMap<String,Integer> minValToPlay = new HashMap<>();
            String[] gameEntry = line.split(":");
            boolean conditionNotRespected = false;
            int gameID = Integer.parseInt(gameEntry[0].split(" ")[1]);
            String[] gamesSet = gameEntry[1].split(";");

            for(String s : gamesSet){
                String[] game = s.split(",");
                minimalRequirements(game, minValToPlay);

                if(!verify(game, maxValToPlay)){conditionNotRespected = true;}
            }

            int power = computePower(minValToPlay);

            totalPower += power;

            if(conditionNotRespected)  {continue;} //at least one condition is not respected
            total += gameID;
        }

        System.out.println("Total: " + total);
        System.out.println("Total power: " + totalPower);
    }

    private static int computePower(HashMap<String, Integer> minValToPlay) {
        Collection <Integer> allBoxRequirementPerColor = minValToPlay.values();
        int power = 1;
        for(Integer val : allBoxRequirementPerColor){ power *= val; }
        return power;
    }

    private static void minimalRequirements(String[] game, HashMap<String, Integer> minValToPlay) {
        for(String colors : game){
            int numberOfBoxes = Integer.parseInt(colors.trim().split(" ")[0]);
            String color = colors.trim().split(" ")[1];

            if(minValToPlay.containsKey(color)){
                if(minValToPlay.get(color)<numberOfBoxes){
                    minValToPlay.replace(color, numberOfBoxes); //le plus.
                }
            } else
                minValToPlay.put(color, numberOfBoxes);


        }
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

