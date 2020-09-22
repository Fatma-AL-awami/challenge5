package com.example.challenge5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prev_Button: Button
    private lateinit var questionTextView: TextView

    public var test = 0;


    private val questionBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prev_Button = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

/////////////////textview onclick listener

        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
            trueButton.isEnabled=false;
            falseButton.isEnabled=false;

        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
            trueButton.isEnabled=false;
            falseButton.isEnabled=false;
        }
        updateQuestion()

        nextButton.setOnClickListener {
            trueButton.isEnabled=true;
            falseButton.isEnabled=true;
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        prev_Button.setOnClickListener {
            trueButton.isEnabled=false;
            falseButton.isEnabled=false;
            currentIndex = if (currentIndex == 0) {
                currentIndex + 1
            } else {
                currentIndex - 1
            }

            updateQuestion()
        }


    }


    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

    }

    private fun result(){
        if(test==(questionBank.size-1))
        {
            Toast.makeText(this, "Good luck 100%", Toast.LENGTH_SHORT)
                    .show()
        }
        else
        {
            var y=(test*10).toString()+"% Good luck"
            Toast.makeText(this, y, Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            test+=1;
            R.string.correct_toast

        } else {

            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show()
        if(currentIndex==(questionBank.size-1)) {
            nextButton.isEnabled = false;
            prev_Button.isEnabled = false
            result()
        }
    }

}

