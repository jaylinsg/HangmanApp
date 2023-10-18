package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers.anyChar
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GameActivityUnitTest {

    // Initialize your activity for testing
    private lateinit var gameActivity: GameActivity

    @Before
    fun setUp() {
        // Initialize any mocks
        MockitoAnnotations.initMocks(this)

        // Create an instance of the activity
        gameActivity = GameActivity()
    }

    @Test
    fun testHangmanGameInitialization() {
        // Ensure that hangmanGame is initialized
        assertNotNull(gameActivity.hangmanGame)
    }

    @Test
    fun testCorrectGuessUpdatesUI() {
        // Mock the hangmanGame to simulate a correct guess
        `when`(gameActivity.hangmanGame.guessLetter(anyChar())).thenReturn(true)

        // Save the initial hangman image resource
        val initialHangmanImageResource = gameActivity.getHangmanImageResource()

        // Perform a correct guess (replace 'A' with the correct letter)
        gameActivity.onLetterButtonClick(ButtonWithText("A"))

        // Verify that the hangman image resource has changed
        assertNotEquals(initialHangmanImageResource, gameActivity.getHangmanImageResource())
    }

    @Test
    fun testIncorrectGuessUpdatesUI() {
        // Mock the hangmanGame to simulate an incorrect guess
        `when`(gameActivity.hangmanGame.guessLetter(anyChar())).thenReturn(false)

        // Save the initial hangman image resource
        val initialHangmanImageResource = gameActivity.getHangmanImageResource()

        // Perform an incorrect guess (replace 'Z' with the incorrect letter)
        gameActivity.onLetterButtonClick(ButtonWithText("Z"))

        // Verify that the hangman image resource has changed
        assertNotEquals(initialHangmanImageResource, gameActivity.getHangmanImageResource())
    }
}