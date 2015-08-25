package com.team2052.frckrawler.db;

import com.team2052.frckrawler.db.DaoSession;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table "ROBOT_PHOTO".
 */
public class RobotPhoto implements java.io.Serializable {

    private Long id;
    private String location;
    private Long robot_id;
    private String title;
    private java.util.Date date;

    /**
     * Used to resolve relations
     */
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    private transient RobotPhotoDao myDao;

    private Robot robot;
    private Long robot__resolvedKey;


    public RobotPhoto() {
    }

    public RobotPhoto(Long id) {
        this.id = id;
    }

    public RobotPhoto(Long id, String location, Long robot_id, String title, java.util.Date date) {
        this.id = id;
        this.location = location;
        this.robot_id = robot_id;
        this.title = title;
        this.date = date;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getRobotPhotoDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(Long robot_id) {
        this.robot_id = robot_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    public Robot getRobot() {
        Long __key = this.robot_id;
        if (robot__resolvedKey == null || !robot__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            RobotDao targetDao = daoSession.getRobotDao();
            Robot robotNew = targetDao.load(__key);
            synchronized (this) {
                robot = robotNew;
                robot__resolvedKey = __key;
            }
        }
        return robot;
    }

    public void setRobot(Robot robot) {
        synchronized (this) {
            this.robot = robot;
            robot_id = robot == null ? null : robot.getId();
            robot__resolvedKey = robot_id;
        }
    }

    /**
     * Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context.
     */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context.
     */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context.
     */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

}
