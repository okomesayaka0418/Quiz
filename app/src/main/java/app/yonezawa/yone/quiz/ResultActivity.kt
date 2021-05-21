package app.yonezawa.yone.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz.quizText
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //クイズ画面からクイズ数を受け取る
        val quizCount: Int = intent.getIntExtra("QuizCount", 0)
        //クイズ数をTextViewに反映する
        quizText.text = "$quizCount 問中・・・"

        //正解数を受けとる
        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)

        //正解数を受け取る
        correctCountText.text = correctCount.toString()
    }

}
