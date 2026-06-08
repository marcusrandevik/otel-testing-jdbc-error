An example project where we fail to initialize the otel JDBC wrapper on versions `1.63.0` when it works on `1.62.0`.

It follows the documented approach of manually setting up jdbc instrumentation as seen [here](https://github.com/open-telemetry/opentelemetry-java-instrumentation/blob/main/instrumentation/jdbc/library/README.md#datasource-way)


Steps to reproduce:

- Set up a local postgres server (the code defaults to port 8989 to ensure we connect to one specifically set up for this test).
- ./gradlew run