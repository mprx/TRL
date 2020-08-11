package com.example.TRL

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Listview und inhalt definieren
        val listView = findViewById<ListView>(R.id.bookList)
        val books = arrayListOf<Array<Any>>()

        //datenbank
        val mDatabaseHelper = DatabaseHelper(this)

        //Wenn App erstes Mal gestartet wird, bbc bücher in db einfügen
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        if (!prefs.getBoolean("firstTime", false)) {

            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.putString("sort", "all")
            editor.commit()

            mDatabaseHelper.addData("1984","George","Orwell","")
            mDatabaseHelper.addData("A Christmas Carol","Charles","Dickens","")
            mDatabaseHelper.addData("A Clockwork Orange","Anthony","Burgess","")
            mDatabaseHelper.addData("A Confederacy of Dunces","John Kennedy","Toole","")
            mDatabaseHelper.addData("A Fine Balance","Rohinton","Mistry","")
            mDatabaseHelper.addData("A Prayer for Owen Meany","John","Irving","")
            mDatabaseHelper.addData("A Suitable Boy","Vikram","Seth","")
            mDatabaseHelper.addData("A Tale of Two Cities","Charles","Dickens","")
            mDatabaseHelper.addData("A Town Like Alice","Nevil","Shute","")
            mDatabaseHelper.addData("Adventures of Sherlock Holmes","Arthur Conan","Doyle","")
            mDatabaseHelper.addData("Alchemist, The","Paulo","Coelho","")
            mDatabaseHelper.addData("Alice in Wonderland","Lewis","Carroll","")
            mDatabaseHelper.addData("Animal Farm","George","Orwell","")
            mDatabaseHelper.addData("Anna Karenina","Leo","Tolstoy","")
            mDatabaseHelper.addData("Anne of Green Gables","LM","Montgomery","")
            mDatabaseHelper.addData("Atonement","Ian","McEwan","")
            mDatabaseHelper.addData("Bell Jar, The","Sylvia","Plath","")
            mDatabaseHelper.addData("Bible, The","Moses","& Co.","")
            mDatabaseHelper.addData("Birdsong","Sebastian","Faulks","")
            mDatabaseHelper.addData("Bleak House","Charles","Dickens","")
            mDatabaseHelper.addData("Brave New World","Aldous","Huxley","")
            mDatabaseHelper.addData("Brideshead Revisited","Evelyn","Waugh","")
            mDatabaseHelper.addData("Bridget Jones's Diary","Helen","Fielding","")
            mDatabaseHelper.addData("Captain Corelli's Mandolin","Louis","de Bernieres","")
            mDatabaseHelper.addData("Catch-22","Joseph","Heller","")
            mDatabaseHelper.addData("Catcher in the Rye, The","JD","Salinger","")
            mDatabaseHelper.addData("Charlie and the Chocolate Factory","Roald","Dahl","")
            mDatabaseHelper.addData("Charlotte's Web","E. B.","White","")
            mDatabaseHelper.addData("Cloud Atlas","David","Mitchell","")
            mDatabaseHelper.addData("Cold Comfort Farm","Stella","Gibbons","")
            mDatabaseHelper.addData("Color Purple, The","Alice","Walker ","")
            mDatabaseHelper.addData("Complete Works of Shakespeare","William","Shakespeare","")
            mDatabaseHelper.addData("Count of Monte Cristo, The","Alexandre","Dumas","")
            mDatabaseHelper.addData("Crime and Punishment","Fyodor","Dostoyevsky","")
            mDatabaseHelper.addData("Curious Incident of the Dog in the Night-Time, The","Mark","Haddon","")
            mDatabaseHelper.addData("Da Vinci Code, The","Dan","Brown","")
            mDatabaseHelper.addData("David Copperfield","Charles","Dickens","")
            mDatabaseHelper.addData("Dracula","Bram","Stoker","")
            mDatabaseHelper.addData("Dune","Frank","Herbert","")
            mDatabaseHelper.addData("Emma","Jane","Austen","")
            mDatabaseHelper.addData("Far From the Madding Crowd","Thomas","Hardy","")
            mDatabaseHelper.addData("Faraway Tree Collection, The","Enid","Blyton","")
            mDatabaseHelper.addData("Five People You Meet in Heaven, The","Mitch","Albom","")
            mDatabaseHelper.addData("Germinal","Emile","Zola","")
            mDatabaseHelper.addData("Gone With the Wind","Margaret","Mitchell","")
            mDatabaseHelper.addData("Grapes of Wrath, The","John","Steinbeck","")
            mDatabaseHelper.addData("Great Expectations","Charles","Dickens","")
            mDatabaseHelper.addData("Great Gatsby, The","F. Scott","Fitzgerald","")
            mDatabaseHelper.addData("Handmaid's Tale, The","Margaret","Atwood","")
            mDatabaseHelper.addData("Harry Potter And The Chamber of Secrets","J. K.","Rowling","")
            mDatabaseHelper.addData("Harry Potter And The Deathly Hallows","J. K.","Rowling","")
            mDatabaseHelper.addData("Harry Potter And The Goblet of Fire","J. K.","Rowling","")
            mDatabaseHelper.addData("Harry Potter And The Half-Blood Prince","J. K.","Rowling","")
            mDatabaseHelper.addData("Harry Potter And The Order of the Phoenix","J. K.","Rowling","")
            mDatabaseHelper.addData("Harry Potter And The Philosopher's Stone","J. K.","Rowling","")
            mDatabaseHelper.addData("Harry Potter And The Prisoner of Azkaban","J. K.","Rowling","")
            mDatabaseHelper.addData("Heart of Darkness","Joseph","Conrad","")
            mDatabaseHelper.addData("His Dark Materials Trilogy","Philip","Pullman","")
            mDatabaseHelper.addData("Hitchhiker's Guide to the Galaxy, The","Douglas","Adams","")
            mDatabaseHelper.addData("Hobbit, The","JRR","Tolkien","")
            mDatabaseHelper.addData("Horse and His Boy, The","C. S.","Lewis","")
            mDatabaseHelper.addData("Jane Eyre","Charlotte","Brontë","")
            mDatabaseHelper.addData("Jude the Obscure","Thomas","Hardy","")
            mDatabaseHelper.addData("Kite Runner","Khaled","Hosseini","")
            mDatabaseHelper.addData("Last Battle, The","C. S.","Lewis","")
            mDatabaseHelper.addData("Les Misérables","Victor","Hugo","")
            mDatabaseHelper.addData("Life of Pi","Yann","Martel","")
            mDatabaseHelper.addData("Little Prince, The","Antoine","de Saint-Exupéry","")
            mDatabaseHelper.addData("Little Women","Louisa","May Alcott","")
            mDatabaseHelper.addData("Lolita","Vladimir","Nabokov","")
            mDatabaseHelper.addData("Lord of the Flies","William","Golding","")
            mDatabaseHelper.addData("Lord of the Rings, The","JRR","Tolkien","")
            mDatabaseHelper.addData("Love in the Time of Cholera","Gabriel","García Márquez","")
            mDatabaseHelper.addData("Lovely Bones, The","Alice","Sebold","")
            mDatabaseHelper.addData("Madame Bovary","Gustave","Flaubert","")
            mDatabaseHelper.addData("Magician's Nephew, The","C. S.","Lewis","")
            mDatabaseHelper.addData("Memoirs of a Geisha","Arthur","Golden","")
            mDatabaseHelper.addData("Middlemarch","George","Eliot","")
            mDatabaseHelper.addData("Midnight's Children","Salman","Rushdie","")
            mDatabaseHelper.addData("Moby Dick","Herman","Melville","")
            mDatabaseHelper.addData("Notes From a Small Island","Bill","Bryson","")
            mDatabaseHelper.addData("Of Mice and Men","John","Steinbeck","")
            mDatabaseHelper.addData("Oliver Twist","Charles","Dickens","")
            mDatabaseHelper.addData("On the Road","Jack","Kerouac","")
            mDatabaseHelper.addData("One Hundred Years of Solitude","Gabriel","García Márquez","")
            mDatabaseHelper.addData("Persuasion","Jane","Austen","")
            mDatabaseHelper.addData("Possession","A. S.","Byatt","")
            mDatabaseHelper.addData("Pride and Prejudice","Jane","Austen","")
            mDatabaseHelper.addData("Prince Caspian: The Return to Narnia","C. S.","Lewis","")
            mDatabaseHelper.addData("Rebecca","Daphne","du Maurier","")
            mDatabaseHelper.addData("Remains of the Day, The","Kazuo","Ishiguro","")
            mDatabaseHelper.addData("Secret Garden, The","Frances","Hodgson Burnett","")
            mDatabaseHelper.addData("Secret History, The","Donna","Tartt","")
            mDatabaseHelper.addData("Sense and Sensibility","Jane","Austen","")
            mDatabaseHelper.addData("Shadow of the Wind, The","Carlos","Ruiz Zafon","")
            mDatabaseHelper.addData("Silver Chair, The","C. S.","Lewis","")
            mDatabaseHelper.addData("Swallows and Amazons","Arthur","Ransome","")
            mDatabaseHelper.addData("Tess of the D'urbervilles","Thomas","Hardy","")
            mDatabaseHelper.addData("The Lion, the Witch and the Wardrobe","C. S.","Lewis","")
            mDatabaseHelper.addData("Three Musketeers, The","Alexandre","Dumas","")
            mDatabaseHelper.addData("Time Traveler's Wife, The","Audrey","Niffenegger","")
            mDatabaseHelper.addData("To Kill a Mockingbird","Harper","Lee","")
            mDatabaseHelper.addData("Ulysses","James","Joyce","")
            mDatabaseHelper.addData("Vanity Fair","William","Makepeace Thackeray","")
            mDatabaseHelper.addData("Voyage of the Dawn Treader, The","C. S.","Lewis","")
            mDatabaseHelper.addData("War and Peace","Leo","Tolstoy","")
            mDatabaseHelper.addData("Wasp Factory, The","Iain","Banks","")
            mDatabaseHelper.addData("Watership Down","Richard","Adams","")
            mDatabaseHelper.addData("Wind in the Willows, The","Kenneth","Grahame","")
            mDatabaseHelper.addData("Winnie-The-Pooh","A. A.","Milne","")
            mDatabaseHelper.addData("Woman in White, The","Wilkie","Collins","")
            mDatabaseHelper.addData("Wuthering Heights","Emily","Brontë","")
        }


        //sortierung
        var sortPref = prefs.getString("sort", "all")
        var where = ""
        if(sortPref == "finished"){
            where = "b_gelesen = 1"
        }
        if(sortPref == "unfinished"){
            where = "b_gelesen = 0"
        }

        val sortText = findViewById<TextView>(R.id.textViewShowValue)

        sortText.setText(sortPref)

        sortText.setOnClickListener {
            val editor = prefs.edit()
            if(sortPref == "all"){
                sortPref = "unfinished"
                editor.putString("sort", "unfinished")
                editor.commit()
            }
            else if(sortPref == "unfinished"){
                sortPref = "finished"
                editor.putString("sort", "finished")
                editor.commit()
            }
            else if(sortPref == "finished"){
                sortPref = "all"
                editor.putString("sort", "all")
                editor.commit()
            }
            else{
                sortPref = "all"
                editor.putString("sort", "all")
                editor.commit()
            }

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            this.finish()
        }

        //daten holen
        val bookquery = mDatabaseHelper.getData(where,1,true)

        //ins Array eintragen
        while (bookquery.moveToNext()) {

            val temp = arrayOf<Any>(bookquery.getString(0), bookquery.getString(1), bookquery.getString(2), bookquery.getString(3), bookquery.getInt(5))

            books.add(temp)
        }

        //Daten in die Liste schreiben
        listView.adapter = booklistAdapter(this, books)

        listView.onItemClickListener = object : OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {

                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as String
                /*
                // Toast the values
                Toast.makeText(applicationContext,
                    "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG)
                    .show()
                 */
                //definiert was aufzurufen ist
                val intent = Intent(this@MainActivity, edit_book_activity::class.java)
                //übergibt die ID an die activity
                intent.putExtra("book_id",itemValue)
                startActivity(intent)
            }
        }

        //progressbar und textview definieren
        val bookProgressBar = findViewById<ProgressBar>(R.id.bookProgress)
        val bookProgressText = findViewById<TextView>(R.id.textViewProgress)
        //daten für die progress bar holen

        val bookCount = mDatabaseHelper.getCount("")
        val readCount = mDatabaseHelper.getCount("b_gelesen = 1")

        bookProgressBar.max = bookCount
        bookProgressBar.progress = readCount

        bookProgressText.setText("Finished: " + readCount + "/" + bookCount)


    }

    override fun onBackPressed() {
        super.onBackPressed()

        this.finishAffinity()
    }

    private class booklistAdapter (context: Context, data: ArrayList<Array<Any>>): BaseAdapter() {

        private val mContext: Context
        private val mData = data

        init {
            mContext = context
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return mData.get(position).get(0)
        }

        override fun getCount(): Int {
            return mData.size
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val id = mData.get(position).get(0)
            val titel = mData.get(position).get(1)
            val autor_vn = mData.get(position).get(2)
            val autor_nn = mData.get(position).get(3)
            val gelesen = mData.get(position).get(4)

            val layoutInflater = LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.listviewitem,viewGroup, false)

            val row_titel = rowMain.findViewById<TextView>(R.id.textView)
            row_titel.text = "$titel"

            val row_autor = rowMain.findViewById<TextView>(R.id.textView2)
            row_autor.text = "$autor_vn $autor_nn"

            var row_gelesen = rowMain.findViewById<CheckBox>(R.id.checkBoxGelesen)

            row_gelesen.visibility = View.GONE

            if(gelesen == 1){
                row_gelesen.isChecked = true
                row_gelesen.visibility = View.VISIBLE
            }

            return rowMain


           /*
            val textView = TextView(mContext)
            textView.text = "ROW for Listview"
            return textView

            */
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        } */

        return when (item.itemId) {
            /*R.id.action_settings -> {
                //newGame()
                true
            }*/
            R.id.goToAddBook2 -> {
                val intent = Intent(this, add_book_activity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }
}
