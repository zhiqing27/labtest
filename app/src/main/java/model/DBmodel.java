package model;

public class DBmodel {
    String strSubjectCode;
    String strSubjectName;
    int strCreditHour;
    String strGrade;

    public DBmodel() {
    }


    public String getStrSubjectCode() {
        return strSubjectCode;
    }
    public void setStrSubjectCode(String strSubjectCode)
    {
        this.strSubjectCode = strSubjectCode;
    }
    public String getStrSubjectName() {
        return strSubjectName;
    }
    public void setStrSubjectName(String strSubjectName)
    {
        this.strSubjectName = strSubjectName;
    }
    public int getStrCreditHour() {
        return strCreditHour;
    }
    public void setStrCreditHour(int strCreditHour)
    {
        this.strCreditHour =strCreditHour ;
    }
    public String getStrGrade() {
        return strGrade;
    }
    public void setStrGrade(String strGrade)
    {
        this.strGrade = strGrade;
    }

}


