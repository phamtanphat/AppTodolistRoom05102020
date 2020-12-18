package com.example.apptodolistroom05102020.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.util.TableInfo;

@Entity(tableName = "Word")
public class WordEntity {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "en")
    private String en;

    @ColumnInfo(name = "vn")
    private String vn;

    @ColumnInfo(name = "ismemorized" , defaultValue = "0")
    private Integer ismemorized;

    @Ignore
    public WordEntity(String en, String vn, Integer ismemorized) {
        this.en = en;
        this.vn = vn;
        this.ismemorized = ismemorized;
    }

    public WordEntity(int id , String en, String vn, Integer ismemorized) {
        this.id = id;
        this.en = en;
        this.vn = vn;
        this.ismemorized = ismemorized;
    }

    public WordEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getVn() {
        return vn;
    }

    public void setVn(String vn) {
        this.vn = vn;
    }

    public Integer getIsmemorized() {
        return ismemorized;
    }

    public void setIsmemorized(Integer ismemorized) {
        this.ismemorized = ismemorized;
    }
}
