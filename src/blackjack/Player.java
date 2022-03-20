/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.Scanner;

/**
 *
 * @author Lola
 */
public class Player {

    String name;
    protected int score = 0;
    protected Card card[] = new Card[10];
    boolean blackJack = false;
    boolean lost = false;
    int cardIndex = 2;


    GUI gui = new GUI();
    Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }
    public Player() {
    }

   
}
