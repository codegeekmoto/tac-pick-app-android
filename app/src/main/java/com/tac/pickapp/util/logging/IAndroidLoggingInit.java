package com.tac.pickapp.util.logging;

import java.util.Map;

public interface IAndroidLoggingInit {
    Map<String, Integer> getLogLevels();

    int getRootLevel();
}
