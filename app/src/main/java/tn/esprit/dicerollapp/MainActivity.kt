package tn.esprit.dicerollapp

// Importation des bibliothèques nécessaires en Kotlin et Android.
// Kotlin évite les importations inutiles.
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge // Extension Kotlin spécifique pour l'interface immersive.
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat // Classe utilitaire pour les vues avec compatibilité supplémentaire.
import androidx.core.view.WindowInsetsCompat // Utilisé pour la gestion des insets de fenêtre (ex : barre de statut).

// Déclaration de la classe principale MainActivity qui hérite de AppCompatActivity.
// En Kotlin, l'héritage des classes est simple avec le symbole ":", éliminant le besoin d'un mot-clé 'extends'.
class MainActivity : AppCompatActivity() {

    // Déclaration de la variable 'diceImage' pour l'image du dé.
    // Kotlin prend en charge l'initialisation tardive (lateinit) des variables non nullables qui seront initialisées plus tard.
    lateinit var diceImage: ImageView
    // 'rollBtn' est un bouton initialisé à null (nullable), un des avantages de Kotlin est son système de null safety.
    var rollBtn: Button? = null

    // Méthode 'onCreate' appelée lors de la création de l'activité.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 'enableEdgeToEdge' est une extension Kotlin pour activer l'affichage en plein écran, une fonctionnalité moderne pour Android.
        enableEdgeToEdge()

        // Associe l'activité à son layout XML (ici, 'activity_main').
        // Kotlin permet de simplifier le code, rendant l'initialisation des layouts plus claire.
        setContentView(R.layout.activity_main)

        // Utilisation de ViewCompat pour gérer les insets des fenêtres et assurer que l'interface prend en compte les barres système.
        // Le gestionnaire d'insets est implémenté avec une lambda, illustrant l'utilisation courante des fonctions lambda en Kotlin.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            // Récupère les dimensions des barres système (status bar et navigation bar).
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            // Applique des marges aux vues pour qu'elles n'entrent pas en collision avec les barres système.
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialisation de 'diceImage' avec l'ImageView définie dans le layout.
        // Kotlin élimine le besoin de cast explicite pour les vues, simplifiant l'accès aux composants.
        diceImage = findViewById(R.id.diceImage)
        // 'rollBtn' est initialisé de manière nullable, évitant les erreurs de nullité communes en Java.
        rollBtn = findViewById(R.id.rollBtn)

        // Appel de la fonction pour afficher une image de dé au démarrage.
        displayDiceImage()

        // Définit un écouteur d'événements sur le bouton pour relancer le dé lorsqu'il est cliqué.
        // En Kotlin, les lambda expressions rendent ce type de code plus lisible et plus compact.
        rollBtn!!.setOnClickListener {
            displayDiceImage()
        }
    }

    // Fonction pour générer un numéro aléatoire de 1 à 6 et afficher l'image du dé correspondant.
    // En Kotlin, les fonctions peuvent être définies simplement avec le mot-clé 'fun' sans déclaration explicite de retour si inutile.
    fun displayDiceImage() {
        // Création d'un objet Dice avec 6 faces.
        val dice = Dice(6)
        // Appel de la méthode rollDice() qui génère un numéro aléatoire entre 1 et 6.
        val generatedNumber = dice.rollDice()

        // Structure when équivalente à switch en Java, mais plus puissante en Kotlin.
        // Elle permet de gérer plusieurs conditions de manière concise.
        when (generatedNumber) {
            1 -> diceImage.setImageResource(R.drawable.dice_1) // Met à jour l'image pour un dé montrant 1.
            2 -> diceImage.setImageResource(R.drawable.dice_2) // Met à jour l'image pour un dé montrant 2.
            3 -> diceImage.setImageResource(R.drawable.dice_3) // Met à jour l'image pour un dé montrant 3.
            4 -> diceImage.setImageResource(R.drawable.dice_4) // Met à jour l'image pour un dé montrant 4.
            5 -> diceImage.setImageResource(R.drawable.dice_5) // Met à jour l'image pour un dé montrant 5.
            6 -> diceImage.setImageResource(R.drawable.dice_6) // Met à jour l'image pour un dé montrant 6.
        }
    }
}
