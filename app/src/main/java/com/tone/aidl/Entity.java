package com.tone.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhaotong on 2016/8/8.
 */
public class Entity implements Parcelable{

    private String name;
    private int id;

    public Entity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Entity(Parcel in){
        id = in.readInt();
        name = in.readString();
    }
    public static final Creator<Entity> CREATOR = new Creator<Entity>() {
        @Override
        public Entity createFromParcel(Parcel in) {
            return new Entity(in);
        }

        @Override
        public Entity[] newArray(int size) {
            return new Entity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }
}
