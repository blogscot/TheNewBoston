package com.diamond.iain.thenewboston;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HotOrNot {

	public static final String ROWID_KEY = "_id";
	public static final String NAME_KEY = "person_name";
	public static final String HOTNESS_KEY = "person_hotness";

	private static final String DATABASE_NAME = "HotorNotdb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	public HotOrNot(Context context) {
		ourContext = context;
	}

	public HotOrNot open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			String sql = "CREATE TABLE " + DATABASE_TABLE + " (" + ROWID_KEY
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME_KEY
					+ " TEXT NOT NULL, " + HOTNESS_KEY + " TEXT NOT NULL);";
			db.execSQL(sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "DROP TABLE IF EXISTS" + DATABASE_TABLE;
			db.execSQL(sql);
			onCreate(db);
		}
	}

	public long createEntry(String name, String hotness) {
		ContentValues cv = new ContentValues();
		cv.put(NAME_KEY, name);
		cv.put(HOTNESS_KEY, hotness);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}

	public String getData() {
		String[] columns = new String[] { ROWID_KEY, NAME_KEY, HOTNESS_KEY };
		ourDatabase = ourHelper.getReadableDatabase();
		Cursor cursor = ourDatabase.query(DATABASE_TABLE, columns, null, null,
				null, null, null);
		String result = "";
		
		int row = cursor.getColumnIndex(ROWID_KEY);
		int name = cursor.getColumnIndex(NAME_KEY);
		int hotness = cursor.getColumnIndex(HOTNESS_KEY);

		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			result += cursor.getString(row) + " " + cursor.getString(name)
					+ " " + cursor.getString(hotness) + "\n";
		}
		return result;
	}

	public String getName(long row) {
		String[] columns = new String[] { ROWID_KEY, NAME_KEY, HOTNESS_KEY };
		ourDatabase = ourHelper.getReadableDatabase();
		Cursor cursor = ourDatabase.query(DATABASE_TABLE, columns, ROWID_KEY + "=" + row, null,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			try {
				String name = cursor.getString(1);
				return name;
			} catch (Exception e) {
				// row doesn't exist. just return empty string
			}
		}
		return "";
	}

	public String getHotness(long row) {
		String[] columns = new String[] { ROWID_KEY, NAME_KEY, HOTNESS_KEY };
		ourDatabase = ourHelper.getReadableDatabase();
		Cursor cursor = ourDatabase.query(DATABASE_TABLE, columns, ROWID_KEY + "=" + row, null,
				null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			try {
				String name = cursor.getString(2);
				return name;
			} catch (Exception e) {
				// row doesn't exist. just return empty string
			}
		}
		return "";
	}

	public void update(long row, String editName, String editHotness) {
		ContentValues cv = new ContentValues();
		cv.put(NAME_KEY, editName);
		cv.put(HOTNESS_KEY, editHotness);
		ourDatabase.update(DATABASE_TABLE, cv, ROWID_KEY + "=" + row, null);
	}

	public void delete(long row) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, ROWID_KEY + "=" + row, null);
		
	}
}
