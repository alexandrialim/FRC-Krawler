package com.team2052.frckrawler.db;

import java.util.List;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import com.team2052.frckrawler.db.RobotPhoto;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "ROBOT_PHOTO".
 */
public class RobotPhotoDao extends AbstractDao<RobotPhoto, Long> {

    public static final String TABLENAME = "ROBOT_PHOTO";

    /**
     * Properties of entity RobotPhoto.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Location = new Property(1, String.class, "location", false, "LOCATION");
        public final static Property Robot_id = new Property(2, Long.class, "robot_id", false, "ROBOT_ID");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Date = new Property(4, java.util.Date.class, "date", false, "DATE");
    }

    ;

    private DaoSession daoSession;


    public RobotPhotoDao(DaoConfig config) {
        super(config);
    }

    public RobotPhotoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"ROBOT_PHOTO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"LOCATION\" TEXT," + // 1: location
                "\"ROBOT_ID\" INTEGER," + // 2: robot_id
                "\"TITLE\" TEXT," + // 3: title
                "\"DATE\" INTEGER);"); // 4: date
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ROBOT_PHOTO\"";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, RobotPhoto entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        String location = entity.getLocation();
        if (location != null) {
            stmt.bindString(2, location);
        }

        Long robot_id = entity.getRobot_id();
        if (robot_id != null) {
            stmt.bindLong(3, robot_id);
        }

        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(4, title);
        }

        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(5, date.getTime());
        }
    }

    @Override
    protected void attachEntity(RobotPhoto entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public RobotPhoto readEntity(Cursor cursor, int offset) {
        RobotPhoto entity = new RobotPhoto( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // location
                cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // robot_id
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // title
                cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)) // date
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, RobotPhoto entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLocation(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setRobot_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDate(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(RobotPhoto entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(RobotPhoto entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getRobotDao().getAllColumns());
            builder.append(" FROM ROBOT_PHOTO T");
            builder.append(" LEFT JOIN ROBOT T0 ON T.\"ROBOT_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }

    protected RobotPhoto loadCurrentDeep(Cursor cursor, boolean lock) {
        RobotPhoto entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Robot robot = loadCurrentOther(daoSession.getRobotDao(), cursor, offset);
        entity.setRobot(robot);

        return entity;
    }

    public RobotPhoto loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();

        String[] keyArray = new String[]{key.toString()};
        Cursor cursor = db.rawQuery(sql, keyArray);

        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }

    /**
     * Reads all available rows from the given cursor and returns a list of new ImageTO objects.
     */
    public List<RobotPhoto> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<RobotPhoto> list = new ArrayList<RobotPhoto>(count);

        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }

    protected List<RobotPhoto> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }


    /**
     * A raw-style query where you can pass any WHERE clause and arguments.
     */
    public List<RobotPhoto> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }

}
