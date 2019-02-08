package src.example.vo;

import com.google.gson.annotations.SerializedName;

public class HomeClassListVO {

    @SerializedName("class_name")
    String className;
    @SerializedName("class_info")
    String classInfo;
    @SerializedName("study_cost")
    int studyCost;
    @SerializedName("class_id")
    int classId;

    public HomeClassListVO(){};

    public HomeClassListVO(String className, String classInfo, int studyCost, int classId) {
        this.className = className;
        this.classInfo = classInfo;
        this.studyCost = studyCost;
        this.classId = classId;
    }

    public int getStudyCost() {
        return studyCost;
    }

    public void setStudyCost(int studyCost) {
        this.studyCost = studyCost;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(String classInfo) {
        this.classInfo = classInfo;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
