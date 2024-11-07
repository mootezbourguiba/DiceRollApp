package tn.esprit.dicerollapp

class Dice(val diceNumber: Int){


    fun rollDice() : Int{
        return (1..diceNumber).random()
    }
}