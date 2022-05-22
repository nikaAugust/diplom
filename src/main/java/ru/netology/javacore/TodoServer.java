package ru.netology.javacore;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TodoServer {
    int port;
    Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }


    public void start() {
        System.out.println("Starting server at " + port + "...");
        JSONParser parser = new JSONParser();

        try (ServerSocket serverSocket = new ServerSocket(8989);) {
            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                ) {
                    Object obj = parser.parse(in.readLine());
                    JSONObject jsonObject = (JSONObject) obj;
                    String task = (String) jsonObject.get("task");
                    String typeOperation = (String) jsonObject.get("type");

                    if (typeOperation.equals("ADD")) {
                        todos.addTask(task);
                        out.println("Вы внесли задачу" + task);
                    }
                    if (typeOperation.equals("REMOVE")) {
                        todos.removeTask(task);
                        out.println("Вы удалили задачу " + task);
                    }
                    out.println(todos.getAllTasks());

                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
