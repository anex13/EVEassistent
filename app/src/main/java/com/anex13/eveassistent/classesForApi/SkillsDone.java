package com.anex13.eveassistent.classesForApi;

/**
 * Created by it.zavod on 12.12.2016.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillsDone {

    @SerializedName("skills")
    @Expose
    private List<Skill> skills = null;
    @SerializedName("total_sp")
    @Expose
    private Integer totalSp;

    /**
     *
     * @return
     * The skills
     */
    public List<Skill> getSkills() {
        return skills;
    }

    /**
     *
     * @param skills
     * The skills
     */
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    /**
     *
     * @return
     * The totalSp
     */
    public Integer getTotalSp() {
        return totalSp;
    }

    /**
     *
     * @param totalSp
     * The total_sp
     */
    public void setTotalSp(Integer totalSp) {
        this.totalSp = totalSp;
    }

}