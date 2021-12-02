package com.example.ohoodal_harthi

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var recView: RecyclerView
    lateinit var showOP: TextView
    lateinit var answer: EditText
    lateinit var submitButton: Button
    lateinit var score: TextView
    var result = ArrayList<String>()
    lateinit var randomQuestions: String
    var correctAns = ""
    var scoreIncrease = 0
    var  highScore = 4


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        customAlert(
            "Math Study App ",
            "Welcome to Math Study App !How many questions can you solve?"
        )
        recView = findViewById(R.id.recView)
        showOP = findViewById(R.id.showOp)
        answer = findViewById(R.id.answer)
        score = findViewById(R.id.scoreTV)
        submitButton = findViewById(R.id.submit)

      sharedPreferences = this.getSharedPreferences(
         getString(R.string.ScoreSP), Context.MODE_PRIVATE)
        highScore = sharedPreferences.getInt(getString(R.string.Score),0)
        with(sharedPreferences.edit()) {
            putInt(getString(R.string.Score), highScore)
            apply()
        }

        var answ = answer.text.toString()


        val questions = mapOf("9+2 = " to 10, "5+3 =" to 8, "10+3 = " to 13, "9+4 =" to 13)

        var randomQ = questions.entries.elementAt(Random.nextInt(questions.size))
        correctAns = randomQ.value.toString()

        showOP.text = randomQ.key







        submitButton.setOnClickListener {

            if (answ.equals(correctAns)) {

                scoreIncrease++
                score.text = "High Score:$highScore \n score $scoreIncrease"
                var store = randomQ.key+answ
                result.add(store)
                recView.adapter=RecyclerViewAdapter( result)
                recView.layoutManager = LinearLayoutManager(this)
                updateScore(scoreIncrease)

                val questions = mapOf("20+10 " to 30, "15+14" to 29, "22+13" to 35, "16+8" to 24)

                var randomQ = questions.entries.elementAt(Random.nextInt(questions.size))
                correctAns = randomQ.value.toString()

                showOP.text = randomQ.key


            }
            else {
                customAlert1(
                    "Math Study App ","play agin?"


                )
            }

        }


    }

    fun customAlert(title: String, message: String) {

        val builder = AlertDialog.Builder(this)
        //  set title for alert dialog
        builder.setTitle(title)
        //  set message for alert dialog
        message.toString()
        builder.setMessage(message)

        builder.setIcon(android.R.drawable.ic_dialog_info)

        //performing positive action
        builder.setPositiveButton("Start") { dialogInterface, which ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun customAlert1(title: String, message: String) {

        val builder = AlertDialog.Builder(this)
        //  set title for alert dialog
        builder.setTitle(title)
        //  set message for alert dialog
        message.toString()
        builder.setMessage(message)

        builder.setIcon(android.R.drawable.ic_dialog_info)

        //performing positive action
        builder.setPositiveButton("yes") { dialogInterface, which ->
        clear()

        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    /* fun randomQuestions () :String{
     randomQuestions.add("9+2")
        randomQuestions.add("5+3")
        randomQuestions.add("10+3")
        randomQuestions.add("9+4")
        randomQuestions.add("7+3")
        randomQuestions.add("8+4")
        randomQuestions.add("1+7")
        randomQuestions.add("2+10")
        randomQuestions.add("4+3")
        randomQuestions.add("5+7")
        randomQ= randomQuestions[Random.nextInt(0, randomQuestions.size)]
        return randomQ
    }*/



    fun clear (){
        updateScore(0)
        scoreIncrease = 0
       score.text = "Score: $scoreIncrease"
        result.clear()
        recView.adapter = RecyclerViewAdapter(result)


    }

    private fun updateScore(score: Int) {
        with(sharedPreferences.edit()) {
            putInt(getString(R.string.Score), score)
            apply()
        }
    }


}


