package playground

import java.nio.charset.StandardCharsets
import java.util.Base64

object test extends App{
  val string = "hello . "
  if string.contains(".") then print("file") else println("directory")

  val path = "Hello"
  val encodedPath = Base64.getEncoder.encodeToString(path.getBytes(StandardCharsets.UTF_8))
  private val decoder: String => String = (decodePath) => new String(Base64.getDecoder.decode(decodePath), StandardCharsets.UTF_8)

  print(decoder(encodedPath))

}
