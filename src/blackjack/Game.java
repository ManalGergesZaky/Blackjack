/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Lola
 */
import java.util.Random;
import java.util.Scanner;

public class Game {

    Player players[] = new Player[4];
    protected Card card[] = new Card[52];
    protected int maxScore = 0;
    protected int index = 0;
    protected int indexMaxScore = 0;
    protected int card_number = 0;

    public void generates_the_card() {

        for (int s = 0; s < 4; s++) {
            for (int v = 0; v < 13; v++) {

                if (v >= 10) {
                    card[card_number] = new Card(s, v, 10);

                } else if (v < 10) {
                    card[card_number] = new Card(s, v, v + 1);
                }

                card_number++;
            }
        }

    }

    public Card Draw_Random_Card() {
        Random rand = new Random();
        int randomChoice = rand.nextInt(52);
        while (card[randomChoice] == null) {

            randomChoice = rand.nextInt(52);
        }

        Card cardReturn = new Card(card[randomChoice]);
        card[randomChoice] = null;
        System.out.println(cardReturn.value);
        return cardReturn;
    }


    public void Informations_player() {
        Scanner input = new Scanner(System.in);
        if (index == 3) {
            players[3] = new Player("Dealer");
        } else {
            System.out.printf("Player %d : Enter the name : ", index + 1);
            String name = input.next();
            players[index] = new Player(name);
        }
        for (int i = 0; i < 2; i++) {
            players[index].card[i] = Draw_Random_Card();
            players[index].score += players[index].card[i].value;
        }
        index++;

    }

    public void Max_Score(int i) {
        if (players[i].score == 21) {
            maxScore = players[i].score;
            indexMaxScore = i;

        } else if (players[i].score > maxScore && players[i].score <= 21) {
            maxScore = players[i].score;
            indexMaxScore = i;
        }

    }

    public void turn_player(int i) {

        GUI gui = new GUI();
        Scanner input = new Scanner(System.in);
        int cardIndex = 2;

        while (players[i].score < 21) {

            System.out.println("Enter 1 to HIT or 2 to Stand ");
            int chose = input.nextInt();
            if (chose == 1) {
                Card card_rand = new Card(Draw_Random_Card());
                players[i].card[cardIndex] = card_rand;
                players[i].score += card_rand.value;
                gui.updatePlayerHand(card_rand, i);
                Max_Score(i);
            } else {
                Max_Score(i);
                System.out.printf("Player %d is Stand and this score = %d \n",
                        i + 1, players[i].score);
                break;
            }

        }
        if (players[i].score == 21) {
            System.out.printf("Player %d is BlackJack and this score = %d \n",
                    i + 1, players[i].score);
            players[i].blackJack = true;
            Max_Score(i);

        } else if (players[i].score > 21) {
            System.out.printf("Player %d is BUSTED and this score = %d \n",
                    i + 1, players[i].score);
            players[i].lost = true;

        }

    }

}
