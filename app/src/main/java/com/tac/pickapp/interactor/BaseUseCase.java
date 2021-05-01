package com.tac.pickapp.interactor;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseUseCase {

    protected List<Thread> threads = new ArrayList<>();

    public void dispose() {
        for (Thread<?> thread: threads) {
            thread.dispose();
        }
    }
}
