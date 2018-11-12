import static java.lang.System.out;

import domain.Task;
import domain.TaskType;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Task2 {

    public static void main(String[] args) {
        List<Task> tasks = Task.getTasks();

        /*List<Task> readingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getType() == TaskType.READING) {
                readingTasks.add(task);
            }
        }
        Collections.sort(readingTasks, new Comparator<Task>() {
            @Override
            public int compare(Task t1, Task t2) {
                return t1.getTitle().length() - t2.getTitle().length();
            }
        });
        for (Task readingTask : readingTasks) {
            out.println(readingTask.getTitle());
        }*/
        List<String> readingTasksTitlesSortedByDate = getTaskTitles(sortReadingTasksByDate(filterReadingTasks(tasks)));
        System.out.println("Tasks sorted by date: -> " + readingTasksTitlesSortedByDate);
        Collections.reverse(readingTasksTitlesSortedByDate);
        System.out.println("Tasks sorted by date in reverse order -> " + readingTasksTitlesSortedByDate);
        System.out.println("Distinct tasks -> " + findDistinctTasks(tasks));
        System.out.println("Distinct tags -> " + findAllDistinctTags(tasks));
        System.out.println("Do all READING tasks contain the 'book' tag?   - " + checkBookTagInReadingTasks(tasks));
        List<String> allTaskTitles = getTaskTitles(tasks.stream().collect(Collectors.toList()));
        System.out.println("All tasks by title -> " + allTaskTitles);


    }



    public static List<Task> sortReadingTasksByDate(List<Task> tasks){
        Comparator<Task> dateComparator = (t1, t2) -> t1.getCreatedOn().compareTo(t2.getCreatedOn());

        return tasks.stream().sorted(dateComparator).collect(Collectors.toList());
    }

    public static Set<Task> findDistinctTasks(List<Task> tasks){
        return tasks.stream().collect(Collectors.toSet());

    }

    public static Set<String> findAllDistinctTags(List<Task> tasks){
        Set<Set<String>> distinctTagSets = tasks.stream().map(t -> t.getTags()).collect(Collectors.toSet());
        return distinctTagSets.stream().flatMap(Set :: stream).collect(Collectors.toSet());
    }

    public static boolean checkBookTagInReadingTasks(List<Task> readingTasks){
        Predicate<Task> bookPredicate = p -> p.getTags().contains("books");
        return readingTasks.stream().allMatch(bookPredicate);
    }



    public static List<String> getTaskTitles(Collection<Task> tasks){
        List<String> taskTitles = new ArrayList<>();
        for(Task task : tasks){
            taskTitles.add(task.getTitle());
        }

        return taskTitles;
    }

    private static List<Task> filterReadingTasks(List<Task> tasks){
        Predicate<Task> readingTasksPredicate = p -> p.getType() == TaskType.READING;
        return tasks.stream().filter(readingTasksPredicate).collect(Collectors.<Task>toList());
    }
}