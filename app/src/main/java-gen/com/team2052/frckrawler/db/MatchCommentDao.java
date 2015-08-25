package com.team2052.frckrawler.db;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.team2052.frckrawler.db.MatchComment;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "MATCH_COMMENT".
 */
public class MatchCommentDao extends AbstractDao<MatchComment, Long> {

    public static final String TABLENAME = "MATCH_COMMENT";

    /**
     * Properties of entity MatchComment.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Match_number = new Property(1, Long.class, "match_number", false, "MATCH_NUMBER");
        public final static Property Game_type = new Property(2, Integer.class, "game_type", false, "GAME_TYPE");
        public final static Property Robot_id = new Property(3, Long.class, "robot_id", false, "ROBOT_ID");
        public final static Property Event_id = new Property(4, Long.class, "event_id", false, "EVENT_ID");
        public final static Property Comment = new Property(5, String.class, "comment", false, "COMMENT");
    }

    ;

    private Query<MatchComment> event_MatchCommentListQuery;
    private Query<MatchComment> robot_MatchCommentListQuery;

    public MatchCommentDao(DaoConfig config) {
        super(config);
    }

    public MatchCommentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"MATCH_COMMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ," + // 0: id
                "\"MATCH_NUMBER\" INTEGER," + // 1: match_number
                "\"GAME_TYPE\" INTEGER," + // 2: game_type
                "\"ROBOT_ID\" INTEGER," + // 3: robot_id
                "\"EVENT_ID\" INTEGER," + // 4: event_id
                "\"COMMENT\" TEXT);"); // 5: comment
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MATCH_COMMENT\"";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, MatchComment entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }

        Long match_number = entity.getMatch_number();
        if (match_number != null) {
            stmt.bindLong(2, match_number);
        }

        Integer game_type = entity.getGame_type();
        if (game_type != null) {
            stmt.bindLong(3, game_type);
        }

        Long robot_id = entity.getRobot_id();
        if (robot_id != null) {
            stmt.bindLong(4, robot_id);
        }

        Long event_id = entity.getEvent_id();
        if (event_id != null) {
            stmt.bindLong(5, event_id);
        }

        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(6, comment);
        }
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
    public MatchComment readEntity(Cursor cursor, int offset) {
        MatchComment entity = new MatchComment( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // match_number
                cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // game_type
                cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // robot_id
                cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // event_id
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // comment
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, MatchComment entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMatch_number(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setGame_type(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setRobot_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setEvent_id(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setComment(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(MatchComment entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(MatchComment entity) {
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

    /**
     * Internal query to resolve the "matchCommentList" to-many relationship of Event.
     */
    public List<MatchComment> _queryEvent_MatchCommentList(Long event_id) {
        synchronized (this) {
            if (event_MatchCommentListQuery == null) {
                QueryBuilder<MatchComment> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Event_id.eq(null));
                event_MatchCommentListQuery = queryBuilder.build();
            }
        }
        Query<MatchComment> query = event_MatchCommentListQuery.forCurrentThread();
        query.setParameter(0, event_id);
        return query.list();
    }

    /**
     * Internal query to resolve the "matchCommentList" to-many relationship of Robot.
     */
    public List<MatchComment> _queryRobot_MatchCommentList(Long robot_id) {
        synchronized (this) {
            if (robot_MatchCommentListQuery == null) {
                QueryBuilder<MatchComment> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.Robot_id.eq(null));
                robot_MatchCommentListQuery = queryBuilder.build();
            }
        }
        Query<MatchComment> query = robot_MatchCommentListQuery.forCurrentThread();
        query.setParameter(0, robot_id);
        return query.list();
    }

}
