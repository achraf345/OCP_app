package ma.ofppt.ocp_projet

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBCHANGE(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "OCP_CHANGE.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_CHANGES = "change_requests"
        const val COLUMN_ID = "id"
        const val COLUMN_MATRICULE = "matricule"
        const val COLUMN_FULLNAME = "fullname"
        const val COLUMN_PRODUCT = "product"
        const val COLUMN_TYPE = "type"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_AGENT_TYPE = "agent_type"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val createChangeTableQuery = """
            CREATE TABLE $TABLE_CHANGES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_MATRICULE TEXT,
                $COLUMN_FULLNAME TEXT,
                $COLUMN_PRODUCT TEXT,
                $COLUMN_TYPE TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_PHONE TEXT,
                $COLUMN_AGENT_TYPE TEXT
            )
        """.trimIndent()
        db.execSQL(createChangeTableQuery)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CHANGES")
        onCreate(db)
    }
    fun insertChangeRequest(
        matricule: String,
        fullname: String,
        product: String,
        type: String,
        description: String,
        phone: String,
        agentType: String
    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_MATRICULE, matricule)
            put(COLUMN_FULLNAME, fullname)
            put(COLUMN_PRODUCT, product)
            put(COLUMN_TYPE, type)
            put(COLUMN_DESCRIPTION, description)
            put(COLUMN_PHONE, phone)
            put(COLUMN_AGENT_TYPE, agentType)
        }
        val result = db.insert(TABLE_CHANGES, null, values)
        db.close()
        return result != -1L
    }
}
