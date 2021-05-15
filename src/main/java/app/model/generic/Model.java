package app.model.generic;

public abstract class Model implements Comparable<Model> {
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                '}';
    }

    @Override
    public int compareTo(Model model) {
        return -Integer.compare(id, model.id);
    }
}
