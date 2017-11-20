package com.example.huascar.checkd;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by huascar on 17/11/2017.
 */

public class TaskTest {

    private Task task;

    @Before
    public void before() {
        task = new Task();
        task.setId(1);
        task.setTitle("Shop");
        task.setDescription("Shopping");
        task.setCompleted("True");
    }

    @Test
    public void testGetTitle() {
        assertEquals("Shop", task.getTitle() );
    }

    @Test
    public void testGetDescription() {
        assertEquals("Shopping", task.getDescription());
    }

    @Test
    public void testGetCompleted() {
        assertEquals("True", task.getCompleted().toString());
    }


}
