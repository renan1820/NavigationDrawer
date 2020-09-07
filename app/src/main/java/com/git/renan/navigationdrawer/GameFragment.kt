package com.git.renan.navigationdrawer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.git.renan.navigationdrawer.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar

class GameFragment:Fragment() {
    data class Question(val text: String, val answers: List<String>,val idCorrect: Int)

    private lateinit var questions: MutableList<Question>

    lateinit var currentQuestion: Question
    lateinit var answers: List<String>
    var numQuestion: Int = 0
    private var questionIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)
        initQuestions()
        randomizeQuestions()

        binding.game = this

        binding.btContinue.setOnClickListener {
            view: View ->
            val checkedId = binding.radioGroup.checkedRadioButtonId

            if(-1 != checkedId){
                var answersIndex = 0
                when(checkedId){
                    R.id.second_radio -> answersIndex = 1
                    R.id.third_radio -> answersIndex = 2
                    R.id.four_radio -> answersIndex = 3
                }

                if(checkCorrectQuestion(questions,questionIndex,answersIndex)){
                    questionIndex++

                    if(questionIndex < numQuestion){
                        currentQuestion = questions[questionIndex]

                        setQuestion()
                        binding.invalidateAll()
                        binding.radioGroup.clearCheck()
                    }else{
                        //Correct all Questions
                        updateToolbar(true)
                        view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameWonFragment(numQuestion,questionIndex))
                    }
                }else{
                    //Error any question
                    updateToolbar(false)
                    view.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment())
                }
            }else{
                Snackbar.make(view,getString(R.string.fragment_game_error_snackbar), Snackbar.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }

    private fun randomizeQuestions(){
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion(){
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        answers.shuffled()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.fragment_game_title_appbar, questionIndex + 1, numQuestion)
    }

    private fun updateToolbar(success: Boolean){
        (activity as AppCompatActivity).supportActionBar?.title = if(success) getString(R.string.fragment_game_title_success_appbar) else getString(R.string.fragment_game_title_fail_appbar)

    }

    private fun checkCorrectQuestion(questions: MutableList<Question>, questionIndex: Int, chooseAnswers: Int): Boolean{
        return chooseAnswers.equals(questions[questionIndex].idCorrect)
    }
    private fun initQuestions(){
        questions = mutableListOf(
            Question(text = getString(R.string.question_one), answers = listOf(getString(R.string.question_one_option_1),getString(R.string.question_one_option_2),getString(R.string.question_one_option_3),getString(R.string.question_one_option_4)), idCorrect = 1),
            Question(text = getString(R.string.question_two), answers = listOf(getString(R.string.question_two_option_1),getString(R.string.question_two_option_2),getString(R.string.question_two_option_3),getString(R.string.question_two_option_4)), idCorrect = 1),
            Question(text = getString(R.string.question_three), answers = listOf(getString(R.string.question_three_option_1),getString(R.string.question_three_option_2),getString(R.string.question_three_option_3),getString(R.string.question_three_option_4)), idCorrect = 2),
            Question(text = getString(R.string.question_four), answers = listOf(getString(R.string.question_four_option_1),getString(R.string.question_four_option_2),getString(R.string.question_four_option_3),getString(R.string.question_four_option_4)), idCorrect = 0),
            Question(text = getString(R.string.question_five), answers = listOf(getString(R.string.question_five_option_1),getString(R.string.question_five_option_2),getString(R.string.question_five_option_3),getString(R.string.question_five_option_4)), idCorrect = 3),
            Question(text = getString(R.string.question_six), answers = listOf(getString(R.string.question_six_option_1),getString(R.string.question_six_option_2),getString(R.string.question_six_option_3),getString(R.string.question_six_option_4)), idCorrect = 2),
            Question(text = getString(R.string.question_seven), answers = listOf(getString(R.string.question_seven_option_1),getString(R.string.question_seven_option_2),getString(R.string.question_seven_option_3),getString(R.string.question_seven_option_4)), idCorrect = 2),
            Question(text = getString(R.string.question_eight), answers = listOf(getString(R.string.question_eight_option_1),getString(R.string.question_eight_option_2),getString(R.string.question_eight_option_3),getString(R.string.question_eight_option_4)), idCorrect = 0),
            Question(text = getString(R.string.question_nine), answers = listOf(getString(R.string.question_nine_option_1),getString(R.string.question_nine_option_2),getString(R.string.question_nine_option_3),getString(R.string.question_nine_option_4)), idCorrect = 2),
            Question(text = getString(R.string.question_ten), answers = listOf(getString(R.string.question_ten_option_1),getString(R.string.question_ten_option_2),getString(R.string.question_ten_option_3),getString(R.string.question_ten_option_4)), idCorrect = 3)
        )

        numQuestion = Math.min((questions.size + 1) / 2, 3)
    }

}