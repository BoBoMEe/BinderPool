package pattern.impl;

import android.util.Log;

import pattern.IStrategyHandler;

public class AddRule implements IStrategyHandler {
    private static final String TAG = "Rule";
    private int a, b;
    private String op;

    public AddRule(int a, int b, String op) {
        this.a = a;
        this.b = b;
        this.op = op;
    }

    @Override
    public boolean accord() {
        return "+".equals(op);
    }

    @Override
    public void handle() {
        int c = a + b;
        Log.d(TAG, "apply: " + c);
    }

}
