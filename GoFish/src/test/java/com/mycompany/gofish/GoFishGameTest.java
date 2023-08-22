/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.gofish;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author talha
 */
public class GoFishGameTest {
    
    public GoFishGameTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of initializeDeck method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testInitializeDeck() {
        System.out.println("initializeDeck");
        GoFishGame instance = new GoFishGame();
        instance.initializeDeck();
        assertEquals(52, instance.deck.size());
    }

    /**
     * Test of dealInitialCards method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testDealInitialCards() {
        System.out.println("dealInitialCards");
        GoFishGame instance = new GoFishGame();
        instance.initializeDeck();
        instance.dealInitialCards();
        assertEquals(7, instance.player1Hand.size());
        assertEquals(7, instance.player2Hand.size());
    }

    /**
     * Test of drawCard method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testDrawCard() {
        System.out.println("drawCard");
        GoFishGame instance = new GoFishGame();
        int expResult = 51;
        instance.initializeDeck();
        Card drawnCard = instance.drawCard();
        assertNotNull(drawnCard);
        assertEquals(expResult, instance.deck.size());
    }

    /**
     * Test of switchPlayer method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testSwitchPlayer() {
        System.out.println("switchPlayer");
        GoFishGame instance = new GoFishGame();
        instance.getPlayers().add(new HumanPlayer("Player 1"));
        instance.getPlayers().add(new HumanPlayer("Player 2"));
        instance.initializeDeck();
        instance.dealInitialCards();
        Player initialPlayer = instance.currentPlayer;
        instance.switchPlayer();
        Player switchedPlayer = instance.currentPlayer;
        assertNotEquals(initialPlayer, switchedPlayer);
    }

    /**
     * Test of checkForBook method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testCheckForBook() {
        System.out.println("checkForBook");
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Rank.TWO, Suit.SPADES));
        hand.add(new Card(Rank.TWO, Suit.DIAMONDS));
        hand.add(new Card(Rank.TWO, Suit.HEARTS));
        hand.add(new Card(Rank.TWO, Suit.CLUBS));
        GoFishGame instance = new GoFishGame();
        boolean expResult = true;
        boolean result = instance.checkForBook(hand);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeBookFromHand method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testRemoveBookFromHand() {
        System.out.println("removeBookFromHand");
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Rank.TWO, Suit.SPADES));
        hand.add(new Card(Rank.TWO, Suit.DIAMONDS));
        hand.add(new Card(Rank.TWO, Suit.HEARTS));
        hand.add(new Card(Rank.TWO, Suit.CLUBS));
        GoFishGame instance = new GoFishGame();
        instance.removeBookFromHand(hand, Rank.TWO);
        assertEquals(0, hand.size());
    }

    /**
     * Test of getPlayerHand method, of class GoFishGame.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerHand() {
        System.out.println("getPlayerHand");
        GoFishGame instance = new GoFishGame();
        instance.getPlayers().add(new HumanPlayer("Player 1"));
        instance.getPlayers().add(new HumanPlayer("Player 2"));
        instance.initializeDeck();
        instance.dealInitialCards();
        Player player = instance.currentPlayer;
        List<Card> result = instance.getPlayerHand(player);
        assertNotNull(result);
    }
    
}
