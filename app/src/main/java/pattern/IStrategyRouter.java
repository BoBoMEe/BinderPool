package pattern;

public interface IStrategyRouter {
    void accept(IStrategyHandler... handlers);

    void handle();
}
