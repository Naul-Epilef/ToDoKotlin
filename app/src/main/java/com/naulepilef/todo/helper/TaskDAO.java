package com.naulepilef.todo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.naulepilef.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO{
    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public TaskDAO(Context context) {
        DbHelper db = new DbHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean save(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("description", task.getDescription());
        try {
            write.insert(DbHelper.TABLE_TASKS, null, cv);
            Log.i("DATABASE", "SUCCEEDED");
        }catch (Exception e){
            Log.e("DATABASE", "ERROR SAVING TASK!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("description", task.getDescription());

        try {
            String[] args = {task.getId().toString()};
            write.update(DbHelper.TABLE_TASKS, cv, "id = ?", args);
            Log.i("DATABASE", "SUCCEEDED | " + task.getDescription() + " | ");
        }catch (Exception e){
            Log.e("DATABASE", "ERROR UPDATING TASK!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Task task) {
        try {
            String[] args = {task.getId().toString()};
            write.delete(DbHelper.TABLE_TASKS, "id = ?", args);
            Log.i("DATABASE", "DELETED");
        }catch (Exception e){
            Log.e("DATABASE", "ERROR DELETING TASK!" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Task> list() {
        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_TASKS + " ;";
        Cursor c = read.rawQuery(sql, null);

        while (c.moveToNext()){
            Task task = new Task();

            Long id = c.getLong(Math.max(c.getColumnIndex("id"), 0));
            String description = c.getString(Math.max(c.getColumnIndex("description"), 0));

            task.setId(id);
            task.setDescription(description);

            tasks.add(task);
        }
        c.close();

        return tasks;
    }
}
