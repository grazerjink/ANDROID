package com.example.graze.mydatabasedemo;

/**
 * Created by graze on 25/05/2016.
 */
public class Country {

    public static final String TABLE_NAME = "Country";
    public static final String COL_ID = "_id" ;
    public static final String COL_NAME_EN = "nameEn";
    public static final String COL_NAME_VI = "nameVi";
    public static final String COL_FLAG = "flag";
    public static final String COL_POPULATION = "population";

    long _id;
    String nameEn;
    String nameVi;
    String flag;
    long population;

    public Country() {
    }

    public Country(long _id, String nameEn, String nameVi, String flag, long population) {
        this._id = _id;
        this.nameEn = nameEn;
        this.nameVi = nameVi;
        this.flag = flag;
        this.population = population;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameVi() {
        return nameVi;
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
