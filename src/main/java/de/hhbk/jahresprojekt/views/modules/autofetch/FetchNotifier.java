package de.hhbk.jahresprojekt.views.modules.autofetch;

import de.hhbk.jahresprojekt.views.modules.autofetch.Listeners.FetchRequestListener;

import java.util.ArrayList;
import java.util.List;

public class FetchNotifier {
    private static FetchNotifier fetchNotifier;

    public static FetchNotifier getInstance(){
        if(fetchNotifier == null) fetchNotifier = new FetchNotifier();
        return fetchNotifier;
    }

    private List<FetchRequestListener> fetchRequestListeners;

    private FetchNotifier(){
        fetchRequestListeners = new ArrayList<>();
    }

    public void addListener(FetchRequestListener fetchRequestListener){
        fetchRequestListeners.add(fetchRequestListener);
    }

    public void requestFetch(){
        fetchRequestListeners.stream().forEach(FetchRequestListener::request);
    }
}
