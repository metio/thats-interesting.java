# That's Interesting (TI) ![Verify](https://github.com/metio/thats-interesting.java/workflows/Verify/badge.svg)

*That's Interesting* is a strongly typed logging framework targeting applications that want to aggregate their log messages into a central logging system, e.g. a [Elasticsearch](https://www.elastic.co/) cluster.

## Usage

The library works by defining logger interfaces (called **point of interest**) such as: 

```java
package your.own.pkg;

public interface YourOwnPOI {

    void somethingInteresting(YourPOJO pojo);

    void businessWarning(YourCustomer pojo);

}
```

You use that interface together with the `Interested` class like this to create an instance at runtime and log something that is of interest in your application:

```java
package your.own.pkg;

import wtf.metio.ti.Interested;
import wtf.metio.ti.handler.StandardInvocationHandlers;

public class YourOwnClass {

    private static final YourOwnPOI poi = Interested.in(YourOwnPOI.class)
                .invocationHandler(StandardInvocationHandlers.systemOut())
                .createLogger();

    public void yourBusinessMethod(YourPOJO pojo) {
        poi.somethingInteresting(pojo);
    }

    public void yourBusinessWarning(YourCustomer customer) {
        poi.businessWarning(customer);
    }

}
```

With its current default settings the above call will result in something like this on `System.out`:

```
Class: [your.own.package.YourOwnPOI] Method: [somethingInteresting] Arguments: [pojo: {field1: value1, field2: value2}]
```

You probably don't want to write to `System.out` but log into a file on your filesystem like this:

```java
package your.own.pkg;

import static wtf.metio.ti.handler.StandardInvocationHandlers.logFile;

import wtf.metio.ti.Interested;
import java.nio.file.Paths;

public class YourOwnClass {

    private static final YourOwnPOI poi = Interested.in(YourOwnPOI.class)
                .invocationHandler(logFile(Paths.get("target/file.log")))
                .createLogger();

}
```

You can customize the outgoing message like this:

```java
package your.own.pkg;

import static wtf.metio.ti.converter.StandardConverters.stringFormat;
import static wtf.metio.ti.sink.StandardSinks.logFile;

import wtf.metio.ti.Interested;
import java.nio.file.Paths;

public class YourOwnClass {

    private static final YourOwnPOI poi = Interested.in(YourOwnPOI.class)
                .buildHandler()
                .converter(stringFormat("%s is the class, %s is the method, %s are the args"))
                .sinks(fileAppender(Paths.get("target/file.log")))
                .createLogger();

}
```

However, that is still no fun since you probably want to aggregate all your logs from all your applications in a central place like an Elasticsearch cluster, or your next best Kafka broker. Users of the ELK stack can ignore Logstash and push directly into ES like this:

```java
package your.own.pkg;

import static wtf.metio.ti.handler.ElasticsearchInvocationHandlers.elasticsearch;

import wtf.metio.ti.Interested;

public class YourOwnClass {

    private static final YourOwnPOI poi = Interested.in(YourOwnPOI.class)
                .invocationHandler(elasticsearch("localhost:9300", "indexName"))
                .createLogger();

}
```

The above configuration sends objects similar to the following JSON:

```json
{
  "class": "your.own.package.YourOwnPOI",
  "method": "somethingInteresting",
  "arguments": {
    "pojo": {
      "field1": "value1",
      "field2": "value2"
    }
  }
}
```

If you want to add additional key-value pairs, do the following:

```java
package your.own.pkg;

import static wtf.metio.ti.converter.JacksonConverters.json;
import static wtf.metio.ti.sink.ElasticsearchSinks.elasticsearch;

import wtf.metio.ti.Interested;
import java.time.LocalDateTime;

public class YourOwnClass {

    private static final YourOwnPOI poi = Interested.in(YourOwnPOI.class)
                .buildHandler()
                .converter(json())
                .sinks(elasticsearch("localhost:9300", "indexName"))
                .withStaticExtra("application", "yourCoolApp")
                .withStaticExtra("hostname", "your.custom.domain.tld")
                .withSuppliedExtra("timestamp", () -> LocalDateTime.now())
                .createLogger();

}
```

Which then produces:

```json
{
  "class": "your.own.package.YourOwnPOI",
  "method": "somethingInteresting",
  "arguments": {
    "pojo": {
      "field1": "value1",
      "field2": "value2"
    }
  },
  "application": "yourCoolApp",
  "hostname": "your.custom.domain.tld",
  "timestamp": "your-local-date-time"
}
```

## License

```
To the extent possible under law, the author(s) have dedicated all copyright
and related and neighboring rights to this software to the public domain
worldwide. This software is distributed without any warranty.

You should have received a copy of the CC0 Public Domain Dedication along
with this software. If not, see http://creativecommons.org/publicdomain/zero/1.0/.
```

## Mirrors

- https://github.com/metio/thats-interesting.java
