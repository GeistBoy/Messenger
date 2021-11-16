package xyz.theprojecttool.messenger.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import xyz.theprojecttool.messenger.database.entity.Notification;

@Dao
public interface NotificationDao {

    @Insert
    Completable insertNotification(Notification input);

    @Query("SELECT * from notification")
    Flowable<List<Notification>> getAll();

    @Delete()
    void deleteAll(List<Notification> targets);
}
