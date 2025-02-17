import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "users"
        private const val COLUMN_MATRICULE = "matricule"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_MATRICULE TEXT PRIMARY KEY, " +
                "$COLUMN_PASSWORD TEXT NOT NULL)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(matricule: String, password: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_MATRICULE, matricule)
            put(COLUMN_PASSWORD, password)
        }
        db.insert(TABLE_USERS, null, contentValues)
        db.close()
    }
}