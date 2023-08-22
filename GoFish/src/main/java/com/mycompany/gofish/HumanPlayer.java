/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gofish;

import java.util.Scanner;

/**
 *
 * @author talha
 */
public class HumanPlayer extends Player{
    private Scanner scanner;
    public boolean endgame = false;

    public HumanPlayer(String name) {
        super(name);
        scanner = new Scanner(System.in);
    }

    @Override
    public void play() {
        // The human player's turn logic
        // Implement the specific gameplay actions for the human player
    }

    @Override
    public String selectRank() {
        System.out.print("Enter the rank to ask for(or enter -1 to exit): ");
        String input = scanner.nextLine().toUpperCase();
        //if(Integer.parseInt(input) == -1){
          //  endgame = true;

        //}
        return input;
    }
}
