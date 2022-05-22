package ru.netology.javacore;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodosTests {

    @BeforeAll
    public static void start() {
        System.out.println("Start tests");
    }

    @AfterAll
    public static void finish() {
        System.out.printf("Tests finished");
    }

    Todos sut = new Todos();

    @org.junit.jupiter.api.Test
    public void testAddTask1() {

        String task = "работать";
        ArrayList<String> list2 = new ArrayList<>();
        list2.add(task);

        sut.addTask(task);

        assertTrue(list2.equals(sut.list));
    }
    @Test
    public void testAddTask2() {

        String task = "идти";
        String expected = "идти ";

        sut.addTask(task);
        String result = sut.getAllTasks();
        assertThat(result, equalTo(expected));
    }

    @Test
    public void testRemoveTask() {
        String task1 = "идти";
        String task2 = "работать";
        String expected = "работать ";

        sut.addTask(task1);
        sut.addTask(task2);
        sut.removeTask("идти");
        String result = sut.getAllTasks();

        assertThat(result, equalTo(expected));
    }

    @org.junit.jupiter.api.Test
    public void testGetAllTasks() {
        // given:
        ArrayList<String> list2 = new ArrayList<>();
        String task1 = "идти";
        String task2 = "работать";
        String task3 = "радоваться жизни";
        list2.add(task1);
        list2.add(task2);
        list2.add(task3);

        Collections.sort(list2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list2.size(); i++) {
            sb.append(list2.get(i) + " ");
        }
        String result = sb.toString();

        sut.addTask("идти");
        sut.addTask("работать");
        sut.addTask("радоваться жизни");
        sut.getAllTasks();

        assertTrue(result.equals(sut.getAllTasks()));
    }
}
