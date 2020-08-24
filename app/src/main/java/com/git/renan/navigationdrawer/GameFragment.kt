package com.git.renan.navigationdrawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.git.renan.navigationdrawer.databinding.FragmentGameBinding

class GameFragment:Fragment() {
    data class Question(val text: String, val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "Melhor jogo em andamento pelo TGA 2019", answers = listOf("Raindow Six Siegue","Fortnite","League of Legends","Dota 2")),//2
        Question(text = "Jogo que maior premia no e-sports", answers = listOf("Smite","Dota 2","League of Legends","Counter Strike: Global Offensive")),//2
        Question(text = "Jogo do ano pelo The Game Awards 2018", answers = listOf("Fortnite","Red Dead Redemption 2","God of War","Celeste")),//3
        Question(text = "Jogo do ano pelo TGA 2019", answers = listOf("Sekiro: Shadows Die Twice","Death Stranding","Call of Duty: Modern Warfare","Gris")),//1
        Question(text = "Jogo mais lucrativo da história", answers = listOf("God of War","GTA V","League of Legends","Red Dead Redemption 2")),//4
        Question(text = "Qual é o jogo que não é da Rockstar Games?", answers = listOf("Max Payne 3","L.A. Noire","Maple Story","Grand Theft Auto V")),//3
        Question(text = "Qual é o jogo de PC mais jogado do mundo em 2019?", answers = listOf("Raindow Six Siegue","Dota 2","League of Legends","Minecraft")),//3
        Question(text = "Melhor trilha sonora pelo The Game Awards 2018", answers = listOf("Red Dead Redemption 2","Forza Horizon 4","God of War","Fortnite")),//1
        Question(text = "Melhor direção de jogo pelo The Game Awards 2018", answers = listOf("Red Dead Redemption 2","Forza Horizon 4","God of War","Fortnite")),//3
        Question(text = "Melhor trabalho de áudio pelo The Game Awards 2019", answers = listOf("Disco Elysium","Luigi’s Mansion 3","Super Smash Bros. Ultimate","Call of Duty: Modern Warfare"))//4
    )

    lateinit var currentQuestion: Question
    lateinit var answers: List<String>
    private var questionIndex = 0
    private val numQuestion = Math.min((questions.size + 1) / 2, 3)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)

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
                if(answers[answersIndex] == currentQuestion.answers[0]){
                    questionIndex++

                    if(questionIndex < numQuestion){
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    }else{

                    }
                }else{

                }
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

}