package com.example.android.architecture.blueprints.todoapp.tasks

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [30]) // Remove when Robolectric supports SDK 31
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    // Subject under test
    private lateinit var tasksViewModel: TasksViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    //Task 4
//    @Test
//    fun addNewTask_setsNewTaskEvent() {
//
//        // Given a fresh ViewModel
//        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
//
//        // When adding a new task
//        tasksViewModel.addNewTask()
//
//        // Then the new task event is triggered
//        // TODO test LiveData
//    }

    //Task 5
//    @Test
//    fun addNewTask_setsNewTaskEvent() {
//        // Given a fresh ViewModel
//        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
//
//        // When adding a new task
//        tasksViewModel.addNewTask()
//
//        // Then the new task event is triggered
//        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
//
//        assertThat(value.getContentIfNotHandled(), not(nullValue()))
//    }

    // Task 6
    @Test
    fun addNewTask_setsNewTaskEvent() {

        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(
            value?.getContentIfNotHandled(), (not(nullValue()))
        )
    }

    @Test
    fun getTasksAddViewVisible() {

        // When the filter type is ALL_TASKS
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

}