package ma.ofppt.ocp_projet.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ma.ofppt.ocp_projet.Ticket

class DBTicket(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ticket_database.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_TICKETS = "tickets"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PRODUCT_TYPE = "product_type"
        private const val COLUMN_AGENT_TYPE = "agent_type"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_TICKETS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_PRODUCT_TYPE TEXT NOT NULL, " +
                "$COLUMN_AGENT_TYPE TEXT NOT NULL)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TICKETS")
        onCreate(db)
    }

    fun addTicket(productType: String, agentType: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PRODUCT_TYPE, productType)
            put(COLUMN_AGENT_TYPE, agentType)
        }
        val result = db.insert(TABLE_TICKETS, null, values)
        db.close()
        return result
    }

    fun getAllTickets(): List<Ticket> {
        val ticketList = mutableListOf<Ticket>()
        val db = readableDatabase
        val cursor = db.query(TABLE_TICKETS, null, null, null, null, null, "$COLUMN_ID DESC")

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val productType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_TYPE))
            val agentType = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGENT_TYPE))
            ticketList.add(Ticket(id, productType, agentType))
        }
        cursor.close()
        return ticketList
    }
}
