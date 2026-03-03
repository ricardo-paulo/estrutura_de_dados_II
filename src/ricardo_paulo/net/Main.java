package ricardo_paulo.net;

public class Main {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        Student student1 = new Student("Paulo Ricardo", 10, 9);
        Student student2 = new Student("Alice Zuberg", 9, 9);
        Student student3 = new Student("Walter de Alencar Ramos", 8, 10);
        Student student4 = new Student("Ana Clara de Jesus", 8, 10);

        binaryTree.addStudent(student1);
        binaryTree.addStudent(student2);
        binaryTree.addStudent(student3);
        binaryTree.addStudent(student4);

    }

}
