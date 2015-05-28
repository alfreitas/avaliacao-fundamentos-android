package com.example.administrador.myapplication.models.persistence;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.models.entities.User;
import com.example.administrador.myapplication.util.AppUtil;

import java.util.List;

/**
 * Created by Administrador on 28/05/2015.
 */
public class UsersRepository {

    private static class Singleton {
        public static final UsersRepository INSTANCE = new UsersRepository();
    }

    private UsersRepository() {
        super();
    }

    public static UsersRepository getInstance() {
        return Singleton.INSTANCE;
    }

    public void save(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        if (user.getId() == null) {
            db.insert(UserContract.TABLE, null, UserContract.getContentValues(user));
        } else {
            String where = UserContract.ID + " = ?";
            String[] args = {user.getId().toString()};
            db.update(UserContract.TABLE, UserContract.getContentValues(user), where, args);
        }
        db.close();
        helper.close();
    }

    public void delete(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getWritableDatabase();
        String where = UserContract.ID + " = ?";
        String[] args = {user.getId().toString()};
        db.delete(UserContract.TABLE, where, args);
        db.close();
        helper.close();
    }

    public List<User> getAll() {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, null, null, null, null,UserContract.LOGIN);
        List<User> user = UserContract.bindList(cursor);
        db.close();
        helper.close();
        return user;
    }

    public Boolean validateLoginUser(User user) {
        DatabaseHelper helper = new DatabaseHelper(AppUtil.CONTEXT);
        SQLiteDatabase db = helper.getReadableDatabase();
        String where = UserContract.LOGIN+"='"+user.getLogin()+"' and "+UserContract.PASSWORD+"='"+user.getPassword()+"'";
        Cursor cursor = db.query(UserContract.TABLE, UserContract.COLUNS, where, null, null, null,null);
        List<User> users = UserContract.bindList(cursor);
        db.close();
        helper.close();
        return users.size()>0;
    }
}
