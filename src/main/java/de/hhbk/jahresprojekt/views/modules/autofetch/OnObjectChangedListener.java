package de.hhbk.jahresprojekt.views.modules.autofetch;

import java.lang.reflect.InvocationTargetException;

public interface OnObjectChangedListener<T> {
    public void changed(T nValue);
}
