package blackjack;

import java.util.Scanner;

/**
 *
 * @author Lola
 */
public class BlackJack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        GUI gui = new GUI();
        Game game = new Game();
        game.generates_the_card();

        //Enter Name Of Player
        for (int i = 0; i < 4; i++) {
            game.Informations_player();
        }

        gui.runGUI(game.card, game.players[0].card, game.players[1].card, game.players[2].card, game.players[3].card);

        for (int i = 0; i < 3; i++) {
            System.out.printf("It's the player %d turn \n", i + 1);
            game.turn_player(i);
        }
        while (true) {
            int d = 2;
            if (game.players[3].score == 21) {
                System.out.printf(" %s is BlackJack and Score = %d \n",
                        game.players[3].name, game.players[3].score);
                game.players[3].blackJack = true;
                game.Max_Score(3);
                break;

            } else if (game.players[3].score > 21) {
                System.out.printf("Player : %s is BUSTED and Score = %d \n",
                        game.players[3].name, game.players[3].score);
                game.players[3].lost = true;
                break;
            } else {
                Card cra_dra_d = new Card(game.Draw_Random_Card());
                game.players[3].card[d] = cra_dra_d;
                game.players[3].score += cra_dra_d.value;
                gui.updateDealerHand(cra_dra_d, game.card);
                game.Max_Score(3);
                d++;
            }

        }

        int countBlack = 0;
        int push = 0;
        
        for (int i = 0; i < 4; i++) {

            if (game.players[i].blackJack) {
                countBlack++;
            }
            
        }
       for(int j = 0 ; j<3 ; j++)
        {
            if(game.players[j].score == game.players[j+1].score && game.players[j].score <=21  )
            {
                push++ ; 
                //0 1 //1 2 // 2 3 //
            }
        }
        /*if (   game.players[0].score == game.players[1].score ||
                        game.players[0].score == game.players[2].score ||
                        game.players[0].score == game.players[3].score ||
                        game.players[1].score == game.players[2].score ||
                        game.players[1].score == game.players[3].score ||
                        game.players[2].score == game.players[3].score
                    )
                    {
                        push ++ ;
                        
                    }
        */
        if (countBlack > 1 || push > 0) {
            System.out.println("This game is PUSH");
        } else {
            System.out.printf("The Player %d WINs and score = %d \n",
                    game.indexMaxScore+1 , game.maxScore);
        }

    }

}
