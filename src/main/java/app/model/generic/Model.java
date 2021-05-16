package app.model.generic;

import java.util.Set;

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

    public Set<String> getFinal() {
        return Set.of("id");
    }

    @Override
    public int compareTo(Model model) {
        return -Integer.compare(id, model.id);
    }
}
