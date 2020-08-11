package com.example.TRL

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_book.*


class add_book_activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book)

        var submitbutton = findViewById<Button>(R.id.addBookSubmit)

        //button default disablen
        submitbutton.isEnabled = false
        submitbutton.isClickable = false
        submitbutton.setTextColor(Color.parseColor("#666666"))

        addBookTitle.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if(addBookTitle.length() > 1){
                    submitbutton.isEnabled = true
                    submitbutton.isClickable = true
                    submitbutton.setTextColor(Color.parseColor("#000000"))
                }
                else{
                    submitbutton.isEnabled = false
                    submitbutton.isClickable = false
                    submitbutton.setTextColor(Color.parseColor("#666666"))
                }
            }
        })

        addBookSubmit.setOnClickListener {

            //Submit button sperren, damit er nicht mehrmals geklickt werden kann
            submitbutton.isEnabled = false
            submitbutton.isClickable = false
            submitbutton.setTextColor(Color.parseColor("#666666"))

            /*
            val database = getSharedPreferences("database", Context.MODE_PRIVATE)
            database.edit().apply {
                putString("savedBookTitle99", addBookTitle.text.toString())
            }.apply()
             */


            val bookTitle = addBookTitle.text.toString()
            val authorVN = addBookAutorVN.text.toString()
            val authorNN = addBookAutorNN.text.toString()
            val bookComment = addBookComment.text.toString()

            var mDatabaseHelper = DatabaseHelper(this)

            mDatabaseHelper.addData(bookTitle, authorVN, authorNN, bookComment)


            d("Mario: ", "Buch hinzugefügt (?): $bookTitle")

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            this.finish()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        startActivity(intent)
        this.finish()
    }

}