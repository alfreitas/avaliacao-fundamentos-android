package com.example.administrador.myapplication.models.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrador.myapplication.models.persistence.ServiceOrdersRepository;
import com.example.administrador.myapplication.models.persistence.UsersRepository;

import java.util.List;

/**
 * Created by Administrador on 28/05/2015.
 */
public class User implements Parcelable {

    private Integer mId;
    private String mLogin;
    private String mPassword;

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String mULogin) {
        this.mLogin = mULogin;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mLogin);
        dest.writeString(this.mPassword);
    }

    public User() {
    }

    private User(Parcel in) {
        this.mId = in.readInt();
        this.mLogin = in.readString();
        this.mPassword = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public static List<ServiceOrder> getAll() {
        return ServiceOrdersRepository.getInstance().getAll();
    }

    public void save() {
        UsersRepository.getInstance().save(this);
    }

    public void delete() {
        UsersRepository.getInstance().delete(this);
    }
}
