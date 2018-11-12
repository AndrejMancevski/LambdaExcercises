import static domain.Task.getTasks;
import static java.lang.System.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import domain.Task;
import domain.TaskType;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Task3 {

    public static void main(String[] args) {
        List<Task> tasks = getTasks();
        Map<TaskType, List<Task>> tasksByType = tasks.stream().collect(groupingBy(Task::getType));
        for(TaskType type : tasksByType.keySet()){
            System.out.print(type.toString() + " => ");
            System.out.println(tasksByType.get(type));
        }
        System.out.println("Task with the longest title -> " + findTaskWithLongestTitle(tasks).get(0).getTitle());
        System.out.println("Total number of tags -> " + countTotalNumberOfTags(tasks));

        Map<TaskType, Map<LocalDate, List<Task>>> tasksByTypeAndDate = tasks.stream().collect(
                groupingBy(Task::getType,
                        groupingBy(Task::getCreatedOn)
                )
        ) ;
        for(TaskType type : tasksByTypeAndDate.keySet()){
            System.out.println(type.toString() + " tasks by date => ");
            for(LocalDate ld : tasksByTypeAndDate.get(type).keySet()){
                System.out.print(ld.toString() + " -> ");
                System.out.println(tasksByTypeAndDate.get(type).get(ld));
            }
        }
    }

    public static List<Task> findTaskWithLongestTitle(List<Task> tasks){
        return tasks.stream()
                .sorted(Comparator.comparingInt(t -> t.getTitle().length()))
                .collect(Collectors.toList());
    }

    public static int countTotalNumberOfTags(List<Task> tasks){
        List<Set<String>> distinctTagSets = tasks.stream().map(t -> t.getTags()).collect(Collectors.toList());
        return distinctTagSets.stream().flatMap(Set::stream).collect(toList()).size();
    }
}
