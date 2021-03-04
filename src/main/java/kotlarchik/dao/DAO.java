package kotlarchik.dao;

import java.util.List;

public interface DAO <Entity, Key>{
    Entity read (Key key);
    List<Entity> readAll();
}
