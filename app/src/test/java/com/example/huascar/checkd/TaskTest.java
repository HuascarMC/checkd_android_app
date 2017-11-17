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
        task = new Task("Go shopping", "Go to tesco next Saturday 3pm", false);
    }

    @Test
    public void testGetTitle() {
        assertEquals("Go shopping", task.getTitle() );
    }



}
