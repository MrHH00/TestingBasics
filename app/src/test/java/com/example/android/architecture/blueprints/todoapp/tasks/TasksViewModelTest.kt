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

// Cấu hình Robolectric để sử dụng SDK 30, có thể bỏ khi hỗ trợ SDK 31
@Config(sdk = [30])
@RunWith(AndroidJUnit4::class)
class TasksViewModelTest {

    // Đối tượng cần kiểm thử
    private lateinit var tasksViewModel: TasksViewModel

    // Luật này giúp cho các tác vụ LiveData được thực thi ngay lập tức khi kiểm thử
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        // Khởi tạo ViewModel trước mỗi bài kiểm thử
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

//    // Bài kiểm thử cho Task 4
//    @Test
//    fun addNewTask_setsNewTaskEvent() {
//
//        // Cho trước một ViewModel mới
//        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
//
//        // When adding a new task
//        tasksViewModel.addNewTask()
//    }

//    //Bài kiểm thử cho Task 5
//    @Test
//    fun addNewTask_setsNewTaskEvent() {
//        // Cho trước một ViewModel mới
//        val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
//
//        // Khi thêm một Task mới
//        tasksViewModel.addNewTask()
//
//        // Sau đó, lấy giá trị của LiveData newTaskEvent
//        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
//             //Kiểm tra xem sự kiện có được kích hoạt không
//        assertThat(value.getContentIfNotHandled(), not(nullValue()))
//    }
//
     // Bài kiểm thử cho Task 6
    @Test
    fun addNewTask_setsNewTaskEvent() {

        // Khi thêm một Task mới
        tasksViewModel.addNewTask()

        // Sau đó, lấy giá trị của LiveData newTaskEvent
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        // Kiểm tra xem sự kiện có được kích hoạt không
        assertThat(
            value?.getContentIfNotHandled(), (not(nullValue()))
        )
    }

    @Test
    fun getTasksAddViewVisible() {

        // Khi thiết lập bộ lọc là ALL_TASKS (tất cả các Task)
        tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Nút "Thêm Task" cần được hiển thị
        assertThat(tasksViewModel.tasksAddViewVisible.getOrAwaitValue(), `is`(true))
    }

}