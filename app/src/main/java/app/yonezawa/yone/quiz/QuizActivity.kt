package app.yonezawa.yone.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {
    //クイズを作る
    val quizLists: List<List<String>> =listOf(
            listOf("Androidコースのキャラクターの名前は？", "ランディ", "フィル", "ドロイド", "ランディ"),
            listOf("Androidアプリを開発する言語はどれ？","JavaScript", "kotlin", "Swift", "kotlin"),
            listOf("ImageViewは、何を扱える要素？", "文字", "音声","画像", "画像")
    )

    //クイズをシャッフルして変数に入れる
    val shuffledList : List<List<String>> = quizLists.shuffled()

    //クイズをカウントする変数を作る）
   var quizCount: Int = 0

    //正解の答えを入れる変数を作る
    var correctAnswer: String = ""

    //正解の回数を入れる変数を作る
    var correctCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //クイズを表示する
        showQuestion()

        //1つ目のボタンがタップされたら
        answerButton1.setOnClickListener {
            //回答をチェックする
            checkAnswer(answerButton1.text.toString())
        }
        //2つ目のボタンがタップされたら
        answerButton2.setOnClickListener {
            //回答をチェックする
            checkAnswer(answerButton2.text.toString())
        }

        //3つ目のボタンがタップされたら
        answerButton3.setOnClickListener {
            //回答をチェックする
            checkAnswer(answerButton3.text.toString())
        }
        //次に進ボタンがタップされたら
        nextButton.setOnClickListener {
            //現在のクイズ数と、全問クイズ数が一致するか比較して
            if (quizCount == quizLists.size) {
                //一緒だったら、結果画面へ移動する準備をする
                val resultIntent: Intent = Intent(this,ResultActivity::class.java)
                //クイズ数をセットする
                resultIntent.putExtra("QuizCount",quizLists.size)
                //正解数をセットする
                resultIntent.putExtra("CorrectCount",correctCount)
                //結果画面に移動する
                startActivity(resultIntent)

            } else {
                //一緒でなければ、判定(〇☓）画像を非表示にする
                judgeImage.isVisible = false
                nextButton.isVisible = false
                //回答ボタンを有効にする
                answerButton1.isEnabled = true
                answerButton2.isEnabled = true
                answerButton3.isEnabled = true
                //正解表示をリセットする
                correctAnswerText.text = ""
                //クイズを表示する
                showQuestion()

            }
        }

    }
    //画面に表示するクイズを作る
    fun showQuestion() {
        //クイズを取り出す
        val question: List<String> = shuffledList[quizCount]
        //クイズの中身を確認する
        Log.d("debug",question.toString())
        //クイズを、TextViewに反映する
        quizText.text = question[0]
        //クイズの選択肢を表示する
        answerButton1.text = question[1]
        answerButton2.text = question[2]
        answerButton3.text = question[3]
        //クイズの正しい答えをセットする
        correctAnswer = question[4]

    }
    //回答をチェックする
    fun checkAnswer(answerText: String) {
        //タップされた回答と、正しい答えを比べて
        if (answerText == correctAnswer) {
            //一緒だったら、〇画像を反映する
            judgeImage.setImageResource(R.drawable.maru_image)
            //正解した回数をカウントする
            correctCount++
        } else {
            //違っていたら、X画像を反映する
            judgeImage.setImageResource(R.drawable.batsu_image)
        }
        //判定（〇☓）画像を表示する
        showAnswer()
        //クイズ数をカウントする
        quizCount++
   }
    //判定（〇☓）画像を表示する際のボタンを設定する
    fun showAnswer() {
        //正解を表示する
        correctAnswerText.text = "正解:$correctAnswer"
        //判定（〇☓）画像を表示する
        judgeImage.isVisible = true
        //次へボタンを表示する
        nextButton.isVisible = true
        //回答ボタンを無効にする
        answerButton1.isEnabled = false
        answerButton2.isEnabled = false
        answerButton3.isEnabled = false
    }
}