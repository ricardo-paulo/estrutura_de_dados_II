package ricardo_paulo.net;

public class BinaryTree {

    Student root;

    public BinaryTree () {
        this.root = null;
    }

    public void addStudent (Student newStudent) {
        if (root == null) {
            root = newStudent;
        } else {
            addStudentRecursively(newStudent, root);
        }
    }

    private void addStudentRecursively (Student newStudent, Student current) {
        int compareResult = newStudent.name.compareToIgnoreCase(current.name);

        // Se compareResult < 0, então newStudent vem primeiro que current na ordem alfabética. Então o aluno vai para
        // a ESQUERDA.
        // Caso contrário (compareResult > 0), então newStudent vem depois que current na ordem alfabética. Ou seja,
        // o aluno vai para a DIREITA.
        // Se compareTo = 0, então o aluno vai para a ESQUERDA.

        if (compareResult <= 0) {
            if (current.left == null) {
                current.left = newStudent;
            } else {
                addStudentRecursively(newStudent, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = newStudent;
            } else {
                addStudentRecursively(newStudent, current.right);
            }
        }
    }

}
