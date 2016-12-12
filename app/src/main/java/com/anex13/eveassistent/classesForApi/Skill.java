package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 12.12.2016.
 */import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skill {

    @SerializedName("skill_id")
    @Expose
    private Integer skillId;
    @SerializedName("skillpoints_in_skill")
    @Expose
    private Integer skillpointsInSkill;
    @SerializedName("current_skill_level")
    @Expose
    private Integer currentSkillLevel;

    /**
     *
     * @return
     * The skillId
     */
    public Integer getSkillId() {
        return skillId;
    }

    /**
     *
     * @param skillId
     * The skill_id
     */
    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    /**
     *
     * @return
     * The skillpointsInSkill
     */
    public Integer getSkillpointsInSkill() {
        return skillpointsInSkill;
    }

    /**
     *
     * @param skillpointsInSkill
     * The skillpoints_in_skill
     */
    public void setSkillpointsInSkill(Integer skillpointsInSkill) {
        this.skillpointsInSkill = skillpointsInSkill;
    }

    /**
     *
     * @return
     * The currentSkillLevel
     */
    public Integer getCurrentSkillLevel() {
        return currentSkillLevel;
    }

    /**
     *
     * @param currentSkillLevel
     * The current_skill_level
     */
    public void setCurrentSkillLevel(Integer currentSkillLevel) {
        this.currentSkillLevel = currentSkillLevel;
    }

}
