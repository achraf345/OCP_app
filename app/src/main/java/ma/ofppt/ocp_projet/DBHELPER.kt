import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_database.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRSTNAME = "firstname"
        private const val COLUMN_LASTNAME = "lastname"
        private const val COLUMN_MATRICULE = "matricule"
        private const val COLUMN_PHONENUMBER = "phonenumber"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_CPASSWORD = "cpassword"
        private const val COLUMN_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_FIRSTNAME TEXT NOT NULL, " +
                "$COLUMN_LASTNAME TEXT NOT NULL, " +
                "$COLUMN_MATRICULE TEXT NOT NULL, " +
                "$COLUMN_PHONENUMBER TEXT NOT NULL, " +
                "$COLUMN_PASSWORD TEXT NOT NULL, " +
                "$COLUMN_CPASSWORD TEXT NOT NULL, " +
                "$COLUMN_LOCATION TEXT NOT NULL)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(firstname: String, lastname: String, matricule: String, phonenumber: String, password: String, cpassword: String, location: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_FIRSTNAME, firstname)
            put(COLUMN_LASTNAME, lastname)
            put(COLUMN_MATRICULE, matricule)
            put(COLUMN_PHONENUMBER, phonenumber)
            put(COLUMN_PASSWORD, password)
            put(COLUMN_CPASSWORD, cpassword)
            put(COLUMN_LOCATION, location)  // Save spinner selection
        }

        val result = db.insert(TABLE_USERS, null, contentValues)
        db.close()

        if (result == -1L) {
            println("Database Insertion Failed")
        } else {
            println("User Inserted Successfully with ID: $result")
        }

        return result
    }
    fun readUser(matricule: String, password: String): Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_MATRICULE = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(matricule, password)
        val cursor = db.query(TABLE_USERS, null, selection, selectionArgs, null, null, null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
    fun updatePassword(matricule: String, newPassword: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_PASSWORD, newPassword)
            put(COLUMN_CPASSWORD, newPassword)
        }

        val whereClause = "$COLUMN_MATRICULE = ?"
        val whereArgs = arrayOf(matricule)

        val rowsAffected = db.update(TABLE_USERS, contentValues, whereClause, whereArgs)
        db.close()

        return rowsAffected > 0
    }
}

