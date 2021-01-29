package kr.ukinas.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.children
import kr.ukinas.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //  0 - cross 1 - circle
    var activePlayer = 0
    val gameState = arrayListOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    val winningPositions = listOf(
        listOf(0, 1, 2),
        listOf(3, 4, 5),
        listOf(6, 7, 8),
        listOf(0, 3, 6),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(0, 4, 8),
        listOf(2, 4, 6)
    )
    var gameActive = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun dropIn(view: View) {
        val slotImage = view as ImageView
        val tappedSlot = slotImage.tag.toString().toInt()

        if (gameState[tappedSlot] == 2 && gameActive) {
            gameState[tappedSlot] = activePlayer
            slotImage.translationY = -2000f
            if (activePlayer == 0) {
                slotImage.setImageResource(R.drawable.tttx)
                activePlayer = 1
            } else {
                slotImage.setImageResource(R.drawable.ttto)
                activePlayer = 0
            }
            slotImage.animate().translationYBy(2000f).rotation(1800f).setDuration(500)
        }

        for (combo in winningPositions) {
            if (gameState[combo[0]] == gameState[combo[1]] &&
                gameState[combo[1]] == gameState[combo[2]] && gameState[combo[0]] != 2
            ) {

                var winner = ""
                if (activePlayer == 0) winner = "Circles"
                else if (activePlayer == 1) winner = "Crosses"
                Toast.makeText(this, "We Have A Winner! $winner!", Toast.LENGTH_LONG).show()
                gameActive = false
                val playAgainButton = binding.button
                playAgainButton.visibility = View.VISIBLE
            }
        }
    }

    fun resetGame(view: View) {
        binding.button.visibility = View.INVISIBLE

        for(tappedSlot in 0..8) { gameState[tappedSlot] = 2 }
        gameActive = true

        binding.root.children.forEach {
            if (it !is Button) {
                val slot = it as ImageView
                if( slot.tag != null ) {
                    slot.setImageDrawable(null)
                }
            }
        }
    }
}
