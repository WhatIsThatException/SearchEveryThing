package controller.service;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Created by kpant on 7/13/17.
 */
public class CacheFileService extends Service<Void> {

    private String locationToCache;
    public CacheFileService(String locationToCache){
        this.locationToCache = locationToCache;
    }
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                return null;
            }
        };
    }
}
