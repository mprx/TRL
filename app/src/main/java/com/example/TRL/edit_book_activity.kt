package com.example.TRL

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_book.addBookAutorNN
import kotlinx.android.synthetic.main.add_book.addBookAutorVN
import kotlinx.android.synthetic.main.add_book.addBookComment
import kotlinx.android.synthetic.main.add_book.addBookSubmit
import kotlinx.android.synthetic.main.add_book.addBookTitle
import kotlinx.android.synthetic.main.edit_book.*

class edit_book_activity: AppCompatActivity()  {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_book)

        //buch id aus intent holen
        val book_id=intent.getStringExtra("book_id")

        //daten des buchs aus der DB holen
        val mDatabaseHelper = DatabaseHelper(this)
        val bookQuery = mDatabaseHelper.getData("ID = $book_id",1,true)

        bookQuery.moveToNext()

        val b_id = bookQuery.getInt(0)
        val b_title = bookQuery.getString(1)
        val b_autor_vn = bookQuery.getString(2)
        val b_autor_nn = bookQuery.getString(3)
        val b_comment = bookQuery.getString(4)
        val b_read = bookQuery.getInt(5)

        addBookTitle.setText(b_title)
        addBookAutorVN.setText(b_autor_vn)
        addBookAutorNN.setText(b_autor_nn)
        addBookComment.setText(b_comment)

        val checkboxgelesen = findViewById<CheckBox>(R.id.checkBoxRead)

        if(b_read == 1){
            checkboxgelesen.setChecked(true)
        }
        else{
            checkboxgelesen.setChecked(false)
        }



        var submitbutton = findViewById<Button>(R.id.addBookSubmit)

        //button default enablen
        submitbutton.isEnabled = true
        submitbutton.isClickable = true
        submitbutton.setTextColor(Color.parseColor("#000000"))

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

            var bookGelesen: Int

            if(checkboxgelesen.isChecked){
                bookGelesen = 1
            }
            else{
                bookGelesen = 0
            }


            var mDatabaseHelper = DatabaseHelper(this)

            mDatabaseHelper.updateName(bookTitle,b_id,"b_title")
            mDatabaseHelper.updateName(authorVN,b_id,"b_autor_vn")
            mDatabaseHelper.updateName(authorNN,b_id,"b_autor_nn")
            mDatabaseHelper.updateName(bookComment,b_id,"b_comment")
            mDatabaseHelper.updateName(bookGelesen,b_id,"b_gelesen")

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            this.finish()

        }

        var deleteSubmitbutton = findViewById<Button>(R.id.deleteBookSubmit)
        var deletesure = findViewById<TextView>(R.id.textViewSure)
        var deleteButtonYes = findViewById<Button>(R.id.deleteBookSubmitYes)
        var deleteButtonNo = findViewById<Button>(R.id.deleteBookSubmitNo)

        deletesure.visibility = View.GONE
        deleteButtonNo.visibility = View.GONE
        deleteButtonNo.isClickable = false
        deleteButtonNo.isEnabled = false
        deleteButtonYes.visibility = View.GONE
        deleteButtonYes.isClickable = false
        deleteButtonYes.isEnabled = false



        deleteBookSubmit.setOnClickListener {
            deleteSubmitbutton.isEnabled = false
            deleteSubmitbutton.isClickable = false
            deleteSubmitbutton.visibility = View.GONE

            deletesure.visibility = View.VISIBLE
            deleteButtonNo.visibility = View.VISIBLE
            deleteButtonNo.isClickable = true
            deleteButtonNo.isEnabled = true
            deleteButtonYes.visibility = View.VISIBLE
            deleteButtonYes.isClickable = true
            deleteButtonYes.isEnabled = true

        }

        deleteButtonNo.setOnClickListener {
            deleteSubmitbutton.isEnabled = true
            deleteSubmitbutton.isClickable = true
            deleteSubmitbutton.visibility = View.VISIBLE

            deletesure.visibility = View.GONE
            deleteButtonNo.visibility = View.GONE
            deleteButtonNo.isClickable = false
            deleteButtonNo.isEnabled = false
            deleteButtonYes.visibility = View.GONE
            deleteButtonYes.isClickable = false
            deleteButtonYes.isEnabled = false
        }

        deleteButtonYes.setOnClickListener {
            mDatabaseHelper.deleteName(b_id)

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