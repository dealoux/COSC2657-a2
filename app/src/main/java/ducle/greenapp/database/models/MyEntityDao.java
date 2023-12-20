package ducle.greenapp.database.models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MyEntityDao<T extends MyEntity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(T ... entities);

    @Delete
    Completable delete(T ... entities);

    @Update
    Completable update(T ... entities);
}
