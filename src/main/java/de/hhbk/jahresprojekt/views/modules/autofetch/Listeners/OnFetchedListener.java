package de.hhbk.jahresprojekt.views.modules.autofetch.Listeners;

import java.util.List;

public interface OnFetchedListener<T> {
    public void run(List<T> list);
}
