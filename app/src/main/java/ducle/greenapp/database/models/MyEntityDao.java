package ducle.greenapp.database.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

@Dao
public interface MyEntityDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(T ... entities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(T entity);

    @Delete
    void delete(T ... entities);

    @Update
    void update(T ... entities);
}
