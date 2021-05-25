[![Build Status][build-badge]][build-link]
[![Release Artifacts][release-badge]][release-link]

# Sectery

Sectery is an digital assistant IRC bot.

## Producer

Message responses are coded in `Producer.apply` in
<src/main/scala/sectery/MessageQueues.scala>.

To add support for a new message response, add a `Rx => URIO[Clock,
Iterable[Tx]]` case to `Producer.apply`:

```scala
case Rx(c, _, "@foo") =>
  ZIO.effectTotal(Some(Tx(c, "bar")))
```

## References

### PircBotX

* https://github.com/pircbotx/pircbotx
* https://pircbotx.github.io/pircbotx/2.2/apidocs/index.html

### ZIO

* https://zio.dev/docs/overview/overview_index
* https://javadoc.io/doc/dev.zio/zio_2.12/1.0.8/zio/index.html

[build-badge]: https://github.com/earldouglas/sectery/workflows/build/badge.svg "Build Status"
[build-link]: https://github.com/earldouglas/sectery/actions "GitHub Actions"

[release-link]: https://oss.sonatype.org/content/repositories/releases/com/earldouglas/linear-scala/ "Sonatype Releases"
[release-badge]: https://img.shields.io/nexus/r/https/oss.sonatype.org/com.earldouglas/linear-scala "Sonatype Releases"
