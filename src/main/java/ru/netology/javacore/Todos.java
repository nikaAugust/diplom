package ru.netology.javacore;

import java.util.*;

public class Todos {
    ArrayList<String> list = new ArrayList<>();

    public void addTask(String task) {
        list.add(task);
    }

    public void removeTask(String task) {
        list.remove(task);
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        for (String task : list) {
            sb.append(task + " ");
        }
        return sb.toString();
    }

}
