package com.example.tictactoetaller

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoetaller.databinding.ActivityTicTacToeBinding

class TicTacToeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTicTacToeBinding
    private var currentPlayer = "X" // Jugador actual, comienza con X
    private var gameState = Array(3) { arrayOfNulls<String>(3) }// Estado del juego, matriz 3x3 para almacenar los movimientos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val buttons = listOf(binding.button1, binding.button2, binding.button3, binding.button4, binding.button5, binding.button6, binding.button7, binding.button8, binding.button9)
        for (button in buttons) {
            button.setOnClickListener {
                onButtonClick(it as Button)
            }
        }
        binding.newGameButton.setOnClickListener {
            resetGame()
        }
        updatePlayerTurn()
    }

    // Maneja los clics en los botones del juego
    private fun onButtonClick(button: Button) {
        val tag = button.tag?.toString()?.toIntOrNull()

        if (tag == null) {
            Log.e("TicTacToeActivity", "no hay tag para el boton: ${button.id}")
            return
        }

        val row = tag / 3 // Calcula la fila a partir del tag
        val col = tag % 3 // Calcula la columna a partir del tag

        Log.d("TicTacToeActivity", "fila: $row, Column: $col")
        // Realiza el movimiento si la celda está vacía
        if (gameState[row][col] == null) {
            gameState[row][col] = currentPlayer
            button.text = currentPlayer

            if (checkAndHighlightWinner()) {
                Log.d("TicTacToeActivity", "Ganador Encontrado")
                disableButtons()
            } else {
                switchPlayer()
            }
        } else {
            Log.d("TicTacToeActivity", "Puesto ocupado")
        }
    }
    // Verifica si hay un ganador en el juego
    private fun checkAndHighlightWinner(): Boolean {
        val winningButtons = mutableListOf<Button>()
        Log.d("TicTacToeActivity", "Verificando si hay ganador...")

        // Comprobación horizontal
        for (i in 0..2) {
            if (gameState[i][0] == currentPlayer && gameState[i][1] == currentPlayer && gameState[i][2] == currentPlayer) {
                winningButtons.addAll(listOf(
                    getButtonForPosition(i, 0),
                    getButtonForPosition(i, 1),
                    getButtonForPosition(i, 2)
                ))
                break
            }
        }

        // Comprobación vertical
        for (i in 0..2) {
            if (gameState[0][i] == currentPlayer && gameState[1][i] == currentPlayer && gameState[2][i] == currentPlayer) {
                winningButtons.addAll(listOf(
                    getButtonForPosition(0, i),
                    getButtonForPosition(1, i),
                    getButtonForPosition(2, i)
                ))
                break
            }
        }

        // Comprobación diagonal
        if (gameState[0][0] == currentPlayer && gameState[1][1] == currentPlayer && gameState[2][2] == currentPlayer) {
            winningButtons.addAll(listOf(
                binding.button1,
                binding.button5,
                binding.button9
            ))
        } else if (gameState[0][2] == currentPlayer && gameState[1][1] == currentPlayer && gameState[2][0] == currentPlayer) {
            winningButtons.addAll(listOf(
                binding.button3,
                binding.button5,
                binding.button7
            ))
        }

        return if (winningButtons.isNotEmpty()) {
            for (button in winningButtons) {
                button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.green)
            }
            true
        } else {
            Log.d("TicTacToeActivity", "Ganador no encontrado")
            false
        }
    }

    // Obtiene el botón correspondiente a una posición específica en la matriz
    private fun getButtonForPosition(row: Int, col: Int): Button {
        return when (row * 3 + col) {
            0 -> binding.button1
            1 -> binding.button2
            2 -> binding.button3
            3 -> binding.button4
            4 -> binding.button5
            5 -> binding.button6
            6 -> binding.button7
            7 -> binding.button8
            8 -> binding.button9
            else -> throw IllegalArgumentException("posicion no valida del boton")
        }
    }


    // Desactiva todos los botones del juego
    private fun disableButtons() {
        val buttons = listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )
        for (button in buttons) {
            button.isEnabled = false
        }
    }


    // Reinicia el estado del juego
    private fun resetGame() {
        Log.d("TicTacToeActivity", "Reiniciando el juego ")
        gameState = Array(3) { arrayOfNulls<String>(3) }
        currentPlayer = "X"
        for (button in listOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )) {
            button.text = ""
            button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.blue)
            button.isEnabled = true
        }
        updatePlayerTurn()
    }


    // Cambia el turno del jugador actual
    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
        Log.d("TicTacToeActivity", "Cambio de jugador a $currentPlayer")
        updatePlayerTurn()
    }



    // Actualiza el estado visual del turno del jugador actual resaltando de azul y textcolor blanco
    private fun updatePlayerTurn() {
        Log.d("TicTacToeActivity", "Cambiando el resaltado del actual jugador")
        val lightBlue = ContextCompat.getColor(this, R.color.lightblue)
        val transparent = ContextCompat.getColor(this, android.R.color.transparent)
        val white = ContextCompat.getColor(this, R.color.white)
        val darkgreen = ContextCompat.getColor(this, R.color.darkgreen)

        if (currentPlayer == "X") {
            binding.playerOneTextView.setBackgroundColor(lightBlue)
            binding.playerTwoTextView.setBackgroundColor(transparent)
            binding.playerOneTextView.setTextColor(white)
            binding.playerTwoTextView.setTextColor(darkgreen)
        } else {
            binding.playerTwoTextView.setBackgroundColor(lightBlue)
            binding.playerOneTextView.setBackgroundColor(transparent)
            binding.playerOneTextView.setTextColor(darkgreen)
            binding.playerTwoTextView.setTextColor(white)
        }
    }
}