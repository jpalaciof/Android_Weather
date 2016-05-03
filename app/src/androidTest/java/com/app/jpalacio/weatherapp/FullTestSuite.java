package com.app.jpalacio.weatherapp;

import android.test.suitebuilder.TestSuiteBuilder;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Created by jpalacio on 4/28/16.
 */
public class FullTestSuite {
    public static Test Suite() {
        return new TestSuiteBuilder(FullTestSuite.class)
                .includeAllPackagesUnderHere().build();
    }

    public FullTestSuite() { super(); }
}
