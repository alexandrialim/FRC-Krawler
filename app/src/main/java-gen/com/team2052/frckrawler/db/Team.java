package com.team2052.frckrawler.db;

import java.util.List;
import com.team2052.frckrawler.db.DaoSession;
import de.greenrobot.dao.DaoException;

import com.google.gson.JsonElement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "TEAM".
 */
public class Team implements java.io.Serializable {

    private Long number;
    private String teamkey;
    private String name;
    private JsonElement data;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient TeamDao myDao;

    private List<Robot> robotList;

    public Team() {
    }

    public Team(Long number) {
        this.number = number;
    }

    public Team(Long number, String teamkey, String name, JsonElement data) {
        this.number = number;
        this.teamkey = teamkey;
        this.name = name;
        this.data = data;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeamDao() : null;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getTeamkey() {
        return teamkey;
    }

    public void setTeamkey(String teamkey) {
        this.teamkey = teamkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Robot> getRobotList() {
        if (robotList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RobotDao targetDao = daoSession.getRobotDao();
            List<Robot> robotListNew = targetDao._queryTeam_RobotList(number);
            synchronized (this) {
                if(robotList == null) {
                    robotList = robotListNew;
                }
            }
        }
        return robotList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetRobotList() {
        robotList = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
