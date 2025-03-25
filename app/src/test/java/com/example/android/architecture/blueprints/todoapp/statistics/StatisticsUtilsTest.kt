package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class StatisticsUtilsTest {

    /**
     * Kiểm thử trường hợp danh sách chỉ có nhiệm vụ chưa hoàn thành.
     * Kết quả mong đợi:
     * - Phần trăm nhiệm vụ đang hoạt động = 100%
     * - Phần trăm nhiệm vụ đã hoàn thành = 0%
     */
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {
        val tasks = listOf(
            Task("title", "desc", isCompleted = false)
        )
        // Khi tính toán thống kê
        val result = getActiveAndCompletedStats(tasks)

        // Kiểm tra kết quả có đúng như mong đợi không
        assertThat(result.activeTasksPercent, `is`(100f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

    /**
     * Kiểm thử trường hợp danh sách chỉ có nhiệm vụ đã hoàn thành.
     * Kết quả mong đợi:
     * - Phần trăm nhiệm vụ đang hoạt động = 0%
     * - Phần trăm nhiệm vụ đã hoàn thành = 100%
     */
    @Test
    fun getActiveAndCompletedStats_noActive_returnsZeroHundred() {
        val tasks = listOf(
            Task("title", "desc", isCompleted = true)
        )
        // Khi tính toán thống kê
        val result = getActiveAndCompletedStats(tasks)

        // Kiểm tra kết quả có đúng như mong đợi không
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(100f))
    }

    /**
     * Kiểm thử trường hợp danh sách có cả nhiệm vụ đã hoàn thành và chưa hoàn thành.
     * Trong danh sách có:
     * - 3 nhiệm vụ đã hoàn thành
     * - 2 nhiệm vụ chưa hoàn thành
     * Tổng cộng có 5 nhiệm vụ, nên:
     * - Phần trăm nhiệm vụ đang hoạt động = (2/5) * 100 = 40%
     * - Phần trăm nhiệm vụ đã hoàn thành = (3/5) * 100 = 60%
     */
    @Test
    fun getActiveAndCompletedStats_both_returnsFortySixty() {
        // Given 3 completed tasks and 2 active tasks
        val tasks = listOf(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        // Khi tính toán thống kê
        val result = getActiveAndCompletedStats(tasks)

        // Kiểm tra kết quả có đúng như mong đợi không
        assertThat(result.activeTasksPercent, `is`(40f))
        assertThat(result.completedTasksPercent, `is`(60f))
    }

    /**
     * Kiểm thử trường hợp truyền vào danh sách null (có thể xảy ra lỗi khi tải dữ liệu).
     * Kết quả mong đợi:
     * - Phần trăm nhiệm vụ đang hoạt động = 0%
     * - Phần trăm nhiệm vụ đã hoàn thành = 0%
     */
    @Test
    fun getActiveAndCompletedStats_error_returnsZeros() {
        // Khi danh sách nhiệm vụ bị null
        val result = getActiveAndCompletedStats(null)

        // Kiểm tra kết quả có đúng như mong đợi không
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }

/**
 * Kiểm thử trường hợp danh sách nhiệm vụ rỗng.
 * Kết quả mong đợi:
 * - Phần trăm nhiệm vụ đang hoạt động = 0%
 * - Phần trăm nhiệm vụ đã hoàn thành = 0%
 **/

    @Test
    fun getActiveAndCompletedStats_empty_returnsZeros() {
    // Khi danh sách nhiệm vụ không có phần tử nào
        val result = getActiveAndCompletedStats(emptyList())

        // Kiểm tra kết quả có đúng như mong đợi không
        assertThat(result.activeTasksPercent, `is`(0f))
        assertThat(result.completedTasksPercent, `is`(0f))
    }
}