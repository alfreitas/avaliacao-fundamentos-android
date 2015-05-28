package com.example.administrador.myapplication.models.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.models.entities.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrador on 28/05/2015.
 */
public class UserContract {

    public static final String TABLE = "user";
    public static final String ID = "id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String LOGIN_DEFAULT = "freitas";
    public static final String PASSWORD_DEFAULT = "123456";

    public static final String[] COLUNS = {ID, LOGIN, PASSWORD};

    public static String createTable() {
        final StringBuilder sql = new StringBuilder();
        sql.append(" CREATE TABLE ");
        sql.append(TABLE);
        sql.append(" ( ");
        sql.append(ID + " INTEGER PRIMARY KEY, ");
        sql.append(LOGIN + " TEXT, ");
        sql.append(PASSWORD + " TEXT ");
        sql.append(" ); ");
        sql.append("INSERT INTO ");
        sql.append(TABLE);
        sql.append("(");
        sql.append(ID+",");
        sql.append(LOGIN+",");
        sql.append(PASSWORD+")");
        sql.append(" VALUES('");
        sql.append("'"+LOGIN_DEFAULT+"','"+PASSWORD_DEFAULT+"')");
        return sql.toString();
    }

    public static String createDefaultUser() {
        final StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(TABLE);
        sql.append("(");
        sql.append(ID +",");
        sql.append(LOGIN+",");
        sql.append(PASSWORD+")");
        sql.append(" VALUES(");
        sql.append("1,'freitas','123456')");
        return sql.toString();
    }

    public static ContentValues getContentValues(User user) {
        ContentValues content = new ContentValues();
        content.put(ID, user.getId());
        content.put(LOGIN, user.getLogin());
        content.put(PASSWORD, user.getPassword());
        return content;
    }

    public static User bind(Cursor cursor) {
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            User user = new User();
            user.setId((cursor.getInt(cursor.getColumnIndex(ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            return user;
        }
        return null;
    }

    public static List<User> bindList(Cursor cursor) {
        final List<User> users = new ArrayList<User>();
        while (cursor.moveToNext()) {
            users.add(bind(cursor));
        }
        return users;
    }
}
