import static java.lang.System.*;

import domain.Task;
import domain.TaskType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {

    public static void main(String[] args) {
        List<Task> tasks = Task.getTasks();
        List<String> titles = filterReadingTasks(tasks);
        for (String title : titles) {
            out.println(title);
        }

    }

    /*private static List<String> taskTitles(List<Task> tasks) {
        List<String> readingTitles = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getType() == TaskType.READING) {
                readingTitles.add(task.getTitle());
            }
        }
        return readingTitles;
    }*/

    private static List<String> filterReadingTasks(List<Task> tasks){
        Predicate<Task> readingTasksPredicate = p -> p.getType() == TaskType.READING;
        List<Task> readingTasks =  tasks.stream().filter(readingTasksPredicate).collect(Collectors.<Task>toList());

        List<String> taskTitles = new ArrayList<>();
        for(Task task : readingTasks){
            taskTitles.add(task.getTitle());
        }

        return taskTitles;
    }

}
