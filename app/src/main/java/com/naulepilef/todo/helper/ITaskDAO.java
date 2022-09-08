package com.naulepilef.todo.helper;

import com.naulepilef.todo.model.Task;

import java.util.List;

public interface ITaskDAO {
    public boolean save(Task task);
    public boolean update(Task task);
    public boolean delete(Task task);
    public List<Task> list();
}
