package pattern.impl;

import android.util.Log;

import pattern.IStrategyHandler;

public class DivRule implements IStrategyHandler {
    private static final String TAG = "Rule";
    private int a, b;
    private String op;

    public DivRule(int a, int b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    @Override
    public boolean accord() {
        return op.equals("/");
    }

    @Override
    public void handle() {
        int c = a / b;
        Log.d(TAG, "apply: " + c);
    }

}
