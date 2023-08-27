import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void TestMatchesForSimpleTaskWhenTrue() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        boolean actual = task.matches("родителям");

        Assertions.assertTrue(actual);
    }

    @Test
    public void TestMatchesForSimpleTaskWhenFalse() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");

        boolean actual = task.matches("дяде");

        Assertions.assertFalse(actual);
    }

    @Test
    public void NoTestMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить подарок"));
        todos.add(new Epic(2, new String[]{"Провести собрание"}));
        Task[] expected = {};
        Task[] actual = todos.search("Создать встречу");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void OneTestMatch() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить подарок"));
        todos.add(new Epic(2, new String[]{"Провести собрание"}));
        Task[] expected = {new SimpleTask(1, "Купить подарок")};
        Task[] actual = todos.search("подарок");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchingWithEmptyQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить подарок"));
        todos.add(new Epic(2, new String[]{"Провести собрание"}));
        Task[] expected = {new SimpleTask(1, "Купить подарок"), new Epic(2, new String[]{"Провести собрание"})};
        Task[] actual = todos.search("");
        Assertions.assertArrayEquals(expected, actual);
    }
}