package pattern;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class StrategyRouter implements IStrategyRouter {
    private final Set<IStrategyHandler> strategyHandlers = new LinkedHashSet<>();

    @Override
    public void accept(IStrategyHandler... handlers) {
        strategyHandlers.addAll(Arrays.asList(handlers));
    }

    @Override
    public void handle() {
        for (IStrategyHandler handler : strategyHandlers) {
            boolean accord = handler.accord();
            if (accord) {
                handler.handle();
                break;
            }
        }
    }
}
