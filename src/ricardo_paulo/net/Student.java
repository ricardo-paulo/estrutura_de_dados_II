package ricardo_paulo.net;

public class Student {

    private static int usersCounter;
    public int registry;
    public String name;
    public int note1;
    public int note2;
    public Student left;
    public Student right;

    public Student(String name, int note1, int note2) {
        registry = ++usersCounter;
        this.name = name;
        this.note1 = note1;
        this.note2 = note2;
        this.left = null;
        this.right = null;
    }

}
