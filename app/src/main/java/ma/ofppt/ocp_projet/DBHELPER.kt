import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRSTNAME = "firstname"
        private const val COLUMN_LASTNAME = "lastname"
        private const val COLUMN_MATRICULE = "matricule"
        private const val COLUMN_PHONENUMBER = "phonenumber"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_CPASSWORD = "cpassword"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRSTNAME TEXT NOT NULL, " +
                "$COLUMN_LASTNAME TEXT NOT NULL, " +
                "$COLUMN_MATRICULE TEXT NOT NULL, " +
                "$COLUMN_PHONENUMBER TEXT NOT NULL, " +
                "$COLUMN_PASSWORD TEXT NOT NULL, " +
                "$COLUMN_CPASSWORD TEXT NOT NULL)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(firstname: String, lastname: String, matricule: String, phonenumber: String, password: String, cpassword: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_FIRSTNAME, firstname)
            put(COLUMN_LASTNAME, lastname)
            put(COLUMN_MATRICULE, matricule)
            put(COLUMN_PHONENUMBER, phonenumber)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_CPASSWORD, cpassword)
        }
        val result = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return result
    }

    fun ReadUser(firstname: String, lastname: String, matricule: String, phonenumber: String, password: String, cpassword: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_FIRSTNAME = ? AND $COLUMN_LASTNAME = ? AND $COLUMN_MATRICULE = ? AND $COLUMN_PHONENUMBER = ? AND $COLUMN_PASSWORD = ? AND $COLUMN_CPASSWORD = ?"
        val selectionArgs = arrayOf(firstname, lastname, matricule, phonenumber, password, cpassword)
        val cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
}
