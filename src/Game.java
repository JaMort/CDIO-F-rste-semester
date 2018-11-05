import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game {
    private String pName1, pName2;
    private Dice masterDice = new Dice();
    private Player firstPlayer;
    private Player secondPlayer;
    private int winCon = 3000;
    Main main = new Main();
    JSONObject jText = new JSONObject();
    JSONParser parser = new JSONParser();

    public Game(Player player1, Player player2, Dice dice1, String playerName1, String playerName2) {
        firstPlayer = player1;
        secondPlayer = player2;
        masterDice = dice1;
        pName1 = playerName1;
        pName2 = playerName2;
    }
    public Game(Player player1, Player player2){
        firstPlayer = player1;
        secondPlayer = player2;
    }

    /** Passes the turn to the next player */
    public void turnSwitch(Player playerOne, Player playerTwo){
        playerOne.setTurn(true);
        playerTwo.setTurn(false);
    }

    public void playTurn(Player playerOne, Player playerTwo) {
        masterDice.roll();
        try{
            Object obj = parser.parse(new FileReader(main.getJFile()));
            JSONObject jobj = (JSONObject) obj;
            System.out.println((String) jobj.get("rollP1") + masterDice.sum()+  jobj.get("rollP2"));
        switch (masterDice.sum()) {
            case 2: playerOne.balance.addPoints(250);
                System.out.println((String) jobj.get("sq2"));
                break;
            case 3: playerOne.balance.subPoints(100);
                System.out.println((String) jobj.get("sq3"));
                break;
            case 4: playerOne.balance.addPoints(100);
                System.out.println((String) jobj.get("sq4"));
                break;
            case 5: playerOne.balance.subPoints(20);
                System.out.println((String) jobj.get("sq5"));
                break;
            case 6: playerOne.balance.addPoints(180);
                System.out.println((String) jobj.get("sq6"));
                break;
            case 7:
                System.out.println((String) jobj.get("sq7"));
                break;
            case 8: playerOne.balance.subPoints(70);
                System.out.println((String) jobj.get("sq8"));
                break;
            case 9: playerOne.balance.addPoints(60);
                System.out.println((String) jobj.get("sq9"));
                break;
            case 10: playerOne.balance.subPoints(80);
                System.out.println((String) jobj.get("sq10"));
                break;
            case 11: playerOne.balance.subPoints(50);
                System.out.println((String) jobj.get("sq11"));
                break;
            case 12: playerOne.balance.addPoints(650);
                System.out.println((String) jobj.get("sq12"));

                break;
            default:
                System.out.println("Error, you did not roll a value between 2 and 12");
                break;
        }
        if(playerOne.getIsTurn())
            System.out.print(playerOne.toString());
        else
            System.out.print(playerTwo.toString());
        System.out.println((String) jobj.get("balance") + playerOne.getScore());
            System.out.println((String) jobj.get("balance") + playerTwo.getScore());
        if(masterDice.sum() != 10)turnSwitch(playerTwo, playerOne);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException f){
            f.printStackTrace();
        }}

    public boolean winGame(Player player1, Player player2){
        if (player1.balance.getPoints() >= 3000 ){
            //TODO implement winner string for player1
            return true;
        }else if(player2.balance.getPoints() >= 3000){
            //TODO implement winner string for player2
            return true;
        }else return false;
    }
}