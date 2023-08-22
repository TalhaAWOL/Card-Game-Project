/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gofish;

/**
 *
 * @author talha
 */
public class Main {
    public static void main(String[] args) {
        GoFishGame game = new GoFishGame();
        game.getPlayers().add(new HumanPlayer("Player 1"));
        game.getPlayers().add(new HumanPlayer("Player 2"));
        game.play();
        game.declareWinner();
    }
}
