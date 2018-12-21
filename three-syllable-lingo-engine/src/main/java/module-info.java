module three.syllable.lingo.engine {
    exports games.tsl.engine;
    exports games.tsl.engine.api;
    exports games.tsl.engine.api.exception;

    requires transitive commons.lang3;
    requires transitive commons.csv;

    opens games.tsl.engine;
}