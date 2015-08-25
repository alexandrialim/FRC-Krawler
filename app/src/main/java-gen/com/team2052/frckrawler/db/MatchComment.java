package com.team2052.frckrawler.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table "MATCH_COMMENT".
 */
public class MatchComment implements java.io.Serializable {

    private Long id;
    private Long match_number;
    private Integer game_type;
    private Long robot_id;
    private Long event_id;
    private String comment;

    public MatchComment() {
    }

    public MatchComment(Long id) {
        this.id = id;
    }

    public MatchComment(Long id, Long match_number, Integer game_type, Long robot_id, Long event_id, String comment) {
        this.id = id;
        this.match_number = match_number;
        this.game_type = game_type;
        this.robot_id = robot_id;
        this.event_id = event_id;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatch_number() {
        return match_number;
    }

    public void setMatch_number(Long match_number) {
        this.match_number = match_number;
    }

    public Integer getGame_type() {
        return game_type;
    }

    public void setGame_type(Integer game_type) {
        this.game_type = game_type;
    }

    public Long getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(Long robot_id) {
        this.robot_id = robot_id;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
