/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gofish;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author talha
 */
public class GoFishGame extends Game {

    public List<Card> deck;
    public List<Card> player1Hand;
    public List<Card> player2Hand;
    private Random random;
    public Player currentPlayer;

    public GoFishGame() {
        super("Go Fish");
        deck = new ArrayList<>();
        player1Hand = new ArrayList<>();
        player2Hand = new ArrayList<>();
        random = new Random();
    }

    // Initialize the deck with cards
    public void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(rank, suit));
            }
        }
    }

    // Deal initial cards to players
    public void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player1Hand.add(drawCard());
            player2Hand.add(drawCard());
        }
    }

    // Draw a card from the deck
    public Card drawCard() {
        int index = random.nextInt(deck.size());
        return deck.remove(index);
    }

    // Switch to the next player
    public void switchPlayer() {
        if (currentPlayer == getPlayers().get(0)) {
            currentPlayer = getPlayers().get(1);
        } else {
            currentPlayer = getPlayers().get(0);
        }
    }

    // Check if a player has a book (a set of four cards of the same rank)
    public boolean checkForBook(List<Card> hand) {
        for (Rank rank : Rank.values()) {
            int count = 0;
            for (Card card : hand) {
                if (card.getRank() == rank) {
                    count++;
                }
            }
            if (count == 4) {
                removeBookFromHand(hand, rank);
                return true;
            }
        }
        return false;
    }

    // Remove a book (a set of four cards of the same rank) from the player's hand
    public void removeBookFromHand(List<Card> hand, Rank rank) {
        hand.removeIf(card -> card.getRank() == rank);
    }

    // Ask the other player for a card of a specific rank
    private void askOtherPlayer(Player otherPlayer, Rank rank) {
        List<Card> otherPlayerHand = getPlayerHand(otherPlayer);
        List<Card> cardsToGive = new ArrayList<>();
        for (Card card : otherPlayerHand) {
            if (card.getRank() == rank) {
                cardsToGive.add(card);
            }
        }
        otherPlayerHand.removeAll(cardsToGive);
        getPlayerHand(currentPlayer).addAll(cardsToGive);
        if (cardsToGive.isEmpty()) {
            System.out.println(otherPlayer.getName() + " says, 'Go fish!'");
            goFish();
        } else {
            System.out.println(otherPlayer.getName() + " gives " + currentPlayer.getName() + " " + cardsToGive.size() + " card(s) of rank " + rank);
            if (!checkForBook(getPlayerHand(currentPlayer))) {
                switchPlayer();
            }
        }
    }

    // Perform the "Go Fish" action
    private void goFish() {
        List<Card> currentPlayerHand = getPlayerHand(currentPlayer);
        if (deck.isEmpty()) {
            System.out.println("Deck is empty!");
            switchPlayer();
            return;
        }
        Card drawnCard = drawCard();
        currentPlayerHand.add(drawnCard);
        System.out.println(currentPlayer.getName() + " draws a " + drawnCard);
        if (checkForBook(currentPlayerHand)) {
            switchPlayer();
        } else {
            switchPlayer();
        }
    }

    // Get the hand of a player
    public List<Card> getPlayerHand(Player player) {
        if (player == getPlayers().get(0)) {
            return player1Hand;
        } else {
            return player2Hand;
        }
    }

    @Override
    public void play() {
        initializeDeck();
        dealInitialCards();
        currentPlayer = getPlayers().get(0);

        while (!deck.isEmpty() || !player1Hand.isEmpty() || !player2Hand.isEmpty()) {
            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            displayHand(getPlayerHand(currentPlayer));
            String input = currentPlayer.selectRank();
            try {
                if(Integer.parseInt(input)==-1){
                    declareWinner();
                    System.exit(0);
                }
            }catch (Exception e){

            }
            Rank rank = Rank.valueOf(input);

            Player otherPlayer = getPlayers().get(1 - getPlayers().indexOf(currentPlayer));
            askOtherPlayer(otherPlayer, rank);
        }
    }

    // Display the cards in a player's hand
    private void displayHand(List<Card> hand) {
        System.out.println(currentPlayer.getName() + "'s hand:");
        for (Card card : hand) {
            System.out.println(card);
        }
    }

    @Override
    public void declareWinner() {
        int player1Score = player1Hand.size() / 4;
        int player2Score = player2Hand.size() / 4;

        System.out.println("\nGame over!");
        System.out.println("Scores:");
        System.out.println(getPlayers().get(0).getName() + ": " + player1Score);
        System.out.println(getPlayers().get(1).getName() + ": " + player2Score);

        if (player1Score > player2Score) {
            System.out.println(getPlayers().get(0).getName() + " wins!");
        } else if (player2Score > player1Score) {
            System.out.println(getPlayers().get(1).getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
